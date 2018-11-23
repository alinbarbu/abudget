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

    private static final String[] budgetNames = {
        "Food", "Supermarket", "Car",
        "Bills", "Very long budget name",
        "Charity", "Hobbies", "Restaurant",
        "Vacation", "Gifts"
    };

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
                budgetNames[position-1],
                new Date(),
                new Date(),
                1000.0,
                200.54,
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
