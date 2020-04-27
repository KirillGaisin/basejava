package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeInStorageException;
import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static com.kgaisin.webapp.ResumeTestData.*;

public abstract class AbstractStorageTest {
    Storage storage;

    private static final String UUID_1 = "uuid1";
    //private static final Resume RESUME_1 = new Resume(UUID_1, "Name1");
    private static final Resume RESUME_1 = addData(UUID_1, "Name1");
    private static final String UUID_2 = "uuid2";
    //private static final Resume RESUME_2 = new Resume(UUID_2, "Name2");
    private static final Resume RESUME_2 = addData(UUID_2, "Name2");
    private static final String UUID_3 = "uuid3";
    //private static final Resume RESUME_3 = new Resume(UUID_3, "Name3");
    private static final Resume RESUME_3 = addData(UUID_3, "Name3");
    private static final String TEST_UUID = "test_uuid";
    //private static final Resume TEST_RESUME = new Resume(TEST_UUID, "Test_name");
    private static final Resume TEST_RESUME = addData(TEST_UUID, "Test_name");

    AbstractStorageTest(Storage storage) {
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

    @Test(expected = ResumeNotFoundException.class)
    public void delete() {
        try {
            storage.delete(UUID_1);
            assertEquals(2, storage.size());
        } catch (StorageException ex) {
            fail("Unexpected exception has occured while deleting resume!");
        }
        assertNotEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void deleteNonExistent() {
        storage.delete(TEST_UUID);
    }

    @Test
    public void update() {
        storage.update(addData(UUID_1, "Name1"));
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = ResumeNotFoundException.class)
    public void updateNonExistent() {
        storage.update(addData(TEST_UUID, "Test_Name"));
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedResumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertEquals(expectedResumes.size(), storage.size());
        assertEquals(expectedResumes, storage.getAllSorted());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}
