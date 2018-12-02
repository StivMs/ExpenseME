package da345af1.ai2530.mah.se.p1_stivan;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class NameFragment extends Fragment {
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnNext;
    private boolean firstTime = true;
    private OverViewFragment overViewFragment;

    public NameFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        btnNext = view.findViewById(R.id.btnNext);
        overViewFragment = new OverViewFragment();
        btnNext.setOnClickListener(new ButtonListener());
        return view;
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            writeData();
            Intent intent = new Intent(getActivity(), OverViewActivity.class);
            startActivity(intent);

        }
    }

    /*
        SharedPreference method for saving first and lastname for later use in another activity / fragment
     */
    private void writeData() {
        firstTime = false;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("NameFragment", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("firstName", etFirstName.getText().toString());
        edit.putString("lastName", etLastName.getText().toString());
        edit.putBoolean("firstTime", firstTime);
        edit.commit();
    }


}
