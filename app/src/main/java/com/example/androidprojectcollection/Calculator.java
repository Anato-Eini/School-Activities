package com.example.androidprojectcollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calculator extends AppCompatActivity {
    Button clear, clearAll, equals, point, squared, cubed, logarithm;
    TextView display, viewTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        clear = (Button) findViewById(R.id.remove);
        display = (TextView) findViewById(R.id.display);
        clearAll = (Button) findViewById(R.id.clearAll);
        equals = (Button) findViewById(R.id.equals);
        viewTotal = (TextView) findViewById(R.id.total);
        point = (Button) findViewById(R.id.point);
        squared = (Button) findViewById(R.id.squared);
        cubed = (Button) findViewById(R.id.cubed);
        logarithm = (Button) findViewById(R.id.logarithm);
        viewTotal.setText("0");
        AtomicBoolean isSpecialOp = new AtomicBoolean(false);
        Button[] numbers = {
                (Button) findViewById(R.id.num0),
                (Button) findViewById(R.id.num1),
                (Button) findViewById(R.id.num2),
                (Button) findViewById(R.id.num3),
                (Button) findViewById(R.id.num4),
                (Button) findViewById(R.id.num5),
                (Button) findViewById(R.id.num6),
                (Button) findViewById(R.id.num7),
                (Button) findViewById(R.id.num8),
                (Button) findViewById(R.id.num9),
        },
        operators = {
                (Button) findViewById(R.id.add),
                (Button) findViewById(R.id.subtract),
                (Button) findViewById(R.id.multiply),
                (Button) findViewById(R.id.divide),
                (Button) findViewById(R.id.modulo)
        };
        for(int i = 0; i < numbers.length; i ++){
            String input = String.valueOf(i);
            numbers[i].setOnClickListener(view -> {
                if(isSpecialOp.get()) {
                    display.setText(viewTotal.getText().toString());
                    isSpecialOp.set(false);
                }
                display.append(input);
                equals.performClick();
            });
        }
        for (Button b: operators){
            b.setOnClickListener(view -> {
                if(isSpecialOp.get()){
                    display.setText(viewTotal.getText().toString());
                    isSpecialOp.set(false);
                }
                String contentText = display.getText().toString();
                if (contentText.isEmpty() || contentText.charAt(contentText.length() - 1) == '.') {
                    display.append("0" + b.getText());
                } else if (isOperator(contentText.charAt(contentText.length() - 1))) {
                    String output = contentText.substring(0, contentText.length() - 1) + b.getText();
                    display.setText(output);
                } else display.append(b.getText());
            });
        }
        clear.setOnClickListener(view -> {
            String contentText = display.getText().toString();
            if(!contentText.isEmpty()){
                String output = contentText.substring(0, contentText.length() - 1);
                display.setText(output);
            }
            equals.performClick();
        });
        clearAll.setOnClickListener(view -> {
            display.setText("");
            viewTotal.setText("0");
        });
        equals.setOnClickListener(view -> {
            if(isSpecialOp.get()){
                display.setText(viewTotal.getText().toString());
                isSpecialOp.set(false);
            }
            ArrayList<String> operands = new ArrayList<>(), operator = new ArrayList<>();
            String contentText = display.getText().toString();
            Pattern pattern = Pattern.compile("\\d*\\.?\\d+|[-+*/%]");
            Matcher matcher = pattern.matcher(contentText);
            while(matcher.find()){
                String token = matcher.group();
                if(token.matches("[-+/*%]"))
                    operator.add(token);
                else
                    operands.add(token);
            }
            if(operator.size() == operands.size() && !operands.isEmpty())
                operator.remove(operator.size() - 1);

            for(int i = 0; i < operator.size(); i++)
                while(i < operator.size() && operator.get(i).equals("*"))
                    evaluate2(i, operands, operator);

            for(int i = 0; i < operator.size(); i++)
                while(i < operator.size() && operator.get(i).equals("/"))
                    evaluate2(i, operands, operator);

            for(int i = 0; i < operator.size(); i++)
                while(i < operator.size() && operator.get(i).equals("+"))
                    evaluate2(i, operands, operator);

            for(int i = 0; i < operator.size(); i++)
                while(i < operator.size() && operator.get(i).equals("-"))
                    evaluate2(i, operands, operator);

            for (int i = 0; i < operator.size(); i++)
                while(i < operator.size() && operator.get(i).equals("%"))
                    evaluate2(i, operands, operator);

            double output = (!operands.isEmpty() ? Double.parseDouble(operands.get(0)) : 0);
            viewTotal.setText(output < Math.ceil(output) ? String.valueOf(output): output == 0 ?
                    "": String.valueOf(output).replaceAll("0*$", "").
                            replaceAll("\\.$", "")
                    );
        });
        point.setOnClickListener(view -> {
            String contentText = display.getText().toString();
            if(contentText.isEmpty() || isOperator(contentText.charAt(contentText.length() - 1))){
                display.append("0.");
            }else if(contentText.charAt(contentText.length() - 1) != '.'){
                display.append(".");
            }
        });

        logarithm.setOnClickListener(view -> {
            if(!display.getText().toString().isEmpty()){
                equals.performClick();
                String viewTotalText = viewTotal.getText().toString();
                display.setText(viewTotalText);
                double output = Math.log10(
                        viewTotalText.contains(".") ? Double.parseDouble(viewTotalText) :
                                (double) Integer.parseInt(viewTotalText)
                );
                viewTotal.setText(
                        output < Math.ceil(output) ? String.valueOf(output) :
                                String.valueOf(output).replaceAll("0*$", "").
                                        replaceAll("\\.$", "")
                );
                isSpecialOp.set(true);
            }
        });
        squared.setOnClickListener(view -> {
            if(!display.getText().toString().isEmpty()){
                equals.performClick();
                String viewTotalText = viewTotal.getText().toString();
                display.setText(viewTotalText);
                double output = Math.pow(viewTotalText.contains(".") ? Double.parseDouble(viewTotalText) :
                        (double) Integer.parseInt(viewTotalText), 2);
                viewTotal.setText(
                        output < Math.ceil(output) ? String.valueOf(output) :
                                String.valueOf(output).replaceAll("0*$", "").
                                        replaceAll("\\.$", "")
                );
                isSpecialOp.set(true);
            }
        });
        cubed.setOnClickListener(view -> {
            if(!display.getText().toString().isEmpty()){
                equals.performClick();
                String viewTotalText = viewTotal.getText().toString();
                display.setText(viewTotalText);
                double output = Math.pow(
                        viewTotalText.contains(".") ? Double.parseDouble(viewTotalText) :
                                (double) Integer.parseInt(viewTotalText), 3);
                viewTotal.setText(
                        output < Math.ceil(output) ? String.valueOf(output) :
                                String.valueOf(output).replaceAll("0*$", "").
                                        replaceAll("\\.$", "")
                );
                isSpecialOp.set(true);
            }
        });
    }

    void evaluate2(int i, ArrayList<String> operands, ArrayList<String> operators){
        double operand1 = (operands.get(i).contains(".") ? Double.parseDouble(operands.get(i)) :
                (double) Integer.parseInt(operands.get(i)));
        operand1 = evaluate(operands.get(i + 1), operators.get(i).charAt(0), operand1);
        operands.remove(i + 1);
        operands.set(i, String.valueOf(operand1));
        operators.remove(i);
    }
    boolean isOperator(char character){
        return character == '+' || character == '-' || character == '/' || character == '*' ||
                character == '%';
    }
    double evaluate(String s, char operator, double total){
        double v = (s.contains(".") ? Double.parseDouble(s) : (double) Integer.parseInt(s));
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