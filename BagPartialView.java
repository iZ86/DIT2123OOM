import javax.swing.*;
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
    public BagPartialView(ActionListener listenForCloseBagPartialViewButton) {

    }

    /** Adds an ActionListener to JButton closeBagPartialViewButton*/
    private void addCloseBagPartialViewButtonListener(ActionListener listenForCloseBagPartialViewButton) {
        closeBagPartialViewButton.addActionListener(listenForCloseBagPartialViewButton);
    }

    /** Sets up the bagPartialViewPanel*/
    private void setupViewPanel() {
        //TODO the view that's gonna pop up when click's the add baggage button
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