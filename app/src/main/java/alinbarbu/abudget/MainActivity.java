package alinbarbu.abudget;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import java.util.Date;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private int ADD_EXPENSE_REQUEST_CODE = 100;

    BudgetListAdapter listAdapter;
    ExpandableListView expandableListView;

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
        BudgetViewModel mBudgetViewModel = ViewModelProviders.of(this).get(BudgetViewModel.class);

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
        ExpenseViewModel mExpenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

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

        FloatingActionButton fab = findViewById(R.id.add_floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
                startActivityForResult(intent, ADD_EXPENSE_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_EXPENSE_REQUEST_CODE && resultCode == RESULT_OK) {
            String amount = data.getStringExtra(AddExpenseActivity.EXTRA_AMOUNT);
            String description = data.getStringExtra(AddExpenseActivity.EXTRA_DESCRIPTION);

            addExpense(amount, description);
        }
    }

    public void addExpense(String amount, String description)
    {
        ExpenseViewModel mExpenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);

        Expense budget = new Expense(
                31,
                new Date(),
                Double.parseDouble(amount),
                "RON",
                description);
        mExpenseViewModel.insert(budget);
    }
}
