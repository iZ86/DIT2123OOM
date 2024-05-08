public class Passenger {
    /** Passport number of the passenger. */
    private int passportNumber; 
    /** bookingID of the passenger. */
    private String bookingID; 
    /** Full name of the passenger. */
    private String fullName; 
    /** Bags of the passenger. */
    private Bag[] bags;
    /** Special needs status of the passenger. */
    private boolean specialNeed;

    /** Creates a Passenger object to hold data. */
    public Passenger(String bookingID, int passportNumber, String fullName, Bag[] bags) {
        this.bookingID = bookingID;
        this.passportNumber = passportNumber;
        this.fullName = fullName;
        this.bags = bags;
    }

    /** Returns the bookingID. */
    public String getBookingID() {
        return bookingID;
    }

    /** Returns the passport number. */
    public int getPassportNumber() {
        return passportNumber;
    }

    /**Returns the full name */
    public String getFullName() {
        return fullName;
    }

    /** Returns the bag at int INDEX, returns null, if the bag index doesn't exist. */
    public Bag getBag(int index) {
        return bags[index];
    }

    /** Returns specialNeed. */
    public boolean isSpecialNeed() {
        return specialNeed;
    }

    /** Sets specialNeed. */
    public void setSpecialNeed(boolean specialNeed) {
        this.specialNeed = specialNeed;
    }
}
