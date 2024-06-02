public class Staff {
    private String staffID;
    private String staffName;
    private String staffRole;
    private int counter;

    public Staff(String staffID, String staffName, String staffRole, int counter) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffRole = staffRole;
        this.counter = counter;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public int getCounter() {
        return counter;
    }

}
