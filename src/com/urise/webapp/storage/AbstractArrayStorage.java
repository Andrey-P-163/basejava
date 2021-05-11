package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected int count = 0;
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract void saveElement(Resume resume, int index);

    @Override
    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (count >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }

        if (index > 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveElement(resume, index);
        count++;
    }

    @Override
    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        System.arraycopy(storage, (index + 1), storage, index, (count - index - 1));
        count--;
    }

    @Override
    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        storage[index] = resume;
    }

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    @Override
    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        return storage[index];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }
}