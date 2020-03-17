package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new LinkedHashMap<>();
    private List<String> keys = new LinkedList<>();
    private String uuid;

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
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
    protected void addResume(Resume resume, int index) {
        uuid = resume.getUuid();
        storage.put(uuid, resume);
    }

    @Override
    protected void removeResume(int index) {
        uuid = keys.get(index);
        storage.remove(uuid);
        keys.remove(uuid);
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        uuid = keys.get(index);
        storage.replace(uuid, resume);
    }

    @Override
    protected Resume getResume(int index) {
        uuid = keys.get(index);
        return storage.get(uuid);
    }

    @Override
    protected int checkForResumePresence(String uuid) {
        Resume resume = new Resume(uuid);
        if(storage.containsValue(resume)) {
            return keys.indexOf(uuid);
        }
        keys.add(uuid);
        return -1;
    }
}
