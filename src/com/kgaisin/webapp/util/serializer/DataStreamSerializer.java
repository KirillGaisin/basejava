package com.kgaisin.webapp.util.serializer;

import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            writeSection(dos, r.getContacts().entrySet(), contacts -> {
                dos.writeUTF(contacts.getKey().name());
                dos.writeUTF(contacts.getValue().toString());
                if (contacts.getKey() == ContactType.MOBILE_PHONE || contacts.getKey() == ContactType.HOME_PHONE) {
                    dos.writeUTF(contacts.getValue().getName());
                } else {
                    dos.writeUTF(contacts.getValue().getName());
                    dos.writeUTF(contacts.getValue().getUrl());
                }
            });
            writeSection(dos, r.getSections().entrySet(), sections -> {
                SectionType sectionName = sections.getKey();
                AbstractSection section = sections.getValue();
                dos.writeUTF(sectionName.name());
                switch (sectionName) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeSection(dos, ((ListSection) section).getItems(), dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writeSection(dos, ((ExperienceSection) section).getPastPositions(), pos -> {
                            dos.writeUTF(pos.getLink().getName());
                            dos.writeUTF(pos.getLink().getUrl());
                            writeSection(dos, Arrays.asList(pos.getPeriod()), period -> {
                                writeDate(dos, period.getDateSince());
                                writeDate(dos, period.getDateUntil());
                                dos.writeUTF(period.getDescription().getHeader());
                                dos.writeUTF(period.getDescription().getContent());
                            });
                        });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            ContactType contactType = ContactType.valueOf(dis.readUTF());
            for (int i = 0; i < size; i++) {
                if (contactType == ContactType.MOBILE_PHONE || contactType == ContactType.HOME_PHONE) {
                    resume.addContact(contactType, new Link(dis.readUTF(), null));
                } else {
                    resume.addContact(contactType, new Link(dis.readUTF(), dis.readUTF()));
                }
            }
            int sectionSize = dis.readInt();
            SectionType sectionType = SectionType.valueOf(dis.readUTF());
            for (int i = 0; i < sectionSize; i++) {
                resume.addSection(sectionType, readSection(dis, sectionType));
            }
            return resume;
        }
    }

    private <T> void writeSection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType section) throws IOException {
        switch (section) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new ExperienceSection(readList(dis, () -> new Position(
                        new Link(dis.readUTF(), dis.readUTF()),
                        new Period(readDate(dis), readDate(dis), new TextSection(dis.readUTF()))
                )));
            default:
                throw new StorageException("Nonexistent section");
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.ofYearDay(dis.readInt(), dis.readInt());
    }

    private interface ElementWriter<T> {
        void write(T type) throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }
}
