package Lecture.Sorting;

import java.util.Arrays;
import java.util.Random;

public class Main {
    
    /* -------------------- SELECTION SORT -------------------- */
    private static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }
    }

    /* -------------------- INSERTION SORT -------------------- */
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /* -------------------- SHELL SORT -------------------- */
    private static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i], j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    /* -------------------- BUBBLE SORT -------------------- */
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
    }

    /* -------------------- MERGE SORT -------------------- */
    private static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l;

        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    /* -------------------- QUICK SORT (RANDOM PIVOT) -------------------- */
    private static final Random RAND = new Random();

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = randomPartition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int randomPartition(int[] arr, int low, int high) {
        int randomIndex = low + RAND.nextInt(high - low + 1);
        swap(arr, randomIndex, high);
        return partition(arr, low, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) swap(arr, ++i, j);
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /* -------------------- RADIX SORT -------------------- */
    private static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSort(arr, exp);
    }

    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int value : arr) count[(value / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[--count[digit]] = arr[i];
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    /* -------------------- TIME MEASURE -------------------- */
    private static long measure(Runnable sort) {
        long start = System.nanoTime();
        sort.run();
        return (System.nanoTime() - start) / 1_000_000;
    }

    public static void main(String[] args) {

        Random rd = new Random();
        int size = 10000;

        for (int round = 1; round <= 4; round++) {

            int[] base = rd.ints(size, 0, 10000).toArray();

            System.out.println("----- Round " + round + " -----");

            System.out.printf("%-15s %d ms%n", "Selection Sort", measure(() -> selectionSort(Arrays.copyOf(base, size))));
            System.out.printf("%-15s %d ms%n", "Insertion Sort", measure(() -> insertionSort(Arrays.copyOf(base, size))));
            System.out.printf("%-15s %d ms%n", "Shell Sort",     measure(() -> shellSort(Arrays.copyOf(base, size))));
            System.out.printf("%-15s %d ms%n", "Bubble Sort",    measure(() -> bubbleSort(Arrays.copyOf(base, size))));
            System.out.printf("%-15s %d ms%n", "Merge Sort",     measure(() -> mergeSort(Arrays.copyOf(base, size), 0, size - 1)));
            System.out.printf("%-15s %d ms%n", "Quick Sort",     measure(() -> quickSort(Arrays.copyOf(base, size), 0, size - 1)));
            System.out.printf("%-15s %d ms%n", "Radix Sort",     measure(() -> radixSort(Arrays.copyOf(base, size))));

            System.out.println("-----------------------------------\n");
        }
    }
}
