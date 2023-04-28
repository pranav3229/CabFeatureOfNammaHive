import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Admin implements ActionListener, Runnable {
    JFrame f;
    JButton b1, b2, b3, b4;

    Admin() {
        f = new JFrame("Admin");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        b2 = new JButton("Show Details");
        b2.setBounds(50, 50, 140, 120);
        f.add(b2);

        b3 = new JButton("Schedule");
        b3.setBounds(200, 50, 140, 120);
        f.add(b3);

        b4 = new JButton("Show Billings");
        b4.setBounds(50, 180, 140, 120);
        f.add(b4);

        b1 = new JButton("Log Out");
        b1.setBounds(200, 180, 140, 120);
        f.add(b1);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        f.getContentPane();
        f.setVisible(true);
        f.setSize(390, 350);
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == b1) {
            f.setVisible(false);
            new App();
        } else if (ee.getSource() == b2) {
            try {
                new Details();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ee.getSource() == b3) {
            new Schedule();
        } else if (ee.getSource() == b4) {
            try {
                new Charges();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
}
