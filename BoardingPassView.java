import javax.swing.*;
import java.awt.event.ActionListener;

public class BoardingPassView {
    private JPanel boardingPassViewPanel;
    private KioskCheckInModel kioskCheckInModel;
    private JButton previousBoardingPassButton = new JButton("p revious");
    private JButton nextBoardingPassButton = new JButton("next");
    private JButton printBoardingPassButton = new JButton("print");
    private JButton mainMenuButton = new JButton("Main Menu");

    public BoardingPassView(KioskCheckInModel kioskCheckInModel){
        setUpViewPanel();
    }

    private void setUpViewPanel(){
        boardingPassViewPanel = new JPanel();
    }

    public JPanel getBoardingPassViewPanel(){
        return boardingPassViewPanel;
    }

    public void addPreviousBoardingPassButtonListener(ActionListener listenForPreviousBoardingPassButton){
        previousBoardingPassButton.addActionListener(listenForPreviousBoardingPassButton);
    }

    public void addNextBoardingPassButtonListener(ActionListener listenForNextBoardingPassButton){
        nextBoardingPassButton.addActionListener(listenForNextBoardingPassButton);
    }

    public void addPrintBoardingPassButtonListener(ActionListener listenForPrintBoardingPassButton){
        printBoardingPassButton.addActionListener(listenForPrintBoardingPassButton);
    }

    public void addMainMenuButtonListener(ActionListener listenForMainMenuButton){
        mainMenuButton.addActionListener(listenForMainMenuButton);
    }
}
