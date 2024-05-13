import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    /** Creates a Controller object with GUI gui, and KioskCheckInModel kioskCheckInModel,
     * acts as the bridge between GUI gui and KioskCheckInModekl kioskCheckInModel,
     * controlling the workflow.
     */
    public Controller(GUI gui, CounterModel counterModel, KioskCheckInModel kioskCheckInModel) {
        this.gui = gui;
        this.counterModel = counterModel;
        this.kioskCheckInModel = kioskCheckInModel;
        addActionListeners();
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
            tempPassengersData = new Passenger[1];
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
            kioskCheckInModel.setNumberOfPassengers(numberOfPassengers);
            tempPassengersData = new Passenger[numberOfPassengers];
            gui.getCheckInView().updateView();
            gui.changeView(GUI.CHECKINVIEWINDEX);
        }
    }

    public class AddBagButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Don't clear the text field that was added.
            // TODO: An easy fix is to put the textFields as instance attributes.
            if (gui.getCheckInView().getNumberOfBagPartialViews() >= 4) {
                gui.getCheckInView().maximumBagErrorMessage();
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
                int prevIndex = kioskCheckInModel.getPassengerIndex() - 1;
                loadCacheCheckInViewData(checkInView, prevIndex);


            } catch (NumberFormatException ex) {
                // TODO: Temporary.
                JOptionPane.showMessageDialog(new JFrame(),
                        "Number format exception",
                        "AeroCheck In",
                        JOptionPane.ERROR_MESSAGE);

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
                int nextIndex = kioskCheckInModel.getPassengerIndex() + 1;
                loadCacheCheckInViewData(checkInView, nextIndex);

            } catch (NumberFormatException ex) {
                // TODO: Temporary.
                JOptionPane.showMessageDialog(new JFrame(),
                        "Number format exception",
                        "AeroCheck In",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public class CheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Need to keep track of bags in Passenger properly.
            // TODO: Use a different passenger index to add the passengers.
            cacheCheckInViewData(gui.getCheckInView());
            kioskCheckInModel.setPassengerIndex(0);
            for (Passenger tempPassengerData : tempPassengersData) {
                kioskCheckInModel.insertPassenger(tempPassengerData);
            }
            kioskCheckInModel.setPassengerIndex(0);
            gui.getBoardingPassView().updateView();
            gui.changeView(GUI.BOARDINGPASSVIEWINDEX);
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

            gui.getCheckInView().removeBagPartialView(bagPartialViewIndex);
            gui.getCheckInView().updateView();
        }
    }

    // TODO: Check if there is a way to NOT use kioskCheckInModel.setPassengerIndex,
    // TODO: Try to take out removeAllBagPartialViews method and updateView method in loadCacheCheckInView method.
    /** Caches the checkInView data inputted by the user into tempPassengersData. */
    public void cacheCheckInViewData(CheckInView checkInView) {
        String bookingID = checkInView.getBookingNumberFromTextField();
        String passportNumber = checkInView.getPassportNumberFromTextField();
        String fullName = checkInView.getFullNameFromTextField();
        Bag[] bags = new Bag[checkInView.getNumberOfBagPartialViews()];
        LinkedList<BagPartialView> currBagPartialViews = checkInView.getBagPartialViews();
        for (int i = 0; i < checkInView.getNumberOfBagPartialViews(); i++) {
            bags[i] = new Bag(null, currBagPartialViews.get(i).getBagColorFromTextField(), Double.valueOf(currBagPartialViews.get(i).getBagWeightFromTextField()));
        }
        int currIndex = kioskCheckInModel.getPassengerIndex();
        tempPassengersData[currIndex] = new Passenger(bookingID, passportNumber, fullName, bags);
    }

    /** Loads the cache at index tempPassengersData that was saved by checkInView data,
     * if there isn't any, nothing is loaded.
     */
    public void loadCacheCheckInViewData(CheckInView checkInView, int index) {

        checkInView.removeAllBagPartialViews();
        Passenger nextPassengerData = tempPassengersData[index];
        kioskCheckInModel.setPassengerIndex(index);
        checkInView.updateView();

        if (nextPassengerData != null) {


            for (int i = 0; i < nextPassengerData.getNumberOfBags(); i++) {
                checkInView.createBagPartialView(new RemoveBagPartialViewButtonListener());
            }

            checkInView.updateView();

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
        }
    }
}
