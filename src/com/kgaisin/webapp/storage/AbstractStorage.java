package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeInStorageException;
import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object id = checkForResumePresence(uuid);
        if (checkId(uuid)) {
            throw new ResumeInStorageException(uuid);
        }

        addResume(resume, id);
    }

    public void delete(String uuid) {
        if(!checkId(uuid)) {
            throw new ResumeNotFoundException(uuid);
        }
        Object id = checkForResumePresence(uuid);
        removeResume(id);
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        if(!checkId(uuid)) {
            throw new ResumeNotFoundException(uuid);
        }
        Object id = checkForResumePresence(uuid);
        updateResume(resume, id);
    }

    public Resume get(String uuid) {
        if(!checkId(uuid)) {
            throw new ResumeNotFoundException(uuid);
        }
        Object id = checkForResumePresence(uuid);
        return getResume(id);
    }

    protected boolean checkId(String uuid) {
        int id = (int)checkForResumePresence(uuid);
        return id >= 0;
    }

    protected abstract void addResume(Resume resume, Object id);

    protected abstract void removeResume(Object id);

    protected abstract void updateResume(Resume resume, Object id);

    protected abstract Resume getResume(Object id);

    protected abstract Object checkForResumePresence(String uuid);
}
