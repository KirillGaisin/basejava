package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.delete()) {
                    throw new StorageException("File not deleted", file.getName());
                }
            }
        }
    }

    @Override
    public int size() {
        if (directory.listFiles() == null) {
            return 0;
        } else {
            return directory.listFiles().length;
        }
    }

    @Override
    protected File checkForResumePresence(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void updateResume(Resume r, File file) {
        if(directory.getName().equals(r.getUuid())) {
            directory = file;
        }
    }

    @Override
    protected boolean checkId(File file) {
        return file.exists();
    }

    @Override
    protected void addResume(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void removeResume(File file) {
        if (!file.delete()) {
            throw new StorageException("File not deleted", file.getName());
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        File[] files = directory.listFiles();
        List<Resume> resumes = new ArrayList<>();
        for (File file : files) {
            resumes.add(getResume(file));
        }
        return resumes;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}
