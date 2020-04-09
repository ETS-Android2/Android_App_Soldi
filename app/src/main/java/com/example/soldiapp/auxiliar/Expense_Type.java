package com.example.soldiapp.auxiliar;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Expense_Type {

    private double expense;

    private String expenseType;

    public Expense_Type() {
    }

    @Ignore
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

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

}
