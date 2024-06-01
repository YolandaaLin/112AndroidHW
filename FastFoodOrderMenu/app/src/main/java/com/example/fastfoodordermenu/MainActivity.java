package com.example.fastfoodordermenu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 定義 CheckBox 和 ImageView 變數
    private CheckBox chk1, chk2, chk3, chk4;
    private ImageView output1, output2, output3, output4;

    // 存儲勾選順序的列表
    private List<CheckBox> checkedOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到佈局中的 CheckBox
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);

        // 找到佈局中的 ImageView
        output1 = findViewById(R.id.output1);
        output2 = findViewById(R.id.output2);
        output3 = findViewById(R.id.output3);
        output4 = findViewById(R.id.output4);

        // 設置 CheckBox 的監聽器
        chk1.setOnCheckedChangeListener((buttonView, isChecked) -> updateOrder(chk1, isChecked));
        chk2.setOnCheckedChangeListener((buttonView, isChecked) -> updateOrder(chk2, isChecked));
        chk3.setOnCheckedChangeListener((buttonView, isChecked) -> updateOrder(chk3, isChecked));
        chk4.setOnCheckedChangeListener((buttonView, isChecked) -> updateOrder(chk4, isChecked));
    }

    // 更新訂單方法
    private void updateOrder(CheckBox checkBox, boolean isChecked) {
        if (isChecked) {
            // 如果是勾選操作，將 CheckBox 添加到列表末尾
            checkedOrder.add(checkBox);
        } else {
            // 如果是取消勾選操作，將 CheckBox 從列表中移除
            checkedOrder.remove(checkBox);
        }

        // 重置 ImageView 的可見性
        output1.setVisibility(View.INVISIBLE);
        output2.setVisibility(View.INVISIBLE);
        output3.setVisibility(View.INVISIBLE);
        output4.setVisibility(View.INVISIBLE);

        // 根據 checkedOrder 列表的順序更新 ImageView
        for (int i = 0; i < checkedOrder.size(); i++) {
            ImageView imgView = getImageViewByIndex(i);
            if (imgView != null) {
                CheckBox currentCheckBox = checkedOrder.get(i);
                if (currentCheckBox == chk1) {
                    imgView.setImageResource(R.drawable.burger);
                } else if (currentCheckBox == chk2) {
                    imgView.setImageResource(R.drawable.coffee);
                } else if (currentCheckBox == chk3) {
                    imgView.setImageResource(R.drawable.frenchfry);
                } else if (currentCheckBox == chk4) {
                    imgView.setImageResource(R.drawable.softdrink);
                }
                imgView.setVisibility(View.VISIBLE);
            }
        }
    }

    // 根據索引返回對應的 ImageView
    private ImageView getImageViewByIndex(int index) {
        switch (index) {
            case 0: return output1;
            case 1: return output2;
            case 2: return output3;
            case 3: return output4;
            default: return null;
        }
    }
}
