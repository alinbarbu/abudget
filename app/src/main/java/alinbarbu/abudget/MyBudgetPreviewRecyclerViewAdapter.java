package alinbarbu.abudget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alinbarbu.abudget.BudgetPreviewFragment.OnListFragmentInteractionListener;
import alinbarbu.abudget.data.BudgetList.BudgetPreview;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BudgetPreview} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBudgetPreviewRecyclerViewAdapter extends RecyclerView.Adapter<MyBudgetPreviewRecyclerViewAdapter.ViewHolder> {

    private final List<BudgetPreview> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyBudgetPreviewRecyclerViewAdapter(List<BudgetPreview> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_budgetpreview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).name);
        String startDate = new SimpleDateFormat("dd-MM-yyyy").format(mValues.get(position).startDate);
        String endDate = new SimpleDateFormat("dd-MM-yyyy").format(mValues.get(position).endDate);
        holder.mPeriodView.setText(startDate + " - " + endDate);
        String currency = " " + mValues.get(position).currency;
        NumberFormat formatter = new DecimalFormat("#,##0.00");
        holder.mAllocatedAmountView.setText(formatter.format(mValues.get(position).allocatedAmount) + currency);
        holder.mSpentAmountView.setText(formatter.format(mValues.get(position).spentAmount) + currency);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mPeriodView;
        public final TextView mAllocatedAmountView;
        public final TextView mSpentAmountView;
        public BudgetPreview mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mPeriodView = (TextView) view.findViewById(R.id.period);
            mAllocatedAmountView = (TextView) view.findViewById(R.id.allocatedAmount);
            mSpentAmountView = (TextView) view.findViewById(R.id.spentAmount);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
