package com.example.androidprojectcollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;


public class Calculator extends AppCompatActivity {
    Button clear, clearAll, equals, point, squared, cubed, logarithm;
    TextView display, viewTotal;
    AtomicBoolean isSpecialOp = new AtomicBoolean(false),
            isDot = new AtomicBoolean(false), isError = new AtomicBoolean(false);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Operation operation = new Operation();
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
                try {
                    display.append(input);
                    operation.sequential(this);
                    isError.set(false);
                } catch (Exception e) {
                    isError.set(true);
                    viewTotal.setText(e.getMessage());
                }
            });
        }
        for (Button b: operators){
            b.setOnClickListener(view -> {
                isDot.set(false);
                if(isSpecialOp.get()){
                    display.setText(viewTotal.getText().toString());
                    isSpecialOp.set(false);
                }
                String contentText = display.getText().toString();
                if (contentText.isEmpty() || contentText.charAt(contentText.length() - 1) == '.')
                    display.append("0" + b.getText());
                else if (operation.isOperator(contentText.charAt(contentText.length() - 1)))
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
            if(!contentText.isEmpty()) {
                if(contentText.charAt(contentText.length() - 1) == '.')
                    isDot.set(false);
                display.setText(contentText.substring(0, contentText.length() - 1));
            }
            isSpecialOp.set(false);
            try {
                if(!isError.get()) {
                    operation.sequential(this);
                    isError.set(false);
                }else
                    clearAll.performClick();
            } catch (Exception e) {
                isError.set(true);
                viewTotal.setText(e.getMessage());
            }
        });
        clearAll.setOnClickListener(view -> {
            isDot.set(false);
            display.setText("");
            viewTotal.setText("0");
            isSpecialOp.set(false);
        });
        equals.setOnClickListener(view -> {
            try{
                operation.compute(this);
                display.setText(viewTotal.getText());
                isError.set(false);
            }catch (Exception e){
                isError.set(true);
            }
        });
        point.setOnClickListener(view -> {
            String contentText = display.getText().toString();
            if(!isDot.get()){
                if (contentText.isEmpty() ||
                        operation.isOperator(contentText.charAt(contentText.length() - 1)))
                    display.append("0.");
                else
                    display.append(".");
                isDot.set(true);
            }else if(contentText.charAt(contentText.length() - 1) == '.'){
                display.setText(contentText.substring(0, contentText.length() - 1));
                isDot.set(false);
            }
        });

        logarithm.setOnClickListener(view -> {
            if(!display.getText().toString().isEmpty()){
                try {
                    operation.compute(this);
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
                    isError.set(false);
                } catch (Exception e) {
                    isError.set(true);
                    viewTotal.setText(e.getMessage());
                }

            }
        });
        squared.setOnClickListener(view -> {
            if(!display.getText().toString().isEmpty()){
                try {
                    operation.compute(this);
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
                    isError.set(false);
                } catch (Exception e) {
                    isError.set(true);
                    viewTotal.setText(e.getMessage());
                }

            }
        });
        cubed.setOnClickListener(view -> {
            if(!display.getText().toString().isEmpty()){
                try {
                    operation.compute(this);
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
                    isError.set(false);
                } catch (Exception e) {
                    isError.set(true);
                    viewTotal.setText(e.getMessage());
                }

            }
        });
    }
}