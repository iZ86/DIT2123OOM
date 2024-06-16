public class Passenger {
    /** Passport number of the passenger. */
    private String passportNumber;
    /** bookingNumber of the passenger. */
    private String bookingNumber;
    /** Full name of the passenger. */
    private String fullName; 
    /** Bags of the passenger. */
    private Bag[] bags;
    /** Special needs status of the passenger. */
    private boolean specialNeed;
    /** Wheelchair status of the passenger. */
    private boolean requireWheelchair;
    /** Blindness status of the passenger. */
    private boolean blind;
    /** Deafness status of the passenger. */
    private boolean deaf;
    /** Other Special Accommodation status of the passenger. */
    private boolean otherSpecialAccommodation;
    /** Special Accommodation details of the passenger. */
    private String otherSpecialAccommodationDetails;

    /** Creates a Passenger object to hold data. */
    public Passenger(String bookingNumber, String passportNumber, String fullName) {
        this.bookingNumber = bookingNumber;
        this.passportNumber = passportNumber;
        this.fullName = fullName;
        setBagArray(0);
    }

    /** Returns the number of bags the passenger has. */
    public int getNumberOfBags() {
        return bags.length;
    }

    /** Sets the bags. */
    public void setBagArray(int numberOfBags) {
        bags = new Bag[numberOfBags];
    }

    /** Returns the bookingNumber. */
    public String getBookingNumber() {
        return bookingNumber;
    }

    /** Returns the passport number. */
    public String getPassportNumber() {
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

    /** Returns requireWheelchair. */
    public boolean isRequireWheelchair() {
        return requireWheelchair;
    }

    /** Sets requireWheelchair. */
    public void setRequireWheelchair(boolean requireWheelchair) {
        this.requireWheelchair = requireWheelchair;
    }

    /** Returns blind. */
    public boolean isBlind() {
        return blind;
    }

    /** Sets blind. */
    public void setBlind(boolean blind) {
        this.blind = blind;
    }

    /** Returns deaf. */
    public boolean isDeaf() {
        return deaf;
    }

    /** Sets deaf. */
    public void setDeaf(boolean deaf) {
        this.deaf = deaf;
    }

    /** Returns otherSpecialAccommodation. */
    public boolean isOtherSpecialAccommodation() {
        return otherSpecialAccommodation;
    }

    /** Sets otherSpecialAccommodation. */
    public void setOtherSpecialAccommodation(boolean otherSpecialAccommodation) {
        this.otherSpecialAccommodation = otherSpecialAccommodation;
    }

    /** Return otherSpecialAccommodationDetails. */
    public String getOtherSpecialAccommodationDetails() {
        return otherSpecialAccommodationDetails;
    }

    /** Sets otherSpecialAccommodationDetails. */
    public void setOtherSpecialAccommodationDetails(String otherSpecialAccommodationDetails) {
        this.otherSpecialAccommodationDetails = otherSpecialAccommodationDetails;
    }

}
