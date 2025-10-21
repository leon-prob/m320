package ch.tbz.leon_michel.coffeeapp;

public class PostFinanceCard implements PaymentMethod {
    @Override public String name() { return "PostFinance Card"; }
    @Override public double fee(double subtotal) {
        return subtotal * 0.005;
    }
}
