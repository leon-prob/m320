package ch.tbz.leon_michel.vehicle.menus;

import ch.tbz.leon_michel.vehicle.Person;
import ch.tbz.leon_michel.vehicle.VehicleRentalManager;

import java.time.Period;
import java.util.Scanner;

public class EditPeopleMenu {

    private final VehicleRentalManager vehicleRentalManager;

    public EditPeopleMenu (VehicleRentalManager vehicleRentalManager){
        this.vehicleRentalManager = vehicleRentalManager;
        startMenu();
    }

    private void startMenu(){
        boolean isInMenu = true;
        do{
            printBannerMenu();
            switch (getInt()){
                case 0: isInMenu = false; break;
                case 1: editPerson(getRightPerson(false)); break;
                case 2: editPerson(getRightPerson(true)); break;
                case 3: vehicleRentalManager.getDenyList().forEach(this::printPerson); break;
                default: vehicleRentalManager.getCustomerList().forEach(this::printPerson);
            }
        } while(isInMenu);
    }

    private void printBannerMenu(){
        System.out.println("***********************");
        System.out.println("    CUSTOMER MANAGER    ");
        System.out.println("***********************");
        System.out.println("0. exit");
        System.out.println("1. Edit person in deny list");
        System.out.println("2. Edit person in customer list");
        System.out.println("3. Print deny list");
        System.out.println("4. Print customer list");
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

    private void printPerson(Person person){
        System.out.println("--------------------------------------");
        System.out.println("Name:\t" + person.getFirstName() + " " + person.getLastName());
        System.out.println("Age:\t" + person.getAge());
        System.out.println("--------------------------------------\n");
    }

    private Person getRightPerson(boolean isCustomer){
        while (true) {
            System.out.println("-------------------------------");
            System.out.println("Type the index of the person");
            int index = getInt();
            try {
                checkIndex(index, isCustomer);
                if (isCustomer) {
                    return vehicleRentalManager.getCustomerList().get(index);
                } else {
                    return vehicleRentalManager.getDenyList().get(index);
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void checkIndex(int index, boolean isCustomer) throws IndexOutOfBoundsException{
        if(isCustomer){
            if(index >= vehicleRentalManager.getCustomerList().size()){
                throw new IndexOutOfBoundsException();
            }
        } else {
            if(index >= vehicleRentalManager.getDenyList().size()){
                throw new IndexOutOfBoundsException();
            }
        }

    }

    private void editPerson(Person person){
        printEditPersonBanner();
        switch(getInt()){
            case 0:  printPerson(person); break;
            case 1:  break;
            case 2:  break;
            case 3:  break;
            default: break;
        }
    }

    private void printEditPersonBanner(){
        System.out.println("0. print Person");
        System.out.println("1. set first name");
        System.out.println("2. set last name");
        System.out.println("3. set birthdate");
    }
}
