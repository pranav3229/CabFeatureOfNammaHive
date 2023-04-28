import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Ride implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4, l5;
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2;
    UserData usr;

    Ride(UserData usr) {
        this.usr = usr;
        f = new JFrame("Request Ride");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel("BITS ID");
        l1.setBounds(40, 20, 100, 30);
        f.add(l1);

        l2 = new JLabel("Date (MMDDYYYY)");
        l2.setBounds(40, 70, 200, 30);
        f.add(l2);

        l3 = new JLabel("Source");
        l3.setBounds(40, 120, 100, 30);
        f.add(l3);

        l4 = new JLabel("Destination");
        l4.setBounds(40, 170, 100, 30);
        f.add(l4);

        l5 = new JLabel("Time (HHMM)");
        l5.setBounds(40, 220, 100, 30);
        f.add(l5);

        t1 = new JTextField();
        t1.setText(usr.getBITSID());
        t1.setEditable(false);
        t1.setBounds(200, 20, 150, 30);
        f.add(t1);

        t2 = new JTextField();
        t2.setBounds(200, 70, 150, 30);
        f.add(t2);

        t3 = new JTextField();
        t3.setBounds(200, 120, 150, 30);
        f.add(t3);

        t4 = new JTextField();
        t4.setBounds(200, 170, 150, 30);
        f.add(t4);

        t5 = new JTextField();
        t5.setBounds(200, 220, 150, 30);
        f.add(t5);

        b1 = new JButton("Back");
        b1.setBounds(40, 270, 120, 30);
        f.add(b1);

        b2 = new JButton("Request");
        b2.setBounds(200, 270, 120, 30);
        f.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(400, 350);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            this.f.setVisible(false);
            new Student(this.usr);
        } else if (e.getSource() == b2) {
            String bitsId = t1.getText();
            String date = t2.getText();
            String source = t3.getText();
            String destination = t4.getText();
            String time = t5.getText();

            try {
                if (bitsId.equals("") || date.equals("") || source.equals("") || destination.equals("")
                        || time.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                } else {
                    ConnectionClass c1 = new ConnectionClass();
                    String q1 = "insert into rides values('" + bitsId + "','" + date + "','" + source + "','"
                            + destination + "','" + time + "')";
                    c1.stm.executeUpdate(q1);
                    JOptionPane.showMessageDialog(null, "Ride Requested");
                    f.setVisible(false);
                    new Student(this.usr);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
