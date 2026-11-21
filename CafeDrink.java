class CafeDrink extends CafeItem {
    private boolean iced;

    public CafeDrink(String name, double price, String description, int stock, boolean iced) {
        super(name, price, description, stock);
        this.iced = iced;
    }

    public boolean isIced() { return iced; }
    public void setIced(boolean iced) { this.iced = iced; }

    @Override
    public String getSummary() {
        String status = getStock() > 0 ? "Stock: " + getStock() : "OUT OF STOCK";
        return getName() + " (Cafe Drink - " + (iced ? "Iced" : "Hot") + ") - ₱" + getPrice() + " [" + status + "]";
    }
}
