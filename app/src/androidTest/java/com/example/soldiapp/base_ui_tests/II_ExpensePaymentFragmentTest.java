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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class II_ExpensePaymentFragmentTest {

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
        appCompatEditText.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.inputExpense), withText("1"),
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
    }

    @Test
    public void backButtonTest() {
        ViewInteraction imageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.backButton)),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    @Test
    public void toolbarTitleTest() {
        ViewInteraction textView = onView(
                allOf(withText(activity.getString(R.string.addingExpense)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.addingExpense))));
    }

    @Test
    public void fragmentTitleTest() {
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textPaymentMethod), withText(activity.getString(R.string.payment_method_title)),
                        isDisplayed()));
        textView2.check(matches(withText(activity.getString(R.string.payment_method_title))));
    }

    @Test
    public void cashPairTest() {

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
    public void orTextViewTest() {
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textOrPayment), withText(activity.getString(R.string.or)),
                        isDisplayed()));
        textView4.check(matches(withText(activity.getString(R.string.or))));
    }

    @Test
    public void cardPairTest() {
        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.cardPaymentButton),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textCardPayment), withText(activity.getString(R.string.card_payment)),
                        isDisplayed()));
        textView5.check(matches(withText(activity.getString(R.string.card_payment))));
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
