package ch.tbz.leon_michel.coffeeapp;

public class CreditCard implements PaymentMethod {
    @Override public String name() { return "Credit Card"; }
    @Override public double fee(double subtotal) {
        return subtotal * 0.015 + 0.30;
    }
}
