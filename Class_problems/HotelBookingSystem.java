import java.util.*;

abstract class Room {

    protected int roomNumber;
    protected boolean available;

    Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.available = true;
    }

    public abstract double calculatePrice(int days);

    public int getRoomNumber() {
        return roomNumber;
    }
}

class StandardRoom extends Room {

    StandardRoom(int roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {

    DeluxeRoom(int roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * 180;
    }
}

class Customer {

    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {

    Customer customer;
    Room room;
    int startDay;
    int endDay;
    boolean active;

    Reservation(
            Customer customer,
            Room room,
            int startDay,
            int endDay) {

        this.customer = customer;
        this.room = room;
        this.startDay = startDay;
        this.endDay = endDay;
        this.active = true;
    }

    public boolean overlaps(int newStart, int newEnd) {

        return newStart < endDay &&
               newEnd > startDay;
    }

    public void cancel() {

        if (active) {
            active = false;

            System.out.println(
                    "Reservation for " +
                    customer.name +
                    ", Room " +
                    room.getRoomNumber() +
                    " cancelled successfully.");
        }
    }
}

class Hotel {

    ArrayList<Reservation> reservations =
            new ArrayList<>();

    public boolean isAvailable(
            Room room,
            int startDay,
            int endDay) {

        for (Reservation r : reservations) {

            if (r.active &&
                r.room == room &&
                r.overlaps(startDay, endDay)) {

                return false;
            }
        }

        return true;
    }

    public Reservation bookRoom(
            Customer customer,
            Room room,
            int startDay,
            int endDay) {

        if (!isAvailable(room, startDay, endDay)) {

            System.out.println(
                    "Room " +
                    room.getRoomNumber() +
                    " is not available.");

            return null;
        }

        Reservation reservation =
                new Reservation(
                        customer,
                        room,
                        startDay,
                        endDay);

        reservations.add(reservation);

        int days = endDay - startDay;

        System.out.println(
                "Reservation confirmed for " +
                customer.name +
                ", Room " +
                room.getRoomNumber());

        System.out.println(
                "Price: $" +
                room.calculatePrice(days));

        return reservation;
    }
}

public class HotelBookingSystem {

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Customer customerA =
                new Customer("Customer A");

        Customer customerB =
                new Customer("Customer B");

        Customer customerC =
                new Customer("Customer C");

        StandardRoom room101 =
                new StandardRoom(101);

        DeluxeRoom room201 =
                new DeluxeRoom(201);

        System.out.println(
                "Checking Room 101 from Jan 1 to Jan 5:");

        if (hotel.isAvailable(room101, 1, 5)) {
            System.out.println(
                    "Standard Room 101 is available.");
        }

        Reservation reservation =
                hotel.bookRoom(
                        customerA,
                        room101,
                        1,
                        5);

        System.out.println(
                "\nCustomer B tries Jan 3 to Jan 7:");

        hotel.bookRoom(
                customerB,
                room101,
                3,
                7);

        System.out.println("\nCustomer A cancels:");

        if (reservation != null) {
            reservation.cancel();
        }

        System.out.println("\nCustomer C books Deluxe Room:");

        hotel.bookRoom(
                customerC,
                room201,
                10,
                12);
    }
}