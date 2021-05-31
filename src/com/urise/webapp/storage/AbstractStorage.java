package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void setResume(Resume resume, int index);

    protected abstract void removeResume(int index, String uuid);

    protected abstract void updateResume(int index, Resume resume);

    protected abstract Resume getResume(int index, String uuid);

    public void save(Resume resume) {
        int index = getIndexIfResumeNotExist(resume.getUuid());
        setResume(resume, index);
    }

    public void delete(String uuid) {
        int index = checkUuidNotExist(uuid);
        removeResume(index, uuid);
    }

    public void update(Resume resume) {
        int index = checkUuidNotExist(resume.getUuid());
        updateResume(index, resume);
    }

    public Resume get(String uuid) {
        int index = checkUuidNotExist(uuid);
        return getResume(index, uuid);
    }

    private int getIndexIfResumeNotExist(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    private int checkUuidNotExist(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}


