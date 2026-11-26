
package cafe;

// CAFE DRINK
class CafeDrink extends CafeItem { // [INHERITANCE]

    // [ENCAPSULATION]
    private boolean iced;

    public CafeDrink(String name, double price, String description,
                     int stock, boolean iced) {
        
        // [INHERITANCE]
        super(name, price, description, stock); 
        this.iced = iced;
    }

    // [ENCAPSULATION]
    public boolean isIced() {
        return iced;
    }

    public void setIced(boolean iced) {
        this.iced = iced;
    }

    // [POLYMORPHISM - Dynamic / Method Overriding]
    @Override
    public String getSummary() {

        String status = getStock() > 0
                ? "Stock: " + getStock()
                : "OUT OF STOCK";

        return getName()
                + " (Cafe Drink - " + (iced ? "Iced" : "Hot") + ")"
                + " - ₱" + getPrice()
                + " [" + status + "]";
    }
}
