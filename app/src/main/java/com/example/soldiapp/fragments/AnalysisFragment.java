package com.example.soldiapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.soldiapp.R;
import com.example.soldiapp.auxiliar.Expense;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.Expense_Type;
import com.example.soldiapp.data_handling.ExpenseViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class AnalysisFragment extends Fragment {

    PieChart expenseTypeChart;
    PieChart expensesPaymentChart;
    ExpenseViewModel expenseViewModel;
    List<Expense_Type> expensesTypeList = new ArrayList<>();
    List<Expense_Payment> expensesPaymentList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class); //ViewModel dealing with database
        expensesTypeList = expenseViewModel.getSumTypeExpenses();
        expensesPaymentList = expenseViewModel.getSumPaymentExpenses();
//        expenseViewModel.getAllExpenses().observe(getViewLifecycleOwner(), new Observer<List<Expense>>() {
//            @Override
//            public void onChanged(List<Expense> expenses) {
//                fillExpenseTypeChart(expenses);
//            }
//        });
        return inflater.inflate(R.layout.fragment_analysis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillExpenseTypeChart(expensesTypeList);
        fillExpensePaymentChart(expensesPaymentList);


    }

    private void fillExpenseTypeChart(List<Expense_Type> expenses) {

        expenseTypeChart = getView().findViewById(R.id.expensesTypeChart);

        expenseTypeChart.setUsePercentValues(true);
        expenseTypeChart.getDescription().setEnabled(false);
        expenseTypeChart.setExtraOffsets(5, 10, 5, 5);

        expenseTypeChart.setDragDecelerationFrictionCoef(0.95f);

        expenseTypeChart.setDrawHoleEnabled(true);
        expenseTypeChart.setHoleColor(Color.WHITE);
        expenseTypeChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        for (Expense_Type expense : expenses) {
            yValues.add(new PieEntry((int) expense.getExpense(), expense.getExpenseType()));
        }

        PieDataSet dataSetExpenseType = new PieDataSet(yValues, "Expense Types");
        dataSetExpenseType.setSliceSpace(3f);
        dataSetExpenseType.setSelectionShift(5f);
        dataSetExpenseType.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSetExpenseType);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        expenseTypeChart.setData(data);
    }

    private void fillExpensePaymentChart(List<Expense_Payment> expenses) {

        expensesPaymentChart = getView().findViewById(R.id.expensesPaymentChart);

        expensesPaymentChart.setUsePercentValues(true);
        expensesPaymentChart.getDescription().setEnabled(false);
        expensesPaymentChart.setExtraOffsets(5, 10, 5, 5);

        expensesPaymentChart.setDragDecelerationFrictionCoef(0.95f);

        expensesPaymentChart.setDrawHoleEnabled(true);
        expensesPaymentChart.setHoleColor(Color.WHITE);
        expensesPaymentChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        for (Expense_Payment expense : expenses) {
            yValues.add(new PieEntry((int) expense.getExpense(), expense.isPaymentWithCash()));
        }

        PieDataSet dataSetExpenseType = new PieDataSet(yValues, "Payment Types");
        dataSetExpenseType.setSliceSpace(3f);
        dataSetExpenseType.setSelectionShift(5f);
        dataSetExpenseType.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSetExpenseType);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        expensesPaymentChart.setData(data);
    }
}


