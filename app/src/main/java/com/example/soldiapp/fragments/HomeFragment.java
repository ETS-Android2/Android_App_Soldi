package com.example.soldiapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soldiapp.MainActivity;
import com.example.soldiapp.R;
import com.example.soldiapp.data_handling.SharedViewModel;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.example.soldiapp.fragments.SettingsFragment.APP_PREFERENCES;

public class HomeFragment extends Fragment {

    private SharedViewModel viewModel;
    private EditText inputExpense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        inputExpense = v.findViewById(R.id.inputExpense);
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        //checkFirstAdvice();
        
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        ((MainActivity) getActivity()).showBackButton(false);

        ImageView button = view.findViewById(R.id.startExpenseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputExpense(inputExpense.getText().toString())) {
                    saveExpense();
                    navController.navigate(R.id.action_homeFragment_to_i_ExpenseTypeFragment);
                    ((MainActivity) getActivity()).showBackButton(true);
                } else {
                    inputExpense.setText("");
                }
            }
        });

    }

    /*private void checkFirstAdvice() {
        SharedPreferences settings = getActivity().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        boolean firstExpense =  settings.getBoolean("firstExpense",true);
        if (firstExpense)
            ((TextView)getView().findViewById(R.id.initialAdvise)).setText(getString(R.string.firstTime));
        else
            ((TextView)getView().findViewById(R.id.initialAdvise)).setText("");
    }*/

    public boolean checkInputExpense(String input) {
        try {
            double expense = Double.parseDouble(input);
            if (expense >= 0.01 && expense < 1000000) {
                String number = String.valueOf(expense);
                String[] checkParts = number.split("\\.");

                String integerPart = checkParts[0];
                String decimalPart = checkParts[1];

                //Not allowing bigger numbers than 999 999
                if (integerPart.length() > 6 || integerPart.length() < 1)
                    return false;

                if (checkParts.length > 1)
                    if (decimalPart.length() > 2 || decimalPart.length() == 0)
                        return false;

                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void saveExpense() {
        double expense = Double.parseDouble(inputExpense.getText().toString());
        String expense_tmp = String.format("%.2f", expense);

        Locale locale = new Locale(getActivity().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getString("locale", "en"));
        NumberFormat nf = NumberFormat.getInstance(locale);

        Number parsedNumber = null;
        try {
            parsedNumber = nf.parse(expense_tmp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        expense = parsedNumber.doubleValue();
        viewModel.setExpense(expense);
    }


}
