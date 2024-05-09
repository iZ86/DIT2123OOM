import javax.swing.*;
import java.awt.event.ActionListener;

/** This class represents the view to show the boarding pass. */
public class BoardingPassView {
    /** JPanel for BoardingPassView. */
    private JPanel boardingPassViewPanel;
    /** The model that holds the passenger and their bag data. */
    private KioskCheckInModel kioskCheckInModel;
    /** JButton to show the previous passenger's and their bag's data,
     * this button won't be displayed if the passenger is the first passenger on the list.
     */
    private JButton previousBoardingPassButton = new JButton("previous");
    /** JButton to show the next passenger's and their bag's data,
     * this button won't be displayed if the passenger is the last passenger on the list.
     */
    private JButton nextBoardingPassButton = new JButton("next");
    /** JButton that saves a PDF file of the BoardingPassView. */
    private JButton printBoardingPassButton = new JButton("print");
    /** JButton that changes the view to the MainMenuView. */
    private JButton mainMenuButton = new JButton("Main Menu");

    /** Creates a BoardingPassView object with KioskCheckInModel kioskCheckInModel as the model. */
    public BoardingPassView(KioskCheckInModel kioskCheckInModel) {
        this.kioskCheckInModel = kioskCheckInModel;
        setupViewPanel();
    }

    /** Sets up the boardingPassViewPanel. */
    private void setupViewPanel() {
        //TODO: GUI & Get details from user input
        boardingPassViewPanel = new JPanel();

        JLabel boardingPassLabel = new JLabel("- Boarding Pass Details -");
        JLabel nameLabel = new JLabel("Mr/Ms ");
        kioskCheckInModel.getFullName();
        JLabel bookingIdLabel = new JLabel("Booking ID: ");get d
        kioskCheckInModel.getBookingID();
        JLabel passportNumberLabel = new JLabel("Passport Number: ");
        kioskCheckInModel.getPassportNumber();
        JLabel typeLabel = new JLabel("Type: ");

        JLabel bagDetailsLabel = new JLabel("- Bag Details -");
        JLabel bagIdLabel = new JLabel("Bag ID :");
        kioskCheckInModel.getBagID();
        JLabel bagColorLabel = new JLabel("Bag Color : ");
        kioskCheckInModel.getBagColor();
        JLabel bagWeightLabel = new JLabel("Bag Weight : ");
        kioskCheckInModel.getBagWeight();

        boardingPassViewPanel.add(boardingPassLabel);
        boardingPassViewPanel.add(nameLabel); boardingPassViewPanel.add(bookingIdLabel);
        boardingPassViewPanel.add(passportNumberLabel); boardingPassViewPanel.add(typeLabel);
        boardingPassViewPanel.add(bagDetailsLabel); boardingPassViewPanel.add(bagIdLabel);
        boardingPassViewPanel.add(bagColorLabel); boardingPassViewPanel.add(bagWeightLabel);
    }

    /** Returns the boardingPassViewPanel. */
    public JPanel getBoardingPassViewPanel() {
        return boardingPassViewPanel;
    }

    /** Adds an ActionListener to previousBoardingPassButton. */
    public void addPreviousBoardingPassButtonListener(ActionListener listenForPreviousBoardingPassButton) {
        previousBoardingPassButton.addActionListener(listenForPreviousBoardingPassButton);
    }

    /** Adds an ActionListener to nextBoardingPassButton. */
    public void addNextBoardingPassButtonListener(ActionListener listenForNextBoardingPassButton) {
        nextBoardingPassButton.addActionListener(listenForNextBoardingPassButton);
    }

    /** Adds an ActionListener to printBoardingPassButton. */
    public void addPrintBoardingPassButtonListener(ActionListener listenForPrintBoardingPassButton) {
        printBoardingPassButton.addActionListener(listenForPrintBoardingPassButton);
    }

    /** Adds an ActionListener to mainMenuButton. */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }
}
