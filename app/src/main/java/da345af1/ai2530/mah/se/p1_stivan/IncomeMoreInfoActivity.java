package da345af1.ai2530.mah.se.p1_stivan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class IncomeMoreInfoActivity extends AppCompatActivity {
    private TextView tvIncMoreCategory;
    private TextView tvIncMoreName;
    private TextView tvIncMoreAmount;
    private TextView tvIncMoreDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_more_info);
        initComponents();
        Intent intent = getIntent();
        String category = intent.getStringExtra("incCategory");
        String name = intent.getStringExtra("incType");
        String amount = intent.getStringExtra("incAmount");
        String date = intent.getStringExtra("incDate");
        tvIncMoreName.setText(name);
        tvIncMoreCategory.setText(category);
        tvIncMoreAmount.setText(amount);
        tvIncMoreDate.setText(date);
    }


    public void initComponents() {
        tvIncMoreCategory = findViewById(R.id.tvMoreIncCategory);
        tvIncMoreName = findViewById(R.id.tvIncMoreName);
        tvIncMoreAmount = findViewById(R.id.tvIncMoreAmount);
        tvIncMoreDate = findViewById(R.id.tvIncMoreDate);
    }
}
