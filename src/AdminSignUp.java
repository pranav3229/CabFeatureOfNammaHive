import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class AdminSignUp implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4;
    JTextField t1, t2, t3;
    JPasswordField p1;
    JButton b1, b2;

    AdminSignUp() {
        f = new JFrame("Create Admin Account");
        f.setBackground(Color.white);
        f.setLayout(null);

        l2 = new JLabel("Name");
        l2.setBounds(40, 20, 1000, 30);
        f.add(l2);

        l3 = new JLabel("Password");
        l3.setBounds(40, 70, 1000, 30);
        f.add(l3);

        t2 = new JTextField();
        t2.setBounds(150, 20, 150, 30);
        f.add(t2);

        p1 = new JPasswordField();
        p1.setBounds(150, 70, 150, 30);
        f.add(p1);

        b1 = new JButton("Back");
        b1.setBounds(40, 140, 120, 30);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Sign Up");
        b2.setBounds(200, 140, 120, 30);
        b2.addActionListener(this);
        f.add(b2);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(400, 240);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            String name = t2.getText();
            String password = p1.getText();

            try {
                if (name.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the required fields");
                } else {
                    ConnectionClass c1 = new ConnectionClass();
                    String q1 = "insert into admin values('" + name + "','" + password + "')";
                    int aa = c1.stm.executeUpdate(q1);

                    if (aa == 1) {
                        JOptionPane.showMessageDialog(null, "Account Created Successfully");
                        f.setVisible(false);
                        new AdminLogin();
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid details");
                        this.f.setVisible(false);
                        this.f.setVisible(true);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b1) {
            f.setVisible(false);
            new App();
        }
    }
}
