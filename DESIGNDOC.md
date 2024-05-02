# Classes

## Bag
This class holds data about a bag.

### Instance Variables

private String bagID - bagID of the bag.

private String bagColor - Color of the bag.

private double bagWeight - Weight of the bag.

### Methods

public String getBagID() - Returns the bagID.

public String getBagColor() - Returns the color of the bag.

public double getBagWeight() - Returns the weight of the bag.


## Passenger
This class holds data about the passenger.

### Instance Variables

private String bookingID - bookingID of the passenger.

private int passportNumber - Passport number of the passenger.

private String fullName - Full name of the passenger.

private Bag[] bags - Bags of the passenger.

### Methods

public Passenger(String bookingID, int passportNumber, String fullName, Bag[] bags[]) - Creates a Passenger object to hold data.

public String getBookingID() - Returns the bookingID.

public int getPassportNumber() - Returns the passport number.

public String getFullName() - Returns the full name.

public Bag getBag(int index) - Returns the bag at int INDEX, returns null, if the bag index doesn't exist.

## KioskCheckInModel

### Instance Variables

private int numberOfPassengers - The number of passengers to check in.

private Passenger[] passenger - Array of passengers, used to keep track of different passengers data.

### Methods

public KioskCheckInModel() - Creates a KioskCheckInModel object to be used.

public void setNumberOfPassengers(int numberOfPassengers) - Sets the number of passengers to be checked in.

public void createPassenger(String bookingID, int passportNumber, String fullName, Bag[] bags) - Creates a passenger object that holds the data to be stored.
