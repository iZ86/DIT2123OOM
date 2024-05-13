import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* This class represents the view for choosing the check in options, this allows the user to choose between single and group check in. */
public class CheckInOptionView {
    
    /** JPanel for the CheckInOptionView */
    private JPanel CheckInOptionViewPanel = new JPanel(new GridBagLayout());

    /** JButton that changes the view to the MainMenuView */
    private JButton mainMenuButton = new JButton("Main Menu");

    /** JButton that changes the view to  */
    private JButton groupButton = new JButton("Group");

    /** JButton that changes the view to  */
    private JButton singleButton = new JButton("Single");

    /** JSpinner to select the number of passengers */
    private JSpinner numberOfPassengers = new JSpinner();

    /* Title */
    JLabel titleText = new JLabel("Check In Option");

    /** Creates a CheckInOptionView object */
    public CheckInOptionView(){
        setupCheckInOptionViewPanel();
    }

    /** Sets up the CheckInOptionViewPanel */
    private void setupCheckInOptionViewPanel(){
        GridBagConstraints mainMenuButtonConstraints = new GridBagConstraints();
        GridBagConstraints titleTextConstraints = new GridBagConstraints();
        GridBagConstraints groupButtonConstraints = new GridBagConstraints();
        GridBagConstraints singleButtonConstraints = new GridBagConstraints();
        GridBagConstraints numberOfPeopleConstraints = new GridBagConstraints();

        // Set size for all components
        mainMenuButton.setPreferredSize(new Dimension(100, 40));
        groupButton.setPreferredSize(new Dimension(100, 40));
        singleButton.setPreferredSize(new Dimension(100, 40));
        numberOfPassengers.setPreferredSize(new Dimension(100, 40));

        // Title
        titleText.setFont(new Font("Arial", Font.BOLD, 17));
        titleTextConstraints.ipadx = 0;
        titleTextConstraints.ipady = 0;
        titleTextConstraints.gridx = 0;
        titleTextConstraints.gridy = 0;
        titleTextConstraints.insets = new Insets(0, 0, 300, 0);
        CheckInOptionViewPanel.add(titleText, titleTextConstraints);

        // Add Back To Main Menu button to panel
        mainMenuButtonConstraints.ipadx = 0;
        mainMenuButtonConstraints.ipady = 0;
        mainMenuButtonConstraints.gridx = 0;
        mainMenuButtonConstraints.gridy = 0;
        mainMenuButtonConstraints.insets = new Insets(300, 0, 0, 0);
        CheckInOptionViewPanel.add(mainMenuButton, mainMenuButtonConstraints);

        // Add Single button to panel
        singleButtonConstraints.ipadx = 0;
        singleButtonConstraints.ipady = 0;
        singleButtonConstraints.gridx = 0;
        singleButtonConstraints.gridy = 0;
        singleButtonConstraints.insets = new Insets(0, 0, 50, 175);
        CheckInOptionViewPanel.add(singleButton, singleButtonConstraints);
        
        // Add Group button to panel
        groupButtonConstraints.ipadx = 0;
        groupButtonConstraints.ipady = 0;
        groupButtonConstraints.gridx = 0;
        groupButtonConstraints.gridy = 0;
        groupButtonConstraints.insets = new Insets(0, 175, 50, 0);
        CheckInOptionViewPanel.add(groupButton, groupButtonConstraints);

        // JSpinner for number of people
        numberOfPeopleConstraints.ipadx = 0;
        numberOfPeopleConstraints.ipady = 0;
        numberOfPeopleConstraints.gridx = 0;
        numberOfPeopleConstraints.gridy = 0;
        numberOfPeopleConstraints.insets = new Insets(0 ,175, 150, 0);
        CheckInOptionViewPanel.add(numberOfPassengers, numberOfPeopleConstraints);
    }

    /** Returns the CheckInOptionViewPanel */
    public JPanel getCheckInOptionViewPanel(){
        return CheckInOptionViewPanel;
    }

    /** Returns the number of passengers in JSpinners numberOfPassenger. */
    public int getNumberOfPassengersFromJSpinner() {
        return (Integer) numberOfPassengers.getValue();
    }

    /** Sets the number of passengers in JSpinner numberOfPassengers. */
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers.setValue(numberOfPassengers);
    }
    /** Adds an ActionListener to the JButton mainMenuButton */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton){
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }

    /** Adds an ActionListener to the JButton singleButton */
    public void addSingleButtonListener(ActionListener listenForSingleButton) {
        singleButton.addActionListener(listenForSingleButton);
    }

    /** Adds an ActionListener to the JButton groupButton */
    public void addGroupButtonListener(ActionListener listenForGroupButton) {
        groupButton.addActionListener(listenForGroupButton);
    }
}