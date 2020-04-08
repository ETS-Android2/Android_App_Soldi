package com.example.soldiapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.soldiapp.R;
import com.example.soldiapp.auxiliar.Expense_Type;
import com.example.soldiapp.auxiliar.MonthDate;
import com.example.soldiapp.data_handling.ExpenseViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class AnalysisMonthFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    PieChart expenseTypeChart;
    List<MonthDate> monthsRegisteredList = new ArrayList<>();
    List<Expense_Type> expensesTypeList = new ArrayList<>();
    ExpenseViewModel expenseViewModel;

    public AnalysisMonthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class); //ViewModel dealing with database
        monthsRegisteredList = expenseViewModel.getMonthsRegistered();
        expensesTypeList = expenseViewModel.getSumTypeExpenses();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analysis_month, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepareSpinner(view);

        fillExpenseTypeChart(expensesTypeList);
    }

    private void prepareSpinner(View view) {
        Spinner spinnerMonth = view.findViewById(R.id.spinnerMonth);

        ArrayAdapter<MonthDate> adapter = new ArrayAdapter<MonthDate>(getActivity(),android.R.layout.simple_spinner_item,monthsRegisteredList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapter);
        spinnerMonth.setOnItemSelectedListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}