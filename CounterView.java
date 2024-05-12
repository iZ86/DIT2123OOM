import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* This class represents the view for showing the user their designated physical counter, after choosing to check in physically. */
public class CounterView {
    /* JPanel for the CounterView. */
    private JPanel counterViewPanel = new JPanel(new GridBagLayout());

    /** The model that holds the staff and counter information. */
    private CounterModel CounterModel;

    /* JButton that changes the view to the MainMenuView. */
    private JButton backToMainMenuButton = new JButton("Main Menu");

    /* Creates a CounterView object. */
    public CounterView(CounterModel CounterModel) {
        this.CounterModel = CounterModel;
        setupViewPanel();
    }

    /* Sets up the counterViewPanel. */
    private void setupViewPanel() {
        /* Staff and Counter information */
        JLabel counterNumber = new JLabel(String.format("Counter: %d",CounterModel.getCounterNumber()));
        JLabel staffName = new JLabel("Staff Name: " + CounterModel.getStaffName());
        JLabel staffRole = new JLabel("Staff Role: " + CounterModel.getStaffRole());
        JLabel staffID = new JLabel(String.format("Staff ID: %d",CounterModel.getStaffID()));

        GridBagConstraints backToMainMenuButtonConstraints = new GridBagConstraints();
        GridBagConstraints counterNumberConstraints = new GridBagConstraints();
        GridBagConstraints staffNameConstraints = new GridBagConstraints();
        GridBagConstraints staffRoleConstraints = new GridBagConstraints();
        GridBagConstraints staffIDConstraints = new GridBagConstraints();

        // Add Back To Main Menu button to panel
        backToMainMenuButtonConstraints.ipadx = 0;
        backToMainMenuButtonConstraints.ipady = 0;
        backToMainMenuButtonConstraints.gridx = 0;
        backToMainMenuButtonConstraints.gridy = 0;
        backToMainMenuButtonConstraints.insets = new Insets(300, 0, 0, 0);
        counterViewPanel.add(backToMainMenuButton, backToMainMenuButtonConstraints);

        // Add counter number to panel
        counterNumberConstraints.ipadx = 0;
        counterNumberConstraints.ipady = 0;
        counterNumberConstraints.gridx = 0;
        counterNumberConstraints.gridy = 0;
        counterNumberConstraints.insets = new Insets(0, 0, 300, 0);
        counterViewPanel.add(counterNumber, counterNumberConstraints);

        // Add staff name to panel
        staffNameConstraints.ipadx = 0;
        staffNameConstraints.ipady = 0;
        staffNameConstraints.gridx = 0;
        staffNameConstraints.gridy = 0;
        staffNameConstraints.insets = new Insets(0, 0, 200, 0);
        counterViewPanel.add(staffName, staffNameConstraints);
    
        // Add staff role to panel
        staffRoleConstraints.ipadx = 0;
        staffRoleConstraints.ipady = 0;
        staffRoleConstraints.gridx = 0;
        staffRoleConstraints.gridy = 0;
        staffRoleConstraints.insets = new Insets(0, 0, 100, 0);
        counterViewPanel.add(staffRole, staffRoleConstraints);
        
        // Add staff ID to panel
        staffIDConstraints.ipadx = 0;
        staffIDConstraints.ipady = 0;
        staffIDConstraints.gridx = 0;
        staffIDConstraints.gridy = 0;
        staffIDConstraints.insets = new Insets(0, 0, 0, 0);
        counterViewPanel.add(staffID, staffIDConstraints);
    }

    /* Returns the counterViewPanel. */
    public JPanel getCounterViewPanel() {
        return counterViewPanel;
    }

    /* Adds an ActionListener to the JButton mainMenuButton. */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
        backToMainMenuButton.addActionListener(listenForMainMenuButton);
    }
}
