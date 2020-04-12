package com.example.soldiapp;

import com.example.soldiapp.fragments.HomeFragment;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class InputExpensesCheck {
    @Test
    public void invalidChecks() {
        HomeFragment home = new HomeFragment();

        /**
         * No inputs / spaces
         */

        //No input
        String input = "";
        assertFalse(home.checkInputExpense(input));

        //Space
        input =  " " ;
        assertFalse(home.checkInputExpense(input));

        //Space
        input = "  "  ;
        assertFalse(home.checkInputExpense(input));

        //Dot
        input =  "." ;
        assertFalse(home.checkInputExpense(input));

        //Dot
        input =  " ." ;
        assertFalse(home.checkInputExpense(input));

        //Dot
        input =  " . " ;
        assertFalse(home.checkInputExpense(input));

        //Dot
        input =  ". " ;
        assertFalse(home.checkInputExpense(input));

        /**
         * Zeros
         */

        //Wrong Nº
        input =  "0." ;
        assertFalse(home.checkInputExpense(input));

        //Wrong Nº
        input =  ".0" ;
        assertFalse(home.checkInputExpense(input));

        //Invalid Nº
        input =  "0" ;
        assertFalse(home.checkInputExpense(input));

        //Invalid Nº
        input =  "0.00" ;
        assertFalse(home.checkInputExpense(input));



        /**
         * Negative nºs
         */

        input =  "-1" ;
        assertFalse(home.checkInputExpense(input));

        input =  "-0.1" ;
        assertFalse(home.checkInputExpense(input));

        input =  "-999" ;
        assertFalse(home.checkInputExpense(input));

        //Negative and bigger than possible
        input =  "-9999999" ;
        assertFalse(home.checkInputExpense(input));

        /**
         * Decimals
         */

        input =  "0.001" ;
        assertFalse(home.checkInputExpense(input));

        input =  "0.009" ;
        assertFalse(home.checkInputExpense(input));

        input =  "0.0001" ;
        assertFalse(home.checkInputExpense(input));

        input =  "0.00001" ;
        assertFalse(home.checkInputExpense(input));

        input =  "0.111" ;
        assertFalse(home.checkInputExpense(input));

        input =  "0.999" ;
        assertFalse(home.checkInputExpense(input));

        input =  "1.001" ;
        assertFalse(home.checkInputExpense(input));

        input =  "1.999" ;
        assertFalse(home.checkInputExpense(input));

        input =  "1.0001" ;
        assertFalse(home.checkInputExpense(input));

        input =  "13.4501" ;
        assertFalse(home.checkInputExpense(input));


        /**
         * Big integers
         */

        //Million (limit)
        input =  "1000000" ;
        assertFalse(home.checkInputExpense(input));

        input =  "2000000" ;
        assertFalse(home.checkInputExpense(input));

        input =  "9999999999" ;
        assertFalse(home.checkInputExpense(input));

    }

    @Test
    public void validChecks() {
        HomeFragment home = new HomeFragment();

        /**
         * Near zero
         */

        String input =  "0000.01" ;
        assertTrue(home.checkInputExpense(input));

        input =  "000000.01" ;
        assertTrue(home.checkInputExpense(input));

        input =  "0.1" ;
        assertTrue(home.checkInputExpense(input));

        input =  "0.01" ;
        assertTrue(home.checkInputExpense(input));

        input =  "0.99" ;
        assertTrue(home.checkInputExpense(input));

        input =  "00.99" ;
        assertTrue(home.checkInputExpense(input));

        input =  "1" ;
        assertTrue(home.checkInputExpense(input));

        input =  "1.0" ;
        assertTrue(home.checkInputExpense(input));

        input =  "1.00" ;
        assertTrue(home.checkInputExpense(input));

        input =  "1.01" ;
        assertTrue(home.checkInputExpense(input));

        /**
         * Regular values
         */

        input =  "1.5" ;
        assertTrue(home.checkInputExpense(input));

        input =  "10" ;
        assertTrue(home.checkInputExpense(input));

        input =  "13." ;
        assertTrue(home.checkInputExpense(input));

        input =  "13.000" ;
        assertTrue(home.checkInputExpense(input));

        input =  "15.59" ;
        assertTrue(home.checkInputExpense(input));

        input =  "15.00" ;
        assertTrue(home.checkInputExpense(input));

        input =  "150.40" ;
        assertTrue(home.checkInputExpense(input));

        input =  "270.1" ;
        assertTrue(home.checkInputExpense(input));

        input =  "270.99" ;
        assertTrue(home.checkInputExpense(input));

        input =  "10000.34" ;
        assertTrue(home.checkInputExpense(input));

        input =  "10000." ;
        assertTrue(home.checkInputExpense(input));

        /**
         * Big values
         */

        input =  "100000" ;
        assertTrue(home.checkInputExpense(input));

        input =  "500000" ;
        assertTrue(home.checkInputExpense(input));

        input =  "500000.1" ;
        assertTrue(home.checkInputExpense(input));

        input =  "500000.55" ;
        assertTrue(home.checkInputExpense(input));

        input =  "999999" ;
        assertTrue(home.checkInputExpense(input));

        input =  "999999.00" ;
        assertTrue(home.checkInputExpense(input));

        input =  "999999.99" ;
        assertTrue(home.checkInputExpense(input));

    }


}