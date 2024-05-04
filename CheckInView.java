import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;    

public class CheckInView {
    private JButton checkInButton = new JButton("Check In");
    private JButton addBagButton = new JButton("Add a Baggage");
    private JButton nextPassengerButton = new JButton("Next");
    private JButton previousPassengerButton = new JButton("Previous");
    private JPanel checkInViewPanel = new JPanel(new GridBagLayout());
    private KioskCheckInModel kioskCheckInModel;
    private JTextField bookingIDTextField;
    private JTextField passportNumberTextField;
    private JTextField fullNameTextField;
    private LinkedList <BagPartialView> bagPartialViews;
    private JButton checkInKioskButton = new JButton("Back To Last Page");
    private JButton mainMenuButton = new JButton("Main Menu");

    public CheckInView() {
        setupViewPanel();
    }

    public void createBagPartialView(ActionListener listenForCloseBagPartialViewButton) {

    }

    public void removeBagPartialView(int index) {

    }

    public JPanel getCheckInViewPanel() {
        return checkInViewPanel;
    };

    public String getBookingIDFromTextField() {
        return bookingIDTextField.getText();
    }

    public void setBookingIDTextField(String bookingID) {
        bookingIDTextField.setText(bookingID);;
    }

    public String getPassportNumberFromTextField() {
        return passportNumberTextField.getText();
    }

    public void setPassportNumberTextField(String passportNumber) {
        passportNumberTextField.setText(passportNumber);
    }

    public String getFullNameFromTextField() {
        return fullNameTextField.getText();
    }

    public void setFullNameTextField(String fullName) {
        fullNameTextField.setText(fullName);
    }

    public void addAddBagButtonListener(ActionListener listenForAddBagButton) {
        addBagButton.addActionListener(listenForAddBagButton);
    }

    public void addPreviousPassengerButtonListener(ActionListener listenForPreviousPassengerButton) {
        previousPassengerButton.addActionListener(listenForPreviousPassengerButton);
    }

    public void addNextPassengerButtonListener(ActionListener listenForNextButtonListener) {
        nextPassengerButton.addActionListener(listenForNextButtonListener);
    }

    public void addCheckInButtonListener(ActionListener listenForCheckInButton) {
        checkInButton.addActionListener(listenForCheckInButton);
    }

    public void addCheckInKioskButtonListener(ActionListener listenForCheckInKioskButton) {
        checkInKioskButton.addActionListener(listenForCheckInKioskButton);
    }

    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }

    private void setupViewPanel() {
        JPanel textFieldPanel = setupViewTextFieldPanel();
        JPanel buttonPanel = setupViewButtonPanel();

        GridBagConstraints constraintsForTextFieldPanel = new GridBagConstraints();
        GridBagConstraints constraintsForButtonPanel = new GridBagConstraints();

        constraintsForTextFieldPanel.gridy = 0;
        constraintsForTextFieldPanel.insets = new Insets(0, 0, 120, 0);
        
        constraintsForButtonPanel.gridy = 1;       
        constraintsForButtonPanel.insets = new Insets(20, 0, 0, 0);

        checkInViewPanel.add(textFieldPanel, constraintsForTextFieldPanel);
        checkInViewPanel.add(buttonPanel, constraintsForButtonPanel);
    }

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

    private JPanel setupViewButtonPanel() {
        JPanel informationPageButtonPanel = new JPanel(new GridBagLayout());

        int horizontalSizeOfButton = 20;
        int verticalSizeOfButton = 4;
        
        GridBagConstraints constraintsForAddBaggageButton = new GridBagConstraints();
        GridBagConstraints constraintsForNextAndPreviousButton = new GridBagConstraints();
        GridBagConstraints constraintsForMainMenuButton = new GridBagConstraints();
        GridBagConstraints constraintsForBackToCheckInOptionView = new GridBagConstraints();
        GridBagConstraints constraintsForCheckInButton = new GridBagConstraints();
        constraintsForAddBaggageButton.gridx = 0;
        constraintsForAddBaggageButton.gridy = 0;

        constraintsForAddBaggageButton.ipadx = horizontalSizeOfButton;
        constraintsForAddBaggageButton.ipady = verticalSizeOfButton;

        constraintsForNextAndPreviousButton.ipadx = horizontalSizeOfButton;
        constraintsForNextAndPreviousButton.ipady = verticalSizeOfButton;
        constraintsForNextAndPreviousButton.insets = new Insets(0, 10, 0, 10);
        
        constraintsForNextAndPreviousButton.gridy = 1;
        constraintsForCheckInButton.gridy = 1;
        constraintsForMainMenuButton.ipadx = horizontalSizeOfButton;
        constraintsForMainMenuButton.ipady = verticalSizeOfButton;

        constraintsForBackToCheckInOptionView.ipadx = horizontalSizeOfButton;
        constraintsForBackToCheckInOptionView.ipady = verticalSizeOfButton;

        constraintsForCheckInButton.ipadx = horizontalSizeOfButton;
        constraintsForCheckInButton.ipady = verticalSizeOfButton;

        informationPageButtonPanel.add(addBagButton, constraintsForAddBaggageButton);
        informationPageButtonPanel.add(previousPassengerButton, constraintsForNextAndPreviousButton);
        informationPageButtonPanel.add(nextPassengerButton, constraintsForNextAndPreviousButton);
        informationPageButtonPanel.add(checkInButton, constraintsForCheckInButton);
        informationPageButtonPanel.add(mainMenuButton, constraintsForMainMenuButton);
        informationPageButtonPanel.add(checkInKioskButton, constraintsForBackToCheckInOptionView);
      
        

        return informationPageButtonPanel;
    }

    
    
}