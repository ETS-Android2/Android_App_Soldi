package com.project.soldiapp.base_ui_tests;


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
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavigationDrawerTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    Activity activity;

    @Before
    public void reachNavDrawer(){
        activity = mActivityTestRule.getActivity();
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }

    @Test
    public void testNavHeader(){
        ViewInteraction textView = onView(
                allOf(withId(R.id.textView2), withText(activity.getString(R.string.app_name)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.app_name))));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.app_icon),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    @Test
    public void testMenuItems(){
        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text),isChecked(),withText(mActivityTestRule.getActivity().getString(R.string.home)),
                        isDisplayed()));
        checkedTextView.check(matches(isDisplayed()));

        ViewInteraction checkedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text),isNotChecked(),withText(mActivityTestRule.getActivity().getString(R.string.analysis)),
                        isDisplayed()));
        checkedTextView2.check(matches(isDisplayed()));

        ViewInteraction checkedTextView3 = onView(
                allOf(withId(R.id.design_menu_item_text),isNotChecked(),withText(mActivityTestRule.getActivity().getString(R.string.settings)),
                        isDisplayed()));
        checkedTextView3.check(matches(isDisplayed()));

        ViewInteraction checkedTextView4 = onView(
                allOf(withId(R.id.design_menu_item_text),isNotChecked(),withText(mActivityTestRule.getActivity().getString(R.string.share)),
                        isDisplayed()));
        checkedTextView4.check(matches(isDisplayed()));
    }



}
