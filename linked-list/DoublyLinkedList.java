package Lecture.LinkedList;

/**
 * A generic doubly linked list implementation with insert/delete operations at any position.
 * Supports any data type through Java Generics.
 */
class DoublyLinkedList<T> {
    /**
     * Node class representing elements in the doubly linked list.
     * Contains data and references to previous/next nodes.
     */
    class Node {
        T data;
        Node prev, next;

        Node(T data) {
            this.data = data;
            this.prev = this.next = null;
        }
    }

    private Node head; // Head (first node) of the list

    /**
     * Inserts new data at specified position (1-based index)
     * @param data The element to insert
     * @param position The target position (1 = head)
     */
    void insertAtPosition(T data, int position) {
        Node newNode = new Node(data);

        if (head == null) { 
            head = newNode;
            return;
        }

        if (position == 1) { 
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }

        Node temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds");
            return;
        }

        newNode.next = temp.next;
        if (temp.next != null) { 
            temp.next.prev = newNode;
        }
        temp.next = newNode;
        newNode.prev = temp;
    }

    /**
     * Deletes node at specified position (1-based index)
     * @param position The position to remove
     */
    void deleteAtPosition(int position) {
        if (head == null || position < 1) {
            System.out.println("List is empty or invalid position");
            return;
        }

        Node temp = head;

        if (position == 1) { 
            head = temp.next;
            if (head != null) {
                head.prev = null;
            }
            return;
        }

        for (int i = 1; temp != null && i < position; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds");
            return;
        }

        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }

        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
    }

    /** Prints the list in format: A <-> B <-> C <-> NULL */
    void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        // Integer list demo
        DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();
        intList.insertAtPosition(10, 1);
        intList.insertAtPosition(20, 2);
        intList.insertAtPosition(30, 3);
        intList.insertAtPosition(15, 2);
        intList.display(); // 10 <-> 15 <-> 20 <-> 30 <-> NULL
        intList.deleteAtPosition(3);
        intList.display(); // 10 <-> 15 <-> 30 <-> NULL

        // String list demo
        DoublyLinkedList<String> strList = new DoublyLinkedList<>();
        strList.insertAtPosition("A", 1);
        strList.insertAtPosition("B", 2);
        strList.insertAtPosition("C", 3);
        strList.insertAtPosition("D", 2);
        strList.display(); // A <-> D <-> B <-> C <-> NULL
        strList.deleteAtPosition(3);
        strList.display(); // A <-> D <-> C <-> NULL
    }
}