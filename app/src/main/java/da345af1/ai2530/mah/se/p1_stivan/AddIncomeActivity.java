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

public class AddIncomeActivity extends AppCompatActivity {

    private Spinner spIncomeCategory;
    private EditText etIncomeName;
    private EditText etIncomeAmount;
    private EditText etIncomeDate;
    private Button btnSaveIncome;
    private DatePickerDialog datePickerDialog;
    private Calendar cal;
    private String[] incomeCategoryArr = {"Lön", "Övrigt"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);

    String datestr = "";
    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        initComponents();
    }


    public void initComponents() {
        spIncomeCategory = findViewById(R.id.spIncomeCategory);
        spIncomeCategory.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, incomeCategoryArr));
        etIncomeName = findViewById(R.id.etIncomeName);
        etIncomeAmount = findViewById(R.id.etIncomeAmount);
        etIncomeDate = findViewById(R.id.etIncomeDatePicker);
        etIncomeDate.setOnClickListener(new DateListener());
        btnSaveIncome = findViewById(R.id.btnSaveIncome);
        btnSaveIncome.setOnClickListener(new SaveIncomeListener());
    }

    public String getSpIncomeCategory() {
        return spIncomeCategory.getSelectedItem().toString();
    }

    public String getEtIncomeName() {
        return etIncomeName.getText().toString();
    }

    public String getEtIncomeAmount() {
        return etIncomeAmount.getText().toString();
    }

    public Date getEtIncomeDate() {

        return date;
    }

    public void setEtIncomeDate(String date) {
        this.etIncomeDate.setText(date);
    }


    private class DateListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);


            datePickerDialog = new DatePickerDialog(AddIncomeActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                    datestr = day + "-" + (month + 1) + "-" + year;
                    setEtIncomeDate(datestr);

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

    private class SaveIncomeListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Income income = new Income(getSpIncomeCategory(), getEtIncomeName(), Double.parseDouble(getEtIncomeAmount()), getEtIncomeDate());
            IncomeActivity.incomesDatabase.incomeDao().addIncome(income);
            Toast.makeText(getApplicationContext(), "Ny inkomst tillagd", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddIncomeActivity.this, IncomeActivity.class));

        }
    }
}
