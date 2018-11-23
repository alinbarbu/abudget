package alinbarbu.abudget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import alinbarbu.abudget.data.BudgetList;

public class MainActivity extends AppCompatActivity implements BudgetPreviewFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.budget_list_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            BudgetPreviewFragment firstFragment = new BudgetPreviewFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.budget_list_container, firstFragment).commit();
        }

    }

    public void onListFragmentInteraction(BudgetList.BudgetPreview item) {
    }
}
