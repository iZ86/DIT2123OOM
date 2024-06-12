import javax.swing.*;
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

    /** Creates a GUI object that takes in KioskCheckInModel kioskCheckInModel for the view to use. */
    public GUI(CounterModel counterModel, KioskCheckInModel kioskCheckInModel) {
        GUI = new JFrame("Aerocheck in");

        GUIViewPanel = new JPanel(new CardLayout());
        mainMenuView = new MainMenuView();
        counterView = new CounterView(counterModel);
        checkInOptionView = new CheckInOptionView();
        checkInView = new CheckInView(kioskCheckInModel);
        boardingPassView = new BoardingPassView(kioskCheckInModel);

        setupGUI();

        GUI.add(GUIViewPanel);

        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.setSize(new Dimension(400, 600));
    }

    /** Sets up the GUIViewPanel. */
    private void setupGUI() {
        // TODO: Commented first, since they do not exist yet.
        GUIViewPanel.add(mainMenuView.getMainMenuViewPanel(), MAINMENUVIEWINDEX);
        GUIViewPanel.add(counterView.getCounterViewPanel(), COUNTERVIEWINDEX);
        GUIViewPanel.add(checkInOptionView.getCheckInOptionViewPanel(), CHECKINOPTIONVIEWINDEX);
        GUIViewPanel.add(checkInView.getCheckInViewPanel(), CHECKINVIEWINDEX);
        GUIViewPanel.add(boardingPassView.getBoardingPassViewPanel(), BOARDINGPASSVIEWINDEX);
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

    /** Adds an ActionListener to ALL checkInCounterButton in all the view objects. */
    public void addAllCheckInCounterButtonsListener(ActionListener listenForAllCheckInCounterButtons) {
        // TODO: Find all the view classes that have checkInCounterButton and adds the ActionListener to them.
        mainMenuView.addCheckInCounterButtonListener(listenForAllCheckInCounterButtons);
    }

    /** Adds an ActionListener to ALL checkInKioskButton in all the view objects. */
    public void addAllCheckInKioskButtonsListener(ActionListener listenForAllCheckInKioskButtons) {
        // TODO: Find all the view classes that have checkInKioskButton and adds the ActionListener to them.
        // MainMenuView.
        mainMenuView.addCheckInKioskButtonListener(listenForAllCheckInKioskButtons);
        checkInView.addCheckInKioskButtonListener(listenForAllCheckInKioskButtons);
    }

    /** Adds an ActionListener to ALL singleCheckInButton in all the view objects. */
    public void addAllSingleCheckInButtonsListener(ActionListener listenForAllSingleCheckInButtons) {
        // TODO: Find all the view classes that have singleCheckInButton and adds the ActionListener to them.
        // CheckInOptionView.
        checkInOptionView.addSingleButtonListener(listenForAllSingleCheckInButtons);
    }

    /** Adds an ActionListener to ALL groupCheckInButton in all the view objects. */
    public void addAllGroupCheckInButtonsListener(ActionListener listenForAllGroupCheckInButtons) {
        // TODO: Find all the view classes that have groupCheckInButton and adds the ActionListener to them.
        // CheckInOptionView.
        checkInOptionView.addGroupButtonListener(listenForAllGroupCheckInButtons);
    }

    /** Adds an ActionListener to ALL previousPassengerButton in all the view objects. */
    public void addAllPreviousPassengerButtonsListener(ActionListener listenForAllPreviousPassengerButtons) {
        // TODO: Find all the view classes that have previousPassengerButton and adds the ActionListener to them.
        checkInView.addPreviousPassengerButtonListener(listenForAllPreviousPassengerButtons);
    }

    /** Adds an ActionListener to ALL nextPassengerButton in all the view objects. */
    public void addAllNextPassengerButtonsListener(ActionListener listenForAllNextPassengerButtons) {
        // TODO: Find all the view classes that have nextPassengerButton and adds the ActionListener to them.
        checkInView.addNextPassengerButtonListener(listenForAllNextPassengerButtons);
    }

    /** Adds an ActionListener to ALL checkInButton in all the view objects. */
    public void addAllCheckInButtonsListener(ActionListener listenForAllCheckInButtons) {
        // TODO: Find all the view classes that have checkInButton and adds the ActionListener to them.
        checkInView.addCheckInButtonListener(listenForAllCheckInButtons);
    }

    /** Adds an ActionListener to ALL previousBoardingPassButton in all the view objects. */
    public void addAllPreviousBoardingPassButtonsListener(ActionListener listenForAllPreviousBoardingPassButtons) {
        // TODO: Find all the view classes that have previousBoardingPassButton and adds the ActionListener to them.
        boardingPassView.addPreviousBoardingPassButtonListener(listenForAllPreviousBoardingPassButtons);
    }

    /** Adds an ActionListener to ALL nextBoardingPassButton in all the view objects. */
    public void addAllNextBoardingPassButtonsListener(ActionListener listenForAllNextBoardingPassButtons) {
        // TODO: Find all the view classes that have nextBoardingPassButton and adds the ActionListener to them.
        boardingPassView.addNextBoardingPassButtonListener(listenForAllNextBoardingPassButtons);
    }

    /** Adds an ActionListener to ALL printBoardingPassButton in all the view objects. */
    public void addAllPrintBoardingPassButtonsListener(ActionListener listenForAllPrintBoardingPassButtons) {
        // TODO: Find all the view classes that have printBoardingPassButton and adds the ActionListener to them.
        boardingPassView.addPrintBoardingPassButtonListener(listenForAllPrintBoardingPassButtons);
    }

    /** Adds an ActionListener to ALL mainMenuButton in all the view objects. */
    public void addAllMainMenuButtonsListener(ActionListener listenForAllMainMenuButtons) {
        // TODO: Find all the view classes that have mainMenuButton and adds the ActionListener to them.
        // CounterView.
        // CheckInOptionView.
        checkInOptionView.addMainMenuButtonListener(listenForAllMainMenuButtons);
        counterView.addMainMenuButtonListener(listenForAllMainMenuButtons);
        checkInView.addMainMenuButtonListener(listenForAllMainMenuButtons);
        boardingPassView.addMainMenuButtonListener(listenForAllMainMenuButtons);
    }

    /** Adds an ItemListener to the JCheckBox othersSpecialAccommodationCheckBox in checkInView. */
    public void addOthersSpecialAccommodationCheckBox(ItemListener listenForOthersSpecialAccommodationCheckBox) {
        checkInView.addOthersSpecialAccommodationCheckBoxItemListener(listenForOthersSpecialAccommodationCheckBox);
    }
}
