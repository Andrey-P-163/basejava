package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, count, searchKey);
    }

    @Override
    protected boolean checkSearchKey(Object searchKey, String uuid) {
        return (int) searchKey >= 0;
    }

    @Override
    protected void saveElement(Resume resume, int index) {
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, count - index);
        storage[index] = resume;
    }


}
