package ch.tbz.leon_michel.garage;

import java.util.*;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Garage garage = seed();

        while (true) {
            System.out.println("\n-- Garage Menu --");
            System.out.println("1) List all vehicles");
            System.out.println("2) Repair vehicle by ID");
            System.out.println("3) Repair vehicle by ID with discount (0.0..0.5)");
            System.out.println("4) Show repaired vehicles and costs");
            System.out.println("5) Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    garage.list().forEach(System.out::println);
                    break;
                case "2":
                    repairFlow(garage, false);
                    break;
                case "3":
                    repairFlow(garage, true);
                    break;
                case "4":
                    showRepaired(garage);
                    break;
                case "5":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static Garage seed() {
        Garage garage = new Garage();
        garage.add(new Car("C1", "Honda Civic", 2.0));
        garage.add(new Truck("T1", "Volvo FH", 3.5));
        garage.add(new ElectricCar("E1", "Tesla Model 3", 2.5));
        return garage;
    }

    private static void repairFlow(Garage garage, boolean withDiscount) {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine().trim();

        Vehicle v = garage.findById(id);
        if (v == null) {
            System.out.println("Not found.");
            return;
        }

        double cost;
        if (withDiscount) {
            System.out.print("Discount (0.0..0.5): ");
            double d;
            try {
                d = Double.parseDouble(scanner.nextLine().trim());
            } catch (Exception e) {
                d = 0;
            }
            if (d < 0)
                d = 0;
            if (d > 0.5)
                d = 0.5;
            cost = v.repair(d);
        } else {
            cost = v.repair();
        }
        System.out.printf("Repaired %s %s for %.2f%n",
                v.getClass().getSimpleName(), v.getId(), cost);
    }

    private static void showRepaired(Garage garage) {
        List<Vehicle> done = garage.repaired();
        if (done.isEmpty()) {
            System.out.println("No vehicles repaired yet.");
            return;
        }
        for (Vehicle v : done) {
            System.out.printf("%s %s -> cost: %.2f%n",
                    v.getClass().getSimpleName(), v.getId(), v.getLastRepairCost());
        }
        System.out.printf("Total: %.2f%n", garage.totalRepairCosts());
    }
}
