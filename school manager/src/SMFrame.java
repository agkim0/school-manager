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
    private JTextField id = new JTextField();
    private JTextField ln = new JTextField();
    private JTextField fn = new JTextField();
    private JTextField cn = new JTextField();
    private JList studentViewList = new JList();
    private JLabel studentViewText = new JLabel("Students");
    private JScrollPane sscroll = new JScrollPane(studentViewList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JLabel idLabel = new JLabel("ID: ");
    private JLabel fnameLabel = new JLabel("First Name: ");
    private JLabel lnameLabel = new JLabel("Last Name: ");
    private JLabel cnameLabel = new JLabel("Course Name: ");
    private JLabel courseTypeLabel = new JLabel("Course Type: ");
    private JLabel courseViewText = new JLabel("Courses");
    private JList courseViewList = new JList();
    private JScrollPane cscroll = new JScrollPane(courseViewList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JRadioButton courseTypeAP = new JRadioButton("AP");
    private JRadioButton courseTypeKAP = new JRadioButton("KAP");
    private JRadioButton courseTypeACA = new JRadioButton("Academic");
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
        cnameLabel.setFont(labels);
        courseTypeLabel.setFont(labels);

        id.setBounds(350,100,300,30);
        add(id);
        fn.setBounds(350,180,300,30);
        add(fn);
        ln.setBounds(350,260,300,30);
        add(ln);
        idLabel.setBounds(220,100,150,30);
        add(idLabel);
        fnameLabel.setBounds(220,180,150,30);
        add(fnameLabel);
        lnameLabel.setBounds(220,260,150,30);
        add(lnameLabel);

        //teacher view
        teacherViewText.setBounds(10,35,100,10);
        add(teacherViewText);
        tscroll.setBounds(10,50,175,600);
        add(tscroll);
        teacherViewList.addListSelectionListener(e->{selectedTeacher();});

        //student view
        studentViewText.setBounds(10,35,100,10);
        add(studentViewText);
        sscroll.setBounds(10,50,175,600);
        add(sscroll);
        studentViewList.addListSelectionListener(e->{selectedStudent();});

        //course view
        courseViewText.setBounds(10,35,100,10);
        add(courseViewText);
        cscroll.setBounds(10,50,175,600);
        cnameLabel.setBounds(220,180,150,30);
        add(cnameLabel);
        cn.setBounds(350,180,300,30);
        add(cn);
        courseTypeLabel.setBounds(220,260,300,30);
        add(courseTypeLabel);
        courseTypeACA.setBounds(350,260,100,30);
        add(courseTypeACA);
        courseTypeKAP.setBounds(450,260,100,30);
        add(courseTypeKAP);
        courseTypeAP.setBounds(550,260,100,30);
        add(courseTypeAP);
        id.setVisible(true);
        idLabel.setVisible(true);



        saveChanges.setBounds(220,500,200,30);
        add(saveChanges);
        saveChanges.addActionListener(e->{saveChanges();});






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
        studentItem.addActionListener(e->{studentView();});

        setVisible(true);
        setAllVisibilityFalse();
        id.setVisible(true);
        idLabel.setVisible(true);
//        teacherView();
    }

    public void setAllVisibilityFalse(){
        id.setVisible(false);
        fn.setVisible(false);
        ln.setVisible(false);
        idLabel.setVisible(false);
        fnameLabel.setVisible(false);
        lnameLabel.setVisible(false);
        teacherViewList.setVisible(false);
        teacherViewText.setVisible(false);
        tscroll.setVisible(false);
        studentViewList.setVisible(false);
        studentViewText.setVisible(false);
        sscroll.setVisible(false);

    }

    public void teacherView(){
        setAllVisibilityFalse();
        saveChanges.setVisible(false);
        cview = "teachers";
        id.setVisible(true);
        fn.setVisible(true);
        ln.setVisible(true);
        idLabel.setVisible(true);
        fnameLabel.setVisible(true);
        lnameLabel.setVisible(true);
        tscroll.setVisible(true);
//        sql.writeStatement("INSERT INTO teachers(first_name, last_name) VALUES('testfn','testln');");
        teacherViewList.setVisible(true);
        teacherViewText.setVisible(true);
        teacherViewList.setListData(sql.getTeacherList().toArray());

    }
    public void selectedTeacher(){
        Teacher curr = (Teacher) teacherViewList.getSelectedValue();
        if(curr==null){
            id.setText("");
            fn.setText("");
            ln.setText("");
        }
        else{
            saveChanges.setVisible(true);
            System.out.println("ID: "+curr.getId());
            id.setText(""+curr.getId());
            ln.setText(""+curr.getLn());
            fn.setText(""+curr.getFn());
        }
    }
    public void studentView(){
        setAllVisibilityFalse();
        saveChanges.setVisible(false);
        cview = "students";
        id.setVisible(true);
        fn.setVisible(true);
        ln.setVisible(true);
        idLabel.setVisible(true);
        fnameLabel.setVisible(true);
        lnameLabel.setVisible(true);
        sscroll.setVisible(true);
        studentViewList.setVisible(true);
        studentViewText.setVisible(true);
        studentViewList.setListData(sql.getStudentList().toArray());

    }
    public void selectedStudent(){
        Student curr = (Student) studentViewList.getSelectedValue();
        if(curr==null){
            id.setText("");
            fn.setText("");
            ln.setText("");
        }
        else{
            saveChanges.setVisible(true);
            System.out.println("ID: "+curr.getId());
            id.setText(""+curr.getId());
            ln.setText(""+curr.getLn());
            fn.setText(""+curr.getFn());
        }
    }
    public void courseView(){
        setAllVisibilityFalse();
        saveChanges.setVisible(false);
        cview = "students";
        id.setVisible(true);
        cnameLabel.setVisible(true);
        cn.setVisible(true);
        idLabel.setVisible(true);
        courseTypeLabel.setVisible(true);
        courseTypeKAP.setVisible(true);
        cscroll.setVisible(true);
        courseViewList.setVisible(true);
        courseViewText.setVisible(true);
        courseViewList.setListData(sql.getStudentList().toArray());

    }
    public void saveChanges(){
        if(cview.equals("teachers")){
            Teacher curr = (Teacher) teacherViewList.getSelectedValue();
            curr.setFn(fn.getText());
            curr.setLn(ln.getText());
            sql.writeStatement("UPDATE teachers SET first_name='"+curr.getFn()+"' WHERE teacher_id="+curr.getId()+";");
            sql.writeStatement("UPDATE teachers SET last_name='"+curr.getLn()+"' WHERE teacher_id="+curr.getId()+";");
            teacherView();
        }
        else if(cview.equals("students")){
            Student curr = (Student) studentViewList.getSelectedValue();
            curr.setFn(fn.getText());
            curr.setLn(ln.getText());
            sql.writeStatement("UPDATE students SET first_name='"+curr.getFn()+"' WHERE student_id="+curr.getId()+";");
            sql.writeStatement("UPDATE students SET last_name='"+curr.getLn()+"' WHERE student_id="+curr.getId()+";");
            teacherView();
        }

    }
    public void newEntry(){
        id.setText("");
        fn.setText("");
        ln.setText("");
    }
    public void saveEntry(){
        sql.writeStatement("INSERT INTO teachers(first_name, last_name) VALUES('"+fn.getText()+"','"+fn.getText()+"');");

    }
}
