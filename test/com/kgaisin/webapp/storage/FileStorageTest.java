package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.util.serializer.ObjectStreamSerializer;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(DIR, new ObjectStreamSerializer()));
    }
}
