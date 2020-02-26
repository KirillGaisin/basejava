package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 4;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume resume) {
        // проверка на заполненность storage
        if (size >= storage.length) {
            System.out.println("----------------------------\n" +
                    "Resume storage is full. Delete entries or clear the storage to add a new one");
            return;
        }

        // проверка на присутствие в storage добавляемого резюме
        int index = checkForResumePresence(resume.getUuid());
        if (index >= 0) {
            System.out.println("----------------------------\n" +
                    "Resume with uuid " + resume.getUuid() + " is already in the storage! Enter another command");
            return;
        }

        addResume(resume, index);
        size++;
    }

    public void delete(String uuid) {
        int index = checkForResumePresence(uuid);
        if (index >= 0) {
            removeResume(index);
            storage[size - 1] = null;
            size--;
            return;
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + uuid + " not found.");
    }

    public void update(Resume resume) {
        int index = checkForResumePresence(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("----------------------------\n" +
                    "Resume with uuid " + resume.getUuid() + " updated.");
            return;
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + resume.getUuid() + " not found.");
    }

    public Resume get(String uuid) {
        int index = checkForResumePresence(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + uuid + " not found.");
        return null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public abstract void addResume(Resume resume, int index);

    public abstract void removeResume(int index);

    protected abstract int checkForResumePresence(String uuid);
}
