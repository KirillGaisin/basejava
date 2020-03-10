package com.kgaisin.webapp.exception;

public class ResumeInStorageException extends StorageException {
    public ResumeInStorageException(String uuid) {
        super("Resume " + uuid + " already exists", uuid);
    }
}
