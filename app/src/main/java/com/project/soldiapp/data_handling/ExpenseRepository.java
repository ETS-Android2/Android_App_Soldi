package com.project.soldiapp.data_handling;

import android.app.Application;
import android.os.AsyncTask;

import com.project.soldiapp.auxiliar.DayExpense;
import com.project.soldiapp.auxiliar.Expense;
import com.project.soldiapp.auxiliar.Expense_Payment;
import com.project.soldiapp.auxiliar.Expense_Type;
import com.project.soldiapp.auxiliar.MonthDate;
import com.project.soldiapp.auxiliar.MonthExpense;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExpenseRepository {
    private ExpenseDao expenseDao;

    /**
     * Database operations need to be on a different thread from the main app thread (otherwise app would crash)
     */
    public ExpenseRepository(Application application) { //Context
        ExpenseDatabase database = ExpenseDatabase.getInstance(application);
        expenseDao = database.expenseDao();
    }

    /**
     * Insert expense on database
     *
     * @param expense
     */
    public void insert(Expense expense) {
        new InsertExpenseAsyncTask(expenseDao).execute(expense);
    }

    private static class InsertExpenseAsyncTask extends AsyncTask<Expense, Void, Void> {
        private ExpenseDao expenseDao;

        private InsertExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.insert(expenses[0]);
            return null;
        }
    }

    /**
     * Delete expenses of a given month and year
     * @param month
     * @param year
     */
    public void delete(int month, int year) {
        new DeleteExpensesAsyncTask(expenseDao).execute(month,year);
    }
    private static class DeleteExpensesAsyncTask extends AsyncTask<Integer, Void, Void> {
        private ExpenseDao expenseDao;

        private DeleteExpensesAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            expenseDao.deleteExpenses(integers[0],integers[1]);
            return null;
        }
    }

    /**
     * Delete all expenses
     */
    public void deleteAll() {
        new DeleteAllExpensesAsyncTask(expenseDao).execute();
    }
    private static class DeleteAllExpensesAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExpenseDao expenseDao;

        private DeleteAllExpensesAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            expenseDao.deleteAllExpenses();
            return null;
        }
    }


    /**
     * Get all expenses from database
     *
     * @return List<Expense>
     */
    public List<Expense> getAllExpenses() {
        try {
            return new GetExpenseAsyncTask(expenseDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetExpenseAsyncTask extends AsyncTask<Void, Void, List<Expense>> {
        private ExpenseDao expenseDao;

        private GetExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<Expense> doInBackground(Void... voids) {
            return expenseDao.getAllExpenses();
        }
    }

    /**
     * Gets a list of the sum of the expenses of each type on a given month and year
     *
     * @param month
     * @param year
     * @return List<Expense_Type>
     */
    public List<Expense_Type> getSumTypeExpenses(int month, int year) {
        try {
            return new GetTypeExpenseAsyncTask(expenseDao).execute(month, year).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetTypeExpenseAsyncTask extends AsyncTask<Integer, Void, List<Expense_Type>> {
        private ExpenseDao expenseDao;

        private GetTypeExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<Expense_Type> doInBackground(Integer... arg0) {
            return expenseDao.getSumTypeExpenses(arg0[0], arg0[1]);
        }
    }

    /**
     * Gets a list of the sum of the expenses of each type on a given year
     *
     * @param year
     * @return List<Expense_Type>
     */
    public List<Expense_Type> getSumTypeExpenses(int year) {
        try {
            return new GetYearTypeExpenseAsyncTask(expenseDao).execute(year).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetYearTypeExpenseAsyncTask extends AsyncTask<Integer, Void, List<Expense_Type>> {
        private ExpenseDao expenseDao;

        private GetYearTypeExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<Expense_Type> doInBackground(Integer... arg0) {
            return expenseDao.getSumTypeExpenses(arg0[0]);
        }
    }


    /**
     * Gets a list of the sum of the expenses of the two possible methods of payments given a year
     *
     * @param year
     * @return List<Expense_Payment>
     */
    public List<Expense_Payment> getSumPaymentExpenses(int year) {
        try {
            return new GetYearPaymentExpenseAsyncTask(expenseDao).execute(year).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetYearPaymentExpenseAsyncTask extends AsyncTask<Integer, Void, List<Expense_Payment>> {
        private ExpenseDao expenseDao;

        private GetYearPaymentExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<Expense_Payment> doInBackground(Integer... arg0) {
            return expenseDao.getSumPaymentExpenses(arg0[0]);
        }
    }

    /**
     * Gets a list of the sum of the expenses of the two possible methods of payments given a year and a month
     *
     * @param year
     * @param month
     * @return List<Expense_Payment>
     */
    public List<Expense_Payment> getSumPaymentExpenses(int year, int month) {
        try {
            return new GetPaymentExpenseAsyncTask(expenseDao).execute(year, month).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetPaymentExpenseAsyncTask extends AsyncTask<Integer, Void, List<Expense_Payment>> {
        private ExpenseDao expenseDao;

        private GetPaymentExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<Expense_Payment> doInBackground(Integer... arg0) {
            return expenseDao.getSumPaymentExpenses(arg0[0], arg0[1]);
        }
    }

    /**
     * Get all months and year from which a user has added expenses
     *
     * @return List<MonthDate>
     */
    public List<MonthDate> getMonthsRegistered() {
        try {
            return new GetMonthsRegisteredAsyncTask(expenseDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetMonthsRegisteredAsyncTask extends AsyncTask<Void, Void, List<MonthDate>> {
        private ExpenseDao expenseDao;

        private GetMonthsRegisteredAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<MonthDate> doInBackground(Void... voids) {
            return expenseDao.getMonthsRegistered();
        }
    }

    /**
     * Get all years in which the user has added a expense
     *
     * @return List<Integer>
     */
    public List<Integer> getYearsRegistered() {
        try {
            return new GetYearsRegisteredAsyncTask(expenseDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetYearsRegisteredAsyncTask extends AsyncTask<Void, Void, List<Integer>> {
        private ExpenseDao expenseDao;

        private GetYearsRegisteredAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<Integer> doInBackground(Void... voids) {
            return expenseDao.getYearsRegistered();
        }
    }

    /**
     * Get all expenses per day on a given month of a given year
     *
     * @return List<DayExpense>
     */
    public List<DayExpense> getDayExpenses(int month, int year) {
        try {
            return new GetDayExpensesAsyncTask(expenseDao).execute(month, year).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetDayExpensesAsyncTask extends AsyncTask<Integer, Void, List<DayExpense>> {
        private ExpenseDao expenseDao;

        private GetDayExpensesAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<DayExpense> doInBackground(Integer... arg0) {
            return expenseDao.getDayExpenses(arg0[0], arg0[1]);
        }
    }

    /**
     * Get all expenses per month on a given year
     *
     * @return List<MonthExpense>
     */
    public List<MonthExpense> getMonthExpenses(int year) {
        try {
            return new GetMonthExpensesAsyncTask(expenseDao).execute(year).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetMonthExpensesAsyncTask extends AsyncTask<Integer, Void, List<MonthExpense>> {
        private ExpenseDao expenseDao;

        private GetMonthExpensesAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected List<MonthExpense> doInBackground(Integer... arg0) {
            return expenseDao.getMonthExpenses(arg0[0]);
        }
    }


}
