
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    // Add token at the beginning
    void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
    }

    // Add token at the end
    void addLast(int token) {
        Node newNode = new Node(token);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    // Insert token at a given index
    void insertAt(int index, int token) {
        if (index < 0) {
            System.out.println("Invalid index");
            return;
        }

        if (index == 0) {
            addFirst(token);
            return;
        }

        Node temp = head;

        for (int i = 0; i < index - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Invalid index");
            return;
        }

        Node newNode = new Node(token);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Display the linked list
    void display() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.print("Enter number of starting tokens: ");
        int n = sc.nextInt();

        System.out.println("Enter the starting tokens:");
        for (int i = 0; i < n; i++) {
            list.addLast(sc.nextInt());
        }

        System.out.print("Enter number of operations: ");
        int operations = sc.nextInt();

        for (int i = 0; i < operations; i++) {
            System.out.println("1. addFirst  2. addLast  3. insertAt");
            int choice = sc.nextInt();

            if (choice == 1) {
                int token = sc.nextInt();
                list.addFirst(token);
            } else if (choice == 2) {
                int token = sc.nextInt();
                list.addLast(token);
            } else if (choice == 3) {
                int index = sc.nextInt();
                int token = sc.nextInt();
                list.insertAt(index, token);
            } else {
                System.out.println("Invalid operation");
            }
        }

        System.out.println("Final linked list:");
        list.display();

        sc.close();
    }
}
