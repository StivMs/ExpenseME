package da345af1.ai2530.mah.se.p1_stivan;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OverViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        initialize();
    }

    private void initialize(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        OverViewFragment overViewFragment  = (OverViewFragment) fragmentManager.findFragmentById(R.id.overviewFragment);
    }

}
