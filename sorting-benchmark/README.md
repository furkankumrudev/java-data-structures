📊 Sorting Algorithms Benchmark in Java

This project benchmarks various sorting algorithms in Java by measuring their execution time on randomly generated arrays.
Each algorithm is tested across 4 rounds, using the same input data for fair comparison.



🚀 Purpose

The goal of this project is to compare the real-time performance of popular sorting algorithms,
observe how they behave under identical conditions, and better understand their underlying mechanics.



| Algorithm      | Best Case  | Average Case | Worst Case | Space    | Stable | Notes                           |
| -------------- | ---------- | ------------ | ---------- | -------- | ------ | ------------------------------- |
| Selection Sort | O(n²)      | O(n²)        | O(n²)      | O(1)     | ❌      | Simple but slow                 |
| Insertion Sort | O(n)       | O(n²)        | O(n²)      | O(1)     | ✔️     | Very fast on nearly sorted data |
| Shell Sort     | O(n log n) | O(n^(3/2))   | O(n²)      | O(1)     | ❌      | Improved insertion sort         |
| Bubble Sort    | O(n)       | O(n²)        | O(n²)      | O(1)     | ✔️     | Slowest algorithm               |
| Merge Sort     | O(n log n) | O(n log n)   | O(n log n) | O(n)     | ✔️     | Divide & conquer                |
| Quick Sort     | O(n log n) | O(n log n)   | O(n²)      | O(log n) | ❌      | Fastest in practice             |
| Radix Sort     | O(nk)      | O(nk)        | O(nk)      | O(n + k) | ✔️     | Excellent for integers          |




<img width="1536" height="1024" alt="ChatGPT Image 12 Ara 2025 22_08_06" src="https://github.com/user-attachments/assets/a682a9e3-f549-4792-8b88-d201dd8617d3" />



## 🛠️ Setup & Run
1. **Requirements**:
   - Java JDK 17+
   - Maven (optional)

2. **Compile & Execute**:
   ```bash
   javac Main.java
   java Main
