// A111221064
package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private int totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnOk);
        TextView lblOutput = findViewById(R.id.lblOutput);
        lblOutput.setMovementMethod(new ScrollingMovementMethod());

        RadioGroup gender = findViewById(R.id.rgGender);
        RadioGroup type = findViewById(R.id.rgType);
        EditText edtTxtNum = findViewById(R.id.edtTxtNum);

        // 監聽性別選擇變化
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOutput(lblOutput);
            }
        });

        // 監聽票種選擇變化
        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOutput(lblOutput);
            }
        });

        // 監聽張數輸入變化
        edtTxtNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 不需要實作
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateOutput(lblOutput);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 不需要實作
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderDetails = lblOutput.getText().toString();
                if (TextUtils.isEmpty(orderDetails)) {
                    Toast.makeText(MainActivity.this, "請選擇性別、票種和輸入張數", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 創建 Intent 對象並啟動 ShowActivity
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("order_details", orderDetails);
                intent.putExtra("total_amount", totalAmount);
                startActivity(intent);
            }
        });
    }

    private void updateOutput(TextView lblOutput) {
        StringBuilder outputStr = new StringBuilder();

        RadioGroup gender = findViewById(R.id.rgGender);
        RadioButton boy = findViewById(R.id.rdbBoy);
        RadioButton girl = findViewById(R.id.rdbGirl);
        if (boy.isChecked())
            outputStr.append("男性\n");
        else if (girl.isChecked())
            outputStr.append("女性\n");

        RadioGroup type = findViewById(R.id.rgType);
        RadioButton adult = findViewById(R.id.rdbAdult);
        RadioButton child = findViewById(R.id.rdbChild);
        RadioButton student = findViewById(R.id.rdbStudent);
        int price = 0;
        if (type.getCheckedRadioButtonId() == R.id.rdbAdult) {
            outputStr.append("成人票\n");
            price = 500;
        } else if (type.getCheckedRadioButtonId() == R.id.rdbChild) {
            outputStr.append("孩童票\n");
            price = 250;
        } else if (type.getCheckedRadioButtonId() == R.id.rdbStudent) {
            outputStr.append("學生票\n");
            price = 400;
        }

        EditText edtTxtNum = findViewById(R.id.edtTxtNum);
        String numStr = edtTxtNum.getText().toString();
        if (!numStr.isEmpty()) {
            int num = Integer.parseInt(numStr);
            outputStr.append(num).append("\n");
            outputStr.append("金額：").append(num * price).append("元\n");
            totalAmount = num * price;
        }

        lblOutput.setText(outputStr.toString());
    }
}
