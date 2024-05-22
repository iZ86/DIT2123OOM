import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.image.WritableRaster;
import java.util.LinkedList;

/** This class represents the view for users to check in. */
public class CheckInView {
    /** JPanel for the CheckInView. */
    private JPanel checkInViewPanel = new JPanel(new GridBagLayout());
    /** The model that holds the passenger and their bag data. */
    private KioskCheckInModel kioskCheckInModel;
    /** The text field where user enters their bookingID. */
    private JTextField bookingNumberTextField = new JTextField();
    /** The text field where user enters their passport number. */
    private JTextField passportNumberTextField = new JTextField();
    /** The text field where the user enters their full name. */
    private JTextField fullNameTextField = new JTextField();
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
    private JButton previousPassengerButton = new JButton("< ~ ~ ~");
    /** JButton that changes the CheckInView to display an empty CheckInView,
     * if the Passenger information hasn't been added yet.
     * Otherwise, shows the Passenger information that has been added.
     * This button will not be displayed when showing the LAST Passenger data.
     */
    private JButton nextPassengerButton = new JButton("~ ~ ~ >");
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
    private int gridYOfBagPartialView = 3;
    /** Check Box for Wheel Chair Option.*/
    private JCheckBox wheelchairCheckBox = new JCheckBox("Wheelchair");
    /** Check Box for Blindness Option.*/
    private JCheckBox blindnessCheckBox = new JCheckBox("Blindness");
    /** Check Bo for Deafness Option.*/
    private JCheckBox deafnessCheckbox = new JCheckBox("Deafness");
    /** Check Box for Others Option*/
    private JCheckBox othersSpecialAccommodationCheckBox = new JCheckBox("Others");
    /** Text Field to let user enter if user choose OTHERS Option.*/
    private JTextField othersSpecialAccommodationTextField = new JTextField();
    /** checkInViewPagingIndex, used to show the page that the user is at. */
    private int checkInViewPagingIndex;
    /** True iff the bookingID inputted is invalid, used to decide when to display "invalid bookingID". */
    private boolean invalidBookingID;
    /** Deciding factor if there should be a warning label for empty booking number in bookingNumberTextField. */
    private boolean warnEmptyBookingNumberInput;
    /** Deciding factor if there should be a warning label for empty passport number in passportNumberTextField. */
    private boolean warnEmptyPassportNumberInput;
    /** Deciding factor if there should be a warning label for empty full name in fullNameTextField. */
    private boolean warnEmptyFullNameInput;

    /** Creates a CheckInView object with a model KioskCheckInModel kioskCheckInModel */
    public CheckInView(KioskCheckInModel kioskCheckInModel) {
        this.kioskCheckInModel = kioskCheckInModel;
        bagPartialViews = new LinkedList<>();
        invalidBookingID = false;
        setupViewPanel();
        bookingNumberTextField.setColumns(1);
        passportNumberTextField.setColumns(1);
        fullNameTextField.setColumns(1);
        othersSpecialAccommodationTextField.setColumns(1);
    }

    /** Sets the checkInViewPagingIndex. */
    public void setCheckInViewPagingIndex(int checkInViewPagingIndex) {
        this.checkInViewPagingIndex = checkInViewPagingIndex;
    }
    /** The method to get the size of <LinkedList> bagPartialViews.*/
    public int getNumberOfBagPartialViews() {
        return bagPartialViews.size();
    }

    /** Clears the view. */
    private void clearView() {
        checkInViewPanel.removeAll();
    }

    /** Resets the view from all user inputs. */
    public void resetView() {
        // Removes all the bagPartialViews.
        bagPartialViews = new LinkedList<>();

        // Sets all the user input to nothing.
        bookingNumberTextField.setText("");
        passportNumberTextField.setText("");
        fullNameTextField.setText("");
        othersSpecialAccommodationTextField.setText("");


        setWheelchairCheckBoxSelected(false);
        setDeafnessCheckBoxSelected(false);
        setBlindnessCheckBoxSelected(false);
        setOthersSpecialAccommodationCheckBoxSelected(false);
        setOthersSpecialAccommodationTextFieldEnabled(false);
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

        JPanel panelForTextFieldAndBagPartialView = new JPanel(new GridBagLayout());
        int horizontalSizeOfBaggageButton = 200;
        int verticalSizeOfBaggageButton = 8;

        JPanel textFieldPanel = setupViewTextFieldPanel();
        JPanel panelForSpecialAccommodationInput = setupPanelForSpecialAccommodationInput();

        GridBagConstraints constraintsForTextFieldPanel = new GridBagConstraints();
        constraintsForTextFieldPanel.gridy = 0;
        constraintsForTextFieldPanel.gridx = 0;
        constraintsForTextFieldPanel.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints constraintsForPanelForSpecialAccommodationInput= new GridBagConstraints();
        constraintsForPanelForSpecialAccommodationInput.gridy = 1;
        constraintsForPanelForSpecialAccommodationInput.gridx = 0;
        constraintsForPanelForSpecialAccommodationInput.insets = new Insets(0,0,20,0);

        GridBagConstraints constraintsForAddBaggageButton = new GridBagConstraints();
        constraintsForAddBaggageButton.gridy = 2;
        constraintsForAddBaggageButton.ipadx = horizontalSizeOfBaggageButton;
        constraintsForAddBaggageButton.ipady = verticalSizeOfBaggageButton;

        panelForTextFieldAndBagPartialView.add(textFieldPanel, constraintsForTextFieldPanel);
        panelForTextFieldAndBagPartialView.add(panelForSpecialAccommodationInput, constraintsForPanelForSpecialAccommodationInput);
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


        GridBagConstraints constraintsForScrollPane = new GridBagConstraints();
        constraintsForScrollPane.gridx = 0;
        constraintsForScrollPane.gridy = 0;
        constraintsForScrollPane.ipadx = 370;
        constraintsForScrollPane.ipady = 300;


        JPanel buttonPanel = setupViewButtonPanel();

        GridBagConstraints constraintsForButtonPanel = new GridBagConstraints();
        constraintsForButtonPanel.gridy = 1;
        constraintsForButtonPanel.anchor = GridBagConstraints.PAGE_END;

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

    /** Sets invalidBookingID. */
    public void setInvalidBookingID(boolean invalidBookingID) {
        this.invalidBookingID = invalidBookingID;
    }

    /** Sets warnEmptyBookingNumberInput. */
    public void setWarnEmptyBookingNumberInput(boolean warnEmptyBookingNumberInput) {
        this.warnEmptyBookingNumberInput = warnEmptyBookingNumberInput;
    }

    /** Sets warnEmptyPassportNumberInput. */
    public void setWarnEmptyPassportNumberInput(boolean warnEmptyPassportNumberInput) {
        this.warnEmptyPassportNumberInput  = warnEmptyPassportNumberInput;
    }

    /** Sets warnEmptyFullNameInput. */
    public void setWarnEmptyFullNameInput(boolean warnEmptyFullNameInput) {
        this.warnEmptyFullNameInput = warnEmptyFullNameInput;
    }

    /** Returns the checkInViewPanel. */
    public JPanel getCheckInViewPanel() {
        return checkInViewPanel;
    };

    /** Returns the bookingID from the text field set by the user. */
    public String getBookingNumberFromTextField() {
        return bookingNumberTextField.getText();
    }

    /** Sets the bookingIDTextField with String bookingID. */
    public void setBookingNumberTextField(String bookingNumber) {
        bookingNumberTextField.setText(bookingNumber);
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

    /** Returns true iff wheelchairCheckBox is selected. */
    public boolean isWheelChairCheckBoxSelected() {
        return wheelchairCheckBox.isSelected();
    }

    /** Sets wheelchairCheckBox. */
    public void setWheelchairCheckBoxSelected(boolean selected) {
        wheelchairCheckBox.setSelected(selected);
    }

    /** Returns true iff deafnessCheckBox is selected. */
    public boolean isDeafnessCheckBoxSelected() {
        return deafnessCheckbox.isSelected();
    }

    /** Sets deafnessCheckBox. */
    public void setDeafnessCheckBoxSelected(boolean selected) {
        deafnessCheckbox.setSelected(selected);
    }

    /** Returns true iff blindnessCheckBox is selected. */
    public boolean isBlindnessCheckBoxSelected() {
        return blindnessCheckBox.isSelected();
    }

    /** Sets blindnessCheckBox. */
    public void setBlindnessCheckBoxSelected(boolean selected) {
        blindnessCheckBox.setSelected(selected);
    }

    /** Returns true iff othersSpecialAccommodationCheckBox is selected. */
    public boolean isOthersSpecialAccommodationCheckBoxSelected() {
        return othersSpecialAccommodationCheckBox.isSelected();
    }

    /** Sets othersSpecialAccommodationCheckBox. */
    public void setOthersSpecialAccommodationCheckBoxSelected(boolean selected) {
        othersSpecialAccommodationCheckBox.setSelected(selected);
    }

    /** Returns the text in othersSpecialAccommodationTextField. */
    public String getOthersSpecialAccommodationTextField() {
        return othersSpecialAccommodationTextField.getText();
    }

    /** Sets the text in othersSpecialAccommodationTextField. */
    public void setOthersSpecialAccommodationTextField(String text) {
        othersSpecialAccommodationTextField.setText(text);
    }

    /** Sets whether the othersSpecialAccommodationTextField is enabled or not. */
    public void setOthersSpecialAccommodationTextFieldEnabled(boolean enabled) {
        othersSpecialAccommodationTextField.setEnabled(enabled);
    }

    /** Adds an ItemListener to checkboxForOthers. */
    public void addOthersSpecialAccommodationCheckBoxItemListener(ItemListener listenForOthersSpecialAccommodationCheckBox) {
        othersSpecialAccommodationCheckBox.addItemListener(listenForOthersSpecialAccommodationCheckBox);
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
        int horizontalSizeOfTextField = 180;
        int verticalSizeOfTextField = 8;

        JPanel textFieldPanel = new JPanel(new GridBagLayout());

        JLabel bookingNumberLabel = new JLabel("Booking Number");

        JLabel passportLabel = new JLabel("Passport Number");

        JLabel fullNameLabel = new JLabel("Full Name");



        GridBagConstraints constraintsForBookingNumberLabel = new GridBagConstraints();
        constraintsForBookingNumberLabel.gridx = 0;
        constraintsForBookingNumberLabel.gridy = 0;
        constraintsForBookingNumberLabel.insets = new Insets(15, 0, 0, 20);

        GridBagConstraints constraintsForBookingNumberTextField = new GridBagConstraints();
        constraintsForBookingNumberTextField.gridx = 1;
        constraintsForBookingNumberTextField.gridy = 0;
        constraintsForBookingNumberTextField.ipadx = horizontalSizeOfTextField;
        constraintsForBookingNumberTextField.ipady = verticalSizeOfTextField;
        constraintsForBookingNumberTextField.insets = new Insets(15, 0, 0, 0);

        if (invalidBookingID) {
            JLabel invalidBookingNumberLabel = new JLabel("Invalid Booking Number.");
            invalidBookingNumberLabel.setForeground(Color.RED);
            GridBagConstraints constraintsForInvalidBookingNumberLabel = new GridBagConstraints();
            constraintsForInvalidBookingNumberLabel.gridx = 1;
            constraintsForInvalidBookingNumberLabel.gridy = 1;

            textFieldPanel.add(invalidBookingNumberLabel, constraintsForInvalidBookingNumberLabel);
        }

        if (warnEmptyBookingNumberInput) {

            JLabel warningEmptyBookingNumberLabel = new JLabel("Empty Field.");
            warningEmptyBookingNumberLabel.setForeground(Color.RED);
            GridBagConstraints constraintsForWarningEmptyBookingNumberLabel = new GridBagConstraints();
            constraintsForWarningEmptyBookingNumberLabel.gridx = 1;
            constraintsForWarningEmptyBookingNumberLabel.gridy = 1;

            textFieldPanel.add(warningEmptyBookingNumberLabel, constraintsForWarningEmptyBookingNumberLabel);
        }

        GridBagConstraints constraintsForPassportLabel = new GridBagConstraints();
        constraintsForPassportLabel.gridx = 0;
        constraintsForPassportLabel.gridy = 2;
        constraintsForPassportLabel.insets = new Insets(15, 0, 0, 20);

        GridBagConstraints constraintsForPassportNumberTextField = new GridBagConstraints();
        constraintsForPassportNumberTextField.gridx = 1;
        constraintsForPassportNumberTextField.gridy = 2;
        constraintsForPassportNumberTextField.fill = GridBagConstraints.HORIZONTAL;
        constraintsForPassportNumberTextField.ipady = verticalSizeOfTextField;
        constraintsForPassportNumberTextField.insets = new Insets(15, 0, 0, 0);

        if (warnEmptyPassportNumberInput) {

            JLabel warningEmptyPassportNumberLabel = new JLabel("Empty Field.");
            warningEmptyPassportNumberLabel.setForeground(Color.RED);
            GridBagConstraints constraintsForWarningEmptyPassportNumberLabel = new GridBagConstraints();
            constraintsForWarningEmptyPassportNumberLabel.gridx = 1;
            constraintsForWarningEmptyPassportNumberLabel.gridy = 3;

            textFieldPanel.add(warningEmptyPassportNumberLabel, constraintsForWarningEmptyPassportNumberLabel);
        }

        // constraints.gridy = 4 for fullNameLabel and fullNameTextField
        // because JLabels representing error messages will occupy space
        // underneath JTextField passportNumberTextField.

        GridBagConstraints constraintsForFullNameLabel = new GridBagConstraints();
        constraintsForFullNameLabel.gridx = 0;
        constraintsForFullNameLabel.gridy = 4;
        constraintsForFullNameLabel.insets = new Insets(15, 0, 0, 20);

        GridBagConstraints constraintsForFullNameTextField = new GridBagConstraints();
        constraintsForFullNameTextField.gridx = 1;
        constraintsForFullNameTextField.gridy = 4;
        constraintsForFullNameTextField.fill = GridBagConstraints.HORIZONTAL;
        constraintsForFullNameTextField.ipady = verticalSizeOfTextField;
        constraintsForFullNameTextField.insets = new Insets(15, 0, 0, 0);

        if (warnEmptyFullNameInput) {

            JLabel warningEmptyFullNameLabel = new JLabel("Empty Field.");
            warningEmptyFullNameLabel.setForeground(Color.RED);
            GridBagConstraints constraintsForWarningEmptyFullNameLabel = new GridBagConstraints();
            constraintsForWarningEmptyFullNameLabel.gridx = 1;
            constraintsForWarningEmptyFullNameLabel.gridy = 5;

            textFieldPanel.add(warningEmptyFullNameLabel, constraintsForWarningEmptyFullNameLabel);
        }




        textFieldPanel.add(bookingNumberLabel, constraintsForBookingNumberLabel);
        textFieldPanel.add(bookingNumberTextField, constraintsForBookingNumberTextField);
        textFieldPanel.add(passportLabel, constraintsForPassportLabel);
        textFieldPanel.add(passportNumberTextField, constraintsForPassportNumberTextField);
        textFieldPanel.add(fullNameLabel, constraintsForFullNameLabel);
        textFieldPanel.add(fullNameTextField, constraintsForFullNameTextField);


        return textFieldPanel;
    }

    /** Sets up the panelForSpecialAccommodationInput. */
    private JPanel setupPanelForSpecialAccommodationInput() {

        JPanel panelForSpecialAccommodationInput = new JPanel(new GridBagLayout());

        int horizontalSizeOfSpecialNeedsTextField = 150;
        int verticalSizeOfSpecialNeedsTextField = 8;


        JLabel labelForSpecialNeeds = new JLabel("Any Special Needs?");

        GridBagConstraints constraintsForSpecialNeedsLabel = new GridBagConstraints();
        constraintsForSpecialNeedsLabel.gridy = 0;

        GridBagConstraints constraintsForWheelChairJCheckBox = new GridBagConstraints();
        constraintsForWheelChairJCheckBox.gridy = 1;
        constraintsForWheelChairJCheckBox.gridx = 0;

        GridBagConstraints constraintsForBlindnessJCheckBox = new GridBagConstraints();
        constraintsForBlindnessJCheckBox.gridy = 1;
        constraintsForBlindnessJCheckBox.gridx = 1;

        GridBagConstraints constraintsForDeafnessJCheckBox = new GridBagConstraints();
        constraintsForDeafnessJCheckBox.gridy = 1;
        constraintsForDeafnessJCheckBox.gridx = 2;

        GridBagConstraints constraintsForOthersJCheckBox = new GridBagConstraints();
        constraintsForOthersJCheckBox.gridy = 2;
        constraintsForOthersJCheckBox.gridx = 0;

        GridBagConstraints constraintsForOtherNeedsTextField = new GridBagConstraints();
        constraintsForOtherNeedsTextField.gridy = 2;
        constraintsForOtherNeedsTextField.gridx = 1;
        constraintsForOtherNeedsTextField.gridwidth = GridBagConstraints.REMAINDER;
        constraintsForOtherNeedsTextField.ipady = verticalSizeOfSpecialNeedsTextField;
        constraintsForOtherNeedsTextField.ipadx = horizontalSizeOfSpecialNeedsTextField;
        constraintsForOtherNeedsTextField.insets = new Insets(5,0,0,0);

        panelForSpecialAccommodationInput.add(labelForSpecialNeeds, constraintsForSpecialNeedsLabel);
        panelForSpecialAccommodationInput.add(wheelchairCheckBox, constraintsForWheelChairJCheckBox);
        panelForSpecialAccommodationInput.add(blindnessCheckBox, constraintsForBlindnessJCheckBox);
        panelForSpecialAccommodationInput.add(deafnessCheckbox, constraintsForDeafnessJCheckBox);
        panelForSpecialAccommodationInput.add(othersSpecialAccommodationCheckBox, constraintsForOthersJCheckBox);
        panelForSpecialAccommodationInput.add(othersSpecialAccommodationTextField, constraintsForOtherNeedsTextField);

        return panelForSpecialAccommodationInput;

    }

    /** Sets up JButtons and adjusting. */
    private JPanel setupViewButtonPanel() {
        JPanel informationPageButtonPanel = new JPanel(new GridBagLayout());
        JPanel panelForNextAndPreviousButton = new JPanel(new GridBagLayout());
        JPanel panelForOtherButtons = new JPanel(new GridBagLayout());
        JLabel labelForPassengerPageIndex = new JLabel("Passenger " + (checkInViewPagingIndex + 1) + " / " + kioskCheckInModel.getNumberOfPassengers());

        int horizontalSizeOfButton = 18;
        int verticalSizeOfButton = 4;

        GridBagConstraints constraintsForNextButton = new GridBagConstraints();
        GridBagConstraints constraintsForPreviousButton = new GridBagConstraints();
        GridBagConstraints constraintsForMainMenuButton = new GridBagConstraints();
        GridBagConstraints constraintsForBackToCheckInOptionView = new GridBagConstraints();
        GridBagConstraints constraintsForCheckInButton = new GridBagConstraints();
        GridBagConstraints constraintsForNextAndPreviousButtonPanel = new GridBagConstraints();
        GridBagConstraints constraintsForOtherButtonPanel = new GridBagConstraints();
        GridBagConstraints constraintsForPassengerIndexLabel = new GridBagConstraints();

        constraintsForNextButton.ipadx = horizontalSizeOfButton;
        constraintsForNextButton.ipady = verticalSizeOfButton;

        constraintsForPreviousButton.ipadx = horizontalSizeOfButton;
        constraintsForPreviousButton.ipady = verticalSizeOfButton;

        constraintsForBackToCheckInOptionView.ipadx = horizontalSizeOfButton;

        constraintsForMainMenuButton.ipadx = horizontalSizeOfButton;

        constraintsForCheckInButton.ipadx = horizontalSizeOfButton;
        constraintsForCheckInButton.ipady = verticalSizeOfButton;

        // Adjust insets for spacing between next and previous buttons
        constraintsForPreviousButton.insets = new Insets(0, 0, 0, 0); // Add spacing of 10 pixels to the right
        constraintsForNextButton.insets = new Insets(0, 10, 0, 0); // Add spacing of 10 pixels to the left
        constraintsForNextAndPreviousButtonPanel.insets = new Insets(15, 0, 10, 0);
        constraintsForBackToCheckInOptionView.insets = new Insets(0, 10, 0, 10);
        constraintsForPassengerIndexLabel.insets = new Insets(20,0,0,0);
        constraintsForOtherButtonPanel.insets = new Insets(5,0,0,0);

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

        constraintsForPassengerIndexLabel.gridy = 0;
        constraintsForPassengerIndexLabel.gridx = 0;

        constraintsForNextAndPreviousButtonPanel.gridy = 1;
        constraintsForNextAndPreviousButtonPanel.gridx = 0;

        constraintsForOtherButtonPanel.gridy = 2;
        constraintsForOtherButtonPanel.gridx = 0;

        constraintsForMainMenuButton.fill = GridBagConstraints.VERTICAL;
        constraintsForBackToCheckInOptionView.fill = GridBagConstraints.VERTICAL;

        // TODO: Dont add if at certain page.
        if (kioskCheckInModel.getNumberOfPassengers() > 0) {
            if (checkInViewPagingIndex > 0) {

                panelForNextAndPreviousButton.add(previousPassengerButton, constraintsForPreviousButton);
            }
            // TODO: Maybe change this.
            if (checkInViewPagingIndex < kioskCheckInModel.getNumberOfPassengers() - 1) {

                panelForNextAndPreviousButton.add(nextPassengerButton, constraintsForNextButton);
            }
        }

        panelForOtherButtons.add(mainMenuButton, constraintsForMainMenuButton);
        panelForOtherButtons.add(checkInKioskButton, constraintsForBackToCheckInOptionView);
        panelForOtherButtons.add(checkInButton, constraintsForCheckInButton);

        informationPageButtonPanel.add(labelForPassengerPageIndex, constraintsForPassengerIndexLabel);
        informationPageButtonPanel.add(panelForNextAndPreviousButton, constraintsForNextAndPreviousButtonPanel);
        informationPageButtonPanel.add(panelForOtherButtons, constraintsForOtherButtonPanel);

        return informationPageButtonPanel;
    }
}
