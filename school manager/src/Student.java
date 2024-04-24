public class Student {
    private int id;
    private String fn;
    private String ln;

    public Student(int id, String fn, String ln) {
        this.id = id;
        this.fn = fn;
        this.ln = ln;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String toString() {
        return id+" "+fn+" "+ln;
    }
}
