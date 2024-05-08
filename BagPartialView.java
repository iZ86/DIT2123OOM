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
    /** JButton that deletes this BagPartialView from CheckInView*/
    private JButton closeBagPartialViewButton;

    /** Creates a BagPartialView object with ActionListener listenForCloseBagPartialViewButton*/
    public BagPartialView() {
        bagPartialViewPanel = new JPanel(new GridBagLayout());
        setupViewPanel();
    }

    /** Adds an ActionListener to JButton closeBagPartialViewButton*/
    public void addCloseBagPartialViewButtonListener(ActionListener listenForCloseBagPartialViewButton) {
        closeBagPartialViewButton.addActionListener(listenForCloseBagPartialViewButton);
    }

    /** Sets up the bagPartialViewPanel*/
    private void setupViewPanel() {
        //TODO: Find out how the panel add vetically instead of horizontally
        //TODO: Add ScrollBar or ScrollPane
        int horizontalSizeOfTextField = 155;
        int verticalSizeOfTextField = 5;

        int horizontalSizeOfCloseButton = 18;
        int verticalSizeOfCloseButton = 3;

        GridBagConstraints gridBagConstraintsForBaggageLabels = new GridBagConstraints();
        GridBagConstraints gridBagConstraintsForBaggageTextFields = new GridBagConstraints();
        GridBagConstraints gridBagConstraintsForCloseButton = new GridBagConstraints();

        bagPartialViewPanel.setPreferredSize(new Dimension(350,100));

        JLabel labelForBagColor = new JLabel("Bag Color");
        bagColorTextField = new JTextField();

        JLabel labelForBagWeight = new JLabel("Bag Weight");
        bagWeightTextField = new JTextField();

        closeBagPartialViewButton = new JButton("Close");

        gridBagConstraintsForBaggageLabels.anchor = GridBagConstraints.WEST;
        gridBagConstraintsForBaggageLabels.insets = new Insets(0, 0, 15, 45);

        gridBagConstraintsForBaggageTextFields.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsForBaggageTextFields.weightx = 1;

        gridBagConstraintsForBaggageTextFields.ipadx = horizontalSizeOfTextField;
        gridBagConstraintsForBaggageTextFields.ipady = verticalSizeOfTextField;

        gridBagConstraintsForBaggageTextFields.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraintsForBaggageTextFields.insets = new Insets(0, 20, 15, 0);


        gridBagConstraintsForCloseButton.ipady = verticalSizeOfCloseButton;
        gridBagConstraintsForCloseButton.ipadx = horizontalSizeOfCloseButton;

        gridBagConstraintsForCloseButton.gridx = 1;
        gridBagConstraintsForCloseButton.insets = new Insets(0,70,0,0);

        bagPartialViewPanel.add(labelForBagColor, gridBagConstraintsForBaggageLabels);
        bagPartialViewPanel.add(bagColorTextField, gridBagConstraintsForBaggageTextFields);
        bagPartialViewPanel.add(labelForBagWeight, gridBagConstraintsForBaggageLabels);
        bagPartialViewPanel.add(bagWeightTextField, gridBagConstraintsForBaggageTextFields);
        bagPartialViewPanel.add(closeBagPartialViewButton, gridBagConstraintsForCloseButton);
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
