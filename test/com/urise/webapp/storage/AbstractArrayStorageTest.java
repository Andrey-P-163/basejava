package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    Storage storage;

    AbstractArrayStorageTest (Storage storage) {
        this.storage = storage;
    }

    Resume resume_1 = new Resume("UUID_1");
    Resume resume_2 = new Resume("UUID_2");
    Resume resume_3 = new Resume("UUID_3");
    Resume resume_4 = new Resume("UUID_4");

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
    }

    public void assertEqualsSize(int uuid) {
        Assert.assertEquals(uuid,storage.size());
    }

    public void assertEqualsObject (Resume resume, String string) {
        Assert.assertEquals(resume, storage.get(string));
    }

    @Test(expected = ExistStorageException.class)
    public void save() {
        storage.save(resume_4);
        assertEqualsSize(4);
        storage.save(resume_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("UUID_1");
        assertEqualsSize(2);
        storage.delete("UUID_5");
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        Resume upDateResume = new Resume("UUID_1");
        storage.update(upDateResume);
        Assert.assertSame(upDateResume, storage.get("UUID_1"));
        assertEqualsObject(resume_1,"UUID_5");
    }

    @Test
    public void clear() {
        storage.clear();
        assertEqualsSize(0);
    }

    @Test(expected = NotExistStorageException.class)
    public void get() {
        assertEqualsObject(resume_1,"UUID_1");
        assertEqualsObject(resume_2,"UUID_2");
        assertEqualsObject(resume_3,"UUID_3");
        assertEqualsObject(resume_1,"UUID_5");
    }

    @Test
    public void size() {
        assertEqualsSize(3);
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        resumes[0] = resume_1;
        resumes[1] = resume_2;
        resumes[2] = resume_3;
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}