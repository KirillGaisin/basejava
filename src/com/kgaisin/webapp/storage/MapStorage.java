package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeNotFoundException;
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
        if(storage.containsKey(uuid)) {
            return uuid;
        }
        return "Not found";
    }

    @Override
    protected boolean checkId(String uuid) {
        String id = (String)checkForResumePresence(uuid);
        return id.compareTo(uuid) == 0;
    }
}
