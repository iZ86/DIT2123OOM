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

  
            
    /** all these are wrong, you should create your own method and put it inside the methods */

    public MainMenuView() {
        setupViewPanel();
    }
    
    /** GUI */
    public void setupViewPanel(){
        GridBagConstraints c = new GridBagConstraints();

        // JLabel to display a welcome title 
        JLabel welcomeTitle = new JLabel("Welcome to AeroCheck Check-In System");
        
        // Set font for welcome title
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 19));
        

        // Add welcome title to panel
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 10;
        mainMenuViewPanel.add(welcomeTitle, c);

        // Add checkInCounter button to panel 
        c.gridx = 7;
        c.gridy = 4;
        mainMenuViewPanel.add(checkInCounterButton, c);

        // Add checkInKiosk button to panel 
        c.gridx = 9;
        c.gridy = 5;
        mainMenuViewPanel.add(checkInKioskButton, c);
    }

    public JPanel getMainMenuViewPanel(){
        return mainMenuViewPanel;    
    }

    public void addCheckInCounterButtonListener(ActionListener listenForCheckInCounterButton){
        checkInCounterButton.addActionListener(listenForCheckInCounterButton);
    }

    public void addCheckInKioskButtonListener(ActionListener listenForCheckInKioskButton){
        checkInKioskButton.addActionListener(listenForCheckInKioskButton);
    }
}

