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
import static org.hamcrest.Matchers.containsString;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class III_ExpenseConfirmFragmentTest {

    int inputPassed = 1;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    
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

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.cashPaymentButton),
                        isDisplayed()));
        appCompatImageView3.perform(click());

    }

    @Test
    public void testBackButton(){
        ViewInteraction imageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.backButton)),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    @Test
    public void testToolbarTitle(){
        ViewInteraction textView = onView(
                allOf(withText(activity.getString(R.string.confirmExpense)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.confirmExpense))));
    }

    @Test
    public void testCancelButton(){
        ViewInteraction imageView = onView(
                allOf(withId(R.id.cancelExpenseButton),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    @Test
    public void testFragmentTitle(){
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textView), withText(activity.getString(R.string.confirm_expense_title)),
                        isDisplayed()));
        textView2.check(matches(withText(activity.getString(R.string.confirm_expense_title))));
    }

    @Test
    public void testTypeConfirm(){
        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.imageConfirmExpenseType),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));
    }

    @Test
    public void testArrowPair(){
        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.firstArrowConfirm),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.secondArrowConfirm),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));
    }

    @Test
    public void testExpenseText(){
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textConfirmMoney), withText(containsString("€")),
                        isDisplayed()));
        textView3.check(matches(withText(containsString("€"))));
    }

    @Test
    public void testPaymentMethodConfirm(){
        ViewInteraction imageView5 = onView(
                allOf(withId(R.id.imageConfirmPaymentMethod),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));
    }

    @Test
    public void testConfirmButton(){

        ViewInteraction imageView6 = onView(
                allOf(withId(R.id.confirmExpenseButton),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));
    }


}
