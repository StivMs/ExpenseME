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

public class OutcomeActivity extends AppCompatActivity {
    private Button btnAddOutcome, btnSearchFilter;
    double totOutcome = 0;
    private static final String TAG = "OverviewActivity";
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Outcomes> outcomesList;
    static OutcomeDatabase outcomeDatabase;
    private EditText etOutDateFrom, etOutDateTo;
    private Date outStartDate, outEndDate;
    private String strDateFrom, strDateTo;

    private DatePickerDialog datePickerDialog;
    private Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);
        recyclerView = findViewById(R.id.rvOutcomeList);
        btnAddOutcome = findViewById(R.id.btnAddOutcome);
        btnAddOutcome.setOnClickListener(new AddOutcomeListener());
        btnSearchFilter = findViewById(R.id.btnOutSearchViaDate);
        btnSearchFilter.setOnClickListener(new FilterListener());
        etOutDateFrom = findViewById(R.id.etOutDateFrom);
        etOutDateFrom.setOnClickListener(new DateFromListener());
        etOutDateTo = findViewById(R.id.etOutDateTo);
        etOutDateTo.setOnClickListener(new DateToListener());
        outcomeDatabase = Room.databaseBuilder(getApplicationContext(), OutcomeDatabase.class, "outcomesDB")
                .allowMainThreadQueries()
                .build();

        outcomesList = OutcomeActivity.outcomeDatabase.outcomeDAO().getAllOutcomes();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OutcomeAdapter(outcomesList, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    public void setEtOutDateFrom(String date) {
        this.etOutDateFrom.setText(date);
    }

    public void setEtOutDateTo(String date) {
        this.etOutDateTo.setText(date);
    }

    private class AddOutcomeListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), AddOutcomeActivity.class);
            startActivity(intent);
        }
    }

    private class DateFromListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    strDateFrom = day + "-" + (month + 1) + "-" + year;
                    setEtOutDateFrom(strDateFrom);
                    // convert string-date to a Date object

                    try {
                        outStartDate = sdf.parse(strDateFrom);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onDateSet: " + outStartDate);


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
                    setEtOutDateTo(strDateTo);
                    // convert string-date to a Date object

                    try {
                        outEndDate = sdf.parse(strDateTo);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onDateSet: " + outEndDate);


                }
            }, year, month, day);
            datePickerDialog.show();
            Toast.makeText(view.getContext(), strDateTo, Toast.LENGTH_LONG).show();

        }
    }

    private class FilterListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            outcomesList.clear();
            adapter.notifyDataSetChanged();

            outcomesList = outcomeDatabase.outcomeDAO().loadAllOutcomeFromDate(outStartDate, outEndDate);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            adapter = new OutcomeAdapter(outcomesList, getApplicationContext());
            recyclerView.setAdapter(adapter);

        }
    }

    public void onBackPressed() {

        this.startActivity(new Intent(OutcomeActivity.this, OverViewActivity.class));

        return;
    }
}
