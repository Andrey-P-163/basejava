package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected int getIndex(Resume[] storage, String uuid, int size) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

//    protected abstract int getIndex(String uuid);

    protected int checkBeforeSaving(Resume[] storage, String uuid, int size) {
        int index = getIndex(storage, uuid, size);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected int checkIndex(Resume[] storage, String uuid, int size) {
        int index = getIndex(storage, uuid, size);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}


