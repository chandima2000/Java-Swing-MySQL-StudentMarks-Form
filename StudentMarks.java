import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentMarks extends JFrame {

    private final Container container;
    private final JLabel title;
    private final JLabel studentID;
    private final JTextField tstudentID;
    private final JLabel sName;
    private final JTextField tsName;
    private final JLabel ca;
    private final JTextField tca;
    private final JLabel practical;
    private final JTextField tpractical;
    private final JLabel theory;
    private final JTextField ttheory;
    private final JLabel gender;
    private final JRadioButton male;
    private final JRadioButton female;
    private final ButtonGroup genGrp;
    private final JButton save;

    Connection connection;
    PreparedStatement pst;

    private static final String dburl = "jdbc:mysql://localhost:3406/oop";
    private static final String username = "root";
    private static final String password = "";
    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dburl,username,password);
            System.out.println("Connection is Established");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public StudentMarks(){

        connect();

        setTitle("Student Marks");
        setBounds(100,50,900,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        //Add Title
        title = new JLabel("Enter Student OOP Marks");
        title.setSize(500,30);
        title.setLocation(250,100);
        title.setFont(new Font("Arial",Font.PLAIN,30));
        container.add(title);


        //Student ID

        studentID = new JLabel("Student ID");
        studentID.setSize(500,30);
        studentID.setLocation(150,250);
        studentID.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(studentID);

        tstudentID = new JTextField();
        tstudentID.setSize(300,30);
        tstudentID.setLocation(300,250);
        tstudentID.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(tstudentID);


        //Student Name

        sName = new JLabel("Student Name");
        sName.setSize(500,30);
        sName.setLocation(150,300);
        sName.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(sName);

        tsName = new JTextField();
        tsName.setSize(300,30);
        tsName.setLocation(300,300);
        tsName.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(tsName);

        //CA Marks

        ca = new JLabel("CA");
        ca.setSize(500,30);
        ca.setLocation(150,350);
        ca.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(ca);

        tca = new JTextField();
        tca.setSize(300,30);
        tca.setLocation(300,350);
        tca.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(tca);


        //Practical

        practical = new JLabel("Practical");
        practical.setSize(500,30);
        practical.setLocation(150,400);
        practical.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(practical);

        tpractical = new JTextField();
        tpractical.setSize(300,30);
        tpractical.setLocation(300,400);
        tpractical.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(tpractical);

        //Theory
        theory = new JLabel("Theory");
        theory.setSize(500,30);
        theory.setLocation(150,450);
        theory.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(theory);

        ttheory = new JTextField();
        ttheory.setSize(300,30);
        ttheory.setLocation(300,450);
        ttheory.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(ttheory);

        //Gender
        gender = new JLabel("Gender");
        gender.setSize(500,30);
        gender.setLocation(150,500);
        gender.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(gender);

        male = new JRadioButton("Male");
        male.setSize(150,50);
        male.setLocation(300,500);
        container.add(male);

        female = new JRadioButton("Female");
        female.setSize(150,50);
        female.setLocation(450,500);
        container.add(female);

        genGrp = new ButtonGroup();
        genGrp.add(male);
        genGrp.add(female);


        save = new JButton("Submit");
        save.setSize(150,50);
        save.setLocation(350,600);
        save.setFont(new Font("Arial",Font.PLAIN,25));
        container.add(save);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String S_ID,S_Name,CA,Practical,Theory,Gender;

                S_ID = tstudentID.getText();
                S_Name = tsName.getText();
                CA = tca.getText();
                Practical = tpractical.getText();
                Theory = ttheory.getText();
                if(male.isSelected()){
                    Gender="Male";
                }else{
                    Gender="Female";
                }

                try{
                    pst = connection.prepareStatement("insert into marks(S_ID,S_Name,CA,Practical,Theory,Gender)values(?,?,?,?,?,?)");
                    pst.setString(1,S_ID);
                    pst.setString(2,S_Name);
                    pst.setString(3,CA);
                    pst.setString(4,Practical);
                    pst.setString(5,Theory);
                    pst.setString(6,Gender);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data is Inserted");

                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args){
        StudentMarks sm = new StudentMarks();
        sm.setVisible(true);
    }



}
