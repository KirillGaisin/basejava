package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected void addResume(Resume resume, Object id) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(Object id) {
        storage.entrySet().removeIf(entry -> entry.getValue().getFullName().equals(id));
    }

    @Override
    protected void updateResume(Resume resume, Object id) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object id) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().getFullName().equals(id)) {
                return entry.getValue();
            }
        }
        return null;
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
    protected String checkForResumePresence(String uuid) {
        if (Objects.isNull(storage.get(uuid))) {
            return "Not found";
        }
        Resume searchKey = storage.get(uuid);
        return searchKey.getFullName();
    }

    @Override
    protected boolean checkId(Object id) {
        return !id.equals("Not found");
    }

}
