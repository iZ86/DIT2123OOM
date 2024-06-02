
/** This class represents the data for the aerocheck in staff. */
public class Staff {
    /** Staff id. */
    private String staffID;
    /** Staff name. */
    private String staffName;
    /** Staff role. */
    private String staffRole;
    /** Counter. */
    private int counter;

    /** Creates a new Staff object for holding staff data. */
    public Staff(String staffID, String staffName, String staffRole, int counter) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffRole = staffRole;
        this.counter = counter;
    }

    /** Returns staffID. */
    public String getStaffID() {
        return staffID;
    }

    /** Returns staffName. */
    public String getStaffName() {
        return staffName;
    }

    /** Returns staffRole. */
    public String getStaffRole() {
        return staffRole;
    }

    /** Returns counter. */
    public int getCounter() {
        return counter;
    }

}
