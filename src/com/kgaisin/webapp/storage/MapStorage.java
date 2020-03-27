package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new LinkedHashMap<>();

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
    protected void addResume(Resume resume, Object id) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(Object id) {
        storage.remove(id.toString());
    }

    @Override
    protected void updateResume(Resume resume, Object id) {
        storage.replace(id.toString(), resume);
    }

    @Override
    protected Resume getResume(Object id) {
        return storage.get(id.toString());
    }

    @Override
    protected Object checkForResumePresence(String uuid) {
        Resume resume = new Resume(uuid);
        if(storage.containsValue(resume)) {
            return uuid;
        }
        return -1;
    }
}
