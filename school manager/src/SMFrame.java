import javax.swing.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SMFrame extends JFrame {

    private JMenuBar mb = new JMenuBar();

    public static sqlConnection sql = new sqlConnection();

    JMenu file = new JMenu("File");
        JMenuItem exportItem = new JMenuItem("Export Data");
        JMenuItem importItem = new JMenuItem("Import Data");
        JMenuItem purgeItem = new JMenuItem("Purge");
        JMenuItem exitItem = new JMenuItem("Exit");

    JMenu view = new JMenu("View");
        JMenuItem teacherItem = new JMenuItem("Teachers");
        JMenuItem studentItem = new JMenuItem("Students");
        JMenuItem courseItem = new JMenuItem("Courses");
        JMenuItem sectionItem = new JMenuItem("Sections");


    JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");

    private JList teacherViewList = new JList<>();
    private JLabel teacherViewText = new JLabel("Teachers");
    private JScrollPane scroll = new JScrollPane(teacherViewList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    public SMFrame(){
        super("School Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,800);
        setResizable(true);
        setAlwaysOnTop(true);
        setVisible(true);
        setLayout(null);

        setJMenuBar(mb);
        mb.add(file);
        file.add(exportItem);
        file.add(importItem);
        file.add(purgeItem);
        file.add(exitItem);

        mb.add(view);
        view.add(teacherItem);
        view.add(studentItem);
        view.add(courseItem);
        view.add(sectionItem);

        mb.add(help);
        help.add(about);

        add(mb);

        teacherViewText.setBounds(10,35,100,10);
        add(teacherViewText);
        scroll.setBounds(10,50,175,600);
        add(scroll);

        sql.writeStatement("CREATE TABLE IF NOT EXISTS teachers(" +
                "teacher_id INTEGER NOT NULL AUTO_INCREMENT," +
                "first_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
                " sections TEXT NOT NULL," +
                "PRIMARY KEY(teacher_id)" +
                ");");

        sql.writeStatement("CREATE TABLE IF NOT EXISTS students(" +
                "student_id INTEGER NOT NULL AUTO_INCREMENT," +
                "first_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
                "PRIMARY KEY(student_id));");

        sql.writeStatement("CREATE TABLE IF NOT EXISTS courses(" +
                "course_id INTEGER NOT NULL AUTO_INCREMENT," +
                "name TEXT NOT NULL," +
                "type TEXT NOT NULL," +
                "PRIMARY KEY(course_id));");
        sql.writeStatement("CREATE TABLE IF NOT EXISTS section(section_id INTEGER NOT NULL AUTO_INCREMENT,"+
                "course_id INTEGER NOT NULL,"+
                "teacher_id INTEGER NOT NULL,"+
                "PRIMARY KEY(section_id),"+
                "FOREIGN KEY(course_id) REFERENCES courses(course_id) "+
                "ON UPDATE CASCADE "+
                "ON DELETE CASCADE,"+
                "FOREIGN KEY(teacher_id) REFERENCES teachers(teacher_id) ON UPDATE CASCADE ON DELETE CASCADE" +
                ");");
    }

    public void teacherView(){
        ArrayList<String> tname = new ArrayList<>();
        teacherViewList.setVisible(true);
        teacherViewText.setVisible(true);
        ResultSet rs;
        while(!rs.equals(null)){
            tname.get
        }

    }
}
