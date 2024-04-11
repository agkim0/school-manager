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

public class SMFrame extends JFrame {
    private Font labels = new Font("Serif",Font.BOLD,20);
    private JMenuBar mb = new JMenuBar();
    private String cview;

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
    private JScrollPane tscroll = new JScrollPane(teacherViewList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JTextField teacherID = new JTextField();
    private JTextField teacherLN = new JTextField();
    private JTextField teacherFN = new JTextField();
    private JLabel idLabel = new JLabel("ID: ");
    private JLabel fnameLabel = new JLabel("First Name: ");
    private JLabel lnameLabel = new JLabel("Last Name: ");
    private JButton saveChanges = new JButton("Save Changes");

    public SMFrame(){
        super("School Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,800);
        setResizable(true);
//        setAlwaysOnTop(true);
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

        idLabel.setFont(labels);
        fnameLabel.setFont(labels);
        lnameLabel.setFont(labels);
        saveChanges.setFont(labels);

        //teacher view
        teacherViewText.setBounds(10,35,100,10);
        add(teacherViewText);
        tscroll.setBounds(10,50,175,600);
        add(tscroll);
        teacherID.setBounds(350,100,300,30);
        add(teacherID);
        teacherFN.setBounds(350,180,300,30);
        add(teacherFN);
        teacherLN.setBounds(350,260,300,30);
        add(teacherLN);
        teacherViewList.addListSelectionListener(e->{selectedTeacher();});

        idLabel.setBounds(220,100,150,30);
        add(idLabel);
        fnameLabel.setBounds(220,180,150,30);
        add(fnameLabel);
        lnameLabel.setBounds(220,260,150,30);
        add(lnameLabel);

        saveChanges.setBounds(220,500,200,30);
        add(saveChanges);
        saveChanges.addActionListener(e->{saveChanges(cview);});






//        sql.writeStatement("DROP TABLE IF EXISTS section;");
//        sql.writeStatement("DROP TABLE IF EXISTS courses;");
//        sql.writeStatement("DROP TABLE IF EXISTS students;");
//        sql.writeStatement("DROP TABLE IF EXISTS teachers;"); folds everything
        sql.writeStatement("CREATE TABLE IF NOT EXISTS teachers(" +
                "teacher_id INTEGER NOT NULL AUTO_INCREMENT," +
                "first_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
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

//        sql.writeStatement("INSERT INTO teachers(first_name, last_name, sections) VALUES('testfn','testln','testsec');");
        teacherItem.addActionListener(e->{teacherView();});

        setVisible(true);
    }

    public void teacherView(){
        saveChanges.setVisible(false);
        cview = "teachers";
//        sql.writeStatement("INSERT INTO teachers(first_name, last_name) VALUES('testfn','testln');");
        ArrayList<Teacher> tname = sql.getTeacherList();
        teacherViewList.setVisible(true);
        teacherViewText.setVisible(true);
        teacherViewList.setListData(tname.toArray());

    }
    public void selectedTeacher(){
        Teacher curr = (Teacher) teacherViewList.getSelectedValue();
        if(curr==null){
            teacherID.setText("");
            teacherFN.setText("");
            teacherLN.setText("");
        }
        else{
            saveChanges.setVisible(true);
            System.out.println("ID: "+curr.getId());
            teacherID.setText(""+curr.getId());
            teacherLN.setText(""+curr.getLn());
            teacherFN.setText(""+curr.getFn());
        }


    }
    public void saveChanges(String table){
        if(table.equals("teachers")){
            Teacher curr = (Teacher) teacherViewList.getSelectedValue();
            curr.setFn(teacherFN.getText());
            curr.setLn(teacherLN.getText());
            sql.writeStatement("UPDATE teachers SET first_name='"+curr.getFn()+"' WHERE teacher_id="+curr.getId()+";");
            sql.writeStatement("UPDATE teachers SET last_name='"+curr.getLn()+"' WHERE teacher_id="+curr.getId()+";");
            teacherView();
        }

    }
    public void newEntry(){
        teacherID.setText("");
        teacherFN.setText("");
        teacherLN.setText("");
    }
    public void saveEntry(){
        sql.writeStatement("INSERT INTO teachers(first_name, last_name) VALUES('"+teacherFN.getText()+"','"+teacherLN.getText()+"');");

    }
}
