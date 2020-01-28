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
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        //not null-safe
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("----------------------------\n" +
                        "Resume with this uuid is already in the storage! Enter another command");
                return;
            }
        }
        if (storage[storage.length - 1] != null) {
            System.out.println("----------------------------\n" +
                    "Resume storage is full. Delete entries or clear the storage to add a new one");
            return;
        }
        storage[size] = r;
        size++;
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("----------------------------\n" +
                        "Resume with uuid " + r.getUuid() + " updated.");
                return;
            }
        }
        System.out.println("----------------------------\n" +
                "Resume with uuid " + r.getUuid() + " not found.");
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
