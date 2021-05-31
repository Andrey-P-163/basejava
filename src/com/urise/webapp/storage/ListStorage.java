package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storageList = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (uuid.equals(storageList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void setResume(Resume resume, int index) {
        storageList.add(resume);
    }

    @Override
    protected void removeResume(int index, String uuid) {
        storageList.remove(index);
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        storageList.set(index, resume);
    }

    @Override
    protected Resume getResume(int index, String uuid) {
        return storageList.get(index);
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