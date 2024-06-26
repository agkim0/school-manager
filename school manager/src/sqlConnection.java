import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

public class sqlConnection {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public sqlConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.connect  = DriverManager.getConnection("jdbc:mysql://localhost:3306/akim_p6_school_manager","root","password");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void writeStatement(String statement){
        try{
            Statement st = connect.createStatement();
            st.execute(statement);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Teacher> getTeacherList(){
        ArrayList<Teacher> list = new ArrayList<>();
        try{
            Statement st = connect.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM teacher;");
            int i = 1;
            while(!rs.equals(null)&&rs.next()){
                try {
                    list.add(new Teacher(rs.getInt("teacher_id"),rs.getString("first_name"),rs.getString("last_name")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Student> getStudentList(){
        ArrayList<Student> list = new ArrayList<>();
        try{
            Statement st = connect.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM student;");
            int i = 1;
            while(!rs.equals(null)&&rs.next()){
                try {
                    list.add(new Student(rs.getInt("student_id"),rs.getString("first_name"),rs.getString("last_name")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Course> getCourseList(){
        ArrayList<Course> list = new ArrayList<>();
        try{
            Statement st = connect.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM course;");
            int i = 1;
            while(!rs.equals(null)&&rs.next()){
                try {
                    list.add(new Course(rs.getInt("course_id"),rs.getString("title"),rs.getInt("type")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Section> getSectionList(Course c){
        ArrayList<Section> list = new ArrayList<>();
        if(c!=null){
            try{
                Statement st = connect.createStatement();
                ResultSet rs= st.executeQuery("SELECT * FROM section;");
                int i = 1;
                while(!rs.equals(null)&&rs.next()){
                    try {
                        if(rs.getInt("course_id")==c.getId()){
                            list.add(new Section(rs.getInt("section_id"),rs.getInt("course_id"),rs.getInt("teacher_id")));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            try{
                Statement st = connect.createStatement();
                ResultSet rs= st.executeQuery("SELECT * FROM section;");
                int i = 1;
                while(!rs.equals(null)&&rs.next()){
                    try {
                        list.add(new Section(rs.getInt("section_id"),rs.getInt("course_id"),rs.getInt("teacher_id")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;

    }
    public ArrayList<Section> getSectionsTaught(Teacher t){
        ArrayList<Section> list = new ArrayList<>();
        if(t!=null){
            try{
                Statement st = connect.createStatement();
                ResultSet rs= st.executeQuery("SELECT * FROM section;");
                int i = 1;
                while(!rs.equals(null)&&rs.next()){
                    try {
                        if(rs.getInt("teacher_id")==t.getId()){
                            list.add(new Section(rs.getInt("section_id"),rs.getInt("course_id"),rs.getInt("teacher_id")));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            try{
                Statement st = connect.createStatement();
                ResultSet rs= st.executeQuery("SELECT * FROM section;");
                int i = 1;
                while(!rs.equals(null)&&rs.next()){
                    try {
                        list.add(new Section(rs.getInt("section_id"),rs.getInt("course_id"),rs.getInt("teacher_id")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;

    }
    public ArrayList<Enrollment> getEnrollmentList(Section s){
        ArrayList<Enrollment> list = new ArrayList<>();
        if(s==null){
            try{
                Statement st = connect.createStatement();
                ResultSet rs= st.executeQuery("SELECT * FROM enrollment;");
                int i = 1;
                while(!rs.equals(null)&&rs.next()){
                    try {
                        list.add(new Enrollment(rs.getInt("student_id"),rs.getInt("section_id")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            try{
                Statement st = connect.createStatement();
                ResultSet rs= st.executeQuery("SELECT * FROM enrollment;");
                int i = 1;
                while(!rs.equals(null)&&rs.next()){
                    try {
                        if(rs.getInt("section_id")==s.getId()){
                            list.add(new Enrollment(rs.getInt("student_id"),rs.getInt("section_id")));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return list;
    }

    public Section fromSectID(int id){
        ArrayList<Section> sectList = getSectionList(null);
        for(int i=0; i<sectList.size();i++){
            if(sectList.get(i).getId()==id){
                return sectList.get(i);
            }
        }
        return null;
    }

    public ResultSet snQueryEx(String table){
        try{
            Statement st = connect.createStatement();
            return st.executeQuery("Select * FROM "+table+";");
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }


    }

//    public ArrayList<Enrollment> getStudentsNotEnrolledInSectList(Section s){
//        ArrayList<Enrollment> list = new ArrayList<>();
//        if(s==null){
//            try{
//                Statement st = connect.createStatement();
//                ResultSet rs= st.executeQuery("SELECT * FROM enrollment;");
//                int i = 1;
//                while(!rs.equals(null)&&rs.next()){
//                    try {
//                        list.add(new Enrollment(rs.getInt("student_id"),rs.getInt("section_id")));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        else {
//            try {
//                Statement st = connect.createStatement();
//                ResultSet rs = st.executeQuery("SELECT * FROM enrollment;");
//                int i = 1;
//                while (!rs.equals(null) && rs.next()) {
//                    try {
//                        if (rs.getInt("section_id") != s.getId()) {
//                            list.add(new Enrollment(rs.getInt("student_id"), rs.getInt("section_id")));
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//
//    }
}
