package edu.birzeit.jetset.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import edu.birzeit.jetset.R;
import edu.birzeit.jetset.model.CustomArrayAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PassengerSecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PassengerSecondFragment extends Fragment {
    private static final String DEFAULT_LOCAL = "Palestine";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextInputEditText editIssueDate;
    private TextInputEditText editExpiryDate;
    private TextInputEditText editDateOfBirth;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PassengerSecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PassengerSecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PassengerSecondFragment newInstance(String param1, String param2) {
        PassengerSecondFragment fragment = new PassengerSecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_second, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editIssueDate = view.findViewById(R.id.editTextDate);
        editExpiryDate = view.findViewById(R.id.editTextDate2);
        editDateOfBirth = view.findViewById(R.id.editTextDate3);
        Spinner typeSpinner = view.findViewById(R.id.spinner);
        Spinner issuePlace = view.findViewById(R.id.spinnerCountry);
        Spinner nationality = view.findViewById(R.id.spinnerNationality);

        String[] types = {"Vegetarian", "Non-Vegetarian", "Vegan", "Pescatarian", "Diabetic", "Halal", "Kosher"};
        ArrayList<String> typesList = new ArrayList<>(List.of(types));
        CustomArrayAdapter adapter = new CustomArrayAdapter(getContext(), R.layout.spinner_item, typesList, 14);
        typeSpinner.setAdapter(adapter);

        SortedSet<String> countries = new TreeSet<>();
        for (Locale locale : Locale.getAvailableLocales()) {
            if (!TextUtils.isEmpty(locale.getDisplayCountry())) {
                countries.add(locale.getDisplayCountry());
            }
        }

        ArrayList<String> countriessList = new ArrayList<>(countries);
        CustomArrayAdapter adapterCountries = new CustomArrayAdapter(getContext(), R.layout.spinner_item, countriessList, 14);
        issuePlace.setAdapter(adapterCountries);
        nationality.setAdapter(adapterCountries);

        Locale defaultLocale = Locale.getDefault();
        String defaultCountry = defaultLocale.getDisplayCountry();
        if (!TextUtils.isEmpty(defaultCountry)) {
            int position = adapterCountries.getPosition(defaultCountry);
            if (position >= 0) {
                issuePlace.setSelection(position);
                nationality.setSelection(position);
            }
        }


        editIssueDate.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                showDatePickerDialog(editIssueDate);
                v.performClick();
            }
            return false;
        });
        editExpiryDate.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                showDatePickerDialog(editExpiryDate);
                v.performClick();
            }
            return false;
        });
        editDateOfBirth.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                showDatePickerDialog(editDateOfBirth);
                v.performClick();
            }
            return false;
        });
    }

    private void showDatePickerDialog(final EditText editText) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOnDateSetListener((year, month, day) -> {
            String selectedDate = day + "/" + (month + 1) + "/" + year;
            editText.setText(selectedDate);
        });
        newFragment.show(getParentFragmentManager(), "datePicker");
    }
}