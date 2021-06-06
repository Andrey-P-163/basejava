package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storageMap = new TreeMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return (storageMap.containsKey(uuid) ? uuid : null);
    }

    @Override
    protected void setResume(Resume resume, Object uuid) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(Object uuid) {
        storageMap.remove((String) uuid);
    }

    @Override
    protected void updateResume(Resume resume, Object uuid) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object uuid) {
        return storageMap.get((String) uuid);
    }

    @Override
    protected boolean checkSearchKey(Object searchKey, String uuid) {
        return searchKey != null;
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
