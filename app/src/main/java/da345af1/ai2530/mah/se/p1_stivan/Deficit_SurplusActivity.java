package da345af1.ai2530.mah.se.p1_stivan;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Deficit_SurplusActivity extends AppCompatActivity {
    private TextView tvSurOrDef;
    private TextView tvSurDefAmount;
    private EditText etDateFrom, etDateTo;
    private Button btnSurplus;

    double totOutcome = 0;
    double totIncome = 0;
    List<Outcomes> outcomesList;
    List<Income> incomeList;
    OutcomeDatabase outcomeDatabase;
    IncomesDatabase incomesDatabase;

    private DatePickerDialog datePickerDialog;
    private Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);

    String strDateFrom, strDateTo;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);

    private Date startDate, endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deficit_surplus);

        outcomeDatabase = Room.databaseBuilder(getApplicationContext(), OutcomeDatabase.class, "outcomesDB")
                .allowMainThreadQueries()
                .build();

        incomesDatabase = Room.databaseBuilder(getApplicationContext(), IncomesDatabase.class, "incomeDB")
                .allowMainThreadQueries()
                .build();


        initComponents();
        returnRes();

    }


    public void initComponents() {
        etDateFrom = findViewById(R.id.etDateFromSur);
        etDateFrom.setOnClickListener(new DateFromListener());
        etDateTo = findViewById(R.id.etDateToSur);
        etDateTo.setOnClickListener(new DateToListener());
        btnSurplus = findViewById(R.id.btnSurplus);
        btnSurplus.setOnClickListener(new FilterAmountListener());
        tvSurOrDef = findViewById(R.id.tvSurOrDef);
        tvSurDefAmount = findViewById(R.id.tvSurDefAmount);
    }


    private class DateFromListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    strDateFrom = day + "-" + (month + 1) + "-" + year;
                    setEtDateFrom(strDateFrom);
                    // convert string-date to a Date object

                    try {
                        startDate = sdf.parse(strDateFrom);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.d("TEST", "onDateSet: " + startDate);


                }
            }, year, month, day);
            datePickerDialog.show();
            Toast.makeText(view.getContext(), strDateFrom, Toast.LENGTH_LONG).show();

        }
    }


    private class DateToListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            tvSurOrDef.setText("");
            tvSurDefAmount.setText("");
            datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    strDateTo = day + "-" + (month + 1) + "-" + year;
                    setEtDateTo(strDateTo);
                    // convert string-date to a Date object
                    try {
                        endDate = sdf.parse(strDateTo);
                    } catch (ParseException e) {
                        e.printStackTrace();

                    }

                    Log.d("TEST", "onDateSet: " + endDate);
                }
            }, year, month, day);

            datePickerDialog.show();

        }
    }


    public void returnRes() {
        // spara ner alla inkomst från båda datum
        // spara ner alla utkomst från båda datum (BEFORE)
        // Skapa en SQL - sats
        outcomesList = this.outcomeDatabase.outcomeDAO().getAllOutcomes();
        incomeList = this.incomesDatabase.incomeDao().getAllIncomes();


        for (int i = 0; i < outcomesList.size(); i++) {
            totOutcome += outcomesList.get(i).getAmount();
        }

        for (int i = 0; i < incomeList.size(); i++) {
            totIncome += incomeList.get(i).getAmount();
        }
        if (totIncome - totOutcome < 0) {
            tvSurDefAmount.setText(String.valueOf(totIncome - totOutcome));
            tvSurOrDef.setText("UNDERSKOTT");
        } else if (totIncome - totOutcome > 0) {
            tvSurDefAmount.setText(String.valueOf(totIncome - totOutcome));
            tvSurOrDef.setText("ÖVERSKOTT");
        }


    }

    public String getEtDateFrom() {
        return etDateFrom.getText().toString();
    }

    public void setEtDateFrom(String dateFrom) {
        this.etDateFrom.setText(dateFrom);
    }

    public String getEtDateTo() {
        return etDateTo.getText().toString();
    }

    public void setEtDateTo(String dateTo) {
        this.etDateTo.setText(dateTo);
    }

    private class FilterAmountListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            totIncome = 0;
            totOutcome = 0;
            incomeList = incomesDatabase.incomeDao().loadAllIncomeFromDate(startDate, endDate);
            outcomesList = outcomeDatabase.outcomeDAO().loadAllOutcomeFromDate(startDate, endDate);
            for (int i = 0; i < incomeList.size(); i++) {
                totIncome += incomeList.get(i).getAmount();

            }
            Log.d("test", "returnRes: " + totIncome);


            for (int i = 0; i < outcomesList.size(); i++) {
                totOutcome += outcomesList.get(i).getAmount();
            }


            if (totIncome - totOutcome < 0) {
                tvSurDefAmount.setText(String.valueOf(totIncome - totOutcome));
                tvSurOrDef.setText("UNDERSKOTT");
                tvSurOrDef.setTextColor(Color.RED);
            } else if (totIncome - totOutcome > 0) {
                tvSurDefAmount.setText(String.valueOf(totIncome - totOutcome));
                tvSurOrDef.setText("ÖVERSKOTT");
                tvSurOrDef.setTextColor(Color.GREEN);
            }
        }
    }
}
