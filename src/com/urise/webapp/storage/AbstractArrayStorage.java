package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected int count;
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract void saveElement(Resume resume, int index);

    @Override
    protected void setResume(Resume resume, Object index) {
        if (count >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        saveElement(resume, (int) index);
        count++;
    }

    @Override
    protected void removeResume(Object index) {
        System.arraycopy(storage, ((int) index + 1), storage, (int) index, (count - (int) index - 1));
        count--;
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(int) index];
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