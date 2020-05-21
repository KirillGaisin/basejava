package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.Resume;
import com.kgaisin.webapp.util.serializer.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileStorage extends AbstractStorage<File> {
    private File directory;

    private StreamSerializer streamSerializer;

    protected FileStorage(File directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.streamSerializer = streamSerializer;
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
        getFiles().forEach(this::removeResume);
    }

    @Override
    public int size() {
        return getFiles().size();
    }

    @Override
    protected File checkForResumePresence(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void updateResume(Resume r, File file) {
        try {
            streamSerializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error while writing into file", file.getName(), e);
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
        } catch (IOException e) {
            throw new StorageException("Error while creating file", file.getName(), e);
        }
        updateResume(r, file);
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(new FileInputStream(file)));
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
        return getFiles().stream().map(this::getResume).collect(Collectors.toList());
    }

    private List<File> getFiles() {
        if(directory.listFiles() == null) {
            throw new StorageException("Directory is null");
        }
        return Arrays.asList(Objects.requireNonNull(directory.listFiles()));
    }
}
