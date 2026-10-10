
import java.util.*;

class CampusVehiclePass {

    interface Chargeable {
        void charge();
    }

    static class Vehicle {
        String passNumber;
        String owner;
        String type;
        int fee;

        Vehicle(String type, String passNumber, String owner, int fee) {
            this.type = type;
            this.passNumber = passNumber;
            this.owner = owner;
            this.fee = fee;
        }

        void displayPass() {
            System.out.println(
                passNumber + " (" + type + ") pass fee " + fee);
        }
    }

    static class Bike extends Vehicle {
        Bike(String pass, String owner) {
            super("Bike", pass, owner, 300);
        }
    }

    static class Car extends Vehicle {
        Car(String pass, String owner) {
            super("Car", pass, owner, 1000);
        }
    }

    static class EBike extends Vehicle implements Chargeable {
        EBike(String pass, String owner) {
            super("EBike", pass, owner, 300);
        }

        public void charge() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    static class ECar extends Vehicle implements Chargeable {
        ECar(String pass, String owner) {
            super("ECar", pass, owner, 1000);
        }

        public void charge() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equalsIgnoreCase("PASS")) {
                String type = p[1];
                String pass = p[2];
                String owner = p[3];

                Vehicle v;

                switch (type.toUpperCase().replace("-", "")) {
                    case "BIKE":
                        v = new Bike(pass, owner);
                        break;
                    case "CAR":
                        v = new Car(pass, owner);
                        break;
                    case "EBIKE":
                        v = new EBike(pass, owner);
                        break;
                    case "ECAR":
                        v = new ECar(pass, owner);
                        break;
                    default:
                        continue;
                }

                vehicles.put(pass, v);
                v.displayPass();

            } else if (p[0].equalsIgnoreCase("CHARGE")) {
                Vehicle v = vehicles.get(p[1]);

                if (v == null) {
                    System.out.println("Vehicle not found");
                } else if (v instanceof Chargeable) {
                    ((Chargeable) v).charge();
                } else {
                    System.out.println(
                        p[1] + " rejected: charging unsupported");
                }
            }
        }

        sc.close();
    }
}
