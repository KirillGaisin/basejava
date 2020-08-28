package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.util.SqlConfig;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(SqlConfig.getInstance().getStorage());
    }
}
