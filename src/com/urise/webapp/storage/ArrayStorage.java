package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int count;
    private int index = -1;
    private Resume[] storage = new Resume[10_000];

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume resume) {
        byte forCheck = 0;
        getIndex(resume.getUuid());
        if (count < storage.length) {
            if (index >= 0) {
                System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " уже внесено в базу.");
                forCheck = 1;
            }
            if (forCheck == 0) {
                storage[count] = resume;
                count++;
            }
        } else {
            System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " не сохранено. В базе нет места для сохранения нового резюме.");
        }
    }

    public Resume get(String uuid) {
        getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("ERROR: Резюме с uuid = " + uuid + " нет в базе.");
        return null;
    }

    public void delete(String uuid) {
        byte forCheck = 0;
        getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, count - index - 1);
            count--;
            forCheck = 1;
        }
        if (forCheck == 0) {
            System.out.println("ERROR: Резюме с uuid = " + uuid + " нет базе. Удаление невозможно.");
        }
    }

    public void update(Resume resume) {
        if (resume != null) {
            if (getIndex(resume.getUuid()) >= 0) {
                // System.out.println(resume.update());
                storage[index] = resume;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    public int size() {
        return count;
    }

    private int getIndex(String uuid) {
        index = -1;
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                index = i;
                return index;
            }
        }
        return index;
    }
}