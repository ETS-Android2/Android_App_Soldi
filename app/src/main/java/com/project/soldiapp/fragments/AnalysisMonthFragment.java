package com.project.soldiapp.fragments;

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

import com.project.soldiapp.R;
import com.project.soldiapp.utils.ChartAuxiliar;
import com.project.soldiapp.auxiliar.DayExpense;
import com.project.soldiapp.auxiliar.Expense_Payment;
import com.project.soldiapp.auxiliar.Expense_Type;
import com.project.soldiapp.auxiliar.MonthDate;
import com.project.soldiapp.data_handling.ExpenseViewModel;
import com.project.soldiapp.utils.MonthHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class AnalysisMonthFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    PieChart expenseTypeChart;
    PieChart expensePaymentChart;

    LineChart lineExpenseChart;

    TextView totalText;
    TextView titleLineChart;

    List<DayExpense> dayExpenses = new ArrayList<>();

    List<MonthDate> monthsRegisteredList = new ArrayList<>();
    List<Expense_Type> expensesTypeList = new ArrayList<>();
    List<Expense_Payment> expensesPaymentList = new ArrayList<>();

    boolean firstCallSpinner = true;

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

        if(monthsRegisteredList.size()!=0){
            prepareSpinner(view);

            //Get last registered month and year
            MonthDate m = monthsRegisteredList.get(0);

            //Title line chart
            titleLineChart = getView().findViewById(R.id.titleLineChartMonth);
            titleLineChart.setText(getString(R.string.currentMonth) +" "+ MonthHandler.capitalize(MonthHandler.numberMonthToText(m.getMonth())));

            //Fill line chart
            dayExpenses = expenseViewModel.getDayExpenses(m.getMonth(), m.getYear());
            fillLineChart(dayExpenses);

            //Total expense
            totalText = view.findViewById(R.id.textTotalExpense);
            totalText.setText(String.format("%.2f",totalExpense(dayExpenses)) + " " + getString(R.string.badge));

            //Fill expense type chart
            expensesTypeList = expenseViewModel.getSumTypeExpenses(m.getMonth(), m.getYear());
            fillExpenseTypeChart(expensesTypeList);
            //Fill breakdown of type expenses
            fillBreakdownTypeExpenses();

            //Fill expense payment chart
            expensesPaymentList = expenseViewModel.getSumPaymentExpenses(m.getMonth(), m.getYear());
            fillExpensePaymentChart(expensesPaymentList);
            fillBreakdownPayment();
        }

    }

    private void fillBreakdownTypeExpenses() {

        TextView supermarketText = getView().findViewById(R.id.MonthlyExpenseSupermarket);
        supermarketText.setText("0"+getString(R.string.badge));

        TextView transportText = getView().findViewById(R.id.MonthlyExpenseTransport);
        transportText.setText("0"+getString(R.string.badge));

        TextView leisureText = getView().findViewById(R.id.MonthlyExpenseLeisure);
        leisureText.setText("0"+getString(R.string.badge));

        TextView shoppingText = getView().findViewById(R.id.MonthlyExpenseShopping);
        shoppingText.setText("0"+getString(R.string.badge));

        TextView billsText = getView().findViewById(R.id.MonthlyExpenseBills);
        billsText.setText("0"+getString(R.string.badge));

        TextView otherText = getView().findViewById(R.id.MonthlyExpenseOther);
        otherText.setText("0"+getString(R.string.badge));

        for (Expense_Type exp : expensesTypeList) {
            if (exp.getExpenseType().equals(getString(R.string.supermarket_type))) {
                if (exp.getExpense() % 1 == 0) //if no decimal part
                    supermarketText.setText(((int) exp.getExpense()) + getString(R.string.badge));
                else
                    supermarketText.setText(String.format("%.2f", exp.getExpense()) + getString(R.string.badge));
            } else if (exp.getExpenseType().equals(getString(R.string.transport_type))) {
                if (exp.getExpense() % 1 == 0)
                    transportText.setText(((int) exp.getExpense()) + getString(R.string.badge));
                else
                    transportText.setText(String.format("%.2f", exp.getExpense()) + getString(R.string.badge));
            } else if (exp.getExpenseType().equals(getString(R.string.leisure_type))) {
                if (exp.getExpense() % 1 == 0)
                    leisureText.setText(((int) exp.getExpense()) + getString(R.string.badge));
                else
                    leisureText.setText(String.format("%.2f", exp.getExpense()) + getString(R.string.badge));
            } else if (exp.getExpenseType().equals(getString(R.string.shopping_type))) {
                if (exp.getExpense() % 1 == 0)
                    shoppingText.setText(((int) exp.getExpense()) + getString(R.string.badge));
                else
                    shoppingText.setText(String.format("%.2f", exp.getExpense()) + getString(R.string.badge));
            } else if (exp.getExpenseType().equals(getString(R.string.bills_type))) {
                if (exp.getExpense() % 1 == 0)
                    billsText.setText(((int) exp.getExpense()) + getString(R.string.badge));
                else
                    billsText.setText(String.format("%.2f", exp.getExpense()) + getString(R.string.badge));
            } else if (exp.getExpenseType().equals(getString(R.string.other_type))) {
                if (exp.getExpense() % 1 == 0)
                    otherText.setText(((int) exp.getExpense()) + getString(R.string.badge));
                else
                    otherText.setText(String.format("%.2f", exp.getExpense()) + getString(R.string.badge));
            }
        }
    }

    private void fillBreakdownPayment(){
        TextView cashText = getView().findViewById(R.id.MonthlyExpenseCash);
        cashText.setText("0"+getString(R.string.badge));

        TextView cardText = getView().findViewById(R.id.MonthlyExpenseCard);
        cardText.setText("0"+getString(R.string.badge));

        for (Expense_Payment exp : expensesPaymentList) {
            if (exp.isPaymentWithCash())
                if (exp.getExpense() % 1 == 0) //if no decimal part
                    cashText.setText(((int) exp.getExpense()) + getString(R.string.badge));
                else
                    cashText.setText(String.format("%.2f", exp.getExpense()) + getString(R.string.badge));
            else {
                if (exp.getExpense() % 1 == 0) //if no decimal part
                    cardText.setText(((int) exp.getExpense()) + getString(R.string.badge));
                else
                    cardText.setText(String.format("%.2f", exp.getExpense()) + getString(R.string.badge));
            }

        }
    }

    private double totalExpense(List<DayExpense> dayExpenses) {
        double sum = 0;
        for (DayExpense expense : dayExpenses) {
            sum += expense.getExpense();
        }
        return sum;
    }

    private void fillLineChart(List<DayExpense> expenses) {
        lineExpenseChart = getView().findViewById(R.id.lineMonthChart);

        lineExpenseChart = ChartAuxiliar.setLineChartConf(lineExpenseChart);

        ArrayList<Entry> values = new ArrayList<>();
        int days = 0;
        for (int i = 1; i < 32; i++) {
            if (days < expenses.size()) {
                if (i == expenses.get(days).getDay()) { //Add days in which there are expenses
                    values.add(new Entry(i, (float) expenses.get(days).getExpense()));
                    days++;
                } else {
                    if (i == 1 || i == 31)
                        values.add(new Entry(i, 0));
                }
            } else {
                if (i == 1 || i == 31)
                    values.add(new Entry(i, 0));
            }

        }

        //Dataset
        LineDataSet dataSet = new LineDataSet(values, "");
        dataSet = ChartAuxiliar.setLineChartDataset(dataSet,getActivity());

        //Add datasets
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        LineData data = new LineData(dataSets);

        lineExpenseChart.setData(data);

        lineExpenseChart.invalidate(); // refreshes chart
    }

    private void prepareSpinner(View view) {
        spinnerMonth = view.findViewById(R.id.spinnerMonth);

        ArrayAdapter<MonthDate> adapter = new ArrayAdapter<MonthDate>(getActivity(), android.R.layout.simple_spinner_item, monthsRegisteredList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapter);
        spinnerMonth.setOnItemSelectedListener(this);
    }

    private void fillExpenseTypeChart(List<Expense_Type> expenses) {

        expenseTypeChart = getView().findViewById(R.id.expensesMonthTypeChart);

        expenseTypeChart = ChartAuxiliar.chartPersonalization(expenseTypeChart);

        expenseTypeChart.setCenterText(getString(R.string.typeChartTitle));
        expenseTypeChart.setCenterTextSize(20);

        DecimalFormat df = new DecimalFormat("0.00");

        ArrayList<PieEntry> yValues = new ArrayList<>();

        expenses = MonthHandler.adaptLanguage(getActivity(),expenses);

        for (Expense_Type expense : expenses) {
            yValues.add(new PieEntry((int) expense.getExpense(), MonthHandler.capitalize(expense.getExpenseType())));
        }

        PieDataSet dataSetExpenseType = new PieDataSet(yValues, "");

        //Config dataset
        dataSetExpenseType = ChartAuxiliar.setPieDataSetConfType(dataSetExpenseType);

        PieData data = new PieData(dataSetExpenseType);

        //Config data
        data = ChartAuxiliar.setDataConf(data,expenseTypeChart);

        //Modify legend
        expenseTypeChart = ChartAuxiliar.modifyLegend(expenseTypeChart);

        expenseTypeChart.setData(data);

        expenseTypeChart.invalidate(); // refreshes chart

    }

    private void fillExpensePaymentChart(List<Expense_Payment> expenses) {

        expensePaymentChart = getView().findViewById(R.id.expensesMonthPaymentChart);

        expensePaymentChart = ChartAuxiliar.chartPersonalization(expensePaymentChart);
        expensePaymentChart.setCenterText(getString(R.string.paymentChartTitle));
        expensePaymentChart.setCenterTextSize(20);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        for (Expense_Payment expense : expenses) {
            String payment = getString(R.string.card_payment);
            if (expense.isPaymentWithCash())
                payment = getString(R.string.cash_payment);
            yValues.add(new PieEntry((int) expense.getExpense(), payment));
        }

        PieDataSet dataSetExpenseType = new PieDataSet(yValues, "");

        //Config dataset
        dataSetExpenseType = ChartAuxiliar.setPieDataSetConfPaym(dataSetExpenseType);

        PieData data = new PieData(dataSetExpenseType);

        //Config data
        data = ChartAuxiliar.setDataConf(data,expensePaymentChart);

        //Modify legend
        expensePaymentChart = ChartAuxiliar.modifyLegend(expensePaymentChart);

        expensePaymentChart.setData(data);

        expensePaymentChart.invalidate(); // refreshes chart

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!firstCallSpinner){
            String text = parent.getItemAtPosition(position).toString();
            String[] month_year = text.split(" - ");

            int month = MonthHandler.extractMonth(getActivity(),month_year[0]);
            int year = Integer.parseInt(month_year[1]);

            titleLineChart.setText(MonthHandler.capitalize(text));

            dayExpenses = expenseViewModel.getDayExpenses(month, year);
            fillLineChart(dayExpenses);

            totalText.setText(String.format("%.2f",totalExpense(dayExpenses)) + " " + getString(R.string.badge));

            expensesTypeList = expenseViewModel.getSumTypeExpenses(month, year);
            fillExpenseTypeChart(expensesTypeList);
            fillBreakdownTypeExpenses();

            expensesPaymentList = expenseViewModel.getSumPaymentExpenses(month, year);
            fillExpensePaymentChart(expensesPaymentList);
            fillBreakdownPayment();
        }
        firstCallSpinner=false;


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}