package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.util.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
