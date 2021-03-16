package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int count;
    private final Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume r) {
        if (count < storage.length) {
            for (Resume resume : getAll()) {
                if (r.getUuid().equals(resume.getUuid())) {
                    System.out.println("ERROR: Резюме с таким uuid уже внесено в базу.");
                    break;
                } else {
                    storage[count] = r;
                    count++;
                    break;
                }
            }
            if (count == 0) {
                storage[count] = r;
                count++;
            }
        } else {
            System.out.println("Не хватает места для ввода нового резюме.");
        }
    }

    public Resume get(String uuid) {
        for (Resume resume : getAll()) {
            if (uuid.equals(resume.getUuid())) {
                return resume;
            }
        }
        System.out.println("ERROR: Данного резюме нет в базе");
        return null;
    }

    public void delete(String uuid) {
        byte forCheck = 0;
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                System.arraycopy(storage, i + 1, storage, i, count - i - 1);
                count--;
                forCheck = 1;
                break;
            }
        }
        if (forCheck == 0) {
            System.out.println("ERROR: Попытка удалить не существующее резюме.");
        }
    }

    public void update(Resume r) {
        if (r != null) {
            for (Resume resume : getAll()) {
                if (r.equals(resume)) {
                    System.out.println("Update" + " " + r);
                }
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
}