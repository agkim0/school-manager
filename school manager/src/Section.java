import java.util.ArrayList;

public class Section {
    private int id;
    private int course_id;
    private int teacher_id;

    public Section(int id, int course_id, int teacher_id) {
        this.id = id;
        this.course_id = course_id;
        this.teacher_id = teacher_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        ArrayList<Course> c = SMFrame.sql.getCourseList();
        for(int i = 0; i<c.size();i++){
            if(c.get(i).getId()==course_id){
                return id+" "+c.get(i).getCn();
            }
        }
        return id+"";
    }
}
