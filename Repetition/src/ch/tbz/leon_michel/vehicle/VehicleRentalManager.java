package ch.tbz.leon_michel.vehicle;

import ch.tbz.leon_michel.vehicle.exceptions.DenyListedPersonException;
import ch.tbz.leon_michel.vehicle.exceptions.LeaseLengthCollisionException;
import ch.tbz.leon_michel.vehicle.exceptions.MinorAgeException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class VehicleRentalManager {
    private final ArrayList<Person> customerList;
    private final ArrayList<Person> denyList;
    private final ArrayList<Vehicle> vehicles;
    private final ArrayList<Contract> contracts;

    public VehicleRentalManager(){
        customerList = new ArrayList<>();
        denyList = new ArrayList<>();
        vehicles = new ArrayList<>();
        contracts = new ArrayList<>();
    }

    public void addPersonToDenyList(Person person){
        customerList.remove(person);
        if(!denyList.contains(person)){
            denyList.add(person);
        }
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public void createContract(Vehicle vehicle, Person person, LocalDate startDate, LocalDate endDate) {
        try{
            checkContract(vehicle, person, startDate, endDate);
            contracts.add(new Contract(person, vehicle, startDate, endDate));
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void checkContract (Vehicle vehicle, Person person, LocalDate startDate, LocalDate endDate) throws Exception{
        if(denyList.contains(person)){
            throw new DenyListedPersonException();
        }  else if(person.getAge() < vehicle.getRequiredAge()){
            throw new MinorAgeException();
        } else if(startDate.isAfter(endDate)){
            throw new Exception("Your start date is after the end date");
        } else if(Period.between(startDate, endDate).getYears()*365 + Period.between(startDate, endDate).getMonths()*30
                + Period.between(startDate, endDate).getDays() > vehicle.getMaxLeaseLengthInDays()){
            throw new LeaseLengthCollisionException(vehicle.getMaxLeaseLengthInDays());
        } else if(!customerList.contains(person)){
            customerList.add(person);
        }
    }

    public ArrayList<Vehicle> getVehicles(){return vehicles;}
    public ArrayList<Contract> getContracts(){return contracts;}
    public ArrayList<Person> getCustomerList(){return customerList;}
    public ArrayList<Person> getDenyList(){return denyList;}

    public void editVehicle(int index, int editChoice){
        Vehicle activeVehicle = vehicles.get(index);
        switch (editChoice){
            
        }
    }
}
