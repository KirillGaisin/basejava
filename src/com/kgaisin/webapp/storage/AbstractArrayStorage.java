package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public abstract void save(Resume resume);

    public abstract void delete(String uuid);

    public void update(Resume resume) {
        int index = checkForResumePresence(resume.getUuid());
        if (checkForResumePresence(resume.getUuid()) >= 0) {
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
        if (checkForResumePresence(uuid) >= 0) {
            return storage[index];
        }
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

    protected abstract int checkForResumePresence(String uuid);
}
