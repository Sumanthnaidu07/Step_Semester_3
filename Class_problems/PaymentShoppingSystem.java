import java.util.*;

class Product {

    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class OrderItem {

    Product product;
    int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.price * quantity;
    }
}

interface PaymentMethod {

    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {

    public boolean processPayment(double amount) {

        System.out.println(
                "Processing Credit Card payment...");

        return true;
    }
}

class PayPalPayment implements PaymentMethod {

    public boolean processPayment(double amount) {

        System.out.println(
                "Processing PayPal payment...");

        return false;
    }
}

class BankTransferPayment implements PaymentMethod {

    public boolean processPayment(double amount) {

        System.out.println(
                "Processing Bank Transfer...");

        return true;
    }
}

class Customer {

    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Order {

    Customer customer;

    ArrayList<OrderItem> items =
            new ArrayList<>();

    String status = "Pending";

    Order(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(
            Product product,
            int quantity) {

        items.add(
                new OrderItem(product, quantity));
    }

    public double calculateTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void pay(PaymentMethod paymentMethod) {

        if (items.isEmpty()) {

            System.out.println(
                    "Cannot process payment for an empty order.");

            return;
        }

        double amount = calculateTotal();

        System.out.println(
                "Payment initiated for Order of "
                + customer.name);

        boolean success =
                paymentMethod.processPayment(amount);

        if (success) {

            status = "Paid";

            System.out.println(
                    "Payment successful.");

        } else {

            System.out.println(
                    "Payment failed.");
        }

        System.out.println(
                "Order status: " + status);
    }
}

public class PaymentShoppingSystem {

    public static void main(String[] args) {

        Product productA =
                new Product("Product A", 100);

        Product productB =
                new Product("Product B", 200);

        Product productC =
                new Product("Product C", 300);

        Customer customerX =
                new Customer("Customer X");

        Customer customerY =
                new Customer("Customer Y");

        Customer customerZ =
                new Customer("Customer Z");

        // Customer X
        Order orderX =
                new Order(customerX);

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println(
                "Order created for Customer X.");

        orderX.pay(
                new CreditCardPayment());

        // Customer Y
        System.out.println();

        Order orderY =
                new Order(customerY);

        System.out.println(
                "Order created for Customer Y.");

        orderY.pay(
                new CreditCardPayment());

        // Customer Z
        System.out.println();

        Order orderZ =
                new Order(customerZ);

        orderZ.addProduct(productC, 1);

        System.out.println(
                "Order created for Customer Z.");

        orderZ.pay(
                new PayPalPayment());
    }
}