package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    public abstract void save(Resume resume);

    public abstract void delete(String uuid);

    public abstract void update(Resume resume);

    public abstract void clear();

    public abstract Resume get(String uuid);

    public abstract int size();

    public abstract Resume[] getAll();
}
