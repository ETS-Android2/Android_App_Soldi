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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CompleteRoutineUser_2_Test {

    Activity activity;
    String spanishHomeTitle = "¿Cuánto?";
    String italianHomeTitle = "Quanto?";
    String englishHomeTitle = "How much?";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void before(){
        activity = mActivityTestRule.getActivity();
    }

    @Test
    public void completeRoutineUser_2_Test() {

        //Opens navigation drawer
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

        //Goes to Settings
        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView.perform(click());


        //--- CHANGE OF FRAGMENT ---

        //Changes of Language to Spanish
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.spanishLanguage),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatImageView.perform(click());


        //--- CHANGE OF FRAGMENT --- Automatically to home because of Activity restart


        //Check language has changed
        ViewInteraction textView = onView(
                allOf(withId(R.id.addExpense), withText(spanishHomeTitle),
                        childAtPosition(
                                allOf(withId(R.id.homeLayout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText(spanishHomeTitle)));


        //Opens navigation drawer
        ViewInteraction appCompatImageButton1 = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton1.perform(click());

        //Goes to Settings
        ViewInteraction navigationMenuItemView1 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView1.perform(click());


        //--- CHANGE OF FRAGMENT ---

        //Changes of Language to Italian
        ViewInteraction appCompatImageView1 = onView(
                allOf(withId(R.id.italianLanguage),
                        isDisplayed()));
        appCompatImageView1.perform(click());


        //--- CHANGE OF FRAGMENT --- Automatically to home because of Activity restart


        //Check language has changed
        ViewInteraction textView1 = onView(
                allOf(withId(R.id.addExpense), withText(italianHomeTitle),
                        childAtPosition(
                                allOf(withId(R.id.homeLayout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                0),
                        isDisplayed()));
        textView1.check(matches(withText(italianHomeTitle)));


        //Opens navigation drawer - ENGLISH
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

        //Goes to Settings - ENGLISH
        ViewInteraction navigationMenuItemView2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView2.perform(click());


        //--- CHANGE OF FRAGMENT --- ENGLISH

        //Changes of Language to English - ENGLISH
        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.englishLanguage),
                        isDisplayed()));
        appCompatImageView2.perform(click());


        //--- CHANGE OF FRAGMENT --- Automatically to home because of Activity restart


        //Check language has changed
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.addExpense), withText(englishHomeTitle),
                        childAtPosition(
                                allOf(withId(R.id.homeLayout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText(englishHomeTitle)));
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
