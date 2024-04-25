package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private StringBuilder orderDetails = new StringBuilder();
    private int totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSelect = findViewById(R.id.btnSelect);
        Button buttonOk = findViewById(R.id.btnOk);
        TextView lblOutput = findViewById(R.id.lblOutput);
        lblOutput.setMovementMethod(new ScrollingMovementMethod());

        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outputStr = "";

                RadioGroup gender = findViewById(R.id.rgGender);
                RadioButton boy = findViewById(R.id.rdbBoy);
                RadioButton girl = findViewById(R.id.rdbGirl);
                if (boy.isChecked())
                    outputStr += "男性\n";
                else if (girl.isChecked())
                    outputStr += "女性\n";
                else {
                    Toast.makeText(MainActivity.this, "請選擇性別", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioGroup type = findViewById(R.id.rgType);
                RadioButton adult = findViewById(R.id.rdbAdult);
                RadioButton child = findViewById(R.id.rdbChild);
                RadioButton student = findViewById(R.id.rdbStudent);
                int price = 0;
                if (type.getCheckedRadioButtonId() == R.id.rdbAdult) {
                    outputStr += "成人票\n";
                    price = 500;
                } else if (type.getCheckedRadioButtonId() == R.id.rdbChild) {
                    outputStr += "孩童票\n";
                    price = 250;
                } else if (type.getCheckedRadioButtonId() == R.id.rdbStudent) {
                    outputStr += "學生票\n";
                    price = 400;
                } else {
                    Toast.makeText(MainActivity.this, "請選擇票種", Toast.LENGTH_SHORT).show();
                    return;
                }

                TextView edtTxtNum = findViewById(R.id.edtTxtNum);
                String numStr = edtTxtNum.getText().toString();
                if (numStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "請輸入張數", Toast.LENGTH_SHORT).show();
                    return;
                }

                int num = Integer.parseInt(numStr);
                int subTotal = num * price;
                outputStr += num + "張\n";
                outputStr += "金額：" + subTotal + "元\n";

                // 將訂單細節和總金額添加到 orderDetails 和 totalAmount 中
                orderDetails.append(outputStr).append("\n");
                totalAmount += subTotal;

                // 更新訂單顯示
                lblOutput.setText(orderDetails.toString() + "\n總金額：" + totalAmount + "元");
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDetails.length() == 0) {
                    Toast.makeText(MainActivity.this, "請先選擇票種", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 創建 Intent 對象並啟動 ShowActivity
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("order_details", orderDetails.toString());
                intent.putExtra("total_amount", totalAmount);
                startActivity(intent);
            }
        });
    }
}
