
/** This class is the model for the CounterView class. */
public class CounterModel {

    /** The designated counter number for the user. */
    private int counterNumber;
    /** The name of staff at the designated counter. */
    private String staffName;
    /** The role of the staff at the designated counter. */
    private String staffRole;
    /** The age of the staff at the designated counter. */
    private int staffAge;
    /** Utils for getting random information. */
    private Utils utils;

    public CounterModel() {
        utils = new Utils();
    }

    /** Returns counterNumber. */
    public int getCounterNumber() {
        return counterNumber;
    }

    /** Returns staffName. */
    public String getStaffName() {
        return staffName;
    }

    /** Returns staffRole. */
    public String getStaffRole() {
        return staffRole;
    }

    /** Returns staffAge. */
    public int getStaffAge() {
        return staffAge;
    }

}