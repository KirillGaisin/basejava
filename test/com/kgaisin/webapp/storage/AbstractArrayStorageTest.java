package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeInStorageException;
import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String TEST_UUID = "test_uuid";
    private static final Resume TEST_RESUME = new Resume(TEST_UUID);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() throws Exception {
        storage.save(TEST_RESUME);
        assertEquals(4, storage.size());
        assertEquals(TEST_RESUME, storage.get(TEST_UUID));
    }

    @Test(expected = ResumeInStorageException.class)
    public void saveExisting() {
        storage.save(RESUME_1);
    }

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

    @Test(expected = ResumeNotFoundException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        assertNotEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void deleteNonexistent() {
        storage.delete("nothing");
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void updateNonexistent() {
        storage.update(new Resume("nothing"));
    }

    @Test
    public void get() {
        Resume newResume = storage.get(UUID_1);
        assertEquals(RESUME_1, newResume);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] testResumes = new Resume[] {RESUME_1, RESUME_2, RESUME_3};
        Resume[] actualResumes = storage.getAll();
        assertArrayEquals(testResumes, actualResumes);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}