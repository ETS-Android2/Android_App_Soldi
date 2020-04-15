package com.example.soldiapp.ui_routines;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.soldiapp.MainActivity;
import com.example.soldiapp.R;
import com.example.soldiapp.data_handling.ExpenseRepository;
import com.example.soldiapp.utils.MonthHandler;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CompleteRoutineUser_1_Test {

    Activity activity;
    String expensePrice;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void before() {
        activity = mActivityTestRule.getActivity();
        expensePrice = "12";
        ExpenseRepository expenseRepository = new ExpenseRepository(activity.getApplication());
        expenseRepository.deleteAll();

        //Go Home
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));

        appCompatImageButton.perform(click());

        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text),withText(mActivityTestRule.getActivity().getString(R.string.home)),
                        isDisplayed()));
        checkedTextView.check(matches(isDisplayed()));

        checkedTextView.perform(click());
    }

    /**
     * USER PROCESS (BEFORE- GO HOME AND ELIMINATE ALL EXPENSES): INTRODUCE EXPENSE (_inputExpense €_, BILLS, CARD) -> NAV DRAWER ->
     * -> ANALYSIS -> MONTH TAB -> CHECK TOTALS -> YEAR TAB ->
     * -> CHECK TOTALS -> CHECK TYPE EXPENSES CORRESPONDENCE -> CHECK PAYMENT METHODS CORRESPONDENCE
     */
    @Test
    public void completeRoutineUser_1_Test() {

        //Open edit text and introduce input
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.inputExpense),
                        childAtPosition(
                                allOf(withId(R.id.homeLayout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText(expensePrice), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.inputExpense), withText(expensePrice),
                        childAtPosition(
                                allOf(withId(R.id.homeLayout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.startExpenseButton),
                        childAtPosition(
                                allOf(withId(R.id.homeLayout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());


        // --- CHANGE FRAGMENT ---


        //Choose expense type (BILLS)
        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.billsTypeButton),
                        childAtPosition(
                                allOf(withId(R.id.i_type_container),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                1)),
                                7),
                        isDisplayed()));
        appCompatImageView2.perform(click());


        // --- CHANGE FRAGMENT ---


        //Choose payment method
        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.cardPaymentButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        1),
                                1),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.confirmExpenseButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        1),
                                2),
                        isDisplayed()));
        appCompatImageView4.perform(click());


        // --- CHANGE FRAGMENT ---


        //User is back on Home, presses NavDrawer opener
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        //User selects Analysis fragment
        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());


        // --- CHANGE FRAGMENT ---


        //Analysis is displayed, check of total month price matches expense introduced
        ViewInteraction textView = onView(
                allOf(withId(R.id.textTotalExpense), withText(containsString(expensePrice)),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView.check(matches(withText(containsString(expensePrice))));

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        String monthName = MonthHandler.numberMonthToText(month);

        //Check current month is displayed
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.titleLineChartMonth), withText(activity.getString(R.string.currentMonth) +  " " + monthName),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText(activity.getString(R.string.currentMonth) +  " " + monthName)));

        //Scroll
        onView(withId(R.id.MonthlyExpenseBills))
                .perform(scrollTo());

        //Checks expenses types are correct (Bills has the expense introduced and the others nothing)
        ViewInteraction textView6 = onView(
                allOf(withId(R.id.MonthlyExpenseBills),
                        isDisplayed()));
        textView6.check(matches(withText(containsString(expensePrice))));


        //Shopping has 0 euros on expenses
        ViewInteraction textView7 = onView(
                allOf(withId(R.id.MonthlyExpenseShopping), withText(containsString("0€")),
                        isDisplayed()));
        textView7.check(matches(withText(containsString("0€"))));

        //Scroll
        onView(withId(R.id.MonthlyExpenseOther))
                .perform(scrollTo());

        //Other has 0 euros on expenses
        ViewInteraction textView8 = onView(
                allOf(withId(R.id.MonthlyExpenseOther), withText("0€"),
                        isDisplayed()));
        textView8.check(matches(withText("0€")));

        //Leisure has 0 euros on expenses
        ViewInteraction textView9 = onView(
                allOf(withId(R.id.MonthlyExpenseLeisure), withText("0€"),
                        isDisplayed()));
        textView9.check(matches(withText("0€")));

        //Transport has 0 euros on expenses
        ViewInteraction textView10 = onView(
                allOf(withId(R.id.MonthlyExpenseTransport), withText("0€"),
                        isDisplayed()));
        textView10.check(matches(withText("0€")));

        //Supermarket has 0 euros on expenses
        ViewInteraction textView11 = onView(
                allOf(withId(R.id.MonthlyExpenseSupermarket), withText("0€"),
                        isDisplayed()));
        textView11.check(matches(withText("0€")));

        //Scroll
        onView(withId(R.id.cardMonthLegend))
                .perform(scrollTo());

        //Checks payment method is also correct (Card payment with price of expense previously introduced)
        ViewInteraction textView13 = onView(
                allOf(withId(R.id.MonthlyExpenseCard), withText(containsString(expensePrice)),
                        isDisplayed()));
        textView13.check(matches(withText(containsString(expensePrice))));

        //Cash has 0 euros on expenses
        ViewInteraction textView14 = onView(
                allOf(withId(R.id.MonthlyExpenseCash), withText("0€"),
                        isDisplayed()));
        textView14.check(matches(withText("0€")));


        // --- CHANGE FRAGMENT ---


        //Changes to Year tab
        ViewInteraction tabView = onView(
                allOf(withContentDescription(activity.getString(R.string.year)),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.titleYearTab), withText(activity.getString(R.string.yearTitle)),
                        isDisplayed()));
        textView3.check(matches(withText(activity.getString(R.string.yearTitle))));

        //Checks total price equals the expense introduced
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textTotalExpenseYear), withText(containsString(expensePrice)),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView4.check(matches(withText(containsString(expensePrice))));

        //Checks current year is correct
        ViewInteraction textView5 = onView(
                allOf(withId(R.id.titleLineChartYear), withText(activity.getString(R.string.currentYear)+ " " + year),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText(activity.getString(R.string.currentYear)+ " " + year)));


        // --- CHANGE FRAGMENT ---


        //Changes to Month tab again
        ViewInteraction tabView1 = onView(
                allOf(withContentDescription(activity.getString(R.string.month)),
                        isDisplayed()));
        tabView1.perform(click());



    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
