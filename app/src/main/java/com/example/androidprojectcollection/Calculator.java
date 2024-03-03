package com.example.androidprojectcollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calculator extends AppCompatActivity {
    Button clear, clearAll, equals, point, squared, cubed, logarithm;
    TextView display, viewTotal;
    AtomicBoolean isSpecialOp = new AtomicBoolean(false);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        clear = findViewById(R.id.remove);
        display = findViewById(R.id.display);
        clearAll = findViewById(R.id.clearAll);
        equals = findViewById(R.id.equals);
        viewTotal = findViewById(R.id.total);
        point = findViewById(R.id.point);
        squared = findViewById(R.id.squared);
        cubed = findViewById(R.id.cubed);
        logarithm = findViewById(R.id.logarithm);
        viewTotal.setText("0");
        Button[] numbers = {
                findViewById(R.id.num0),
                findViewById(R.id.num1),
                findViewById(R.id.num2),
                findViewById(R.id.num3),
                findViewById(R.id.num4),
                findViewById(R.id.num5),
                findViewById(R.id.num6),
                findViewById(R.id.num7),
                findViewById(R.id.num8),
                findViewById(R.id.num9)
        },
        operators = {
                findViewById(R.id.add),
                findViewById(R.id.subtract),
                findViewById(R.id.multiply),
                findViewById(R.id.divide),
                findViewById(R.id.modulo)
        };
        for(int i = 0; i < numbers.length; i ++){
            String input = String.valueOf(i);
            numbers[i].setOnClickListener(view -> {
                if(isSpecialOp.get()) {
                    display.setText(viewTotal.getText().toString());
                    isSpecialOp.set(false);
                }
                display.append(input);
                compute();
            });
        }
        for (Button b: operators){
            b.setOnClickListener(view -> {
                if(isSpecialOp.get()){
                    display.setText(viewTotal.getText().toString());
                    isSpecialOp.set(false);
                }
                String contentText = display.getText().toString();
                if (contentText.isEmpty() || contentText.charAt(contentText.length() - 1) == '.')
                    display.append("0" + b.getText());
                else if (isOperator(contentText.charAt(contentText.length() - 1)))
                    display.setText(
                            contentText.substring(0, contentText.length() - 1).
                                    concat(b.getText().toString())
                    );
                else
                    display.append(b.getText());
            });
        }
        clear.setOnClickListener(view -> {
            String contentText = display.getText().toString();
            if(!contentText.isEmpty())
                display.setText(contentText.substring(0, contentText.length() - 1));
            isSpecialOp.set(false);
            compute();
        });
        clearAll.setOnClickListener(view -> {
            display.setText("");
            viewTotal.setText("0");
            isSpecialOp.set(false);
        });
        equals.setOnClickListener(view -> display.setText(viewTotal.getText()));
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
                compute();
                String viewTotalText = viewTotal.getText().toString();
                display.setText(viewTotalText);
                double output = Math.log10(
                        viewTotalText.contains(".") ? Double.parseDouble(viewTotalText) :
                                (double) Long.parseLong(viewTotalText)
                );
                if(!Double.isInfinite(output)) {
                    String outputString = String.valueOf(output);
                    viewTotal.setText(
                            output < Math.ceil(output) ?
                                    outputString : outputString.contains("E") ?
                                    String.format(Locale.US, "%.0f", output) :
                                    outputString.replaceAll("0*$", "").
                                            replaceAll("\\.$", "")
                    );
                }
                isSpecialOp.set(true);
            }
        });
        squared.setOnClickListener(view -> {
            if(!display.getText().toString().isEmpty()){
                compute();
                String viewTotalText = viewTotal.getText().toString();
                display.setText(viewTotalText);
                double output = Math.pow(viewTotalText.contains(".") ?
                        Double.parseDouble(viewTotalText) :
                        (double) Long.parseLong(viewTotalText), 2);
                if(!Double.isInfinite(output)) {
                    String outputString = String.valueOf(output);
                    viewTotal.setText(
                            output < Math.ceil(output) ?
                                    outputString : outputString.contains("E") ?
                                    String.format(Locale.US, "%.0f", output) :
                                    outputString.replaceAll("0*$", "").
                                            replaceAll("\\.$", "")
                    );
                }
                isSpecialOp.set(true);
            }
        });
        cubed.setOnClickListener(view -> {
            if(!display.getText().toString().isEmpty()){
                compute();
                String viewTotalText = viewTotal.getText().toString();
                display.setText(viewTotalText);
                double output = Math.pow(
                        viewTotalText.contains(".") ? Double.parseDouble(viewTotalText) :
                                (double) Long.parseLong(viewTotalText), 3);
                if(!Double.isInfinite(output)) {
                    String outputString = String.valueOf(output);
                    viewTotal.setText(
                            output < Math.ceil(output) ?
                                    outputString : outputString.contains("E") ?
                                    String.format(Locale.US, "%.0f", output) :
                                    outputString.replaceAll("0*$", "").
                                            replaceAll("\\.$", "")
                    );
                }
                isSpecialOp.set(true);
            }
        });
    }

    void evaluate2(int i, ArrayList<String> operands, ArrayList<String> operators){
        double operand1 = (operands.get(i).contains(".") ? Double.parseDouble(operands.get(i)) :
                (double) Long.parseLong(operands.get(i)));
        operand1 = evaluate(operands.get(i + 1), operators.get(i).charAt(0), operand1);
        operands.remove(i + 1);
        operands.set(i, String.valueOf(operand1));
        operators.remove(i);
    }
    boolean isOperator(char character){
        return character == '+' || character == '-' || character == '/' || character == '*' ||
                character == '%';
    }

    void compute(){
        if(isSpecialOp.get()){
            display.setText(viewTotal.getText().toString());
            isSpecialOp.set(false);
        }
        ArrayList<String> operands = new ArrayList<>(), operator = new ArrayList<>();
        String contentText = display.getText().toString();
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
        if(!Double.isInfinite(output)){
            System.out.println(output);
            String outputString = String.valueOf(output);
            viewTotal.setText(output < Math.ceil(output) ? outputString : output == 0 ?
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