package com.example.soldiapp.ui_routines;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.soldiapp.MainActivity;
import com.example.soldiapp.R;
import com.example.soldiapp.auxiliar.Expense;
import com.example.soldiapp.data_handling.ExpenseRepository;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
public class CompleteRoutineUser_3_Test {

    Activity activity;
    double price1 = 24;
    double price2 = 26;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void before(){
        activity = mActivityTestRule.getActivity();

        ExpenseRepository repository = new ExpenseRepository(activity.getApplication());

        repository.deleteAll();

        repository.insert(new Expense(price1,"supermarket",true,13,4,2020));
        repository.insert(new Expense(price2,"other",true,13,4,2020));

        //Total expenses 50euros
    }

    /**
     * USER PROCESS (BEFORE- DELETE ALL EXPENSES AND INSERT 2 NEW ONES): NAV DRAWER ->
     * -> ANALYSIS -> CHECK TOTAL MATCHES THE SUM OF THE EXPENSES -> NAV DRAWER -> SETTINGS -> DELETE ALL EXPENSES ->
     * -> NAV DRAWER -> ANALYSIS -> CHECK TOTAL NOW IS 0
     */
    @Test
    public void completeRoutineUser_3_Test() {

        //Open nav drawer
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        childAtPosition(
                                Matchers.allOf(ViewMatchers.withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        //Go to Analysis
        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.design_menu_item_text),withText(mActivityTestRule.getActivity().getString(R.string.analysis)),
                        isDisplayed()));
        navigationMenuItemView.perform(click());


        //---CHANGE FRAGMENT---


        //Check total expenses -> Should be sum -> 50 euros with current parameters
        ViewInteraction textView = onView(
                allOf(withId(R.id.textTotalExpense), withText(containsString(String.valueOf(price1+price2))),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        //textView.check(matches(withText(String.valueOf(price1+price2))));
        textView.check(matches(withText(containsString(String.valueOf(price1+price2)))));

        //Open Nav drawer
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        //Go to Settings
        ViewInteraction navigationMenuItemView2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView2.perform(click());


        //---CHANGE FRAGMENT---

        //Delete ALL expenses with button
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.deleteButtonAll), withText(activity.getString(R.string.deleteButtonAll)),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        2),
                                0)));
        appCompatButton.perform(scrollTo(), click());

        //Confirm on dialog
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText(activity.getString(R.string.yes)),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        //Open nav drawer
        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        //Go to Analysis
        ViewInteraction navigationMenuItemView3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView3.perform(click());


        //---CHANGE FRAGMENT---

        //Check total expense is now 0 -> because of remove all
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textTotalExpense), withText("0€"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        textView2.check(matches(withText("0€")));
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
