package da345af1.ai2530.mah.se.p1_stivan;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class IncomeActivity extends AppCompatActivity {

    private static final String TAG = "IncomeActivity";
    private Button btnAddIncome;
    private Button btnSearchViaDate;
    private EditText etDateFrom;
    private EditText etDateTo;
    private Date startDate;
    private Date endDate;
    private Date dtIncome;
    private String strDateFrom, strDateTo;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Income> incomesList;
    public static IncomesDatabase incomesDatabase;

    private DatePickerDialog datePickerDialog;
    private Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        recyclerView = findViewById(R.id.rvIncomeList);
        btnSearchViaDate = findViewById(R.id.btnSearchViaDate);
        btnSearchViaDate.setOnClickListener(new FilterListener());
        btnAddIncome = findViewById(R.id.btnAddIncome);
        btnAddIncome.setOnClickListener(new AddIncomeListener());
        etDateFrom = findViewById(R.id.etDateFromSur);
        etDateFrom.setOnClickListener(new DateFromListener());
        etDateTo = findViewById(R.id.etDateToSur);
        etDateTo.setOnClickListener(new DateToListener());

        incomesDatabase = Room.databaseBuilder(getApplicationContext(), IncomesDatabase.class, "incomeDB").allowMainThreadQueries().build();
        incomesList = IncomeActivity.incomesDatabase.incomeDao().getAllIncomes();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IncomeAdapter(incomesList, this);
        recyclerView.setAdapter(adapter);
        getTotalAmount();
    }

    private class AddIncomeListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // Öppna new activity med inmatning input
            Intent intent = new Intent(view.getContext(), AddIncomeActivity.class);
            startActivity(intent);
        }
    }


    // returns the total amount of incomes
    public double getTotalAmount() {
        double totAmount = 0;
        for (int i = 0; i < incomesList.size(); i++) {
            totAmount += incomesList.get(i).getAmount();
        }
        return totAmount;
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
                    Log.d(TAG, "onDateSet: " + startDate);


                }
            }, year, month, day);
            datePickerDialog.show();
            Toast.makeText(view.getContext(), strDateFrom, Toast.LENGTH_LONG).show();

        }
    }


    private class DateToListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
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

                    Log.d(TAG, "onDateSet: " + endDate);
                }
            }, year, month, day);

            datePickerDialog.show();

        }
    }


    private class FilterListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // Show all income
            if (getEtDateFrom().equals("")) {
                incomesList = IncomeActivity.incomesDatabase.incomeDao().getAllIncomes();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new IncomeAdapter(incomesList, getApplicationContext());
                recyclerView.setAdapter(adapter);
            } else {
                incomesList.clear();
                adapter.notifyDataSetChanged();

                incomesList = IncomeActivity.incomesDatabase.incomeDao().loadAllIncomeFromDate(startDate, endDate);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new IncomeAdapter(incomesList, getApplicationContext());
                recyclerView.setAdapter(adapter);

                Log.d(TAG, "onClick: " + startDate + " " + endDate);
            }


        }
    }

    public void onBackPressed() {
        this.startActivity(new Intent(IncomeActivity.this, OverViewActivity.class));
        return;
    }

    public void setEtDateFrom(String dateFrom) {
        this.etDateFrom.setText(dateFrom);
    }

    public void setEtDateTo(String dateTo) {
        this.etDateTo.setText(dateTo);
    }

    public String getEtDateFrom() {
        return etDateFrom.getText().toString();
    }

    public String getEtDateTo() {
        return etDateTo.getText().toString();
    }
}
