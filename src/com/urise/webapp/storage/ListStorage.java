package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storageList = new ArrayList<>();

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (uuid.equals(storageList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void setResume(Resume resume, Object index) {
        storageList.add(resume);
    }

    @Override
    protected void removeResume(Object index) {
        storageList.remove((int) index);
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        storageList.set((int) index, resume);
    }

    @Override
    protected Resume getResume(Object index) {
        return storageList.get((int) index);
    }

    @Override
    protected boolean checkSearchKey(Object searchKey, String uuid) {
        return (int) searchKey >= 0;
    }

    @Override
    public void clear() {
        storageList.clear();
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