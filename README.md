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

private int passengerIndex - Index for the passenger array.

### Methods

public KioskCheckInModel() - Creates a KioskCheckInModel object to be used.

public void setNumberOfPassengers(int numberOfPassengers) - Sets the number of passengers to be checked in.

public void insertPassenger(Passenger) - Inserts the passenger data's that needs to be stored.

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

private void setupViewPanel() - Sets up the mainMenuViewPanel.

public JPanel getMainMenuViewPanel() - Returns the mainMenuViewPanel.

public void addCheckInCounterButtonListener(ActionListener listenForCheckInCounterButton) - Adds an ActionListener to checkInCounterButton.

public void addCheckInKioskButtonListener(ActionListener listenForCheckInKioskButton) - Adds an ActionListener to checkInKioskButton.

## CounterView
This class represents the view for showing the user their designated physical counter, after choosing to check in physically.

### Instance Variables

private JPanel counterViewPanel - JPanel for the CounterView.

private JButton mainMenuButton - JButton that changes the view to the MainMenuView.

### Methods

public CounterView() - Creates a CounterView object.

private void setupViewPanel() - Sets up the counterViewPanel.

public JPanel getCounterViewPanel() - Returns the counterViewPanel.

public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) - Adds an ActionListener to the JButton mainMenuButton.

## CheckInOptionView
This class represents the view for choosing the check in options, this allows the user to choose between single and group check in.

### Instance Variablles

private JPanel checkInOptionViewPanel - JPanel for CheckInOptionView.

private JButton singleCheckInButton - JButton that changes the view to CheckInView, for user to choose single check in.

private JTextField numberOfPassengersTextField - JTextField that allows users to type the number of passengers they wish to check in, for group check in action.

private JButton groupCheckInButton - JButton that changes the view to CheckInView, for user to choose group check in.

private JButton mainMenuButton - JButton that changes the view to MainMenuView, for user to return to main menu.

### Methods

public CheckInOptionView() - Creates a CheckInOptionView Object.

private void setupViewPanel() - Sets up the checkInOptionViewPanel.

public JPanel getCheckInOptionViewPanel() - Returns the checkInOptionViewPanel.

public String getNumberOfPassengersFromTextField() - Returns the numberOfPassengers from the JTextField numberOfPassengersTextField, this will be sent to the KioskCheckInModel so that it knows how many passenger's data it should keep track of.

public void addSingleCheckInButtonListener(ActionListener listenForSingleCheckInButton) - Adds an ActionListener to singleCheckInButton.

public void addGroupCheckInButtonListener(ActionListener listenForGroupCheckInButton) - Adds an ActionListener to groupCheckInButton.

public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) - Adds an ActionListener to mainMenuButton.


## CheckInView
This class represents the view for users to check in.

### Instance Variables

private JPanel checkInViewPanel - JPanel for the CheckInView.

private KioskCheckInModel kioskCheckInModel - The model that holds the passenger and their bag data.

private JTextField bookingIDTextField - The text field where user enters their bookingID.

private JFieldText passportNumberTextField - The text field where user enters their passport number.

private JTextField fullNameTextField - The text field where the user enters their full name.

private LinkedList<BagPartialView> bagPartialViews - A Linked List of BagPartialView that each asks the user for information for a different bag, LinkedList used because it does not allow gaps between data. For example, a list of 3, if I remove the 2nd element, the 3rd element will now become the 2nd element. Therefore, no null is made.

private JButton addBagButton - JButton that creates a BagPartialView and adds its viewPanel to LinkedList<JPanel> bagPartialViewPanels.

private JButton previousPassengerButton - JButton that changes the CheckInView to display the previous Passenger information. This button will not be displayed when showing the FIRST Passenger's data.

private JButton nextPassengerButton - JButton that changes the CheckInView to display an empty CheckInView, if the Passenger information hasn't been added yet. Otherwise, shows the Passenger information that has been added. This button will not be displayed when showing the LAST Passenger data.

private JButton checkInButton - JButton that checks in all the Passengers and their data, changing the view to the BoardingPassView. For user to check in, once all sufficient data is added. An error will be shown, if there is a Passenger information not filled.

private JButton checkInKioskButton - JButton that changes the view back to CheckInOptionView.

private JButton mainMenuButton - JButton that changes the view to the MainMenuView.

### Methods

public CheckInView(KioskCheckInModel kioskCheckInModel) - Creates a CheckInView object with a model KioskCheckInModel kioskCheckInModel.

private void setupViewPanel() - Sets up the checkInViewPanel.

public void createBagPartialView(ActionListener listenForCloseBagPartialViewButton) - 
Creates a BagPartialView object with the ActionListener listenForCloseBagPartialViewButton added to the JButton closeBagPartialViewButton, 
and adds the BagPartialView to LinkedList<BagPartialView> bagPartialViews, and adds its viewPanel to the checkInViewPanel.

public void removeBagPartialView(int index) - Removes the BagPartialView from the LinkedList<BagPartialView> bagPartialViews and remove it from the checkInViewPanel.

public JPanel getCheckInViewPanel() - Returns the checkInViewPanel.

public String getBookingIDFromTextField() - Returns the bookingID from the text field set by the user.

public void setBookingIDTextField(String bookingID) - Sets the bookingIDTextField with String bookingID.

public String getPassportNumberFromTextField() - Returns the passport number from the text field set by the user.

public void setPassportNumberTextField(String passportNumber) - Sets the passportNumberTextField with String passportNumber.

public String getFullNameFromTextField() - Returns the full name from the text field set by the user.

public void setFullNameTextField(String fullName) - Sets the fullNameTextField with String fullName.

public void addAddBagButtonListener(ActionListener listenForAddBagButton) - Adds an ActionListener to addBagButton.

public void addPreviousPassengerButtonListener(ActionListener listenForPreviousPassengerButton) - Adds an ActionListener to previousPassengerButton.

public void addNextPassengerButtonListener(ActionListener listenForNextPassengerButton) - Adds an ActionListener to nextPassengerButton.

public void addCheckInButtonListener(ActionListener listenForCheckInButton) - Adds an ActionListener to checkInButton.

public void addCheckInKioskButtonListener(ActionListener listenForCheckInKioskButton) - Adds an ActionListener to checkInKioskButton.

public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) - Adds an ActionListener to mainMenuButton.

private JPanel setupViewTextFieldPanel() - Sets up the JTextField, JLabel and adjusting micro position for JLabel and JTextFields.

private JPanel setupViewButtonPanel() - Sets up JButtons and adjusting.

## BagPartialView
This class is a partial view for the CheckInView class.

### Instance Variables

private JPanel bagPartialViewPanel - JPanel for BagPartialView.

private JTextField bagColorTextField - The text field where user enters the bag color.

private JTextField bagWeightTextField - The text field where user enters the bag weight.

private JButton closeBagPartialViewButton - JButton that deletes this BagPartialView from CheckInView.

### Methods

public BagPartialView(ActionListener listenForCloseBagPartialViewButton) - Creates a BagPartialView object with ActionListener listenForCloseBagPartialViewButton.

private void addCloseBagPartialViewButtonListener(ActionListener listenForCloseBagPartialViewButton) - Adds an ActionListener to JButton closeBagPartialViewButton.

private void setupViewPanel() - Sets up the bagPartialViewPanel.

public JPanel getBagPartialViewPanel() - Returns bagPartialViewPanel.

public String getBagColorFromTextField() - Returns the bag color from the text field set by the user.

public void setBagColorTextField(String bagColor) - Sets the bagColorTextField with String bagColor.

public String getBagWeightFromTextField() - Returns the bag weight from the text field set by the user.

public void setBagWeightTextField(String bagWeight) - Sets the bagWeightTextField with String bagWeight.

## BoardingPassView
This class represents the view to show the boarding pass.

### Instance Variables

private JPanel boardingPassViewPanel - JPanel for BoardingPassView.

private KioskCheckInModel kioskCheckInModel - The model that holds the passenger and their bag data.

private JButton previousBoardingPassButton - JButton to show the previous passenger's and their bag's data, this button won't be displayed if the passenger is the first passenger on the list.

private JButton nextBoardingPassButton - JButton to show the next passenger's and their bag's data, this button won't be displayed if the passenger is the last passenger on the list.

private JButton printBoardingPassButton - JButton that saves a PDF file of the BoardingPassView.

private JButton mainMenuButton - JButton that changes the view to the MainMenuView.

### Methods

public BoardingPassView(KioskCheckInModel kioskCheckInModel) - Creates a BoardingPassView object with KioskCheckInModel kioskCheckInModel as the model.

private void setupViewPanel - Sets up the boardingPassViewPanel.

public JPanel getBoardingPassViewPanel() - Returns the boardingPassViewPanel.

public void addPreviousBoardingPassButtonListener(ActionListener listenForPreviousBoardingPassButton) - Adds an ActionListener to previousBoardingPassButton.

public void addNextBoardingPassButtonListener(ActionListener listenForNextBoardingPassButton) - Adds an ActionListener to nextBoardingPassButton.

public void addPrintBoardingPassButtonListener(ActionListener listenForPrintBoardingPassButtonListener) - Adds an ActionListener to printBoardingPassButton.

public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) - Adds an ActionListener to mainMenuButton.

## GUI
This class is the GUI of the program, it creates a window to display all the necessary view classes.

### Instance Variables

private JFrame GUI - The JFrame of the GUI, creates a window for the program.

private JPanel GUIViewPanel - The GUI view panel that will occupy the JFrame, it uses a CardLayout to switch to other view panels.

private MainMenuView mainMenuView - The main menu view.

private CounterView counterView - The counter view.

private CheckInOptionView checkInOptionView - The check in option view.

private CheckInView checkInView - The check in view.

private BoardingPassView boardingPassView - the boarding pass view.

### Methods

public GUI(KioskCheckInModel kioskCheckInModel) - Creates a GUI object that takes in KioskCheckInModel kioskCheckInModel for the view to use.

private void setupGUI() - Sets up the GUIViewPanel.

public void display() - Displays the GUI JFrame window.

public void exit() - Terminates the program and closes the GUI.

public void changeView(String index) - Changes the view of the CardLayout in GUIViewPanel.

public MainMenuView getMainMenuView() - Returns the mainMenuView.

public CounterView getCounterView() - Returns the counterView.

public CheckInOptionView getCheckInOptionView() - Returns the checkInOptionView.

public BoardingPassView getBoardingPassView() - Returns the boardingPassView.

public void addAllCheckInCounterButtonsListener(ActionListener listenForAllCheckInCounterButtons) - Adds an ActionListener to ALL checkInCounterButton in all the view objects.

public void addAllCheckInKioskButtonsListener(ActionListener listenForAllCheckInKioskButtons) - Adds an ActionListener to ALL checkInKioskButton in all the view objects.

public void addAllSingleCheckInButtonsListener(ActionListener listenForAllSingleCheckInButtons) - Adds an ActionListener to ALL singleCheckInButton in all the view objects.

public void addAllGroupCheckInButtonsListener(ActionListener listenForAllGroupCheckInButtons) - Adds an ActionListener to ALL groupCheckInButton in all the view objects.

public void addAllAddBagButtonsListener(ActionListener listenForAllAddBagButtons) - Adds an ActionListener to ALL addBagButton in all the view objects.

public void addAllPreviousPassengerButtonsListener(ActionListener listenForAllPreviousPassengerButtons) - Adds an ActionListener to ALL previousPassengerButton in all the view objects.

public void addAllNextPassengerButtonsListener(ActionListener listenForAllNextPassengerButtons) - Adds an ActionListener to ALL nextPassengerButton in all the view objects.

public void addAllCheckInButtonsListener(ActionListener listenForAllCheckInButtons) - Adds an ActionListener to ALL checkInButton in all the view objects.

public void addAllPreviousBoardingPassButtonsListener(ActionListener listenForAllPreviousBoardingPassButtons) - Adds an ActionListener to ALL previousBoardingPassButton in all the view objects.

public void addAllNextBoardingPassButtonsListener(ActionListener listenForAllNextBoardingPassButtons) - Adds an ActionListener to ALL nextBoardingPassButton in all the view objects.

public void addAllPrintBoardingPassButtonsListener(ActionListener listenForAllPrintBoardingPassButtons) - Adds an ActionListener to ALL printBoardingPassButton in all the view objects.

public void addAllMainMenuButtonsListener(ActionListener listenForAllMainMenuButtons) - Adds an ActionListener to ALL mainMenuButton in all the view objects.

## Controller
This class acts as the controller of the MVC structure in this program.

### Instance Variables

private GUI gui - The gui.

private KioskCheckInModel kioskCheckInModel - The model to hold all the data.

### Methods

public Controller(GUI gui, KioskCheckInModel kioskCheckInModel) - Creates a Controller object with GUI gui, and KioskCheckInModel kioskCheckInModel,
acts as the bridge between GUI gui and KioskCheckInModekl kioskCheckInModel,
controlling the workflow. 