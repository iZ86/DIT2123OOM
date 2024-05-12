import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* This class represents the view for choosing the check in options, this allows the user to choose between single and group check in. */
public class CheckInOptionView {
    
    /** JPanel for the CheckInOptionView */
    private JPanel CheckInOptionViewPanel = new JPanel(new GridBagLayout());

    /** JButton that changes the view to the MainMenuView */
    private JButton backToMainMenuButton = new JButton("Main Menu");

    /** JButton that changes the view to  */
    private JButton groupButton = new JButton("Group");

    /** JButton that changes the view to  */
    private JButton singleButton = new JButton("Single");

    /** JSpinner to select the number of people */
    private JSpinner numberOfPeople = new JSpinner();

    /* Title */
    JLabel titleText = new JLabel("Check In Option");

    /** Creates a CheckInOptionView object */
    public CheckInOptionView(){
        setupCheckInOptionViewPanel();
    }

    /** Sets up the CheckInOptionViewPanel */
    private void setupCheckInOptionViewPanel(){
        GridBagConstraints backToMainMenuButtonConstraints = new GridBagConstraints();
        GridBagConstraints titleTextConstraints = new GridBagConstraints();
        GridBagConstraints groupButtonConstraints = new GridBagConstraints();
        GridBagConstraints singleButtonConstraints = new GridBagConstraints();
        GridBagConstraints numberOfPeopleConstraints = new GridBagConstraints();

        // Set size for all components
        backToMainMenuButton.setPreferredSize(new Dimension(100, 40));
        groupButton.setPreferredSize(new Dimension(100, 40));
        singleButton.setPreferredSize(new Dimension(100, 40));
        numberOfPeople.setPreferredSize(new Dimension(100, 40));

        // Title
        titleText.setFont(new Font("Arial", Font.BOLD, 17));
        titleTextConstraints.ipadx = 0;
        titleTextConstraints.ipady = 0;
        titleTextConstraints.gridx = 0;
        titleTextConstraints.gridy = 0;
        titleTextConstraints.insets = new Insets(0, 0, 300, 0);
        CheckInOptionViewPanel.add(titleText, titleTextConstraints);

        // Add Back To Main Menu button to panel
        backToMainMenuButtonConstraints.ipadx = 0;
        backToMainMenuButtonConstraints.ipady = 0;
        backToMainMenuButtonConstraints.gridx = 0;
        backToMainMenuButtonConstraints.gridy = 0;
        backToMainMenuButtonConstraints.insets = new Insets(300, 0, 0, 0);
        CheckInOptionViewPanel.add(backToMainMenuButton, backToMainMenuButtonConstraints);

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