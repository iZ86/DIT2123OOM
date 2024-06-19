import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/** This class represents the view for users to check in. */
public class CheckInView {

    /** CSS used to wrap String in JLabels for questions. */
    private static final String CSSFORMAT = "<html><body style='width: %1spx'>%1s";
    /** CSS used to wrap String in JLabels for warnAlreadyCheckedInBookingNumber. */
    private static final String CSSFORMAT1= "<html><body style='width: %1spx; text-align: center;'>%1s";
    /** JPanel for the CheckInView. */
    private JPanel checkInViewPanel = new JPanel(new GridBagLayout());
    /** The model that holds the passenger and their bag data. */
    private KioskCheckInModel kioskCheckInModel;
    /** The text field where user enters their bookingNumber. */
    private JTextField bookingNumberTextField = new JTextField();
    /** The text field where user enters their passport number. */
    private JTextField passportNumberTextField = new JTextField();
    /** The text field where the user enters their full name. */
    private JTextField fullNameTextField = new JTextField();
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
     * An error will be shown, if there is a Passenger information not filled
     * or invalid bookingNumber.
     */
    private JButton checkInButton = new JButton("Check In");
    /** JButton that changes the view back to CheckInOptionView. */
    private JButton checkInKioskButton = new JButton("Last Page");
    /** JButton that changes the view to the MainMenuView. */
    private JButton mainMenuButton = new JButton("Main Menu");
    /** Check Box for Wheelchair Option. */
    private JCheckBox wheelchairCheckBox = new JCheckBox("Wheelchair");
    /** Check Box for Blindness Option. */
    private JCheckBox blindnessCheckBox = new JCheckBox("Blindness");
    /** Check Bo for Deafness Option. */
    private JCheckBox deafnessCheckbox = new JCheckBox("Deafness");
    /** Check Box for Others Option. */
    private JCheckBox othersSpecialAccommodationCheckBox = new JCheckBox("Others");
    /** Text Field to let user enter if user choose OTHERS Option. */
    private JTextField othersSpecialAccommodationTextField = new JTextField();
    /** checkInViewPagingIndex, used to show the page that the user is at. */
    private int checkInViewPagingIndex;
    /** True if there should be a warning prompt for empty invalid booking number in bookingNumberTextField. */
    private boolean warnInvalidBookingNumber;
    /** True if there should be a warning prompt for empty booking number in bookingNumberTextField. */
    private boolean warnEmptyBookingNumberInput;
    /** True if there should be a prompt for already checkedin bookingNumber. */
    private boolean warnAlreadyCheckedInBookingNumber;
    /** True if there should be a warning prompt for empty invalid passport number in passportNumberTextField. */
    private boolean warnInvalidPassportNumber;
    /** True if there should be a warning prompt for empty passport number in passportNumberTextField. */
    private boolean warnEmptyPassportNumberInput;
    /** True if there should be a warning prompt for empty invalid full name in fullNameTextField. */
    private boolean warnInvalidFullName;
    /** True if there should be a warning prompt for empty full name in fullNameTextField. */
    private boolean warnEmptyFullNameInput;
    /** Yes radio buttons for every bag check in question. */
    private JRadioButton[] answerYesRadioButtons = new JRadioButton[6];
    /** No radio buttons for every bag check in question. */
    private JRadioButton[] answerNoRadioButtons = new JRadioButton[6];
    /** Button group that contains both answerYesRadioButton and answerNoRadioButton. */
    private ButtonGroup[] buttonGroup = new ButtonGroup[6];
    /** JSpinner to select the number of passengers */
    private JSpinner numberOfBagsSpinner;
    /** True if there should be a warning prompt for unanswered questions. */
    private boolean[] warnUnansweredQuestions = new boolean[6];
    /** True if there should be a warning prompt for the same booking number. */
    private boolean warnSameBookingNumber;

    /** Creates a CheckInView object with a model KioskCheckInModel kioskCheckInModel */
    public CheckInView(KioskCheckInModel kioskCheckInModel) {
        this.kioskCheckInModel = kioskCheckInModel;

        // This is used to identify the radio buttons, when pressed for,
        // The ItemListener that will be added to them.
        for (int i = 0; i < answerYesRadioButtons.length; i++) {
            answerYesRadioButtons[i] = new JRadioButton("Yes");
            answerNoRadioButtons[i] = new JRadioButton("No");
            buttonGroup[i] = new ButtonGroup();
            buttonGroup[i].add(answerYesRadioButtons[i]);
            buttonGroup[i].add(answerNoRadioButtons[i]);
            answerYesRadioButtons[i].putClientProperty("index", i);
            answerYesRadioButtons[i].putClientProperty("answer", "Yes");
            answerNoRadioButtons[i].putClientProperty("index", i);
            answerNoRadioButtons[i].putClientProperty("answer", "No");
        }


        // Set limiter on spinner.
        SpinnerNumberModel numberOfBagsSpinnerModel = new SpinnerNumberModel(0, 0, 4, 1);
        numberOfBagsSpinner = new JSpinner(numberOfBagsSpinnerModel);

        // Won't resize when there are text in them.
        bookingNumberTextField.setColumns(1);
        passportNumberTextField.setColumns(1);
        fullNameTextField.setColumns(1);
        othersSpecialAccommodationTextField.setColumns(1);

        setupViewPanel();
    }

    /** Returns the checkInViewPagingIndex. */
    public int getCheckInViewPagingIndex() {
        return checkInViewPagingIndex;
    }

    /** Sets the checkInViewPagingIndex. */
    public void setCheckInViewPagingIndex(int checkInViewPagingIndex) {
        this.checkInViewPagingIndex = checkInViewPagingIndex;
    }

    /** Clears the view. */
    private void clearView() {
        checkInViewPanel.removeAll();
    }

    /** Resets the view from all user inputs and removes all warnings. */
    public void resetView() {

        // Sets all the user input to nothing.
        bookingNumberTextField.setText("");
        passportNumberTextField.setText("");
        fullNameTextField.setText("");
        othersSpecialAccommodationTextField.setText("");
        setNumberOfBagsSpinner(0);

        setWheelchairCheckBoxSelected(false);
        setDeafnessCheckBoxSelected(false);
        setBlindnessCheckBoxSelected(false);
        setOthersSpecialAccommodationCheckBoxSelected(false);
        setOthersSpecialAccommodationTextFieldEnabled(false);

        for (int i = 0; i < kioskCheckInModel.getNumberOfBagCheckInQuestions(); i++) {
            setQuestionAnswerRadioButtonsEnabled(i, false);
            setBagCheckInQuestionAnswer(i, 0);
            setWarnUnansweredQuestions(i, false);
        }

        setWarnInvalidBookingNumber(false);
        setWarnEmptyBookingNumberInput(false);
        setWarnAlreadyCheckedInBookingNumber(false);
        setWarnInvalidPassportNumber(false);
        setWarnEmptyPassportNumberInput(false);
        setWarnInvalidFullName(false);
        setWarnEmptyFullNameInput(false);
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

        JPanel contentPanel = new JPanel(new GridBagLayout());

        JPanel textFieldPanel = setupViewTextFieldPanel();
        JPanel specialAccommodationInputPanel = setupSpecialAccommodationInputPanel();
        JPanel bagCheckInQuestionsPanel = setupBagCheckInQuestionsPanel();
        JPanel numberOfBagsSpinnerPanel = setupNumberOfBagsSpinnerPanel();

        // Text Field Panel
        GridBagConstraints constraintsForTextFieldPanel = new GridBagConstraints();
        constraintsForTextFieldPanel.gridx = 0;
        constraintsForTextFieldPanel.gridy = 0;
        constraintsForTextFieldPanel.insets = new Insets(0, 0, 20, 0);

        // Special Accommodation inputs.
        GridBagConstraints constraintsForSpecialAccommodationInputPanel = new GridBagConstraints();
        constraintsForSpecialAccommodationInputPanel.gridx = 0;
        constraintsForSpecialAccommodationInputPanel.gridy = 1;
        constraintsForSpecialAccommodationInputPanel.insets = new Insets(0,0,20,0);

        // Number Of Bags Spinner.
        GridBagConstraints constraintsForNumberOfBagsSpinnerPanel = new GridBagConstraints();
        constraintsForNumberOfBagsSpinnerPanel.gridx = 0;
        constraintsForNumberOfBagsSpinnerPanel.gridy = 2;
        constraintsForNumberOfBagsSpinnerPanel.insets = new Insets(0,0,20,0);

        // Questions panel.
        GridBagConstraints constraintsForBagCheckInQuestionsPanel = new GridBagConstraints();
        constraintsForBagCheckInQuestionsPanel.gridx = 0;
        constraintsForBagCheckInQuestionsPanel.gridy = 3;


        contentPanel.add(textFieldPanel, constraintsForTextFieldPanel);
        contentPanel.add(specialAccommodationInputPanel, constraintsForSpecialAccommodationInputPanel);
        contentPanel.add(numberOfBagsSpinnerPanel, constraintsForNumberOfBagsSpinnerPanel);
        contentPanel.add(bagCheckInQuestionsPanel, constraintsForBagCheckInQuestionsPanel);

        // Wrapper used for scrollPane
        JPanel wrapperPanel = new JPanel(new BorderLayout());

        wrapperPanel.add(contentPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);



        GridBagConstraints constraintsForScrollPane = new GridBagConstraints();
        constraintsForScrollPane.gridx = 0;
        constraintsForScrollPane.gridy = 0;

        constraintsForScrollPane.ipadx = 370;
        constraintsForScrollPane.ipady = 300;

        // Buttons
        JPanel buttonPanel = setupViewButtonPanel();

        GridBagConstraints constraintsForButtonPanel = new GridBagConstraints();
        constraintsForButtonPanel.gridy = 1;



        checkInViewPanel.add(scrollPane, constraintsForScrollPane);
        checkInViewPanel.add(buttonPanel, constraintsForButtonPanel);
    }

    /** Sets warnInvalidBookingNumber. */
    public void setWarnInvalidBookingNumber(boolean warnInvalidBookingNumber) {
        this.warnInvalidBookingNumber = warnInvalidBookingNumber;
    }

    /** Sets warnEmptyBookingNumberInput. */
    public void setWarnEmptyBookingNumberInput(boolean warnEmptyBookingNumberInput) {
        this.warnEmptyBookingNumberInput = warnEmptyBookingNumberInput;
    }

    /** Sets warnAlreadyCheckedInBookingNumber. */
    public void setWarnAlreadyCheckedInBookingNumber(boolean warnAlreadyCheckedInBookingNumber) {
        this.warnAlreadyCheckedInBookingNumber = warnAlreadyCheckedInBookingNumber;
    }

    /** Sets warnInvalidPassportNumber. */
    public void setWarnInvalidPassportNumber(boolean warnInvalidPassportNumber) {
        this.warnInvalidPassportNumber = warnInvalidPassportNumber;
    }

    /** Sets warnEmptyPassportNumberInput. */
    public void setWarnEmptyPassportNumberInput(boolean warnEmptyPassportNumberInput) {
        this.warnEmptyPassportNumberInput  = warnEmptyPassportNumberInput;
    }

    public void setWarnInvalidFullName(boolean warnInvalidFullName) {
        this.warnInvalidFullName = warnInvalidFullName;
    }
    /** Sets warnEmptyFullNameInput. */
    public void setWarnEmptyFullNameInput(boolean warnEmptyFullNameInput) {
        this.warnEmptyFullNameInput = warnEmptyFullNameInput;
    }

    /** Sets warnsUnansweredQuestions[index]. */
    public void setWarnUnansweredQuestions(int index, boolean warnUnansweredQuestion) {
        warnUnansweredQuestions[index] = warnUnansweredQuestion;
    }

    /** Sets warnSameBookingNumber. */
    public void setWarnSameBookingNumber(boolean warnSameBookingNumber) {
        this.warnSameBookingNumber = warnSameBookingNumber;
    }

    /** Returns the checkInViewPanel. */
    public JPanel getCheckInViewPanel() {
        return checkInViewPanel;
    };

    /** Returns the bookingNumber from the text field set by user. */
    public String getBookingNumberFromTextField() {
        return bookingNumberTextField.getText();
    }

    /** Sets the bookingNumberTextField with String bookingNumber. */
    public void setBookingNumberTextField(String bookingNumber) {
        bookingNumberTextField.setText(bookingNumber);
    }

    /** Returns the passport number from the text field set by user. */
    public String getPassportNumberFromTextField() {
        return passportNumberTextField.getText();
    }

    /** Sets the passportNumberTextField with String passportNumber. */
    public void setPassportNumberTextField(String passportNumber) {
        passportNumberTextField.setText(passportNumber);
    }

    /** Returns the full name from the text field set by user. */
    public String getFullNameFromTextField() {
        return fullNameTextField.getText();
    }

    /** Sets the fullNameTextField with String fullName. */
    public void setFullNameTextField(String fullName) {
        fullNameTextField.setText(fullName);
    }

    /** Returns the number of bags from the spinner set by user. */
    public int getNumberOfBagsFromSpinner() {
        return (Integer) numberOfBagsSpinner.getValue();
    }

    /** Sets the numberOfBagsSpinner with int numberOfBags. */
    public void setNumberOfBagsSpinner(int numberOfBags) {
        this.numberOfBagsSpinner.setValue(numberOfBags);
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

    /** Sets whether the questionYesRadioButtons[index] and questionNoRadioButtons[index]
     * is enabled or not. */
    public void setQuestionAnswerRadioButtonsEnabled(int index, boolean enabled) {
        answerYesRadioButtons[index].setEnabled(enabled);
        answerNoRadioButtons[index].setEnabled(enabled);
    }

    /** Sets bagCheckInQuestionAnswer. */
    public void setBagCheckInQuestionAnswer(int index, int answer) {
        if (answer == 1) {
            answerYesRadioButtons[index].setSelected(true);
        } else if (answer == -1) {
            answerNoRadioButtons[index].setSelected(true);
        } else if (answer == 0) {
            buttonGroup[index].clearSelection();
        }
    }

    /** Returns 1 if the answer of the question at index is yes,
     * -1 if no.
     * Otherwise, 0 if both is not selected.
     */
    public int getBagCheckInQuestionAnswer(int index) {
        if (answerYesRadioButtons[index].isSelected()) {
            return 1;
        } else if (answerNoRadioButtons[index].isSelected()) {
            return -1;
        }
        return 0;
    }

    /** Adds an ItemListener to checkboxForOthers. */
    public void addOthersSpecialAccommodationCheckBoxItemListener(ItemListener listenForOthersSpecialAccommodationCheckBox) {
        othersSpecialAccommodationCheckBox.addItemListener(listenForOthersSpecialAccommodationCheckBox);
    }

    /** Adds an ItemListener to numberOfBagsSpinner. */
    public void addNumberOfBagsSpinnerListener(ChangeListener listenForNumberOfBagsSpinner) {
        numberOfBagsSpinner.addChangeListener(listenForNumberOfBagsSpinner);
    }

    /** Adds an itemListener to the 5th yes radio button. */
    public void addQuestionFiveYesRadioButtonListener(ItemListener listenForQuestionFiveYesRadioButton) {
        answerYesRadioButtons[4].addItemListener(listenForQuestionFiveYesRadioButton);
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

    /** Adds an ItemListener to all the answer radio buttons. */
    public void addQuestionAnswerRadioButtonListener(ItemListener listenForQuestionAnswerRadioButton) {
        for (int i = 0; i < kioskCheckInModel.getNumberOfBagCheckInQuestions(); i++) {
            answerYesRadioButtons[i].addItemListener(listenForQuestionAnswerRadioButton);
            answerNoRadioButtons[i].addItemListener(listenForQuestionAnswerRadioButton);
        }
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

        JLabel bookingNumberWarningLabel;

        if (warnAlreadyCheckedInBookingNumber) {
            bookingNumberWarningLabel = setupWarningJLabel("Booking Number already checked in");
        } else if (warnInvalidBookingNumber) {
            bookingNumberWarningLabel = setupWarningJLabel("Invalid Booking Number");
        } else if (warnEmptyBookingNumberInput) {
            bookingNumberWarningLabel = setupWarningJLabel("Empty Field");
        } else if (warnSameBookingNumber) {
            bookingNumberWarningLabel = setupWarningJLabel("Cannot check in the same booking number");
        } else {
            bookingNumberWarningLabel = null;
        }

        if (bookingNumberWarningLabel != null) {
            GridBagConstraints constraintsForWarningLabel = new GridBagConstraints();
            constraintsForWarningLabel.gridx = 1;
            constraintsForWarningLabel.gridy = 1;
            textFieldPanel.add(bookingNumberWarningLabel, constraintsForWarningLabel);
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

        JLabel passportNumberWarningLabel;

        if (warnInvalidPassportNumber) {
            passportNumberWarningLabel = setupWarningJLabel("Invalid Passport Number");
        } else if (warnEmptyPassportNumberInput) {
            passportNumberWarningLabel = setupWarningJLabel("Empty Field");
        } else {
            passportNumberWarningLabel = null;
        }

        if (passportNumberWarningLabel != null) {
            GridBagConstraints constraintsForPassportNumberWarningLabel = new GridBagConstraints();
            constraintsForPassportNumberWarningLabel.gridx = 1;
            constraintsForPassportNumberWarningLabel.gridy = 3;
            textFieldPanel.add(passportNumberWarningLabel, constraintsForPassportNumberWarningLabel);
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

        JLabel fullNameWarningLabel;

        if (warnInvalidFullName) {
            fullNameWarningLabel = setupWarningJLabel("Invalid Full Name");
        } else if (warnEmptyFullNameInput) {
            fullNameWarningLabel = setupWarningJLabel("Empty Field");
        } else {
            fullNameWarningLabel = null;
        }

        if (fullNameWarningLabel != null) {
            GridBagConstraints constraintsForFullNameWarningLabel = new GridBagConstraints();
            constraintsForFullNameWarningLabel.gridx = 1;
            constraintsForFullNameWarningLabel.gridy = 5;
            textFieldPanel.add(fullNameWarningLabel, constraintsForFullNameWarningLabel);
        }


        textFieldPanel.add(bookingNumberLabel, constraintsForBookingNumberLabel);
        textFieldPanel.add(bookingNumberTextField, constraintsForBookingNumberTextField);
        textFieldPanel.add(passportLabel, constraintsForPassportLabel);
        textFieldPanel.add(passportNumberTextField, constraintsForPassportNumberTextField);
        textFieldPanel.add(fullNameLabel, constraintsForFullNameLabel);
        textFieldPanel.add(fullNameTextField, constraintsForFullNameTextField);


        return textFieldPanel;
    }

    /** Sets up warning JLabel for every input. */
    private JLabel setupWarningJLabel(String warningMessage) {
        JLabel warningJLabel = new JLabel(String.format(CSSFORMAT1, 150, warningMessage));
        warningJLabel.setForeground(Color.RED);
        return warningJLabel;
    }

    /** Sets up the panelForSpecialAccommodationInput. */
    private JPanel setupSpecialAccommodationInputPanel() {

        JPanel specialAccommodationInputPanel = new JPanel(new GridBagLayout());

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

        specialAccommodationInputPanel.add(labelForSpecialNeeds, constraintsForSpecialNeedsLabel);
        specialAccommodationInputPanel.add(wheelchairCheckBox, constraintsForWheelChairJCheckBox);
        specialAccommodationInputPanel.add(blindnessCheckBox, constraintsForBlindnessJCheckBox);
        specialAccommodationInputPanel.add(deafnessCheckbox, constraintsForDeafnessJCheckBox);
        specialAccommodationInputPanel.add(othersSpecialAccommodationCheckBox, constraintsForOthersJCheckBox);
        specialAccommodationInputPanel.add(othersSpecialAccommodationTextField, constraintsForOtherNeedsTextField);

        return specialAccommodationInputPanel;

    }


    private JPanel setupNumberOfBagsSpinnerPanel() {
        JPanel numberOfBagsSpinnerPanel = new JPanel(new GridBagLayout());

        int horizontalSizeOfSpinner = 30;
        int verticalSizeOfSpinner = 10;


        JLabel labelForNumberOfBags = new JLabel("Number of bags");
        GridBagConstraints constraintsForNumberOfBagsLabel = new GridBagConstraints();
        constraintsForNumberOfBagsLabel.gridx = 0;
        constraintsForNumberOfBagsLabel.gridy = 0;

        GridBagConstraints constraintsForNumberOfBagsSpinner = new GridBagConstraints();
        constraintsForNumberOfBagsSpinner.gridx = 1;
        constraintsForNumberOfBagsSpinner.gridy = 0;
        constraintsForNumberOfBagsSpinner.ipadx = horizontalSizeOfSpinner;
        constraintsForNumberOfBagsSpinner.ipady = verticalSizeOfSpinner;

        numberOfBagsSpinnerPanel.add(labelForNumberOfBags, constraintsForNumberOfBagsLabel);
        numberOfBagsSpinnerPanel.add(numberOfBagsSpinner, constraintsForNumberOfBagsSpinner);


        return numberOfBagsSpinnerPanel;
    }

    private JPanel setupBagCheckInQuestionsPanel() {

        JPanel bagCheckInQuestionsPanel = new JPanel(new GridBagLayout());

        // Questions
        for (int i = 0; i < kioskCheckInModel.getNumberOfBagCheckInQuestions(); i ++) {
            JPanel bagCheckInQuestionPanel = new JPanel(new GridBagLayout());
            JPanel bagCheckInQuestionRadioButtonPanel = new JPanel(new GridBagLayout());
            JLabel bagCheckInQuestionLabel = new JLabel(String.format(CSSFORMAT, 250, kioskCheckInModel.getBagCheckInQuestions(i)));


            // Adding the Question answer checkboxes to bagCheckInQuestionCheckBoxPanel.


            GridBagConstraints constraintsForQuestionNoCheckBox = new GridBagConstraints();
            constraintsForQuestionNoCheckBox.gridx = 0;
            constraintsForQuestionNoCheckBox.gridy = 0;

            GridBagConstraints constraintsForQuestionYesCheckBox = new GridBagConstraints();
            constraintsForQuestionYesCheckBox.gridx = 1;
            constraintsForQuestionYesCheckBox.gridy = 0;

            bagCheckInQuestionRadioButtonPanel.add(answerNoRadioButtons[i], constraintsForQuestionNoCheckBox);
            bagCheckInQuestionRadioButtonPanel.add(answerYesRadioButtons[i], constraintsForQuestionYesCheckBox);

            // Adding the question label and the answer checkboxes to the bagCheckInQuestionPanel.
            GridBagConstraints constraintsForBagCheckInQuestionLabel = new GridBagConstraints();
            constraintsForBagCheckInQuestionLabel.gridx = 0;
            constraintsForBagCheckInQuestionLabel.gridy = 0;



            if (warnUnansweredQuestions[i]) {
                JLabel warnUnansweredQuestionLabel = new JLabel("Please answer this question!");
                warnUnansweredQuestionLabel.setForeground(Color.RED);
                GridBagConstraints constraintsForWarnUnansweredQuestionLabel = new GridBagConstraints();
                constraintsForWarnUnansweredQuestionLabel.gridx = 0;
                constraintsForWarnUnansweredQuestionLabel.gridy = 1;
                bagCheckInQuestionPanel.add(warnUnansweredQuestionLabel, constraintsForWarnUnansweredQuestionLabel);
            }


            GridBagConstraints constraintsForBagCheckInQuestionCheckBoxPanel = new GridBagConstraints();
            constraintsForBagCheckInQuestionCheckBoxPanel.gridx = 0;
            constraintsForBagCheckInQuestionCheckBoxPanel.gridy = 2;

            bagCheckInQuestionPanel.add(bagCheckInQuestionLabel, constraintsForBagCheckInQuestionLabel);
            bagCheckInQuestionPanel.add(bagCheckInQuestionRadioButtonPanel, constraintsForBagCheckInQuestionCheckBoxPanel);

            // Adding bagCheckInQuestionPanel to the checkInViewPanel.
            GridBagConstraints constraintsForBagCheckInQuestionPanel = new GridBagConstraints();
            constraintsForBagCheckInQuestionPanel.gridx = 0;
            constraintsForBagCheckInQuestionPanel.gridy = i;
            constraintsForBagCheckInQuestionPanel.insets = new Insets(0, 0 , 20, 0);

            bagCheckInQuestionsPanel.add(bagCheckInQuestionPanel, constraintsForBagCheckInQuestionPanel);
        }
        return bagCheckInQuestionsPanel;
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
        
        if (kioskCheckInModel.getNumberOfPassengers() > 0) {
            if (checkInViewPagingIndex > 0) {

                panelForNextAndPreviousButton.add(previousPassengerButton, constraintsForPreviousButton);
            }
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
