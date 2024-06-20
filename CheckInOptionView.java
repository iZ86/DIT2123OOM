
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/* This class represents the view for choosing the check in options, this allows the user to choose between single and group check in. */
public class CheckInOptionView {
    
    /** JPanel for the checkInOptionView */
    private final JPanel checkInOptionViewPanel = new JPanel(new GridBagLayout());

    /** JButton that changes the view to the MainMenuView */
    private final JButton mainMenuButton = new JButton("Main Menu");

    /** JButton that changes the view to  */
    private final JButton groupButton = new JButton("Group");

    /** JButton that changes the view to  */
    private final JButton singleButton = new JButton("Single");

    /** JSpinner to select the number of passengers */
    private final JSpinner numberOfPassengers = new JSpinner();
    /** True if there should be warning prompt for minimum number of passengers. */
    private boolean warnMinimumNumberOfPassengers;


    /** Creates a CheckInOptionView object */
    public CheckInOptionView() {
        setupViewPanel();
    }

    /** Sets up the CheckInOptionViewPanel */
    private void setupViewPanel() {

        GridBagConstraints titleConstraints = new GridBagConstraints();
        GridBagConstraints infoAreaConstraints = new GridBagConstraints();

        JPanel titlePanel = new JPanel(new GridBagLayout());

        JLabel titleLabel = new JLabel("Check-In Option", JLabel.CENTER);
        titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));

        GridBagConstraints constraintsForTitlePanel = new GridBagConstraints();
        constraintsForTitlePanel.gridx = 0;
        constraintsForTitlePanel.gridy = 0;
        constraintsForTitlePanel.insets = new Insets(0, 0, 30, 0);
        titlePanel.add(titleLabel, constraintsForTitlePanel);

        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.fill = GridBagConstraints.BOTH;
        titleConstraints.insets = new Insets(20, 0, 10, 0);
        checkInOptionViewPanel.add(titlePanel, titleConstraints);

        JPanel infoAreaPanel = new JPanel(new GridBagLayout());
        infoAreaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Choose A Type ", TitledBorder.CENTER, TitledBorder.TOP, new Font("Verdana", Font.PLAIN, 14)));
        infoAreaPanel.setPreferredSize(new Dimension(330, 280));

        infoAreaConstraints.gridx = 0;
        infoAreaConstraints.gridy = 1;
        infoAreaConstraints.insets = new Insets(0, 0, 20, 0);
        checkInOptionViewPanel.add(infoAreaPanel, infoAreaConstraints);

        singleButton.setPreferredSize(new Dimension(240, 50));
        singleButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        GridBagConstraints singleButtonConstraints = new GridBagConstraints();
        singleButtonConstraints.gridx = 0;
        singleButtonConstraints.gridy = 0;
        singleButtonConstraints.insets = new Insets(20, 0, 15, 0);
        infoAreaPanel.add(singleButton, singleButtonConstraints);

        groupButton.setPreferredSize(new Dimension(240, 50));
        groupButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        GridBagConstraints groupButtonConstraints = new GridBagConstraints();
        groupButtonConstraints.gridx = 0;
        groupButtonConstraints.gridy = 1;
        groupButtonConstraints.insets = new Insets(20, 0, 0, 0);
        infoAreaPanel.add(groupButton, groupButtonConstraints);

        numberOfPassengers.setPreferredSize(new Dimension(50, 30));
        GridBagConstraints spinnerConstraints = new GridBagConstraints();
        spinnerConstraints.gridx = 0;
        spinnerConstraints.gridy = 2;
        spinnerConstraints.insets = new Insets(20, 0, 0, 0);
        spinnerConstraints.anchor = GridBagConstraints.CENTER; // Center the spinner horizontally
        infoAreaPanel.add(numberOfPassengers, spinnerConstraints);

        if (warnMinimumNumberOfPassengers) {
            JLabel warnLabel = new JLabel("Error: Minimum number is 2 !", JLabel.CENTER);
            warnLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
            warnLabel.setForeground(Color.RED);
            GridBagConstraints warnConstraints = new GridBagConstraints();
            warnConstraints.gridx = 0;
            warnConstraints.gridy = 3;
            warnConstraints.gridwidth = 1;
            warnConstraints.insets = new Insets(15, 0, 0, 0);
            infoAreaPanel.add(warnLabel, warnConstraints);
        }

        mainMenuButton.setPreferredSize(new Dimension(240, 50));
        mainMenuButton.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        GridBagConstraints mainMenuButtonConstraints = new GridBagConstraints();
        mainMenuButtonConstraints.gridx = 0;
        mainMenuButtonConstraints.gridy = 2;
        mainMenuButtonConstraints.gridwidth = 1;
        mainMenuButtonConstraints.insets = new Insets(30, 0, 0, 0);
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
    public JPanel getCheckInOptionViewPanel() {
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
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
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
