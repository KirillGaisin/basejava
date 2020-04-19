package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
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
    protected void addResume(Resume resume, String id) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(String id) {
        storage.remove(id);
    }

    @Override
    protected void updateResume(Resume resume, String id) {
        storage.replace(id, resume);
    }

    @Override
    protected Resume getResume(String id) {
        return storage.get(id);
    }

    @Override
    protected String checkForResumePresence(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkId(String id) {
        return storage.containsKey(id);
    }

}
