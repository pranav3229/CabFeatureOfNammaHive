import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class App extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1;
    JButton b1, b2;

    App() {
        f = new JFrame("BITS Cab Service");
        f.setLayout(null);

        b1 = new JButton("Admin");
        b1.setBounds(40, 50, 120, 30);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Student");
        b2.setBounds(200, 50, 120, 30);
        b2.addActionListener(this);
        f.add(b2);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(350, 150);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            f.setVisible(false);
            new AdminLogin();
        } else if (ae.getSource() == b2) {
            f.setVisible(false);
            new UserLogin();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
}
