/** This class holds the booking information data. */
public class BookingInformation {
    /** Booking Number. */
    private String bookingNumber;
    /** Passport Number. */
    private String passportNumber;
    /** Full name. */
    private String fullName;
    /** Seat number. */
    private String seatNumber;
    /** Destination. */
    private String destination;
    /** Flight status. */
    private String flightStatus;
    /** Gate number. */
    private int gateNumber;
    /** Boarding time. */
    private String boardingTime;

    /** Creates a BookingInformation object with all the booking information data. */
    public BookingInformation(String bookingNumber, String passportNumber, String fullName, String seatNumber, String destination,
                              String flightStatus, int gateNumber, String boardingTime) {
        this.bookingNumber = bookingNumber;
        this.passportNumber = passportNumber;
        this.fullName = fullName;
        this.seatNumber = seatNumber;
        this.destination = destination;
        this.flightStatus = flightStatus;
        this.gateNumber = gateNumber;
        this.boardingTime = boardingTime;
    }

    /** Returns bookingNumber. */
    public String getBookingNumber() {
        return bookingNumber;
    }

    /** Returns passportNumber. */
    public String getPassportNumber() {
        return passportNumber;
    }

    /** Returns fullName. */
    public String getFullName() {
        return fullName;
    }

    /** Returns seatNumber. */
    public String getSeatNumber() {
        return seatNumber;
    }

    /** Returns destination. */
    public String getDestination() {
        return destination;
    }

    /** Returns flightStatus. */
    public String getFlightStatus() {
        return flightStatus;
    }

    /** Returns gateNumber. */
    public int getGateNumber() {
        return gateNumber;
    }

    /** Returns boardingTime. */
    public String getBoardingTime() {
        return boardingTime;
    }
}
