package com.example.soldiapp.data_handling;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.soldiapp.auxiliar.DayExpense;
import com.example.soldiapp.auxiliar.Expense;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.Expense_Type;
import com.example.soldiapp.auxiliar.MonthDate;
import com.example.soldiapp.auxiliar.MonthExpense;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private ExpenseRepository repository;

    private List<Expense> allExpenses;

    private List<MonthDate> monthsRegistered;
    private List<Integer> yearsRegistered;

    public ExpenseViewModel(@NonNull Application application){
        super(application);
        repository = new ExpenseRepository(application);
        allExpenses = repository.getAllExpenses();
        monthsRegistered = repository.getMonthsRegistered();
        yearsRegistered = repository.getYearsRegistered();

    }

    public void insert(Expense expense){
        repository.insert(expense);
    }

    public List<Expense> getAllExpenses(){
        return allExpenses;
    }

    public List<Expense_Type> getSumTypeExpenses(int month, int year){
        return repository.getSumTypeExpenses(month,year);
    }

    public List<Expense_Type> getSumTypeExpenses( int year){
        return repository.getSumTypeExpenses(year);
    }

    public List<Expense_Payment> getSumPaymentExpenses(int year){
        return repository.getSumPaymentExpenses(year);
    }

    public List<Expense_Payment> getSumPaymentExpenses(int month, int year){
        return repository.getSumPaymentExpenses(month,year);
    }


    public List<MonthDate> getMonthsRegistered(){
        return monthsRegistered;
    }
    public List<Integer> getYearsRegistered(){
        return yearsRegistered;
    }

    public List<DayExpense> getDayExpenses(int month,int year){
        return repository.getDayExpenses(month,year);
    }
    public List<MonthExpense> getMonthExpenses(int month){
        return repository.getMonthExpenses(month);
    }


}
