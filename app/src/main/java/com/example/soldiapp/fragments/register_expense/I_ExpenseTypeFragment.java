package com.example.soldiapp.fragments.register_expense;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.soldiapp.MainActivity;
import com.example.soldiapp.R;
import com.example.soldiapp.data_handling.SharedViewModel;

import java.util.ArrayList;

public class I_ExpenseTypeFragment extends Fragment {

    SharedViewModel viewModel;

    public I_ExpenseTypeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        return inflater.inflate(R.layout.fragment_i_expense_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        //Change toolbar title
        ((MainActivity)getActivity()).getToolbar().setTitle(getString(R.string.addingExpense));

        //Add buttons listeners
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
                    saveExpenseType(v);
                    navController.navigate(R.id.action_i_ExpenseTypeFragment_to_ii_PaymentMethodFragment);
                }
            });
        }

    }

    private void saveExpenseType(View v){
        switch (v.getId()){
            case R.id.supermarketTypeButton:
                viewModel.setExpenseType("supermarket");
                break;
            case R.id.transportTypeButton:
                viewModel.setExpenseType("transport");
                break;
            case R.id.leisureTypeButton:
                viewModel.setExpenseType("leisure");
                break;
            case R.id.shoppingTypeButton:
                viewModel.setExpenseType("shopping");
                break;
            case R.id.billsTypeButton:
                viewModel.setExpenseType("bills");
                break;
            case R.id.otherTypeButton:
                viewModel.setExpenseType("other");
                break;
            default:
                ;
        }
    }
}