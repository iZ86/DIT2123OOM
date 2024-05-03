public class KioskCheckInModel {
    /**The number of passengers to check in */
    private int numberOfPassengers; 
    /**Array of passengers, used to keep track of different passengers data */
    private Passenger[] passenger; 
   
    /**Sets the number of passengers to be checked in */
    public void setNumberOfPassenger(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        this.passenger = new Passenger[numberOfPassengers];
    }

    /**Creates a passenger object that holds the data to be stored */
    public void createPassenger(String bookingID, int passportNumber, String fullName, Bag[] bag) {

    }

    /**Returns the bookingID of the passenger in Passenger[] passengers at passengerIndex */
    public String getBookingID(int passengerIndex) {
        return passenger[passengerIndex].getBookingID();
    }

    /**Returns the passport number of the passenger in Passenger[] passengers at passengerIndex */
    public int getPassportNumber(int passengerIndex) {
        return this.passenger[passengerIndex].getPassportNumber();
    }

    /**Returns the full name of the passenger in Passenger[] passengers at passengerIndex */
    public String getFullName(int passengerIndex) {
        return this.passenger[passengerIndex].getFullName();
    }

    /**Returns the bagID at bagIndex of the passenger in Passenger[] passengers at passengerIndex */
    public Bag getBagID(int passengerIndex, int bagIndex) {
        return null;
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