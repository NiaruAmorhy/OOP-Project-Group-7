
package cafe;

// =================================
// [INTERFACE] 
// =================================
interface PaymentMethod {
    void pay();
}

class Gcash implements PaymentMethod {
    @Override  
    public void pay() {
        System.out.println("GCash payment successful.");
    }
}

class Cash implements PaymentMethod {
    @Override  
    public void pay() {
        System.out.println("Cash payment successful.");
    }
}

class Card implements PaymentMethod {
    @Override  
    public void pay() {
        System.out.println("Card payment successful.");
    }
}
