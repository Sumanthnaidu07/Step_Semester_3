abstract class Vehicle {
    protected String vehicleId;
    protected String vehicleName;
    protected boolean available;

    Vehicle(String vehicleId, String vehicleName) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rentVehicle() {
        available = false;
    }

    public void returnVehicle() {
        available = true;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {

    Sedan(String vehicleId, String vehicleName) {
        super(vehicleId, vehicleName);
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {

    SUV(String vehicleId, String vehicleName) {
        super(vehicleId, vehicleName);
    }

    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    Vehicle vehicle;
    Customer customer;
    int days;

    Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public void rent() {
        if (vehicle.isAvailable()) {
            vehicle.rentVehicle();

            System.out.println(vehicle.vehicleName +
                    " rented successfully by " + customer.name);

            System.out.println("Rental charge: $" +
                    vehicle.calculateCharge(days));
        } else {
            System.out.println(vehicle.vehicleName +
                    " is currently unavailable.");
        }
    }

    public void returnVehicle() {
        vehicle.returnVehicle();

        System.out.println(vehicle.vehicleName +
                " returned by " + customer.name);
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {

        Sedan sedanA = new Sedan("S1", "Sedan A");
        SUV suvB = new SUV("S2", "SUV B");

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Rental rental1 =
                new Rental(sedanA, customer1, 3);

        rental1.rent();

        Rental rental2 =
                new Rental(sedanA, customer2, 2);

        rental2.rent();

        rental1.returnVehicle();

        Rental rental3 =
                new Rental(suvB, customer3, 5);

        rental3.rent();
    }
}