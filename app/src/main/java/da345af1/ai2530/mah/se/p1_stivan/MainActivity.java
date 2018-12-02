package da345af1.ai2530.mah.se.p1_stivan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    NameFragment nameFragment;
    OverViewFragment overViewFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("NameFragment", this.MODE_PRIVATE);
        boolean firstTime = sharedPreferences.getBoolean("firstTime", true);
        /* Checks if it's the first time the app opens
           If it's not first time, skip input name
         */

        if(!firstTime){
            Intent intent = new Intent(getApplicationContext(), OverViewActivity.class);
            startActivity(intent);

        }
        else if (firstTime){
            setContentView(R.layout.activity_main);
        }

    }


}
