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
import android.widget.TextView;
import android.widget.Toast;

import com.example.soldiapp.R;
import com.example.soldiapp.auxiliar.DayExpense;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.Expense_Type;
import com.example.soldiapp.auxiliar.MonthDate;
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
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class AnalysisMonthFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    PieChart expenseTypeChart;
    PieChart expensePaymentChart;

    LineChart lineExpenseChart;

    TextView totalText;

    List<DayExpense> dayExpenses = new ArrayList<>();

    List<MonthDate> monthsRegisteredList = new ArrayList<>();
    List<Expense_Type> expensesTypeList = new ArrayList<>();
    List<Expense_Payment> expensesPaymentList = new ArrayList<>();

    ExpenseViewModel expenseViewModel;
    Spinner spinnerMonth;

    public AnalysisMonthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class); //ViewModel dealing with database
        monthsRegisteredList = expenseViewModel.getMonthsRegistered();

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

        //Get last registered month and year
        MonthDate m  = monthsRegisteredList.get(0);

        //Fill line chart
        dayExpenses = expenseViewModel.getDayExpenses(m.getMonth(),m.getYear());
        fillLineChart(dayExpenses);

        totalText = view.findViewById(R.id.textTotalExpense);
        totalText.setText(totalExpense(dayExpenses)+getString(R.string.badge));

        //Fill expense type chart
        expensesTypeList = expenseViewModel.getSumTypeExpenses(m.getMonth(),m.getYear());
        fillExpenseTypeChart(expensesTypeList);

        //Fill expense payment chart
        expensesPaymentList = expenseViewModel.getSumPaymentExpenses(m.getMonth(),m.getYear());
        fillExpensePaymentChart(expensesPaymentList);
    }

    private double totalExpense(List<DayExpense> dayExpenses) {
        double sum = 0;
        for(DayExpense expense: dayExpenses){
            sum+=expense.getExpense();
        }
        return sum;
    }

    private void fillLineChart(List<DayExpense> expenses) {
        lineExpenseChart = (LineChart) getView().findViewById(R.id.lineMonthChart);

        lineExpenseChart.setDragEnabled(true);
        lineExpenseChart.setScaleEnabled(false);

        ArrayList<Entry> values = new ArrayList<>();
        int days = 0;
        for(int i=1; i<32;i++){
            if(days<expenses.size()) {
                if(i==expenses.get(days).getDay()){ //Add days in which there are expenses
                    values.add(new Entry(i,expenses.get(days).getExpense()));
                    days++;
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

    private void prepareSpinner(View view) {
        spinnerMonth = view.findViewById(R.id.spinnerMonth);

        ArrayAdapter<MonthDate> adapter = new ArrayAdapter<MonthDate>(getActivity(),android.R.layout.simple_spinner_item,monthsRegisteredList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapter);
        spinnerMonth.setOnItemSelectedListener(this);
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

        expenseTypeChart = getView().findViewById(R.id.expensesMonthTypeChart);

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

        expensePaymentChart = getView().findViewById(R.id.expensesMonthPaymentChart);

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
        String[] month_year = text.split(" - ");

        int month =  extractMonth(month_year[0]);
        int year = Integer.parseInt(month_year[1]);

        dayExpenses = expenseViewModel.getDayExpenses(month,year);
        fillLineChart(dayExpenses);

        totalText.setText(totalExpense(dayExpenses) + getString(R.string.badge));

        expensesTypeList = expenseViewModel.getSumTypeExpenses(month,year);
        fillExpenseTypeChart(expensesTypeList);

        expensesPaymentList = expenseViewModel.getSumPaymentExpenses(month,year);
        fillExpensePaymentChart(expensesPaymentList);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private int extractMonth(String s) {
        switch (s.toLowerCase()){
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                return -1;
        }
    }
}