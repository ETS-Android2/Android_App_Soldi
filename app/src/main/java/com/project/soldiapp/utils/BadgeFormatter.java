package com.project.soldiapp.utils;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class BadgeFormatter extends ValueFormatter {


    public BadgeFormatter() {
    }


    /**
     * Called when drawing any label, used to change numbers into formatted strings.
     *
     * @param value float to be formatted
     * @return formatted string label
     */
    @Override
    public String getFormattedValue(float value) {
        if(value==0)
            return "";

        String number = String.valueOf(value);
        String[] parts = number.split("\\.");
        if(parts.length>1)
            if(parts[1].equals("00") || parts[1].equals("0")){
                int result = (int) value;
                return result + "€";
            }


        return value + "€";
    }

}