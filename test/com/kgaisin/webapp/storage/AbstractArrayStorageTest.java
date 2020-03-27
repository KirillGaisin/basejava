package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {


    //todo define constructor, work on test hierarchy
    private Storage storage;

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("should_not_add"));
            }

        } catch (StorageException ex) {
            fail();
        }
        //добавление резюме сверх лимита
        storage.save(new Resume("should_not_add"));
    }

}