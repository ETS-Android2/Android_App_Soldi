package com.example.soldiapp.auxiliar;

import androidx.room.Entity;
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

    //private Date date;


    public Expense(){
    }

    public Expense(double expense, String expenseType, boolean paymentWithCash) {
        this.expense = expense;
        this.expenseType = expenseType;
        this.paymentWithCash = paymentWithCash;
        //this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getExpense() {
        return expense;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public boolean isPaymentWithCash() {
        return paymentWithCash;
    }

    /*public Date getDate() {
        return date;
    }*/

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public void setPaymentWithCash(boolean paymentWithCash) {
        this.paymentWithCash = paymentWithCash;
    }

    /*public void setDate(Date date) {
        this.date = date;
    }*/
}
