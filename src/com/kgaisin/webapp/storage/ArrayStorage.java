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
        if (checkForResumeDupe(resume.getUuid())) {
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

    public void update(Resume oldResume, Resume newResume) {
        if (checkForResumeDupe(newResume.getUuid())) {
            return;
        } else if (checkForResumePresence(oldResume.getUuid()) >= 0) {
            int index = checkForResumePresence(oldResume.getUuid());
            storage[index] = newResume;
            System.out.println("----------------------------\n" +
                    "Resume with uuid " + oldResume.getUuid() + " updated. New uuid is " + newResume.getUuid());
            return;
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + oldResume.getUuid() + " not found.");
    }

    public Resume get(String uuid) {
        Resume resumeToSearch = new Resume(uuid);
        if (checkForResumePresence(resumeToSearch.getUuid()) >= 0) {
            return resumeToSearch;
        }
        return null;
    }

    public void delete(String uuid) {
        Resume resumeToDelete = new Resume(uuid);
        if (checkForResumePresence(resumeToDelete.getUuid()) >= 0) {
            int index = checkForResumePresence(resumeToDelete.getUuid());
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
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

    private int checkForResumePresence(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkForResumeDupe(String uuid) {
        if (checkForResumePresence(uuid) >= 0) {
            System.out.println("----------------------------\n" +
                    "Resume with uuid " + uuid + " is already in the storage! Enter another command");
            return true;
        }
        return false;
    }
}
