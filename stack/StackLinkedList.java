package Lecture.Stack;

class StackLinkedList<T> {
    class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;

    StackLinkedList() {
        this.top = null;
    }

    
    void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    T pop() {
        if (top == null) {
            System.out.println("Stack Underflow");
            return null;
        }
        T poppedData = top.data;
        top = top.next;
        return poppedData;
    }

    boolean isEmpty() {
        return top == null;
    }

    void printStack() {
        if (top == null) {
            System.out.println("Stack Empty");
            return;
        }
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StackLinkedList<Integer> stack = new StackLinkedList<>();

        stack.push(5);
        stack.printStack();
        stack.push(6);
        stack.push(7);
        stack.printStack();
        stack.pop();
        stack.printStack();
        stack.pop();
        stack.printStack();
        stack.pop();
        System.out.println("Is empty? " + stack.isEmpty());
    }
}
