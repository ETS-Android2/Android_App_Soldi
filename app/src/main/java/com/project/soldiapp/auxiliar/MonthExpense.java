package com.project.soldiapp.auxiliar;

public class MonthExpense {
    private int month;
    private double expense;

    public MonthExpense(int month, double expense) {
        this.month = month;
        this.expense = expense;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

}
