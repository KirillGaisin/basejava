package com.kgaisin.webapp.exception;

public class ResumeNotFoundException extends StorageException {
    public ResumeNotFoundException(String uuid) {
        super("Resume " + uuid + " not found", uuid);
    }
}
