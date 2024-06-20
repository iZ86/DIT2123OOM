import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/* This class represents the view for showing the user their designated physical counter, after choosing to check in physically. */
public class CounterView {
    /* JPanel for the CounterView. */
    private final JPanel counterViewPanel = new JPanel(new GridBagLayout());

    /** The model that holds the staff and counter information. */
    private final CounterModel counterModel;

    /** JButton that changes the view to the MainMenuView. */
    private final JButton mainMenuButton = new JButton("Main Menu");

    /** Creates a CounterView object. */
    public CounterView(CounterModel counterModel) {
        this.counterModel = counterModel;
        setupViewPanel();
    }

    /** Sets up the counterViewPanel. */
    private void setupViewPanel() {

        GridBagConstraints titleConstraints = new GridBagConstraints();
        GridBagConstraints infoAreaConstraints = new GridBagConstraints();
        GridBagConstraints mainMenuConstraints = new GridBagConstraints();

        // Title panel with titled border
        JPanel titlePanel = new JPanel(new GridBagLayout());

        JLabel titleLabel = new JLabel("Check-In Counter", JLabel.CENTER);
        titleLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));

        GridBagConstraints constraintsForTitlePanel = new GridBagConstraints();
        constraintsForTitlePanel.gridx = 0;
        constraintsForTitlePanel.gridy = 0;
        constraintsForTitlePanel.insets = new Insets(0, 0, 30, 0);
        titlePanel.add(titleLabel, constraintsForTitlePanel);

        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.fill = GridBagConstraints.BOTH;
        titleConstraints.insets = new Insets(20, 0, 10, 0);
        counterViewPanel.add(titlePanel, titleConstraints);

        // Panel for staff and counter information
        JPanel infoAreaPanel = new JPanel(new GridBagLayout());
        infoAreaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Staff and Counter Information ", TitledBorder.CENTER, TitledBorder.TOP, new Font("Verdana", Font.PLAIN, 14)));
        infoAreaPanel.setPreferredSize(new Dimension(330, 240));

        infoAreaConstraints.gridx = 0;
        infoAreaConstraints.gridy = 1;
        infoAreaConstraints.insets = new Insets(0, 0, 20, 0);
        counterViewPanel.add(infoAreaPanel, infoAreaConstraints);

        GridBagConstraints infoConstraints = new GridBagConstraints();
        infoConstraints.gridx = 0;
        infoConstraints.insets = new Insets(15, 0, 15, 0);
        infoConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel counterNumberLabel = new JLabel(String.format("Counter: %d", counterModel.getCounter()), JLabel.CENTER);
        counterNumberLabel.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        infoConstraints.gridy = 0;
        infoAreaPanel.add(counterNumberLabel, infoConstraints);

        JLabel staffIDLabel = new JLabel("Staff ID: " + counterModel.getStaffID(), JLabel.CENTER);
        staffIDLabel.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        infoConstraints.gridy = 1;
        infoAreaPanel.add(staffIDLabel, infoConstraints);

        JLabel staffNameLabel = new JLabel("Staff Name: " + counterModel.getStaffName(), JLabel.CENTER);
        staffNameLabel.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        infoConstraints.gridy = 2;
        infoAreaPanel.add(staffNameLabel, infoConstraints);

        JLabel staffRoleLabel = new JLabel("Staff Role: " + counterModel.getStaffRole(), JLabel.CENTER);
        staffRoleLabel.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        infoConstraints.gridy = 3;
        infoAreaPanel.add(staffRoleLabel, infoConstraints);

        // Main Menu button outside the titled border
        mainMenuButton.setPreferredSize(new Dimension(240, 50));
        mainMenuButton.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        mainMenuButton.setFocusPainted(false);
        mainMenuConstraints.gridx = 0;
        mainMenuConstraints.gridy = 2; // Adjust this index to place it below the infoAreaPanel
        mainMenuConstraints.insets = new Insets(30, 0, 0, 0);
        counterViewPanel.add(mainMenuButton, mainMenuConstraints);
    }

    /** Clears the view. */
    private void clearView() {
        counterViewPanel.removeAll();
    }

    /** Updates the view. */
    public void updateView() {
        clearView();
        setupViewPanel();
        counterViewPanel.revalidate();
        counterViewPanel.repaint();
    }

    /** Returns the counterViewPanel. */
    public JPanel getCounterViewPanel() {
        return counterViewPanel;
    }

    /** Adds an ActionListener to the JButton mainMenuButton. */
    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton) {
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }
}
