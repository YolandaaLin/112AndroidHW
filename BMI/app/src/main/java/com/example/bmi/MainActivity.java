package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private TextView txvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvShow = (TextView) findViewById(R.id.lblOutput);
        txvShow.setTextSize(36);
        EditText edtHeight = (EditText) findViewById(R.id.edthight);
        EditText edtWeight = (EditText) findViewById(R.id.edtweight);
        Button btnCalc = (Button) findViewById(R.id.buttonBMI);
        Button btnClear = (Button) findViewById(R.id.buttonClear);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double height = Double.parseDouble(edtHeight.getText().toString());
                    double weight = Double.parseDouble(edtWeight.getText().toString());
                    if (height <= 0 || weight <= 0) {
                        txvShow.setText("輸入錯誤! 請輸入正確的身高和體重!");
                        txvShow.setTextColor(Color.GRAY);
                        return;
                    }
                    double bmi = weight / Math.pow(height/100.0 , 2);
                    if(bmi >= 24)
                        txvShow.setTextColor(Color.RED);
                    else if(bmi < 18.5)
                        txvShow.setTextColor(Color.BLUE);
                    else
                        txvShow.setTextColor(Color.GREEN);
                    txvShow.setText(String.format("%.2f", bmi));
                }catch (NumberFormatException e){
                    txvShow.setText("輸入錯誤!請輸入數值!");
                    txvShow.setTextColor(Color.BLACK);
                }

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtHeight.setText("0");
                edtWeight.setText("0");
                txvShow.setText("");
            }
        });

    }
}