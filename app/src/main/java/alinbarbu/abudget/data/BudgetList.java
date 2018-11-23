package alinbarbu.abudget.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetList {

    /**
     * An array of sample (BudgetPreview) items.
     */
    public static final List<BudgetPreview> BUDGETS = new ArrayList<BudgetPreview>();

    /**
     * A map of sample (BudgetPreview) items, by ID.
     */
    public static final Map<String, BudgetPreview> BUDGETS_MAP = new HashMap<String, BudgetPreview>();

    private static final int COUNT = 10;

    private static final String[] budgetNamesData = {
            "Food", "Supermarket", "Car", "Bills", "Very long budget name",
            "Charity", "Hobbies", "Restaurant", "Vacation", "Gifts"
    };

    private static final Double[] budgetAllocatedData = {
            200.0, 500.0, 100000.0, 600.0, 600000000.0, 200.0, 500.0, 1500.0, 5000.0, 1000.0 };

    private static final Double[] budgetsSpentData = {
            123.45, 200.90, 94550.50, 600.0, 450563445.0, 234.12, 443.50, 1013.55, 2305.0, 705.1 };

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createBudgetPreview(i));
        }
    }

    private static void addItem(BudgetPreview item) {
        BUDGETS.add(item);
        BUDGETS_MAP.put(item.id, item);
    }

    private static BudgetPreview createBudgetPreview(int position) {
        return new BudgetPreview(
                String.valueOf(position),
                budgetNamesData[position-1],
                new Date(),
                new Date(),
                budgetAllocatedData[position-1],
                budgetsSpentData[position-1],
                "RON");
    }

    public static class BudgetPreview {
        public final String id;
        public final String name;
        public final Date startDate;
        public final Date endDate;
        public final Double allocatedAmount;
        public final Double spentAmount;
        public final String currency;

        public BudgetPreview(
                String id,
                String name,
                Date startDate,
                Date endDate,
                Double allocatedAmount,
                Double spentAmount,
                String currency
        ){
            this.id = id;
            this.name = name;
            this.startDate = startDate;
            this.endDate = endDate;
            this.allocatedAmount = allocatedAmount;
            this.spentAmount = spentAmount;
            this.currency = currency;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
