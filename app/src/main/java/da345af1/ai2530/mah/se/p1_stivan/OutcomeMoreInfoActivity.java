package da345af1.ai2530.mah.se.p1_stivan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class OutcomeMoreInfoActivity extends AppCompatActivity {
    private ImageView ivOutcomeIMG;
    private TextView tvOutMoreName;
    private TextView tvOutMoreCategory;
    private TextView tvOutMoreAmount;
    private TextView tvOutMoreDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome_more_info);
        initComponents();
        readIntent();
    }


    public void initComponents() {
        ivOutcomeIMG = findViewById(R.id.ivOutcomeIMG);
        tvOutMoreName = findViewById(R.id.tvOutMoreName);
        tvOutMoreCategory = findViewById(R.id.tvOutMoreCategory);
        tvOutMoreAmount = findViewById(R.id.tvOutMoreAmount);
        tvOutMoreDate = findViewById(R.id.tvOutMoreDate);
    }

    public void readIntent() {
        Intent intent = getIntent();
        setTvOutMoreCategory(intent.getStringExtra("outCategory"));
        setIvOutcomeIMG(intent.getIntExtra("outIMG", 0));
        setTvOutMoreName(intent.getStringExtra("outType"));
        setTvOutMoreAmount(intent.getStringExtra("outAmount"));
        setTvOutMoreDate(intent.getStringExtra("outDate"));
    }

    public void setIvOutcomeIMG(int img) {
        this.ivOutcomeIMG.setImageResource(img);
    }

    public void setTvOutMoreName(String name) {
        this.tvOutMoreName.setText(name);
    }

    public void setTvOutMoreCategory(String category) {
        this.tvOutMoreCategory.setText(category);
    }

    public void setTvOutMoreAmount(String amount) {
        this.tvOutMoreAmount.setText(amount);
    }

    public void setTvOutMoreDate(String date) {
        this.tvOutMoreDate.setText(date);
    }
}
