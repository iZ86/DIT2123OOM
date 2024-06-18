import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/** This class represents the view that is shown after,
 * the user is done viewing all the boarding passes.
 */
public class DoneView {
    /** JPanel for DoneView. */
    JPanel doneViewPanel = new JPanel(new GridBagLayout());
    /** Main Menu Button. */
    JButton mainMenuButton = new JButton("Return to Main Menu");

    /** Creates the DoneView object. */
    public DoneView() {
        setupDoneViewPanel();
    }

    /** Returns doneViewPanel. */
    public JPanel getDoneViewPanel() {
        return doneViewPanel;
    }

    /** Sets up the view panel. */
    private void setupDoneViewPanel() {

        JLabel thankYouLabel = new JLabel("Thank you for using aero-check in service!");
        GridBagConstraints constraintsForThankYouLabel = new GridBagConstraints();
        constraintsForThankYouLabel.gridx = 0;
        constraintsForThankYouLabel.gridy = 0;

        GridBagConstraints constraintsForMainMenuButton = new GridBagConstraints();
        constraintsForMainMenuButton.gridx = 0;
        constraintsForMainMenuButton.gridy = 1;

        doneViewPanel.add(thankYouLabel, constraintsForThankYouLabel);
        doneViewPanel.add(mainMenuButton, constraintsForMainMenuButton);

    }

    /** Adds an action listener to main menu button. */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }
}
