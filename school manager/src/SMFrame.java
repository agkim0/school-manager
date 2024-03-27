import javax.swing.*;

public class SMFrame extends JFrame {
    JMenuBar teach = new JMenuBar();
    JMenu teacherMenu = new JMenu("View");

    public SMFrame(){
        super("School Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700);
        setResizable(false);
        setAlwaysOnTop(true);
        setVisible(true);

        add(teach);
        add(teacherMenu);
        setJMenuBar(teach);
        teach.add(teacherMenu);
        teach.setBounds(10,10,100,100);


    }
}
