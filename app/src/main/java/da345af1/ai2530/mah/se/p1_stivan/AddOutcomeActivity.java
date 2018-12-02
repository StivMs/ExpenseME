package da345af1.ai2530.mah.se.p1_stivan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddOutcomeActivity extends AppCompatActivity {

    private Spinner spCategory;
    private EditText etType;
    private EditText etAmount;
    private EditText etDatepicker;
    private Button btnSaveOutcome;
    private DatePickerDialog datePickerDialog;
    private Calendar cal;
    private String[] outcomeCategoryArr = {"Livsmedel", "Boende", "Fritid", "Resor", "Övrigt"};
    String datestr = "";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outcome);
        initcomponents();

    }

    public void initcomponents() {
        spCategory = findViewById(R.id.spOutcomeCategory);
        spCategory.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, outcomeCategoryArr));
        etType = findViewById(R.id.etOutcomeName);
        etAmount = findViewById(R.id.etOutcomeAmount);
        etDatepicker = findViewById(R.id.etOutcomeDatePicker);
        etDatepicker.setOnClickListener(new DateListener());
        btnSaveOutcome = findViewById(R.id.btnSaveOutcome);
        btnSaveOutcome.setOnClickListener(new NewOutcomeListener());
    }

    public String getSpCategory() {
        return spCategory.getSelectedItem().toString();
    }

    public String getEtType() {
        return etType.getText().toString();
    }

    public String getEtAmount() {
        return etAmount.getText().toString();
    }

    public Date getEtDatepicker() {
        return date;
    }


    public int getImage() {
        int image;
        String imageName = getSpCategory();
        if (imageName == "Boende") {
            image = R.drawable.boende;
        } else if (imageName == "Fritid") {
            image = R.drawable.fritid;
        } else if (imageName == "Livsmedel") {
            image = R.drawable.livsmedel;
        } else if (imageName == "Resor") {
            image = R.drawable.resor;
        } else {
            image = R.drawable.other;
        }
        return image;
    }


    private class DateListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(AddOutcomeActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    datestr = day + "-" + (month + 1) + "-" + year;
                    etDatepicker.setText(datestr);
                    try {
                        date = sdf.parse(datestr);
                    } catch (ParseException e) {
                        e.printStackTrace();

                    }
                }

            }, year, month, day);
            datePickerDialog.show();
        }
    }


    private class NewOutcomeListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Outcomes outcomes = new Outcomes(getSpCategory(), getEtType(), Double.parseDouble(getEtAmount()), getEtDatepicker(), getImage());
            OutcomeActivity.outcomeDatabase.outcomeDAO().addOutcome(outcomes);
            Toast.makeText(getApplicationContext(), "Ny utgift tillagd", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), OutcomeActivity.class);
            startActivity(intent);

        }
    }


}
