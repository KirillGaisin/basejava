package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeInStorageException;
import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        int index = checkForResumePresence(resume.getUuid());
        if (index >= 0) {
            throw new ResumeInStorageException(resume.getUuid());
        }
        addResume(resume, index);
    }

    public void delete(String uuid) {
        int index = checkIfResumeInStorage(uuid);
        removeResume(index);
    }

    public void update(Resume resume) {
        int index = checkIfResumeInStorage(resume.getUuid());
        updateResume(resume, index);
    }

    public Resume get(String uuid) {
        int index = checkIfResumeInStorage(uuid);
        return getResume(index);
    }

    private int checkIfResumeInStorage(String uuid) {
        int index = checkForResumePresence(uuid);
        if(index < 0) {
            throw new ResumeNotFoundException(uuid);
        }
        return index;
    }

    public abstract void clear();

    public abstract Resume[] getAll();

    protected abstract void addResume(Resume resume, int index);

    protected abstract void removeResume(int index);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract Resume getResume(int index);

    protected abstract int checkForResumePresence(String uuid);
}
