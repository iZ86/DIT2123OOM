public class KioskCheckInModel {
    /**The number of passengers to check in */
    private int numberOfPassengers; 
    /**Array of passengers, used to keep track of different passengers data */
    private Passenger[] passengers;
    /** Index of the passenger. */
    private int passengerIndex;

    public KioskCheckInModel() {
        passengerIndex = 0;
    }
   
    /**Sets the number of passengers to be checked in */
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        this.passengers = new Passenger[numberOfPassengers];
    }

    /** Inserts the passenger data's that needs to be stored. */
    public void insertPassenger(Passenger passenger) {

    }

    /**Returns the bookingID of the passenger in Passenger[] passengers at passengerIndex */
    public String getBookingID(int passengerIndex) {
        return passengers[passengerIndex].getBookingID();
    }

    /**Returns the passport number of the passenger in Passenger[] passengers at passengerIndex */
    public int getPassportNumber(int passengerIndex) {
        return this.passengers[passengerIndex].getPassportNumber();
    }

    /**Returns the full name of the passenger in Passenger[] passengers at passengerIndex */
    public String getFullName(int passengerIndex) {
        return this.passengers[passengerIndex].getFullName();
    }

    /**Returns the bagID at bagIndex of the passenger in Passenger[] passengers at passengerIndex */
    public Bag getBagID(int passengerIndex, int bagIndex) {
        return this.passengers[passengerIndex].getBag(bagIndex);
    }

    /**Returns the bag color at bagIndex of the passenger in Passenger[] passengers at passengerIndex */
    public String getBagColor(int passengerIndex, int bagIndex) {
        return null;
    }

    /**Returns the bag weight at bagIndex of the passenger in Passenger[] passengers at passengerIndex */
    public double getBagWeight(int passengerIndex, int bagIndex) {
        return 0;
    }

}
