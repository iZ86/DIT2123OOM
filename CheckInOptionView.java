import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* This class represents the view for choosing the check in options, this allows the user to choose between single and group check in. */
public class CheckInOptionView {
    
    /** JPanel for the checkInOptionView */
    private JPanel checkInOptionViewPanel = new JPanel(new GridBagLayout());

    /** JButton that changes the view to the MainMenuView */
    private JButton mainMenuButton = new JButton("Main Menu");

    /** JButton that changes the view to  */
    private JButton groupButton = new JButton("Group");

    /** JButton that changes the view to  */
    private JButton singleButton = new JButton("Single");

    /** JSpinner to select the number of passengers */
    private JSpinner numberOfPassengers = new JSpinner();
    /** True if there should be warning prompt for minimum number of passengers. */
    private boolean warnMinimumNumberOfPassengers;


    /** Creates a CheckInOptionView object */
    public CheckInOptionView(){
        setupViewPanel();
    }

    /** Sets up the CheckInOptionViewPanel */
    private void setupViewPanel(){


        JLabel titleText = new JLabel("Check In Option");

        GridBagConstraints mainMenuButtonConstraints = new GridBagConstraints();
        GridBagConstraints titleTextConstraints = new GridBagConstraints();
        GridBagConstraints groupButtonConstraints = new GridBagConstraints();
        GridBagConstraints singleButtonConstraints = new GridBagConstraints();
        GridBagConstraints numberOfPeopleConstraints = new GridBagConstraints();

        int widthSizeOfButton = 20;
        int heightSizeOfButton = 10;
        int widthOfJSpanner = 65;
        int heightOfJSpanner = 15;

        // Title
        titleText.setFont(new Font("Arial", Font.BOLD, 17));
        titleTextConstraints.ipadx = widthSizeOfButton;
        titleTextConstraints.ipady = heightSizeOfButton;
        titleTextConstraints.gridx = 0;
        titleTextConstraints.gridy = 0;
        titleTextConstraints.gridwidth = 2;
        titleTextConstraints.insets = new Insets(0, 0, 0, 0);
        checkInOptionViewPanel.add(titleText, titleTextConstraints);

        // JSpinner for number of people
        numberOfPeopleConstraints.ipadx = widthOfJSpanner;
        numberOfPeopleConstraints.ipady = heightOfJSpanner;
        numberOfPeopleConstraints.gridx = 1;
        numberOfPeopleConstraints.gridy = 1;
        // Insets 15 to the left to follow the group button.
        numberOfPeopleConstraints.insets = new Insets(40 ,15, 0, 0);
        checkInOptionViewPanel.add(numberOfPassengers, numberOfPeopleConstraints);

        // Add Single button to panel
        singleButtonConstraints.ipadx = widthSizeOfButton;
        singleButtonConstraints.ipady = heightSizeOfButton;
        singleButtonConstraints.gridx = 0;
        singleButtonConstraints.gridy = 2;
        singleButtonConstraints.insets = new Insets(10, 0, 0, 15);
        checkInOptionViewPanel.add(singleButton, singleButtonConstraints);
        
        // Add Group button to panel
        groupButtonConstraints.ipadx = widthSizeOfButton;
        groupButtonConstraints.ipady = heightSizeOfButton;
        groupButtonConstraints.gridx = 1;
        groupButtonConstraints.gridy = 2;
        groupButtonConstraints.insets = new Insets(10, 15, 0, 0);
        checkInOptionViewPanel.add(groupButton, groupButtonConstraints);

        if (warnMinimumNumberOfPassengers) {
            JLabel warnMinimumNumberOfPassengersLabel = new JLabel("Minimum number is 2!");
            warnMinimumNumberOfPassengersLabel.setFont(new Font("Arial", Font.PLAIN, 8));

            warnMinimumNumberOfPassengersLabel.setForeground(Color.RED);
            GridBagConstraints constraintsForWarnMinimumNumberOfPassengersLabel = new GridBagConstraints();

            constraintsForWarnMinimumNumberOfPassengersLabel.gridx = 1;
            constraintsForWarnMinimumNumberOfPassengersLabel.gridy = 3;
            constraintsForWarnMinimumNumberOfPassengersLabel.insets = new Insets(0, 15, 0, 0);
            checkInOptionViewPanel.add(warnMinimumNumberOfPassengersLabel, constraintsForWarnMinimumNumberOfPassengersLabel);
        }

        // Add Back To Main Menu button to panel
        mainMenuButtonConstraints.ipadx = widthSizeOfButton;
        mainMenuButtonConstraints.ipady = heightSizeOfButton;
        mainMenuButtonConstraints.gridx = 0;
        mainMenuButtonConstraints.gridy = 4;
        mainMenuButtonConstraints.gridwidth = 2;
        mainMenuButtonConstraints.insets = new Insets(50, 0, 0, 0);
        checkInOptionViewPanel.add(mainMenuButton, mainMenuButtonConstraints);
    }

    /** Resets the view from all user inputs and removes all warnings. */
    public void resetView() {
        setNumberOfPassengers(2);
        setWarnMinimumNumberOfPassengers(false);
    }

    /** Clears the view. */
    private void clearView() {
        checkInOptionViewPanel.removeAll();
    }

    /** Updates the view. */
    public void updateView() {
        clearView();
        setupViewPanel();
        checkInOptionViewPanel.revalidate();
        checkInOptionViewPanel.repaint();
    }

    /** Sets warnMinimumNumberOfPassengers. */
    public void setWarnMinimumNumberOfPassengers(boolean warnMinimumNumberOfPassengers) {
        this.warnMinimumNumberOfPassengers = warnMinimumNumberOfPassengers;
    }
    /** Returns the CheckInOptionViewPanel */
    public JPanel getCheckInOptionViewPanel(){
        return checkInOptionViewPanel;
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