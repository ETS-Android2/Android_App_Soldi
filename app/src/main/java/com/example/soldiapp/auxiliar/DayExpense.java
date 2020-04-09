package com.example.soldiapp.auxiliar;

import androidx.room.Ignore;

import java.text.DateFormatSymbols;

public class DayExpense {
    private int day;
    private int expense;

    public DayExpense(){
    }

    @Ignore
    public DayExpense(int day, int expense) {
        this.day = day;
        this.expense = expense;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

}
