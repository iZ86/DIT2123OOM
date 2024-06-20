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
    private JButton previousBoardingPassButton = new JButton("< ~ ~ ~");
    /** JButton to show the next passenger's and their bag's data,
     * this button won't be displayed if the passenger is the last passenger on the list.
     */
    private JButton nextBoardingPassButton = new JButton("~ ~ ~ >");
    /** JButton that saves a PDF file of the BoardingPassView. */
    private JButton printBoardingPassButton = new JButton("Print");
    /** JButton that changes the view to the doneView. */
    private JButton doneButton = new JButton("Done");
    /** Paging index for boarding pass view. */
    private int boardingPassViewPagingIndex;

    /** Creates a BoardingPassView object with KioskCheckInModel kioskCheckInModel as the model. */
    public BoardingPassView(KioskCheckInModel kioskCheckInModel) {
        this.kioskCheckInModel = kioskCheckInModel;
        boardingPassViewPagingIndex = 0;
        setupViewPanel();
    }

    /** Sets up the boardingPassViewPanel. */
    private void setupViewPanel() {

        JPanel panelForBoardingPassView = new JPanel(new GridBagLayout());

        JPanel informationAreaPanel = new JPanel(new GridBagLayout());
        informationAreaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()," Boarding Pass Details  ", TitledBorder.LEFT,TitledBorder.TOP));
        informationAreaPanel.setPreferredSize(new Dimension(350, 200));
        panelForBoardingPassView.add(informationAreaPanel);

        JLabel nameLabel = new JLabel("Mr/Ms " + kioskCheckInModel.getFullName());
        JLabel bookingIdLabel = new JLabel("Booking ID: " + kioskCheckInModel.getBookingNumber());
        JLabel passportNumberLabel = new JLabel("Passport Number: " + kioskCheckInModel.getPassportNumber());

        GridBagConstraints constraintsForLabel = new GridBagConstraints();
        GridBagConstraints constraintsForButton = new GridBagConstraints();
        GridBagConstraints constraintsForPanel = new GridBagConstraints();

        constraintsForLabel.anchor = GridBagConstraints.WEST;
        constraintsForLabel.insets = new Insets(10, 0, 15, 160);

        constraintsForButton.anchor = GridBagConstraints.CENTER;
        constraintsForButton.insets = new Insets(15, 0, 1500, 0);

        informationAreaPanel.add(nameLabel, constraintsForLabel);

        constraintsForLabel.gridy = 1;
        informationAreaPanel.add(bookingIdLabel, constraintsForLabel);

        constraintsForLabel.gridy = 2;
        informationAreaPanel.add(passportNumberLabel, constraintsForLabel);

        constraintsForPanel.gridy = 1;

        JPanel panelForBagAreaPanel = new JPanel(new GridBagLayout());
        panelForBagAreaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()," Baggage Details  ", TitledBorder.LEFT,TitledBorder.TOP));
        panelForBagAreaPanel.setPreferredSize(new Dimension(350, 230));
        constraintsForPanel.insets = new Insets(20, 0, 0, 0);
        panelForBoardingPassView.add(panelForBagAreaPanel,constraintsForPanel);

        JLabel bagDetailsHeaderLabel = new JLabel("Bag ID                Bag Weight                Bag Status");
        GridBagConstraints constraintsForBagHeaderLabel = new GridBagConstraints();
        constraintsForBagHeaderLabel.anchor = GridBagConstraints.WEST;
        constraintsForBagHeaderLabel.insets = new Insets(15, 0, 25, 0);
        constraintsForBagHeaderLabel.gridy = 0;
        panelForBagAreaPanel.add(bagDetailsHeaderLabel, constraintsForBagHeaderLabel);

        GridBagConstraints constraintsForBagLabel = new GridBagConstraints();
        constraintsForBagLabel.anchor = GridBagConstraints.WEST;
        constraintsForBagLabel.insets = new Insets(0, 0, 15, 0);

        for (int i = 0; i < kioskCheckInModel.getNumberOfBags(); i++) {
            String bagDetails = String.format("%s                %.2f kg                     %s",
                    kioskCheckInModel.getBagID(i),
                    kioskCheckInModel.getBagWeight(i),
                    kioskCheckInModel.getBagScreeningStatus(i));
            JLabel bagDetailsLabel = new JLabel(bagDetails);
            constraintsForBagLabel.gridy = i + 1;
            panelForBagAreaPanel.add(bagDetailsLabel, constraintsForBagLabel);
        }

        JPanel informationPageButtonPanel = new JPanel(new GridBagLayout());
        JPanel panelForNextAndPreviousButton = new JPanel(new GridBagLayout());
        JPanel panelForOtherButtons = new JPanel(new GridBagLayout());
        JLabel labelForPassengerPageIndex = new JLabel("Passenger " + (boardingPassViewPagingIndex + 1) + " / " + kioskCheckInModel.getNumberOfPassengers());

        int horizontalSizeOfButton = 18;
        int verticalSizeOfButton = 4;

        GridBagConstraints constraintsForNextButton = new GridBagConstraints();
        GridBagConstraints constraintsForPreviousButton = new GridBagConstraints();
        GridBagConstraints constraintsForDoneButton = new GridBagConstraints();
        GridBagConstraints constraintsForNextAndPreviousButtonPanel = new GridBagConstraints();
        GridBagConstraints constraintsForOtherButtonPanel = new GridBagConstraints();
        GridBagConstraints constraintsForPassengerIndexLabel = new GridBagConstraints();

        constraintsForNextButton.ipadx = horizontalSizeOfButton;
        constraintsForNextButton.ipady = verticalSizeOfButton;

        constraintsForPreviousButton.ipadx = horizontalSizeOfButton;
        constraintsForPreviousButton.ipady = verticalSizeOfButton;

        constraintsForPreviousButton.insets = new Insets(0, 0, 0, 0);
        constraintsForNextButton.insets = new Insets(0, 10, 0, 0);
        constraintsForNextAndPreviousButtonPanel.insets = new Insets(15, 0, 10, 0);
        constraintsForDoneButton.insets = new Insets(0, 10, 0, 10);
        constraintsForPassengerIndexLabel.insets = new Insets(0, 0, 0, 0);
        constraintsForOtherButtonPanel.insets = new Insets(5, 0, 0, 0);

        constraintsForPreviousButton.gridx = 0;
        constraintsForPreviousButton.gridy = 0;

        constraintsForNextButton.gridx = 1;
        constraintsForNextButton.gridy = 0;

        constraintsForPassengerIndexLabel.gridy = 0;
        constraintsForPassengerIndexLabel.gridx = 0;

        constraintsForNextAndPreviousButtonPanel.gridy = 1;
        constraintsForNextAndPreviousButtonPanel.gridx = 0;

        constraintsForOtherButtonPanel.gridy = 2;
        constraintsForOtherButtonPanel.gridx = 0;

        if (kioskCheckInModel.getNumberOfPassengers() > 0) {
            if (boardingPassViewPagingIndex > 0) {
                panelForNextAndPreviousButton.add(previousBoardingPassButton, constraintsForPreviousButton);
            }
            if (boardingPassViewPagingIndex < kioskCheckInModel.getNumberOfPassengers() - 1) {
                panelForNextAndPreviousButton.add(nextBoardingPassButton, constraintsForNextButton);
            }
        }

        informationPageButtonPanel.add(labelForPassengerPageIndex, constraintsForPassengerIndexLabel);
        informationPageButtonPanel.add(panelForNextAndPreviousButton, constraintsForNextAndPreviousButtonPanel);
        informationPageButtonPanel.add(panelForOtherButtons, constraintsForOtherButtonPanel);

        constraintsForPanel.gridy = 2;
        panelForBoardingPassView.add(informationPageButtonPanel, constraintsForPanel);

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

    /** Returns boardingPassViewPagingIndex. */
    public int getBoardingPassViewPagingIndex() {
        return boardingPassViewPagingIndex;
    }

    /** Sets boardingPassViewPagingIndex. */
    public void setBoardingPassViewPagingIndex(int boardingPassViewPagingIndex) {
        this.boardingPassViewPagingIndex = boardingPassViewPagingIndex;
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

    /** Adds an ActionListener to doneButton. */
    public void addDoneButtonListener(ActionListener listenForDoneButton) {
        doneButton.addActionListener(listenForDoneButton);
    }
}
