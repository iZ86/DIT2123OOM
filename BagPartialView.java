import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BagPartialView {

    /** JPanel for BagPartialView*/
    private JPanel bagPartialViewPanel;
    /** The text field where user enters the bag color*/
    private JTextField bagColorTextField;
    /** The text field where user enters the bag weight*/
    private JTextField bagWeightTextField;
    /** JButton that removes this BagPartialView from CheckInView*/
    private JButton removeBagPartialViewButton;

    /** Creates a BagPartialView object. */
    public BagPartialView() {
        bagPartialViewPanel = new JPanel(new GridBagLayout());
        setupViewPanel();
    }

    /** Adds an ActionListener to JButton removeBagPartialViewButton. */
    public void addRemoveBagPartialViewButtonListener(ActionListener listenForRemoveBagPartialViewButton) {
        removeBagPartialViewButton.addActionListener(listenForRemoveBagPartialViewButton);
    }

    /** Sets the removeBagPartialView property client key of "index" to int index. */
    public void setIndex(int index) {
        removeBagPartialViewButton.putClientProperty("index", index);
    }

    /** Sets up the bagPartialViewPanel*/
    private void setupViewPanel() {
        //TODO: Add ScrollBar or ScrollPane
        int horizontalSizeOfTextField = 155;
        int verticalSizeOfTextField = 5;

        int horizontalSizeOfCloseButton = 18;
        int verticalSizeOfCloseButton = 3;

        GridBagConstraints gridBagConstraintsForBaggageLabels = new GridBagConstraints();
        GridBagConstraints gridBagConstraintsForBaggageTextFields = new GridBagConstraints();
        GridBagConstraints gridBagConstraintsForCloseButton = new GridBagConstraints();

        JLabel labelForBagColor = new JLabel("Bag Color");
        bagColorTextField = new JTextField();

        JLabel labelForBagWeight = new JLabel("Bag Weight");
        bagWeightTextField = new JTextField();

        removeBagPartialViewButton = new JButton("Remove");

        gridBagConstraintsForBaggageLabels.anchor = GridBagConstraints.WEST;
        gridBagConstraintsForBaggageLabels.insets = new Insets(0, 0, 15, 45);

        gridBagConstraintsForBaggageTextFields.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraintsForBaggageTextFields.ipadx = horizontalSizeOfTextField;
        gridBagConstraintsForBaggageTextFields.ipady = verticalSizeOfTextField;

        gridBagConstraintsForBaggageTextFields.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraintsForBaggageTextFields.insets = new Insets(0, 20, 15, 0);


        gridBagConstraintsForCloseButton.ipady = verticalSizeOfCloseButton;
        gridBagConstraintsForCloseButton.ipadx = horizontalSizeOfCloseButton;

        gridBagConstraintsForCloseButton.gridx = 1;
        gridBagConstraintsForCloseButton.insets = new Insets(0, 70, 10, 0);

        bagPartialViewPanel.add(labelForBagColor, gridBagConstraintsForBaggageLabels);
        bagPartialViewPanel.add(bagColorTextField, gridBagConstraintsForBaggageTextFields);
        bagPartialViewPanel.add(labelForBagWeight, gridBagConstraintsForBaggageLabels);
        bagPartialViewPanel.add(bagWeightTextField, gridBagConstraintsForBaggageTextFields);
        bagPartialViewPanel.add(removeBagPartialViewButton, gridBagConstraintsForCloseButton);
    }

    /** Returns bagPartialViewPanel*/
    public JPanel getBagPartialViewPanel() {
        return bagPartialViewPanel;
    }

    /** Returns the bag color from the text field set by the user*/
    public String getBagColorFromTextField() {
        return bagColorTextField.getText();
    }

    /** Sets the bagColorTextField with String bagColor*/
    public void setBagColorTextField(String bagColor) {
        bagColorTextField.setText(bagColor);
    }

    /** Returns the bag weight from the text field set by the user*/
    public String getBagWeightFromTextField() {
        return bagWeightTextField.getText();
    }

    /** Sets the bagWeightTextField with String bagWeight*/
    public void setBagWeightTextField(String bagWeight) {
        bagWeightTextField.setText(bagWeight);
    }
}
