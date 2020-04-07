package com.example.soldiapp.data_handling;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.soldiapp.auxiliar.Expense;
import com.example.soldiapp.auxiliar.Expense_Payment;
import com.example.soldiapp.auxiliar.Expense_Type;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExpenseRepository {
    private ExpenseDao expenseDao;

    public ExpenseRepository(Application application){ //Context
        ExpenseDatabase database = ExpenseDatabase.getInstance(application);
        expenseDao = database.expenseDao();
        //allExpenses = expenseDao.getAllExpenses();
    }

    /**
     * Database operations need to be on a different thread from the main app thread (otherwise app would crash)
     */
    public void insert(Expense expense){
        new InsertExpenseAsyncTask(expenseDao).execute(expense);
    }

    private static class InsertExpenseAsyncTask extends AsyncTask<Expense,Void,Void> {
        private ExpenseDao expenseDao;

        private InsertExpenseAsyncTask(ExpenseDao expenseDao){
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.insert(expenses[0]);
            return null;
        }
    }

    /**
     * Database operations need to be on a different thread from the main app thread (otherwise app would crash)
     */
    public List<Expense> getAllExpenses(){
        try {
            return new GetExpenseAsyncTask(expenseDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetExpenseAsyncTask extends AsyncTask<Void,Void,List<Expense>> {
        private ExpenseDao expenseDao;

        private GetExpenseAsyncTask(ExpenseDao expenseDao){
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<Expense> doInBackground(Void... voids) {
            return expenseDao.getAllExpenses();
        }
    }

    /**
     * Database operations need to be on a different thread from the main app thread (otherwise app would crash)
     */
    public List<Expense_Type> getSumTypeExpenses(){
        try {
            return new GetTypeExpenseAsyncTask(expenseDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetTypeExpenseAsyncTask extends AsyncTask<Void,Void,List<Expense_Type>> {
        private ExpenseDao expenseDao;

        private GetTypeExpenseAsyncTask(ExpenseDao expenseDao){
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<Expense_Type> doInBackground(Void... voids) {
            return expenseDao.getSumTypeExpenses();
        }
    }


    /**
     * Database operations need to be on a different thread from the main app thread (otherwise app would crash)
     */
    public List<Expense_Payment> getSumPaymentExpenses(){
        try {
            return new GetPaymentExpenseAsyncTask(expenseDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetPaymentExpenseAsyncTask extends AsyncTask<Void,Void,List<Expense_Payment>> {
        private ExpenseDao expenseDao;

        private GetPaymentExpenseAsyncTask(ExpenseDao expenseDao){
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<Expense_Payment> doInBackground(Void... voids) {
            return expenseDao.getSumPaymentExpenses();
        }
    }








}
