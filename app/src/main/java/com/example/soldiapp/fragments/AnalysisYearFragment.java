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
import com.example.soldiapp.auxiliar.DayExpense;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.Expense_Type;
import com.example.soldiapp.auxiliar.MonthDate;
import com.example.soldiapp.auxiliar.MonthExpense;
import com.example.soldiapp.data_handling.ExpenseViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class AnalysisYearFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    PieChart expensePaymentChart;
    PieChart expenseTypeChart;

    LineChart lineExpenseChart;

    List<MonthExpense> monthExpenses = new ArrayList<>();

    List<Integer> yearsRegisteredList = new ArrayList<>();

    List<Expense_Payment> expensesPaymentList = new ArrayList<>();
    List<Expense_Type> expensesTypeList = new ArrayList<>();

    ExpenseViewModel expenseViewModel;

    public AnalysisYearFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class); //ViewModel dealing with database

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

        int year = yearsRegisteredList.get(0);

        //Fill line chart
        monthExpenses = expenseViewModel.getMonthExpenses(year);
        fillLineChart(monthExpenses);

        //Fill expense type chart
        expensesTypeList = expenseViewModel.getSumTypeExpenses(year);
        fillExpenseTypeChart(expensesTypeList);

        //Fill payment type chart
        expensesPaymentList = expenseViewModel.getSumPaymentExpenses(year);
        fillExpensePaymentChart(expensesPaymentList);

    }

    private void prepareSpinner(View view) {
        Spinner spinnerYear = view.findViewById(R.id.spinnerYear);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(),android.R.layout.simple_spinner_item,yearsRegisteredList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapter);
        spinnerYear.setOnItemSelectedListener(this);
    }

    private void fillLineChart(List<MonthExpense> expenses) {
        lineExpenseChart = (LineChart) getView().findViewById(R.id.lineYearChart);

        lineExpenseChart.setDragEnabled(true);
        lineExpenseChart.setScaleEnabled(false);

        ArrayList<Entry> values = new ArrayList<>();
        int months = 0;
        for(int i=1; i<13;i++){
            if(months<expenses.size()) {
                if(i==expenses.get(months).getMonth()){ //Add months in which there are expenses
                    values.add(new Entry(i,expenses.get(months).getExpense()));
                    months++;
                }
            }else{
                values.add(new Entry(i,0));
            }

        }

        LineDataSet dataSet = new LineDataSet(values,"Expenses");

        dataSet.setFillAlpha(110);
        dataSet.setColor(Color.RED);
        dataSet.setLineWidth(3f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        LineData data = new LineData(dataSets);

        lineExpenseChart.setData(data);

        lineExpenseChart.invalidate(); // refreshes chart
    }


    private PieChart chartPersonalization(PieChart p){
        p.setUsePercentValues(true);
        p.getDescription().setEnabled(false);
        p.setExtraOffsets(5, 10, 5, 5);

        p.setDragDecelerationFrictionCoef(0.95f);

        p.setDrawHoleEnabled(true);
        p.setHoleColor(Color.WHITE);
        p.setTransparentCircleRadius(61f);

        return p;
    }

    private void fillExpenseTypeChart(List<Expense_Type> expenses) {

        expenseTypeChart = getView().findViewById(R.id.expensesYearTypeChart);

        expenseTypeChart = chartPersonalization(expenseTypeChart);

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

        expenseTypeChart.invalidate(); // refreshes chart

    }

    private void fillExpensePaymentChart(List<Expense_Payment> expenses) {

        expensePaymentChart = getView().findViewById(R.id.expensesYearPaymentChart);

        expensePaymentChart = chartPersonalization(expensePaymentChart);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        for (Expense_Payment expense : expenses) {
            String payment = "card";
            if(expense.isPaymentWithCash())
                payment="cash";
            yValues.add(new PieEntry((int) expense.getExpense(),payment));
        }

        PieDataSet dataSetExpenseType = new PieDataSet(yValues, "Expense Payment");
        dataSetExpenseType.setSliceSpace(3f);
        dataSetExpenseType.setSelectionShift(5f);
        dataSetExpenseType.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSetExpenseType);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        expensePaymentChart.setData(data);

        expensePaymentChart.invalidate(); // refreshes chart

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        int year = Integer.parseInt(text);

        monthExpenses = expenseViewModel.getMonthExpenses(year);
        fillLineChart(monthExpenses);

        expensesTypeList = expenseViewModel.getSumTypeExpenses(year);
        fillExpenseTypeChart(expensesTypeList);

        expensesPaymentList = expenseViewModel.getSumPaymentExpenses(year);
        fillExpensePaymentChart(expensesPaymentList);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}