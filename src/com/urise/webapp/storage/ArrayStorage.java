package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (count < STORAGE_LIMIT) {
            if (index >= 0) {
                System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " уже внесено в базу.");
            } else {
                storage[count] = resume;
                count++;
            }
        } else {
            System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " не сохранено. В базе нет места для сохранения нового резюме.");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
