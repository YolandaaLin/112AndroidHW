package com.example.menu;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 獲取訂單結果
        String mainMeal = getIntent().getStringExtra("mainMeal");
        String sideDishes = getIntent().getStringExtra("sideDishes");
        String beverage = getIntent().getStringExtra("beverage");

        // 顯示訂單結果
        TextView lblOutput = findViewById(R.id.lblOutput);
        lblOutput.setText(mainMeal + "\n" + sideDishes + "\n" + beverage);
    }
}
