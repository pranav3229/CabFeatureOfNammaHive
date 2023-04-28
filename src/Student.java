import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Student implements ActionListener, Runnable {
    JFrame f;
    JButton b1, b2, b3, b4;
    UserData usr;

    Student(UserData usr) {
        this.usr = usr;
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == b1) {
            f.setVisible(false);
            new UserLogin();
        } else if (ee.getSource() == b2) {
            new Ride(this.usr);
        } else if (ee.getSource() == b3) {
            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "SELECT * FROM proposed where MemberID like '%" + usr.getBITSID() + "%';";
                ResultSet rs = obj.stm.executeQuery(query);
                if (rs.next()) {
                    new Proposed(this.usr);
                } else {
                    JOptionPane.showMessageDialog(null, "No rides proposed yet");
                    new Student(this.usr);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        this.usr = usr;
        f = new JFrame("Student");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        b1 = new JButton("Log Out");
        b1.setBounds(125, 180, 140, 120);
        f.add(b1);

        b2 = new JButton("Add Ride");
        b2.setBounds(50, 50, 140, 120);
        f.add(b2);

        b3 = new JButton("Proposed Rides");
        b3.setBounds(200, 50, 140, 120);
        f.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        f.getContentPane();
        f.setVisible(true);
        f.setSize(390, 350);
    }
}
