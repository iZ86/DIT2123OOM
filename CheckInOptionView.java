import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckInOptionView {
    
    /** Instance Variables */
    /** JPanel for the CheckInOptionView */
    private JPanel CheckInOptionViewPanel = new JPanel(new GridBagLayout());

    /** JButton that changes the view to the MainMenuView */
    private JButton backToMainMenuButton = new JButton("Main Menu");

    /** JButton that changes the view to  */
    private JButton groupButton = new JButton("Group");

    /** JButton that changes the view to  */
    private JButton singleButton = new JButton("Single");

    /** JSpinner to select the number of people */
    private JSpinner numberOfPeople = new JSpinner();   //In testing phase

    /** Creates a CheckInOptionView object */
    public CheckInOptionView(){
        setupCheckInOptionViewPanel();
    }

    /** GUI */
    /** Sets up the CheckInOptionViewPanel */
    private void setupCheckInOptionViewPanel(){
        GridBagConstraints backToMainMenuButtonConstraints = new GridBagConstraints();
        GridBagConstraints groupButtonConstraints = new GridBagConstraints();
        GridBagConstraints singleButtonConstraints = new GridBagConstraints();
        GridBagConstraints numberOfPeopleConstraints = new GridBagConstraints();

        // Set size for all components
        backToMainMenuButton.setPreferredSize(new Dimension(100, 40));
        groupButton.setPreferredSize(new Dimension(100, 40));
        singleButton.setPreferredSize(new Dimension(100, 40));
        numberOfPeople.setPreferredSize(new Dimension(100, 40));

        // Add Back To Main Menu button to panel
        backToMainMenuButtonConstraints.gridx = 0;
        backToMainMenuButtonConstraints.gridy = 0;
        backToMainMenuButtonConstraints.insets = new Insets(0, 0, 200, 350);
        CheckInOptionViewPanel.add(backToMainMenuButton, backToMainMenuButtonConstraints);

        // Add Single button to panel
        singleButtonConstraints.gridx = 0;
        singleButtonConstraints.gridy = 0;
        singleButtonConstraints.insets = new Insets(100, 0, 0, 175);
        CheckInOptionViewPanel.add(singleButton, singleButtonConstraints);
        
        // Add Group button to panel
        groupButtonConstraints.gridx = 0;
        groupButtonConstraints.gridy = 0;
        groupButtonConstraints.insets = new Insets(100, 175, 0, 0);
        CheckInOptionViewPanel.add(groupButton, groupButtonConstraints);

        // JSpinner for number of people
        numberOfPeopleConstraints.gridx = 0;
        numberOfPeopleConstraints.gridy = 0;
        numberOfPeopleConstraints.insets = new Insets(0 ,175, 0, 0);
        CheckInOptionViewPanel.add(numberOfPeople, numberOfPeopleConstraints);
    }

    /** Returns the CheckInOptionViewPanel */
    public JPanel getCheckInOptionViewPanel(){
        return CheckInOptionViewPanel;
    }

    /** Adds an ActionListener to the JButton mainMenuButton */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton){
        backToMainMenuButton.addActionListener(listenForMainMenuButton);
    }
}