import java.util.ArrayList;
import java.util.List;

public class UserData {
    private String BITSID = "";
    private String Name = "";
    private int PhoneNo = 0;

    public UserData(String Id) {
        this.BITSID = Id;
    }

    public String getBITSID() {
        return BITSID;
    }

    public String getName() {
        return Name;
    }

    public int getPhoneNo() {
        return PhoneNo;
    }
}