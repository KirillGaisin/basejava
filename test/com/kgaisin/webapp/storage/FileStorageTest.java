package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.util.Serializer.ObjectStreamSerializer;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(DIR, new ObjectStreamSerializer()));
    }
}
