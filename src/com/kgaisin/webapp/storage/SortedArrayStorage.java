package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (checkForResumePresence(resume.getUuid()) < 0) {
            int index = -(checkForResumePresence(resume.getUuid()) + 1);
            if (storage[index] != null) {
                System.arraycopy(storage, index, storage, index + 1, size - index + 1);
            }
            storage[index] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        if (checkForResumePresence(uuid) >= 0) {
            int index = checkForResumePresence(uuid);
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            size--;
            return;
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + uuid + " not found.");
    }

    @Override
    protected int checkForResumePresence(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
