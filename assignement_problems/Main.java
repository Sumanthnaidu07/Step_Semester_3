
import java.util.Scanner;

public class Main {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        Node head;

        // Insert at the beginning
        void addFirst(int token) {
            Node newNode = new Node(token);
            newNode.next = head;
            head = newNode;
        }

        // Insert at the end
        void addLast(int token) {
            Node newNode = new Node(token);

            if (head == null) {
                head = newNode;
                return;
            }

            Node current = head;
            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        // Insert at a specified 0-based index
        void insertAt(int index, int token) {
            if (index < 0) {
                return;
            }

            if (index == 0) {
                addFirst(token);
                return;
            }

            Node current = head;

            for (int i = 0; i < index - 1 && current != null; i++) {
                current = current.next;
            }

            if (current == null) {
                return;
            }

            Node newNode = new Node(token);
            newNode.next = current.next;
            current.next = newNode;
        }

        // Print the list
        void display() {
            Node current = head;

            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }

            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SinglyLinkedList list = new SinglyLinkedList();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            list.addLast(sc.nextInt());
        }

        int operations = sc.nextInt();

        for (int i = 0; i < operations; i++) {
            String operation = sc.next();

            switch (operation) {
                case "addFirst":
                    list.addFirst(sc.nextInt());
                    break;

                case "addLast":
                    list.addLast(sc.nextInt());
                    break;

                case "insertAt":
                    int index = sc.nextInt();
                    int token = sc.nextInt();
                    list.insertAt(index, token);
                    break;
            }
        }

        list.display();
        sc.close();
    }
}
