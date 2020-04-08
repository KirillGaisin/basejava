package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 4;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    public void addResume(Resume resume, Object id) {
        if (size >= storage.length) {
            throw new StorageException("Resume storage is full", resume.getUuid());
        }
        addElement(resume, (Integer) id);
        size++;
    }

    public void removeResume(Object id) {
        removeElement((Integer) id);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean checkId(Object id) {
        return (int) id >= 0;
    }

    @Override
    public void updateResume(Resume resume, Object id) {
        storage[(Integer) id] = resume;
    }

    @Override
    protected Resume getResume(Object id) {
        return storage[(Integer) id];
    }

    public abstract void addElement(Resume resume, int id);

    public abstract void removeElement(int id);

    protected abstract Object checkForResumePresence(String uuid);
}
