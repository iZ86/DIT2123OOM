import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        InformationInputView i = new InformationInputView();
        
        JFrame f = new JFrame();

        f.add(i.getInformationInputPagePanel());
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(425,650);
        f.setResizable(false);
    }
}
