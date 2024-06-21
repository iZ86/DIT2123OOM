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

        JLabel errorLabel = new JLabel("NOTICE");
        errorLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
        errorLabel.setForeground(Color.RED.darker());

        JLabel lockedLabel = new JLabel("LOCKED BOOKING NUMBER");
        lockedLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        lockedLabel.setForeground(Color.RED);

        JPanel lockedNumbersPanel = new JPanel();
        lockedNumbersPanel.setLayout(new BoxLayout(lockedNumbersPanel, BoxLayout.Y_AXIS));

        String lockedBookingNumbersString = setupLockedBookingNumbersString();
        JLabel bookingNumberLabel = new JLabel("<html>" + lockedBookingNumbersString.replaceAll(", ", "<br>") + "</html>");
        bookingNumberLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        lockedNumbersPanel.add(bookingNumberLabel);

        JLabel noticeLabel2 = new JLabel("Oops, your booking numbers have been locked.");
        noticeLabel2.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

        JLabel noticeLabel3 = new JLabel("This is due to answering incorrectly.");
        noticeLabel3.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

        JLabel noticeLabel4 = new JLabel("Please head to any counter to resolve this issue.");
        noticeLabel4.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

        GridBagConstraints constraintsForErrorLabel = new GridBagConstraints();
        constraintsForErrorLabel.gridx = 0;
        constraintsForErrorLabel.gridy = 0;
        constraintsForErrorLabel.insets = new Insets(0, 0, 20, 0);

        GridBagConstraints constraintsForLockedLabel = new GridBagConstraints();
        constraintsForLockedLabel.gridx = 0;
        constraintsForLockedLabel.gridy = 1;
        constraintsForLockedLabel.insets = new Insets(20, 0, 10, 0);

        GridBagConstraints constraintsForLockedNumbersPanel = new GridBagConstraints();
        constraintsForLockedNumbersPanel.gridx = 0;
        constraintsForLockedNumbersPanel.gridy = 2;
        constraintsForLockedNumbersPanel.insets = new Insets(5, 0, 20, 0);

        GridBagConstraints constraintsForNoticeLabel2 = new GridBagConstraints();
        constraintsForNoticeLabel2.gridx = 0;
        constraintsForNoticeLabel2.gridy = 3;
        constraintsForNoticeLabel2.insets = new Insets(20, 0, 5, 0);

        GridBagConstraints constraintsForNoticeLabel3 = new GridBagConstraints();
        constraintsForNoticeLabel3.gridx = 0;
        constraintsForNoticeLabel3.gridy = 4;
        constraintsForNoticeLabel3.insets = new Insets(10, 0, 5, 0);

        GridBagConstraints constraintsForNoticeLabel4 = new GridBagConstraints();
        constraintsForNoticeLabel4.gridx = 0;
        constraintsForNoticeLabel4.gridy = 5;
        constraintsForNoticeLabel4.insets = new Insets(10, 0, 20, 0);

        GridBagConstraints constraintsForMainMenuButton = new GridBagConstraints();
        constraintsForMainMenuButton.gridx = 0;
        constraintsForMainMenuButton.gridy = 6;
        constraintsForMainMenuButton.insets = new Insets(30, 0, 0, 0);

        lockedBookingNumberViewPanel.add(errorLabel, constraintsForErrorLabel);
        lockedBookingNumberViewPanel.add(lockedLabel, constraintsForLockedLabel);
        lockedBookingNumberViewPanel.add(lockedNumbersPanel, constraintsForLockedNumbersPanel);
        lockedBookingNumberViewPanel.add(noticeLabel2, constraintsForNoticeLabel2);
        lockedBookingNumberViewPanel.add(noticeLabel3, constraintsForNoticeLabel3);
        lockedBookingNumberViewPanel.add(noticeLabel4, constraintsForNoticeLabel4);

        mainMenuButton.setFocusPainted(false);
        mainMenuButton.setPreferredSize(new Dimension(240, 50));
        mainMenuButton.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        lockedBookingNumberViewPanel.add(mainMenuButton, constraintsForMainMenuButton);
    }

    /** Return a string containing the locked booking numbers and a note. */
    private String setupLockedBookingNumbersString() {
        StringBuilder stringForLockedBookingNumbers = new StringBuilder();

        for (Map.Entry<String, String> entry : lockedBookingNumbers.entrySet()) {
            stringForLockedBookingNumbers.append(entry.getKey());
            stringForLockedBookingNumbers.append(", ");
        }

        if (!lockedBookingNumbers.isEmpty()) {
            stringForLockedBookingNumbers.delete(stringForLockedBookingNumbers.length() - 2, stringForLockedBookingNumbers.length());
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
