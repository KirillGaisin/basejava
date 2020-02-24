package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void addResume(Resume resume) {
        storage[size] = resume;
    }

    @Override
    public void removeResume(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    @Override
    protected int checkForResumePresence(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
