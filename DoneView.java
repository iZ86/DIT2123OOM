import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/** This class represents the view that is shown after,
 * the user is done viewing all the boarding passes.
 */
public class DoneView {
    /** JPanel for DoneView. */
    private final JPanel doneViewPanel = new JPanel(new GridBagLayout());
    /** Main Menu Button. */
    private final JButton mainMenuButton = new JButton("Return to Main Menu");

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

        JLabel thankYouLabel1 = new JLabel("Thank You For Using");
        thankYouLabel1.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        JLabel thankYouLabel2 = new JLabel("AeroCheck Check-In Service");
        thankYouLabel2.setFont(new Font("Trebuchet MS", Font.BOLD, 24));

        GridBagConstraints constraintsForThankYouLabel1 = new GridBagConstraints();
        constraintsForThankYouLabel1.gridx = 0;
        constraintsForThankYouLabel1.gridy = 0;
        constraintsForThankYouLabel1.insets = new Insets(0, 0, 10, 0);

        GridBagConstraints constraintsForThankYouLabel2 = new GridBagConstraints();
        constraintsForThankYouLabel2.gridx = 0;
        constraintsForThankYouLabel2.gridy = 1;

        GridBagConstraints constraintsForMainMenuButton = new GridBagConstraints();
        constraintsForMainMenuButton.gridx = 0;
        constraintsForMainMenuButton.gridy = 2;
        constraintsForMainMenuButton.insets = new Insets(50, 0, 0, 0);

        doneViewPanel.add(thankYouLabel1, constraintsForThankYouLabel1);
        doneViewPanel.add(thankYouLabel2, constraintsForThankYouLabel2);

        mainMenuButton.setPreferredSize(new Dimension(240, 50));
        mainMenuButton.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        doneViewPanel.add(mainMenuButton, constraintsForMainMenuButton);
    }

    /** Adds an action listener to main menu button. */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }
}
