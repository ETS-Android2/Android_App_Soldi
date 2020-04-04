package com.example.soldiapp.register_expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soldiapp.MainActivity;
import com.example.soldiapp.R;

import java.util.ArrayList;

public class II_PaymentMethodFragment extends Fragment {

    public II_PaymentMethodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ii_payment_method, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        ((MainActivity)getActivity()).getToolbar().setTitle(getString(R.string.payment_method_title));

        ArrayList<ImageView> paymentButtons = new ArrayList<ImageView>();

        paymentButtons.add((ImageView) view.findViewById(R.id.cashPaymentButton));
        paymentButtons.add((ImageView) view.findViewById(R.id.cardPaymentButton));

        for (ImageView button : paymentButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.action_ii_PaymentMethodFragment_to_iii_ConfirmExpenseFragment);
                }
            });
        }

    }
}