import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;


public class MainMenuView {
    
    /** Instance variables */
    /** JPanel for the MainMenuView */
    private final JPanel mainMenuViewPanel = new JPanel(new GridBagLayout());

    /** JButton to change the view to counterView */
    private final JButton checkInCounterButton = new JButton("Staff");

    /** JButton to change the view to checkInOptionView */
    private final JButton checkInKioskButton = new JButton("Kiosk");

    /** Method to place the components on the panel */
    public MainMenuView() {
        setupViewPanel();
    }
    
    /** GUI */
    public void setupViewPanel() {
        GridBagConstraints titleConstraints = new GridBagConstraints();
        GridBagConstraints buttonAreaConstraints = new GridBagConstraints();

        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setPreferredSize(new Dimension(330, 150));

        JLabel welcomeLabel = new JLabel("WELCOME", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));

        JLabel aeroCheckLabel = new JLabel("AeroCheck", JLabel.CENTER);
        aeroCheckLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));

        JLabel checkInSystemLabel = new JLabel("Check-In System", JLabel.CENTER);
        checkInSystemLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));

        GridBagConstraints constraintsForTitlePanel = new GridBagConstraints();
        constraintsForTitlePanel.gridx = 0;
        constraintsForTitlePanel.gridy = 0;
        constraintsForTitlePanel.insets = new Insets(0, 0, 10, 0);
        titlePanel.add(welcomeLabel, constraintsForTitlePanel);

        constraintsForTitlePanel.gridy = 1;
        constraintsForTitlePanel.insets = new Insets(0, 0, 10, 0);
        titlePanel.add(aeroCheckLabel, constraintsForTitlePanel);

        constraintsForTitlePanel.gridy = 2;
        constraintsForTitlePanel.insets = new Insets(0, 0, 20, 0);
        titlePanel.add(checkInSystemLabel, constraintsForTitlePanel);

        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.fill = GridBagConstraints.BOTH;
        titleConstraints.insets = new Insets(20, 0, 10, 0);
        mainMenuViewPanel.add(titlePanel, titleConstraints);

        JPanel buttonAreaPanel = new JPanel(new GridBagLayout());
        buttonAreaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()," Select Check-In Mode ", TitledBorder.CENTER, TitledBorder.TOP, new Font("Verdana", Font.PLAIN, 14)));
        buttonAreaPanel.setPreferredSize(new Dimension(330, 230));

        buttonAreaConstraints.gridx = 0;
        buttonAreaConstraints.gridy = 1;
        buttonAreaConstraints.insets = new Insets(0, 0, 20, 0);
        mainMenuViewPanel.add(buttonAreaPanel, buttonAreaConstraints);

        GridBagConstraints buttonConstraintsForTitlePanel = new GridBagConstraints();
        buttonConstraintsForTitlePanel.gridx = 0;
        buttonConstraintsForTitlePanel.insets = new Insets(15, 0, 15, 0);
        buttonConstraintsForTitlePanel.fill = GridBagConstraints.HORIZONTAL;

        checkInCounterButton.setPreferredSize(new Dimension(240, 50));
        checkInCounterButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        checkInCounterButton.setFocusPainted(false);
        buttonConstraintsForTitlePanel.gridy = 0;
        buttonAreaPanel.add(checkInCounterButton, buttonConstraintsForTitlePanel);

        checkInKioskButton.setPreferredSize(new Dimension(240, 50));
        checkInKioskButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        checkInKioskButton.setFocusPainted(false);
        buttonConstraintsForTitlePanel.gridy = 1;
        buttonAreaPanel.add(checkInKioskButton, buttonConstraintsForTitlePanel);
    }

    public JPanel getMainMenuViewPanel() {
        return mainMenuViewPanel;    
    }

    /** Adds an Action Listener to checkInCounterButton */
    public void addCheckInCounterButtonListener(ActionListener listenForCheckInCounterButton) {
        checkInCounterButton.addActionListener(listenForCheckInCounterButton);
    }

    /** Adds an ActionListener to checkInKioskButton */
    public void addCheckInKioskButtonListener(ActionListener listenForCheckInKioskButton) {
        checkInKioskButton.addActionListener(listenForCheckInKioskButton);
    }
}

