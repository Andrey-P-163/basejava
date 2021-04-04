package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (count < STORAGE_LIMIT) {
            if (index >= 0) {
                System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " уже внесено в базу.");
            } else {
                index = -index - 1;
                System.arraycopy(storage, index, storage, index + 1, count + index);
                storage[index] = resume;
                count++;
            }
        } else {
            System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " не сохранено. В базе нет места для сохранения нового резюме.");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, count, searchKey);
    }
}
