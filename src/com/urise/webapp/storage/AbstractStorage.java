package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getPosition(String uuid);

    protected abstract void setResume(Resume resume, Object positionInBase);

    protected abstract void removeResume(Object positionInBase);

    protected abstract void updateResume(Resume resume, Object positionInBase);

    protected abstract Resume getResume(Object positionInBase);

    public void save(Resume resume) {
        Object positionInBase = getIndexIfResumeNotExist(resume.getUuid());
        setResume(resume, positionInBase);
    }

    public void delete(String uuid) {
        Object positionInBase = checkUuidNotExist(uuid);
        removeResume(positionInBase);
    }

    public void update(Resume resume) {
        Object positionInBase = checkUuidNotExist(resume.getUuid());
        updateResume(resume, positionInBase);
    }

    public Resume get(String uuid) {
        Object positionInBase = checkUuidNotExist(uuid);
        return getResume(positionInBase);
    }

    private Object getIndexIfResumeNotExist(String uuid) {
        Object positionInBase = getPosition(uuid);
        if (positionInBase instanceof String) {
            if (positionInBase.equals(uuid)) {
                throw new ExistStorageException(uuid);
            }
            return positionInBase;
        } else {
            if ((int) positionInBase >= 0) {
                throw new ExistStorageException(uuid);
            }
            return positionInBase;
        }
    }

    private Object checkUuidNotExist(String uuid) {
        Object positionInBase = getPosition(uuid);
        if (positionInBase instanceof String) {
            if (!positionInBase.equals(uuid)) {
                throw new NotExistStorageException(uuid);
            }
            return positionInBase;
        } else {
            if ((int) positionInBase < 0) {
                throw new NotExistStorageException(uuid);
            }
            return positionInBase;
        }
    }
}


