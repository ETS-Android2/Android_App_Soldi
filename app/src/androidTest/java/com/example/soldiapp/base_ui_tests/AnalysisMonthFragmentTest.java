package com.example.soldiapp.base_ui_tests;


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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeUp;
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
public class AnalysisMonthFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    Activity activity;

    @Before
    public void reachFragment(){
        activity = mActivityTestRule.getActivity();

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

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
    }

    @Test
    public void navDrawerTest(){
        ViewInteraction imageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    @Test
    public void toolbarTitleTest(){
        ViewInteraction textView = onView(
                allOf(withText(activity.getString(R.string.analysis)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.analysis))));
    }

    @Test
    public void tabMonthTest(){
        ViewInteraction actionBar$Tab = onView(
                allOf(withContentDescription(activity.getString(R.string.month)),
                        isDisplayed()));
        actionBar$Tab.check(matches(isDisplayed()));
    }

    @Test
    public void tabYearTest(){
        ViewInteraction actionBar$Tab2 = onView(
                allOf(withContentDescription(activity.getString(R.string.year)),
                        isDisplayed()));
        actionBar$Tab2.check(matches(isDisplayed()));
    }

    @Test
    public void titleMonthExpensesTest(){
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.titleMonthTab), withText(activity.getString(R.string.monthTitle)),
                        isDisplayed()));
        textView2.check(matches(withText(activity.getString(R.string.monthTitle))));
    }

    @Test
    public void spinnerMonthTest(){
        ViewInteraction spinner = onView(
                allOf(withId(R.id.spinnerMonth),
                        isDisplayed()));
        spinner.check(matches(isDisplayed()));
    }

    @Test
    public void textTotalExpensesTest(){
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.totalText), withText(activity.getString(R.string.total)),
                        isDisplayed()));
        textView3.check(matches(withText(activity.getString(R.string.total))));
    }

    @Test
    public void totalExpenseTextTest(){

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textTotalExpense), withText(containsString("€")),
                        isDisplayed()));
        textView4.check(matches(withText(containsString("€"))));
    }

    @Test
    public void lineChartTitleTest(){
        ViewInteraction textView5 = onView(
                allOf(withId(R.id.titleLineChartMonth),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));
    }

    @Test
    public void lineChartTest(){
        ViewInteraction viewGroup2 = onView(
                allOf(withId(R.id.lineMonthChart),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));
    }

    @Test
    public void xAxisTitleTest(){
        onView(withId(R.id.xAxisTitleMonth))
                .perform(scrollTo());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.xAxisTitleMonth),withText(activity.getString(R.string.days)),
                        isDisplayed()));
        textView6.check(matches(withText(activity.getString(R.string.days))));

    }

    @Test
    public void titleTypeChartTest(){
        onView(withId(R.id.titleTypeChartMonth))
                .perform(scrollTo());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.titleTypeChartMonth), withText(activity.getString(R.string.typeChartTitle)),
                        isDisplayed()));
        textView7.check(matches(withText(activity.getString(R.string.typeChartTitle))));
    }

    @Test
    public void pieChartTypeTest(){
        onView(withId(R.id.expensesMonthTypeChart))
                .perform(scrollTo());

        ViewInteraction viewGroup4 = onView(
                allOf(withId(R.id.expensesMonthTypeChart),
                        isDisplayed()));
        viewGroup4.check(matches(isDisplayed()));
    }

    @Test
    public void legendSupermarketPairTest(){
        onView(withId(R.id.supermarketMonthLegend))
                .perform(scrollTo());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.supermarketMonthLegend),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.MonthlyExpenseSupermarket), withText(containsString("€")),
                        isDisplayed()));
        textView8.check(matches(withText(containsString("€"))));
    }

    @Test
    public void legendShoppingPairTest(){

        onView(withId(R.id.shoppingMonthLegend))
                .perform(scrollTo());

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.shoppingMonthLegend),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.MonthlyExpenseShopping), withText(containsString("€")),
                        isDisplayed()));
        textView9.check(matches(withText(containsString("€"))));
    }

    @Test
    public void legendTransportPairTest(){

        onView(withId(R.id.transportMonthLegend))
                .perform(scrollTo());

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.transportMonthLegend),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.MonthlyExpenseTransport), withText(containsString("€")),
                        isDisplayed()));
        textView10.check(matches(withText(containsString("€"))));
    }

    @Test
    public void legendBillsPairTest(){
        onView(withId(R.id.billsMonthLegend))
                .perform(scrollTo());

        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.billsMonthLegend),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.MonthlyExpenseBills), withText(containsString("€")),
                        isDisplayed()));
        textView11.check(matches(withText(containsString("€"))));
    }

    @Test
    public void legendLeisurePairTest(){

        onView(withId(R.id.leisureMonthLegend))
                .perform(scrollTo());

        ViewInteraction imageView5 = onView(
                allOf(withId(R.id.leisureMonthLegend),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.MonthlyExpenseLeisure), withText(containsString("€")),
                        isDisplayed()));
        textView12.check(matches(withText(containsString("€"))));
    }

    @Test
    public void legendOtherPairTest(){
        onView(withId(R.id.otherMonthLegend))
                .perform(scrollTo());

        ViewInteraction imageView6 = onView(
                allOf(withId(R.id.otherMonthLegend),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.MonthlyExpenseOther), withText(containsString("€")),

                        isDisplayed()));
        textView13.check(matches(withText(containsString("€"))));
    }

    @Test
    public void titlePaymentChartTest(){
        onView(withId(R.id.titlePaymentChartMonth))
                .perform(scrollTo());

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.titlePaymentChartMonth), withText(activity.getString(R.string.paymentChartTitle)),
                        isDisplayed()));
        textView14.check(matches(withText(activity.getString(R.string.paymentChartTitle))));
    }

    @Test
    public void pieChartPaymentTest(){
        onView(withId(R.id.expensesMonthPaymentChart))
                .perform(scrollTo());

        ViewInteraction viewGroup6 = onView(
                allOf(withId(R.id.expensesMonthPaymentChart),
                        isDisplayed()));
        viewGroup6.check(matches(isDisplayed()));
    }

    @Test
    public void legendCashPairTest(){
        onView(withId(R.id.cashMonthLegend))
                .perform(scrollTo());

        ViewInteraction imageView8 = onView(
                allOf(withId(R.id.cashMonthLegend),
                        isDisplayed()));
        imageView8.check(matches(isDisplayed()));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.MonthlyExpenseCash), withText(containsString("€")),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.GridLayout.class),
                                        0),
                                1)));
        textView15.check(matches(withText(containsString("€"))));
    }

    @Test
    public void legendCardPairTest(){
        onView(withId(R.id.cardMonthLegend))
                .perform(scrollTo());

        ViewInteraction imageView9 = onView(
                allOf(withId(R.id.cardMonthLegend),
                        isDisplayed()));
        imageView9.check(matches(isDisplayed()));

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.MonthlyExpenseCard), withText(containsString("€")),
                        isDisplayed()));
        textView16.check(matches(withText(containsString("€"))));
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
