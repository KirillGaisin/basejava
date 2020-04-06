package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeInStorageException;
import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object id = getNonExistentId(uuid);
        addResume(resume, id);
    }

    public void delete(String uuid) {
        Object id = getExistingId(uuid);
        removeResume(id);
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object id = getExistingId(uuid);
        updateResume(resume, id);
    }

    public Resume get(String uuid) {
        Object id = getExistingId(uuid);
        return getResume(id);
    }

    private Object getExistingId(String uuid) {
        Object id = checkForResumePresence(uuid);
        if (checkId(uuid)) {
            return id;
        }
        throw new ResumeNotFoundException(uuid);
    }

    private Object getNonExistentId(String uuid) {
        Object id = checkForResumePresence(uuid);
        if (!checkId(uuid)) {
            return id;
        }
        throw new ResumeInStorageException(uuid);
    }

    protected abstract void addResume(Resume resume, Object id);

    protected abstract void removeResume(Object id);

    protected abstract void updateResume(Resume resume, Object id);

    protected abstract Resume getResume(Object id);

    protected abstract Object checkForResumePresence(String uuid);

    protected abstract boolean checkId(String uuid);
}
