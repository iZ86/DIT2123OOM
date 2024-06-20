import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

/** This class represents the view to show the user that their booking numbers has been locked. */
public class LockedBookingNumberView {
    /** CSS used to wrap String in JLabels. */
    private static final String CSSFORMAT = "<html><body style='width: %1spx; text-align: center;'>%1s";
    /** JPanel for LockedBookingNumberView. */
    private final JPanel lockedBookingNumberViewPanel = new JPanel(new GridBagLayout());
    /** List of locked booking numbers, key is the booking numbers, value is always null.
     * This is because of faster look up and automatically ordered.
     */
    private TreeMap<String, String> lockedBookingNumbers = new TreeMap<>();
    /** Main menu button. */
    private final JButton mainMenuButton = new JButton("Main Menu");

    public LockedBookingNumberView() {
        setupViewPanel();
    }

    /** Clears the view. */
    private void clearView() {
        lockedBookingNumberViewPanel.removeAll();
    }

    /** Updates the view. */
    public void updateView() {
        clearView();
        setupViewPanel();
        lockedBookingNumberViewPanel.revalidate();
        lockedBookingNumberViewPanel.repaint();
    }

    /** Sets lockedBookingNumbers. */
    public void setLockedBookingNumbers(TreeMap<String, String> lockedBookingNumbers) {
        this.lockedBookingNumbers = lockedBookingNumbers;
    }

    /** Sets up the view panel. */
    private void setupViewPanel() {
        JLabel noticeLabel = new JLabel(String.format(CSSFORMAT,
                300,
                "Unfortunately, these booking numbers has been locked, "
                        + "due to answering incorrectly. "
                        + "Please head to any counter to resolve this issue."));
        noticeLabel.setForeground(Color.RED);

        String lockedBookingNumbersString = setupLockedBookingNumbersString();


        JLabel lockedBookingNumbersLabel = new JLabel(String.format(CSSFORMAT, 300, lockedBookingNumbersString));
        lockedBookingNumbersLabel.setForeground(Color.RED);

        GridBagConstraints constraintsForNoticeLabel = new GridBagConstraints();
        constraintsForNoticeLabel.gridx = 0;
        constraintsForNoticeLabel.gridy = 0;
        constraintsForNoticeLabel.insets = new Insets(0, 0, 50, 0);

        GridBagConstraints constraintsForLockedBookingNumbersLabel = new GridBagConstraints();
        constraintsForLockedBookingNumbersLabel.gridx = 0;
        constraintsForLockedBookingNumbersLabel.gridy = 1;
        constraintsForLockedBookingNumbersLabel.insets = new Insets(0, 0, 50, 0);

        GridBagConstraints constraintsForMainMenuButton = new GridBagConstraints();
        constraintsForMainMenuButton.gridx = 0;
        constraintsForMainMenuButton.gridy = 2;

        lockedBookingNumberViewPanel.add(noticeLabel, constraintsForNoticeLabel);
        lockedBookingNumberViewPanel.add(lockedBookingNumbersLabel, constraintsForLockedBookingNumbersLabel);
        lockedBookingNumberViewPanel.add(mainMenuButton, constraintsForMainMenuButton);


    }

    /** Return a string containing the locked booking numbers and a note. */
    private String setupLockedBookingNumbersString() {
        StringBuilder stringForLockedBookingNumbers = new StringBuilder("These are the locked booking numbers: ");

        for (Map.Entry<String, String> entry : lockedBookingNumbers.entrySet()) {
            stringForLockedBookingNumbers.append(entry.getKey());
            stringForLockedBookingNumbers.append(", ");
        }

        if (!lockedBookingNumbers.isEmpty()) {
            stringForLockedBookingNumbers.delete(stringForLockedBookingNumbers.length() - 2, stringForLockedBookingNumbers.length());
            stringForLockedBookingNumbers.append(".");
        }

        return stringForLockedBookingNumbers.toString();
    }

    /** Return lockedBookingNumberViewPanel. */
    public JPanel getLockedBookingNumberViewPanel() {
        return lockedBookingNumberViewPanel;
    }

    /** Adds an ActionListener to mainMenuButton. */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }
}
