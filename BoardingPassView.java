import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/** This class represents the view to show the boarding pass. */
public class BoardingPassView {
    /** JPanel for BoardingPassView. */
    private JPanel boardingPassViewPanel =  new JPanel();
    /** The model that holds the passenger and their bag data. */
    private KioskCheckInModel kioskCheckInModel;
    /** JButton to show the previous passenger's and their bag's data,
     * this button won't be displayed if the passenger is the first passenger on the list.
     */
    private JButton previousBoardingPassButton = new JButton("Previous");
    /** JButton to show the next passenger's and their bag's data,
     * this button won't be displayed if the passenger is the last passenger on the list.
     */
    private JButton nextBoardingPassButton = new JButton("Next");
    /** JButton that saves a PDF file of the BoardingPassView. */
    private JButton printBoardingPassButton = new JButton("Print");
    /** JButton that changes the view to the MainMenuView. */
    private JButton mainMenuButton = new JButton("Main Menu");
    private JPanel panelForBoardingPassView;

    /** Creates a BoardingPassView object with KioskCheckInModel kioskCheckInModel as the model. */
    public BoardingPassView(KioskCheckInModel kioskCheckInModel) {
        this.kioskCheckInModel = kioskCheckInModel;
        setupViewPanel();
    }

    /** Sets up the boardingPassViewPanel. */
    private void setupViewPanel() {

        panelForBoardingPassView = new JPanel(new GridBagLayout());

        JPanel informationAreaPanel = new JPanel(new GridBagLayout());
        informationAreaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()," Boarding Pass Details  ", TitledBorder.LEFT,TitledBorder.TOP));
        informationAreaPanel.setPreferredSize(new Dimension(350, 200));

        panelForBoardingPassView.add(informationAreaPanel);

        JLabel nameLabel = new JLabel("Mr/Ms ");
        JLabel bookingIdLabel = new JLabel("Booking ID: ");
        JLabel passportNumberLabel = new JLabel("Passport Number: ");

        JLabel nameDataLabel = new JLabel(kioskCheckInModel.getFullName());
        JLabel bookingIdDataLabel = new JLabel(kioskCheckInModel.getBookingNumber());
        JLabel passportNumberDataLabel = new JLabel(kioskCheckInModel.getPassportNumber());

        GridBagConstraints constraintsForLabel = new GridBagConstraints();
        GridBagConstraints constraintsForData = new GridBagConstraints();
        GridBagConstraints constraintsForButton = new GridBagConstraints();
        GridBagConstraints constraintsForPanel = new GridBagConstraints();

        constraintsForLabel.anchor = GridBagConstraints.WEST;
        constraintsForLabel.insets = new Insets(10, 0, 15, 200);

        constraintsForData.anchor = GridBagConstraints.WEST;
        constraintsForData.insets = new Insets(0, 0, 5, 0);

        constraintsForButton.anchor = GridBagConstraints.CENTER;
        constraintsForButton.insets = new Insets(15, 0, 1500, 0);

        informationAreaPanel.add(nameLabel, constraintsForLabel);
        informationAreaPanel.add(nameDataLabel, constraintsForData);

        constraintsForLabel.gridy = 1;
        constraintsForData.gridy = 1;
        informationAreaPanel.add(bookingIdLabel, constraintsForLabel);
        informationAreaPanel.add(bookingIdDataLabel, constraintsForData);

        constraintsForLabel.gridy = 2;
        constraintsForData.gridy = 2;
        informationAreaPanel.add(passportNumberLabel, constraintsForLabel);
        informationAreaPanel.add(passportNumberDataLabel, constraintsForData);

        constraintsForPanel.gridy = 1;

        JPanel panelForBagAreaPanel = new JPanel(new GridBagLayout());
        panelForBagAreaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()," Baggage Details  ", TitledBorder.LEFT,TitledBorder.TOP));
        panelForBagAreaPanel.setPreferredSize(new Dimension(350, 200));
        constraintsForPanel.insets = new Insets(20, 0, 0, 0);
        panelForBoardingPassView.add(panelForBagAreaPanel,constraintsForPanel);

        // Checks if the passenger has any number of bags.
        // If got adds it to the view.
        // This is to prevent exception when the passenger has no bags.
        // It will ask for null attributes, and that is an error.
        // TODO: @i4GS implement this however u like. but it MUST be implemented.
        if (kioskCheckInModel.getNumberOfBags() > 0) {
            JLabel bagIdLabel = new JLabel("Bag ID: ");
            JLabel bagColorLabel = new JLabel("Bag Color: ");
            JLabel bagWeightLabel = new JLabel("Bag Weight: ");

            JLabel bagIdDataLabel = new JLabel(kioskCheckInModel.getBagID());
            JLabel bagWeightDataLabel = new JLabel();
            constraintsForLabel.anchor = GridBagConstraints.WEST;
            constraintsForLabel.insets = new Insets(10, 0, 15, 235);

            panelForBagAreaPanel.add(bagIdLabel, constraintsForLabel);
            panelForBagAreaPanel.add(bagIdDataLabel, constraintsForData);

            constraintsForLabel.gridy = 4;
            constraintsForData.gridy = 4;
            panelForBagAreaPanel.add(bagColorLabel, constraintsForLabel);

            constraintsForLabel.gridy = 5;
            constraintsForData.gridy = 5;
            panelForBagAreaPanel.add(bagWeightLabel, constraintsForLabel);
            panelForBagAreaPanel.add(bagWeightDataLabel, constraintsForData);
        }




        constraintsForButton.gridy = 6;
        constraintsForButton.gridx = 0;
        //constraintsForButton.ipadx = horizontalSizeOfButton;
        //constraintsForButton.ipady = verticalSizeOfButton;
        constraintsForButton.insets = new Insets(10, 10, 0, 0);
        constraintsForButton.anchor = GridBagConstraints.CENTER;

        panelForBoardingPassView.add(previousBoardingPassButton,constraintsForButton);
        panelForBoardingPassView.add(nextBoardingPassButton,constraintsForButton);

        constraintsForButton.gridy = 7;
        panelForBoardingPassView.add(mainMenuButton, constraintsForButton);

        constraintsForButton.gridy = 8;
        panelForBoardingPassView.add(printBoardingPassButton,constraintsForButton);

        boardingPassViewPanel.add(panelForBoardingPassView);
    }

    /** Updates the view. */
    public void updateView() {
        boardingPassViewPanel.removeAll();
        setupViewPanel();
        boardingPassViewPanel.revalidate();
        boardingPassViewPanel.repaint();
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
