import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Proposed implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13;
    JLabel la, lb, lc, ld;
    JButton b1, b2, b3;
    UserData usr;

    Proposed(UserData usr) throws SQLException {
        this.usr = usr;
        ConnectionClass obj = new ConnectionClass();
        String query = "SELECT * FROM proposed where MemberID like '%" + usr.getBITSID() + "%';";
        ResultSet rs = obj.stm.executeQuery(query);
        rs.next();

        f = new JFrame("Rides");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel("From");
        l1.setBounds(20, 20, 500, 50);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(l1);

        l2 = new JLabel("To");
        l2.setBounds(140, 20, 500, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(l2);

        l3 = new JLabel("Date");
        l3.setBounds(260, 20, 500, 50);
        l3.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(l3);

        l4 = new JLabel("Time");
        l4.setBounds(380, 20, 500, 50);
        l4.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(l4);

        l5 = new JLabel("Members");
        l5.setBounds(500, 20, 500, 50);
        l5.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(l5);

        l6 = new JLabel("Total Cost");
        l6.setBounds(740, 20, 500, 50);
        l6.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(l6);

        l7 = new JLabel(rs.getString("Source"));
        l7.setBounds(20, 60, 500, 50);
        l7.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        f.add(l7);

        l8 = new JLabel(rs.getString("Destination"));
        l8.setBounds(140, 60, 500, 50);
        l8.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        f.add(l8);

        l9 = new JLabel(rs.getString("Date"));
        l9.setBounds(260, 60, 500, 50);
        l9.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        f.add(l9);

        l10 = new JLabel(rs.getString("Time"));
        l10.setBounds(380, 60, 500, 50);
        l10.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        f.add(l10);

        String mem = rs.getString("MemberID");
        mem += "a a a";
        String[] mems = mem.split(" ");
        System.out.println(Arrays.toString(mems));
        la = new JLabel(mems[0].substring(0, mems[0].length() - 1));
        la.setBounds(500, 60, 500, 50);
        la.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        f.add(la);

        lb = new JLabel(mems[1].substring(0, mems[1].length() - 1));
        lb.setBounds(500, 90, 500, 50);
        lb.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        f.add(lb);

        lc = new JLabel(mems[2].substring(0, mems[2].length() - 1));
        lc.setBounds(500, 120, 500, 50);
        lc.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        f.add(lc);

        ld = new JLabel(mems[3].substring(0, mems[3].length() - 1));
        ld.setBounds(500, 150, 500, 50);
        ld.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        f.add(ld);

        l12 = new JLabel(rs.getString("Cost"));
        l12.setBounds(740, 60, 500, 50);
        l12.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        f.add(l12);

        l13 = new JLabel("Accept Ride?");
        l13.setBounds(50, 200, 500, 50);
        l13.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 30));
        f.add(l13);

        b1 = new JButton("Yes");
        b1.setBounds(100, 250, 120, 30);
        f.add(b1);

        b2 = new JButton("No");
        b2.setBounds(250, 250, 120, 30);
        f.add(b2);

        b3 = new JButton("Back");
        b3.setBounds(400, 250, 120, 30);
        f.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(1000, 400);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "SELECT * FROM proposed where MemberID like '%" + usr.getBITSID() + "%';";
                ResultSet rs = obj.stm.executeQuery(query);
                rs.next();
                String[] members = rs.getString("MemberID").split(" ");
                int count = 0;
                for (int i = 0; i < members.length; i++) {
                    if (members[i].substring(0, members[i].length() - 1).equals(usr.getBITSID())) {
                        members[i] = usr.getBITSID() + "A";
                    }
                    if (members[i].charAt(members[i].length() - 1) == 'A') {
                        count++;
                    }
                }

                if (count == members.length) {
                    query = "UPDATE proposed SET Status = 'Scheduled' WHERE MemberID like '%" + usr.getBITSID() + "%';";
                    obj.stm.executeUpdate(query);
                }

                String newMembers = "";
                for (int i = 0; i < members.length; i++) {
                    newMembers += members[i] + " ";
                }
                query = "UPDATE proposed SET MemberID = '" + newMembers + "' WHERE MemberID like '%" + usr.getBITSID()
                        + "%';";
                int aa = obj.stm.executeUpdate(query);
                query = "SELECT * FROM proposed where MemberID like '%" + usr.getBITSID() + "%';";
                rs = obj.stm.executeQuery(query);
                rs.next();

                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Ride Confirmed for you. Waiting for other members.");
                    f.setVisible(false);
                    new Student(this.usr);
                } else {
                    JOptionPane.showMessageDialog(null, "Error.");
                    this.f.setVisible(false);
                    this.f.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "Delete from proposed WHERE MemberID like '%" + usr.getBITSID() + "%';";
                int aa = obj.stm.executeUpdate(query);
                f.setVisible(false);

                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Ride rejected.");
                    f.setVisible(false);
                    new Student(this.usr);
                } else {
                    JOptionPane.showMessageDialog(null, "Error.");
                    this.f.setVisible(false);
                    this.f.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b3) {
            f.setVisible(false);
            new Student(this.usr);
        }
    }
}
