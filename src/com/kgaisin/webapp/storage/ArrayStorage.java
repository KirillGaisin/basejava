package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10];

    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        //not null-safe
        if (checkForResumePresence(resume) >= 0) {
            System.out.println("----------------------------\n" +
                    "Resume with uuid " + resume.getUuid() + " is already in the storage! Enter another command");
            return;
        }

        //проверка на заполненность storage
        if (storage[storage.length - 1] != null) {
            System.out.println("----------------------------\n" +
                    "Resume storage is full. Delete entries or clear the storage to add a new one");
            return;
        }
        storage[size] = resume;
        size++;

        //проверка на наличие сохраненного резюме в storage
        if(checkForResumePresence(resume) >= 0) {
            return;
        }
        System.out.println("Resume with uuid " + resume.getUuid() + " was not added to the storage! Enter another command");
    }

    public void update(Resume resume) {
        if (checkForResumePresence(resume) >= 0) {
            System.out.println("----------------------------\n" +
                    "Resume with uuid " + resume.getUuid() + " updated.");
            return;
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + resume.getUuid() + " not found.");
    }

    public Resume get(String uuid) {
        Resume resumeToSearch = new Resume(uuid);
        if (checkForResumePresence(resumeToSearch) >= 0) {
            return resumeToSearch;
        }
        return null;
    }

    public void delete(String uuid) {
        Resume resumeToDelete = new Resume(uuid);
        if(checkForResumePresence(resumeToDelete) >= 0) {
            int elementToDelete = checkForResumePresence(resumeToDelete);
            storage[elementToDelete] = storage[size-1];
            storage[size-1] = null;
            size--;
            return;
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + uuid + " not found.");
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

    private int checkForResumePresence(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
