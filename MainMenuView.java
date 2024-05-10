import javax.swing.*;
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
    public void setupViewPanel(){
        GridBagConstraints welcomeTitleConstraints = new GridBagConstraints();
        GridBagConstraints staffButtonConstraints = new GridBagConstraints();
        GridBagConstraints kioskButtonConstraints = new GridBagConstraints();

        // JLabel to display a welcome title 
        JLabel welcomeTitle = new JLabel("Welcome to AeroCheck Check-In System");
        
        // Set font for welcome title
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 19));
        
        // Add welcome title to panel
        welcomeTitleConstraints.gridx = 1;
        welcomeTitleConstraints.gridy = 1;
        welcomeTitleConstraints.fill = 1;

        // Adjust the welcome title position
        welcomeTitleConstraints.insets = new Insets(50, 0, 150, 0);
        mainMenuViewPanel.add(welcomeTitle, welcomeTitleConstraints);

        // Set size for staff button
        checkInCounterButton.setPreferredSize(new Dimension(100,40));

        // Set size for kiosk button 
        checkInKioskButton.setPreferredSize(new Dimension(100, 40));

        // Add checkInCounter(this is staff) button to panel 
        staffButtonConstraints.gridx = 1;
        staffButtonConstraints.gridy = 6;

        // Adjust the staff button position
        staffButtonConstraints.insets = new Insets(0, 0, 300, 0);
        mainMenuViewPanel.add(checkInCounterButton, staffButtonConstraints);

        // Add checkInKiosk(this is kiosk) button to panel 
        kioskButtonConstraints.gridx = 1;
        kioskButtonConstraints.gridy = 6;

        // Adjust the kiosk button position
        kioskButtonConstraints.insets = new Insets(20, 0, 0, 0);
        mainMenuViewPanel.add(checkInKioskButton, kioskButtonConstraints);
    }

    public JPanel getMainMenuViewPanel(){
        return mainMenuViewPanel;    
    }

    /** Adds an Action Listener to checkInCounterButton */
    public void addCheckInCounterButtonListener(ActionListener listenForCheckInCounterButton){
        checkInCounterButton.addActionListener(listenForCheckInCounterButton);
    }

    /** Adds an ActionListener to checkInKioskButton */
    public void addCheckInKioskButtonListener(ActionListener listenForCheckInKioskButton){
        checkInKioskButton.addActionListener(listenForCheckInKioskButton);
    }
}

