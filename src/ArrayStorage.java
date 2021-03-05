import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int count;
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    void save(Resume r) {
        if (size() <= storage.length) {
            storage[count] = r;
            count++;
        } else {
            System.out.println("Не хватает места для ввода нового резюме.");
        }
    }

    Resume get(String uuid) {
        try {
            for (Resume resume : storage) {
                if (uuid.equals((resume.uuid))) {
                    return resume;
                }
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    void delete(String uuid) {
        try {
            for (int i = 0; i <= size(); i++) {
                if (uuid.equals(storage[i].uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, size() - i);
                    count--;
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Указанное резюме отсутсвует.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    int size() {
        return Arrays.copyOf(storage, count).length;
    }
}
