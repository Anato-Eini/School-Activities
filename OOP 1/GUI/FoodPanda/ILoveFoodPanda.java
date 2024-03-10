package FoodPanda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ILoveFoodPanda extends JFrame{
    private JCheckBox cPizza;
    private JCheckBox cBurger;
    private JCheckBox cFries;
    private JCheckBox cSoftDrinks;
    private JCheckBox cTea;
    private JCheckBox cSundae;
    private JRadioButton rbNone;
    private JRadioButton rb5;
    private JRadioButton rb10;
    private JRadioButton rb15;
    private JButton btnOrder;
    private JPanel foodOrder;
    public ILoveFoodPanda() {
        rbNone.setSelected(true);
        JCheckBox[] checkBoxes = {cPizza, cBurger, cFries, cSoftDrinks, cTea, cSundae};
        JRadioButton[] buttons = {rbNone, rb5, rb10, rb15};
        Integer[] prices = {100, 80, 65, 55, 50, 40};
        btnOrder.addActionListener(e -> {
            HashMap<Integer, Double> accessHM = new HashMap<>(){{
                put(0, 1.0);
                put(1, 0.95);
                put(2, 0.9);
                put(3, 0.85);
            }};
            int index = 0;
            double price = 0, discount = 0;

            for(JRadioButton jrb: buttons){
                if(jrb.isSelected()){
                    discount = accessHM.get(index);
                    break;
                }
                index++;
            }
            index = 0;
            for(JCheckBox j: checkBoxes){
                if(j.isSelected()){
                    price += prices[index];
                }
                index++;
            }
            price *= discount;
            JOptionPane.showMessageDialog(null, String.format("The total price is Php %.2f", price));
        });
    }

    public static void main(String[] args) {
        ILoveFoodPanda iLoveFoodPanda = new ILoveFoodPanda();
        iLoveFoodPanda.setVisible(true);
        iLoveFoodPanda.setContentPane(iLoveFoodPanda.foodOrder);
        iLoveFoodPanda.setTitle("Leap Year Checker");
        iLoveFoodPanda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iLoveFoodPanda.setSize(1000,800);
        iLoveFoodPanda.setLocation(400, 150);

    }
}
