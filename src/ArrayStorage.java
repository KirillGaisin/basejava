/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            try {
                storage[i].setUuid(null);
            } catch (NullPointerException ex) {
                System.out.println("----------------------------\n" +
                        "Resume storage has been successfully cleared.");
                break;
            }
        }
    }

    void save(Resume r) {
        // без уникальности
        for (int i = 0; i < storage.length; i++) {
            try {
                if(storage[i].getUuid().equals(r.getUuid())) {
                    System.out.println("----------------------------\n" +
                            "Resume with this uuid is already in the storage! Enter another command");
                    return;
                }
            } catch (NullPointerException ex) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        // метод выбивается из общего принципа логгирования при поимке нпе из-за необходимости вернуть значение; не нравится дважды return null
        for(int i=0; i<storage.length; i++) {
            try {
                if(storage[i].getUuid().equals(uuid))
                    return storage[i];
            } catch (NullPointerException ex) {
                return null;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for(int i=0; i<storage.length; i++) {
            try {
                if(storage[i].getUuid().equals(uuid))
                    storage[i].setUuid(null);
            } catch (NullPointerException ex) {
                System.out.println("----------------------------\n" +
                        "Element deleted. Enter another command");
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return storage;
    }

    int size() {
        return storage.length;
    }
}
