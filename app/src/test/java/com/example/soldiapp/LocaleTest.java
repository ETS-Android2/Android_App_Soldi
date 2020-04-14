package com.example.soldiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.soldiapp.fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.startActivity;
import static com.example.soldiapp.fragments.SettingsFragment.APP_PREFERENCES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
public class LocaleTest {

    MainActivity activity;
    ActivityController<MainActivity> activityController;

    SettingsFragment settingsFragment;

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

    //English as default language for the application
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
    public void testLocaleChangeSpanish() {
        changeLanguage("es");

        //Check locale validity
        Locale locale = activity.getBaseContext().getResources().getConfiguration().locale;

        assertEquals(locale, new Locale("es"));

        assertEquals(activity.getApplicationContext().getString(R.string.quantity_expense_title), "¿Cuánto?");
        assertEquals(activity.getApplicationContext().getString(R.string.home), "Inicio");
        assertEquals(activity.getApplicationContext().getString(R.string.analysis), "Análisis");
        assertEquals(activity.getApplicationContext().getString(R.string.settings), "Ajustes");
        assertEquals(activity.getApplicationContext().getString(R.string.share), "Compartir");

    }

    @Test
    public void testLocaleChangeItalian() {
        changeLanguage("it");

        //Check locale validity
        Locale locale = activity.getBaseContext().getResources().getConfiguration().locale;

        assertEquals(locale, new Locale("it"));

        assertEquals(activity.getApplicationContext().getString(R.string.quantity_expense_title), "Quanto?");
        assertEquals(activity.getApplicationContext().getString(R.string.home), "Inizio");
        assertEquals(activity.getApplicationContext().getString(R.string.analysis), "Analisi");
        assertEquals(activity.getApplicationContext().getString(R.string.settings), "Impostazioni");
        assertEquals(activity.getApplicationContext().getString(R.string.share), "Condividere");

    }

    @Test
    public void testLocaleChangeEnglish() {
        changeLanguage("en");

        //Check locale validity
        Locale locale = activity.getBaseContext().getResources().getConfiguration().locale;

        assertEquals(locale, new Locale("en"));

        assertEquals(activity.getApplicationContext().getString(R.string.quantity_expense_title), "How much?");
        assertEquals(activity.getApplicationContext().getString(R.string.home), "Home");
        assertEquals(activity.getApplicationContext().getString(R.string.analysis), "Analysis");
        assertEquals(activity.getApplicationContext().getString(R.string.settings), "Settings");
        assertEquals(activity.getApplicationContext().getString(R.string.share), "Share");
    }

    private void changeLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(configuration, activity.getBaseContext().
                getResources().getDisplayMetrics());

        SharedPreferences settings = activity.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString("locale", language);
        prefEditor.commit();

        //It is required to recreate the activity to reflect the change in UI.
        Intent intent = activity.getIntent();
        activity.finish();
        activityController.start().resume();
    }

    @After
    public void tearDown() {
        activityController
                .pause()
                .stop()
                .destroy();
    }
}