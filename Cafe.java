// [ENCAPSULATION]
    private static final String[] CATEGORIES = {
        "Main Course",
        "Small Bites",
        "Beverages/Drinks"
    };

    private static final int STOCK = 5;
    private static final int MAX_CART = 100;

    private int orderCounter = 1;

    private Inventory inventorys = new Inventory();

    private Scanner scanner = new Scanner(System.in);

    //static array-Edyzen
    private CafeItem[][] menus = {

        // MAIN COURSE
        {
            new MainCourse("Steakhouse Ribeye", 100.0,
                "Juicy, tender ribeye steak grilled to perfection, delivering rich flavor in every bite.",
                STOCK, true),

            new MainCourse("Steak and Frites", 120.0,
                "Perfectly grilled steak served with crispy golden French fries for a classic, satisfying meal.",
                STOCK, false),

            new MainCourse("Balsamic Glazed Porkchops", 140.0,
                "Tender, juicy pork chops coated with a sweet and tangy balsamic glaze.",
                STOCK, true),

            new MainCourse("Blackened Salmon Cream", 160.0,
                "Seasoned salmon pan-seared with bold spices and served with a rich, creamy sauce.",
                STOCK, false),

            new MainCourse("Steakhouse Hash & Eggs", 180.0,
                "Savory steakhouse-style hash topped with perfectly cooked eggs for a hearty and satisfying meal.",
                STOCK, false)
        },

        // SMALL BITES
        {
            new CafeBites("Smash Burger", 145.0,
                "Juicy smashed beef patty topped with melted cheese and fresh toppings, served on a toasted bun.",
                STOCK, true),

            new CafeBites("Smoke Double Smash Burger", 110.0,
                "Double smashed beef patties with melted cheese and bold smoky flavor on a toasted bun.",
                STOCK, false),

            new CafeBites("Fried Chicken Sandwich", 155.0,
                "Crispy fried chicken, served with fresh toppings on a toasted bun.",
                STOCK, true),

            new CafeBites("Bunless Fried Chicken", 130.0,
                "Crispy, golden-fried chicken served without the bun for a delicious, satisfying bite.",
                STOCK, true),

            new CafeBites("Spinach Dip", 115.0,
                "A creamy blend of spinach, cheese, and savory seasonings, perfect for sharing.",
                STOCK, false)
        },

        // BEVERAGES / DRINKS
        {
            new CafeDrink("Brewed Coffee", 135.0,
                "Freshly brewed coffee with a rich aroma and smooth, satisfying flavor.",
                STOCK, true),

            new CafeDrink("Americano", 145.0,
                "A bold espresso diluted with hot water, delivering a smooth and balanced coffee taste.",
                STOCK, false),

            new CafeDrink("Caramel Latte", 110.0,
                "Smooth espresso blended with steamed milk and sweet caramel for a rich, creamy treat.",
                STOCK, false),

            new CafeDrink("Java Chip Frappe", 150.0,
                "A creamy, blended coffee drink with chocolate chips and rich mocha flavor, topped with a delightful finish.",
                STOCK, true),

            new CafeDrink("Brewed Raspberry Iced Tea", 105.0,
                "Refreshing brewed iced tea infused with sweet and tangy raspberry flavor, served chilled.",
                STOCK, true)
        }
    };

    // =================================
    // CUSTOMER MENU Montaos
    // =================================

    private void customerFlow() {

        String input;

        while (true) {

            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1. Main Course");
            System.out.println("2. Small Bites");
            System.out.println("3. Beverages/Drinks");
            System.out.println("4. Checkout");
            System.out.println("5. View Cart");
            System.out.println("0. Back");

            System.out.print("Select an option: ");
            input = scanner.nextLine();

            switch (input) {

                case "1":
                    addItem(menus[0], CATEGORIES[0]);
                    break;

                case "2":
                    addItem(menus[1], CATEGORIES[1]);
                    break;

                case "3":
                    addItem(menus[2], CATEGORIES[2]);
                    break;

                case "4":

                    if (inventorys.length() == 0) {

                        System.out.println("Cart is empty.");

                    } else {

                        boolean successful = checkout();

                        if (successful) {
                            inventorys.clear();
                            return;
                        } else {
                            restoreStock();
                        }
                    }

                    break;

                case "5":
                    viewCart();
                    break;

                case "0":
                    restoreStock();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void viewCart() {
        if (inventorys.length() == 0) {
            System.out.println("\nCart is empty.");
            return;
        }

        double total = 0;

        System.out.println("\n========== YOUR CART ==========");

        for (int i = 0; i < inventorys.length(); i++) {

            OrderLine order = inventorys.getInventory().get(i);

            double subtotal = order.getSubtotal();

            total += subtotal;

            System.out.println("\nItem " + (i + 1));
            System.out.println("Name: " + order.getName());
            System.out.println("Quantity: " + order.getQuantity());
            System.out.println("Subtotal: ₱" + subtotal);
            System.out.println("-------------------------------");
        }

        System.out.println("Total: ₱" + total);
        System.out.println("================================");
    }

    // =================================
    // ADD ITEM TO CART Shane
    // =================================

    private void addItem(CafeItem[] items, String title) {

        if (inventorys.length() >= MAX_CART) {
            System.out.println("Cart is full.");
            return;
        }

        String input;
        int choice;
        int quantity;

        while (true) {

            System.out.println("\n--- " + title.toUpperCase() + " ---");
            // [POLYMORPHISM]
            for (int i = 0; i < items.length; i++) {
                System.out.println((i + 1) + ". " + items[i].getSummary());
            }

            System.out.println("0. Back");

            System.out.print("Select an item: ");
            input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection.");
                continue;
            }

            if (choice == 0) {
                return;
            }

            if (choice < 1 || choice > items.length) {
                System.out.println("Invalid selection.");
                continue;
            }

            CafeItem item = items[choice - 1];

            if (item.getStock() <= 0) {
                System.out.println(item.getName() + " is OUT OF STOCK.");
                continue;
            }

            System.out.println("\n" + item.getName());
            System.out.println("Price: ₱" + item.getPrice());
            System.out.println("Stock: " + item.getStock());
            System.out.println("About: " + item.getDescription());

            System.out.print("Quantity (0 to go back): ");
            input = scanner.nextLine();

            try {
                quantity = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity.");
                continue;
            }

            if (quantity == 0) {
                return;
            }

            if (quantity < 0 || quantity > item.getStock()) {
                System.out.println("Invalid quantity.");
                continue;
            }

            item.setStock(item.getStock() - quantity);

            inventorys.add(new OrderLine(item, quantity));

            System.out.println("\n--- Added " + quantity
                    + "x " + item.getName() + " ---");

            return;
        }
    }

    // =================================
    // CHECKOUT Shane
    // =================================

    // [POLYMORPHISM]
    private boolean checkout() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");
        String timestamp = LocalDateTime.now().format(formatter);

        String input;

        // [POLYMORPHISM]
        PaymentMethod method;
        
        
        
        ArrayList<OrderLine> cart = inventorys.getInventory();

        while (true) {
            double total = 0;
            System.out.println("\n========== RECEIPT ==========");
            System.out.println("Order No. " + orderCounter);
            System.out.println("-----------------------------");
                   

            for (int i = 0; i < cart.size(); i++) {

                OrderLine order = cart.get(i);

                double subtotal = order.getSubtotal();

                total = total + subtotal;

                System.out.println(
                        order.getName()
                        + " x" + order.getQuantity()
                        + " = ₱" + subtotal
                );
            }

            System.out.println("-----------------------------");
            System.out.println("Payment Amount (Total): ₱" + total);
             

            System.out.println("\n--- PAYMENT METHOD ---");
            System.out.println("1. Cash");
            System.out.println("2. GCash");
            System.out.println("3. Card");
            System.out.println("0. Cancel order");

            System.out.print("Select payment method: ");
            input = scanner.nextLine();
            
            

            if (input.equals("0")) {

                System.out.println("Order cancelled.");
                return false;

            } else if (input.equals("1")) {

                double amountGiven;

                while (true) {

                    System.out.println("Payment Amount: ₱" + total);
                    System.out.print("Enter amount given (0 to cancel): ₱");
                    input = scanner.nextLine();

                    try {
                        amountGiven = Double.parseDouble(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                        continue;
                    }

                    if (amountGiven == 0) {
                        System.out.println("Order cancelled.");
                        return false;
                    }
                    if (amountGiven < total) {

                        System.out.println("Insufficient payment. Payment Amount: ₱" + total);
                        continue;
                    }

                    double change = amountGiven - total;

                    System.out.println("\n========== FINAL RECEIPT ==========");
                    System.out.println("Order No. " + orderCounter);
                    System.out.println("Date & Time: " + timestamp);
                    System.out.println("Payment Amount: ₱" + total);
                    System.out.println("Amount Given: ₱" + amountGiven);
                    System.out.println("Change: ₱" + change);
                    System.out.println("====================================");

                    // [POLYMORPHISM]
                    method = new Cash();
                    method.pay();

                    System.out.println("Thank you for ordering!");
                    orderCounter++;

                    return true;
                }

            } else if (input.equals("2")) {

                System.out.println("\n========== FINAL RECEIPT ==========");
                System.out.println("Order No. " + orderCounter);
                System.out.println("Payment Amount: ₱" + total);
                System.out.println("Amount Given: ₱" + total);
                System.out.println("Change: ₱0.0");
                System.out.println("====================================");

                // [POLYMORPHISM]
                method = new Gcash();
                method.pay();

                System.out.println("Thank you for ordering!");
                orderCounter++;

                return true;

            } else if (input.equals("3")) {

                System.out.println("\n========== FINAL RECEIPT ==========");
                System.out.println("Order No. " + orderCounter);
                System.out.println("Payment Amount: ₱" + total);
                System.out.println("Amount Given: ₱" + total);
                System.out.println("Change: ₱0.0");
                System.out.println("====================================");

                // [POLYMORPHISM]
                method = new Card();
                method.pay();

                System.out.println("Thank you for ordering!");
                orderCounter++;

                return true;

            } else {

                System.out.println("Invalid payment method.");
            }
        }
        
    }
        
    // =================================
    // RESTORE STOCK  Edyzen
    // =================================
 
    private void restoreStock() {

        ArrayList<OrderLine> cart = inventorys.getInventory();
      
        for (int i = 0; i < cart.size(); i++) {

            OrderLine order = cart.get(i);

            CafeItem item = order.getItem();

            item.setStock(
                    item.getStock() + order.getQuantity()
            );
        }

        inventorys.clear();
    }

    // =================================
    // EMPLOYEE MENU Abendan
    // =================================

    private void employeeFlow() {

        String input;

        while (true) {

            System.out.println("\n--- EMPLOYEE PORTAL ---");
            System.out.println("1. Restock Items");
            System.out.println("2. Edit Item Price");
            System.out.println("0. Back");
            System.out.print("Select a category: ");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    selectCategoryForStaff("restock");
                    break;
                case "2":
                    selectCategoryForStaff("price");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void selectCategoryForStaff(String action) {
        String input;
        while (true) {
            System.out.println("\n--- SELECT CATEGORY ---");
            System.out.println("1. Main Course");
            System.out.println("2. Small Bites");
            System.out.println("3. Beverages/Drinks");
            System.out.println("0. Back");
            System.out.print("Select a category: ");
            input = scanner.nextLine();

            int catIndex;
            switch (input) {
                case "1":
                    catIndex = 0;
                    break;
                case "2":
                    catIndex = 1;
                    break;
                case "3":
                    catIndex = 2;
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid category.");
                    continue;
            }

            if (action.equals("restock")) {
                restockCategory(menus[catIndex], CATEGORIES[catIndex]);
            } else {
                editPriceCategory(menus[catIndex], CATEGORIES[catIndex]);
            }
        }
    }

    // =================================
    // RESTOCK CATEGORY Shane
    // =================================

    private void restockCategory(CafeItem[] items, String title) {

        String input;
        int choice;
        int quantity;

        while (true) {

            System.out.println("\n--- " + title.toUpperCase() + " ---");

            // [POLYMORPHISM]
            for (int i = 0; i < items.length; i++) {
                System.out.println((i + 1) + ". " + items[i].getSummary());
            }

            System.out.println("0. Back");

            System.out.print("Select an item: ");
            input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection.");
                continue;
            }

            if (choice == 0) {
                return;
            }

            if (choice < 1 || choice > items.length) {
                System.out.println("Invalid selection.");
                continue;
            }

            CafeItem item = items[choice - 1];

            System.out.print(
                    "Add stock to " + item.getName()
                    + " (Current stock: " + item.getStock()
                    + ", 0 to cancel): "
            );

            input = scanner.nextLine();

            try {
                quantity = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity.");
                continue;
            }

            if (quantity == 0) {

                System.out.println("Restock cancelled.");

            } else if (quantity > 0) {

                item.setStock(item.getStock() + quantity);

                System.out.println("Stock successfully updated!");
                System.out.println("New stock: " + item.getStock());

            } else {

                System.out.println("Invalid quantity.");
            }
        }
    }


    // =================================
    // EDIT ITEM PRICE  Shane
    // =================================
    private void editPriceCategory(CafeItem[] items, String title) {
        String input;
        int choice;
        double newPrice;
        while (true) {
            System.out.println("\n--- EDIT PRICE: " + title.toUpperCase() + " ---");
            for (int i = 0; i < items.length; i++) {
                // [POLYMORPHISM]
                System.out.println((i + 1) + ". " + items[i].getSummary());
            }
            System.out.println("0. Back");
            System.out.print("Select an item: ");
            input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection.");
                continue;
            }
            if (choice == 0) {
                return;
            }
            if (choice < 1 || choice > items.length) {
                System.out.println("Invalid selection.");
                continue;
            }
            CafeItem item = items[choice - 1];
            System.out.print(
                    "Enter new price for " + item.getName()
                    + " (Current price: ₱" + item.getPrice()
                    + ", 0 to cancel): ₱"
            );
            input = scanner.nextLine();
            try {
                newPrice = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price.");
                continue;
            }
            if (newPrice == 0) {
                System.out.println("Price update cancelled.");
            } else if (newPrice > 0) {
                item.setPrice(newPrice);
                System.out.println("Price successfully updated!");
                System.out.println("New price: ₱" + item.getPrice());
            } else {
                System.out.println("Price must be greater than 0.");
            }
        }
    }
    
      // =================================
    // MAIN MENU Abendan
    // =================================
    
    private void start() {

        String input;

        while (true) {

            System.out.println("\n=== WELCOME TO BASTA CAFE ===");
            System.out.println("1. Customer Menu");
            System.out.println("2. Employee Portal");
            System.out.println("0. Exit");

            System.out.print("Select an option: ");
            input = scanner.nextLine();

            switch (input) {

                case "1":
                    customerFlow();
                    break;

                case "2":
                    if(!login("ExplainMoAbendan" , scanner)){
                        break;
                    }
                    employeeFlow();
                    break;

                case "0":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid command.");
            }
        }
    }
    //Manlapig
    public boolean login(String password, Scanner scanner) {
        int attempt = 3;
        
        while(attempt > 0){
            System.out.print("Password: ");
            String input = scanner.nextLine();
            if(input.equals(password)){
                return true;
            }
            attempt--;
            System.out.println("Wrong Password, Please Try Again \nRemaining Attempt: " + attempt);
        }
        System.out.println("ay bobo");
        return false;
    }
    
        public static void main(String[] args) {
        new FullCode().start();
    }
        
}

