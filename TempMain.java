import javax.swing.*;

public class TempMain {
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        KioskCheckInModel kioskCheckInModel = new KioskCheckInModel();
        CheckInView checkInView = new CheckInView(kioskCheckInModel);
        checkInView.resetView();
        jframe.add(checkInView.getCheckInViewPanel());
        jframe.setSize(400, 600);
        jframe.setVisible(true);
    }
}
