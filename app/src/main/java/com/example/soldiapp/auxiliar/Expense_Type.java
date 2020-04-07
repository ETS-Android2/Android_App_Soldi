package com.example.soldiapp.auxiliar;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Expense_Type {

    private double expense;

    private String expenseType;

    //private Date date;

    public Expense_Type() {
    }

    public Expense_Type(double expense, String expenseType) {
        this.expense = expense;
        this.expenseType = expenseType;
    }

    public double getExpense() {
        return expense;
    }

    public String getExpenseType() {
        return expenseType;
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

    /*public void setDate(Date date) {
        this.date = date;
    }*/
}
