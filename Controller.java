import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;


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
    /** Temp data to keep track of which tempPassengerData's bookingNumber is invalid. */
    private boolean[] tempInvalidBookingNumberData;
    /** Temp data to keep track of which tempPassengerData's bookingNumber is empty. */
    private boolean[] tempWarnEmptyBookingNumberData;
    /** Temp data to keep track of which tempPassengerData's passportNumber is empty. */
    private boolean[] tempWarnEmptyPassportNumberData;
    /** Temp data to keep track of which tempPassengerData's fullName is empty. */
    private boolean[] tempWarnEmptyFullNameData;
    /** Temp data to keep track of which tempPassengerData's question is not answered. */
    private boolean[][] tempWarnQuestionNotAnsweredData;
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
        addChangeListeners();
    }

    /** Adds all the ActionListeners to the GUI respectively. */
    private void addActionListeners() {
        gui.addAllCheckInCounterButtonsListener(new CheckInCounterButtonListener());
        gui.addAllCheckInKioskButtonsListener(new CheckInKioskButtonListener());
        gui.addAllSingleCheckInButtonsListener(new SingleCheckInButtonListener());
        gui.addAllGroupCheckInButtonsListener(new GroupCheckInButtonListener());
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
        gui.addQuestionFiveYesRadioButtonListener(new QuestionFiveYesRadioButtonListener());
        gui.addQuestionAnswerRadioButtonListener(new QuestionAnswerRadioButtonListener());
    }

    /** Adds the ChangeListeners to the GUI respectively. */
    private void addChangeListeners() {
        gui.addNumberOfBagsSpinnerListener(new NumberOfBagsSpinnerListener());
    }

    /** This class is used to change the view to the counterView. */
    public class CheckInCounterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            counterModel.nextCounter();
            gui.getCounterView().updateView();
            gui.changeView(GUI.COUNTERVIEWINDEX);
        }
    }

    /** This class is used to change the view to the checkInOptionView. */
    public class CheckInKioskButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Changes the view to the GUIViewPanel to checkInOptionView.
            CheckInOptionView checkInOptionView = gui.getCheckInOptionView();
            checkInOptionView.resetView();
            checkInOptionView.updateView();
            gui.changeView(GUI.CHECKINOPTIONVIEWINDEX);
        }
    }

    /** Initializes all the temp data arrays. */
    private void initializeTempData(int numberOfData) {
        tempPassengersData = new Passenger[numberOfData];
        tempInvalidBookingNumberData = new boolean[numberOfData];
        tempWarnEmptyBookingNumberData = new boolean[numberOfData];
        tempWarnEmptyPassportNumberData = new boolean[numberOfData];
        tempWarnEmptyFullNameData = new boolean[numberOfData];
        tempWarnQuestionNotAnsweredData = new boolean[numberOfData][gui.getCheckInView().getNumberOfBagCheckInQuestions()];
        for (int i = 0; i < numberOfData; i++) {
            tempInvalidBookingNumberData[i] = false;
            tempWarnEmptyBookingNumberData[i] = false;
            tempWarnEmptyPassportNumberData[i] = false;
            tempWarnEmptyFullNameData[i] = false;
            for (int j = 0; j < gui.getCheckInView().getNumberOfBagCheckInQuestions(); j++) {
                tempWarnQuestionNotAnsweredData[i][j] = false;
            }
        }

    }

    /** This class is used to change the view to the checkInView, with only 1 passenger. */
    public class SingleCheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: set the kioskCheckInModel number of passengers to 1.
            // TODO: the kioskCheckInModel should automatically adjust itself
            // TODO: so no need to touch kioskCheckInModel.
            // TODO: Changes the GUIViewPanel to checkInView.
            kioskCheckInModel.setNumberOfPassengers(1);
            initializeTempData(1);
            gui.getCheckInView().setCheckInViewPagingIndex(0);
            gui.getCheckInView().resetView();
            gui.getCheckInView().updateView();
            gui.changeView(GUI.CHECKINVIEWINDEX);
        }
    }


    /** This class is used to change the view to the checkInView, with only N passengers,
     * where N is set by the user. */
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
                gui.getCheckInView().setCheckInViewPagingIndex(0);
                initializeTempData(numberOfPassengers);
                gui.getCheckInView().resetView();
                gui.getCheckInView().updateView();
                gui.changeView(GUI.CHECKINVIEWINDEX);
            } else {
                CheckInOptionView checkInOptionView = gui.getCheckInOptionView();
                checkInOptionView.resetView();
                checkInOptionView.setWarnMinimumNumberOfPassengers(true);
                checkInOptionView.updateView();
            }

        }
    }

    /** This class is used to change the checkInView to the previous passenger data, only for group check in. */
    public class PreviousPassengerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Tells the checkInView to show different data
            // TODO: in the checkInView.

            CheckInView checkInView = gui.getCheckInView();
            try {
                // Get the current data in checkInView and put it into tempPassengersData.
                cacheCheckInViewData(checkInView);

                checkInView.setCheckInViewPagingIndex(checkInView.getCheckInViewPagingIndex() - 1);
                loadCacheCheckInViewData(checkInView);


            } catch (NumberFormatException ex) {
                checkInView.updateView();
            }
        }
    }

    /** This class is used to change the checkInView to the next passenger data, only for group check in. */
    public class NextPassengerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CheckInView checkInView = gui.getCheckInView();

            try {
                // Get the current data in checkInView and put it into tempPassengersData.
                cacheCheckInViewData(checkInView);

                checkInView.setCheckInViewPagingIndex(checkInView.getCheckInViewPagingIndex() + 1);
                loadCacheCheckInViewData(checkInView);

            } catch (NumberFormatException ex) {
                checkInView.updateView();
            }
        }
    }

    /** This class is used to change the boardingPassView and effectively checks in all the passengers filled,
     * Will prompt error, if there is any such as invalid bookingNumber or empty information. */
    public class CheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Need to keep track of bags in Passenger properly.
            // TODO: Use a different passenger index to add the passengers.
            CheckInView checkInView = gui.getCheckInView();

            try {


                cacheCheckInViewData(checkInView);

                boolean emptyData = false;
                for (int i = kioskCheckInModel.getNumberOfPassengers() - 1; i >= 0 ; i--) {
                    Passenger passengerData = tempPassengersData[i];
                    if (passengerData == null) {
                        checkInView.setCheckInViewPagingIndex(i);
                        tempWarnEmptyBookingNumberData[i] = true;
                        tempWarnEmptyPassportNumberData[i] = true;
                        tempWarnEmptyFullNameData[i] = true;
                        emptyData = true;
                    } else {
                        if (passengerData.getBookingNumber().isEmpty() || passengerData.getBookingNumber().isBlank()) {
                            tempWarnEmptyBookingNumberData[i] = true;
                            checkInView.setCheckInViewPagingIndex(i);
                            emptyData = true;
                        }
                        if (passengerData.getPassportNumber().isEmpty() || passengerData.getPassportNumber().isBlank()) {
                            tempWarnEmptyPassportNumberData[i] = true;
                            checkInView.setCheckInViewPagingIndex(i);
                            emptyData = true;
                        }
                        if (passengerData.getFullName().isEmpty() || passengerData.getFullName().isBlank()) {
                            tempWarnEmptyFullNameData[i] = true;
                            checkInView.setCheckInViewPagingIndex(i);
                            emptyData = true;
                        }


                        if (passengerData.getNumberOfBags() > 0) {
                            for (int j = 0; j < checkInView.getNumberOfBagCheckInQuestions() - 1; j++) {
                                if (passengerData.getBagCheckInQuestionAnswer(j) == 0) {
                                    tempWarnQuestionNotAnsweredData[checkInView.getCheckInViewPagingIndex()][j] = true;
                                    checkInView.setCheckInViewPagingIndex(i);
                                    emptyData = true;
                                }
                            }
                            if (passengerData.getBagCheckInQuestionAnswer(4) == 1 && passengerData.getBagCheckInQuestionAnswer(5) == 0) {
                                tempWarnQuestionNotAnsweredData[checkInView.getCheckInViewPagingIndex()][5] = true;
                                checkInView.setCheckInViewPagingIndex(i);
                                emptyData = true;
                            }
                        }
                    }
                }

                if (emptyData) {

                    loadCacheCheckInViewData(checkInView);
                    throw new NumberFormatException();
                }



                boolean allValid = true;
                int pageIndexOfInvalidBookingNumber = 0;
                boolean foundFirstInvalid = false;

                // Validates all the bookingNumber.
                for (int i = 0; i < kioskCheckInModel.getNumberOfPassengers(); i++) {

                    if (!kioskCheckInModel.validateBookingNumber(tempPassengersData[i].getBookingNumber())) {
                        allValid = false;

                        // If we have found the first invalid bookingNumber,
                        // Keep track of the pagingIndex it is at.
                        if (!foundFirstInvalid) {
                            pageIndexOfInvalidBookingNumber = i;
                            foundFirstInvalid = true;
                        }

                        tempInvalidBookingNumberData[i] = true;

                    } else {
                        tempInvalidBookingNumberData[i] = false;
                    }
                }


                if (allValid) {
                    for (int i = 0; i < kioskCheckInModel.getNumberOfPassengers(); i++) {
                        kioskCheckInModel.insertPassenger(tempPassengersData[i]);
                    }
                    gui.getBoardingPassView().setBoardingPassViewPagingIndex(0);
                    gui.getBoardingPassView().updateView();
                    gui.changeView(GUI.BOARDINGPASSVIEWINDEX);
                } else {
                    gui.getCheckInView().setCheckInViewPagingIndex(pageIndexOfInvalidBookingNumber);
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
            BoardingPassView boardingPassView = gui.getBoardingPassView();
            boardingPassView.setBoardingPassViewPagingIndex(boardingPassView.getBoardingPassViewPagingIndex() - 1);
            boardingPassView.updateView();

        }
    }

    public class NextBoardingPassButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BoardingPassView boardingPassView = gui.getBoardingPassView();
            boardingPassView.setBoardingPassViewPagingIndex(boardingPassView.getBoardingPassViewPagingIndex() + 1);
            boardingPassView.updateView();
        }
    }

    public class PrintBoardingPassButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /** This class changes the view to mainMenuView. */
    public class MainMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.changeView(GUI.MAINMENUVIEWINDEX);
        }
    }

    /** This class sets bag check in questions 1 to 5 radio buttons,
     * whether to be enabled or not based on the number of bags the user has inputted,
     * in checkInView.
     */
    public class NumberOfBagsSpinnerListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            CheckInView checkInView = gui.getCheckInView();

            int number = 5; // Question 1 - 5, not including 5b.
            if (checkInView.getNumberOfBagsFromSpinner() > 0) {
                for (int i = 0; i < number; i++) {
                    checkInView.setQuestionAnswerRadioButtonsEnabled(i, true);
                }
            } else {
                for (int i = 0; i < number; i++) {
                    checkInView.setQuestionAnswerRadioButtonsEnabled(i, false);
                    tempWarnQuestionNotAnsweredData[checkInView.getCheckInViewPagingIndex()][i] = false;
                    checkInView.setWarnUnansweredQuestions(i, false);
                    checkInView.setBagCheckInQuestionAnswer(i, 0);
                }
                checkInView.updateView();
            }
        }
    }

    /** This class sets bag check in questions 5b radio button,
     * whether to be enabled or not based on question 5 yes radio button is selected,
     * in checkInView.
     */
    public class QuestionFiveYesRadioButtonListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            CheckInView checkInView = gui.getCheckInView();

            // If question 5 answer is yes, enable question 5b answer radio buttons.
            // Otherwise, don't
            if (checkInView.getBagCheckInQuestionAnswer(4) == 1) {
                checkInView.setQuestionAnswerRadioButtonsEnabled(5, true);
            } else {
                checkInView.setQuestionAnswerRadioButtonsEnabled(5, false);
            }
        }

    }

    /** This class sets the othersSpecialAccommodationTextField to be enabled,
     * based on whether or not the OtherSpecialAccommodation checkbox is selected in checkInView.
     */
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


    /** This class is used to get rid of the warnings in checkInView when the radio button that represents
     * the answers to the bag check in question is pressed.
     */
    public class QuestionAnswerRadioButtonListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            int answeredQuestionRadioButtonIndex = (int) ((JRadioButton) e.getSource()).getClientProperty("index");
            String answeredQuestionChoice = (String) ((JRadioButton) e.getSource()).getClientProperty("answer");

            CheckInView checkInView = gui.getCheckInView();

            checkInView.setWarnUnansweredQuestions(answeredQuestionRadioButtonIndex, false);
            tempWarnQuestionNotAnsweredData[checkInView.getCheckInViewPagingIndex()][answeredQuestionRadioButtonIndex] = false;
            if (answeredQuestionRadioButtonIndex == 4 && answeredQuestionChoice.equals("No")) {
                checkInView.setWarnUnansweredQuestions(answeredQuestionRadioButtonIndex + 1, false);
                checkInView.setBagCheckInQuestionAnswer(answeredQuestionRadioButtonIndex + 1, 0);
                tempWarnQuestionNotAnsweredData[checkInView.getCheckInViewPagingIndex()][answeredQuestionRadioButtonIndex + 1] = false;
            }
            checkInView.updateView();
        }
    }

    // TODO: Check if there is a way to NOT use kioskCheckInModel.setPassengerIndex,
    // TODO: Try to take out removeAllBagPartialViews method and updateView method in loadCacheCheckInView method.
    /** Caches the checkInView data inputted by the user into tempPassengersData. */
    public void cacheCheckInViewData(CheckInView checkInView) {

        String bookingNumber = checkInView.getBookingNumberFromTextField();
        String passportNumber = checkInView.getPassportNumberFromTextField();
        String fullName = checkInView.getFullNameFromTextField();

        if (!(bookingNumber.isEmpty() && bookingNumber.isBlank())) {
            tempWarnEmptyBookingNumberData[checkInView.getCheckInViewPagingIndex()] = false;
        }

        if (!(passportNumber.isEmpty() && passportNumber.isBlank())) {
            tempWarnEmptyPassportNumberData[checkInView.getCheckInViewPagingIndex()] = false;
        }

        if (!(fullName.isEmpty() && fullName.isBlank())) {
            tempWarnEmptyFullNameData[checkInView.getCheckInViewPagingIndex()] = false;
        }

        Passenger passenger = new Passenger(bookingNumber, passportNumber, fullName);

        passenger.setRequireWheelchair(checkInView.isWheelChairCheckBoxSelected());
        passenger.setBlind(checkInView.isBlindnessCheckBoxSelected());
        passenger.setDeaf(checkInView.isDeafnessCheckBoxSelected());
        passenger.setOtherSpecialAccommodation(checkInView.isOthersSpecialAccommodationCheckBoxSelected());
        if (passenger.isOtherSpecialAccommodation()) {
            passenger.setOtherSpecialAccommodationDetails(checkInView.getOthersSpecialAccommodationTextField());
        }

        int numberOfBags = checkInView.getNumberOfBagsFromSpinner();
        passenger.setBagArraySize(numberOfBags);

        int numberOfBagCheckInQuestions = checkInView.getNumberOfBagCheckInQuestions();
        if (numberOfBags > 0) {
            for (int i = 0; i < numberOfBagCheckInQuestions - 1; i++) {
                passenger.setBagCheckInQuestionAnswer(i, checkInView.getBagCheckInQuestionAnswer(i));
            }

            if (checkInView.getBagCheckInQuestionAnswer(4) == 1) {
                passenger.setBagCheckInQuestionAnswer(5, checkInView.getBagCheckInQuestionAnswer(5));
            }

        } else {
            for (int i = 0; i < numberOfBagCheckInQuestions; i++) {
                passenger.setBagCheckInQuestionAnswer(i, 0);
            }
        }

        tempPassengersData[checkInView.getCheckInViewPagingIndex()] = passenger;

    }

    /** Loads the cache at index tempPassengersData that was saved by checkInView data,
     * if there isn't any, nothing is loaded.
     */
    public void loadCacheCheckInViewData(CheckInView checkInView) {

        // When checkInView.resetView() method is called, it modifies the numberOfBagsSpanner to 0.
        // This causes the tempWarnQuestionNotAnsweredData[checkInViewPagingIndex] to be overridden to all false.
        // During checkInButtonListener actionPerformed method.
        // So this is needed to prevent the overridden.
        int numberOfQuestions = tempWarnQuestionNotAnsweredData[checkInView.getCheckInViewPagingIndex()].length;
        boolean[] tempUnansweredQuestions = new boolean[numberOfQuestions];
        for (int i = 0; i < numberOfQuestions; i++) {
            tempUnansweredQuestions[i] = tempWarnQuestionNotAnsweredData[checkInView.getCheckInViewPagingIndex()][i];
        }

        checkInView.resetView();
        Passenger passengerData = tempPassengersData[checkInView.getCheckInViewPagingIndex()];
        

        tempWarnQuestionNotAnsweredData[checkInView.getCheckInViewPagingIndex()] = tempUnansweredQuestions;

        if (passengerData != null) {

            String nextPassengerBookingNumber = passengerData.getBookingNumber();
            String nextPassengerPassportNumber = passengerData.getPassportNumber();
            String nextPassengerFullName = passengerData.getFullName();

            checkInView.setBookingNumberTextField(nextPassengerBookingNumber);
            checkInView.setPassportNumberTextField(nextPassengerPassportNumber);
            checkInView.setFullNameTextField(nextPassengerFullName);

            checkInView.setWheelchairCheckBoxSelected(passengerData.isRequireWheelchair());
            checkInView.setBlindnessCheckBoxSelected(passengerData.isBlind());
            checkInView.setDeafnessCheckBoxSelected(passengerData.isDeaf());
            checkInView.setOthersSpecialAccommodationCheckBoxSelected(passengerData.isOtherSpecialAccommodation());
            checkInView.setOthersSpecialAccommodationTextField(passengerData.getOtherSpecialAccommodationDetails());



            if (passengerData.getNumberOfBags() > 0) {
                checkInView.setNumberOfBagsSpinner(passengerData.getNumberOfBags());
                for (int i = 0; i < checkInView.getNumberOfBagCheckInQuestions(); i++) {
                    checkInView.setBagCheckInQuestionAnswer(i, passengerData.getBagCheckInQuestionAnswer(i));
                }
            }
        }
        checkInView.setWarnInvalidBookingNumber(tempInvalidBookingNumberData[checkInView.getCheckInViewPagingIndex()]);
        checkInView.setWarnEmptyBookingNumberInput(tempWarnEmptyBookingNumberData[checkInView.getCheckInViewPagingIndex()]);
        checkInView.setWarnEmptyPassportNumberInput(tempWarnEmptyPassportNumberData[checkInView.getCheckInViewPagingIndex()]);
        checkInView.setWarnEmptyFullNameInput(tempWarnEmptyFullNameData[checkInView.getCheckInViewPagingIndex()]);

        for (int i = 0; i < checkInView.getNumberOfBagCheckInQuestions(); i++) {
            checkInView.setWarnUnansweredQuestions(i, tempWarnQuestionNotAnsweredData[checkInView.getCheckInViewPagingIndex()][i]);
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
