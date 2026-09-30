package Lecture.LinkedList;

/**
 * Represents a single node in a linked list.
 * Each node contains data and a reference to the next node.
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
 * A simple LinkedList implementation with basic operations:
 * - Add elements to the end
 * - Print the list
 * - Merge two lists
 */
class LinkedList {
    Node head; // Head (first node) of the list
    
    // Adds a new node with given data to the end of the list
    public void add(int data) {
        Node newNode = new Node(data);
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
    
    // Prints the list in format: 1 -> 2 -> 3 -> null
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
    
    /**
     * Merges two linked lists by connecting the end of the first list
     * to the head of the second list.
     * @param l1 First linked list
     * @param l2 Second linked list
     * @return Merged list (l1 + l2)
     */
    public static LinkedList merge(LinkedList l1, LinkedList l2) {
        if (l1.head == null) return l2;
        if (l2.head == null) return l1;
        
        Node temp = l1.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = l2.head; 
        return l1;
    }
}

// Demo class to test LinkedList merging functionality
public class MergeLinkedList {
    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        
        LinkedList list2 = new LinkedList();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        
        System.out.println("List 1:");
        list1.printList();
        
        System.out.println("List 2:");
        list2.printList();
        
        LinkedList mergedList = LinkedList.merge(list1, list2);
        
        System.out.println("Merged List:");
        mergedList.printList();
    }
}