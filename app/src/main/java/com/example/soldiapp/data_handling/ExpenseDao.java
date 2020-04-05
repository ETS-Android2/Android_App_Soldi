package com.example.soldiapp.data_handling;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.soldiapp.auxiliar.Expense;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(Expense expense);

    @Query("SELECT * FROM expense_table ORDER BY id DESC")
    LiveData<List<Expense>> getAllExpenses(); //As soon as there are changes on List of expenses, the LiveData object will be updated
}
