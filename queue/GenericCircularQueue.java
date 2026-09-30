package Lecture.Queue;

class GenericCircularQueue<T> {
    private T[] queue;
    private int front, rear, size, capacity;

    @SuppressWarnings("unchecked")
    public GenericCircularQueue(int capacity) {
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
        rear = (rear + 1) % capacity; // Rear'ı dairesel şekilde artır
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
        front = (front + 1) % capacity; // Front'u dairesel şekilde artır
        size--;
        return removedValue;
    }

    // Kuyruk durumunu yazdır
    public void display() {
        if (size == 0) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.print("Circular Queue: ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(queue[index] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GenericCircularQueue<Integer> cq = new GenericCircularQueue<>(5);
        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.display();  // 10 20 30
        cq.dequeue();
        cq.display();  // 20 30
        
        GenericCircularQueue<String> cq2 = new GenericCircularQueue<>(3);
        cq2.enqueue("Apple");
        cq2.enqueue("Banana");
        cq2.display();  // Apple Banana
    }
}

