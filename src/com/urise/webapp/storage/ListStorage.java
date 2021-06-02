package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storageList = new ArrayList<>();

    @Override
    protected Object getPosition(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (uuid.equals(storageList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void setResume(Resume resume, Object positionInBase) {
        storageList.add(resume);
    }

    @Override
    protected void removeResume(Object positionInBase) {
        storageList.remove((int) positionInBase);
    }

    @Override
    protected void updateResume(Resume resume, Object positionInBase) {
        storageList.set((int) positionInBase, resume);
    }

    @Override
    protected Resume getResume(Object positionInBase) {
        return storageList.get((int) positionInBase);
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