package com.project.soldiapp.auxiliar;

import androidx.room.Ignore;

public class DayExpense {
    private int day;
    private double expense;

    public DayExpense(){
    }

    @Ignore
    public DayExpense(int day, double expense) {
        this.day = day;
        this.expense = expense;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

}
