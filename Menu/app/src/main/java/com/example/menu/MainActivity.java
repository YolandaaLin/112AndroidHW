package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Spinner itemSpinner;
    private ListView menuListView;
    private TextView txvMainMeal, txvSideDishes, txvBeverage;
    private ArrayAdapter<CharSequence> foodCategoriesAdapter, mainMealAdapter, sideDishesAdapter, drinksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 設置工具欄
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // 初始化視圖
        itemSpinner = findViewById(R.id.item);
        menuListView = findViewById(R.id.menu);
        txvMainMeal = findViewById(R.id.txvMainMeal);
        txvSideDishes = findViewById(R.id.txvSideDishes);
        txvBeverage = findViewById(R.id.txvBeverage);

        // 設置Spinner的適配器
        foodCategoriesAdapter = ArrayAdapter.createFromResource(this, R.array.food_categories, android.R.layout.simple_spinner_item);
        foodCategoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(foodCategoriesAdapter);

        // 設置ListView的適配器
        mainMealAdapter = ArrayAdapter.createFromResource(this, R.array.menu1, android.R.layout.simple_list_item_1);
        sideDishesAdapter = ArrayAdapter.createFromResource(this, R.array.menu2, android.R.layout.simple_list_item_1);
        drinksAdapter = ArrayAdapter.createFromResource(this, R.array.drinks, android.R.layout.simple_list_item_1);

        // 設置Spinner選擇事件
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 根據選擇的類別更新ListView的數據
                switch (position) {
                    case 0:
                        menuListView.setAdapter(mainMealAdapter);
                        break;
                    case 1:
                        menuListView.setAdapter(sideDishesAdapter);
                        break;
                    case 2:
                        menuListView.setAdapter(drinksAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 當沒有選擇任何項目時，不做任何操作
            }
        });

        // 設置ListView點擊事件
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 獲取選中的項目
                String selectedItem = (String) parent.getItemAtPosition(position);
                // 根據選擇的類別更新相應的TextView
                switch (itemSpinner.getSelectedItemPosition()) {
                    case 0:
                        txvMainMeal.setText("主餐: " + selectedItem);
                        break;
                    case 1:
                        txvSideDishes.setText("附餐: " + selectedItem);
                        break;
                    case 2:
                        txvBeverage.setText("飲料: " + selectedItem);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 處理菜單項目點擊事件
        int itemId = item.getItemId();
        if (itemId == R.id.send) {
            // 當按下送出按鈕時，創建一個Intent將訂單結果傳遞到SecondActivity
            Intent intent = new Intent(this, SecondActivity.class);
            // 獲取訂單結果
            String mainMeal = txvMainMeal.getText().toString();
            String sideDishes = txvSideDishes.getText().toString();
            String beverage = txvBeverage.getText().toString();
            // 在Intent中傳遞訂單結果
            intent.putExtra("mainMeal", mainMeal);
            intent.putExtra("sideDishes", sideDishes);
            intent.putExtra("beverage", beverage);
            // 啟動SecondActivity
            startActivity(intent);
            return true;
        } else if (itemId == R.id.cancel) {
            // 當按下取消按鈕時，清空所有 TextView 的內容
            txvMainMeal.setText("主餐: ");
            txvSideDishes.setText("附餐: ");
            txvBeverage.setText("飲料: ");
            Toast.makeText(this, "取消選擇", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


}
