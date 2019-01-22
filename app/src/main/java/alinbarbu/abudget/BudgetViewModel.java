package alinbarbu.abudget;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class BudgetViewModel extends AndroidViewModel {

    private BudgetRepository mRepository;

    private LiveData<List<Budget>> mAllBudgets;

    public BudgetViewModel (Application application) {
        super(application);
        mRepository = new BudgetRepository(application);
        mAllBudgets = mRepository.getAllBudgets();
    }

    LiveData<List<Budget>> getAllBudgets() { return mAllBudgets; }

    public void insert(Budget budget) { mRepository.insert(budget); }
}
