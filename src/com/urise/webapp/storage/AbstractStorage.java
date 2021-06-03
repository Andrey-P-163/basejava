package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void setResume(Resume resume, Object searchKey);

    protected abstract void removeResume(Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract boolean checkSearchKey(Object searchKey, String uuid);

    public void save(Resume resume) {
        Object searchKey = getSearchKeyIfNotExist(resume.getUuid());
        setResume(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        removeResume(searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = getSearchKeyIfExist(resume.getUuid());
        updateResume(resume, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        return getResume(searchKey);
    }

    private Object getSearchKeyIfNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (checkSearchKey(searchKey, uuid)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyIfExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!checkSearchKey(searchKey, uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}