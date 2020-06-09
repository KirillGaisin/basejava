package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.util.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
