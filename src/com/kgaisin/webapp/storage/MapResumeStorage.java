package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected void addResume(Resume resume, Object id) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(Object id) {
        storage.entrySet().removeIf(entry -> entry.getValue().equals(id));
    }

    @Override
    protected void updateResume(Resume resume, Object id) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object id) {
        return (Resume)id;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Resume checkForResumePresence(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean checkId(Object id) {
        return storage.containsValue((Resume)id);
    }

}
