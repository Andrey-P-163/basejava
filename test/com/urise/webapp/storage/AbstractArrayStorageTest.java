package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    Storage storage;

    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";
    private static final String UUID_4 = "uuid_4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEqualsSize(4);
        assertEqualsResume(RESUME_4, "UUID_4");
    }

    @Test(expected = ExistStorageException.class)
    public void saveException() {
        storage.save(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteException() {
        storage.delete("UUID_1");
        assertEqualsSize(2);
        assertEqualsResume(RESUME_1, "UUID_1");
    }

    @Test
    public void update() {
        Resume updateResume = new Resume("UUID_1");
        storage.update(updateResume);
        Assert.assertSame(updateResume, storage.get("UUID_1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateException() {
        storage.get("UUID_5");
    }

    @Test
    public void clear() {
        storage.clear();
        assertEqualsSize(0);
    }

    @Test
    public void get() {
        assertEqualsResume(RESUME_1, "UUID_1");
        assertEqualsResume(RESUME_2, "UUID_2");
        assertEqualsResume(RESUME_3, "UUID_3");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void size() {
        assertEqualsSize(3);
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEqualsSize(3);
        assertEqualsResume(resumes[0], "UUID_1");
        assertEqualsResume(resumes[1], "UUID_2");
        assertEqualsResume(resumes[2], "UUID_3");
    }

    @Test(expected = StorageException.class)
    public void overFlowStorage() throws Exception {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени.");
        }
        storage.save(new Resume());
    }

    private void assertEqualsSize(int uuid) {
        Assert.assertEquals(uuid, storage.size());
    }

    private void assertEqualsResume(Resume resume, String uuid) {
        Assert.assertEquals(resume, storage.get(uuid));
    }
}