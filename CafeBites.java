
package cafe;

// CAFE BITES
class CafeBites extends CafeItem { // [INHERITANCE]

    // [ENCAPSULATION]
    private boolean servedSpicy;

    public CafeBites(String name, double price, String description,
                     int stock, boolean servedSpicy) {
        
        // [INHERITANCE]
        super(name, price, description, stock); 
        this.servedSpicy = servedSpicy;
    }

    // [ENCAPSULATION]
    public boolean isServedSpicy() {
        return servedSpicy;
    }

    public void setServedSpicy(boolean servedSpicy) {
        this.servedSpicy = servedSpicy;
    }

    // [POLYMORPHISM - Dynamic / Method Overriding]
    @Override
    public String getSummary() {

        String status = getStock() > 0
                ? "Stock: " + getStock()
                : "OUT OF STOCK";

        return getName()
                + " (Flavor - " + (servedSpicy ? "Spicy" : "Regular") + ")"
                + " - ₱" + getPrice()
                + " [" + status + "]";
    }
}


