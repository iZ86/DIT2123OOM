import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/** This class represents the view to show the boarding pass. */
public class BoardingPassView {
    /** JPanel for BoardingPassView. */
    private final JPanel BOARDINGPASSVIEWPANEL =  new JPanel();
    /** The model that holds the passenger and their bag data. */
    private final KioskCheckInModel kioskCheckInModel;
    /** JButton to show the previous passenger's and their bag's data,
     * this button won't be displayed if the passenger is the first passenger on the list.
     */
    private final JButton previousBoardingPassButton = new JButton("< < <");
    /** JButton to show the next passenger's and their bag's data,
     * this button won't be displayed if the passenger is the last passenger on the list.
     */
    private final JButton nextBoardingPassButton = new JButton("> > >");
    /** JButton that changes the view to the doneView. */
    private final JButton doneButton = new JButton("DONE");
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

        Font labelFont = new Font("Verdana", Font.PLAIN, 12);
        Font dataFont = new Font("Lucida Sans", Font.BOLD, 13);

        if (kioskCheckInModel.getNumberOfPassengers() > 0) {
            JPanel panelForBoardingPassView = new JPanel(new GridBagLayout());

            JPanel informationAreaPanel = new JPanel(new GridBagLayout());

            GridBagConstraints constraintsForLabel = new GridBagConstraints();
            GridBagConstraints constraintsForPanel = new GridBagConstraints();

            boolean hasSpecialNeeds = kioskCheckInModel.isRequireWheelchair() || kioskCheckInModel.isBlind() || kioskCheckInModel.isDeaf() || kioskCheckInModel.isOtherSpecialAccommodation();

            TitledBorder titledBorder;
            if (hasSpecialNeeds) {
                titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), " Boarding Pass Details ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Verdana", Font.PLAIN, 12));
                titledBorder.setTitleColor(Color.RED.darker());
            } else {
                titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Boarding Pass Details ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Verdana", Font.PLAIN, 12));
            }

            informationAreaPanel.setBorder(titledBorder);
            informationAreaPanel.setPreferredSize(new Dimension(350, 230));
            panelForBoardingPassView.add(informationAreaPanel);

            constraintsForLabel.anchor = GridBagConstraints.CENTER;
            constraintsForLabel.insets = new Insets(5, 0, 5, 0);

            JLabel nameLabel = new JLabel("Mr / Ms " + kioskCheckInModel.getFullName() + ",");
            nameLabel.setFont(labelFont);
            JLabel bookingIdLabel = new JLabel("Booking ID");
            bookingIdLabel.setFont(labelFont);
            JLabel passportNumberLabel = new JLabel("Passport Number");
            passportNumberLabel.setFont(labelFont);

            constraintsForLabel.gridx = 0;
            constraintsForLabel.gridy = 0;
            informationAreaPanel.add(nameLabel, constraintsForLabel);

            JLabel bookingIdValueLabel = new JLabel(kioskCheckInModel.getBookingNumber());
            bookingIdValueLabel.setFont(dataFont);
            bookingIdValueLabel.setForeground(Color.BLUE.darker());
            JLabel passportNumberValueLabel = new JLabel(kioskCheckInModel.getPassportNumber());
            passportNumberValueLabel.setFont(dataFont);
            passportNumberValueLabel.setForeground(Color.BLUE.darker());

            constraintsForLabel.gridy++;
            informationAreaPanel.add(bookingIdLabel, constraintsForLabel);

            constraintsForLabel.gridy++;
            informationAreaPanel.add(bookingIdValueLabel, constraintsForLabel);

            constraintsForLabel.gridy++;
            informationAreaPanel.add(passportNumberLabel, constraintsForLabel);

            constraintsForLabel.gridy++;
            informationAreaPanel.add(passportNumberValueLabel, constraintsForLabel);

            if (hasSpecialNeeds) {
                JLabel specialNeedsLabel = new JLabel("Special Needs");
                specialNeedsLabel.setFont(new Font("Verdana", Font.BOLD, 12));
                specialNeedsLabel.setForeground(Color.RED.darker());
                constraintsForLabel.gridy++;
                constraintsForLabel.gridx = 0;
                informationAreaPanel.add(specialNeedsLabel, constraintsForLabel);

                StringBuilder specialNeedsDetails = new StringBuilder();

                if (kioskCheckInModel.isRequireWheelchair()) {
                    specialNeedsDetails.append(" Wheelchair ");
                }
                if (kioskCheckInModel.isBlind()) {
                    specialNeedsDetails.append(" Blindness ");
                }
                if (kioskCheckInModel.isDeaf()) {
                    specialNeedsDetails.append(" Deafness ");
                }
                if (kioskCheckInModel.isOtherSpecialAccommodation()) {
                    specialNeedsDetails.append(kioskCheckInModel.getOtherSpecialAccommodationDetails());
                }

                JLabel specialNeedsDetailsLabel = new JLabel(specialNeedsDetails.toString());
                specialNeedsDetailsLabel.setFont(dataFont);
                specialNeedsDetailsLabel.setForeground(Color.RED);
                constraintsForLabel.gridy++;
                informationAreaPanel.add(specialNeedsDetailsLabel, constraintsForLabel);
            }

            constraintsForPanel.gridy = 1;

            JPanel panelForBagAreaPanel = new JPanel(new GridBagLayout());

            TitledBorder bagTitledBorder;
            if (hasSpecialNeeds) {
                bagTitledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), " Baggage Details ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Verdana", Font.PLAIN, 12));
                bagTitledBorder.setTitleColor(Color.RED.darker());
            } else {
                bagTitledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Baggage Details ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Verdana", Font.PLAIN, 12));
            }

            panelForBagAreaPanel.setBorder(bagTitledBorder);
            panelForBagAreaPanel.setPreferredSize(new Dimension(350, 190));
            constraintsForPanel.insets = new Insets(15, 0, 0, 0);
            panelForBoardingPassView.add(panelForBagAreaPanel, constraintsForPanel);

            GridBagConstraints constraintsForBagLabel = new GridBagConstraints();
            constraintsForBagLabel.anchor = GridBagConstraints.WEST;
            constraintsForBagLabel.insets = new Insets(0, 10, 15, 10);

            if (kioskCheckInModel.getNumberOfBags() < 1) {
                JLabel noBagsLabel = new JLabel("No Bags Added");
                noBagsLabel.setFont(dataFont);
                constraintsForBagLabel.gridy = 0;
                panelForBagAreaPanel.add(noBagsLabel, constraintsForBagLabel);
            } else {
                // Otherwise, display bag details in columns
                JLabel bagIdHeaderLabel = new JLabel("Bag ID", JLabel.CENTER);
                bagIdHeaderLabel.setFont(labelFont);
                JLabel bagWeightHeaderLabel = new JLabel("Bag Weight", JLabel.CENTER);
                bagWeightHeaderLabel.setFont(labelFont);
                JLabel bagStatusHeaderLabel = new JLabel("Bag Status", JLabel.CENTER);
                bagStatusHeaderLabel.setFont(labelFont);

                constraintsForBagLabel.gridx = 0;
                constraintsForBagLabel.gridy = 0;
                constraintsForBagLabel.insets = new Insets(5, 0, 5, 40);
                panelForBagAreaPanel.add(bagIdHeaderLabel, constraintsForBagLabel);

                constraintsForBagLabel.gridx = 1;
                constraintsForBagLabel.insets = new Insets(5, 0, 5, 40);
                panelForBagAreaPanel.add(bagWeightHeaderLabel, constraintsForBagLabel);

                constraintsForBagLabel.gridx = 2;
                constraintsForBagLabel.insets = new Insets(5, 0, 5, 5);
                panelForBagAreaPanel.add(bagStatusHeaderLabel, constraintsForBagLabel);

                constraintsForBagLabel.anchor = GridBagConstraints.WEST;
                constraintsForBagLabel.gridy = 1;

                for (int i = 0; i < kioskCheckInModel.getNumberOfBags(); i++) {
                    String bagId = kioskCheckInModel.getBagID(i);
                    String bagWeight = String.format("%.2f kg", kioskCheckInModel.getBagWeight(i));
                    String bagStatus = kioskCheckInModel.getBagScreeningStatus(i);

                    JLabel bagIdLabel = new JLabel(bagId, JLabel.CENTER);
                    bagIdLabel.setFont(dataFont);
                    JLabel bagWeightLabel = new JLabel(bagWeight, JLabel.CENTER);
                    bagWeightLabel.setFont(dataFont);
                    JLabel bagStatusLabel = new JLabel(bagStatus, JLabel.CENTER);
                    bagStatusLabel.setFont(dataFont);

                    constraintsForBagLabel.gridx = 0;
                    panelForBagAreaPanel.add(bagIdLabel, constraintsForBagLabel);

                    constraintsForBagLabel.gridx = 1;
                    panelForBagAreaPanel.add(bagWeightLabel, constraintsForBagLabel);

                    constraintsForBagLabel.gridx = 2;
                    panelForBagAreaPanel.add(bagStatusLabel, constraintsForBagLabel);

                    constraintsForBagLabel.gridy++;
                }
            }

            JPanel flightDetailsPanel = new JPanel(new GridBagLayout());

            TitledBorder flightTitledBorder;
            if (hasSpecialNeeds) {
                flightTitledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), " Flight Details ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Verdana", Font.PLAIN, 12));
                flightTitledBorder.setTitleColor(Color.RED.darker());
            } else {
                flightTitledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Flight Details ", TitledBorder.LEFT, TitledBorder.TOP, new Font("Verdana", Font.PLAIN, 12));
            }

            flightDetailsPanel.setBorder(flightTitledBorder);
            flightDetailsPanel.setPreferredSize(new Dimension(350, 165));

            GridBagConstraints constraintsForFlightLabel = new GridBagConstraints();
            constraintsForFlightLabel.anchor = GridBagConstraints.CENTER;
            constraintsForFlightLabel.insets = new Insets(2, 0, 7, 0);

            JLabel seatNumberLabel = new JLabel("Seat Number: ");
            seatNumberLabel.setFont(labelFont);
            JLabel seatNumberValueLabel = new JLabel(kioskCheckInModel.getSeatNumber());
            seatNumberValueLabel.setFont(dataFont);
            seatNumberValueLabel.setForeground(Color.BLUE.darker());

            JLabel destinationLabel = new JLabel("Destination: ");
            destinationLabel.setFont(labelFont);
            JLabel destinationValueLabel = new JLabel(kioskCheckInModel.getDestination());
            destinationValueLabel.setFont(dataFont);
            destinationValueLabel.setForeground(Color.BLUE.darker());

            JLabel flightStatusLabel = new JLabel("Flight Status: ");
            flightStatusLabel.setFont(labelFont);
            JLabel flightStatusValueLabel = new JLabel(kioskCheckInModel.getFlightStatus());
            flightStatusValueLabel.setFont(dataFont);
            flightStatusValueLabel.setForeground(Color.GREEN.darker());

            JLabel gateNumberLabel = new JLabel("Gate Number:");
            gateNumberLabel.setFont(labelFont);
            JLabel gateNumberValueLabel = new JLabel(String.valueOf(kioskCheckInModel.getGateNumber()));
            gateNumberValueLabel.setFont(dataFont);
            gateNumberValueLabel.setForeground(Color.BLUE.darker());

            JLabel boardingTimeLabel = new JLabel("Boarding Time: ");
            boardingTimeLabel.setFont(labelFont);
            JLabel boardingTimeValueLabel = new JLabel(kioskCheckInModel.getBoardingTime());
            boardingTimeValueLabel.setFont(dataFont);
            boardingTimeValueLabel.setForeground(Color.cyan.darker());

            constraintsForFlightLabel.gridx = 0;
            constraintsForFlightLabel.gridy = 0;
            flightDetailsPanel.add(seatNumberLabel, constraintsForFlightLabel);

            constraintsForFlightLabel.gridx = 1;
            flightDetailsPanel.add(seatNumberValueLabel, constraintsForFlightLabel);

            constraintsForFlightLabel.gridx = 0;
            constraintsForFlightLabel.gridy++;
            flightDetailsPanel.add(destinationLabel, constraintsForFlightLabel);

            constraintsForFlightLabel.gridx = 1;
            flightDetailsPanel.add(destinationValueLabel, constraintsForFlightLabel);

            constraintsForFlightLabel.gridx = 0;
            constraintsForFlightLabel.gridy++;
            flightDetailsPanel.add(flightStatusLabel, constraintsForFlightLabel);

            constraintsForFlightLabel.gridx = 1;
            flightDetailsPanel.add(flightStatusValueLabel, constraintsForFlightLabel);

            constraintsForFlightLabel.gridx = 0;
            constraintsForFlightLabel.gridy++;
            flightDetailsPanel.add(gateNumberLabel, constraintsForFlightLabel);

            constraintsForFlightLabel.gridx = 1;
            flightDetailsPanel.add(gateNumberValueLabel, constraintsForFlightLabel);

            constraintsForFlightLabel.gridx = 0;
            constraintsForFlightLabel.gridy++;
            flightDetailsPanel.add(boardingTimeLabel, constraintsForFlightLabel);

            constraintsForFlightLabel.gridx = 1;
            flightDetailsPanel.add(boardingTimeValueLabel, constraintsForFlightLabel);

            constraintsForPanel.gridy = 2;
            constraintsForPanel.insets = new Insets(15, 0, 0, 0);
            panelForBoardingPassView.add(flightDetailsPanel, constraintsForPanel);

            JPanel informationPageButtonPanel = new JPanel(new GridBagLayout());
            JPanel panelForNextAndPreviousButton = new JPanel(new GridBagLayout());
            JPanel panelForOtherButtons = new JPanel(new GridBagLayout());
            JLabel labelForPassengerPageIndex = new JLabel("Passenger " + (boardingPassViewPagingIndex + 1) + " / " + kioskCheckInModel.getNumberOfPassengers());
            labelForPassengerPageIndex.setFont(new Font("Verdana", Font.PLAIN, 13));

            int horizontalSizeOfButton = 18;
            int verticalSizeOfButton = 10;

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
            constraintsForNextButton.insets = new Insets(0, 0, 0, 0);
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

            constraintsForNextAndPreviousButtonPanel.gridy = 2;
            constraintsForNextAndPreviousButtonPanel.gridx = 0;

            nextBoardingPassButton.setFont(new Font("Verdana", Font.BOLD, 10));
            previousBoardingPassButton.setFont(new Font("Verdana", Font.BOLD, 10));

            constraintsForOtherButtonPanel.gridy = 4;
            constraintsForOtherButtonPanel.gridx = 0;

            if (kioskCheckInModel.getNumberOfPassengers() > 0) {
                if (boardingPassViewPagingIndex > 0) {
                    panelForNextAndPreviousButton.add(previousBoardingPassButton, constraintsForPreviousButton);
                }
                if (boardingPassViewPagingIndex < kioskCheckInModel.getNumberOfPassengers() - 1) {
                    panelForNextAndPreviousButton.add(nextBoardingPassButton, constraintsForNextButton);
                }
            }

            constraintsForDoneButton.gridx = 0;
            constraintsForDoneButton.gridy = 0;

            doneButton.setPreferredSize(new Dimension(100, 35));
            doneButton.setFont(new Font("Lucida Sans", Font.BOLD, 12));
            panelForOtherButtons.add(doneButton, constraintsForDoneButton);

            informationPageButtonPanel.add(labelForPassengerPageIndex, constraintsForPassengerIndexLabel);
            informationPageButtonPanel.add(panelForNextAndPreviousButton, constraintsForNextAndPreviousButtonPanel);
            informationPageButtonPanel.add(panelForOtherButtons, constraintsForOtherButtonPanel);

            constraintsForPanel.gridy = 3;
            panelForBoardingPassView.add(informationPageButtonPanel, constraintsForPanel);

            BOARDINGPASSVIEWPANEL.add(panelForBoardingPassView);
        }

    }

    /** Updates the view. */
    public void updateView() {
        BOARDINGPASSVIEWPANEL.removeAll();
        setupViewPanel();
        BOARDINGPASSVIEWPANEL.revalidate();
        BOARDINGPASSVIEWPANEL.repaint();
    }

    /** Returns the boardingPassViewPanel. */
    public JPanel getBoardingPassViewPanel() {
        return BOARDINGPASSVIEWPANEL;
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

    /** Adds an ActionListener to doneButton. */
    public void addDoneButtonListener(ActionListener listenForDoneButton) {
        doneButton.addActionListener(listenForDoneButton);
    }
}
