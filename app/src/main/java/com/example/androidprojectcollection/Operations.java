package com.example.androidprojectcollection;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Operations {

    static void sequential(){
        ArrayList<String> operands = new ArrayList<>(), operator = new ArrayList<>();
        String contentText = Calculator.display.getText().toString();
        Matcher matcher = Pattern.compile("\\d*\\.?\\d+|[-+*/%]").matcher(contentText);
        while(matcher.find()){
            String token = matcher.group();
            if(token.matches("[-+/*%]"))
                operator.add(token);
            else
                operands.add(token);
        }
        if(operator.size() == operands.size() && !operands.isEmpty())
            operator.remove(operator.size() - 1);

        int i = 0;
        while(i < operator.size())
            evaluate2(i, operands, operator);

        double output = (!operands.isEmpty() ? Double.parseDouble(operands.get(0)) : 0);
        if(!Double.isInfinite(output)){
            System.out.println(output);
            String outputString = String.valueOf(output);
            Calculator.viewTotal.setText(output < Math.ceil(output) ? outputString : output == 0 ?
                    "0" : outputString.contains("E") ?
                    String.format(Locale.US, "%.0f", output) :
                    outputString.replaceAll("0*$", "").
                            replaceAll("\\.$", "")
            );
        }
    }

    static void evaluate2(int i, ArrayList<String> operands, ArrayList<String> operators) throws ArithmeticException{
        double operand1 = (operands.get(i).contains(".") ? Double.parseDouble(operands.get(i)) :
                (double) Long.parseLong(operands.get(i)));
        operand1 = evaluate(operands.get(i + 1), operators.get(i).charAt(0), operand1);
        operands.remove(i + 1);
        operands.set(i, String.valueOf(operand1));
        operators.remove(i);
    }
    static boolean isOperator(char character){
        return character == '+' || character == '-' || character == '/' || character == '*' ||
                character == '%';
    }

    static void compute(){
        try{
            if (Calculator.isSpecialOp.get()) {
                Calculator.display.setText(Calculator.viewTotal.getText().toString());
                Calculator.isSpecialOp.set(false);
            }
            ArrayList<String> operands = new ArrayList<>(), operator = new ArrayList<>();
            String contentText = Calculator.display.getText().toString();
            Matcher matcher = Pattern.compile("\\d*\\.?\\d+|[-+*/%]").matcher(contentText);
            while (matcher.find()) {
                String token = matcher.group();
                if (token.matches("[-+/*%]"))
                    operator.add(token);
                else
                    operands.add(token);
            }
            if (operator.size() == operands.size() && !operands.isEmpty())
                operator.remove(operator.size() - 1);

            for (int i = 0; i < operator.size(); i++)
                while (i < operator.size() && operator.get(i).equals("/"))
                    evaluate2(i, operands, operator);

            for (int i = 0; i < operator.size(); i++)
                while (i < operator.size() && operator.get(i).equals("*"))
                    evaluate2(i, operands, operator);

            for (int i = 0; i < operator.size(); i++)
                while (i < operator.size() && operator.get(i).equals("%"))
                    evaluate2(i, operands, operator);

            for (int i = 0; i < operator.size(); i++)
                while (i < operator.size() && operator.get(i).equals("+"))
                    evaluate2(i, operands, operator);

            for (int i = 0; i < operator.size(); i++)
                while (i < operator.size() && operator.get(i).equals("-"))
                    evaluate2(i, operands, operator);

            double output = (!operands.isEmpty() ? Double.parseDouble(operands.get(0)) : 0);
            if (!Double.isInfinite(output)) {
                System.out.println(output);
                String outputString = String.valueOf(output);
                Calculator.viewTotal.setText(output < Math.ceil(output) ? outputString : output == 0 ?
                        "0" : outputString.contains("E") ?
                        String.format(Locale.US, "%.0f", output) :
                        outputString.replaceAll("0*$", "").
                                replaceAll("\\.$", "")
                );
            }
            if(Calculator.isError.get())
                Calculator.isError.set(false);
        }catch (ArithmeticException e){
            String output = "Can't divide 0";
            Calculator.viewTotal.setText(output);
            Calculator.isError.set(true);
        }
    }

    public static double evaluate(String s, char operator, double total){
        double v = (s.contains(".") ? Double.parseDouble(s) : (double) Long.parseLong(s));
        switch (operator){
            case '+':
                total += v;
                break;
            case '-':
                total -= v;
                break;
            case '*':
                total *= v;
                break;
            case '/':
                if(v == 0)
                    throw new ArithmeticException("Can't divide 0");
                total /= v;
                break;
            case '%':
                total %= v;
                break;
        }
        return total;
    }
}
