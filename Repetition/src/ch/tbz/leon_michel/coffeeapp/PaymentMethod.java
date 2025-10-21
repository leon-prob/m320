package ch.tbz.leon_michel.coffeeapp;

public interface PaymentMethod {
    String name();
    double fee(double subtotal);
}
