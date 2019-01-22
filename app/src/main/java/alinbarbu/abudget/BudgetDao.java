package alinbarbu.abudget;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BudgetDao {

    @Insert
    void insert(Budget budget);

    @Query("DELETE FROM budgets_table")
    void deleteAll();

    @Query("SELECT * from budgets_table ORDER BY start_date ASC")
    LiveData<List<Budget>> getAllBudgets();
}
