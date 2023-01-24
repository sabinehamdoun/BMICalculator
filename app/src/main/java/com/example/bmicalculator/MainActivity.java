package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;

    private Button mCalculateButton;
    private String calculate;
    private EditText height, weight;
    private TextView result;
    private RadioButton rg1, rg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalculateButton = findViewById(R.id.calculate_bmi);
        height = findViewById(R.id.edit_height);
        weight = findViewById(R.id.edit_weight);
        result = findViewById(R.id.result_text);
        rg1 = findViewById(R.id.adult_radio);
        rg2 = findViewById(R.id.teen_radio);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String S1 = weight.getText().toString();
                String S2 = height.getText().toString();

                float Weight = Float.parseFloat(S1);
                float Height = Float.parseFloat(S2)/100;
                float bmi;

                if (rg1.isChecked()) {
                    bmi = Weight / (Height * Height);
                }
                else {
                    bmi = (float) ((Weight * 703 * 2.204) / (Height * Height * 39.37));
                }

                result.setText("BMI = " + new DecimalFormat("##.#").format(bmi) + " kg/m^2");
            }
        });
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }
}