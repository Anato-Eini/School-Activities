package LeapYear;
import javax.swing.*;

import static java.time.Year.isLeap;

public class LeapYear extends JFrame{
    private JPanel panel1;
    private JTextField tfYear;
    private JButton btnCheckYear;
    public LeapYear(){
        btnCheckYear.addActionListener(e -> {
            try {
                if (isLeap(Integer.parseInt(tfYear.getText()))) {
                    JOptionPane.showMessageDialog(null, "Leap Year");
                } else {
                    JOptionPane.showMessageDialog(null, "Not a Leap Year");
                }
            }catch (NumberFormatException i){
                JOptionPane.showMessageDialog(null, "Not a Valid Input");
            }
        });
    }

    public static void main(String[] args) {
        LeapYear leapYear = new LeapYear();
        leapYear.setVisible(true);
        leapYear.setContentPane(leapYear.panel1);
        leapYear.setTitle("Leap Year Checker");
        leapYear.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leapYear.setSize(1000,800);
        leapYear.setLocation(400, 150);
    }
}
