public class BookingInformation {
    String bookingID;
    String seatNumber;
    String destination;
    String flightStatus;
    int gateNumber;
    String boardingTime;

    public BookingInformation(String bookingID, String seatNumber, String destination,
                              String flightStatus, int gateNumber, String boardingTime) {
        this.bookingID = bookingID;
        this.seatNumber = seatNumber;
        this.destination = destination;
        this.flightStatus = flightStatus;
        this.gateNumber = gateNumber;
        this.boardingTime = boardingTime;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getDestination() {
        return destination;
    }
    
    public String getFlightStatus() {
        return flightStatus;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public String getBoardingTime() {
        return boardingTime;
    }
}
