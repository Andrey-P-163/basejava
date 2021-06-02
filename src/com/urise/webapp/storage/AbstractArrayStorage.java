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

    protected abstract Object getPosition(String uuid);

    protected abstract void saveElement(Resume resume, int index);

    @Override
    protected void setResume(Resume resume, Object positionInBase) {
        if (count >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        saveElement(resume, (int) positionInBase);
        count++;
    }

    @Override
    protected void removeResume(Object positionInBase) {
        System.arraycopy(storage, ((int) positionInBase + 1), storage, (int) positionInBase, (count - (int) positionInBase - 1));
        count--;
    }

    @Override
    protected void updateResume(Resume resume, Object positionInBase) {
        storage[(int) positionInBase] = resume;
    }

    @Override
    public final void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    @Override
    protected Resume getResume(Object positionInBase) {
        return storage[(int) positionInBase];
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