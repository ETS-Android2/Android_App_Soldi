package com.example.soldiapp.data_handling;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.soldiapp.auxiliar.Expense;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.Expense_Type;
import com.example.soldiapp.auxiliar.MonthDate;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private ExpenseRepository repository;

    private List<Expense> allExpenses;
    private List<Expense_Type> sumTypeExpenses;
    private List<Expense_Payment> sumPaymentExpenses;
    private List<MonthDate> monthsRegistered;
    private List<Integer> yearsRegistered;

    public ExpenseViewModel(@NonNull Application application){
        super(application);
        repository = new ExpenseRepository(application);
        allExpenses = repository.getAllExpenses();
        sumTypeExpenses = repository.getSumTypeExpenses();
        sumPaymentExpenses = repository.getSumPaymentExpenses();
        monthsRegistered = repository.getMonthsRegistered();
        yearsRegistered = repository.getYearsRegistered();

    }

    public void insert(Expense expense){
        repository.insert(expense);
    }

    public List<Expense> getAllExpenses(){
        return allExpenses;
    }

    public List<Expense_Type> getSumTypeExpenses(){
        return sumTypeExpenses;
    }

    public List<Expense_Payment> getSumPaymentExpenses(){
        return sumPaymentExpenses;
    }

    public List<MonthDate> getMonthsRegistered(){
        return monthsRegistered;
    }
    public List<Integer> getYearsRegistered(){
        return yearsRegistered;
    }
}
