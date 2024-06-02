
/** This class is the model for the CounterView class. */
public class CounterModel {

    /** Staff object that holds all the staff data. */
    private Staff staff;
    /** Utils for getting random information. */
    private Utils utils;

    public CounterModel() {
        utils = new Utils();
        // Initialization
        nextCounter();
    }

    /** Returns counterNumber. */
    public int getCounter() {
        return staff.getCounter();
    }

    /** Returns staffName. */
    public String getStaffName() {
        return staff.getStaffName();
    }

    /** Returns staffRole. */
    public String getStaffRole() {
        return staff.getStaffRole();
    }

    /** Returns staffID. */
    public String getStaffID() {
        return staff.getStaffID();
    }

    /** Gets a new Counter and sets its data in the model. */
    public void nextCounter() {
        staff = utils.getRandomStaff();
    }

}
