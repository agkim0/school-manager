import javax.swing.*;

public class SMFrame extends JFrame {
    JMenuBar mb = new JMenuBar();

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




    public SMFrame(){
        super("School Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700);
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

//        setJMenuBar(teach);
//        teach.add(teacherMenu);
////        teach.setBounds(50,10,100,10);
//        add(teach);
//        teacherMenu.add(item);


    }
}
