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
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SettingsFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    Activity activity;

    @Before
    public void reachFragment(){
        activity = mActivityTestRule.getActivity();

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(withText(mActivityTestRule.getActivity().getString(R.string.settings)),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
    }

    @Test
    public void testNavDrawer(){
        ViewInteraction imageButton = onView(
                allOf(withContentDescription(activity.getString(R.string.navigation_drawer_open)),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    @Test
    public void testToolbarTitle(){
        ViewInteraction textView = onView(
                allOf(withText(activity.getString(R.string.settings)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.settings))));
    }

    @Test
    public void testLanguageTitle(){
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.languageTitle), withText(activity.getString(R.string.selectLanguage)),
                        isDisplayed()));
        textView2.check(matches(withText(activity.getString(R.string.selectLanguage))));
    }

    @Test
    public void testSpanishButton(){
        ViewInteraction imageView = onView(
                allOf(withId(R.id.spanishLanguage),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

    }

    @Test
    public void testItalianButton(){
        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.italianLanguage),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));
    }

    @Test
    public void testEnglishButton(){

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.englishLanguage),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));
    }

    @Test
    public void testDeleteExpensesTitle(){
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.deleteTitle), withText(activity.getString(R.string.deleteData)),
                        isDisplayed()));
        textView3.check(matches(withText(activity.getString(R.string.deleteData))));
    }

    @Test
    public void testDeleteSpinner(){
        ViewInteraction spinner = onView(
                allOf(withId(R.id.spinnerDelete),
                        isDisplayed()));
        spinner.check(matches(isDisplayed()));
    }

    @Test
    public void testDeleteButton(){

        ViewInteraction button = onView(
                allOf(withId(R.id.deleteButton),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }

    @Test
    public void testDeleteAllButton(){
        onView(withId(R.id.deleteButtonAll))
                .perform(scrollTo());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.deleteButtonAll),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }


}
