package ch.tbz.leon_michel.coffeeapp;

public class DebitCard implements PaymentMethod {
    @Override public String name() { return "Debit Card"; }
    @Override public double fee(double subtotal) {
        return 0.20;
    }
}
