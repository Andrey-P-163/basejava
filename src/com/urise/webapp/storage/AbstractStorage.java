package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void setResume(Resume resume);

    protected abstract void removeResume(int index);

    protected abstract void updateResume(int index, Resume resume);

    protected abstract Resume getResume(int index);

    public void save(Resume resume) {
        checkUuidExist(resume.getUuid());
        setResume(resume);
    }

    public void delete(String uuid) {
        int index = checkUuidNotExist(uuid);
        removeResume(index);
    }

    public void update(Resume resume) {
        int index = checkUuidNotExist(resume.getUuid());
        updateResume(index, resume);
    }

    public Resume get(String uuid) {
        int index = checkUuidNotExist(uuid);
        return getResume(index);
    }

    private void checkUuidExist(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            throw new ExistStorageException(uuid);
        }
    }

    private int checkUuidNotExist(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}


