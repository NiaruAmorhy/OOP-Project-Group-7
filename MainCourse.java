
package cafe;



//  MAIN COURSE
class MainCourse extends CafeItem { // [INHERITANCE]

    // [ENCAPSULATION]
    private boolean bestSeller;

    public MainCourse(String name, double price, String description,
                    int stock, boolean bestSeller) {
        
        // [INHERITANCE]
        super(name, price, description, stock); 
        this.bestSeller = bestSeller;
    }

    // [ENCAPSULATION]
    public boolean isBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    // [POLYMORPHISM - Dynamic / Method Overriding]
    @Override
    public String getSummary() {

        String status = getStock() > 0
                ? "Stock: " + getStock()
                : "OUT OF STOCK";

        return getName() + " (Food)"
                + (bestSeller ? " [Best Seller]" : "")
                + " - ₱" + getPrice()
                + " [" + status + "]";
    }
}
