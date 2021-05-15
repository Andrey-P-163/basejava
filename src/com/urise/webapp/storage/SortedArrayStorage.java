package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveElement(Resume resume) {
        Resume searchKey = new Resume(resume.getUuid());
        int index = Arrays.binarySearch(storage, 0, count, searchKey);
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, count - index);
        storage[index] = resume;
    }
}
