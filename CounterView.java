import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* This class represents the view for showing the user their designated physical counter, after choosing to check in physically. */
public class CounterView {
    /* JPanel for the CounterView. */
    private JPanel counterViewPanel = new JPanel(new GridBagLayout());

    /** The model that holds the staff and counter information. */
    private CounterModel counterModel;

    /** JButton that changes the view to the MainMenuView. */
    private JButton mainMenuButton = new JButton("Main Menu");




    /** Creates a CounterView object. */
    public CounterView(CounterModel counterModel) {
        this.counterModel = counterModel;
        setupViewPanel();
    }

    /** Sets up the counterViewPanel. */
    private void setupViewPanel() {

        // Staff and counter information
        JLabel counterNumberLabel = new JLabel(String.format("Counter: %d", counterModel.getCounter()));
        JLabel staffIDLabel = new JLabel("Staff ID: " + counterModel.getStaffID());
        JLabel staffNameLabel = new JLabel("Staff Name: " + counterModel.getStaffName());
        JLabel staffRoleLabel = new JLabel("Staff Role: " + counterModel.getStaffRole());


        GridBagConstraints constraintsForTitleLabel = new GridBagConstraints();
        GridBagConstraints constraintsForMainMenuButton = new GridBagConstraints();
        GridBagConstraints constraintsForCounterNumberLabel = new GridBagConstraints();
        GridBagConstraints constraintsForStaffNameLabel = new GridBagConstraints();
        GridBagConstraints constraintsForStaffRoleLabel = new GridBagConstraints();
        GridBagConstraints constraintsForStaffIDLabel = new GridBagConstraints();

        int widthOfButton = 30;
        int heightOfButton = 15;


        // Title
        JLabel titleLabel = new JLabel("Check In Counter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 17));
        constraintsForTitleLabel.gridx = 0;
        constraintsForTitleLabel.gridy = 0;
        counterViewPanel.add(titleLabel, constraintsForTitleLabel);

        // Add counter number to panel
        constraintsForCounterNumberLabel.gridx = 0;
        constraintsForCounterNumberLabel.gridy = 1;
        constraintsForCounterNumberLabel.insets = new Insets(30, 0, 0, 0);
        counterViewPanel.add(counterNumberLabel, constraintsForCounterNumberLabel);

        // Add staff ID to panel
        constraintsForStaffIDLabel.gridx = 0;
        constraintsForStaffIDLabel.gridy = 2;
        constraintsForStaffIDLabel.insets = new Insets(30, 0, 0, 0);
        counterViewPanel.add(staffIDLabel, constraintsForStaffIDLabel);

        // Add staff name to panel
        constraintsForStaffNameLabel.gridx = 0;
        constraintsForStaffNameLabel.gridy = 3;
        constraintsForStaffNameLabel.insets = new Insets(30, 0, 0, 0);
        counterViewPanel.add(staffNameLabel, constraintsForStaffNameLabel);
    
        // Add staff role to panel
        constraintsForStaffRoleLabel.gridx = 0;
        constraintsForStaffRoleLabel.gridy = 4;
        constraintsForStaffRoleLabel.insets = new Insets(30, 0, 0, 0);
        counterViewPanel.add(staffRoleLabel, constraintsForStaffRoleLabel);
        


        // Add Back To Main Menu button to panel
        constraintsForMainMenuButton.ipadx = widthOfButton;
        constraintsForMainMenuButton.ipady = heightOfButton;
        constraintsForMainMenuButton.gridx = 0;
        constraintsForMainMenuButton.gridy = 5;
        constraintsForMainMenuButton.insets = new Insets(30, 0, 0, 0);
        counterViewPanel.add(mainMenuButton, constraintsForMainMenuButton);
    }

    /** Clears the view. */
    private void clearView() {
        counterViewPanel.removeAll();
    }

    /** Updates the view. */
    public void updateView() {
        clearView();
        setupViewPanel();
        counterViewPanel.revalidate();
        counterViewPanel.repaint();
    }

    /** Returns the counterViewPanel. */
    public JPanel getCounterViewPanel() {
        return counterViewPanel;
    }

    /** Adds an ActionListener to the JButton mainMenuButton. */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }
}
