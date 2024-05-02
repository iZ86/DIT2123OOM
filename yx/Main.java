import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel iptTopic = new JLabel(" Input Personal Information ");
        iptTopic.setBounds(100,20,230,30);
        iptTopic.setFont(new Font("Arial",Font.BOLD,14));

        JLabel l1,l2,l3;
        JTextField t1,t2,t3;
        JButton b1,b2,b3;

        l1 = new JLabel("Booking ID");
        l1.setBounds(50,100,180,30);
        t1 = new JTextField(10);
        t1.setBounds(150,100,180,30);

        l2 = new JLabel("Name");
        l2.setBounds(50,160,180,30);
        t2 = new JTextField(10);
        t2.setBounds(150,160,180,30);

        l3 = new JLabel("Passport ID");
        l3.setBounds(50,220,180,30);
        t3 = new JTextField(10);
        t3.setBounds(150,220,180,30);

        b1 = new JButton("Add Baggage");
        b1.setBounds(50,320,280,40);

        b2 = new JButton("Back");
        b2.setBounds(50,380,130,35);

        b3 = new JButton("Next");
        b3.setBounds(200,380,130,35);

        frame.add(l1); frame.add(t1); frame.add(l2); frame.add(t2); frame.add(l3); frame.add(t3);
        frame.add(b1); frame.add(b2); frame.add(b3);
        frame.add(iptTopic);
        frame.setSize(400, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
