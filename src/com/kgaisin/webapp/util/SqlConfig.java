package com.kgaisin.webapp.util;

import com.kgaisin.webapp.storage.SqlStorage;
import com.kgaisin.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlConfig {
    private static final File PROP_DIR = new File("config/resumes.properties");
    private static final SqlConfig INSTANCE = new SqlConfig();
    private final Storage storage;
    private final File dir;

    public SqlConfig() {
        try(InputStream is = new FileInputStream(PROP_DIR)) {
            Properties props = new Properties();
            props.load(is);
            dir = new File(props.getProperty("storage.dir"));
            storage = new SqlStorage
                    (props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public File getDir() {
        return dir;
    }

    public static SqlConfig getInstance() {
        return INSTANCE;
    }
}
