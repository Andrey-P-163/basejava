package com.urise.webapp.storage;

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

    protected abstract void saveElement(Resume resume);

    @Override
    public final void save(Resume resume) {
        if (count >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }

        int index = checkBeforeSaving(storage, resume.getUuid(), count);
        saveElement(resume);
        count++;
    }

    @Override
    public final void delete(String uuid) {
        int index = checkIndex(storage, uuid, count);
        System.arraycopy(storage, (index + 1), storage, index, (count - index - 1));
        count--;
    }

    @Override
    public final void update(Resume resume) {
        int index = checkIndex(storage, resume.getUuid(), count);
        storage[index] = resume;
    }

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    @Override
    public final Resume get(String uuid) {
        int index = checkIndex(storage, uuid, count);
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