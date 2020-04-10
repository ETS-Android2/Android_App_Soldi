package com.example.soldiapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

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
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        ((MainActivity) getActivity()).getToolbar().setTitle(getString(R.string.app_name)); //When going back from other fragment it is needed

        ImageView button = view.findViewById(R.id.startExpenseButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputExpense()) {
                    saveExpense();
                    navController.navigate(R.id.action_homeFragment_to_i_ExpenseTypeFragment);
                    ((MainActivity) getActivity()).showBackButton(true);
                } else {
                    inputExpense.setText("");
                }
            }
        });

    }

    private boolean checkInputExpense() {
        try {
            //TODO validate all possible inputs
            double expense = Double.parseDouble(inputExpense.getText().toString());
            return true;
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
