package dataobj;

public class Group {

    private String id = "";
    private String name = "";
    private String id_curator = "";

    public Group(String id, String name, String id_curator) {
        this.id = id;
        this.name = name;
        this.id_curator = id_curator;
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getID_curator() {
        return id_curator;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", getID(), getName(), getID_curator());
    }
}
