import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];

    private int size = 0;

    void clear() {
        for (int i = size - 1; i >= 0; i--) {
            storage[i] = null;
            size--;
        }
    }

    void save(Resume r) {
        // not null-safe
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("----------------------------\n" +
                        "Resume with this uuid is already in the storage! Enter another command");
                return;
            }
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = null;
                System.arraycopy(storage, i + 1, storage, i, size - 1);
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes = Arrays.copyOf(storage, size);
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
