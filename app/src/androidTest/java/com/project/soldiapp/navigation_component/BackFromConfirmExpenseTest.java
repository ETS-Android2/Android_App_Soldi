package com.project.soldiapp.navigation_component;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.project.soldiapp.MainActivity;
import com.project.soldiapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Test going until the end of the navigation and returning with back buttons until home
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class BackFromConfirmExpenseTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    Activity activity;

    @Test
    public void testFullNavigationExpenseComplete() {

        activity = mActivityTestRule.getActivity();


        ViewInteraction startButton = onView(
                allOf(withId(R.id.startExpenseButton),
                        isDisplayed()));

        //Try to advance without input
        startButton.perform(click());

        //Introduce invalid input
        ViewInteraction editText = onView(
                allOf(withId(R.id.inputExpense),
                        isDisplayed()));
        editText.perform(replaceText("9999999"), closeSoftKeyboard());

        startButton.perform(click());

        //Check no advance
        editText.check(matches(withText("")));

        ViewInteraction textView = onView(
                allOf(withId(R.id.addExpense), withText(activity.getString(R.string.quantity_expense_title)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.quantity_expense_title))));

        //Introduce valid input (23) and accept

        editText.perform(replaceText("23"), closeSoftKeyboard());

        editText.perform(pressImeActionButton());

        editText.check(matches(withText("23")));

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.startExpenseButton),
                        isDisplayed()));
        appCompatImageView2.perform(click());


        // --- CHANGE OF FRAGMENT ---


        //Check fragment change (by checking title)
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textExpenseType), withText(activity.getString(R.string.expense_type_title)),
                        isDisplayed()));
        textView2.check(matches(withText(activity.getString(R.string.expense_type_title))));

        //Select one possible expense type option to go on (transport)
        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.transportTypeButton),
                        isDisplayed()));
        appCompatImageView3.perform(click());


        // --- CHANGE OF FRAGMENT ---


        //Check fragment change (by checking title)
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textPaymentMethod), withText(activity.getString(R.string.payment_method_title)),
                        isDisplayed()));
        textView3.check(matches(withText(activity.getString(R.string.payment_method_title))));

        //Select one possible expense type option to go on (cash)
        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.cashPaymentButton),
                        isDisplayed()));
        appCompatImageView4.perform(click());


        // --- CHANGE OF FRAGMENT ---


        //Check fragment change (by checking title)
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textView), withText(activity.getString(R.string.confirm_expense_title)),
                        isDisplayed()));
        textView4.check(matches(withText(activity.getString(R.string.confirm_expense_title))));

        //Go back!
        ViewInteraction backButton1 = onView(
                allOf(withContentDescription(activity.getString(R.string.backButton)),
                        isDisplayed()));
        backButton1.perform(click());


        // --- CHANGE OF FRAGMENT ---


        //Check fragment change (by checking title)
        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textPaymentMethod), withText(activity.getString(R.string.payment_method_title)),
                        isDisplayed()));
        textView5.check(matches(withText(activity.getString(R.string.payment_method_title))));

        //Go back!
        ViewInteraction backButton2 = onView(
                allOf(withContentDescription(activity.getString(R.string.backButton)),
                        isDisplayed()));
        backButton2.perform(click());


        // --- CHANGE OF FRAGMENT ---


        //Check fragment change (by checking title)
        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textExpenseType), withText(activity.getString(R.string.expense_type_title)),
                        isDisplayed()));
        textView6.check(matches(withText(activity.getString(R.string.expense_type_title))));

        ViewInteraction backButton3 = onView(
                allOf(withContentDescription(activity.getString(R.string.backButton)),
                        isDisplayed()));
        backButton3.perform(click());


        // --- CHANGE OF FRAGMENT ---


        //Check home fragment again!
        ViewInteraction textView7 = onView(
                allOf(withId(R.id.addExpense), withText(activity.getString(R.string.quantity_expense_title)),
                        isDisplayed()));
        textView7.check(matches(withText(activity.getString(R.string.quantity_expense_title))));

        //Check navigation drawer does it work

        ViewInteraction hamburgerIcon = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        isDisplayed()));
        hamburgerIcon.check(matches(isDisplayed()));
        hamburgerIcon.perform(click()); //Just to check visually

    }



}
