package com.example.soldiapp.navigation_component;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;

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
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompleteExpenseTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    Activity activity;

    @Test
    public void fullNavigationExpenseCompleteTest() {

        activity = mActivityTestRule.getActivity();


        ViewInteraction startButton = onView(
                allOf(withId(R.id.startExpenseButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        1),
                                1),
                        isDisplayed()));

        //Try to advance without input
        startButton.perform(click());

        //Introduce invalid input
        ViewInteraction editText = onView(
                allOf(withId(R.id.inputExpense),
                        childAtPosition(
                                allOf(withId(R.id.homeLayout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                1)),
                                2),
                        isDisplayed()));
        editText.perform(replaceText("9999999"), closeSoftKeyboard());

        startButton.perform(click());

        //Check no advance
        editText.check(matches(withText("")));

        ViewInteraction textView = onView(
                allOf(withId(R.id.addExpense), withText(activity.getString(R.string.quantity_expense_title)),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.quantity_expense_title))));

        //Introduce valid input (23) and accept

        editText.perform(replaceText("23"), closeSoftKeyboard());

        editText.perform(pressImeActionButton());

        editText.check(matches(withText("23")));

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.startExpenseButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        1),
                                1),
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

        //Confirm expense
        ViewInteraction appCompatImageView5 = onView(
                allOf(withId(R.id.confirmExpenseButton),
                        isDisplayed()));
        appCompatImageView5.perform(click());


        // --- CHANGE OF FRAGMENT ---


        //Check home fragment again!
        ViewInteraction textView5 = onView(
                allOf(withId(R.id.addExpense), withText(activity.getString(R.string.quantity_expense_title)),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText(activity.getString(R.string.quantity_expense_title))));

        //Check edit text is empty
        ViewInteraction editText1 = onView(
                allOf(withId(R.id.inputExpense),withText(""),
                        childAtPosition(
                                allOf(withId(R.id.homeLayout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                1)),
                                2),
                        isDisplayed()));
        editText1.check(matches(withText("")));
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
