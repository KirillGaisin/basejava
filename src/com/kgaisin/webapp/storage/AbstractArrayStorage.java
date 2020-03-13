package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 4;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

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

    public void addResume(Resume resume, int index) {
        if (size >= storage.length) {
            throw new StorageException("Resume storage is full", resume.getUuid());
        }
        addResumeToArray(resume, index);
        size++;
    }

    public void removeResume(int index) {
        removeResumeFromArray(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void updateResume(Resume resume, int index) {
        storage[index] = resume;
        System.out.println("----------------------------\n" +
                "Resume with uuid " + resume.getUuid() + " updated.");
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    public abstract void addResumeToArray(Resume resume, int index);

    public abstract void removeResumeFromArray(int index);

    protected abstract int checkForResumePresence(String uuid);
}
