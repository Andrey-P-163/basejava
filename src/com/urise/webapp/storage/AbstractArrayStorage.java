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

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(Resume resume, int index);

    @Override
    protected void setResume(Resume resume) {
        if (count >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        saveElement(resume, getIndex(resume.getUuid()));
        count++;
    }

    @Override
    protected void removeResume(int index) {
        System.arraycopy(storage, (index + 1), storage, index, (count - index - 1));
        count--;
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    @Override
    protected Resume getResume(int index) {
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