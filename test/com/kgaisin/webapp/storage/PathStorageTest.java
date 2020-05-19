package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.util.Serializer.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}
