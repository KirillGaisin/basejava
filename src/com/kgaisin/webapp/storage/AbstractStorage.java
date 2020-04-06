package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeInStorageException;
import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object id = checkForResumePresence(uuid);
        try {
            checkId(uuid);
        } catch (ResumeNotFoundException notFound) {
            addResume(resume, id);
            return;
        }
        throw new ResumeInStorageException(uuid);
    }

    public void delete(String uuid) {
        Object id = checkForResumePresence(uuid);
        removeResume(id);
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object id = checkForResumePresence(uuid);
        updateResume(resume, id);
    }

    public Resume get(String uuid) {
        Object id = checkForResumePresence(uuid);
        return getResume(id);
    }

    protected abstract void addResume(Resume resume, Object id);

    protected abstract void removeResume(Object id);

    protected abstract void updateResume(Resume resume, Object id);

    protected abstract Resume getResume(Object id);

    protected abstract Object checkForResumePresence(String uuid);

    protected abstract void checkId(String uuid);
}
