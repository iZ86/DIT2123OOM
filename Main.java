
public class Main {
    public static void main(String[] args) {
        // Does not work currently.
        KioskCheckInModel kioskCheckInModel = new KioskCheckInModel();
        CounterModel counterModel = new CounterModel();
        GUI gui = new GUI(counterModel, kioskCheckInModel);
        Controller controller = new Controller(gui, counterModel, kioskCheckInModel);

        // Does not work currently.
        gui.display();
    }

}
