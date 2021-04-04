package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, count, searchKey);
    }

    @Override
    protected void saveElement(Resume resume, int index, int count) {
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, count + index);
        storage[index] = resume;
    }
}
