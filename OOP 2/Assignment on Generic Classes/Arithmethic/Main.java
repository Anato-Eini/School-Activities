package Arithmethic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        Number num1 = null, num2 = null;
        String input;
        boolean error = true;
        while(error){
            try {
                System.out.print("Enter operand 1: ");
                input = scanner.nextLine();
                System.out.print("Data Type (1-Integer 2-Float 3-Double 4-Short 5-Long): ");
                choice = scanner.nextInt();
                num1 = valueOf(input, choice);
                scanner.nextLine();
                System.out.print("Enter operand 2: ");
                input = scanner.nextLine();
                System.out.print("Data Type (1-Integer 2-Float 3-Double 4-Short 5-Long): ");
                choice = scanner.nextInt();
                num2 = valueOf(input, choice);
                error = false;
            } catch (InputMismatchException | NumberFormatException e) {
                scanner.nextLine();
                System.out.println("Wrong Data Type");
            }
        }

        Arithmetic arithmetic = new Arithmetic(num1, num2);
        while(true){
            System.out.print("Enter operation (1-Add 2-Subtract 3-Multiply 4-Divide 5-GetMin 6-GetMax 7-Exit): ");
            choice = scanner.nextInt();
            if(choice == 7)
                return;
            System.out.println(
                    choice == 1 ? arithmetic.add() : choice == 2 ? arithmetic.subtract() : choice == 3 ?
                            arithmetic.multiply() : choice == 4 ? arithmetic.divide() : choice == 5 ?
                            arithmetic.getMin() : choice == 6 ? arithmetic.getMax() : "Not a valid choice"
            );
        }
    }
    public static Number valueOf(String number, int choice) throws NumberFormatException{
        return switch (choice) {
            case 1 -> Integer.valueOf(number);
            case 2 -> Float.valueOf(number);
            case 3 -> Double.valueOf(number);
            case 4 -> Short.valueOf(number);
            case 5 -> Long.valueOf(number);
            default -> null;
        };
    }
}
