package com.project.soldiapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.project.soldiapp.R;
import com.project.soldiapp.auxiliar.MonthDate;
import com.project.soldiapp.data_handling.ExpenseViewModel;
import com.project.soldiapp.utils.MonthHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {

    public static final String APP_PREFERENCES = "AppPrefs";

    Button deleteButton;
    Button deleteAllButton;
    ImageView spanishLanguage;
    ImageView englishLanguage;
    ImageView italianLanguage;
    Spinner spinnerDelete;

    List<MonthDate> monthsRegisteredList = new ArrayList<>();

    ExpenseViewModel expenseViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class); //ViewModel dealing with database
        monthsRegisteredList = expenseViewModel.getMonthsRegistered();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        deleteButton = view.findViewById(R.id.deleteButton);
        deleteAllButton = view.findViewById(R.id.deleteButtonAll);
        spanishLanguage = view.findViewById(R.id.spanishLanguage);
        englishLanguage = view.findViewById(R.id.englishLanguage);
        italianLanguage = view.findViewById(R.id.italianLanguage);
        spinnerDelete = view.findViewById(R.id.spinnerDelete);

        prepareSpinner();

        addListenersToButtons();
        addListenersToLanguages();

    }

    private void addListenersToLanguages(){

        spanishLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage("es");
        }
        });
        englishLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage("en");
            }
        });
        italianLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage("it");
            }
        });
    }

    public void changeLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getActivity().getBaseContext().getResources().updateConfiguration(configuration,getActivity().getBaseContext().
                getResources().getDisplayMetrics());

        SharedPreferences settings = getActivity().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString("locale",language);
        prefEditor.putBoolean("changedLanguage",true);
        prefEditor.commit();

        //It is required to recreate the activity to reflect the change in UI.
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }

    private void addListenersToButtons() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(getString(R.string.deleteConfirmationTitle) + " " + spinnerDelete.getSelectedItem().toString())
                        .setMessage(getString(R.string.deleteConfirmationText))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = spinnerDelete.getSelectedItem().toString();
                                String[] month_year = text.split(" - ");

                                int month = MonthHandler.extractMonth(getActivity(),month_year[0]);
                                int year = Integer.parseInt(month_year[1]);

                                expenseViewModel.delete(month, year);

                                updateSpinner();

                                Toast.makeText(getActivity(), getString(R.string.confirmDelete), Toast.LENGTH_SHORT).show();
                            }

                        })
                        .setNegativeButton(getString(R.string.no), null).show();
            }
        });
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(getString(R.string.deleteConfirmationTitle) +" " + getString(R.string.allExpenses))
                        .setMessage(getString(R.string.deleteConfirmationText))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                allowFirstExpenseText();

                                expenseViewModel.deleteAll();

                                updateSpinner();

                                Toast.makeText(getActivity(), getString(R.string.confirmDelete), Toast.LENGTH_SHORT).show();
                            }

                        })
                        .setNegativeButton(getString(R.string.no), null).show();



            }
        });
    }

    private void allowFirstExpenseText() {
        SharedPreferences settings = getActivity().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putBoolean("firstExpense", true);
        ((TextView)getActivity().findViewById(R.id.initialAdvice)).setText(getString(R.string.firstTime));
        prefEditor.commit();
    }

    private void updateSpinner(){
        monthsRegisteredList = expenseViewModel.getMonthsRegistered();
        if(monthsRegisteredList.size()==0){
            deleteButton.setOnClickListener(null);
            deleteAllButton.setOnClickListener(null);
        }

        prepareSpinner();
        spinnerDelete.invalidate();
    }

    private void prepareSpinner() {
        spinnerDelete = getView().findViewById(R.id.spinnerDelete);

        ArrayAdapter<MonthDate> adapter = new ArrayAdapter<MonthDate>(getActivity(), android.R.layout.simple_spinner_item, monthsRegisteredList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDelete.setAdapter(adapter);
    }

}
