package alinbarbu.abudget.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BudgetList {

    /**
     * An array of sample (BudgetPreview) items.
     */
    public static final List<BudgetPreview> BUDGETS = new ArrayList<>();

    /**
     * A map of sample (BudgetExpenses) items, by budget ID.
     */
    public static final HashMap<String, List<Expense>> EXPENSE_LIST = new HashMap<>();

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
        EXPENSE_LIST.put(item.id, createExpenseList(item.id));
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

    private static List<Expense> createExpenseList(String budgetId) {

        List<String> tags = new ArrayList<>();
        tags.add("Cinema");
        tags.add("Shopping");
        tags.add("Sport");
        tags.add("Tickets");
        tags.add("Tickets");
        tags.add("Tickets");
        tags.add("Tickets");

        List<Expense> expenseList = new ArrayList<>();

        Random r = new Random();
        int count = r.nextInt(10);

        for (int i = 1; i <= count; i++) {
            Expense expense = new Expense(
                    budgetId,
                    205.15,
                    "RON",
                    new Date(),
                    "inghetata cu banane",
                    tags);
            expenseList.add(expense);
        }

        return expenseList;
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

    public static class Expense {
        public final String budgetId;
        public final Double amount;
        public final String currency;
        public final Date date;
        public final String description;
        public final List<String> tags;

        public Expense(
                String budgetId,
                Double amount,
                String currency,
                Date date,
                String description,
                List<String> tags
        ) {
            this.budgetId = budgetId;
            this.amount = amount;
            this.currency = currency;
            this.date = date;
            this.description = description;
            this.tags = tags;
        }
    }
}
