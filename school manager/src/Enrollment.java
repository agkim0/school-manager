import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
public class Enrollment {
    private int student_id;
    private int section_id;

    public Enrollment(int student_id, int section_id) {
        this.student_id = student_id;
        this.section_id = section_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

//    public Section getSection(){
//
//    }

    @Override
    public String toString() {
        String fn = "";
        String ln = "";
        ArrayList<Student> s = SMFrame.sql.getStudentList();
        for(int i = 0; i<s.size();i++){
            if(s.get(i).getId()==student_id){
                fn = s.get(i).getFn();
                ln = s.get(i).getLn();
            }
        }
        return student_id + " "+fn+" "+ln;
    }
}
