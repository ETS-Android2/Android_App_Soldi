package com.example.soldiapp.utils;

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
        return (int)value + "€";
    }

}