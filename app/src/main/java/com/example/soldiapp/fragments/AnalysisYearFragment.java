package com.example.soldiapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.soldiapp.R;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.MonthDate;
import com.example.soldiapp.data_handling.ExpenseViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class AnalysisYearFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    PieChart expensesPaymentChart;
    List<Integer> yearsRegisteredList = new ArrayList<>();
    List<Expense_Payment> expensesPaymentList = new ArrayList<>();
    ExpenseViewModel expenseViewModel;

    public AnalysisYearFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class); //ViewModel dealing with database
        expensesPaymentList = expenseViewModel.getSumPaymentExpenses();
        yearsRegisteredList = expenseViewModel.getYearsRegistered();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analysis_year, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepareSpinner(view);

        fillExpensePaymentChart(expensesPaymentList);
    }

    private void prepareSpinner(View view) {
        Spinner spinnerYear = view.findViewById(R.id.spinnerYear);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(),android.R.layout.simple_spinner_item,yearsRegisteredList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapter);
        spinnerYear.setOnItemSelectedListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}