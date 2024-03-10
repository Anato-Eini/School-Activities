package com.example.androidprojectcollection;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Stack;
public class Operation {
    public void evaluate2(int i, ArrayList<String> operands, ArrayList<String> operators){
        double operand1 = (operands.get(i).contains(".") ? Double.parseDouble(operands.get(i)) :
                (double) Long.parseLong(operands.get(i)));
        operand1 = evaluate(operands.get(i + 1), operators.get(i).charAt(0), operand1);
        operands.remove(i + 1);
        operands.set(i, String.valueOf(operand1));
        operators.remove(i);
    }
    public boolean isOperator(char character){
        return character == '+' || character == '-' || character == '/' || character == '*' ||
                character == '%';
    }

    public String evaluatePostFix(ArrayList<String> expression){
        Stack<String> stack = new Stack<>();
        for(String s: expression){
            if(s.matches("[-+/*%]")){
                String popped = stack.pop();
                stack.push(String.valueOf(evaluate(stack.pop(), s.charAt(0),
                        (popped.contains(".") ? Double.parseDouble(popped) :
                                (double) Long.parseLong(popped)))));
            }else
                stack.push(s);
        }
        return stack.pop();
    }

    public void convertToPostFix(
            ArrayList<ArrayList<String>> operands, ArrayList<ArrayList<String>> operator, int i
    ){
        operands.get(i + 1).addAll(operands.get(i));
        operands.get(i + 1).addAll(operator.get(i));
        operands.remove(i);
        operator.remove(i);
    }

    public void sequential(Calculator c){
        if(c.isSpecialOp.get()){
        c.display.setText(c.viewTotal.getText().toString());
        c.isSpecialOp.set(false);
    }
        ArrayList<String> operands = new ArrayList<>(), operator = new ArrayList<>();
        String contentText = c.display.getText().toString();
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
            String outputString = String.valueOf(output);
            c.viewTotal.setText(output < Math.ceil(output) ? outputString : output == 0 ?
                    "0" : outputString.contains("E") ?
                    String.format(Locale.US, "%.0f", output) :
                    outputString.replaceAll("0*$", "").
                            replaceAll("\\.$", "")
            );
        }

    }
    public void compute(Calculator c){
        if(c.isSpecialOp.get()){
            c.display.setText(c.viewTotal.getText().toString());
            c.isSpecialOp.set(false);
        }
        c.isDot.set(false);
        ArrayList<ArrayList<String>> operands = new ArrayList<>(), operator = new ArrayList<>();
        String contentText = c.display.getText().toString();
        Matcher matcher = Pattern.compile("\\d*\\.?\\d+|[-+*/%]").matcher(contentText);
        while(matcher.find()){
            String token = matcher.group();
            ArrayList<String> element = new ArrayList<>();
            if(token.matches("[-+/*%]")) {
                element.add(token);
                operator.add(element);
            } else {
                element.add(token);
                operands.add(element);
            }
        }
        if(operator.size() == operands.size() && !operands.isEmpty())
            operator.remove(operator.size() - 1);


        for(int i = operator.size() - 1; i >= 0; i--)
            while(i < operator.size() && operator.get(i).get(0).equals("*"))
                convertToPostFix(operands, operator, i);

        for(int i = operator.size() - 1; i >= 0; i--)
            while(i < operator.size() && operator.get(i).get(0).equals("/"))
                convertToPostFix(operands, operator, i);

        for(int i = operator.size() - 1; i >= 0; i--)
            while(i < operator.size() && operator.get(i).get(0).equals("%"))
                convertToPostFix(operands, operator, i);

        for(int i = operator.size() - 1; i >= 0; i--)
            while(i < operator.size() && operator.get(i).get(0).equals("+"))
                convertToPostFix(operands, operator, i);

        for(int i = operator.size() - 1; i >= 0; i--)
            while(i < operator.size() && operator.get(i).get(0).equals("-"))
                convertToPostFix(operands, operator, i);

        double output = Double.parseDouble(evaluatePostFix(operands.get(0)));
        if(!Double.isInfinite(output)){
            String outputString = String.valueOf(output);
            c.viewTotal.setText(output < Math.ceil(output) ? outputString : output == 0 ?
                    "0" : outputString.contains("E") ?
                    String.format(Locale.US, "%.0f", output) :
                    outputString.replaceAll("0*$", "").
                            replaceAll("\\.$", "")
            );
        }
    }

    double evaluate(String s, char operator, double total){
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
                total /= v;
                break;
            case '%':
                total %= v;
                break;
        }
        return total;
    }
}
