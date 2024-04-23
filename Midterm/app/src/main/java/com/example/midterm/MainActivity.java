package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outputStr = "";

                RadioButton boy = (RadioButton) findViewById(R.id.rdbBoy);
                RadioButton girl = (RadioButton) findViewById(R.id.rdbGirl);
                if (boy.isChecked())
                    outputStr += "男性\n";
                else if (girl.isChecked())
                    outputStr += "女性\n";

                RadioGroup type = findViewById(R.id.rgType);
                RadioButton adult = findViewById(R.id.rdbAdult);
                RadioButton child = findViewById(R.id.rdbChild);
                RadioButton student = findViewById(R.id.rdbStudent);
                int price = 0;
                if (type.getCheckedRadioButtonId() == R.id.rdbAdult) {
                    outputStr += "成人票\n";
                    price = 500;
                }
                else if (type.getCheckedRadioButtonId() == R.id.rdbChild) {
                    outputStr += "孩童票\n";
                    price = 250;
                }
                else if (type.getCheckedRadioButtonId() == R.id.rdbStudent) {
                    outputStr += "學生票\n";
                    price = 400;
                }

                TextView edtTxtNum = findViewById(R.id.edtTxtNum);
                String numStr = edtTxtNum.getText().toString();
                if (!numStr.isEmpty()) {
                    int num = Integer.parseInt(numStr);
                    int total = num * price;
                    outputStr += num + "張\n";
                    outputStr += "金額：" + total + "元\n";
                }

                TextView output = (TextView) findViewById(R.id.lblOutput);
                output.setText(outputStr);
            }
        });
    }
}