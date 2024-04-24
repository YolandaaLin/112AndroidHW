import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.midterm.R;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // 從 Intent 中取得傳遞的資料
        String orderDetails = getIntent().getStringExtra("order_details");
        int totalAmount = getIntent().getIntExtra("total_amount", 0);

        // 取得 TextView 參考
        TextView lblOutput = findViewById(R.id.lblOutput);

        // 顯示訂單細節和總金額
        lblOutput.setText(orderDetails + "\n總金額：" + totalAmount + "元");
    }
}
