# Design Document


# Classes

## Bag
This class holds data about a bag.

### Instance Variables

private String bagID - bagID of the bag.

private String bagColor - Color of the bag.

private double bagWeight - Weight of the bag.

### Methods

public Bag(String bagID, String bagColor, double bagWeight) -  Creates a bag object to hold data about a bag.

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
This class represents the model for kiosk check in.

### Instance Variables

private int numberOfPassengers - The number of passengers to check in.

private Passenger[] passenger - Array of passengers, used to keep track of different passengers data.

### Methods

public KioskCheckInModel() - Creates a KioskCheckInModel object to be used.

public void setNumberOfPassengers(int numberOfPassengers) - Sets the number of passengers to be checked in.

public void createPassenger(String bookingID, int passportNumber, String fullName, Bag[] bags) - Creates a passenger object that holds the data to be stored.

public String getBookingID(int passengerIndex) - Returns the bookingID of the passenger in Passenger[] passengers at passengerIndex.

public int getPassportNumber(int passengerIndex) - Returns the passport number of the passenger in Passenger[] passengers at passengerIndex.

public String getFullName(int passengerIndex) - Returns the full name of the passenger in Passenger[] passengers at passengerIndex.

public String getBagID(int passengerIndex, int bagIndex) - Returns the bagID at bagIndex of the passenger in Passenger[] passengers at passengerIndex.

public String getBagColor(int passengerIndex, int bagIndex) - Returns the bag color at bagIndex of the passenger in Passenger[] passengers at passengerIndex.

public String getBagWeight(int passengerIndex, int bagIndex) - Returns the bag weight at bagIndex of the passenger in Passenger[] passengers at passengerIndex.

## MainMenuView
This class represents the view for the main menu.

### Instance Variables

private JPanel mainMenuViewPanel - JPanel for the MainMenuView.

private JButton checkInCounterButton - JButton to change the view to CounterView.

private JButton checkInKioskButton - JButton to change the view to CheckInOptionView.

### Methods

public MainMenuView() - Creates a MainMenuView object.

private void setupViewPanel() - Setups the mainMenuViewPanel.

public JPanel getMainMenuViewPanel() - Returns the mainMenuViewPanel.

public void addCheckInCounterButtonListener(ActionListener listenForCheckInCounterButton) - Adds an ActionListener to the JButton checkInCounterButton.

public void addCheckInKioskButtonListener(ActionListener listenForCheckInKioskButton) - Adds an ActionListener to the JButton checkInKioskButton.

## CounterView
This class represents the view for showing the user their designated physical counter, after choosing to check in physically.

### Instance Variables

private JPanel counterViewPanel - JPanel for the CounterView.

private JButton mainMenuButton - JButton that changes the view to the MainMenuView.

### Methods

public CounterView() - Creates a CounterView object.

private void setupViewPanel() - Setups the counterViewPanel.

public JPanel getCounterViewPanel() - Returns the counterViewPanel.

public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) - Adds an ActionListener to the JButton mainMenuButton.

## CheckInOptionView
This class represents the view for choosing the check in options, this allows the user to choose between single and group check in.

### Instance Variablles

private JPanel checkInOptionViewPanel - JPanel for the CheckInOptionView.

private JButton singleCheckInButton - JButton that changes the view to CheckInView, for user to choose single check in.

private JTextField numberOfPassengersTextField - JTextField that allows users to type the number of passengers they wish to check in, for group check in action.

private JButton groupCheckInButton - JButton that changes the view to CheckInView, for user to choose group check in.

private JButton mainMenuButton - JButton that changes the view to MainMenuView, for user to return to main menu.

### Methods

public CheckInOptionView() - Creates a CheckInOptionView Object.

private void setupViewPanel() - Setups the checkInOptionViewPanel.

public JPanel getCheckInOptionViewPanel() - Returns the checkInOptionViewPanel.

public String getNumberOfPassengersFromTextField() - Returns the numberOfPassengers from the JTextField numberOfPassengersTextField, this will be sent to the KioskCheckInModel so that it knows how many passenger's data it should keep track of.

public void addSingleCheckInButtonListener(ActionListener listenForSingleCheckInButton) - Adds an ActionListener to the JButton singleCheckInButton.

public void addGroupCheckInButtonListener(ActionListener listenForGroupCheckInButton) - Adds an ActionListener to the JButton groupCheckInButton.

public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) - Adds an ActionListener to the JButton mainMenuButton.
