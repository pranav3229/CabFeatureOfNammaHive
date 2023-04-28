import java.util.ArrayList;
import java.util.List;

public class UserData {
    private String CustomerID = "";
    private String Name = "";
    private int PhoneNo = 0;

    public UserData(String Id) {
        this.CustomerID = Id;
    }

    public String getBITSID() {
        return CustomerID;
    }

    public String getName() {
        return Name;
    }

    public int getPhoneNo() {
        return PhoneNo;
    }
}