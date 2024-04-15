public class Course {
    private int id;
    private String cn;
    private String type;

    public Course(int id, String cn, String type) {
        this.id = id;
        this.cn = cn;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
