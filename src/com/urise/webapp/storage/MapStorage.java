package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> storageMap = new TreeMap<>();

    @Override
    protected int getIndex(String uuid) {
        int index = 0;
        for (Map.Entry<String, Resume> map : storageMap.entrySet()) {
            if (uuid.equals(map.getKey())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    protected void setResume(Resume resume, int index) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeResume(int index) {
        String key = (String) storageMap.keySet().toArray()[index];
        storageMap.remove(key);
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        for (Map.Entry<String, Resume> map : storageMap.entrySet()) {
            if (map.getValue().equals(resume)) {
                storageMap.put(resume.getUuid(), resume);
            }
        }
    }

    @Override
    protected Resume getResume(int index) {
        String key = (String) storageMap.keySet().toArray()[index];
        return storageMap.get(key);
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
        ArrayList<Resume> values = new ArrayList<>(storageMap.values());
        return values.toArray(new Resume[storageMap.size()]);
    }
}
