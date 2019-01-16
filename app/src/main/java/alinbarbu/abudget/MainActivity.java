package alinbarbu.abudget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import alinbarbu.abudget.data.BudgetList;

public class MainActivity extends Activity {

    BudgetListAdapter listAdapter;
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expandableListView = findViewById(R.id.budget_list);

        listAdapter = new BudgetListAdapter(this, BudgetList.BUDGETS, BudgetList.EXPENSE_LIST);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);

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
