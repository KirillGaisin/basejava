/**
 * Initial resume class
 */
public class Resume {

    public Resume() {

    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // Unique identifier
    String uuid;

    @Override
    public String toString() {
        return uuid;
    }
}
