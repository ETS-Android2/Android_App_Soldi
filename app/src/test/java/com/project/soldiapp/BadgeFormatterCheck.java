package com.project.soldiapp;

import com.project.soldiapp.utils.BadgeFormatter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BadgeFormatterCheck {
    @Test
    public void testBadgeFormatter() {
        BadgeFormatter formatter = new BadgeFormatter();

        float number = 0;
        assertEquals("",formatter.getFormattedValue(number));

        number = (float) 0.0;
        assertEquals("",formatter.getFormattedValue(number));

        number = (float) 0.00;
        assertEquals("",formatter.getFormattedValue(number));

        number = 12;
        assertEquals("12€",formatter.getFormattedValue(number));

        number = (float) 12.3;
        assertEquals("12.3€",formatter.getFormattedValue(number));

        number = (float) 12.39;
        assertEquals("12.39€",formatter.getFormattedValue(number));

        number = 100000;
        assertEquals("100000€",formatter.getFormattedValue(number));

    }

}