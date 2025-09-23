package ch.tbz.leon_michel.coffeeapp;

import java.util.Scanner;

public class CoffeeApp {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Coffee Order App ===");
        Order order = new Order();

        while (true) {
            System.out.println();
            System.out.println("Menu:");
            Coffee[] menu = Coffee.values();
            for (int i = 0; i < menu.length; i++) {
                System.out.println("  " + (i + 1) + ") " + menu[i].label()
                        + " - " + fmt(menu[i].unitPrice()) + " CHF");
            }

            System.out.print("Choose drink (number): ");
            int choice = readInt(1, menu.length);
            Coffee selected = menu[choice - 1];

            System.out.print("Quantity: ");
            int qty = readInt(1, 100);
            order.add(selected, qty);

            System.out.print("Add another drink? (y/n): ");
            String again = in.next().trim().toLowerCase();
            if (!again.startsWith("y")) break;
        }

        System.out.println();
        System.out.println("Your Order:");
        for (var e : order.lines().entrySet()) {
            Item item = e.getKey();
            int qty = e.getValue();
            double line = Order.round2(qty * item.unitPrice());
            System.out.println("  " + qty + "x " + item.label()
                    + " @ " + fmt(item.unitPrice())
                    + " = " + fmt(line) + " CHF");
        }
        double subtotal = Order.round2(order.subtotal());
        System.out.println("Subtotal: " + fmt(subtotal) + " CHF");

        PaymentMethod[] methods = new PaymentMethod[]{
                new CreditCard(), new DebitCard(), new PostFinanceCard()
        };
        System.out.println();
        System.out.println("Payment Method:");
        for (int i = 0; i < methods.length; i++) {
            System.out.println("  " + (i + 1) + ") " + methods[i].name());
        }
        System.out.print("Choose payment (number): ");
        int p = readInt(1, methods.length);
        PaymentMethod method = methods[p - 1];

        double fee = Order.round2(method.fee(subtotal));
        double total = Order.round2(subtotal + fee);

        System.out.println();
        System.out.println("=== Receipt ===");
        System.out.println("Subtotal:    " + fmt(subtotal) + " CHF");
        System.out.println("Payment:     " + method.name());
        System.out.println("Service fee: " + fmt(fee) + " CHF");
        System.out.println("TOTAL:       " + fmt(total) + " CHF");
        System.out.println("Thank you for your order!");
    }

    private static int readInt(int min, int max) {
        while (true) {
            String s = in.next().trim();
            try {
                int n = Integer.parseInt(s);
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException ignored) {}
            System.out.print("Please enter a number between " + min + " and " + max + ": ");
        }
    }

    private static String fmt(double v) {
        return String.format("%.2f", v);
    }
}
