package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected final ArrayList<Resume> storageList = new ArrayList<>();

    @Override
    public void save(Resume resume) {
        int index = checkBeforeSaving(getAll(), resume.getUuid(), storageList.size());
        this.storageList.add(resume);
    }

    @Override
    public void delete(String uuid) {
        int index = checkIndex(getAll(), uuid, storageList.size());
        storageList.remove(index);
    }

    @Override
    public void update(Resume resume) {
        int index = checkIndex(getAll(), resume.getUuid(), storageList.size());
        storageList.set(index, resume);
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public Resume get(String uuid) {
        int index = checkIndex(getAll(), uuid, storageList.size());
        return storageList.get(index);
    }

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[storageList.size()]);
    }
}
