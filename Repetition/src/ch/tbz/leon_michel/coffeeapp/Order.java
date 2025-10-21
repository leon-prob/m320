package ch.tbz.leon_michel.coffeeapp;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private final Map<Item, Integer> items = new LinkedHashMap<>();

    public void add(Item item, int qty) {
        if (qty <= 0) return;
        items.merge(item, qty, Integer::sum);
    }

    public Map<Item, Integer> lines() {
        return items;
    }

    public double subtotal() {
        double sum = 0.0;
        for (var e : items.entrySet()) {
            sum += e.getKey().unitPrice() * e.getValue();
        }
        return sum;
    }

    public static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
