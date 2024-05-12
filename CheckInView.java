import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.WritableRaster;
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
    /** Grid Y to push the BagPartialViewPanel downwards, otherwise it will add horizontally. */
    private int gridYOfBagPartialView = 2;
    /** Panel to add all the JTextFields and BagPartialView,
     * to separate Button Panel to let it stick at the bottom of the view.
     */
    private JPanel panelForTextFieldAndBagPartialView;

    /** Creates a CheckInView object with a model KioskCheckInModel kioskCheckInModel */
    public CheckInView(KioskCheckInModel kioskCheckInModel) {
        this.kioskCheckInModel = kioskCheckInModel;
        bagPartialViews = new LinkedList<>();
        setupViewPanel();
    }

    /** The method to get the size of <LinkedList> bagPartialViews.*/
    public int getNumberOfBagPartialViews() {
        return bagPartialViews.size();
    }

    /** Clears the view. */
    private void clearView() {
        checkInViewPanel.removeAll();
    }

    /** Removes all the bagPartialViews. */
    public void removeAllBagPartialViews() {
        bagPartialViews = new LinkedList<>();
    }

    /** Updates the view. */
    public void updateView() {
        clearView();
        setupViewPanel();
        checkInViewPanel.revalidate();
        checkInViewPanel.repaint();
    }

    /** Sets up the checkInViewPanel. */
    private void setupViewPanel() {

        panelForTextFieldAndBagPartialView = new JPanel(new GridBagLayout());
        int horizontalSizeOfBaggageButton = 200;
        int verticalSizeOfBaggageButton = 8;

        JPanel textFieldPanel = setupViewTextFieldPanel();
        JPanel buttonPanel = setupViewButtonPanel();

        GridBagConstraints constraintsForTextFieldPanel = new GridBagConstraints();
        GridBagConstraints constraintsForButtonPanel = new GridBagConstraints();
        GridBagConstraints constraintsForAddBaggageButton = new GridBagConstraints();
        GridBagConstraints constraintsForScrollPane = new GridBagConstraints();

        constraintsForTextFieldPanel.gridy = 0;

        constraintsForAddBaggageButton.gridy = 1;
        constraintsForAddBaggageButton.ipadx = horizontalSizeOfBaggageButton;
        constraintsForAddBaggageButton.ipady = verticalSizeOfBaggageButton;

        constraintsForButtonPanel.gridy = 1;
        constraintsForButtonPanel.anchor = GridBagConstraints.PAGE_END;

        constraintsForScrollPane.gridx = 0;
        constraintsForScrollPane.gridy = 0;
        constraintsForScrollPane.ipadx = 370;
        constraintsForScrollPane.ipady = 300;


        panelForTextFieldAndBagPartialView.add(textFieldPanel, constraintsForTextFieldPanel);
        panelForTextFieldAndBagPartialView.add(addBagButton, constraintsForAddBaggageButton);

        // If there is any bagPartialViews, add them.
        for (int i = 0; i < bagPartialViews.size(); i++) {
            GridBagConstraints constraintsForBagPartialViewPanel = new GridBagConstraints();
            constraintsForBagPartialViewPanel.gridy = gridYOfBagPartialView + i;
            constraintsForBagPartialViewPanel.insets = new Insets(10, 0, 0, 0);
            bagPartialViews.get(i).setIndex(i);
            panelForTextFieldAndBagPartialView.add(bagPartialViews.get(i).getBagPartialViewPanel(), constraintsForBagPartialViewPanel);
        }

        JPanel wrapperPanel = new JPanel(new BorderLayout());


        wrapperPanel.add(panelForTextFieldAndBagPartialView, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        checkInViewPanel.add(scrollPane, constraintsForScrollPane);
        checkInViewPanel.add(buttonPanel, constraintsForButtonPanel);
    }

    /** Creates a BagPartialView object with the ActionListener listenForRemoveBagPartialViewButton,
     * that is added to the JButton removeBagPartialViewButton,
     and adds the BagPartialView to LinkedList<BagPartialView> bagPartialViews,
     and adds its viewPanel to the checkInViewPanel.
     */
    public void createBagPartialView(ActionListener listenForRemoveBagPartialViewButton) {
        int sizeOfLinkedList = bagPartialViews.size();

        BagPartialView bagPartialView = new BagPartialView();
        bagPartialView.addRemoveBagPartialViewButtonListener(listenForRemoveBagPartialViewButton);
        bagPartialView.setIndex(sizeOfLinkedList);
        bagPartialViews.add(bagPartialView);
    }

    /** Warning method to call when the addBagButton is pressed more than 4 times */
    public JLabel maximumBagErrorMessage() {
        JLabel labelForBaggageText = new JLabel("Maximum number of bags is 4 !!!");
        labelForBaggageText.setForeground(Color.red);

        return labelForBaggageText;
    }

    /** Removes the BagPartialView from the LinkedList<BagPartialView> bagPartialViews,
     * and remove it from the checkInViewPanel.
     */
    public void removeBagPartialView(int index) {
        clearView();
        bagPartialViews.remove(index);
        setupViewPanel();
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

    /** Returns bagPartialViews. */
    public LinkedList<BagPartialView> getBagPartialViews() {
        return bagPartialViews;
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
        JPanel panelForOtherButtons = new JPanel(new GridBagLayout());

        int horizontalSizeOfButton = 18;
        int verticalSizeOfButton = 4;

        GridBagConstraints constraintsForNextButton = new GridBagConstraints();
        GridBagConstraints constraintsForPreviousButton = new GridBagConstraints();
        GridBagConstraints constraintsForMainMenuButton = new GridBagConstraints();
        GridBagConstraints constraintsForBackToCheckInOptionView = new GridBagConstraints();
        GridBagConstraints constraintsForCheckInButton = new GridBagConstraints();
        GridBagConstraints constraintsForNextAndPreviousButtonPanel = new GridBagConstraints();
        GridBagConstraints constraintsForOtherButtonPanel = new GridBagConstraints();

        constraintsForNextButton.ipadx = horizontalSizeOfButton;
        constraintsForNextButton.ipady = verticalSizeOfButton;

        constraintsForPreviousButton.ipadx = horizontalSizeOfButton;
        constraintsForPreviousButton.ipady = verticalSizeOfButton;

        constraintsForBackToCheckInOptionView.ipadx = horizontalSizeOfButton;

        constraintsForMainMenuButton.ipadx = horizontalSizeOfButton;

        constraintsForCheckInButton.ipadx = horizontalSizeOfButton;
        constraintsForCheckInButton.ipady = verticalSizeOfButton;

        // Adjust insets for spacing between next and previous buttons
        constraintsForPreviousButton.insets = new Insets(0, 0, 0, 10); // Add spacing of 10 pixels to the right
        constraintsForNextButton.insets = new Insets(0, 10, 0, 0); // Add spacing of 10 pixels to the left
        constraintsForNextAndPreviousButtonPanel.insets = new Insets(10, 0, 10, 0);
        constraintsForBackToCheckInOptionView.insets = new Insets(0, 10, 0, 10);

        constraintsForPreviousButton.gridx = 0;
        constraintsForPreviousButton.gridy = 0;

        constraintsForNextButton.gridx = 1;
        constraintsForNextButton.gridy = 0;

        constraintsForMainMenuButton.gridy = 0;
        constraintsForMainMenuButton.gridx = 0;

        constraintsForBackToCheckInOptionView.gridy = 0;
        constraintsForBackToCheckInOptionView.gridx = 1;

        constraintsForCheckInButton.gridy = 0;
        constraintsForCheckInButton.gridx = 2;



        constraintsForNextAndPreviousButtonPanel.gridy = 0;
        constraintsForNextAndPreviousButtonPanel.gridx = 0;

        constraintsForOtherButtonPanel.gridy = 1;
        constraintsForOtherButtonPanel.gridx = 0;


        constraintsForMainMenuButton.fill = GridBagConstraints.VERTICAL;
        constraintsForBackToCheckInOptionView.fill = GridBagConstraints.VERTICAL;

        // TODO: Dont add if at certain page.
        if (kioskCheckInModel.getNumberOfPassengers() > 0) {
            if (kioskCheckInModel.getPassengerIndex() > 0) {

                panelForNextAndPreviousButton.add(previousPassengerButton, constraintsForPreviousButton);
            }
            if (kioskCheckInModel.getPassengerIndex() < kioskCheckInModel.getNumberOfPassengers()) {

                panelForNextAndPreviousButton.add(nextPassengerButton, constraintsForNextButton);
            }
        }


        panelForOtherButtons.add(mainMenuButton, constraintsForMainMenuButton);
        panelForOtherButtons.add(checkInKioskButton, constraintsForBackToCheckInOptionView);
        panelForOtherButtons.add(checkInButton, constraintsForCheckInButton);

        informationPageButtonPanel.add(panelForNextAndPreviousButton, constraintsForNextAndPreviousButtonPanel);
        informationPageButtonPanel.add(panelForOtherButtons, constraintsForOtherButtonPanel);

        return informationPageButtonPanel;
    }
}
