package alinbarbu.abudget;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BudgetListAdapter listAdapter;
    ExpandableListView expandableListView;

    private BudgetViewModel mBudgetViewModel;
    private ExpenseViewModel mExpenseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expandableListView = findViewById(R.id.budget_list);

        listAdapter = new BudgetListAdapter(this);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);

        // Get a new or existing ViewModel from the ViewModelProvider.
        mBudgetViewModel = ViewModelProviders.of(this).get(BudgetViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mBudgetViewModel.getAllBudgets().observe(this, new Observer<List<Budget>>() {
            @Override
            public void onChanged(@Nullable final List<Budget> budgets) {
                // Update the cached copy of the words in the adapter.
                listAdapter.setBudgets(budgets);
            }
        });

        // Get a new or existing ViewModel from the ViewModelProvider.
        mExpenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mExpenseViewModel.getAllExpenses().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(@Nullable final List<Expense> expenses) {
                // Update the cached copy of the words in the adapter.
                listAdapter.setExpenses(expenses);
            }
        });

        // Listview Group click listener
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listBudgetPreview.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listBudgetPreview.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();

//                Toast.makeText(getApplicationContext(),
//                        "-------------" + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listBudgetPreview.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

//                Toast.makeText(getApplicationContext(),
//                        "++++++++++" + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listBudgetPreview.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listBudgetPreview.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
                return false;
            }
        });
    }
}
