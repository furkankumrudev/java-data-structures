package Lecture.LinkedList;

/**
 * Represents a node in a circular linked list.
 * Contains data and a reference to the next node.
 */
class Node {
    int data;
    Node next;
    
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * Implementation of a circular linked list where:
 * - The tail node points back to the head
 * - Supports basic insertion and traversal
 */
class CircularLinkedList {
    private Node head = null; // First node in the list
    private Node tail = null; // Last node in the list
    
    /**
     * Adds a new node with given data to the end of the list
     * @param data The integer value to be added
     */
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }
    
    /**
     * Displays all nodes in the circular list 
     * Format: 10 -> 20 -> 30 -> (head)
     */
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(head)");
    }
    
    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.add(10);
        cll.add(20);
        cll.add(30);
        cll.add(40);
        cll.display();
    }
}
