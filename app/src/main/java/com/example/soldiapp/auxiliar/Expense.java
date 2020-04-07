package com.example.soldiapp.auxiliar;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "expense_table")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    private int id; //PK

    private double expense;

    private String expenseType;

    private boolean paymentWithCash;

    private int day;
    private int month;
    private int year;


    public Expense(){
    }

    @Ignore
    public Expense( double expense, String expenseType, boolean paymentWithCash, int day, int month, int year) {
        this.expense = expense;
        this.expenseType = expenseType;
        this.paymentWithCash = paymentWithCash;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Ignore
    public Expense(double expense, String expenseType, boolean paymentWithCash) {
        this.expense = expense;
        this.expenseType = expenseType;
        this.paymentWithCash = paymentWithCash;
        //this.date = date;
    }
    @Ignore
    public Expense(double expense, String expenseType) {
        this.expense = expense;
        this.expenseType = expenseType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public boolean isPaymentWithCash() {
        return paymentWithCash;
    }

    public void setPaymentWithCash(boolean paymentWithCash) {
        this.paymentWithCash = paymentWithCash;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
