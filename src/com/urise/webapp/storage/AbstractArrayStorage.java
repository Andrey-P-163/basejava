package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected int count = 0;
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(Resume resume, int index);

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (count < STORAGE_LIMIT) {
            if (index >= 0) {
                System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " уже внесено в базу.");
            } else {
                saveElement(resume, index);
                count++;
            }
        } else {
            System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " не сохранено. В базе нет места для сохранения нового резюме.");
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, (index + 1), storage, index, (count - index - 1));
            count--;
        } else {
            System.out.println("ERROR: Резюме с uuid = " + uuid + " нет базе. Удаление невозможно.");
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " не обновлено.");
        }
    }

    public final void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("ERROR: Резюме с uuid = " + uuid + " нет в базе.");
        return null;
    }

    public int size() {
        return count;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }
}