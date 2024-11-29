package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String currentInput = "";
    private String lastOperator = "";
    private double firstOperand = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] numberButtons = {
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(v -> onNumberClick(((Button) v).getText().toString()));
        }

        findViewById(R.id.btn_add).setOnClickListener(v -> onOperatorClick("+"));
        findViewById(R.id.btn_subtract).setOnClickListener(v -> onOperatorClick("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> onOperatorClick("×"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> onOperatorClick("÷"));
        findViewById(R.id.btn_equals).setOnClickListener(v -> calculate());
        findViewById(R.id.btn_clear).setOnClickListener(v -> clear());
        findViewById(R.id.btn_dot).setOnClickListener(v -> onNumberClick("."));
        findViewById(R.id.btn_delete).setOnClickListener(v -> delete());
    }

    private void onNumberClick(String number) {
        currentInput += number;
        display.setText(currentInput);
    }

    private void onOperatorClick(String operator) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            lastOperator = operator;
            currentInput = "";
        }
    }

    private void calculate() {
        if (!currentInput.isEmpty() && !lastOperator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0.0;

            switch (lastOperator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "×":
                    result = firstOperand * secondOperand;
                    break;
                case "÷":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            currentInput = String.valueOf(result);
            display.setText(currentInput);
            lastOperator = "";
        }
    }

    private void clear() {
        currentInput = "";
        lastOperator = "";
        firstOperand = 0.0;
        display.setText("0");
    }

    private void delete() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            display.setText(currentInput.isEmpty() ? "0" : currentInput);
        }
    }
}
