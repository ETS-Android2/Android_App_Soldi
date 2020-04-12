package com.example.soldiapp;

import com.example.soldiapp.fragments.HomeFragment;
import com.example.soldiapp.utils.MonthHandler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CapitalizeCheck {
    @Test
    public void checks() {
        MonthHandler handler = new MonthHandler();

        String text = "juan";
        assertEquals("Juan",handler.capitalize(text));

        text = "Juan";
        assertEquals("Juan",handler.capitalize(text));

        text = "JUAN";
        assertEquals("Juan",handler.capitalize(text));

        text = "jUAN";
        assertEquals("Juan",handler.capitalize(text));

        text = "JuAN";
        assertEquals("Juan",handler.capitalize(text));

        text = "J";
        assertEquals("J",handler.capitalize(text));

        text = "j";
        assertEquals("J",handler.capitalize(text));


    }

}