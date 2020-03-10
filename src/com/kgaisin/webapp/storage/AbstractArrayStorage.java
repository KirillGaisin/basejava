package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeInStorageException;
import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 4;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume resume) {
        // проверка на заполненность storage
        if (size >= storage.length) {
            throw new StorageException("Resume storage is full", resume.getUuid());
        }

        // проверка на присутствие в storage добавляемого резюме
        int index = checkForResumePresence(resume.getUuid());
        if (index >= 0) {
            throw new ResumeInStorageException(resume.getUuid());
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
        throw new ResumeNotFoundException(uuid);
    }

    public void update(Resume resume) {
        int index = checkForResumePresence(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            System.out.println("----------------------------\n" +
                    "Resume with uuid " + resume.getUuid() + " updated.");
            return;
        }
        throw new ResumeNotFoundException(resume.getUuid());
    }

    public Resume get(String uuid) {
        int index = checkForResumePresence(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new ResumeNotFoundException(uuid);
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
