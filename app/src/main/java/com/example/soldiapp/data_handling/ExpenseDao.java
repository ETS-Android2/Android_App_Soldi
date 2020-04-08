package com.example.soldiapp.data_handling;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.soldiapp.auxiliar.DayExpense;
import com.example.soldiapp.auxiliar.Expense;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.Expense_Type;
import com.example.soldiapp.auxiliar.MonthDate;
import com.example.soldiapp.auxiliar.MonthExpense;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(Expense expense);

    @Query("SELECT * FROM expense_table ORDER BY id DESC")
    List<Expense> getAllExpenses();


    @Query("SELECT SUM(expense) as expense,expenseType FROM expense_table WHERE month=:month AND year=:year GROUP BY expenseType")
    List<Expense_Type> getSumTypeExpenses(int month,int year);

    @Query("SELECT SUM(expense) as expense,expenseType FROM expense_table WHERE year=:year GROUP BY expenseType")
    List<Expense_Type> getSumTypeExpenses(int year);


    @Query("SELECT SUM(expense) as expense,paymentWithCash FROM expense_table WHERE month=:month AND year=:year GROUP BY paymentWithCash")
    List<Expense_Payment> getSumPaymentExpenses(int month, int year);

    @Query("SELECT SUM(expense) as expense,paymentWithCash FROM expense_table WHERE year=:year GROUP BY paymentWithCash")
    List<Expense_Payment> getSumPaymentExpenses(int year);


    @Query("SELECT DISTINCT month,year FROM expense_table ORDER BY year,month DESC ")
    List<MonthDate> getMonthsRegistered();

    @Query("SELECT DISTINCT year FROM expense_table ORDER BY year DESC")
    List<Integer> getYearsRegistered();

    @Query("SELECT SUM(expense) AS expense, day FROM expense_table WHERE month=:month AND year=:year GROUP BY day; ")
    List<DayExpense> getDayExpenses(int month, int year);

    @Query("SELECT SUM(expense) AS expense, month FROM expense_table WHERE year=:year GROUP BY month; ")
    List<MonthExpense> getMonthExpenses(int year);





}
