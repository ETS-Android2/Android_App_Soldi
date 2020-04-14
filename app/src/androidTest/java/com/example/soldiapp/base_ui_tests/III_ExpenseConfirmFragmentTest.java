package com.example.soldiapp.base_ui_tests;


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
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        1),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText(String.valueOf(inputPassed)), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.inputExpense), withText(String.valueOf(inputPassed)),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        1),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.startExpenseButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        1),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.supermarketTypeButton),
                        childAtPosition(
                                allOf(withId(R.id.i_type_container),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                1)),
                                11),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.cashPaymentButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        1),
                                0),
                        isDisplayed()));
        appCompatImageView3.perform(click());

    }

    @Test
    public void backButtonTest(){
        ViewInteraction imageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.backButton)),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    @Test
    public void toolbarTitleTest(){
        ViewInteraction textView = onView(
                allOf(withText(activity.getString(R.string.confirmExpense)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.confirmExpense))));
    }

    @Test
    public void cancelButtonTest(){
        ViewInteraction imageView = onView(
                allOf(withId(R.id.cancelExpenseButton),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    @Test
    public void fragmentTitleTest(){
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textView), withText(activity.getString(R.string.confirm_expense_title)),
                        isDisplayed()));
        textView2.check(matches(withText(activity.getString(R.string.confirm_expense_title))));
    }

    @Test
    public void typeConfirmTest(){
        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.imageConfirmExpenseType),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));
    }

    @Test
    public void arrowPairTest(){
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
    public void expenseTextTest(){
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textConfirmMoney), withText(containsString("€")),
                        isDisplayed()));
        textView3.check(matches(withText(containsString("€"))));
    }

    @Test
    public void paymentMethodConfirmTest(){
        ViewInteraction imageView5 = onView(
                allOf(withId(R.id.imageConfirmPaymentMethod),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));
    }

    @Test
    public void confirmButtonTest(){

        ViewInteraction imageView6 = onView(
                allOf(withId(R.id.confirmExpenseButton),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));
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
