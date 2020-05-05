package com.project.soldiapp.ui_routines;


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

    /**
     * USER PROCESS: NAV DRAWER -> SETTINGS -> SPANISH LANGUAGE -> HOME (AUTOMATICALLY) -> CHECK CHANGE OF LANGUAGE ON TITLE ->
     * -> NAV DRAWER -> SETTINGS -> ITALIAN LANGUAGE -> HOME (AUTOMATICALLY) -> CHECK CHANGE OF LANGUAGE ON TITLE ->
     * -> NAV DRAWER -> SETTINGS -> ENGLISH LANGUAGE -> HOME (AUTOMATICALLY) -> CHECK CHANGE OF LANGUAGE ON TITLE
     */
    @Test
    public void testCompleteRoutineUser_2_() {

        //Opens navigation drawer
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        //Goes to Settings
        ViewInteraction navigationMenuItemView = onView(
                allOf(withText(mActivityTestRule.getActivity().getString(R.string.settings)),
                        isDisplayed()));
        navigationMenuItemView.perform(click());


        //--- CHANGE OF FRAGMENT ---

        //Changes of Language to Spanish
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.spanishLanguage),
                        isDisplayed()));
        appCompatImageView.perform(click());


        //--- CHANGE OF FRAGMENT --- Automatically to home because of Activity restart


        //Check language has changed
        ViewInteraction textView = onView(
                allOf(withId(R.id.addExpense), withText(spanishHomeTitle),
                        isDisplayed()));
        textView.check(matches(withText(spanishHomeTitle)));


        //Opens navigation drawer
        ViewInteraction appCompatImageButton1 = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        isDisplayed()));
        appCompatImageButton1.perform(click());

        //Goes to Settings
        ViewInteraction navigationMenuItemView1 = onView(
                allOf(withText(mActivityTestRule.getActivity().getString(R.string.settings)),
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
                        isDisplayed()));
        textView1.check(matches(withText(italianHomeTitle)));


        //Opens navigation drawer - ENGLISH
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        //Goes to Settings - ENGLISH
        ViewInteraction navigationMenuItemView2 = onView(
                allOf(withText(mActivityTestRule.getActivity().getString(R.string.settings)),
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
                        isDisplayed()));
        textView2.check(matches(withText(englishHomeTitle)));
    }


}
