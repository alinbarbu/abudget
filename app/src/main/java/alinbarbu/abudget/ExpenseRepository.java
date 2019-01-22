package alinbarbu.abudget;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ExpenseRepository {

    private ExpenseDao mExpenseDao;
    private LiveData<List<Expense>> mAllExpenses;

    ExpenseRepository(Application application) {
        ExpenseRoomDatabase db = ExpenseRoomDatabase.getDatabase(application);
        mExpenseDao = db.ExpenseDao();
        mAllExpenses = mExpenseDao.getAllExpenses();
    }

    LiveData<List<Expense>> getAllExpenses() {
        return mAllExpenses;
    }


    public void insert (Expense budget) {
        new insertAsyncTask(mExpenseDao).execute(budget);
    }

    private static class insertAsyncTask extends AsyncTask<Expense, Void, Void> {

        private ExpenseDao mAsyncTaskDao;

        insertAsyncTask(ExpenseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Expense... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}