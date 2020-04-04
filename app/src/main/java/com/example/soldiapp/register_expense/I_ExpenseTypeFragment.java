package com.example.soldiapp.register_expense;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.soldiapp.MainActivity;
import com.example.soldiapp.R;

import java.util.ArrayList;

public class I_ExpenseTypeFragment extends Fragment {

    public I_ExpenseTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_i_expense_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        ((MainActivity)getActivity()).getToolbar().setTitle(getString(R.string.expense_type_title));

        ArrayList<ImageView> typeButtons = new ArrayList<ImageView>();

        typeButtons.add((ImageView) view.findViewById(R.id.supermarketTypeButton));
        typeButtons.add((ImageView) view.findViewById(R.id.transportTypeButton));
        typeButtons.add((ImageView) view.findViewById(R.id.leisureTypeButton));
        typeButtons.add((ImageView) view.findViewById(R.id.shoppingTypeButton));
        typeButtons.add((ImageView) view.findViewById(R.id.billsTypeButton));
        typeButtons.add((ImageView) view.findViewById(R.id.otherTypeButton));

        for (ImageView button : typeButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.action_i_ExpenseTypeFragment_to_ii_PaymentMethodFragment);
                }
            });
        }

    }
}