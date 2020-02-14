package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (checkForResumePresence(resume.getUuid()) >= 0) {
            System.out.println("----------------------------\n" +
                    "Resume with uuid " + resume.getUuid() + " is already in the storage! Enter another command");
            return;
        }

        //проверка на заполненность storage
        if (size >= storage.length) {
            System.out.println("----------------------------\n" +
                    "Resume storage is full. Delete entries or clear the storage to add a new one");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public void delete(String uuid) {
        if (checkForResumePresence(uuid) >= 0) {
            int index = checkForResumePresence(uuid);
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + uuid + " not found.");
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
