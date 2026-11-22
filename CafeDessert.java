class CafeBites extends CafeItem {
    private boolean servedChilled;

    public CafeBites(String name, double price, String description, int stock, boolean servedChilled) {
        super(name, price, description, stock);
        this.servedChilled = servedChilled;
    }

    public boolean isServedChilled() {
        return servedChilled;
    }
    
    public void setServedChilled(boolean servedChilled) {
        this.servedChilled = servedChilled;
    }

    @Override
    public String getSummary() {
        String status = getStock() > 0 ? "Stock: " + getStock() : "OUT OF STOCK";
        return getName() + " (Dessert - " + (servedChilled ? "Chilled" : "Warm") + ") - ₱" + getPrice() + " [" + status + "]";
    }
}
