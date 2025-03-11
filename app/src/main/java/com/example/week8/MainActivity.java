package com.example.week8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText inputFirstNumber, inputSecondNumber;
    private Button buttonPlus, buttonMinus, buttonMultiply, buttonDivide;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply Edge-to-Edge UI adjustments
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        inputFirstNumber = findViewById(R.id.inputFirstNumber);
        inputSecondNumber = findViewById(R.id.inputSecondNumber);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        textResult = findViewById(R.id.textResult);

        // Set button click listeners
        buttonPlus.setOnClickListener(v -> calculate('+'));
        buttonMinus.setOnClickListener(v -> calculate('-'));
        buttonMultiply.setOnClickListener(v -> calculate('*'));
        buttonDivide.setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char operator) {
        String firstNumStr = inputFirstNumber.getText().toString().trim();
        String secondNumStr = inputSecondNumber.getText().toString().trim();

        if (firstNumStr.isEmpty() || secondNumStr.isEmpty()) {
            textResult.setText("Please enter both numbers.");
            return;
        }

        try {
            int num1 = Integer.parseInt(firstNumStr);
            int num2 = Integer.parseInt(secondNumStr);
            int result;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        textResult.setText("Cannot divide by zero.");
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    textResult.setText("Invalid operation.");
                    return;
            }

            textResult.setText(String.valueOf(result)); // Display only the integer result
        } catch (NumberFormatException e) {
            textResult.setText("Invalid input. Please enter valid numbers.");
        }
    }
}
