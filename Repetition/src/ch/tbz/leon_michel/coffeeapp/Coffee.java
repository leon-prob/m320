package ch.tbz.leon_michel.coffeeapp;

public enum Coffee implements Item {
    CAFE_MISTO("Café Misto", 4.20),
    CAFE_LATTE("Café Latte", 4.90),
    DECAF("Decaf", 4.50),
    ESPRESSO("Espresso", 3.50),
    CAPPUCCINO("Cappuccino", 4.60);

    private final String displayName;
    private final double price;

    Coffee(String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    @Override public String label() {
        return displayName;
    }

    @Override public double unitPrice() {
        return price;
    }

    @Override public String toString() {
        return displayName;
    }
}
