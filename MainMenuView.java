import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;


public class MainMenuView {
    
    /** Instance variables */
    /** JPanel for the MainMenuView */
    private JPanel mainMenuViewPanel = new JPanel(new GridBagLayout());

    /** JButton to change the view to counterView */
    private JButton checkInCounterButton = new JButton("Staff");

    /** JButton to change the view to checkInOptionView */
    private JButton checkInKioskButton = new JButton("Kiosk");

    /** Method to place the components on the panel */
    public MainMenuView() {
        setupViewPanel();
    }
    
    /** GUI */
    public void setupViewPanel() {
        GridBagConstraints welcomeTitleConstraints = new GridBagConstraints();
        GridBagConstraints staffButtonConstraints = new GridBagConstraints();
        GridBagConstraints kioskButtonConstraints = new GridBagConstraints();

        JPanel buttonAreaPanel = new JPanel(new GridBagLayout());
        buttonAreaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "  AeroCheck Check-In System   ", TitledBorder.CENTER, TitledBorder.TOP));
        buttonAreaPanel.setPreferredSize(new Dimension(330, 430));
        mainMenuViewPanel.add(buttonAreaPanel);

        // JLabel to display a welcome title 
        JLabel welcomeTitle = new JLabel("Welcome!");
        
        // Set font for welcome title
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 15));
        
        // Add welcome title to panel
        welcomeTitleConstraints.gridx = 0;
        welcomeTitleConstraints.gridy = 0;
        welcomeTitleConstraints.fill = GridBagConstraints.CENTER;

        // Adjust the welcome title position
        welcomeTitleConstraints.gridwidth = 2;
        welcomeTitleConstraints.insets = new Insets(20, 0, 10, 0);
        mainMenuViewPanel.add(welcomeTitle, welcomeTitleConstraints);

        // Set size for staff button
        checkInCounterButton.setPreferredSize(new Dimension(230, 40));

        // Set size for kiosk button 
        checkInKioskButton.setPreferredSize(new Dimension(230, 40));

        GridBagConstraints buttonAreaConstraints = new GridBagConstraints();
        buttonAreaConstraints.gridx = 1;
        buttonAreaConstraints.gridy = 1;
        buttonAreaConstraints.weightx = 1.0;
        buttonAreaConstraints.weighty = 1.0;
        mainMenuViewPanel.add(buttonAreaPanel, buttonAreaConstraints);


        // Add checkInCounter(this is staff) button to panel 
        staffButtonConstraints.gridx = 0;
        staffButtonConstraints.gridy = 0;

        // Adjust the staff button position
        staffButtonConstraints.insets = new Insets(0, 0, 50, 0);
        buttonAreaPanel.add(checkInCounterButton, staffButtonConstraints);

        // Add checkInKiosk(this is kiosk) button to panel 
        kioskButtonConstraints.gridx = 0;
        kioskButtonConstraints.gridy = 1;

        // Adjust the kiosk button position
        kioskButtonConstraints.insets = new Insets(20, 0, 0, 0);
        buttonAreaPanel.add(checkInKioskButton, kioskButtonConstraints);
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

