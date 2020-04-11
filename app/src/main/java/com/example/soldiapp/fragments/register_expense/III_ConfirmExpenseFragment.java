package com.example.soldiapp.fragments.register_expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soldiapp.MainActivity;
import com.example.soldiapp.R;
import com.example.soldiapp.auxiliar.Expense;
import com.example.soldiapp.data_handling.ExpenseViewModel;
import com.example.soldiapp.data_handling.SharedViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class III_ConfirmExpenseFragment extends Fragment {

    SharedViewModel viewModel;
    ExpenseViewModel expenseViewModel;

    public III_ConfirmExpenseFragment() {
        //        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        expenseViewModel = new ViewModelProvider(getActivity()).get(ExpenseViewModel.class); //One dealing with database
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);  //One dealing with info between fragments

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iii_confirm_expense, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showExpenseSummary(view);

        final NavController navController = Navigation.findNavController(view);

        //Set toolbar title
        ((MainActivity) getActivity()).getToolbar().setTitle(getString(R.string.confirmExpense));

        //Add listeners to the buttons
        ArrayList<ImageView> confirmButtons = new ArrayList<ImageView>();

        confirmButtons.add((ImageView) view.findViewById(R.id.confirmExpenseButton));
        confirmButtons.add((ImageView) view.findViewById(R.id.cancelExpenseButton));

        for (ImageView button : confirmButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.confirmExpenseButton) {

                        saveExpense();

                        navController.navigate(R.id.action_iii_ConfirmExpenseFragment_to_homeFragment);
                        ((MainActivity) getActivity()).showBackButton(false);
                    } else {

                        Toast.makeText(getActivity(), getString(R.string.cancel_expense), Toast.LENGTH_SHORT).show();

                        navController.navigate(R.id.action_iii_ConfirmExpenseFragment_to_homeFragment);
                        ((MainActivity) getActivity()).showBackButton(false);
                    }

                }
            });
        }

    }

    //TODO Improve mechanism of this method (dynamic resource allocating instead of check each possible solution)
    private void showExpenseSummary(View view) {
        TextView expenseAmount = (TextView) view.findViewById(R.id.textConfirmMoney);
        Double money = viewModel.getExpense().getValue();
        expenseAmount.setText(money.toString() + getString(R.string.badge));

        ImageView expenseType = (ImageView) view.findViewById(R.id.imageConfirmExpenseType);
        switch (viewModel.getExpenseType().getValue()) {
            case "supermarket":
                expenseType.setImageResource(R.drawable.supermarket);
                break;
            case "transport":
                expenseType.setImageResource(R.drawable.transport);
                break;
            case "leisure":
                expenseType.setImageResource(R.drawable.leisure);
                break;
            case "shopping":
                expenseType.setImageResource(R.drawable.shopping);
                break;
            case "bills":
                expenseType.setImageResource(R.drawable.bills);
                break;
            case "other":
                expenseType.setImageResource(R.drawable.other);
                break;
        }

        ImageView paymentMethod = (ImageView) view.findViewById(R.id.imageConfirmPaymentMethod);
        if (viewModel.getPaymentMethod().getValue())
            paymentMethod.setImageResource(R.drawable.cash);
        else
            paymentMethod.setImageResource(R.drawable.card);

    }

    private void saveExpense() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);

        Expense expense = new Expense(viewModel.getExpense().getValue(),
                viewModel.getExpenseType().getValue(), viewModel.getPaymentMethod().getValue(),day,month,year);

        expenseViewModel.insert(expense);

        Toast.makeText(this.getActivity(), getString(R.string.add_expense), Toast.LENGTH_SHORT).show();

    }

}