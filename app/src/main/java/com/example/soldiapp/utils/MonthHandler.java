package com.example.soldiapp.utils;

import android.app.Activity;
import android.content.res.Resources;

import com.example.soldiapp.R;
import com.example.soldiapp.auxiliar.Expense_Type;

import java.text.DateFormatSymbols;
import java.util.List;

public class MonthHandler {

    public static int extractMonth(Activity activity, String s) {
        s = capitalize(s);
        if(s.equals(activity.getString(R.string.january)))
            return 1;
        else if(s.equals(activity.getString(R.string.february)))
            return 2;
        else if(s.equals(activity.getString(R.string.march)))
            return 3;
        else if(s.equals(activity.getString(R.string.april)))
            return 4;
        else if(s.equals(activity.getString(R.string.may)))
            return 5;
        else if(s.equals(activity.getString(R.string.june)))
            return 6;
        else if(s.equals(activity.getString(R.string.july)))
            return 7;
        else if(s.equals(activity.getString(R.string.august)))
            return 8;
        else if(s.equals(activity.getString(R.string.september)))
            return 9;
        else if(s.equals(activity.getString(R.string.october)))
            return 10;
        else if(s.equals(Resources.getSystem().getString(R.string.november)))
            return 11;
        else
            return 12;

    }

    public static String numberMonthToText(int n){
        return new DateFormatSymbols().getMonths()[n-1];
    }

    public static List<Expense_Type> adaptLanguage(Activity activity, List<Expense_Type> expenses) {
        for(Expense_Type expense : expenses){
            if(expense.getExpenseType().equals("supermarket"))
                expense.setExpenseType(activity.getString(R.string.supermarket_type));
            else if(expense.getExpenseType().equals("transport"))
                expense.setExpenseType(activity.getString(R.string.transport_type));
            else if(expense.getExpenseType().equals("leisure"))
                expense.setExpenseType(activity.getString(R.string.leisure_type));
            else if(expense.getExpenseType().equals("shopping"))
                expense.setExpenseType(activity.getString(R.string.shopping_type));
            else if(expense.getExpenseType().equals("bills"))
                expense.setExpenseType(activity.getString(R.string.bills_type));
            else
                expense.setExpenseType(activity.getString(R.string.other_type));

        }
        return expenses;
    }

    public static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
