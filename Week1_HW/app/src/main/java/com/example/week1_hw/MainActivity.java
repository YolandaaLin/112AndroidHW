package com.example.week1_hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void button_Click(View view){
        EditText edtName = (EditText) findViewById(R.id.edtName);
        EditText txvPassWord =(EditText) findViewById(R.id.TxvPassword);
        TextView txvShow  =(TextView) findViewById(R.id.txvShow);

        String name = edtName.getText().toString();
        String password = txvPassWord.getText().toString();
        if(name.equals("A111221064") && password.equals("05091026") )
        {
            txvShow.setText("密碼輸入正確!\n你的學號是:" + name);
        }
        else
        {
            txvShow.setText("輸入錯誤!\n帳號:A111221064\n密碼:05091026" );
        }

    }
}