import java.util.Scanner;

public class Cafe {
    private static final String[] CATS = {"Main Course", "Small Bites", "Beverages/Drinks"};
    private static final int Stock = 3;
    private final Scanner sc = new Scanner(System.in);
    private final CafeItem[][] menus = buildMenus();

    public static void main(String[] args) { new Cafe().start(); }

    private void start() {
        while (true) {
            System.out.println("\n=== WELCOME TO CAFE NAME ===\n1. Customer Menu\n2. Employee Restock\n0. Exit");
            int input = readInt("Option: ");
            if (input == 1) customerFlow();
            else if (input == 2) employeeFlow();
            else if (input == 0) { System.out.println("Goodbye!"); break; }
            else System.out.println("Invalid choice.");
        }
        sc.close();
    }

    private static CafeItem[][] buildMenus() {
        return new CafeItem[][]{
            {
                new FoodEntree("Steakhouse Ribeye", 100.0, "Juicy, tender ribeye steak grilled to perfection, delivering rich flavor in every bite.", Stock, true),
                new FoodEntree("Steak and Frites", 120.0, "Perfectly grilled steak served with crispy golden French fries for a classic, satisfying meal.", Stock, false),
                new FoodEntree("Balsamic Glazed Porkchops", 140.0, "Tender, juicy pork chops coated with a sweet and tangy balsamic glaze.", Stock, true),
                new FoodEntree("Blackened Salmon Cream", 160.0, "Seasoned salmon pan-seared with bold spices and served with a rich, creamy sauce.", Stock, false),
                new FoodEntree("Steakhouse Hash & Eggs", 180.0, "Savory steakhouse-style hash topped with perfectly cooked eggs for a hearty and satisfying meal.", Stock, false)
            },
            {
                new CafeBites("Smash Burger", 145.0, "Juicy smashed beef patty topped with melted cheese and fresh toppings, served on a toasted bun.", Stock, true),
                new CafeBites("Smoke Double Smash Burger", 110.0, "Double smashed beef patties with melted cheese and bold smoky flavor on a toasted bun.", Stock, false),
                new CafeBites("Fried Chicken Sandwich", 155.0, "Crispy fried chicken, served with fresh toppings on a toasted bun.", Stock, true),
                new CafeBites("Bunless Fried Chicken", 130.0, "Crispy, golden-fried chicken served without the bun for a delicious, satisfying bite.", Stock, true),
                new CafeBites("Spinach Dip", 115.0, "A creamy blend of spinach, cheese, and savory seasonings, perfect for sharing.", Stock, false)
            },
            {
                new CafeDrink("Brewed Coffe", 135.0, "Freshly brewed coffee with a rich aroma and smooth, satisfying flavor.", Stock, true),
                new CafeDrink("Americano", 145.0, "A bold espresso diluted with hot water, delivering a smooth and balanced coffee taste.", Stock, false),
                new CafeDrink("Caramel Latte", 110.0, "Smooth espresso blended with steamed milk and sweet caramel for a rich, creamy treat.", Stock, false),
                new CafeDrink("Java Chip Frappe", 150.0, "A creamy, blended coffee drink with chocolate chips and rich mocha flavor, topped with a delightful finish.", Stock, true),
                new CafeDrink("Brewed Raspberry Iced Tea", 105.0, "Refreshing brewed iced tea infused with sweet and tangy raspberry flavor, served chilled.", Stock, true)
            }
        };
    }

    private void customerFlow() {
        OrderLine[] cart = new OrderLine[100];
        int count = 0;
        while (true) {
            System.out.println("\n--- MENU ---\n1. Main Course\n2. Small Bites\n3. Beverages/Drinks\n4. Checkout\n0. Back");
            int c = readInt("Option: ");
            if (c == 0) {
                for (int i = 0; i < count; i++) cart[i].getItem().setStock(cart[i].getItem().getStock() + cart[i].getQuantity());
                return;
            } else if (c >= 1 && c <= 3) count = addItem(menus[c - 1], CATS[c - 1], cart, count);
            else if (c == 4) {
                if (count == 0) System.out.println("Cart is empty.");
                else { checkout(cart, count); return; }
            } else System.out.println("Invalid option.");
        }
    }

    private int addItem(CafeItem[] items, String title, OrderLine[] cart, int count) {
        if (count >= cart.length) { System.out.println("Cart full."); return count; }
        while (true) {
            int idx = pickItem(items, title);
            if (idx == -1) return count;
            CafeItem item = items[idx];
            if (item.getStock() <= 0) { System.out.println(item.getName() + " is OUT OF STOCK."); continue; }
            System.out.println("\n" + item.getName() + " | ₱" + item.getPrice() + " | Stock: " + item.getStock() + "\nAbout: " + item.getDescription());
            int qty = readInt("Quantity (0 to go back): ");
            if (qty == 0) continue;
            if (qty < 0 || qty > item.getStock()) { System.out.println("Invalid quantity."); continue; }
            item.setStock(item.getStock() - qty);
            cart[count++] = new OrderLine(item, qty);
            System.out.println("--- Added " + qty + "x " + item.getName() + " ---");
            return count;
        }
    }

    private void checkout(OrderLine[] cart, int count) {
        double total = 0;
        System.out.println("\n=== RECEIPT ===");
        for (int i = 0; i < count; i++) {
            double sub = cart[i].getSubtotal();
            total += sub;
            System.out.println("- " + cart[i].getItem().getSummary() + " x" + cart[i].getQuantity() + " = ₱" + sub);
        }
        System.out.println("Total: ₱" + total);
        while (true) {
            System.out.print("Payment (-1 to cancel): ₱");
            double pay = sc.nextDouble();
            if (pay == -1) { System.out.println("Order cancelled."); return; }
            if (pay >= total) { System.out.println("Change: ₱" + (pay - total)); return; }
            System.out.println("Insufficient payment.");
        }
    }

    private void employeeFlow() {
        while (true) {
            System.out.println("\n--- RESTOCK ---\n1. Main Course\n2. Small Bites\n3. Beverages/Drinks\n0. Back");
            int cat = readInt("Option: ");
            if (cat == 0) return;
            if (cat < 1 || cat > 3) { System.out.println("Invalid category."); continue; }
            while (true) {
                int idx = pickItem(menus[cat - 1], CATS[cat - 1]);
                if (idx == -1) break;
                CafeItem t = menus[cat - 1][idx];
                int add = readInt("Add stock to " + t.getName() + " (" + t.getStock() + ", 0 to cancel): ");
                if (add > 0) { t.setStock(t.getStock() + add); System.out.println("Stock: " + t.getStock()); }
            }
        }
    }

    private int pickItem(CafeItem[] items, String title) {
        System.out.println("\n--- " + title.toUpperCase() + " ---");
        for (int i = 0; i < items.length; i++) System.out.println((i + 1) + ". " + items[i].getSummary());
        System.out.println("0. Back");
        int c = readInt("Select (1-" + items.length + " or 0): ");
        if (c == 0) return -1;
        if (c < 1 || c > items.length) { System.out.println("Invalid selection."); return -2; }
        return c - 1;
    }

    private int readInt(String p) { System.out.print(p); return sc.nextInt(); }
}
