package com.example.soldiapp.auxiliar;

public class MonthExpense {
    private int month;
    private int expense;

    public MonthExpense(int month, int expense) {
        this.month = month;
        this.expense = expense;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

}
