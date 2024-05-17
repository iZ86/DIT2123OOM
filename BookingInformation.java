public class BookingInformation {
    String bookingID;
    String seatNumber;
    String destination;
    String flightStatus;
    String gate;
    String boardingTime;

    public BookingInformation(String bookingID, String seatNumber, String destination,
                              String flightStatus, String gate, String boardingTime) {
        this.bookingID = bookingID;
        this.seatNumber = seatNumber;
        this.destination = destination;
        this.flightStatus = flightStatus;
        this.gate = gate;
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

    public String getGate() {
        return gate;
    }

    public String getBoardingTime() {
        return boardingTime;
    }
}
