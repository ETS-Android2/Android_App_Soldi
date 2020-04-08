package com.example.soldiapp.data_handling;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.soldiapp.auxiliar.Expense;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.Expense_Type;
import com.example.soldiapp.auxiliar.MonthDate;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(Expense expense);

    @Query("SELECT * FROM expense_table ORDER BY id DESC")
    List<Expense> getAllExpenses();

    @Query("SELECT SUM(expense) as expense,expenseType FROM expense_table GROUP BY expenseType")
    List<Expense_Type> getSumTypeExpenses();

    @Query("SELECT SUM(expense) as expense,paymentWithCash FROM expense_table GROUP BY paymentWithCash")
    List<Expense_Payment> getSumPaymentExpenses();

    @Query("SELECT DISTINCT month,year FROM expense_table")
    List<MonthDate> getMonthsRegistered();

    @Query("SELECT DISTINCT year FROM expense_table")
    List<Integer> getYearsRegistered();




}
