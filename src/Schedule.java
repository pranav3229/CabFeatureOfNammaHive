import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Schedule {
    Schedule() {
        ConnectionClass obj = new ConnectionClass();
        ConnectionClass obj2 = new ConnectionClass();
        ConnectionClass obj3 = new ConnectionClass();
        ConnectionClass obj4 = new ConnectionClass();
        ConnectionClass obj5 = new ConnectionClass();

        String query = "SELECT * FROM rides;";
        try {
            ResultSet rs = obj.stm.executeQuery(query);
            while (rs.next()) {
                query = "SELECT * from rides where source = '" + rs.getString("source") + "' and destination = '"
                        + rs.getString("destination") + "' and date = '" + rs.getString("date") + "';";
                ResultSet rs2 = obj2.stm.executeQuery(query);
                // group the rides which have less than 30 minutes difference in time together
                // max 4 people in a group
                // put the grouped rides in the propsed table
                HashMap<String, Integer> hm = new HashMap<String, Integer>();
                ArrayList<String> al = new ArrayList<String>();
                while (rs2.next()) {
                    hm.put(rs2.getString("BITSID"), Integer.parseInt(rs2.getString("time").substring(0, 2)) * 60
                            + Integer.parseInt(rs2.getString("time").substring(2, 4)));
                }
                int count = 0;
                for (Map.Entry<String, Integer> entry : hm.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (count == 4) {
                        break;
                    }
                    if (Math.abs(Integer.parseInt(rs.getString("time").substring(0, 2)) * 60
                            + Integer.parseInt(rs.getString("time").substring(2, 4)) - value) <= 30) {
                        // add to proposed
                        al.add(key);
                        count++;
                    }
                }

                String members = "";
                for (String s : al) {
                    members += s + "W ";
                }

                query = "INSERT INTO proposed VALUES ('" + rs.getString("source") + "', '" + rs.getString("destination")
                        + "', '" + rs.getString("date") + "', '" + rs.getString("time") + "', '"
                        + (int) (Math.random() * 100000) + "', '" + members + "', "
                        + "1000" + ", " + "'Proposed');";
                obj3.stm.executeUpdate(query);

                for (String s : al) {
                    query = "DELETE FROM rides WHERE BITSID = '" + s + "' and source = '" + rs.getString("source")
                            + "' and destination = '" + rs.getString("destination") + "' and date = '"
                            + rs.getString("date") + "';";
                    obj4.stm.executeUpdate(query);
                }
            }
            query = "DELETE FROM proposed WHERE MemberID='';";
            obj5.stm.executeUpdate(query);
            // Successful message
            JOptionPane.showMessageDialog(null, "Success");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
