package alinbarbu.abudget;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import alinbarbu.abudget.data.BudgetList;

public class BudgetListAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    private final List<BudgetList.BudgetPreview> mListBudgetPreview;

    private HashMap<String, List<BudgetList.Expense>> mListBudgetExpenses;

    public BudgetListAdapter(Context context, List<BudgetList.BudgetPreview> listBudgetPreview,
                             HashMap<String, List<BudgetList.Expense>> listBudgetExpenses) {
        this.mContext = context;
        this.mListBudgetPreview = listBudgetPreview;
        this.mListBudgetExpenses = listBudgetExpenses;
    }

    @Override
    public BudgetList.Expense getChild(int groupPosition, int childPosition) {
        return this.mListBudgetExpenses.get(this.mListBudgetPreview.get(groupPosition).id)
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final BudgetList.Expense expense = getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expense, null);
        }

        TextView expenseDateView = convertView.findViewById(R.id.expenseDate);
        String expenseDate = new SimpleDateFormat("dd-MM-yy").format(expense.date);
        expenseDateView.setText(expenseDate);

        TextView expenseTagsView = convertView.findViewById(R.id.expenseTags);
        String expenseTags = TextUtils.join(", ", expense.tags);
        expenseTagsView.setText(expenseTags);

        TextView expenseAmountView = convertView.findViewById(R.id.expenseAmount);
        String expenseAmount = expense.amount + " " + expense.currency;
        expenseAmountView.setText(expenseAmount);

        TextView expenseDescriptionView = convertView.findViewById(R.id.expenseDescription);
        expenseDescriptionView.setText(expense.description);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mListBudgetExpenses.get(this.mListBudgetPreview.get(groupPosition).id)
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListBudgetPreview.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mListBudgetPreview.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        BudgetList.BudgetPreview budgetPreview = (BudgetList.BudgetPreview) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.budget_preview, null);
        }

        TextView mNameView = convertView.findViewById(R.id.name);
        mNameView.setText(budgetPreview.name);

        TextView mPeriodView = convertView.findViewById(R.id.period);
        String startDate = new SimpleDateFormat("dd-MM-yyyy").format(budgetPreview.startDate);
        String endDate = new SimpleDateFormat("dd-MM-yyyy").format(budgetPreview.endDate);
        mPeriodView.setText(startDate + " - " + endDate);

        TextView mAllocatedAmountView = convertView.findViewById(R.id.allocatedAmount);
        String currency = " " + budgetPreview.currency;
        NumberFormat formatter = new DecimalFormat("#,##0.00");
        mAllocatedAmountView.setText(formatter.format(budgetPreview.allocatedAmount) + currency);


        TextView mSpentAmountView = convertView.findViewById(R.id.spentAmount);
        mSpentAmountView.setText(formatter.format(budgetPreview.spentAmount) + currency);

        ProgressBar mProgressBar = convertView.findViewById(R.id.progressBar);
        Double progress = budgetPreview.spentAmount / budgetPreview.allocatedAmount * 100;
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