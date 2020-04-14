package com.example.soldiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soldiapp.fragments.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;

import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.example.soldiapp.fragments.SettingsFragment.APP_PREFERENCES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ActivityTest {

    MainActivity activity;
    ActivityController<MainActivity> activityController;

    @Before
    public void setUp() {

        activityController = Robolectric.buildActivity(MainActivity.class)
                .create().start().resume();

        activity = activityController.get();


    }

    @Test
    public void checkNotNull() {
        assertNotNull(activity);
    }

    @Test
    public void testLanguageDefaultSettings() {

        //Check default language
        SharedPreferences preferences = activity.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        String defaultLanguage = preferences.getString("locale", "en");
        assertEquals(defaultLanguage, "en");

        //Check locale validity
        Locale locale = activity.getBaseContext().getResources().getConfiguration().locale;
        assertEquals(locale, new Locale("en"));

    }

    @Test
    public void shouldHaveFragments() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        assertTrue(fragments.size() == 2); //NavHost fragment and Home Fragment
    }


    @Test
    public void testToolbarSetup() {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        assertEquals(toolbar.getTitle(), activity.getSupportActionBar().getTitle());
    }

    @Test
    public void testNavigationSetup() {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        assertNotNull(toolbar);

        DrawerLayout drawerLayout = activity.findViewById(R.id.drawer_layout);
        assertNotNull(drawerLayout);

        NavigationView navigationView = activity.findViewById(R.id.nav_view);
        assertNotNull(navigationView);

        NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
        assertNotNull(navController);
    }

    @Test
    public void testNavigationDrawerSetup() {
        NavigationView navigationView = activity.findViewById(R.id.nav_view);
        assertNotNull(navigationView);

        Menu menu = navigationView.getMenu();
        assertEquals(menu.size(), 4);

        //Bundle==null

        //Home section
        assertEquals(String.valueOf(menu.getItem(0).getTitle()), activity.getString(R.string.home));
        assertEquals(menu.getItem(0).isEnabled(), true);
        assertEquals(menu.getItem(0).isChecked(), true);
        //Analysis section
        assertEquals(String.valueOf(menu.getItem(1).getTitle()), activity.getString(R.string.analysis));
        assertEquals(menu.getItem(1).isEnabled(), true);
        assertEquals(menu.getItem(1).isChecked(), false);
        //Settings section
        assertEquals(String.valueOf(menu.getItem(2).getTitle()), activity.getString(R.string.settings));
        assertEquals(menu.getItem(2).isEnabled(), true);
        assertEquals(menu.getItem(2).isChecked(), false);
        //Share section
        assertEquals(String.valueOf(menu.getItem(3).getTitle()), activity.getString(R.string.share));
        assertEquals(menu.getItem(3).isEnabled(), true);
        assertEquals(menu.getItem(3).isChecked(), false);


    }

    @Test
    public void testNavigationDrawer() throws InterruptedException {
        NavigationView navigationView = activity.findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();

        assertEquals(activity.getSupportActionBar().getTitle(), activity.getString(R.string.app_name));

        activity.onNavigationItemSelected(menu.getItem(1));

        assertEquals(activity.getSupportActionBar().getTitle(), activity.getString(R.string.analysis));

       /* try{
            activity.onNavigationItemSelected(menu.getItem(2));
        } catch(RuntimeException e) { //Exception with a thread, not sure why
        }
        assertEquals(activity.getSupportActionBar().getTitle(), activity.getString(R.string.settings));
        */

        activity.onNavigationItemSelected(menu.getItem(3)); //Fragment is not changed, just the share option is displayed

        assertEquals(activity.getSupportActionBar().getTitle(), activity.getString(R.string.analysis));



    }

    @Test
    public void createAndDestroyActivity() {

        assertEquals(activity.getSupportActionBar().getTitle(), activity.getString(R.string.app_name));

        // Destroy the original activity
        activityController
                .pause()
                .stop()
                .destroy();

        // Bring up a new activity
        activityController = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume();
        activity = activityController.get();


        assertNotNull(activity);
        assertEquals(activity.getSupportActionBar().getTitle(), activity.getString(R.string.app_name));
    }

    @After
    public void tearDown() {
        activityController
                .pause()
                .stop()
                .destroy();
    }
}