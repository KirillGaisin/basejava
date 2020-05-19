package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.StorageException;
import com.kgaisin.webapp.model.Resume;
import com.kgaisin.webapp.util.Serializer.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;

    private StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        this.streamSerializer = streamSerializer;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::removeResume);
        } catch (IOException e) {
            throw new StorageException("Error while deleting dir");
        }
    }

    @Override
    public int size() {
        try {
            return (int)Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Error while getting size of dir");
        }
    }

    @Override
    protected Path checkForResumePresence(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void updateResume(Resume r, Path path) {
        try {
            streamSerializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean checkId(Path path) {
        return path.toFile().exists();
    }

    @Override
    protected void addResume(Resume r, Path path) {
        try {
            Files.createFile(path);
            streamSerializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " + path, path.toString(), e);
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void removeResume(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageException("Error while deleting path", path.toString());
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        try {
            List<Path> paths = Files.list(directory).collect(Collectors.toList());
            List<Resume> resumes = new ArrayList<>();
            paths.forEach(path -> resumes.add(getResume(path)));
            return resumes;
        } catch (IOException e) {
            throw new StorageException("Error while getting sorted resumes", e);
        }
    }
}
