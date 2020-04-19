package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeInStorageException;
import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.model.Resume;

import java.util.logging.Logger;

public abstract class AbstractStorage<ID> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void save(Resume resume) {
        LOG.info("Saving " + resume);
        ID id = getNonExistentId(resume.getUuid());
        addResume(resume, id);
    }

    public void delete(String uuid) {
        ID id = getExistingId(uuid);
        removeResume(id);
    }

    public void update(Resume resume) {
        LOG.info("Updating " + resume);
        ID id = getExistingId(resume.getUuid());
        updateResume(resume, id);
    }

    public Resume get(String uuid) {
        LOG.info("Getting " + uuid);
        ID id = getExistingId(uuid);
        return getResume(id);
    }

    private ID getExistingId(String uuid) {
        ID id = checkForResumePresence(uuid);
        if (checkId(id)) {
            return id;
        }
        LOG.warning("Resume " + uuid + " does not exist");
        throw new ResumeNotFoundException(uuid);
    }

    private ID getNonExistentId(String uuid) {
        ID id = checkForResumePresence(uuid);
        if (!checkId(id)) {
            return id;
        }
        LOG.warning("Resume " + uuid + " already exists");
        throw new ResumeInStorageException(uuid);
    }

    protected abstract void addResume(Resume resume, ID id);

    protected abstract void removeResume(ID id);

    protected abstract void updateResume(Resume resume, ID id);

    protected abstract Resume getResume(ID id);

    protected abstract ID checkForResumePresence(String uuid);

    protected abstract boolean checkId(ID id);
}
