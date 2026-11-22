class CafeBites extends CafeItem {
    private boolean servedSpicy;

    public CafeBites(String name, double price, String description, int stock, boolean  servedSpicy) {
        super(name, price, description, stock);
        this. servedSpicy =  servedSpicy;
    }

    public boolean isServedSpicy() {
        return  servedSpicy;
    }
    
    public void setServedSpicy(boolean  servedSpicy) {
        this. servedSpicy =  servedSpicy;
    }

    @Override
    public String getSummary() {
        String status = getStock() > 0 ? "Stock: " + getStock() : "OUT OF STOCK";
        return getName() + " (Flavor - " + (servedSpicy ? "Spicy" : "Regular") + ") - ₱" + getPrice() + " [" + status + "]";
    }
}
