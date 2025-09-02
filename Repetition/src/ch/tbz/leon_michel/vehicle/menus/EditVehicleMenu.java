package ch.tbz.leon_michel.vehicle.menus;


import ch.tbz.leon_michel.vehicle.Vehicle;
import ch.tbz.leon_michel.vehicle.VehicleRentalManager;

import java.util.Scanner;

public class EditVehicleMenu {
    private final VehicleRentalManager vehicleRentalManager;

    public EditVehicleMenu (VehicleRentalManager vehicleRentalManager){
        this.vehicleRentalManager = vehicleRentalManager;
        startMenu();
    }

    private void startMenu(){
        boolean isInMenu = true;
        do{
            printBannerMenu();
            switch (getInt()){
                case 0: isInMenu = false; break;
                case 1: getIndexMenu(); break;
                default: printAllVehicles();
            }
        } while(isInMenu);
    }

    private void getIndexMenu(){
        System.out.println("-------------------------------");
        System.out.println("Type the index of the vehicle");
        int index = getInt();
        try {
            checkIndex(index);
            editVehicleAttribute(index);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void printAllVehicles(){
        vehicleRentalManager.getVehicles().forEach(vehicle -> {
            System.out.println("--------------------------------------");
            System.out.println("Vehicle Brand:\t" + vehicle.getBrand());
            System.out.println("Vehicle Model:\t" + vehicle.getModel());
            System.out.println("Vehicle Price:\t" + vehicle.getPrice());
            System.out.println("--------------------------------------\n");
        });
    }

    private void printVehicle(int index){
        Vehicle vehicle = vehicleRentalManager.getVehicles().get(index);
        System.out.println("--------------------------------------");
        System.out.println("Vehicle Brand:\t" + vehicle.getBrand());
        System.out.println("Vehicle Model:\t" + vehicle.getModel());
        System.out.println("Vehicle Price:\t" + vehicle.getPrice());
        System.out.println("--------------------------------------\n");
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException{
        if(index >= vehicleRentalManager.getVehicles().size()){
            throw new IndexOutOfBoundsException();
        }
    }

    private void printBannerMenu(){
        System.out.println("***********************");
        System.out.println("    VEHICLE MANAGER    ");
        System.out.println("***********************");
        System.out.println("0. exit");
        System.out.println("1. Edit one vehicle");
        System.out.println("2. Print all vehicles");
    }

    private void editVehicleAttribute(int index){
        printBannerVehicle();
        switch (getInt()){
            case 0: printVehicle(index); break;
            case 1: setVehicleRentalManagerVehicleRequiredAge(index); break;
            case 2: setVehicleRentalManageVehiclePrice(index); break;
            case 3: setVehicleRentalManagerVehicleMaxLeaseLengthInDays(index); break;
            case 4: setVehicleRentalManagerVehicleInsurance(index); break;
            default: break;
        }
    }

    private void printBannerVehicle(){
        System.out.println("0. print vehicle");
        System.out.println("1. set required age");
        System.out.println("2. set price");
        System.out.println("3. set new maximum lease days limit");
        System.out.println("4. set new insurance company");
    }

    private void setVehicleRentalManagerVehicleRequiredAge(int index){
        System.out.println("What should be the new required age?");
        vehicleRentalManager.getVehicles().get(index).setRequiredAge(getInt());
    }

    private void setVehicleRentalManageVehiclePrice(int index){
        System.out.println("What should be the new price?");
        vehicleRentalManager.getVehicles().get(index).setPrice(getDouble());
    }

    private void setVehicleRentalManagerVehicleMaxLeaseLengthInDays(int index){
        System.out.println("What should be the new maximum lease days limit?");
        vehicleRentalManager.getVehicles().get(index).setMaxLeaseLengthInDays(getInt());
    }

    private void setVehicleRentalManagerVehicleInsurance(int index){
        Scanner input = new Scanner(System.in);
        System.out.println("What should be the new insurance?");
        vehicleRentalManager.getVehicles().get(index).setInsuranceCompany(input.nextLine());
    }

    private int getInt(){
        Scanner input = new Scanner(System.in);
        int number = -1;
        while(number < 0){
            try {
                System.out.println("number must be at least 0");
                number = input.nextInt();
                input.nextLine();
            } catch(Exception exception){
                System.out.println("type in a number");
                input.nextLine();
            }
        }
        return number;
    }

    private double getDouble(){
        Scanner input = new Scanner(System.in);
        double number = -1;
        System.out.println("number must be at least 0");
        while(number < 0){
            try {
                number = input.nextDouble();
                input.nextLine();
            } catch(Exception exception){
                System.out.println("type in a double value");
                input.nextLine();
            }
        }
        return number;
    }
}
