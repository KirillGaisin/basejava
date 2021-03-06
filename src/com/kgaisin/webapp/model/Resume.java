package com.kgaisin.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;

    // Unique identifier
    private String uuid;
    private String fullName;

    private Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public void setSections(Map<SectionType, AbstractSection> sections) {
        this.sections = sections;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public void addSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName) &&
                sections.equals(resume.sections) &&
                contacts.equals(resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, sections, contacts);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Resume{");
        sb.append("uuid='").append(uuid).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", sections=").append(sections);
        sb.append(", contacts=").append(contacts);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName) != 0 ? fullName.compareTo(o.fullName) : uuid.compareTo(o.uuid);
    }
}
