package ch.tbz.leon_michel.vehicle;

import ch.tbz.leon_michel.vehicle.menus.*;
import ch.tbz.leon_michel.vehicle.vehicles.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        VehicleRentalManager vehicleRentalManager = new VehicleRentalManager();
        Person michel = new Person("Michel", "Mahadeva", LocalDate.parse("1990-06-23"));
        Person nico = new Person("Nico", "Linder", LocalDate.parse("2008-11-02"));
        Person goncalo = new Person("Gonçalo", "De Almeida", LocalDate.parse("2008-11-02"));
        insertVehicles(vehicleRentalManager);
        vehicleRentalManager.addPersonToDenyList(nico);
        vehicleRentalManager.createContract(vehicleRentalManager.getVehicles().get(1), michel,
                LocalDate.parse("2025-01-15"), LocalDate.parse("2025-05-04"));

        EditVehicleMenu editVehicleMenu = new EditVehicleMenu(vehicleRentalManager);
        //EditPeopleMenu editPeopleMenu = new EditPeopleMenu(vehicleRentalManager);
    }





    private static void insertVehicles(VehicleRentalManager vehicleRentalManager) {
        //campers
        vehicleRentalManager.addVehicle(new Camper(25, 15, 452.35,
                "", "Hymermobil", 8, "ZH 841 962", "Zurich Insurance",
                "1FTSX31F4XEF01530", 5));

        vehicleRentalManager.addVehicle(new Camper(25, 20, 356.25,
                "California", "VW", 6, "ZH 562 746", "Zurich Insurance",
                "4USCJ3335WLG20199", 3));
        //combis
        vehicleRentalManager.addVehicle(new Combi(18, 350, 53.52,
                "corsa", "Opel", 5, "ZH 845 621","Zurich Insurance",
                "5GAKVCKD0FJ166700", true));

        vehicleRentalManager.addVehicle(new Combi(18, 350, 65.40,
                "500", "Fiat", 5, "ZH 556 215","Zurich Insurance",
                "JTEBU5JR1E5151796", false));

        vehicleRentalManager.addVehicle(new Combi(18, 350, 70.42,
                "I10", "Hyundai", 5, "ZH 655 287","Zurich Insurance",
                "2C4GP54L35R581762", true));

        vehicleRentalManager.addVehicle(new Combi(18, 350, 57.65,
                "Swift", "Suzuki", 5, "ZH 982 741*","Zurich Insurance",
                "3C63RRKLXFG568024", true));

        //luxury cars
        vehicleRentalManager.addVehicle(new LuxuryCar(21, 15, 250.32,
                "S-Class", "Mercedes", 5, "ZH 7777",
                "Zurich Insurance", "WDB12345678901234", false, 1));

        vehicleRentalManager.addVehicle(new LuxuryCar(18, 15, 90.5,
                "Z4 cabriolet", "BMW4", 5, "ZH 8544", "Basler Insurance",
                "5NPEC4ACXDH588995", false, 1));

        vehicleRentalManager.addVehicle(new LuxuryCar(25, 60, 185.65,
                "MX5 cabriolet", "Mazda", 5, "ZH 336 372", "Mobiliar Insurance",
                "JT2JA82JXS0048140", true, 1));


        vehicleRentalManager.addVehicle(new LuxuryCar(21, 15, 18.52,
                "18", "BMW", 5, "ZH 59624", "Swiss Life Insurance",
                "5J8TB3H39DL002200", false, 1));

        //lorries
        vehicleRentalManager.addVehicle(new Lorry(22, 300, 620.35, "Vita",
                "Mercedes", 3, "ZH 849 515", "Swiss Life Insurance",
                "2G1WH55K729280981", 35));

        vehicleRentalManager.addVehicle(new Lorry(20, 450, 362.54, "Crafter",
                "VW", 3, "ZH 956 852", "Mobiliar Insurance",
                "1G6DS5EV7A0185780", 70));

        vehicleRentalManager.addVehicle(new Lorry(18, 150, 56.54, "daily",
                "Iveco", 2, "ZH 745 212", "Basler Insurance",
                "3LN6L2G92ER871910", 20));

        vehicleRentalManager.addVehicle(new Lorry(25, 150, 478.56, "e'Crafter",
                "VW", 2, "ZH 687 254", "Zurich Insurance",
                "2GCEK13C371742533", 15));

        //Trailers
        vehicleRentalManager.addVehicle(new Trailer(18, 45, 20.65, "Hochlader",
                "Böckmann", 0, "ZH 545 965", "Zurich Insurance",
                "1FMYU70E82UC02846", 25.2, true));

        vehicleRentalManager.addVehicle(new Trailer(18, 45, 20.65, "Bootsanhänger",
                "Böckmann", 0, "ZH 682 965", "Zurich Insurance",
                "1J4GX48S64C151575", 11, false));

        vehicleRentalManager.addVehicle(new Trailer(18, 45, 20.65, "Tieflader",
                "Böckmann", 0, "ZH 545 568", "Zurich Insurance",
                "1HGCG56672A150347", 5, false));
    }

}