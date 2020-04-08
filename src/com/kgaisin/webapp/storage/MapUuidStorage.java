package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(storage.values());
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
    protected String checkForResumePresence(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkId(Object id) {
        return storage.containsKey((String) id);
    }

}
