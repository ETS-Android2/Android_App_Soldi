package com.example.soldiapp.register_expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soldiapp.R;

import java.util.ArrayList;

public class III_ConfirmExpenseFragment extends Fragment {

    public III_ConfirmExpenseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iii_confirm_expense, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        ArrayList<Button> confirmButtons = new ArrayList<Button>();

        confirmButtons.add((Button) view.findViewById(R.id.confirmExpenseButton));

        for (Button button : confirmButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.action_iii_ConfirmExpenseFragment_to_homeFragment);
                }
            });
        }

    }
}