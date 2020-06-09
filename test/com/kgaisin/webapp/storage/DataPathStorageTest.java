package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.util.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}
