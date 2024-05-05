
public class Main {
    public static void main(String[] args) {
        // Does not work currently.
        KioskCheckInModel kioskCheckInModel = new KioskCheckInModel();
        GUI gui = new GUI(kioskCheckInModel);
        Controller controller = new Controller(gui, kioskCheckInModel);

        // Does not work currently.
        gui.display();
    }

}
