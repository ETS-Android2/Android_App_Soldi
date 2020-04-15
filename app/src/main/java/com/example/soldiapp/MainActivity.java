package com.example.soldiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.os.ConfigurationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.soldiapp.fragments.AnalysisFragment;
import com.example.soldiapp.fragments.HomeFragment;
import com.example.soldiapp.fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;


import java.util.Locale;

import static com.example.soldiapp.fragments.SettingsFragment.APP_PREFERENCES;

public class MainActivity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private boolean toolBarNavigationListenerIsRegistered = false;
    NavigationView navigationView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        languageCheck();

        setContentView(R.layout.activity_main);

        //Navigation
        setupNavigation();

        //Navigation drawer and toolbar
        manageNavigationDrawerAndToolbar(savedInstanceState);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    //Setting up navigation
    private void setupNavigation() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);

        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

    }

    public void showBackButton(boolean show) {
        if(show) {
            //You may not want to open the drawer on swipe from the left in this case
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            // Remove hamburger
            drawerToggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if(!toolBarNavigationListenerIsRegistered) {
                drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
                toolBarNavigationListenerIsRegistered = true;
            }

        } else {
            //Regain the power of swipe for the drawer.
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            // Show hamburger
            drawerToggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            drawerToggle.setToolbarNavigationClickListener(null);
            toolBarNavigationListenerIsRegistered = false;
        }
    }

    public NavigationView setupNavigationDrawerAndToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        drawer = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Class that handles the toggle on the navigation drawer and the "hamburger" icon turn.
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);

        drawerToggle.syncState();
        return navigationView;
    }

    private void manageNavigationDrawerAndToolbar(Bundle savedInstanceState) {

        navigationView = setupNavigationDrawerAndToolbar();

        if (savedInstanceState == null) { //If activity is really created for the first time.
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, //Show home when creating activity
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                       new HomeFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.app_name));
                break;
            case R.id.nav_analysis:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new AnalysisFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.analysis));
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new SettingsFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.settings));
                break;
            case R.id.nav_share:
                shareApp();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void shareApp(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody =getString(R.string.bodyShare);
        String shareSubject = getString(R.string.subjectShare);

        sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

        startActivity(Intent.createChooser(sharingIntent,getString(R.string.shareUsing)));

        Toast.makeText(this, getString(R.string.share), Toast.LENGTH_SHORT).show();
    }

    public void languageCheck(){
        Locale current = ConfigurationCompat.getLocales(getResources().getConfiguration()).get(0);
        String languageDevice = current.getLanguage();

        SharedPreferences settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        String languageApplication = settings.getString("locale", "en");
        boolean changedLanguage = settings.getBoolean("changedLanguage",false);

        String definitiveLanguage = "";
        if(changedLanguage){
            definitiveLanguage = languageApplication;
        }else{
            if(languageDevice.equals("en") ||languageDevice.equals("es") || languageDevice.equals("it"))
                definitiveLanguage = languageDevice;
            else
                definitiveLanguage = "en"; //DEFAULT LANGUAGE if user doesn't have any of the others
        }

        Locale locale = new Locale(definitiveLanguage);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().
                getResources().getDisplayMetrics());

    }
}
