package com.example.soldiapp.fragments.register_expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;

public class II_PaymentMethodFragment extends Fragment {

    SharedViewModel viewModel;

    public II_PaymentMethodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ii_payment_method, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        //Set toolbar title
        ((MainActivity) getActivity()).getToolbar().setTitle(getString(R.string.addingExpense));

        //Add listeners to buttons
        ArrayList<ImageView> paymentButtons = new ArrayList<ImageView>();

        paymentButtons.add((ImageView) view.findViewById(R.id.cashPaymentButton));
        paymentButtons.add((ImageView) view.findViewById(R.id.cardPaymentButton));

        for (ImageView button : paymentButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    savePaymentMethod(v);
                    navController.navigate(R.id.action_ii_PaymentMethodFragment_to_iii_ConfirmExpenseFragment);
                }
            });
        }

    }

    private void savePaymentMethod(View v){
        switch (v.getId()){
            case R.id.cashPaymentButton:
                viewModel.setPaymentMethod(true);
                break;
            case R.id.cardPaymentButton:
                viewModel.setPaymentMethod(false);
                break;
            default:
                ;
        }
    }
}