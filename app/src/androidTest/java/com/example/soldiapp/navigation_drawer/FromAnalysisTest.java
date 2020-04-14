package com.example.soldiapp.navigation_drawer;


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
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FromAnalysisTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    Activity activity;

    //Go Analysis
    @Before
    public void reachNavDrawer(){
        activity = mActivityTestRule.getActivity();

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

        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text),withText(mActivityTestRule.getActivity().getString(R.string.analysis)),
                        isDisplayed()));
        checkedTextView.check(matches(isDisplayed()));

        checkedTextView.perform(click());

        appCompatImageButton.perform(click());
    }

    @Test
    public void analysisToHome(){
        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text),withText(mActivityTestRule.getActivity().getString(R.string.home)),
                        isDisplayed()));
        checkedTextView.check(matches(isDisplayed()));

        checkedTextView.perform(click());

        //Check I am at Home again
        ViewInteraction textView = onView(
                allOf(withText(activity.getString(R.string.app_name)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.app_name))));


    }

    @Test
    public void analysisToAnalysis(){
        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text),withText(mActivityTestRule.getActivity().getString(R.string.analysis)),
                        isDisplayed()));
        checkedTextView.check(matches(isDisplayed()));

        checkedTextView.perform(click());

        //Check I am in Analysis
        ViewInteraction textView = onView(
                allOf(withText(activity.getString(R.string.analysis)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.analysis))));


    }

    @Test
    public void analysisToSettings(){
        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text),withText(mActivityTestRule.getActivity().getString(R.string.settings)),
                        isDisplayed()));
        checkedTextView.check(matches(isDisplayed()));

        checkedTextView.perform(click());

        //Check I am in Settings
        ViewInteraction textView = onView(
                allOf(withText(activity.getString(R.string.settings)),
                        isDisplayed()));
        textView.check(matches(withText(activity.getString(R.string.settings))));


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
