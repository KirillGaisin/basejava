import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private int size = 0;

    void clear() {
        if (size == 0) {
            System.out.println("----------------------------\n" +
                    "Resume storage is already empty.");
        } else {
            for (int i = size - 1; i >= 0; i--) {
                storage[i] = null;
                size--;
            }
        }
    }

    void save(Resume r) {
        // not null-safe
        if(size != 0) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    System.out.println("----------------------------\n" +
                            "Resume with this uuid is already in the storage! Enter another command");
                    return;
                }
            }
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        if (size == 0) {
            System.out.println("----------------------------\n" +
                    "Resume storage is empty.");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid))
                    return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (size == 0) {
            System.out.println("----------------------------\n" +
                    "Resume storage is empty.");
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = null;
                    for (int j = i; j < size; j++) {
                        Resume temp = storage[j + 1];
                        storage[j + 1] = storage[j];
                        storage[j] = temp;
                    }
                    size--;
                    break;
                } else if (i == 0 && !storage[i].getUuid().equals(uuid)) {
                    System.out.println("----------------------------\n" +
                            "No such element present in the storage");
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i <= size; i++) {
            resumes = Arrays.copyOf(storage, i);
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
