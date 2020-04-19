package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public List<Resume> getAllSorted() {
        return storage;
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
    public Resume getResume(Integer id) {
        return storage.get(id);
    }

    @Override
    protected void addResume(Resume resume, Integer id) {
        storage.add(resume);
    }

    @Override
    protected void removeResume(Integer id) {
        storage.remove(id.intValue());
    }

    @Override
    protected void updateResume(Resume resume, Integer id) {
        storage.set(id, resume);
    }

    @Override
    protected Integer checkForResumePresence(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkId(Integer id) {
        return  id >= 0;
    }
}
