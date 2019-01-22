package alinbarbu.abudget;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class BudgetRepository {

    private BudgetDao mBudgetDao;
    private LiveData<List<Budget>> mAllBudgets;

    BudgetRepository(Application application) {
        BudgetRoomDatabase db = BudgetRoomDatabase.getDatabase(application);
        mBudgetDao = db.BudgetDao();
        mAllBudgets = mBudgetDao.getAllBudgets();
    }

    LiveData<List<Budget>> getAllBudgets() {
        return mAllBudgets;
    }


    public void insert (Budget budget) {
        new insertAsyncTask(mBudgetDao).execute(budget);
    }

    private static class insertAsyncTask extends AsyncTask<Budget, Void, Void> {

        private BudgetDao mAsyncTaskDao;

        insertAsyncTask(BudgetDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Budget... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}