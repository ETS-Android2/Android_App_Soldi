package com.example.soldiapp.data_handling;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.soldiapp.auxiliar.Expense;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private ExpenseRepository repository;

    private LiveData<List<Expense>> allExpense;

    public ExpenseViewModel(@NonNull Application application){
        super(application);
        repository = new ExpenseRepository(application);
        allExpense = repository.getAllExpenses();
    }

    public void insert(Expense expense){
        repository.insert(expense);
    }

    public LiveData<List<Expense>> getAllExpense(){
        return allExpense;
    }
}
