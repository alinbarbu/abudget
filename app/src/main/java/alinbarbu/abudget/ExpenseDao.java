package alinbarbu.abudget;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(Expense expense);

    @Query("DELETE FROM expenses_table")
    void deleteAll();

    @Query("SELECT * from expenses_table ORDER BY date DESC")
    LiveData<List<Expense>> getAllExpenses();
}
