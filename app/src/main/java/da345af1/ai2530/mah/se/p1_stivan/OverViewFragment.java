package da345af1.ai2530.mah.se.p1_stivan;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverViewFragment extends Fragment {
    private TextView tvCustomMessage;
    private Button btnIncome;
    private Button btnOutcome;
    private Button btnSurDef;
    private String fullName;
    OutcomeActivity outcomeActivity;

    public OverViewFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_over_view, container, false);
        tvCustomMessage = view.findViewById(R.id.tvNameMessage);
        btnIncome = view.findViewById(R.id.btnIncome);
        btnIncome.setOnClickListener(new IncomeListener());
        btnOutcome = view.findViewById(R.id.btnOutcome);
        btnOutcome.setOnClickListener(new OutcomeListener());
        btnSurDef = view.findViewById(R.id.btnSurDef);
        btnSurDef.setOnClickListener(new surplusDefictListener());
        readName();
        return view;
    }

    public String getTvCustomMessage() {
        return tvCustomMessage.getText().toString();
    }

    public void setTvCustomMessage(String message) {
        this.tvCustomMessage.setText(message);
    }


    /*
        Read data from SharedPreference
     */
    public void readName() {
        SharedPreferences pref = getActivity().getSharedPreferences("NameFragment", Activity.MODE_PRIVATE);
        fullName = "Hej " + pref.getString("firstName", null) + " " + pref.getString("lastName", null) + ", vad vill du kolla på idag?";
        setTvCustomMessage(fullName);
    }

    private class OutcomeListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), OutcomeActivity.class);
            startActivity(intent);
        }
    }

    private class IncomeListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), IncomeActivity.class);
            startActivity(intent);
        }
    }

    private class surplusDefictListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), Deficit_SurplusActivity.class);
            startActivity(intent);
        }
    }
}
