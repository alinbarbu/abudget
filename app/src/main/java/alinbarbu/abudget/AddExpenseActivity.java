package alinbarbu.abudget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddExpenseActivity extends AppCompatActivity {

    public static final String EXTRA_AMOUNT = "alinbarbu.abudget.addexpense.extra.amount";
    public static final String EXTRA_DESCRIPTION = "alinbarbu.abudget.addexpense.extra.description";

    private EditText mExpenseAmount;
    private EditText mExpenseDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        mExpenseAmount = findViewById(R.id.add_expense_amount);
        mExpenseDescription = findViewById(R.id.add_expense_description);
    }

    public void saveExpense(View view) {
        String expenseAmount = mExpenseAmount.getText().toString();
        String expenseDescription = mExpenseDescription.getText().toString();

        Intent addExpenseIntent = new Intent();
        addExpenseIntent.putExtra(EXTRA_AMOUNT, expenseAmount);
        addExpenseIntent.putExtra(EXTRA_DESCRIPTION, expenseDescription);

        setResult(RESULT_OK, addExpenseIntent);
        finish();
    }
}
