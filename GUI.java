import javax.swing.*;
import java.awt.event.ActionListener;

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

    /** Creates a GUI object that takes in KioskCheckInModel kioskCheckInModel for the view to use. */
    public GUI(KioskCheckInModel kioskCheckInModel) {
        // TODO: Setup.
    }

    /** Sets up the GUIViewPanel. */
    private void setupGUI() {
        // TODO: Setup.
    }

    /** Displays the GUI JFrame window. */
    public void displayy() {
        // TODO: Display the JFrame.
    }

    /** Terminates the program and closes the GUI. */
    public void exit() {
        // TODO: Closes the JFrame GUI and terminates the program.
    }

    /** Changes the view of the CardLayout in GUIViewPanel. */
    public void changeView(String index) {
        // TODO: Changes the GUIViewPanel to the index view panel in the CardLayout.
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

    /** Returns the boardingPassView. */
    public BoardingPassView getBoardingPassView() {
        return boardingPassView;
    }

    /** Adds an ActionListener to ALL checkInCounterButton in all the view objects. */
    public void addAllCheckInCounterButtonsListener(ActionListener listenForAllCheckInCounterButtons) {
        // TODO: Find all the view classes that have checkInCounterButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL checkInKioskButton in all the view objects. */
    public void addAllCheckInKioskButtonsListener(ActionListener listenForAllCheckInKioskButtons) {
        // TODO: Find all the view classes that have checkInKioskButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL singleCheckInButton in all the view objects. */
    public void addAllSingleCheckInButtonsListener(ActionListener listenForAllSingleCheckInButtons) {
        // TODO: Find all the view classes that have singleCheckInButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL groupCheckInButton in all the view objects. */
    public void addAllGroupCheckInButtonsListener(ActionListener listenForAllGroupCheckInButtons) {
        // TODO: Find all the view classes that have groupCheckInButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL addBagButton in all the view objects. */
    public void addAllAddBagButtonsListener(ActionListener listenForAllAddBagButtons) {
        // TODO: Find all the view classes that have addBagButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL previousPassengerButton in all the view objects. */
    public void addAllPreviousPassengerButtonsListener(ActionListener listenForAllPreviousPassengerButtons) {
        // TODO: Find all the view classes that have previousPassengerButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL nextPassengerButton in all the view objects. */
    public void addAllNextPassengerButtonsListener(ActionListener listenForAllNextPassengerButtons) {
        // TODO: Find all the view classes that have nextPassengerButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL checkInButton in all the view objects. */
    public void addAllCheckInButtonsListener(ActionListener listenForAllCheckInButtons) {
        // TODO: Find all the view classes that have checkInButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL previousBoardingPassButton in all the view objects. */
    public void addAllPreviousBoardingPassButtonsListener(ActionListener listenForAllPreviousBoardingPassButtons) {
        // TODO: Find all the view classes that have previousBoardingPassButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL nextBoardingPassButton in all the view objects. */
    public void addAllNextBoardingPassButtonsListener(ActionListener listenForAllNextBoardingPassButtons) {
        // TODO: Find all the view classes that have nextBoardingPassButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL printBoardingPassButton in all the view objects. */
    public void addAllPrintBoardingPassButtonsListener(ActionListener listenForAllPrintBoardingPassButtons) {
        // TODO: Find all the view classes that have printBoardingPassButton and adds the ActionListener to them.
    }

    /** Adds an ActionListener to ALL mainMenuButton in all the view objects. */
    public void addAllMainMenuButtonsListener(ActionListener listenForAllMainMenuButtons) {
        // TODO: Find all the view classes that have mainMenuButton and adds the ActionListener to them.
    }
}
