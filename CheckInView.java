import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;    

/** This class represents the view for users to check in. */
public class CheckInView {
    /** JPanel for the CheckInView. */
    private JPanel checkInViewPanel = new JPanel(new GridBagLayout());
    /** The model that holds the passenger and their bag data. */
    private KioskCheckInModel kioskCheckInModel;
    /** The text field where user enters their bookingID. */
    private JTextField bookingIDTextField;
    /** The text field where user enters their passport number. */
    private JTextField passportNumberTextField;
    /** The text field where the user enters their full name. */
    private JTextField fullNameTextField;
    /** A Linked List of BagPartialView that each asks the user for information for a different bag,
     *  LinkedList used because it does not allow gaps between data.
     *  For example, a list of 3, if I remove the 2nd element, the 3rd element will now become the 2nd element.
     *  Therefore, no null is made.
     */
    private LinkedList<BagPartialView> bagPartialViews;
    /** JButton that creates a BagPartialView and adds its viewPanel to LinkedList<JPanel> bagPartialViewPanels. */
    private JButton addBagButton = new JButton("Add Baggage");
    /** JButton that changes the CheckInView to display the previous Passenger information.
     * This button will not be displayed when showing the FIRST Passenger's data.
     */
    private JButton previousPassengerButton = new JButton("Previous");
    /** JButton that changes the CheckInView to display an empty CheckInView,
     * if the Passenger information hasn't been added yet.
     * Otherwise, shows the Passenger information that has been added.
     * This button will not be displayed when showing the LAST Passenger data.
     */
    private JButton nextPassengerButton = new JButton("Next");
    /** JButton that checks in all the Passengers and their data,
     * changing the view to the BoardingPassView.
     * For user to check in, once all sufficient data is added.
     * An error will be shown, if there is a Passenger information not filled.
     */
    private JButton checkInButton = new JButton("Check In");
    /** JButton that changes the view back to CheckInOptionView. */
    private JButton checkInKioskButton = new JButton("Last Page");
    /** JButton that changes the view to the MainMenuView. */
    private JButton mainMenuButton = new JButton("Main Menu");

    /** Creates a CheckInView object with a model KioskCheckInModel kioskCheckInModel */
    public CheckInView(KioskCheckInModel kioskCheckInModel) {
        this.kioskCheckInModel = kioskCheckInModel;
        bagPartialViews = new LinkedList<>();
        setupViewPanel();
    }

    /** Sets up the checkInViewPanel. */
    private void setupViewPanel() {
        int horizontalSizeOfBaggageButton = 200;
        int verticalSizeOfBaggageButton = 8;

        JPanel textFieldPanel = setupViewTextFieldPanel();
        JPanel buttonPanel = setupViewButtonPanel();

        GridBagConstraints constraintsForTextFieldPanel = new GridBagConstraints();
        GridBagConstraints constraintsForButtonPanel = new GridBagConstraints();
        GridBagConstraints constraintsForAddBaggageButton = new GridBagConstraints();

        constraintsForTextFieldPanel.gridy = 0;
        constraintsForTextFieldPanel.insets = new Insets(120, 0, 0, 0);

        constraintsForAddBaggageButton.gridy = 1;
        constraintsForAddBaggageButton.ipadx = horizontalSizeOfBaggageButton;
        constraintsForAddBaggageButton.ipady = verticalSizeOfBaggageButton;
        constraintsForAddBaggageButton.insets = new Insets(0, 0, 0, 0);

        constraintsForButtonPanel.gridy = 3;
        constraintsForButtonPanel.insets = new Insets(180, 0, 0, 0);

        checkInViewPanel.add(addBagButton, constraintsForAddBaggageButton);
        checkInViewPanel.add(textFieldPanel, constraintsForTextFieldPanel);
        checkInViewPanel.add(buttonPanel, constraintsForButtonPanel);
    }

    /** Creates a BagPartialView object with the ActionListener listenForCloseBagPartialViewButton,
     * that is added to the JButton closeBagPartialViewButton,
     and adds the BagPartialView to LinkedList<BagPartialView> bagPartialViews,
     and adds its viewPanel to the checkInViewPanel.
     */
    public void createBagPartialView(ActionListener listenForCloseBagPartialViewButton) {
        BagPartialView bagPartialView = new BagPartialView();
        GridBagConstraints constraintsForBagPartialViewPanel = new GridBagConstraints();
        constraintsForBagPartialViewPanel.gridy = 2;
        constraintsForBagPartialViewPanel.insets = new Insets(20, 0, 0, 0);
        bagPartialViews.addLast(bagPartialView);
        checkInViewPanel.add(bagPartialView.getBagPartialViewPanel(), constraintsForBagPartialViewPanel);
        checkInViewPanel.revalidate();
        checkInViewPanel.repaint();
    }

    /** Removes the BagPartialView from the LinkedList<BagPartialView> bagPartialViews,
     * and remove it from the checkInViewPanel.
     */
    public void removeBagPartialView(int index) {

    }

    /** Returns the checkInViewPanel. */
    public JPanel getCheckInViewPanel() {
        return checkInViewPanel;
    };

    /** Returns the bookingID from the text field set by the user. */
    public String getBookingIDFromTextField() {
        return bookingIDTextField.getText();
    }

    /** Sets the bookingIDTextField with String bookingID. */
    public void setBookingIDTextField(String bookingID) {
        bookingIDTextField.setText(bookingID);
    }

    /** Returns the passport number from the text field set by the user. */
    public String getPassportNumberFromTextField() {
        return passportNumberTextField.getText();
    }

    /** Sets the passportNumberTextField with String passportNumber. */
    public void setPassportNumberTextField(String passportNumber) {
        passportNumberTextField.setText(passportNumber);
    }

    /** Returns the full name from the text field set by the user. */
    public String getFullNameFromTextField() {
        return fullNameTextField.getText();
    }

    /** Sets the fullNameTextField with String fullName. */
    public void setFullNameTextField(String fullName) {
        fullNameTextField.setText(fullName);
    }

    /** Adds an ActionListener to addBagButton. */
    public void addAddBagButtonListener(ActionListener listenForAddBagButton) {
        addBagButton.addActionListener(listenForAddBagButton);
    }

    /** Adds an ActionListener to previousPassengerButton. */
    public void addPreviousPassengerButtonListener(ActionListener listenForPreviousPassengerButton) {
        previousPassengerButton.addActionListener(listenForPreviousPassengerButton);
    }

    /** Adds an ActionListener to nextPassengerButton. */
    public void addNextPassengerButtonListener(ActionListener listenForNextButtonListener) {
        nextPassengerButton.addActionListener(listenForNextButtonListener);
    }

    /** Adds an ActionListener to checkInButton. */
    public void addCheckInButtonListener(ActionListener listenForCheckInButton) {
        checkInButton.addActionListener(listenForCheckInButton);
    }

    /** Adds an ActionListener to checkInKioskButton. */
    public void addCheckInKioskButtonListener(ActionListener listenForCheckInKioskButton) {
        checkInKioskButton.addActionListener(listenForCheckInKioskButton);
    }

    /** Adds an ActionListener to mainMenuButton. */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }


    /** Sets up the JTextField, JLabel and adjusting micro position for JLabel and JTextFields. */
    private JPanel setupViewTextFieldPanel() {
        int horizontalSizeOfTextField = 155;
        int verticalSizeOfTextField = 5;

        JPanel textFieldPanel = new JPanel(new GridBagLayout());

        JLabel passportLabel = new JLabel("Passport Number");
        passportNumberTextField = new JTextField();

        JLabel bookingNumberLabel = new JLabel("Booking Number");
        bookingIDTextField = new JTextField();

        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameTextField = new JTextField();
        
        GridBagConstraints gridBagConstraintsForLabels = new GridBagConstraints();
        gridBagConstraintsForLabels.anchor = GridBagConstraints.WEST;
        gridBagConstraintsForLabels.insets = new Insets(0, 0, 15, 10); 

        GridBagConstraints gridBagConstraintsForTextFields = new GridBagConstraints();
        gridBagConstraintsForTextFields.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsForTextFields.weightx = 1;
        gridBagConstraintsForTextFields.ipadx = horizontalSizeOfTextField;
        gridBagConstraintsForTextFields.ipady = verticalSizeOfTextField;
        gridBagConstraintsForTextFields.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraintsForTextFields.insets = new Insets(0, 20, 15, 0);
        
        textFieldPanel.add(passportLabel, gridBagConstraintsForLabels);
        textFieldPanel.add(passportNumberTextField, gridBagConstraintsForTextFields);
        textFieldPanel.add(bookingNumberLabel, gridBagConstraintsForLabels);
        textFieldPanel.add(bookingIDTextField, gridBagConstraintsForTextFields);
        textFieldPanel.add(fullNameLabel, gridBagConstraintsForLabels);
        textFieldPanel.add(fullNameTextField, gridBagConstraintsForTextFields);

        textFieldPanel.setPreferredSize(new Dimension(350, 150)); 

        return textFieldPanel;
    }

    /** Sets up JButtons and adjusting. */
    private JPanel setupViewButtonPanel() {
        JPanel informationPageButtonPanel = new JPanel(new GridBagLayout());
        JPanel panelForNextAndPreviousButton = new JPanel(new GridBagLayout());

        int horizontalSizeOfButton = 18;
        int verticalSizeOfButton = 4;

        GridBagConstraints constraintsForNextButton = new GridBagConstraints();
        GridBagConstraints constraintsForPreviousButton = new GridBagConstraints();
        GridBagConstraints constraintsForMainMenuButton = new GridBagConstraints();
        GridBagConstraints constraintsForBackToCheckInOptionView = new GridBagConstraints();
        GridBagConstraints constraintsForCheckInButton = new GridBagConstraints();
        GridBagConstraints constraintsForNextAndPreviousButtonPanel = new GridBagConstraints();

        constraintsForNextButton.ipadx = horizontalSizeOfButton;
        constraintsForNextButton.ipady = verticalSizeOfButton;

        constraintsForPreviousButton.ipadx = horizontalSizeOfButton;
        constraintsForPreviousButton.ipady = verticalSizeOfButton;

        constraintsForBackToCheckInOptionView.ipadx = horizontalSizeOfButton;
        constraintsForBackToCheckInOptionView.ipady = verticalSizeOfButton;

        constraintsForMainMenuButton.ipadx = horizontalSizeOfButton;
        constraintsForMainMenuButton.ipady = verticalSizeOfButton;

        constraintsForCheckInButton.ipadx = horizontalSizeOfButton;
        constraintsForCheckInButton.ipady = verticalSizeOfButton;

        // Adjust insets for spacing between next and previous buttons
        constraintsForPreviousButton.insets = new Insets(0, 0, 0, 10); // Add spacing of 10 pixels to the right
        constraintsForNextButton.insets = new Insets(0, 10, 0, 0); // Add spacing of 10 pixels to the left
        constraintsForNextAndPreviousButtonPanel.insets = new Insets(10, 0, 10, 0);
        constraintsForBackToCheckInOptionView.insets = new Insets(0, 10, 0, 10);

        constraintsForPreviousButton.gridx = 0;
        constraintsForNextButton.gridx = 1;

        constraintsForCheckInButton.gridy = 2;
        constraintsForMainMenuButton.gridy = 2;
        constraintsForBackToCheckInOptionView.gridy = 2;

        constraintsForNextAndPreviousButtonPanel.gridy = 1;
        constraintsForNextAndPreviousButtonPanel.gridx = 0;

        constraintsForNextAndPreviousButtonPanel.gridwidth = 3; // Set the width to span both columns

        constraintsForNextButton.fill = GridBagConstraints.HORIZONTAL;
        constraintsForPreviousButton.fill = GridBagConstraints.HORIZONTAL;
        constraintsForMainMenuButton.fill = GridBagConstraints.HORIZONTAL;
        constraintsForCheckInButton.fill = GridBagConstraints.HORIZONTAL;
        constraintsForBackToCheckInOptionView.fill = GridBagConstraints.HORIZONTAL;

        panelForNextAndPreviousButton.add(nextPassengerButton, constraintsForNextButton);
        panelForNextAndPreviousButton.add(previousPassengerButton, constraintsForPreviousButton);

        informationPageButtonPanel.add(panelForNextAndPreviousButton, constraintsForNextAndPreviousButtonPanel);

        informationPageButtonPanel.add(mainMenuButton, constraintsForMainMenuButton);
        informationPageButtonPanel.add(checkInKioskButton, constraintsForBackToCheckInOptionView);
        informationPageButtonPanel.add(checkInButton, constraintsForCheckInButton);

        return informationPageButtonPanel;
    }




}
