package com.example.soldiapp.auxiliar;

import java.text.DateFormatSymbols;

public class MonthDate {
    private int month;
    private int year;

    public MonthDate(int month, int year) {
        this.month = month;
        this.year = year;
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

    public String numberMonthToText(int n){
        return new DateFormatSymbols().getMonths()[month-1];
    }

    @Override
    public String toString() {
        return numberMonthToText(getMonth()) +" - " + getYear() ;
    }
}
