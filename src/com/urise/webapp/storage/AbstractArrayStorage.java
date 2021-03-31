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

    public final int size() {
        return count;
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (count < STORAGE_LIMIT) {
            if (index >= 0) {
                System.out.println("ERROR: Резюме с uuid = " + resume.getUuid() + " уже внесено в базу.");
            } else {
                System.arraycopy(storage, (index * -1 - 1), storage, (index * -1), (count - index - 1));
                storage[index * -1 - 1] = resume;
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, count);
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

    protected abstract int getIndex(String uuid);
}