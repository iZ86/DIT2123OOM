public class KioskCheckInModel {
    /**The number of passengers to check in */
    private int numberOfPassengers; 
    /** Array of passengers, used to keep track of different passengers data */
    private Passenger[] passengers;
    /** Index of the passenger whose data should be accessed,
     * Mostly for getter and setter method use.
     */
    private int passengerIndex;
    /** Index of bag in the Passenger whose data should be accessed,
     * Mostly for getter and setter method use.
     */
    private int bagIndex;
    /** Index for keeping track of the amoutn of passengers that was inserted. */
    private int addPassengerIndex;

    public KioskCheckInModel() {
        // An edge case fix for now.
        passengers = new Passenger[]{new Passenger(null, null, null, new Bag[]{new Bag(null, null, 0)})};
        passengerIndex = 0;
        bagIndex = 0;
    }
   
    /** Sets the number of passengers to be checked in. */
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        passengers = new Passenger[numberOfPassengers];
        addPassengerIndex = 0;
    }

    /** Returns number of passengers. */
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /** Inserts the passenger data's that needs to be stored. */
    public void insertPassenger(Passenger passenger) {
        passengers[addPassengerIndex] = passenger;
        addPassengerIndex += 1;
    }

    /** Returns the passengerIndex. */
    public int getPassengerIndex() {
        return passengerIndex;
    }

    /** Sets the passengerIndex. */
    public void setPassengerIndex(int passengerIndex) {
        this.passengerIndex = passengerIndex;
    }

    /** Returns the bagIndex. */
    public int getBagIndex() {
        return bagIndex;
    }

    /** Sets the bagIndex. */
    public void setBagIndex(int bagIndex) {
        this.bagIndex = bagIndex;
    }

    /** Returns the bookingID of the passenger in Passenger[] passengers at passengerIndex. */
    public String getBookingNumber() {
        return passengers[passengerIndex].getBookingID();
    }

    /** Returns the passport number of the passenger in Passenger[] passengers at passengerIndex. */
    public String getPassportNumber() {
        return passengers[passengerIndex].getPassportNumber();
    }

    /** Returns the full name of the passenger in Passenger[] passengers at passengerIndex. */
    public String getFullName() {
        return passengers[passengerIndex].getFullName();
    }

    /** Returns the bagID at bagIndex of the passenger in Passenger[] passengers at passengerIndex. */
    public String getBagID() {
        return passengers[passengerIndex].getBag(bagIndex).getBagID();
    }

    /** Returns the bag color at bagIndex of the passenger in Passenger[] passengers at passengerIndex. */
    public String getBagColor() {
        return passengers[passengerIndex].getBag(bagIndex).getBagColor();
    }

    /** Returns the bag weight at bagIndex of the passenger in Passenger[] passengers at passengerIndex. */
    public double getBagWeight() {
        return passengers[passengerIndex].getBag(bagIndex).getBagWeight();
    }

}
