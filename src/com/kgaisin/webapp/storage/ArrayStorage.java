package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    /*
    HW2
    - Сделать проверки в update/get/delete - резюме есть в storage? DONE(?)
    - В save - резюме нет в storage?
    - В save проверку на переполнение DONE
    - Избавиться от дублирования кода
    - Методы из Arrays в clear и getAll DONE
     */

    private Resume[] storage = new Resume[10];

    private int size = 0;

    public void clear() {
        if (size == 0)
            return;
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume resume) {
        //not null-safe
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                System.out.println("----------------------------\n" +
                        "Resume with uuid " + resume.getUuid() + " is already in the storage! Enter another command");
                return;
            }
        }

        //проверка на заполненность storage
        if (storage[storage.length - 1] != null) {
            System.out.println("----------------------------\n" +
                    "Resume storage is full. Delete entries or clear the storage to add a new one");
            return;
        }
        storage[size] = resume;
        size++;

        //проверка на наличие сохраненного резюме в storage
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid()))
                return;
        }
        System.out.println("Resume with uuid " + resume.getUuid() + " was not added to the storage! Enter another command");
    }

    public void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                System.out.println("----------------------------\n" +
                        "Resume with uuid " + resume.getUuid() + " updated.");
                return;
            }
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + resume.getUuid() + " not found.");
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return storage[i];
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                size--;
                return;
            }
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + uuid + " not found.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes = Arrays.copyOf(storage, size);
        }
        return resumes;
    }

    public int size() {
        return size;
    }
}
