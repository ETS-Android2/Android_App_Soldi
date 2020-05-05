package com.project.soldiapp.base_ui_tests;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.project.soldiapp.MainActivity;
import com.project.soldiapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class II_ExpensePaymentFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    int inputPassed = 1;
    Activity activity;

    @Before
    public void reachFragment() {
        activity = mActivityTestRule.getActivity();

        ViewInteraction appCompatEditText = onView(
                Matchers.allOf(ViewMatchers.withId(R.id.inputExpense),
                        isDisplayed()));
        appCompatEditText.perform(replaceText(String.valueOf(inputPassed)), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.inputExpense), withText(String.valueOf(inputPassed)),
                        isDisplayed()));
        appCompatEditText2.check(matches(withText(String.valueOf(inputPassed))));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.startExpenseButton),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.supermarketTypeButton),
                        isDisplayed()));
        appCompatImageView2.perform(click());
    }

    @Test
    public void backButton() {
        ViewInteraction imageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.backButton)),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    @Test
    public void testToolbarTitle() {
        ViewInteraction textView = onView(
                allOf(withText(activity.getString(R.string.addingExpense)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.addingExpense))));
    }

    @Test
    public void testFragmentTitle() {
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textPaymentMethod), withText(activity.getString(R.string.payment_method_title)),
                        isDisplayed()));
        textView2.check(matches(withText(activity.getString(R.string.payment_method_title))));
    }

    @Test
    public void testCashPair() {

        ViewInteraction imageView = onView(
                allOf(withId(R.id.cashPaymentButton),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textCashPayment), withText(activity.getString(R.string.cash_payment)),
                        isDisplayed()));
        textView3.check(matches(withText(activity.getString(R.string.cash_payment))));
    }

    @Test
    public void testOrTextView() {
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textOrPayment), withText(activity.getString(R.string.or)),
                        isDisplayed()));
        textView4.check(matches(withText(activity.getString(R.string.or))));
    }

    @Test
    public void testCardPair() {
        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.cardPaymentButton),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textCardPayment), withText(activity.getString(R.string.card_payment)),
                        isDisplayed()));
        textView5.check(matches(withText(activity.getString(R.string.card_payment))));
    }


}
