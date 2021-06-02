package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storageMap = new TreeMap<>();

    @Override
    protected Object getPosition(String uuid) {
        if (storageMap.containsKey(uuid)) {
            return uuid;
        }
        return "";
    }

    @Override
    protected void setResume(Resume resume, Object positionInBase) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(Object positionInBase) {
        storageMap.remove((String) positionInBase);
    }

    @Override
    protected void updateResume(Resume resume, Object positionInBase) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object positionInBase) {
        return storageMap.get((String) positionInBase);
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
