package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume getResume(Object id) {
        return storage.get((int) id);
    }

    @Override
    protected void addResume(Resume resume, Object id) {
        storage.add(resume);
    }

    @Override
    protected void removeResume(Object id) {
        storage.remove((int) id);
    }

    @Override
    protected Object checkForResumePresence(String uuid) {
        Resume resume = new Resume(uuid);
        //падает на i <= storage.size() из-за indexOutOfBounds
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).compareTo(resume) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, Object id) {
        storage.set((int) id, resume);
    }
}
