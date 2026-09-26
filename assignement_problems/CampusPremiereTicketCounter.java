import java.util.*;

interface Seat {
    String getSeatNumber();
    double getPrice();
    String getCategory();
}

class RegularSeat implements Seat {

    private String seatNumber;

    public RegularSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return 150;
    }

    public String getCategory() {
        return "Regular";
    }
}

class PremiumSeat implements Seat {

    private String seatNumber;

    public PremiumSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return 250;
    }

    public String getCategory() {
        return "Premium";
    }
}

class ReclinerSeat implements Seat {

    private String seatNumber;

    public ReclinerSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return 400;
    }

    public String getCategory() {
        return "Recliner";
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Show {

    private String showTime;
    private boolean started;

    private Set<String> bookedSeats = new HashSet<>();

    public Show(String showTime) {
        this.showTime = showTime;
        this.started = false;
    }

    public boolean isSeatAvailable(Seat seat) {
        return !bookedSeats.contains(seat.getSeatNumber());
    }

    public boolean bookSeat(Seat seat) {

        if (!isSeatAvailable(seat)) {
            return false;
        }

        bookedSeats.add(seat.getSeatNumber());
        return true;
    }

    public void releaseSeat(Seat seat) {
        bookedSeats.remove(seat.getSeatNumber());
    }

    public void startShow() {
        started = true;
    }

    public boolean hasStarted() {
        return started;
    }

    public String getShowTime() {
        return showTime;
    }
}

class Booking {

    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled;

    public Booking(
        Customer customer,
        Show show,
        List<Seat> seats
    ) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.cancelled = false;
    }

    public double calculateTotal() {

        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel() {

        if (cancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        if (show.hasStarted()) {
            System.out.println(
                "Cannot cancel: show has already started."
            );
            return;
        }

        for (Seat seat : seats) {
            show.releaseSeat(seat);
        }

        cancelled = true;

        System.out.println(
            customer.getName() +
            "'s booking cancelled."
        );

        System.out.print("Seats released: ");

        for (Seat seat : seats) {
            System.out.print(
                seat.getSeatNumber() + " "
            );
        }

        System.out.println();
    }
}

public class CampusPremiereTicketCounter {

    public static Booking createBooking(
        Customer customer,
        Show show,
        Seat... seats
    ) {

        if (seats.length > 6) {
            System.out.println(
                "Cannot book more than 6 seats."
            );
            return null;
        }

        for (Seat seat : seats) {

            if (!show.isSeatAvailable(seat)) {

                System.out.println(
                    "Seat " +
                    seat.getSeatNumber() +
                    " is already booked for this show."
                );

                return null;
            }
        }

        List<Seat> selectedSeats =
            new ArrayList<>();

        for (Seat seat : seats) {
            show.bookSeat(seat);
            selectedSeats.add(seat);
        }

        Booking booking =
            new Booking(customer, show, selectedSeats);

        System.out.print(
            "Booking confirmed for " +
            customer.getName() + ": "
        );

        for (Seat seat : seats) {
            System.out.print(
                seat.getSeatNumber() + " "
            );
        }

        System.out.printf(
            "%nTotal: ₹%.2f%n",
            booking.calculateTotal()
        );

        return booking;
    }

    public static void main(String[] args) {

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        // Asha books A1, A2 and F5
        Booking ashaBooking =
            createBooking(
                asha,
                show,
                a1,
                a2,
                f5
            );

        // Ravi tries A2
        createBooking(
            ravi,
            show,
            a2
        );

        // Ravi books R1
        createBooking(
            ravi,
            show,
            r1
        );

        // Asha cancels
        ashaBooking.cancel();

        // Neha books A2
        createBooking(
            neha,
            show,
            a2
        );
    }
}