package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.List;

public interface Storage {
    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    //Resume[] getAll();
    List<Resume> getAllSorted();

    int size();
}
