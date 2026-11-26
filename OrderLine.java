
package cafes;

// ORDER LINE
// [ENCAPSULATION]
class OrderLine {

    private CafeItem item;
    private int quantity;

    public OrderLine(CafeItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public CafeItem getItem() {
        return item;
    }

    public String getName() {
        return item.getName();
    }

    public void setItem(CafeItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return item.getPrice() * quantity;
    }
}
