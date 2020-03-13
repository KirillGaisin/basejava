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
    public Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    protected void addResume(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected void removeResume(int index) {
        storage.remove(index);
    }

    @Override
    protected int checkForResumePresence(String uuid) {
        Resume toSearch = new Resume(uuid);
        if(storage.contains(toSearch)) {
            return 1;
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        storage.set(index, resume);
        System.out.println("----------------------------\n" +
                "Resume with uuid " + resume.getUuid() + " updated.");
    }
}
