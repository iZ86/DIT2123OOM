import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;    

public class InformationInputView {
    private final JButton continueButton = new JButton("Continue");
    private final JButton addBaggageButton = new JButton("Add a Baggage");
    private final BasicArrowButton rightButton = new BasicArrowButton(BasicArrowButton.EAST);
    private final BasicArrowButton leftButton = new BasicArrowButton(BasicArrowButton.WEST);
    private final JPanel informationInputPagePanel = new JPanel(new GridBagLayout());

    public InformationInputView() {
        setupInformationPageViewPanel();
    }

    public JPanel getInformationInputPagePanel() {
        return informationInputPagePanel;
    };

    private void setupInformationPageViewPanel() {
        JPanel textFieldPanel = setupInformationTextFieldPanel();
        JPanel buttonPanel = setupInformationButtonPanel();

        GridBagConstraints constraintsForTextFieldPanel = new GridBagConstraints();
        GridBagConstraints constraintsForButtonPanel = new GridBagConstraints();

        constraintsForTextFieldPanel.gridy = 0;
        constraintsForTextFieldPanel.insets = new Insets(0, 0, 150, 0);
        
        constraintsForButtonPanel.gridy = 1;       
        constraintsForButtonPanel.insets = new Insets(20, 0, 0, 0);

        informationInputPagePanel.add(textFieldPanel, constraintsForTextFieldPanel);
        informationInputPagePanel.add(buttonPanel, constraintsForButtonPanel);
    }

    private JPanel setupInformationTextFieldPanel() {
        JPanel textFieldPanel = new JPanel(new GridBagLayout());

        JLabel passportLabel = new JLabel("Passport Number");
        JTextField passportNum = new JTextField();

        JLabel identityCardLabel = new JLabel("Identity Card Number");
        JTextField identityCardTextField = new JTextField();

        JLabel name = new JLabel("Name");
        JTextField nameTextField = new JTextField();
        
        GridBagConstraints gridBagConstraintsForLabels = new GridBagConstraints();
        gridBagConstraintsForLabels.anchor = GridBagConstraints.WEST;
        gridBagConstraintsForLabels.insets = new Insets(0, 0, 15, 10); 

        GridBagConstraints gridBagConstraintsForTextFields = new GridBagConstraints();
        gridBagConstraintsForTextFields.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraintsForTextFields.weightx = 1;
        gridBagConstraintsForTextFields.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraintsForTextFields.insets = new Insets(0, 0, 15, 0);
        
        textFieldPanel.add(passportLabel, gridBagConstraintsForLabels);
        textFieldPanel.add(passportNum, gridBagConstraintsForTextFields);
        textFieldPanel.add(identityCardLabel, gridBagConstraintsForLabels);
        textFieldPanel.add(identityCardTextField, gridBagConstraintsForTextFields);
        textFieldPanel.add(name, gridBagConstraintsForLabels);
        textFieldPanel.add(nameTextField, gridBagConstraintsForTextFields);

        textFieldPanel.setPreferredSize(new Dimension(360, 150)); 

        return textFieldPanel;
    }

    private JPanel setupInformationButtonPanel() {
        JPanel informationPageButtonPanel = new JPanel(new GridBagLayout());

        int horizontalSizeOfArrowButton = 20;
        int verticalSizeOfArrowButton = 20;
        
        GridBagConstraints constraintsForAddBaggageButton = new GridBagConstraints();
        GridBagConstraints constraintsForLeftAndRightArrowButton = new GridBagConstraints();
        constraintsForAddBaggageButton.gridx = 0;

       
        constraintsForLeftAndRightArrowButton.ipadx = horizontalSizeOfArrowButton;
        constraintsForLeftAndRightArrowButton.ipady = verticalSizeOfArrowButton;
        constraintsForLeftAndRightArrowButton.insets = new Insets(0, 15, 0, 15);

        informationPageButtonPanel.add(addBaggageButton, constraintsForAddBaggageButton);
        informationPageButtonPanel.add(leftButton, constraintsForLeftAndRightArrowButton);
        informationPageButtonPanel.add(rightButton, constraintsForLeftAndRightArrowButton);
        informationPageButtonPanel.add(continueButton);

        return informationPageButtonPanel;
    }

    
    
}