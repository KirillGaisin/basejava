import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10];

    void clear() {
        for(Resume r: storage) {
            try {
                r.setUuid(null);
            } catch (NullPointerException ex) {
                System.out.println("----------------------------\n" +
                        "Resume storage has been successfully cleared.");
                break;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            try {
                if (storage[i].getUuid().equals(r.getUuid())) {
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
        for (Resume r:storage) {
            try {
                if (r.getUuid().equals(uuid))
                    return r;
            } catch (NullPointerException ex) {
                return null;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            try {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i].setUuid(null);
                    // из-за этого цикла падаем в блок кетч, поэтому нет сообщения об ошибке при удалении несуществующего элемента (костыльно)
                    for (int j = i + 1; j < storage.length; j++) {
                        if (storage[j] != null) {
                            storage[i] = storage[j];
                            storage[j] = null;
                            i++;
                        }
                    }
                    break;
                }
            } catch (NullPointerException ex) {
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] noNulls = new Resume[10];
        // почему при вызове метода delete на последнем не нулевом элементе массива null остается в массиве в этом методе?
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                noNulls = Arrays.copyOf(storage, i);
                break;
            }
        }
        return noNulls;
    }

    int size() {
        return storage.length;
    }
}
