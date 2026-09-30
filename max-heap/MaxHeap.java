//Furkan Kumru    22050111006

package MaxHeap;

import java.util.ArrayList;
import java.util.Random;

class Event {
    int priority;
    int lifespan;

    public Event(int priority, int lifespan) {
        this.priority = priority;
        this.lifespan = lifespan;
    }
    
    public String toString() {
        return "| Priority: " + priority + ", Lifespan: " + lifespan + " |";
    }

}

public class MaxHeap {
    private ArrayList<Event> heap;
    private int heapSize;

    public MaxHeap() {
        heap = new ArrayList<>();
        heapSize = 0;
    }
    
    private int getParentIndex(int pos){
        return (pos - 1) / 2;
    }

    private int getLeftChildIndex(int pos){
        return (2 * pos) + 1;
    }
    
    private int getRightChildIndex(int pos){
        return (2 * pos) + 2;
    }

    private void swap(int fpos, int spos) {
        Event temp = heap.get(fpos);
        heap.set(fpos, heap.get(spos));
        heap.set(spos, temp);
    }
    
    private void heapifyUp(int pos) {
        int parent = getParentIndex(pos);

        if (pos > 0 && heap.get(pos).priority > heap.get(parent).priority) {
            swap(pos, parent);
            heapifyUp(parent);
        }
    }
    
    private void heapifyDown(int pos) {
        if (!isLeaf(pos)) {
            int left = getLeftChildIndex(pos);
            int right = getRightChildIndex(pos);
            
            if ((left < heapSize && heap.get(pos).priority < heap.get(left).priority) ||
                (right < heapSize && heap.get(pos).priority < heap.get(right).priority)) {
                
                if (right < heapSize && heap.get(right).priority > heap.get(left).priority) {
                    swap(pos, right);
                    heapifyDown(right);
                } 
                else {
                    swap(pos, left);
                    heapifyDown(left);
                }
            }
        }
    }
    
    private boolean isLeaf(int pos) {
        return pos >= (heapSize / 2) && pos < heapSize;
    }
    
    public void insert (int priority, int lifespan){
        Event newEvent = new Event(priority, lifespan);
        heap.add(newEvent);
        heapSize++;
        heapifyUp(heapSize - 1);
    }
    
    public Event peekMax() {
        if (heapSize == 0) 
            return null;
        return heap.get(0);
    }
    
    public Event extractMax() {
        Event max = peekMax();
        if (max == null) {
            return null;
        }
        swap(0, heapSize - 1);
        heap.remove(heapSize - 1);
        heapSize--;
        heapifyDown(0);
        
        for (int i = 0; i < heapSize; i++) {
            heap.get(i).lifespan--;
        }

        removeExpiredEvents();
        return max;
    }
    
    private void removeExpiredEvents() {
        int i = 0;
        while (i < heapSize) {
            if (heap.get(i).lifespan <= 0) {
                swap(i, heapSize - 1);
                heap.remove(heapSize - 1);
                heapSize--;
                if (i < heapSize) {
                    heapifyDown(i);
                    heapifyUp(i);
                }
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            int priority = rand.nextInt(100) + 1; 
            int lifespan = rand.nextInt(5) + 1;   
            heap.insert(priority, lifespan);
        }

        for (int i = 1; i <= 10; i++) {
            Event max = heap.peekMax();
            if (max != null) {
                System.out.println("Step " + i + " - Extracted: " + max);
                heap.extractMax();
            } else {
                System.out.println("Step " + i + " - Heap is empty.");
                break;
            }

            System.out.println("Current Heap:");
            for (int j = 0; j < heap.heapSize; j++) {
                Event e = heap.heap.get(j);
                System.out.println(e);
            }
            System.out.println("\n---------------------------\n");
        }
    }
}
