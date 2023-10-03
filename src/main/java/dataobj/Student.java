package dataobj;

public class Student {

    private String id = "";
    private String fio = "";
    private String sex = "";
    private String id_group = "";

    public Student(String id, String fio, String sex, String id_group) {
        this.id = id;
        this.fio = fio;
        this.sex = sex;
        this.id_group = id_group;
    }

    public String getID() {
        return id;
    }
    public String getFIO() {
        return fio;
    }
    public String getSex() {
        return sex;
    }
    public String getID_group() {
        return id_group;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", getID(), getFIO(), getSex(), getID_group());
    }
}
