package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeInStorageException;
import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        Object id = checkForResumePresence(resume.getUuid());
        if ((id instanceof Integer && (Integer) id >= 0) || id.equals(resume.getUuid())) {
            throw new ResumeInStorageException(resume.getUuid());
        }
        addResume(resume, id);
    }

    public void delete(String uuid) {
        Object id = checkIfResumeInStorage(uuid);
        removeResume(id);
    }

    public void update(Resume resume) {
        Object id = checkIfResumeInStorage(resume.getUuid());
        updateResume(resume, id);
    }

    public Resume get(String uuid) {
        Object id = checkIfResumeInStorage(uuid);
        return getResume(id);
    }

    private Object checkIfResumeInStorage(String uuid) {
        Object id = checkForResumePresence(uuid);
        if (id instanceof Integer && (Integer) id < 0) {
            throw new ResumeNotFoundException(uuid);
        }
        return id;
    }

    protected abstract void addResume(Resume resume, Object id);

    protected abstract void removeResume(Object id);

    protected abstract void updateResume(Resume resume, Object id);

    protected abstract Resume getResume(Object id);

    protected abstract Object checkForResumePresence(String uuid);
}
