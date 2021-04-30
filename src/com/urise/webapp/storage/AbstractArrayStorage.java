package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected int count = 0;
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(Resume resume, int index);

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (count < STORAGE_LIMIT) {
            if (index >= 0) {
                throw new ExistStorageException(resume.getUuid());
            } else {
                saveElement(resume, index);
                count++;
            }
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, (index + 1), storage, index, (count - index - 1));
            count--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public int size() {
        return count;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }
}