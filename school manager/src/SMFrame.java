import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Scanner;

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
    private JList taughtViewList = new JList<>();
    private JScrollPane taughtscroll = new JScrollPane(taughtViewList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JLabel taughtLabel = new JLabel("Sections Taught");
    private JLabel id = new JLabel();
    private JTextField ln = new JTextField();
    private JTextField fn = new JTextField();
    private JTextField cn = new JTextField();
    private JList studentViewList = new JList();
    private JLabel studentViewText = new JLabel("Students");
    private JScrollPane sscroll = new JScrollPane(studentViewList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JList enrollViewList = new JList();
    private JScrollPane enscroll = new JScrollPane(enrollViewList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JLabel scheduleLabel = new JLabel("Schedule");
    private JLabel courseTitleLabel = new JLabel("Course Title: ");
    private JLabel teacherIDandNameLable = new JLabel("Teacher ID and Name: ");
    private JLabel sectionID = new JLabel("Section ID: ");
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
    private JLabel sectionsViewText = new JLabel("Sections for Course:");
    private JList sectionsViewList = new JList();
    private JScrollPane secscroll = new JScrollPane(sectionsViewList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JComboBox<Course> courseBox = new JComboBox<Course>();
    private JComboBox<Teacher> teacherBox = new JComboBox<Teacher>();
    private JLabel teachersInSecLabel = new JLabel("Teacher: ");
    private JLabel courseSectLabel = new JLabel("Course: ");
    private JLabel studentsNotInSecLable = new JLabel("Not in Section");
    private JLabel studentsInSecLable = new JLabel("In Section");
    private JList studentsNotInSecList = new JList<>();
    private JList studentsInSecList = new JList<>();
    private JScrollPane stuNotSecscroll = new JScrollPane(studentsNotInSecList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JScrollPane stusectscroll = new JScrollPane(studentsInSecList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JButton moveStudentLeft = new JButton("<");
    private JButton moveStudentRight = new JButton(">");
    private JButton sectNew = new JButton("New Section");
    private JButton sectDelete = new JButton("Delete Section");
    private JButton sectChanges = new JButton("Save Changes");
    private JButton sectSave = new JButton("Save Section");
    private JButton saveChanges = new JButton("Save Changes");
    private JButton saveEntry = new JButton("Save Entry");
    private JButton newEntry = new JButton("New Entry");
    private JButton deleteEntry = new JButton("Delete Entry");
    private JLabel abtText = new JLabel("About: This program was created by Audrey Kim");

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
        lnameLabel.setFont(labels);id.setFont(labels);
//        saveChanges.setFont(labels);
        cnameLabel.setFont(labels);
        courseTypeLabel.setFont(labels);
        teachersInSecLabel.setFont(labels);
        courseSectLabel.setFont(labels);

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
        taughtscroll.setBounds(220,320,200,150);
        add(taughtscroll);
        taughtLabel.setBounds(220,300,100,10);
        add(taughtLabel);
        teacherViewList.addListSelectionListener(e->{selectedTeacher();});

        //student view
        studentViewText.setBounds(10,35,100,10);
        add(studentViewText);
        sscroll.setBounds(10,50,175,600);
        add(sscroll);
        enscroll.setBounds(220,320,200,150);
        add(enscroll);
        scheduleLabel.setBounds(220,300,100,10);
        add(scheduleLabel);
        courseTitleLabel.setBounds(440, 330,200,15);
        add(courseTitleLabel);
        teacherIDandNameLable.setBounds(440,350,300,15);
        add(teacherIDandNameLable);
        sectionID.setBounds(440,370,200,15);
        add(sectionID);
        studentViewList.addListSelectionListener(e->{selectedStudent();});
        enrollViewList.addListSelectionListener(e->{enrollSelected();});


        //course view
        courseViewText.setBounds(10,35,100,10);
        add(courseViewText);
        cscroll.setBounds(10,50,175,600);
        add(cscroll);
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
        courseViewList.addListSelectionListener(e->{selectedCourse();});

        //sections view
        sectionsViewText.setBounds(10,35,230,10);
        add(sectionsViewText);
        secscroll.setBounds(10,50,175,600);
        add(secscroll);
        courseBox.setBounds(350,30,300,30);
        add(courseBox);
        ArrayList<Course> c = sql.getCourseList();
        for(int i = 0; i<c.size();i++){
            courseBox.addItem(c.get(i));
        }
        courseSectLabel.setBounds(220,30,150,30);
        add(courseSectLabel);
        teachersInSecLabel.setBounds(220,180,150,30);
        add(teachersInSecLabel);
        teacherBox.setBounds(350,180,300,30);
        add(teacherBox);
        stuNotSecscroll.setBounds(220,350,150,300);
        add(stuNotSecscroll);
        stusectscroll.setBounds(490,350,150,300);
        add(stusectscroll);
        studentsNotInSecLable.setBounds(220,330, 100,20);
        add(studentsNotInSecLable);
        studentsInSecLable.setBounds(490,330, 100,20);
        add(studentsInSecLable);
        moveStudentLeft.setBounds(380,470,100,40);
        add(moveStudentLeft);
        moveStudentRight.setBounds(380,530,100,40);
        add(moveStudentRight);
        sectNew.setBounds(220,230,200,30);
        add(sectNew);
        sectSave.setBounds(430,230,200,30);
        add(sectSave);
        sectChanges.setBounds(430,230,200,30);
        add(sectChanges);
        sectDelete.setBounds(220,270,200,30);
        add(sectDelete);
        courseBox.addActionListener(e->{courseBoxSelected();});
        sectChanges.addActionListener(e->{sectChanges();});
        sectDelete.addActionListener(e->{sectDelete();});
        sectSave.addActionListener(e->{sectSave();});
        sectNew.addActionListener(e->{sectNew();});
        sectionsViewList.addListSelectionListener(e->{selectedSection();});
        moveStudentLeft.addActionListener(e->{moveStudentLeft();});
        moveStudentRight.addActionListener(e->{moveStudentRight();});





        id.setVisible(true);
        idLabel.setVisible(true);



        saveChanges.setBounds(220,500,200,30);
        add(saveChanges);
        saveChanges.addActionListener(e->{saveChanges();});
        saveEntry.setBounds(220,500,200,30);
        add(saveEntry);
        saveEntry.addActionListener(e->{saveEntry();});
        newEntry.setBounds(430,500,200,30);
        add(newEntry);
        newEntry.addActionListener(e->{newEntry();});
        deleteEntry.setBounds(220,540,200,30);
        add(deleteEntry);
        deleteEntry.addActionListener(e->{deleteEntry();});

        abtText.setBounds(10,10,500,500);
        add(abtText);
        abtText.setFont(labels);
        about.addActionListener(e->{about();});
        exitItem.addActionListener(e->{exit();});
        importItem.addActionListener(e->{importAction();});
        exportItem.addActionListener(e->{exportAction();});
        purgeItem.addActionListener(e->{purge();});






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
        sql.writeStatement("CREATE TABLE IF NOT EXISTS enrollment(section_id INTEGER NOT NULL, " +
                "student_id INTEGER NOT NULL, " +
                "PRIMARY KEY(section_id,student_id), " +
                "FOREIGN KEY(section_id) REFERENCES section(section_id) ON UPDATE CASCADE ON DELETE CASCADE," +
                "FOREIGN KEY(student_id) REFERENCES students(student_id) ON UPDATE CASCADE ON DELETE CASCADE" +
                ");");

        ArrayList<Teacher> t = sql.getTeacherList();
        boolean exists = false;
        for(int i = 0;i<t.size();i++){
            if(t.get(i).getId()==-1){
                exists = true;
            }
        }
        if(!exists){
            sql.writeStatement("INSERT INTO teachers(teacher_id,first_name,last_name) VALUES(-1,'No','Teacher');");
        }

//        sql.writeStatement("INSERT INTO teachers(first_name, last_name, sections) VALUES('testfn','testln','testsec');");
        teacherItem.addActionListener(e->{teacherView();});
        studentItem.addActionListener(e->{studentView();});
        sectionItem.addActionListener(e->{sectionView();});
        courseItem.addActionListener(e->{courseView();});
        courseTypeKAP.addActionListener(e->{courseTypeButtons("KAP");});
        courseTypeACA.addActionListener(e->{courseTypeButtons("ACA");});
        courseTypeAP.addActionListener(e->{courseTypeButtons("AP");});

        setVisible(true);
        setAllVisibilityFalse();
        id.setVisible(true);
        idLabel.setVisible(true);
        setAllVisibilityFalse();
        teacherView();
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
        taughtViewList.setVisible(false);
        taughtscroll.setVisible(false);
        taughtLabel.setVisible(false);

        studentViewList.setVisible(false);
        studentViewText.setVisible(false);
        sscroll.setVisible(false);
        enscroll.setVisible(false);
        enrollViewList.setVisible(false);
        courseTitleLabel.setVisible(false);
        teacherIDandNameLable.setVisible(false);
        sectionID.setVisible(false);
        scheduleLabel.setVisible(false);

        cn.setVisible(false);
        courseViewList.setVisible(false);
        courseViewText.setVisible(false);
        courseTypeACA.setVisible(false);
        courseTypeAP.setVisible(false);
        courseTypeKAP.setVisible(false);
        courseTypeLabel.setVisible(false);
        cnameLabel.setVisible(false);
        cscroll.setVisible(false);

        sectionsViewText.setVisible(false);
        secscroll.setVisible(false);
        teachersInSecLabel.setVisible(false);
        teacherBox.setVisible(false);
        courseSectLabel.setVisible(false);
        courseBox.setVisible(false);
        stusectscroll.setVisible(false);
        sectDelete.setVisible(false);
        sectChanges.setVisible(false);
        sectSave.setVisible(false);
        sectNew.setVisible(false);
        stusectscroll.setVisible(false);
        stuNotSecscroll.setVisible(false);
        studentsInSecList.setVisible(false);
        studentsNotInSecList.setVisible(false);
        moveStudentLeft.setVisible(false);
        moveStudentRight.setVisible(false);
        studentsInSecLable.setVisible(false);
        studentsNotInSecLable.setVisible(false);

        saveEntry.setVisible(false);
        saveChanges.setVisible(false);
        newEntry.setVisible(false);
        deleteEntry.setVisible(false);
        abtText.setVisible(false);
    }

    public void teacherView(){
        setAllVisibilityFalse();
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
        ArrayList<Teacher> t = sql.getTeacherList();
        for(int i =0;i<t.size();i++){
            if(t.get(i).getId()==-1){
                t.remove(i);
                break;
            }
        }
        teacherViewList.setListData(t.toArray());
        taughtViewList.setVisible(true);
        taughtscroll.setVisible(true);
        taughtLabel.setVisible(true);

        newEntry();

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
            saveEntry.setVisible(false);
            deleteEntry.setVisible(true);
            System.out.println("ID: "+curr.getId());
            id.setText(""+curr.getId());
            ln.setText(""+curr.getLn());
            fn.setText(""+curr.getFn());
            taughtViewList.setListData(getTaught(curr).toArray());
        }
    }
    public ArrayList<Section> getTaught(Teacher t){
        ArrayList<Section> secList = sql.getSectionList(null);
        ArrayList<Section> taught = new ArrayList<>();
        for(int i = 0;i<secList.size();i++){
            if(secList.get(i).getTeacher_id()==t.getId()){
                taught.add(secList.get(i));
            }
        }
        return taught;
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
        enrollViewList.setVisible(true);
        enscroll.setVisible(true);
        enrollViewList.setListData(getSchedule(null).toArray());
        courseTitleLabel.setVisible(true);
        teacherIDandNameLable.setVisible(true);
        sectionID.setVisible(true);
        scheduleLabel.setVisible(true);
        courseTitleLabel.setText("Course Title: ");
        teacherIDandNameLable.setText("Teacher ID and Name: ");
        sectionID.setText("Section ID: ");
        newEntry();

    }
    public ArrayList<Section> getSchedule(Student s){
        ArrayList<Enrollment> enList = sql.getEnrollmentList(null);
        ArrayList<Section> secList = new ArrayList<>();
        if(s==null){
            System.out.println("returning null res");
            return new ArrayList<>();
        }
        else{
            for(int i = 0;i<enList.size();i++){
                if(enList.get(i).getStudent_id()==s.getId()){
                    secList.add(sql.fromSectID(enList.get(i).getSection_id()));
                    System.out.println("adding section: "+sql.fromSectID(enList.get(i).getSection_id()));
                }
            }
            return secList;
        }
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
            saveEntry.setVisible(false);
            deleteEntry.setVisible(true);
            System.out.println("ID: "+curr.getId());
            id.setText(""+curr.getId());
            ln.setText(""+curr.getLn());
            fn.setText(""+curr.getFn());
            System.out.println(getSchedule(curr));
            enrollViewList.setListData(getSchedule(curr).toArray());
            courseTitleLabel.setText("Course: ");
            teacherIDandNameLable.setText("Teacher ID and Name: ");
            sectionID.setText("Section ID: ");
        }
    }

    public void enrollSelected(){
        Section curr = (Section) enrollViewList.getSelectedValue();
        if(curr!=null){
            courseTitleLabel.setText("Course: "+curr.getCourse().getCn());
            teacherIDandNameLable.setText("Teacher ID and Name: "+curr.getTeacher().getId()+" - "+curr.getTeacher().getFn()+" "+curr.getTeacher().getLn());
            sectionID.setText("Section ID: "+curr.getId());
        }

    }
    public void courseView(){
        setAllVisibilityFalse();
        saveChanges.setVisible(false);
        cview = "courses";
        id.setVisible(true);
        cnameLabel.setVisible(true);
        cn.setVisible(true);
        idLabel.setVisible(true);
        courseTypeLabel.setVisible(true);
        courseTypeKAP.setVisible(true);
        courseTypeAP.setVisible(true);
        courseTypeACA.setVisible(true);
        cscroll.setVisible(true);
        courseViewList.setVisible(true);
        courseViewText.setVisible(true);
        courseViewList.setListData(sql.getCourseList().toArray());
        newEntry();
    }
    public void selectedCourse(){
        Course curr = (Course) courseViewList.getSelectedValue();
        if(curr==null){
            id.setText("");
            cn.setText("");
            courseTypeACA.setSelected(false);
            courseTypeKAP.setSelected(false);
            courseTypeAP.setSelected(false);
        }
        else{
            saveChanges.setVisible(true);
            saveEntry.setVisible(false);
            deleteEntry.setVisible(true);
            System.out.println("ID: "+curr.getId());
            id.setText(""+curr.getId());
            cn.setText(""+curr.getCn());
            courseTypeACA.setSelected(false);
            courseTypeKAP.setSelected(false);
            courseTypeAP.setSelected(false);
            if(curr.getType()==0){
                courseTypeACA.setSelected(true);
            }
            else if(curr.getType()==1){
                courseTypeKAP.setSelected(true);
            }
            else if(curr.getType()==2){
                courseTypeAP.setSelected(true);
            }
        }
    }
    public void sectionView(){
        cview="sections";
        setAllVisibilityFalse();
        id.setVisible(true);
        idLabel.setVisible(true);
        secscroll.setVisible(true);
        stusectscroll.setVisible(true);
        teacherBox.setVisible(true);
        teachersInSecLabel.setVisible(true);
        courseBox.setVisible(true);
        courseSectLabel.setVisible(true);
        sectionsViewText.setVisible(true);
        sectNew.setVisible(true);
        sectSave.setVisible(true);
        stusectscroll.setVisible(true);
        stuNotSecscroll.setVisible(true);
        studentsNotInSecList.setVisible(true);
        studentsInSecList.setVisible(true);
        studentsNotInSecLable.setVisible(true);
        studentsInSecLable.setVisible(true);
        moveStudentLeft.setVisible(true);
        moveStudentRight.setVisible(true);
        ArrayList<Course> c = sql.getCourseList();
        courseBox.removeAllItems();
        for(int i = 0; i<c.size();i++){
            courseBox.addItem(c.get(i));
        }
        ArrayList<Teacher> t = sql.getTeacherList();
        teacherBox.removeAllItems();
        for(int i = 0; i<t.size();i++){
            teacherBox.addItem(t.get(i));
        }
        courseBox.setSelectedItem(null);
        teacherBox.setSelectedItem(null);
        sectionsViewList.setListData((new ArrayList<Section>()).toArray());
        studentsInSecList.setListData((new ArrayList<Section>()).toArray());
        studentsNotInSecList.setListData((new ArrayList<Section>()).toArray());

//        if(courseBox.getSelectedItem()!=null){
//            sectionsViewList.setListData(sql.getSectionList((Course) courseBox.getSelectedItem()).toArray());
//        }



    }
    public void sectionView2(){
        if(courseBox.getSelectedItem()!=null){
            teacherBox.setSelectedItem(null);
            id.setText("");
            sectChanges.setVisible(false);
            sectDelete.setVisible(false);
            sectSave.setVisible(true);
            sectionsViewList.setListData(sql.getSectionList((Course) courseBox.getSelectedItem()).toArray());
            studentSorter(null);
        }

    }

    public void studentSorter(Section s){
        if(s==null){
            ArrayList<Enrollment> n = new ArrayList<>();
            studentsNotInSecList.setListData(n.toArray());
            studentsInSecList.setListData(n.toArray());
        }
        else{
            ArrayList<Student> studs = sql.getStudentList();
            ArrayList<Enrollment> enroll = sql.getEnrollmentList(s);
            ArrayList<Student> enrolled = new ArrayList<>();
            ArrayList<Student> notEnrolled = new ArrayList<>();

            for(int x=0;x<studs.size();x++){
                int stuID = studs.get(x).getId();
                boolean found = false;
                for(int y = 0; y<enroll.size();y++){
                    if(stuID==enroll.get(y).getStudent_id()){
                        enrolled.add(studs.get(x));
                        found = true;
                    }
                }
                if(!found){
                    notEnrolled.add(studs.get(x));
                }
            }
            studentsNotInSecList.setListData(notEnrolled.toArray());
            studentsInSecList.setListData(enrolled.toArray());
        }

    }
    public void courseBoxSelected(){
            sectionsViewList.setListData(sql.getSectionList((Course) courseBox.getSelectedItem()).toArray());
            sectionView2();

    }
    public void sectSave(){
        if(teacherBox.getSelectedItem()!=null&&courseBox.getSelectedItem()!=null){
            Course co = (Course) courseBox.getSelectedItem();
            Teacher te = (Teacher) teacherBox.getSelectedItem();
            sql.writeStatement("INSERT INTO section(course_id,teacher_id) VALUES("+co.getId()+","+te.getId()+");");
            sectionView2();
        }

    }
    public void sectDelete(){
        sql.writeStatement("DELETE FROM section WHERE section_id="+id.getText()+";");
        sectionView2();
    }
    public void sectNew(){
        sectSave.setVisible(true);
        id.setText("AUTO-GENERATED");
        sectDelete.setVisible(false);
        sectChanges.setVisible(false);
        teacherBox.setSelectedItem(null);
        sectionView2();
    }
    public void sectChanges(){
        Course co = (Course) courseBox.getSelectedItem();
        Teacher te = (Teacher) teacherBox.getSelectedItem();
        sql.writeStatement("UPDATE section SET course_id='"+co.getId()+"' WHERE section_id="+id.getText()+";");
        sql.writeStatement("UPDATE section SET teacher_id='"+te.getId()+"' WHERE section_id="+id.getText()+";");
        sectionView2();
        teacherBox.setSelectedItem(null);
    }
    public void selectedSection(){
        if(sectionsViewList.getSelectedValue()!=null){
            teacherBox.setSelectedItem(null);
            sectChanges.setVisible(true);
            sectDelete.setVisible(true);
            sectSave.setVisible(false);
            Section curr = (Section) sectionsViewList.getSelectedValue();
            id.setText(curr.getId()+"");
            teacherBox.removeAllItems();
            ArrayList<Teacher> sectTeach = sql.getTeacherList();
            int i = 0;
            for(int x = 0;x<sectTeach.size();x++){
                if(sectTeach.get(x).getId()==curr.getTeacher_id()){
                    i=x;
                }
                teacherBox.addItem(sectTeach.get(x));
            }
            teacherBox.setSelectedItem(sectTeach.get(i));

            studentSorter(curr);
        }

    }

    public void moveStudentLeft(){
        if(studentsInSecList.getSelectedValue()!=null){
            Section currSect = (Section) sectionsViewList.getSelectedValue();
            sql.writeStatement("DELETE FROM enrollment WHERE section_id="+currSect.getId()+";");
            studentSorter((Section) sectionsViewList.getSelectedValue());
        }
    }
    public void moveStudentRight(){
        if(studentsNotInSecList.getSelectedValue()!=null)
        {
            Student curr = (Student) studentsNotInSecList.getSelectedValue();
            Section currSect = (Section) sectionsViewList.getSelectedValue();
            sql.writeStatement("INSERT INTO enrollment(student_id,section_id) VALUES("+curr.getId()+", "+currSect.getId()+");");
            studentSorter((Section) sectionsViewList.getSelectedValue());
        }
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
            studentView();
        }
        else if(cview.equals("courses")){
            Course curr = (Course) courseViewList.getSelectedValue();
            curr.setCn(cn.getText());
            if(courseTypeACA.isSelected()){
                curr.setType(0);
            }
            else if(courseTypeKAP.isSelected()){
                curr.setType(1);
            }
            else if(courseTypeAP.isSelected()){
                curr.setType(2);
            }
            sql.writeStatement("UPDATE courses SET name='"+curr.getCn()+"' WHERE course_id="+curr.getId()+";");
            sql.writeStatement("UPDATE courses SET type='"+curr.getType()+"' WHERE course_id="+curr.getId()+";");
            courseView();


        }
    }
    public void newEntry(){
        id.setText("");
        fn.setText("");
        ln.setText("");
        cn.setText("");
        courseTypeACA.setSelected(false);
        courseTypeKAP.setSelected(false);
        courseTypeAP.setSelected(false);
        newEntry.setVisible(true);
        saveEntry.setVisible(true);
        deleteEntry.setVisible(false);
        saveChanges.setVisible(false);
        taughtViewList.setListData(new ArrayList<>().toArray());

    }
    public void saveEntry(){
        if(cview.equals("teachers")){
            if(!fn.getText().equals("")&&!ln.getText().equals("")) {
                sql.writeStatement("INSERT INTO teachers(first_name, last_name) VALUES('" + fn.getText() + "','" + ln.getText() + "');");
                teacherView();
            }
        }
        else if(cview.equals("students")){
            if(!fn.getText().equals("")&&!ln.getText().equals("")){
                sql.writeStatement("INSERT INTO students(first_name, last_name) VALUES('"+fn.getText()+"','"+ln.getText()+"');");
                studentView();
            }

        }
        else if(cview.equals("courses")){
            int i = -1;
            if(courseTypeACA.isSelected()==true){
                i=0;
            }
            if(courseTypeKAP.isSelected()==true){
                i=1;
            }
            if(courseTypeAP.isSelected()==true){
                i=2;
            }
            sql.writeStatement("INSERT INTO courses(name,type) VALUES('"+cn.getText()+"','"+i+"');");
            courseView();
        }

    }
    public void deleteEntry(){
        if(cview.equals("teachers")){
            ArrayList<Section> staught = sql.getSectionsTaught((Teacher) teacherViewList.getSelectedValue());
            for(int i = 0;i<staught.size();i++){
                sql.writeStatement("UPDATE section SET teacher_id=-1 WHERE section_id="+staught.get(i).getId()+";");
            }
            sql.writeStatement("DELETE FROM teachers WHERE teacher_id="+id.getText());
            teacherView();
        }
        else if(cview.equals("students")){
            sql.writeStatement("DELETE FROM students WHERE student_id="+id.getText());
            studentView();
        }
        else if(cview.equals("courses")){
            sql.writeStatement("DELETE FROM courses WHERE course_id="+id.getText());
            courseView();
        }
    }
    public void courseTypeButtons(String select){
        if(select.equals("ACA")){
            courseTypeKAP.setSelected(false);
            courseTypeAP.setSelected(false);
        }
        else if(select.equals("KAP")){
            courseTypeACA.setSelected(false);
            courseTypeAP.setSelected(false);
        }
        else if(select.equals("AP")){
            courseTypeACA.setSelected(false);
            courseTypeKAP.setSelected(false);
        }
    }

    public void about(){
        setAllVisibilityFalse();
        abtText.setVisible(true);
    }

    public void importAction(){
        sql.writeStatement("DROP TABLE IF EXISTS enrollment");
        sql.writeStatement("DROP TABLE IF EXISTS section;");
        sql.writeStatement("DROP TABLE IF EXISTS students;");
        sql.writeStatement("DROP TABLE IF EXISTS teachers;");
        sql.writeStatement("DROP TABLE IF EXISTS courses;");

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
        sql.writeStatement("CREATE TABLE IF NOT EXISTS enrollment(section_id INTEGER NOT NULL, " +
                "student_id INTEGER NOT NULL, " +
                "PRIMARY KEY(section_id,student_id), " +
                "FOREIGN KEY(section_id) REFERENCES section(section_id) ON UPDATE CASCADE ON DELETE CASCADE," +
                "FOREIGN KEY(student_id) REFERENCES students(student_id) ON UPDATE CASCADE ON DELETE CASCADE" +
                ");");

        JFileChooser fileChooser = new JFileChooser();
        int res = fileChooser.showOpenDialog(null);
        if(res==JFileChooser.APPROVE_OPTION){
            File f = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try{
                Scanner egg = new Scanner(f);
                egg.nextLine();
                while(egg.hasNextLine()){
                    String line = egg.nextLine();
                    if(line.contains("Teachers")){
                        break;
                    }
                    String[] array = line.split(", ");
                    sql.writeStatement("INSERT INTO students(student_id,first_name,last_name) values ("+array[0]+",'"+array[1]+"','"+array[2]+"');");
                }
                while(egg.hasNextLine()){
                    String line = egg.nextLine();
                    if(line.contains("Courses")){
                        break;
                    }
                    String[] array = line.split(", ");
                    sql.writeStatement("INSERT INTO teachers(teacher_id,first_name,last_name) values ("+array[0]+",'"+array[1]+"','"+array[2]+"');");
                }
                while(egg.hasNextLine()){
                    String line = egg.nextLine();
                    if(line.contains("Section")){
                        break;
                    }
                    String[] array = line.split(", ");
                    System.out.println(array.length);
                    sql.writeStatement("INSERT INTO courses(course_id,name,type) values ("+array[0]+",'"+array[1]+"',"+array[2]+");");
                }
                while(egg.hasNextLine()){
                    String line = egg.nextLine();
                    if(line.contains("Enrollment")){
                        break;
                    }
                    String[] array = line.split(", ");
                    sql.writeStatement("INSERT INTO section(section_id,course_id,teacher_id) values ("+array[0]+","+array[1]+","+array[2]+");");
                }
                while(egg.hasNextLine()){
                    String line = egg.nextLine();
                    String[] array = line.split(", ");
                    sql.writeStatement("INSERT INTO enrollment(section_id,student_id) values ("+array[0]+","+array[1]+");");
                }
                if(cview.equals("teachers")){
                    teacherView();
                }
                else if(cview.equals("students")){
                    studentView();
                }
                else if(cview.equals("courses")){
                    courseView();
                }
                else if(cview.equals("sections")){
                    sectionView();
                }


            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void exportAction(){
        PrintWriter out = null;
        try{
            out = new PrintWriter("output.txt");
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        try{
            ResultSet students = sql.snQueryEx("students");
            out.println("Students");
            while(students!=null&&students.next()){
                out.println(students.getInt("student_id")+", "+students.getString("first_name")+", "+students.getString("last_name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            ResultSet teachers = sql.snQueryEx("teachers");
            out.println("Teachers");
            while(teachers!=null&&teachers.next()){
                out.println(teachers.getInt("teacher_id")+", "+teachers.getString("first_name")+", "+teachers.getString("last_name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            ResultSet course = sql.snQueryEx("courses");
            out.println("Courses");
            while(course!=null&&course.next()){
                out.println(course.getInt("course_id")+", "+course.getString("name")+", "+course.getInt("type"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            ResultSet course = sql.snQueryEx("section");
            out.println("Section");
            while(course!=null&&course.next()){
                out.println(course.getInt("section_id")+", "+course.getString("course_id")+", "+course.getInt("teacher_id"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            ResultSet enrollment = sql.snQueryEx("enrollment");
            out.println("Enrollment");
            while(enrollment!=null&&enrollment.next()){
                out.println(enrollment.getInt("section_id")+", "+enrollment.getInt("student_id"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        out.close();
    }

    public void purge(){
        try{
            sql.writeStatement("DROP TABLE IF EXISTS enrollment");
            sql.writeStatement("DROP TABLE IF EXISTS section;");
            sql.writeStatement("DROP TABLE IF EXISTS students;");
            sql.writeStatement("DROP TABLE IF EXISTS teachers;");
            sql.writeStatement("DROP TABLE IF EXISTS courses;");
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
            sql.writeStatement("CREATE TABLE IF NOT EXISTS enrollment(section_id INTEGER NOT NULL, " +
                    "student_id INTEGER NOT NULL, " +
                    "PRIMARY KEY(section_id,student_id), " +
                    "FOREIGN KEY(section_id) REFERENCES section(section_id) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY(student_id) REFERENCES students(student_id) ON UPDATE CASCADE ON DELETE CASCADE" +
                    ");");

        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(cview.equals("teachers")){
            teacherView();
        }
        else if(cview.equals("students")){
            studentView();
        }
        else if(cview.equals("courses")){
            courseView();
        }
        else if(cview.equals("sections")){
            sectionView();
        }
    }

    public void exit(){
        dispose();
    }
}
