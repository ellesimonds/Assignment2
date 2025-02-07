import java.util.Arrays;
import java.util.Random;
public class Question7 {

    abstract static class Tester implements Question6.SortingAlgorithm {
        private Question6.SortingAlgorithm sa;

        //constructor
        public Tester(Question6.SortingAlgorithm sa) {
            this.sa = sa;
        }

        //runs a single test with a randomly generated array of given size
        public double singleTest(int size) {
            int[] array = generateRandomArray(size);
            long startTime = System.nanoTime();
            sa.sorty(array);
            long endTime = System.nanoTime();
            return (endTime - startTime) / 1e6; //convert nanoseconds to milliseconds
        }

        //runs multiple tests and prints the average time
        public double test(int iterations, int size) {
            double totalTime = 0;
            for (int i = 0; i < iterations; i++) {
                totalTime += singleTest(size);
            }
            double avgTime = totalTime / iterations;
            System.out.printf("%s: Average time for %d elements over %d iterations: %.3f ms\n",
                    sa.getClass().getSimpleName(), size, iterations, avgTime);
            return totalTime;
        }

        //generates an array of random integers
        private int[] generateRandomArray(int size) {
            Random rand = new Random();
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = rand.nextInt(10000) - 5000; //random numbers from -5000 to 4999
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

        //run method to execute performance tests
        public static void run() {
            Question6.SortingAlgorithm[] algorithms = {
                    new Question6.BubbleSort(),
                    new Question6.InsertionSort(),
                    new Question6.SelectionSort(),
                    new Question6.ShellSort(),
                    new Question6.QuickSort(),
                    new Question6.MergeSort()
            };

            int iterations = 10;
            int size = 1000; //can be adjusted as needed

            for (Question6.SortingAlgorithm algo : algorithms) {
                Tester tester = new Tester(algo) {
                    @Override
                    //had to override because of static issues when calling Tester.run()
                    public int[] sorty(int[] input) {
                        return new int[0];
                    }
                };
                tester.test(iterations, size);
            }
        }
        public static void main(String[] args) {
            Tester.run();
        }

    }

    }


