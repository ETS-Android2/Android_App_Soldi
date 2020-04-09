package com.example.soldiapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.soldiapp.R;
import com.example.soldiapp.adapter.ChartAuxiliar;
import com.example.soldiapp.auxiliar.DayExpense;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.Expense_Type;
import com.example.soldiapp.auxiliar.MonthDate;
import com.example.soldiapp.auxiliar.MonthExpense;
import com.example.soldiapp.data_handling.ExpenseViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;


public class AnalysisYearFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    PieChart expensePaymentChart;
    PieChart expenseTypeChart;

    LineChart lineExpenseChart;

    TextView totalText;
    TextView titleLineChart;

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

        //Title line chart
        titleLineChart = getView().findViewById(R.id.titleLineChartYear);
        titleLineChart.setText(getString(R.string.currentYear) + year);

        //Fill line chart
        monthExpenses = expenseViewModel.getMonthExpenses(year);
        fillLineChart(monthExpenses);

        //Total expense
        totalText = view.findViewById(R.id.textTotalExpenseYear);
        totalText.setText(totalExpense(monthExpenses) + " " + getString(R.string.badge));

        //Fill expense type chart
        expensesTypeList = expenseViewModel.getSumTypeExpenses(year);
        fillExpenseTypeChart(expensesTypeList);
        //Fill breakdown of type expenses
        fillBreakdownTypeExpenses();

        //Fill payment type chart
        expensesPaymentList = expenseViewModel.getSumPaymentExpenses(year);
        fillExpensePaymentChart(expensesPaymentList);
        fillBreakdownPayment();

    }

    private void fillBreakdownPayment() {
        TextView cashText = getView().findViewById(R.id.YearExpenseCash);
        cashText.setText("0"+getString(R.string.badge));

        TextView cardText = getView().findViewById(R.id.YearExpenseCard);
        cardText.setText("0"+getString(R.string.badge));

        for (Expense_Payment exp : expensesPaymentList) {
            if (exp.isPaymentWithCash())
                cashText.setText(exp.getExpense() + getString(R.string.badge));
            else
                cardText.setText(exp.getExpense() + getString(R.string.badge));
        }
    }

    private void fillBreakdownTypeExpenses() {
        TextView supermarketText = getView().findViewById(R.id.YearExpenseSupermarket);
        supermarketText.setText("0"+getString(R.string.badge));

        TextView transportText = getView().findViewById(R.id.YearExpenseTransport);
        transportText.setText("0"+getString(R.string.badge));

        TextView leisureText = getView().findViewById(R.id.YearExpenseLeisure);
        leisureText.setText("0"+getString(R.string.badge));

        TextView shoppingText = getView().findViewById(R.id.YearExpenseShopping);
        shoppingText.setText("0"+getString(R.string.badge));

        TextView billsText = getView().findViewById(R.id.YearExpenseBills);
        billsText.setText("0"+getString(R.string.badge));

        TextView otherText = getView().findViewById(R.id.YearExpenseOther);
        otherText.setText("0"+getString(R.string.badge));

        for (Expense_Type exp : expensesTypeList) {
            if (exp.getExpenseType().equals(getString(R.string.supermarket_type).toLowerCase()))
                supermarketText.setText(exp.getExpense() + getString(R.string.badge));
            else if (exp.getExpenseType().equals(getString(R.string.transport_type).toLowerCase()))
                transportText.setText(exp.getExpense() + getString(R.string.badge));
            else if (exp.getExpenseType().equals(getString(R.string.leisure_type).toLowerCase()))
                leisureText.setText(exp.getExpense() + getString(R.string.badge));
            else if (exp.getExpenseType().equals(getString(R.string.shopping_type).toLowerCase()))
                shoppingText.setText(exp.getExpense() + getString(R.string.badge));
            else if (exp.getExpenseType().equals(getString(R.string.bills_type).toLowerCase()))
                billsText.setText(exp.getExpense() + getString(R.string.badge));
            else if (exp.getExpenseType().equals(getString(R.string.other_type).toLowerCase()))
                otherText.setText(exp.getExpense() + getString(R.string.badge));
        }
    }

    private double totalExpense(List<MonthExpense> monthExpenses) {
        double sum = 0;
        for (MonthExpense expense : monthExpenses) {
            sum += expense.getExpense();
        }
        return sum;
    }

    private void prepareSpinner(View view) {
        Spinner spinnerYear = view.findViewById(R.id.spinnerYear);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_item, yearsRegisteredList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapter);
        spinnerYear.setOnItemSelectedListener(this);
    }

    private void fillLineChart(List<MonthExpense> expenses) {
        lineExpenseChart = (LineChart) getView().findViewById(R.id.lineYearChart);

        lineExpenseChart = ChartAuxiliar.setLineChartConf(lineExpenseChart);

        ArrayList<Entry> values = new ArrayList<>();
        int months = 0;
        for (int i = 1; i < 13; i++) {
            if (months < expenses.size()) {
                if (i == expenses.get(months).getMonth()) { //Add months in which there are expenses
                    values.add(new Entry(i, (int) expenses.get(months).getExpense()));
                    months++;
                } else {
                    if (i == 1 || i == 12)
                        values.add(new Entry(i, 0));
                }
            } else {
                if (i == 1 || i == 12)
                    values.add(new Entry(i, 0));
            }

        }

        LineDataSet dataSet = new LineDataSet(values, "");

        dataSet = ChartAuxiliar.setLineChartDataset(dataSet);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        LineData data = new LineData(dataSets);

        lineExpenseChart.setData(data);

        lineExpenseChart.invalidate(); // refreshes chart
    }




    private void fillExpenseTypeChart(List<Expense_Type> expenses) {

        expenseTypeChart = getView().findViewById(R.id.expensesYearTypeChart);

        expenseTypeChart = ChartAuxiliar.chartPersonalization(expenseTypeChart);

        expenseTypeChart.setCenterText(getString(R.string.typeChartTitle));
        expenseTypeChart.setCenterTextSize(20);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        for (Expense_Type expense : expenses) {
            yValues.add(new PieEntry((int) expense.getExpense(), capitalize(expense.getExpenseType())));
        }

        PieDataSet dataSetExpenseType = new PieDataSet(yValues, "");

        //Config dataset
        dataSetExpenseType = ChartAuxiliar.setPieDataSetConf(dataSetExpenseType);

        PieData data = new PieData(dataSetExpenseType);

        //Config data
        data = ChartAuxiliar.setDataConf(data,expenseTypeChart);

        //Modify legend
        expenseTypeChart = ChartAuxiliar.modifyLegend(expenseTypeChart);

        expenseTypeChart.setData(data);

        expenseTypeChart.invalidate(); // refreshes chart

    }


    private void fillExpensePaymentChart(List<Expense_Payment> expenses) {

        expensePaymentChart = getView().findViewById(R.id.expensesYearPaymentChart);

        expensePaymentChart = ChartAuxiliar.chartPersonalization(expensePaymentChart);
        expensePaymentChart.setCenterText(getString(R.string.paymentChartTitle));
        expensePaymentChart.setCenterTextSize(20);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        for (Expense_Payment expense : expenses) {
            String payment = "Card";
            if (expense.isPaymentWithCash())
                payment = "Cash";
            yValues.add(new PieEntry((int) expense.getExpense(), payment));
        }

        PieDataSet dataSetExpenseType = new PieDataSet(yValues, "");

        //Config dataset
        dataSetExpenseType = ChartAuxiliar.setPieDataSetConf(dataSetExpenseType);

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
        String text = parent.getItemAtPosition(position).toString();

        int year = Integer.parseInt(text);

        titleLineChart.setText(String.valueOf(year));

        monthExpenses = expenseViewModel.getMonthExpenses(year);
        fillLineChart(monthExpenses);

        totalText.setText(totalExpense(monthExpenses) + getString(R.string.badge));

        expensesTypeList = expenseViewModel.getSumTypeExpenses(year);
        fillExpenseTypeChart(expensesTypeList);
        fillBreakdownTypeExpenses();

        expensesPaymentList = expenseViewModel.getSumPaymentExpenses(year);
        fillExpensePaymentChart(expensesPaymentList);
        fillBreakdownPayment();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}