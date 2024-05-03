import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;    

public class CheckInView {
    private JButton checkInButton = new JButton("Check In");
    private JButton addBagButton = new JButton("Add a Baggage");
    private BasicArrowButton nextPassengerButton = new BasicArrowButton(BasicArrowButton.EAST);
    private BasicArrowButton previousPassengerButton = new BasicArrowButton(BasicArrowButton.WEST);
    private JPanel checkInViewPanel = new JPanel(new GridBagLayout());
    private KioskCheckInModel kioskCheckInModel;
    private JTextField bookingIDTextField;
    private JTextField passportNumberTextField;
    private JTextField fullNameTextField;
    private LinkedList <JPanel> bagPartialViewPanels;
    private JButton checkInOptionViewButton;
    private JButton mainMenuButton;

    public CheckInView(KioskCheckInModel kioskCheckInModel) {
        setupViewPanel();
    }

    public void createBagPartialViewPanel(ActionListener listenForCloseBagPartialViewButton) {

    }

    public void removeBagPartialViewPanel(int index) {

    }

    public JPanel getCheckViewPanel() {
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

    private void setupViewPanel() {
        JPanel textFieldPanel = setupViewTextFieldPanel();
        JPanel buttonPanel = setupViewButtonPanel();

        GridBagConstraints constraintsForTextFieldPanel = new GridBagConstraints();
        GridBagConstraints constraintsForButtonPanel = new GridBagConstraints();

        constraintsForTextFieldPanel.gridy = 0;
        constraintsForTextFieldPanel.insets = new Insets(0, 0, 150, 0);
        
        constraintsForButtonPanel.gridy = 1;       
        constraintsForButtonPanel.insets = new Insets(20, 0, 0, 0);

        checkInViewPanel.add(textFieldPanel, constraintsForTextFieldPanel);
        checkInViewPanel.add(buttonPanel, constraintsForButtonPanel);
    }

    private JPanel setupViewTextFieldPanel() {
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
        gridBagConstraintsForTextFields.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraintsForTextFields.insets = new Insets(0, 0, 15, 0);
        
        textFieldPanel.add(passportLabel, gridBagConstraintsForLabels);
        textFieldPanel.add(passportNumberTextField, gridBagConstraintsForTextFields);
        textFieldPanel.add(bookingNumberLabel, gridBagConstraintsForLabels);
        textFieldPanel.add(bookingIDTextField, gridBagConstraintsForTextFields);
        textFieldPanel.add(fullNameLabel, gridBagConstraintsForLabels);
        textFieldPanel.add(fullNameTextField, gridBagConstraintsForTextFields);

        textFieldPanel.setPreferredSize(new Dimension(360, 150)); 

        return textFieldPanel;
    }

    private JPanel setupViewButtonPanel() {
        JPanel informationPageButtonPanel = new JPanel(new GridBagLayout());

        int horizontalSizeOfArrowButton = 20;
        int verticalSizeOfArrowButton = 20;
        
        GridBagConstraints constraintsForAddBaggageButton = new GridBagConstraints();
        GridBagConstraints constraintsForLeftAndRightArrowButton = new GridBagConstraints();
        constraintsForAddBaggageButton.gridx = 0;

       
        constraintsForLeftAndRightArrowButton.ipadx = horizontalSizeOfArrowButton;
        constraintsForLeftAndRightArrowButton.ipady = verticalSizeOfArrowButton;
        constraintsForLeftAndRightArrowButton.insets = new Insets(0, 15, 0, 15);

        informationPageButtonPanel.add(addBagButton, constraintsForAddBaggageButton);
        informationPageButtonPanel.add(previousPassengerButton, constraintsForLeftAndRightArrowButton);
        informationPageButtonPanel.add(nextPassengerButton, constraintsForLeftAndRightArrowButton);
        informationPageButtonPanel.add(checkInButton);

        return informationPageButtonPanel;
    }

    
    
}