import java.util.ArrayList;
import java.util.HashMap;

public class KioskCheckInModel {
    /**The number of passengers to check in */
    private int numberOfPassengers; 
    /** Array of passengers, used to keep track of different passengers data */
    private Passenger[] passengers;
    /** Index of the passenger whose data should be accessed,
     * Mostly for getter and setter method use.
     */
    private int passengerIndex;
    /** Index for keeping track of the amoutn of passengers that was inserted. */
    private int addPassengerIndex;
    /** HashMap used to store bookingNumber and their respective bookingInformation,
     * for faster lookup speed.
     */
    private HashMap<String, BookingInformation> bookingInformationData;
    /** Utilities class. */
    private Utils utils = new Utils();
    /** Number of bags checked in. */
    private int numberOfBagsCheckedIn;
    /** Arraylist to hold the booking information that has been checked in. */
    public ArrayList<BookingInformation> checkedInBookings = new ArrayList<>();

    public KioskCheckInModel() {
        // An edge case fix for now.
        passengers = new Passenger[]{new Passenger(null, null, null)};
        passengerIndex = 0;
        numberOfBagsCheckedIn = 0;
        bookingInformationData = new HashMap<>();
        setupBookingInformationData();
    }

    /** Setups up the bookingInformationData. */
    private void setupBookingInformationData() {
        for (int i = 0; i < Utils.NUMBEROFDATA; i++) {
            bookingInformationData.put(Utils.BOOKINGNUMBERS[i], new BookingInformation(Utils.BOOKINGNUMBERS[i], Utils.PASSPORTNUMBERS[i], Utils.FULLNAME[i], Utils.SEATNUMBERS[i], Utils.DESTINATIONS[i], "Ready", utils.generateRandomGateNumber(), Utils.BOARDINGTIMES[i]));
        }
    }

    /** Used to validate bookingNumber entered by user,
     * returns true if it's a valid bookingNumber.
     * Otherwise, returns false. */
    public boolean validateBookingNumber(String bookingNumber) {
        return bookingInformationData.containsKey(bookingNumber);
    }

    /** Used to validate passportNumber entered by user,
     * returns true if it's the right passportNumber under the bookingNumber.
     * Otherwise, returns false. */
    public boolean validatePassportNumber(String bookingNumber, String passportNumber) {
        return bookingInformationData.get(bookingNumber).getPassportNumber().equals(passportNumber);
    }

    /** Used to validate fullName entered by user,
     * returns true if it's the right fullName under the bookingNumber.
     * Otherwise, returns false. */
    public boolean validateFullName(String bookingNumber, String fullName) {
        return bookingInformationData.get(bookingNumber).getFullName().equals(fullName);
    }

   
    /** Sets the number of passengers to be checked in. */
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        passengers = new Passenger[numberOfPassengers];
        addPassengerIndex = 0;
    }

    /** Generates bagID for tracking purposes. */
    private String generateBagID() {
        String numberOfBagsCheckedInString = String.valueOf(numberOfBagsCheckedIn);
        StringBuilder bagID = new StringBuilder("B");
        for (int i = numberOfBagsCheckedInString.length(); i < 4; i++) {
            bagID.append("0");
        }
        bagID.append(numberOfBagsCheckedInString);
        return bagID.toString();
    }

    /** Returns number of passengers. */
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /** Inserts the passenger data's that needs to be stored. */
    public void insertPassenger(Passenger passenger) {
        for (int i = 0; i < passenger.getNumberOfBags(); i++) {
            passenger.getBag(i).setBagID(generateBagID());
            passenger.getBag(i).setBagWeight(utils.getRandomBagWeight());
            passenger.getBag(i).setScreeningStatus("Incomplete");
        }
        passengers[addPassengerIndex] = passenger;
        addPassengerIndex += 1;
        checkedInBookings.add(bookingInformationData.get(passenger.getBookingNumber()));
    }

    /** Returns the passengerIndex. */
    public int getPassengerIndex() {
        return passengerIndex;
    }

    /** Sets the passengerIndex. */
    public void setPassengerIndex(int passengerIndex) {
        this.passengerIndex = passengerIndex;
    }

    /** Returns the bookingNumber of the passenger in Passenger[] passengers at passengerIndex. */
    public String getBookingNumber() {
        return passengers[passengerIndex].getBookingNumber();
    }

    /** Returns the passport number of the passenger in Passenger[] passengers at passengerIndex. */
    public String getPassportNumber() {
        return passengers[passengerIndex].getPassportNumber();
    }

    /** Returns the full name of the passenger in Passenger[] passengers at passengerIndex. */
    public String getFullName() {
        return passengers[passengerIndex].getFullName();
    }

    /** Returns the number of bags the passenger has in Passenger[] passenger at passengerIndex. */
    public int getNumberOfBags() {
        return passengers[passengerIndex].getNumberOfBags();
    }

    /** Returns the bagID at bagIndex of the passenger in Passenger[] passengers at passengerIndex. */
    public String getBagID(int bagIndex) {
        return passengers[passengerIndex].getBag(bagIndex).getBagID();
    }

    /** Returns the bag weight at bagIndex of the passenger in Passenger[] passengers at passengerIndex. */
    public double getBagWeight(int bagIndex) {
        return passengers[passengerIndex].getBag(bagIndex).getBagWeight();
    }

    /** Returns the bag screening status at bagIndex of the passenger in Passenger[] passengers at passengerIndex. */
    public String getBagScreeningStatus(int bagIndex) {
        return passengers[passengerIndex].getBag(bagIndex).getScreeningStatus();
    }

    /** Returns seatNumber. */
    public String getSeatNumber() {
        return checkedInBookings.get(passengerIndex).getSeatNumber();
    }

    /** Returns destination. */
    public String getDestination() {
        return checkedInBookings.get(passengerIndex).getDestination();
    }

    /** Returns flightStatus. */
    public String getFlightStatus() {
        return checkedInBookings.get(passengerIndex).getFlightStatus();
    }

    /** Returns gateNumber. */
    public int getGateNumber() {
        return checkedInBookings.get(passengerIndex).getGateNumber();
    }

    /** Returns boardingTime. */
    public String getBoardingTime() {
        return checkedInBookings.get(passengerIndex).getBoardingTime();
    }


}
