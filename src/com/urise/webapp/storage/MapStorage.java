package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storageMap = new TreeMap<>();

    @Override
    protected int getIndex(String uuid) {
        if (storageMap.containsKey(uuid)) {
            return 0;
        }
        return -1;
    }

    @Override
    protected void setResume(Resume resume, int index) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(int index, String uuid) {
        storageMap.remove(uuid);
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        if (storageMap.containsValue(resume)) {
            storageMap.put(resume.getUuid(), resume);
        }
    }

    @Override
    protected Resume getResume(int index, String uuid) {
        return storageMap.get(uuid);
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public int size() {
        return storageMap.size();
    }

    @Override
    public Resume[] getAll() {
        return storageMap.values().toArray(new Resume[storageMap.size()]);
    }
}
