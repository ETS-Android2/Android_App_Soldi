package com.example.soldiapp.data_handling;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.soldiapp.auxiliar.Expense;

@Database(entities = {Expense.class}, version = 1)
public abstract class ExpenseDatabase extends RoomDatabase {

    private static ExpenseDatabase instance;

    public abstract ExpenseDao expenseDao();

    public static synchronized ExpenseDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ExpenseDatabase.class, "expense_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private ExpenseDao expenseDao;

        private PopulateDbAsyncTask(ExpenseDatabase db){
            expenseDao = db.expenseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            expenseDao.insert(new Expense(13.58,"supermarket",true));
            expenseDao.insert(new Expense(9.5,"other",false));
            expenseDao.insert(new Expense(59,"leisure",true));
            expenseDao.insert(new Expense(12,"supermarket",true));
            expenseDao.insert(new Expense(1.5,"supermarket",false));
            expenseDao.insert(new Expense(102,"transport",true));
            return null;
        }
    }
}
