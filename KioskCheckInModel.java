import java.util.HashMap;

/** This class represents the model for the AeroCheck kiosk check in,
 * storing all the necessary data for check ins.
 */
public class KioskCheckInModel {
    /** Questions to ask users regarding their baggage. */
    private static final String[] QUESTIONS = new String[] {
            "1. Have you packed your baggage yourself?",
            "2. Has anyone asked you to carry anything on board for them?",
            "3. Have you left your baggage unattended at any time?",
            "4. Did you remember to turn off all electronic devices and remove their batteries, if removable, before checking in your luggage?",
            "5. Are you carrying any liquids, gels, or aerosols in your carry-on luggage?",
            "5b. If so, are they in containers of 100mL or less and stored in a clear, resealable plastic bag?"
    };
    /** Answers to the questions, 1 for yes, -1 for no, -2 for both is correct. */
    private static final int[] ANSWERS = new int[]{1, -1, -1, 1, -2, 1};
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
    private final Utils utils = new Utils();
    /** Number of bags checked in. */
    private int numberOfBagsCheckedIn;
    /** HashMap to hold the booking number that has been checked in,
     * the key will be the bookingNumber, but the value will always be null.
     * HashMap is used for faster look up.
     */
    private HashMap<String, String> checkedInBookingNumbers = new HashMap<>();
    /** HashMap to hold all the bookingNumber that cannot be checked in,
     * due to being locked for various reasons,
     * the key will be the bookingNumber, but the value will always be null.
     * HashMap is used for faster look up. */
    private HashMap<String, String> lockedBookingNumbers = new HashMap<>();

    /** Creates the model for AeroCheck Kiosk. */
    public KioskCheckInModel() {
        passengers = null;
        numberOfPassengers = 0;
        passengerIndex = 0;
        numberOfBagsCheckedIn = 0;
        bookingInformationData = new HashMap<>();
        setupBookingInformationData();
    }

    /** Setups up the bookingInformationData. */
    private void setupBookingInformationData() {
        for (int i = 0; i < Utils.NUMBEROFDATA; i++) {
            bookingInformationData.put(Utils.BOOKINGNUMBERS[i], new BookingInformation(Utils.BOOKINGNUMBERS[i], Utils.PASSPORTNUMBERS[i], Utils.FULLNAME[i], Utils.SEATNUMBERS[i], Utils.DESTINATIONS[i], "Ready", Utils.BOARDINGGATES[i], Utils.BOARDINGTIMES[i]));
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

    /** Checks the answer, if all the answers are correct.
     * Return true. Otherwise, return false.
     */
    public boolean checkAnswers(Passenger passengerData) {
        for (int i = 0; i < QUESTIONS.length - 1; i++) {
            // If the answer is not the same, and the answer is not -2.
            if ((passengerData.getBagCheckInQuestionAnswer(i) != ANSWERS[i]) && ANSWERS[i] != -2) {
                return false;
            }
        }

        // If qn 5 is yes, check if qn 5b is correct or not.
        if (passengerData.getBagCheckInQuestionAnswer(4) == 1 && passengerData.getBagCheckInQuestionAnswer(5) != ANSWERS[5]) {
            return false;
        }

        return true;
    }


    /** Returns the questions at int index. */
    public String getBagCheckInQuestions(int index) {
        return QUESTIONS[index];
    }

    /** Returns the number of bag checked in quetions. */
    public int getNumberOfBagCheckInQuestions() {
        return QUESTIONS.length;
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
        numberOfBagsCheckedIn += 1;
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
        checkedInBookingNumbers.put(passenger.getBookingNumber(), null);
    }

    /** Insert bookingNumbers that cannot be checked in. */
    public void lockBookingNumber(String bookingNumber) {
        lockedBookingNumbers.put(bookingNumber, null);
    }

    /** Returns true if the bookingNumber has been locked. */
    public boolean isBookingNumberLocked(String bookingNumber) {
        return lockedBookingNumbers.containsKey(bookingNumber);
    }

    /** Returns true if the bookingNumber has already been checked in. */
    public boolean isBookingNumberCheckedIn(String bookingNumber) {
        return checkedInBookingNumbers.containsKey(bookingNumber);
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

    /** Returns the passenger in Passenger[] passengers at passengerIndex,
     * status of requireWheelchair.
     */
    public boolean isRequireWheelchair() {
        return passengers[passengerIndex].isRequireWheelchair();
    }

    /** Returns the passenger in Passenger[] passengers at passengerIndex,
     * status of blindness.
     */
    public boolean isBlind() {
        return passengers[passengerIndex].isBlind();
    }

    /** Returns the passenger in Passenger[] passengers at passengerIndex,
     * status of deafness.
     */
    public boolean isDeaf() {
        return passengers[passengerIndex].isDeaf();
    }

    /** Returns the passenger in Passenger[] passengers at passengerIndex,
     * status of any other special accommodation.
     */
    public boolean isOtherSpecialAccommodation() {
        return passengers[passengerIndex].isOtherSpecialAccommodation();
    }

    /** Returns the passenger in Passenger[] passengers at passengerIndex,
     * the details of the special accommodation details if any.
     */
    public String getOtherSpecialAccommodationDetails() {
        return passengers[passengerIndex].getOtherSpecialAccommodationDetails();
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
        return bookingInformationData.get(passengers[passengerIndex].getBookingNumber()).getSeatNumber();
    }

    /** Returns destination. */
    public String getDestination() {
        return bookingInformationData.get(passengers[passengerIndex].getBookingNumber()).getDestination();
    }

    /** Returns flightStatus. */
    public String getFlightStatus() {
        return bookingInformationData.get(passengers[passengerIndex].getBookingNumber()).getFlightStatus();
    }

    /** Returns gateNumber. */
    public int getGateNumber() {
        return bookingInformationData.get(passengers[passengerIndex].getBookingNumber()).getGateNumber();
    }

    /** Returns boardingTime. */
    public String getBoardingTime() {
        return bookingInformationData.get(passengers[passengerIndex].getBookingNumber()).getBoardingTime();
    }
}
