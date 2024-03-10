package SimpleCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame{
    private JPanel number1;
    private JTextField tfNumber1;
    private JTextField tfNumber2;
    private JComboBox comboBox1;
    private JButton btnCompute;
    private JLabel lblResult;
    private JPanel Simple_Calculator;

    public static int operation(int a, int b, int c){
        return switch (c) {
            case 0 -> a + b;
            case 1 -> a - b;
            case 2 -> a * b;
            case 3 -> a / b;
            default -> 0;
        };
    }
    public SimpleCalculator() {
        btnCompute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int a = Integer.parseInt(tfNumber1.getText()), b = Integer.parseInt(tfNumber2.getText()), c = comboBox1.getSelectedIndex();
                    lblResult.setText(operation(a,b,c) + "");
                }catch (NumberFormatException i){
                    JOptionPane.showMessageDialog(null, "Enter a valid input");
                }
            }
        });
    }

    public static void main(String[] args) {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        simpleCalculator.setVisible(true);
        simpleCalculator.setContentPane(simpleCalculator.Simple_Calculator);
        simpleCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simpleCalculator.setSize(1000, 800);
        simpleCalculator.setTitle("Simple Calculator");
    }
}
