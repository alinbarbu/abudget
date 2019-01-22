package alinbarbu.abudget;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BudgetListAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    private List<Budget> mBudgets = Collections.emptyList();
    private Map<Integer, List<Expense>> mExpenses = new HashMap<Integer, List<Expense>>();

    public BudgetListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public Expense getChild(int groupPosition, int childPosition) {
        return this.mExpenses.get(this.mBudgets.get(groupPosition).getId())
            .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Expense expense = getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expense, null);
        }

        TextView expenseDateView = convertView.findViewById(R.id.expenseDate);
        String expenseDate = new SimpleDateFormat("dd-MM-yy").format(expense.getDate());
        expenseDateView.setText(expenseDate);

        TextView expenseAmountView = convertView.findViewById(R.id.expenseAmount);
        String expenseAmount = expense.getAmount() + " " + expense.getCurrency();
        expenseAmountView.setText(expenseAmount);

        TextView expenseDescriptionView = convertView.findViewById(R.id.expenseDescription);
        expenseDescriptionView.setText(expense.getDescription() + expense.getId());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mExpenses.get(this.mBudgets.get(groupPosition).getId())
                .size();
    }

    void setBudgets(List<Budget> budgets) {
        this.mBudgets = budgets;
        notifyDataSetChanged();
    }

    void setExpenses(List<Expense> expenses) {

        for (Expense expense : expenses)
        {
            int budgetId = expense.getBudgetId();
            List<Expense> currentExpenses = this.mExpenses.get(budgetId);

            if (currentExpenses == null) {
                List<Expense> newExpenseList = new ArrayList<>();
                this.mExpenses.put(budgetId, newExpenseList);
            }

            this.mExpenses.get(budgetId).add(expense);
        }

        notifyDataSetChanged();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mBudgets.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mBudgets.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        Budget budgetPreview = (Budget) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.budget_preview, null);
        }

        TextView mNameView = convertView.findViewById(R.id.name);
        mNameView.setText(budgetPreview.getName());

        TextView mPeriodView = convertView.findViewById(R.id.period);
        String startDate = new SimpleDateFormat("dd-MM-yyyy").format(budgetPreview.getStartDate());
        String endDate = new SimpleDateFormat("dd-MM-yyyy").format(budgetPreview.getEndDate());
        mPeriodView.setText(startDate + " - " + endDate);

        TextView mAllocatedAmountView = convertView.findViewById(R.id.allocatedAmount);
        String currency = " " + budgetPreview.getCurrency();
        NumberFormat formatter = new DecimalFormat("#,##0.00");
        mAllocatedAmountView.setText(formatter.format(budgetPreview.getAllocatedAmount()) + currency);


        TextView mSpentAmountView = convertView.findViewById(R.id.spentAmount);
        mSpentAmountView.setText(formatter.format(budgetPreview.getSpentAmount()) + currency);

        ProgressBar mProgressBar = convertView.findViewById(R.id.progressBar);
        Double progress = budgetPreview.getSpentAmount() / budgetPreview.getAllocatedAmount() * 100;
        if (progress >= 100) {
            progress = 100.0;
            mProgressBar.setProgressDrawable(ContextCompat.getDrawable(mContext, R.drawable.budget_progressbar_exceeded));
        }
        mProgressBar.setProgress(progress.intValue());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}