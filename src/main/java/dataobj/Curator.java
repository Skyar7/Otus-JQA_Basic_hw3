package dataobj;

public class Curator {

    private String id = "";
    private String fio = "";

    public Curator(String id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public String getID() {
        return id;
    }
    public String getFIO() {
        return fio;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", getID(), getFIO());
    }
}
