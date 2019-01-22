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

@Database(entities = {Expense.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class ExpenseRoomDatabase extends RoomDatabase {

    public abstract ExpenseDao ExpenseDao();

    private static volatile ExpenseRoomDatabase INSTANCE;

    static ExpenseRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExpenseRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExpenseRoomDatabase.class, "expense_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ExpenseDao mDao;

        PopulateDbAsync(ExpenseRoomDatabase db) {
            mDao = db.ExpenseDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//            mDao.deleteAll();
//
//            Expense budget = new Expense(
//                    31,
//                    new Date(),
//                    1000.00,
//                    "RON",
//                    "description goes here");
//            mDao.insert(budget);
//
//            budget = new Expense(
//                    31,
//                    new Date(),
//                    680.00,
//                    "RON",
//                    "description goes here");
//            mDao.insert(budget);
//
//
//            budget = new Expense(
//                    32,
//                    new Date(),
//                    120.00,
//                    "RON",
//                    "description goes here");
//            mDao.insert(budget);
//
//            budget = new Expense(
//                    32,
//                    new Date(),
//                    650.00,
//                    "RON",
//                    "description goes here");
//            mDao.insert(budget);
//
//            budget = new Expense(
//                    32,
//                    new Date(),
//                    12.55,
//                    "RON",
//                    "description goes here");
//            mDao.insert(budget);

            return null;
        }
    }
}