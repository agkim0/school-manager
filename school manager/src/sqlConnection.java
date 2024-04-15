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
            ResultSet rs= st.executeQuery("SELECT * FROM teachers;");
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
            ResultSet rs= st.executeQuery("SELECT * FROM students;");
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
            ResultSet rs= st.executeQuery("SELECT * FROM courses;");
            int i = 1;
            while(!rs.equals(null)&&rs.next()){
                try {
                    list.add(new Course(rs.getInt("course_id"),rs.getString("name"),rs.getString("type")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
