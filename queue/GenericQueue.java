package Lecture.Queue;

class GenericQueue<T> {
    private T[] queue;
    private int front, rear, size, capacity;

    @SuppressWarnings("unchecked")
    public GenericQueue(int capacity) {
        this.capacity = capacity;
        queue = (T[]) new Object[capacity]; // Generic array oluşturma
        front = 0;
        rear = -1;
        size = 0;
    }

    // Eleman ekleme (enqueue)
    public void enqueue(T value) {
        if (size == capacity) {
            System.out.println("Queue is full!");
            return;
        }
        rear++;
        queue[rear] = value;
        size++;
    }

    // Eleman çıkarma (dequeue)
    public T dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty!");
            return null;
        }
        T removedValue = queue[front];
        front++; // Front'u bir sonraki elemana kaydır
        size--;
        return removedValue;
    }

    // Kuyruk durumunu yazdır
    public void display() {
        if (size == 0) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.print("Queue: ");
        for (int i = front; i <= rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GenericQueue<Integer> q = new GenericQueue<>(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();  // 10 20 30
        q.dequeue();
        q.display();  // 20 30
        
        GenericQueue<String> q2 = new GenericQueue<>(3);
        q2.enqueue("Ali");
        q2.enqueue("Veli");
        q2.display();  // Ali Veli
    }
}
