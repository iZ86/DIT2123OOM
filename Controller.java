import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

/** This class acts as the controller of the MVC structure in this program. */
public class Controller {
    /** gui. */
    private GUI gui;
    /** The model that holds data for counterView. */
    private CounterModel counterModel;
    /** The model to hold all the data. */
    private KioskCheckInModel kioskCheckInModel;
    /** Temp passenger data including their bag data. */
    private Passenger[] tempPassengersData;
    /** checkInViewPagingIndex,
     * used to keep track of which checkInViewPage is being displayed.
     * Also use to keep track of which index the tempPassengersData should be used.
     */
    private int checkInViewPagingIndex;
    /** boardingPassViewPagingIndex,
     * used to keep track of which passenger's boarding pass to show.
     */
    private int boardingPassViewPagingIndex;
    /** Temp data to keep track of which tempPassengerData's bookingID is invalid. */
    private boolean[] tempInvalidBookingIDData;

    /** Creates a Controller object with GUI gui, and KioskCheckInModel kioskCheckInModel,
     * acts as the bridge between GUI gui and KioskCheckInModekl kioskCheckInModel,
     * controlling the workflow.
     */
    public Controller(GUI gui, CounterModel counterModel, KioskCheckInModel kioskCheckInModel) {
        this.gui = gui;
        this.counterModel = counterModel;
        this.kioskCheckInModel = kioskCheckInModel;
        addActionListeners();
        addItemListeners();
    }

    /** Adds all the ActionListeners to the GUI respectively. */
    private void addActionListeners() {
        gui.addAllCheckInCounterButtonsListener(new CheckInCounterButtonListener());
        gui.addAllCheckInKioskButtonsListener(new CheckInKioskButtonListener());
        gui.addAllSingleCheckInButtonsListener(new SingleCheckInButtonListener());
        gui.addAllGroupCheckInButtonsListener(new GroupCheckInButtonListener());
        gui.addAllAddBagButtonsListener(new AddBagButtonListener());
        gui.addAllPreviousPassengerButtonsListener(new PreviousPassengerButtonListener());
        gui.addAllNextPassengerButtonsListener(new NextPassengerButtonListener());
        gui.addAllCheckInButtonsListener(new CheckInButtonListener());
        gui.addAllPreviousBoardingPassButtonsListener(new PreviousBoardingPassButtonListener());
        gui.addAllNextBoardingPassButtonsListener(new NextBoardingPassButtonListener());
        gui.addAllPrintBoardingPassButtonsListener(new PrintBoardingPassButtonListener());
        gui.addAllMainMenuButtonsListener(new MainMenuButtonListener());
    }

    /** Adds the ItemListeners to the GUI respectively. */
    private void addItemListeners() {
        gui.addOthersSpecialAccommodationCheckBox(new OthersSpecialAccommodationCheckBoxListener());
    }

    // TODO: Add comments and TODO's for all the listener class.
    
    public class CheckInCounterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.changeView(GUI.COUNTERVIEWINDEX);
        }
    }

    public class CheckInKioskButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Changes the view to the GUIViewPanel to checkInOptionView.
            gui.changeView(GUI.CHECKINOPTIONVIEWINDEX);
            gui.getCheckInOptionView().setNumberOfPassengers(2);
        }
    }

    public class SingleCheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: set the kioskCheckInModel number of passengers to 1.
            // TODO: the kioskCheckInModel should automatically adjust itself
            // TODO: so no need to touch kioskCheckInModel.
            // TODO: Changes the GUIViewPanel to checkInView.
            kioskCheckInModel.setNumberOfPassengers(1);
            checkInViewPagingIndex = 0;
            tempPassengersData = new Passenger[1];
            tempInvalidBookingIDData = new boolean[1];
            tempInvalidBookingIDData[0] = false;
            gui.getCheckInView().setCheckInViewPagingIndex(checkInViewPagingIndex);
            gui.getCheckInView().resetView();
            gui.getCheckInView().updateView();
            gui.changeView(GUI.CHECKINVIEWINDEX);
        }
    }

    public class GroupCheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: sets the kioskCheckInModel number of passengers to N.
            // TODO: N must be greater than 2.
            // TODO: Otherwise, error message, this can be done through JOptionPane.
            // TODO: Or even better, modify the checkInOptionView and have some kind of feedback.
            int numberOfPassengers = gui.getCheckInOptionView().getNumberOfPassengersFromJSpinner();
            if (numberOfPassengers >= 2) {
                kioskCheckInModel.setNumberOfPassengers(numberOfPassengers);
                checkInViewPagingIndex = 0;
                gui.getCheckInView().setCheckInViewPagingIndex(checkInViewPagingIndex);
                tempPassengersData = new Passenger[numberOfPassengers];
                tempInvalidBookingIDData = new boolean[numberOfPassengers];
                for (int i = 0; i < numberOfPassengers; i++) {
                    tempInvalidBookingIDData[i] = false;
                }
                gui.getCheckInView().resetView();
                gui.getCheckInView().updateView();
                gui.changeView(GUI.CHECKINVIEWINDEX);
            } else {
                // TODO: Warning.
                // TODO: Must be greater than 2.
                System.out.println("I must be more than 2.");
            }

        }
    }

    public class AddBagButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Don't clear the text field that was added.
            // TODO: An easy fix is to put the textFields as instance attributes.
            if (gui.getCheckInView().getNumberOfBagPartialViews() >= 4) {
                gui.getCheckInView().setWarnMaximumNumberOfBagsAdded(true);
            } else {
                gui.getCheckInView().createBagPartialView(new RemoveBagPartialViewButtonListener());
            }
            gui.getCheckInView().updateView();
        }
    }

    public class PreviousPassengerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Tells the checkInView to show different data
            // TODO: in the checkInView.

            CheckInView checkInView = gui.getCheckInView();
            try {
                // Get the current data in checkInView and put it into tempPassengersData.
                cacheCheckInViewData(checkInView);

                // Retrieve the data of the previous passenger if there is any.
                checkInViewPagingIndex -= 1;

                checkInView.setCheckInViewPagingIndex(checkInViewPagingIndex);
                loadCacheCheckInViewData(checkInView);


            } catch (NumberFormatException ex) {
                checkInView.updateView();
            }
        }
    }

    public class NextPassengerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CheckInView checkInView = gui.getCheckInView();

            try {
                // Get the current data in checkInView and put it into tempPassengersData.
                cacheCheckInViewData(checkInView);


                // Retrieve the data of the next passenger if there is any.
                checkInViewPagingIndex += 1;

                checkInView.setCheckInViewPagingIndex(checkInViewPagingIndex);
                loadCacheCheckInViewData(checkInView);

            } catch (NumberFormatException ex) {
                checkInView.updateView();
            }
        }
    }

    public class CheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Need to keep track of bags in Passenger properly.
            // TODO: Use a different passenger index to add the passengers.
            CheckInView checkInView = gui.getCheckInView();

            try {

                cacheCheckInViewData(checkInView);

                // Check if there is any empty data, if there is any,
                // load it up and throw NumberFormatException (following the cacheCheckInView from the above line.
                for (int i = 0; i < kioskCheckInModel.getNumberOfPassengers(); i++) {
                    if (tempPassengersData[i] == null) {
                        checkInViewPagingIndex = i;
                        checkInView.setCheckInViewPagingIndex(checkInViewPagingIndex);
                        loadCacheCheckInViewData(checkInView);
                        checkInView.setWarnEmptyBookingNumberInput(true);
                        checkInView.setWarnEmptyPassportNumberInput(true);
                        checkInView.setWarnEmptyFullNameInput(true);
                        throw new NumberFormatException();
                    }
                }


                boolean allValid = true;
                int pageIndexOfInvalidBookingID = 0;
                boolean foundFirstInvalid = false;

                // Validates all the bookingID.
                for (int i = 0; i < kioskCheckInModel.getNumberOfPassengers(); i++) {

                    if (!kioskCheckInModel.validateBookingID(tempPassengersData[i].getBookingID())) {
                        allValid = false;

                        // If we have found the first invalid bookingID,
                        // Keep track of the pagingIndex it is at.
                        if (!foundFirstInvalid) {
                            pageIndexOfInvalidBookingID = i;
                            foundFirstInvalid = true;
                        }

                        tempInvalidBookingIDData[i] = true;

                    } else {
                        tempInvalidBookingIDData[i] = false;
                    }
                }


                if (allValid) {
                    boardingPassViewPagingIndex = 0;
                    for (int i = 0; i < kioskCheckInModel.getNumberOfPassengers(); i++) {
                        kioskCheckInModel.insertPassenger(tempPassengersData[i]);
                        gui.getBoardingPassView().updateView();
                        gui.changeView(GUI.BOARDINGPASSVIEWINDEX);
                    }
                } else {
                    checkInViewPagingIndex = pageIndexOfInvalidBookingID;
                    gui.getCheckInView().setCheckInViewPagingIndex(checkInViewPagingIndex);
                    loadCacheCheckInViewData(checkInView);
                }

            } catch (NumberFormatException ex) {
                checkInView.updateView();
            }


        }
    }

    public class PreviousBoardingPassButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class NextBoardingPassButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class PrintBoardingPassButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class MainMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.changeView(GUI.MAINMENUVIEWINDEX);
        }
    }

    public class RemoveBagPartialViewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the property of the JButton, which is the index of the bagPartialView.
            // in the LinkedList bagPartialViews in CheckInView.
            int bagPartialViewIndex = (int) ((JButton) e.getSource()).getClientProperty("index");

            gui.getCheckInView().setWarnMaximumNumberOfBagsAdded(false);
            gui.getCheckInView().removeBagPartialView(bagPartialViewIndex);
            gui.getCheckInView().updateView();
        }
    }

    public class OthersSpecialAccommodationCheckBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            CheckInView checkInView = gui.getCheckInView();
            if (checkInView.isOthersSpecialAccommodationCheckBoxSelected()) {
                checkInView.setOthersSpecialAccommodationTextFieldEnabled(true);
            } else {
                checkInView.setOthersSpecialAccommodationTextFieldEnabled(false);
            }
            checkInView.updateView();
        }


    }

    // TODO: Check if there is a way to NOT use kioskCheckInModel.setPassengerIndex,
    // TODO: Try to take out removeAllBagPartialViews method and updateView method in loadCacheCheckInView method.
    /** Caches the checkInView data inputted by the user into tempPassengersData. */
    public void cacheCheckInViewData(CheckInView checkInView) {

        boolean cacheFailed = false;


        String bookingID = checkInView.getBookingNumberFromTextField();

        if (bookingID.isEmpty()) {
            checkInView.setWarnEmptyBookingNumberInput(true);
            cacheFailed = true;
        }


        String passportNumber = checkInView.getPassportNumberFromTextField();

        if (passportNumber.isEmpty()) {
            checkInView.setWarnEmptyPassportNumberInput(true);
            cacheFailed = true;
        }


        String fullName = checkInView.getFullNameFromTextField();

        if (fullName.isEmpty()) {
            checkInView.setWarnEmptyFullNameInput(true);
            cacheFailed = true;
        }


        Bag[] bags = new Bag[checkInView.getNumberOfBagPartialViews()];
        LinkedList<BagPartialView> currBagPartialViews = checkInView.getBagPartialViews();
        for (int i = 0; i < checkInView.getNumberOfBagPartialViews(); i++) {
            String bagColor = currBagPartialViews.get(i).getBagColorFromTextField();
            String bagWeightString = currBagPartialViews.get(i).getBagWeightFromTextField();

            if (bagColor.isEmpty()) {
                // Set warning message.
                cacheFailed = true;
            }

            boolean invalidBagWeight = false;

            if (bagWeightString.isEmpty()) {
                // Set warning message.
                cacheFailed = true;
                invalidBagWeight = true;
            } else {
                if (!isValidDouble(bagWeightString)) {
                    cacheFailed = true;
                    invalidBagWeight = true;
                    // Set warning message.
                }
            }

            if (invalidBagWeight) {
                bags[i] = new Bag(null, bagColor, -1);
            } else {
                bags[i] = new Bag(null, bagColor, Double.valueOf(bagWeightString));
            }

        }

        if (cacheFailed) {
            throw new NumberFormatException();
        }

        tempPassengersData[checkInViewPagingIndex] = new Passenger(bookingID, passportNumber, fullName, bags);

    }

    /** Loads the cache at index tempPassengersData that was saved by checkInView data,
     * if there isn't any, nothing is loaded.
     */
    public void loadCacheCheckInViewData(CheckInView checkInView) {


        checkInView.resetView();
        Passenger nextPassengerData = tempPassengersData[checkInViewPagingIndex];

        if (nextPassengerData != null) {


            for (int i = 0; i < nextPassengerData.getNumberOfBags(); i++) {
                checkInView.createBagPartialView(new RemoveBagPartialViewButtonListener());
            }



            String nextPassengerBookingID = nextPassengerData.getBookingID();
            String nextPassengerPassportNumber = nextPassengerData.getPassportNumber();
            String nextPassengerFullName = nextPassengerData.getFullName();

            checkInView.setBookingNumberTextField(nextPassengerBookingID);
            checkInView.setPassportNumberTextField(nextPassengerPassportNumber);
            checkInView.setFullNameTextField(nextPassengerFullName);

            LinkedList<BagPartialView> nextBagPartialViews = checkInView.getBagPartialViews();
            for (int i = 0; i < nextPassengerData.getNumberOfBags(); i++) {
                nextBagPartialViews.get(i).setBagColorTextField(nextPassengerData.getBag(i).getBagColor());
                nextBagPartialViews.get(i).setBagWeightTextField(String.valueOf(nextPassengerData.getBag(i).getBagWeight()));
            }

            checkInView.setWarnInvalidBookingID(tempInvalidBookingIDData[checkInViewPagingIndex]);
        }
        checkInView.updateView();
    }

    /** This method is used to validate,
     * that the string is in the format of a double.
     */
    private boolean isValidDouble(String d) {

        if (d.isBlank()) {
            return false;
        }

        int numberOfDecimalPoint = 0;

        for (int i = 0; i < d.length(); i++) {
            char character = d.charAt(i);

            if (isCharacterDecimalPoint(character)) {
                numberOfDecimalPoint += 1;
            }

            /* Return false, if character is not a number or a decimal place,
            or if it has more than one decimal point,
            or if the whole score is just one decimal point,
            or if it has more than two decimal places.
             */
            if ((!isCharacterNumber(character) && !isCharacterDecimalPoint(character))
                    || numberOfDecimalPoint > 1
                    || (numberOfDecimalPoint == 1 && (d.length() == 1))
                    || (numberOfDecimalPoint == 1 && ((d.length() - i) > 3))) {
                return false;
            }
        }

        // subjectScore cannot be less than 0, or more than 100.
        double subjectScore = Double.parseDouble(d);
        if (subjectScore < 0 || subjectScore > 100) {
            return false;
        }
        return true;
    }

    /** Return true, iff char CHARACTER is a decimal point.
     * Otherwise, return false.
     */
    public boolean isCharacterDecimalPoint(char character) {
        return (character == 46);
    }

    /** Return true, iff char NUMBER iS a number.
     * Otherwise, return false.
     */
    public boolean isCharacterNumber(char number) {
        return (number >= 48 && number <= 57);
    }
}
