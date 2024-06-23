
public class Main {
    public static void main(String[] args) {
        KioskCheckInModel kioskCheckInModel = new KioskCheckInModel();
        CounterModel counterModel = new CounterModel();
        GUI gui = new GUI(counterModel, kioskCheckInModel);
        Controller controller = new Controller(gui, counterModel, kioskCheckInModel);

        gui.display();
    }

}
