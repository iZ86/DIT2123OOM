import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** This class acts as the controller of the MVC structure in this program. */
public class Controller {
    /** gui. */
    private GUI gui;
    /** The model to hold all the data. */
    private KioskCheckInModel kioskCheckInModel;

    /** Creates a Controller object with GUI gui, and KioskCheckInModel kioskCheckInModel,
     * acts as the bridge between GUI gui and KioskCheckInModekl kioskCheckInModel,
     * controlling the workflow.
     */
    public Controller(GUI gui, KioskCheckInModel kioskCheckInModel) {
        this.gui = gui;
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

        }
    }

    public class CheckInKioskButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class SingleCheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class GroupCheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class AddBagButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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

        }
    }

    public class NextPassengerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class CheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

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
}
