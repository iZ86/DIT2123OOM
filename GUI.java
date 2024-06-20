import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/** This class is the GUI of the program, it creates a window to display all the necessary view classes. */
public class GUI {
    /** The JFrame of the GUI, creates a window for the program. */
    private JFrame GUI;
    /** The GUI view panel that will occupy the JFrame, it uses a CardLayout to switch to other view panels. */
    private JPanel GUIViewPanel;
    /** The main menu view. */
    private MainMenuView mainMenuView;
    /** The counter view. */
    private CounterView counterView;
    /** The check in option view. */
    private CheckInOptionView checkInOptionView;
    /** The check in view. */
    private CheckInView checkInView;
    /** The boarding pass view. */
    private BoardingPassView boardingPassView;
    /** The done view. */
    private DoneView doneView;
    /** The locked booking number view. */
    private LockedBookingNumberView lockedBookingNumberView;
    /** Index used to access mainMenuView in the GUIViewPanel. */
    public static final String MAINMENUVIEWINDEX = "MainMenuView";
    /** Index used to access counterView in the GUIViewPanel. */
    public static final String COUNTERVIEWINDEX = "CounterView";
    /** Index used to access checkInOptionView in the GUIViewPanel. */
    public static final String CHECKINOPTIONVIEWINDEX = "CheckInOptionView";
    /** Index used to access checkInView in the GUIViewPanel. */
    public static final String CHECKINVIEWINDEX = "CheckInView";
    /** Index used to access boardingPassView in the GUIViewPanel. */
    public static final String BOARDINGPASSVIEWINDEX = "BoardingPassView";
    /** Index used to access doneView in the GUIViewPanel. */
    public static final String DONEVIEWINDEX = "DoneView";
    /** Index used to access lockedBookingNumberView in the GUIViewPanel. */
    public static final String LOCKEDBOOKINGNUMERVIEWINDEX = "LockedBookingNumberView";

    /** Creates a GUI object that takes in KioskCheckInModel kioskCheckInModel for the view to use. */
    public GUI(CounterModel counterModel, KioskCheckInModel kioskCheckInModel) {
        GUI = new JFrame("Aerocheck in");

        GUIViewPanel = new JPanel(new CardLayout());
        mainMenuView = new MainMenuView();
        counterView = new CounterView(counterModel);
        checkInOptionView = new CheckInOptionView();
        checkInView = new CheckInView(kioskCheckInModel);
        boardingPassView = new BoardingPassView(kioskCheckInModel);
        doneView = new DoneView();
        lockedBookingNumberView = new LockedBookingNumberView();

        setupGUI();

        GUI.add(GUIViewPanel);

        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.setSize(new Dimension(400, 600));
    }

    /** Sets up the GUIViewPanel. */
    private void setupGUI() {
        GUIViewPanel.add(mainMenuView.getMainMenuViewPanel(), MAINMENUVIEWINDEX);
        GUIViewPanel.add(counterView.getCounterViewPanel(), COUNTERVIEWINDEX);
        GUIViewPanel.add(checkInOptionView.getCheckInOptionViewPanel(), CHECKINOPTIONVIEWINDEX);
        GUIViewPanel.add(checkInView.getCheckInViewPanel(), CHECKINVIEWINDEX);
        GUIViewPanel.add(boardingPassView.getBoardingPassViewPanel(), BOARDINGPASSVIEWINDEX);
        GUIViewPanel.add(doneView.getDoneViewPanel(), DONEVIEWINDEX);
        GUIViewPanel.add(lockedBookingNumberView.getLockedBookingNumberViewPanel(), LOCKEDBOOKINGNUMERVIEWINDEX);
    }

    /** Displays the GUI JFrame window. */
    public void display() {
        GUI.setVisible(true);
    }

    /** Terminates the program and closes the GUI. */
    public void exit() {
        GUI.dispose();
    }

    /** Changes the view of the CardLayout in GUIViewPanel. */
    public void changeView(String index) {
        CardLayout cl = (CardLayout) (GUIViewPanel.getLayout());
        cl.show(GUIViewPanel, index);
    }

    /** Returns the mainMenuView. */
    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    /** Returns the counterView. */
    public CounterView getCounterView() {
        return counterView;
    }

    /** Returns the checkInOptionView. */
    public CheckInOptionView getCheckInOptionView() {
        return checkInOptionView;
    }

    /** Returns the checkInView. */
    public CheckInView getCheckInView() {
        return checkInView;
    }

    /** Returns the boardingPassView. */
    public BoardingPassView getBoardingPassView() {
        return boardingPassView;
    }

    /** Returns the lockedBookingNumberView. */
    public LockedBookingNumberView getLockedBookingNumberView() {
        return lockedBookingNumberView;
    }

    /** Adds an ActionListener to ALL checkInCounterButton in all the view objects. */
    public void addAllCheckInCounterButtonsListener(ActionListener listenForAllCheckInCounterButtons) {
        mainMenuView.addCheckInCounterButtonListener(listenForAllCheckInCounterButtons);
    }

    /** Adds an ActionListener to ALL checkInKioskButton in all the view objects. */
    public void addAllCheckInKioskButtonsListener(ActionListener listenForAllCheckInKioskButtons) {
        mainMenuView.addCheckInKioskButtonListener(listenForAllCheckInKioskButtons);
        checkInView.addCheckInKioskButtonListener(listenForAllCheckInKioskButtons);
    }

    /** Adds an ActionListener to ALL singleCheckInButton in all the view objects. */
    public void addAllSingleCheckInButtonsListener(ActionListener listenForAllSingleCheckInButtons) {
        checkInOptionView.addSingleButtonListener(listenForAllSingleCheckInButtons);
    }

    /** Adds an ActionListener to ALL groupCheckInButton in all the view objects. */
    public void addAllGroupCheckInButtonsListener(ActionListener listenForAllGroupCheckInButtons) {
        checkInOptionView.addGroupButtonListener(listenForAllGroupCheckInButtons);
    }

    /** Adds an ActionListener to ALL previousPassengerButton in all the view objects. */
    public void addAllPreviousPassengerButtonsListener(ActionListener listenForAllPreviousPassengerButtons) {
        checkInView.addPreviousPassengerButtonListener(listenForAllPreviousPassengerButtons);
    }

    /** Adds an ActionListener to ALL nextPassengerButton in all the view objects. */
    public void addAllNextPassengerButtonsListener(ActionListener listenForAllNextPassengerButtons) {
        checkInView.addNextPassengerButtonListener(listenForAllNextPassengerButtons);
    }

    /** Adds an ActionListener to ALL checkInButton in all the view objects. */
    public void addAllCheckInButtonsListener(ActionListener listenForAllCheckInButtons) {
        checkInView.addCheckInButtonListener(listenForAllCheckInButtons);
    }

    /** Adds an ActionListener to ALL previousBoardingPassButton in all the view objects. */
    public void addAllPreviousBoardingPassButtonsListener(ActionListener listenForAllPreviousBoardingPassButtons) {
        boardingPassView.addPreviousBoardingPassButtonListener(listenForAllPreviousBoardingPassButtons);
    }

    /** Adds an ActionListener to ALL nextBoardingPassButton in all the view objects. */
    public void addAllNextBoardingPassButtonsListener(ActionListener listenForAllNextBoardingPassButtons) {
        boardingPassView.addNextBoardingPassButtonListener(listenForAllNextBoardingPassButtons);
    }

    /** Adds an ActionListener to ALL printBoardingPassButton in all the view objects. */
    public void addAllPrintBoardingPassButtonsListener(ActionListener listenForAllPrintBoardingPassButtons) {
        boardingPassView.addPrintBoardingPassButtonListener(listenForAllPrintBoardingPassButtons);
    }

    /** Adds an ActionListener to ALL mainMenuButton in all the view objects. */
    public void addAllMainMenuButtonsListener(ActionListener listenForAllMainMenuButtons) {
        checkInOptionView.addMainMenuButtonListener(listenForAllMainMenuButtons);
        counterView.addMainMenuButtonListener(listenForAllMainMenuButtons);
        checkInView.addMainMenuButtonListener(listenForAllMainMenuButtons);
        doneView.addMainMenuButtonListener(listenForAllMainMenuButtons);
        lockedBookingNumberView.addMainMenuButtonListener(listenForAllMainMenuButtons);
    }

    /** Adds an ActionListener to doneButton in boardingPassView. */
    public void addDoneButtonListener(ActionListener listenForDoneButton) {
        boardingPassView.addDoneButtonListener(listenForDoneButton);
    }

    /** Adds an ItemListener to the JCheckBox othersSpecialAccommodationCheckBox in checkInView. */
    public void addOthersSpecialAccommodationCheckBox(ItemListener listenForOthersSpecialAccommodationCheckBox) {
        checkInView.addOthersSpecialAccommodationCheckBoxItemListener(listenForOthersSpecialAccommodationCheckBox);
    }

    /** Adds a ChangeListener to the JSpinner numberOfBagsSpinner in checkInView. */
    public void addNumberOfBagsSpinnerListener(ChangeListener listenForNumberOfBagsSpinner) {
        checkInView.addNumberOfBagsSpinnerListener(listenForNumberOfBagsSpinner);
    }

    /** Adds an ItemListener to the JRadioButton questionFiveYesButton in checkInView. */
    public void addQuestionFiveYesRadioButtonListener(ItemListener listenForQuestionFiveYesRadioButton) {
        checkInView.addQuestionFiveYesRadioButtonListener(listenForQuestionFiveYesRadioButton);
    }


    /** Adds an ItemListener to all the JRadioButton yes and no answers for the bag check in question in checkInView. */
    public void addQuestionAnswerRadioButtonListener(ItemListener listenForQuestionAnswerRadioButton) {
        checkInView.addQuestionAnswerRadioButtonListener(listenForQuestionAnswerRadioButton);
    }
}
