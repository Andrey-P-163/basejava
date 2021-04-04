package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {
    void save(Resume resume);

    void delete(String uuid);

    void update(Resume resume);

    void clear();

    Resume get(String uuid);

    int size();

    Resume[] getAll();
}