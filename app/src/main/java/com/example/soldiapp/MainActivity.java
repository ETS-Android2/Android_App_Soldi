package com.example.soldiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Navigation
        setupNavigation();

        //Navigation drawer and toolbar
        setupNavigationDrawerAndToolbar(savedInstanceState);

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);

        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

    }

    //TRATAR DE ENCONTRAR EL LISTENER DEL HAMBURGUER PARA CUANDO ESTE EN FRAGMENT DE NAVIGATION DAR HACIA ATRÁS, NO DESPLEGAR DRAWER
    /*
            if (getSupportActionBar().getTitle().toString().equals(getResources().getString(R.string.expense_type_title))) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.popBackStack();
    } else if (getSupportActionBar().getTitle().toString().equals(getResources().getString(R.string.payment_method_title))) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.popBackStack();
    } else if (getSupportActionBar().getTitle().toString().equals(getResources().getString(R.string.confirm_expense_title))) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.popBackStack();
    } else {
    */

    private void setupNavigationDrawerAndToolbar(Bundle savedInstanceState) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Class that handles the toggle on the navigation drawer and the "hamburger" icon turn.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) { //If activity is really created for the first time.
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, //Show home when creating activity
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String toolbarTitle = "";
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
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new AboutFragment()).commit();
                getSupportActionBar().setTitle(getString(R.string.about));
                break;
            /*case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;*/
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
