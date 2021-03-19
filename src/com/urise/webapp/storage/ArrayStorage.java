package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int count;
    private final Resume[] storage = new Resume[10_000];

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume resume) {
        byte forCheck = 0;
        if (count < storage.length) {
            for (Resume r : getAll()) {
                if (resume.getUuid().equals(r.getUuid())) {
                    System.out.println("ERROR: Резюме с таким uuid уже внесено в базу.");
                    forCheck = 1;
                    break;
                }
            }
            if (forCheck == 0) {
                storage[count] = resume;
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

    public void update(Resume resume) {
        if (resume != null) {
            for (Resume r : getAll()) {
                if (resume.equals(r)) {
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