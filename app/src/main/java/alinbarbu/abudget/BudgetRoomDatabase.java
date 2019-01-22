package alinbarbu.abudget;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.Date;

@Database(entities = {Budget.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class BudgetRoomDatabase extends RoomDatabase {

    public abstract BudgetDao BudgetDao();

    private static volatile BudgetRoomDatabase INSTANCE;

    static BudgetRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BudgetRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BudgetRoomDatabase.class, "budget_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final BudgetDao mDao;

        PopulateDbAsync(BudgetRoomDatabase db) {
            mDao = db.BudgetDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//            mDao.deleteAll();
//
//            Budget budget = new Budget(
//                    "Supermarket",
//                    new Date(),
//                    new Date(),
//                    1000.00,
//                    755.35,
//                    "RON");
//            mDao.insert(budget);
//
//            budget = new Budget(
//                    "Entertainment",
//                    new Date(),
//                    new Date(),
//                    300.00,
//                    247.00,
//                    "RON");
//            mDao.insert(budget);

            return null;
        }
    }
}