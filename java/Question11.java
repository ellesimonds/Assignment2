import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
public class Question11 {


    public static class Tester {
        private Question6.SortingAlgorithm algorithm;

        public Tester(Question6.SortingAlgorithm algorithm) {
            this.algorithm = algorithm;
        }

        public double singleTest(int size, boolean kSorted) {
            int[] array = kSorted ? generateKSorted(size) : generateRandomArray(size);
            long startTime = System.nanoTime();
            algorithm.sorty(array);
            long endTime = System.nanoTime();
            return (endTime - startTime) / 1e6; // Convert to milliseconds
        }

        public double test(int iterations, int size, boolean kSorted) {
            double totalTime = 0;
            for (int i = 0; i < iterations; i++) {
                totalTime += singleTest(size, kSorted);
            }
            return totalTime / iterations;
        }

        private int[] generateRandomArray(int size) {
            Random rand = new Random();
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = rand.nextInt(10000) - 5000;
            }
            return array;
        }

        private int[] generateKSorted(int size) {
            int[] sortedArray = generateRandomArray(size);
            Arrays.sort(sortedArray); // First, sort the array normally

            Random rand = new Random();
            for (int i = 0; i < size; i++) {
                int swapIndex = i + rand.nextInt(21) - 10; // Random swap within ±10 positions
                if (swapIndex >= 0 && swapIndex < size) {
                    int temp = sortedArray[i];
                    sortedArray[i] = sortedArray[swapIndex];
                    sortedArray[swapIndex] = temp;
                }
            }
            return sortedArray;
        }
    }


    public class Performance {
        public static void main(String[] args) {
            run(); // Call the run function
        }

        public static void run() {
            int[] sizes = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
            int iterations = 20;
            String filename = "SortingPerformanceComparison.txt";

            Question6.SortingAlgorithm[] algorithms = {
                    new Question6.BubbleSort(),
                    new Question6.InsertionSort(),
                    new Question6.SelectionSort(),
                    new Question6.ShellSort(),
                    new Question6.QuickSort(),
                    new Question6.MergeSort()
            };

            try (FileWriter writer = new FileWriter(filename)) {
                for (Question6.SortingAlgorithm algo : algorithms) {
                    Tester tester = new Tester(algo);

                    //RANDOM DATA TEST
                    writer.write("Sorting algorithm – " + algo.getClass().getSimpleName() + " (Random Data)\n");
                    System.out.println("Testing " + algo.getClass().getSimpleName() + " (Random Data)...");
                    for (int size : sizes) {
                        double avgTime = tester.test(iterations, size, false);
                        System.out.printf("Sorted %d elements in %.3f ms (avg)\n", size, avgTime);
                    }


                    //10-SORTED DATA TEST
                    System.out.println("Testing " + algo.getClass().getSimpleName() + " (10-Sorted Data)...");
                    for (int size : sizes) {
                        double avgTime = tester.test(iterations, size, true);
                        System.out.printf("Sorted %d elements in %.3f ms (avg)\n", size, avgTime);
                    }
                    System.out.println();

                    writer.write("\n");

                }
                System.out.println("Performance report saved to " + filename);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }
    }

}
