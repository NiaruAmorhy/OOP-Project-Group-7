
package cafes;


import java.util.ArrayList;

// =================================

// [ENCAPSULATION] Inventory
// =================================
class Inventory {

    private ArrayList<OrderLine> inventorys = new ArrayList<>();

    public void add(OrderLine order) {
        inventorys.add(order);
    }

    public void remove(int index) {
        inventorys.remove(index);
    }

    public void clear() {
        inventorys.clear();
    }

    public int length() {
        return inventorys.size();
    }

    public ArrayList<OrderLine> getInventory() {
        return inventorys;
    }
}
