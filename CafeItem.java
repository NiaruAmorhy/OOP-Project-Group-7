
package cafes;

// =================================
// ABSTRACT CAFE ITEM
// =================================
abstract class CafeItem {

    private String name;
    private double price;
    private String description;
    private int stock;

    public CafeItem(String name, double price,
                    String description, int stock) {

        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {

        if (stock < 0) {
            stock = 0;
        }

        this.stock = stock;
    }

    public abstract String getSummary();
}
