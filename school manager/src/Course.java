public class Course {
    private int id;
    private String cn;
    private int type;

    public Course(int id, String cn, int type) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String t="";
        if(type==0){
            t="ACA";
        }
        if(type==1){
            t="KAP";
        }
        if(type==2){
            t="AP";
        }
        return t+" "+cn;
    }
}
