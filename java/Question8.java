import java.io.FileWriter;
import java.io.IOException;
public abstract class Question8 implements Question6.SortingAlgorithm {


    public static void run() {
        Question6.SortingAlgorithm[] algorithms = {
                new Question6.BubbleSort(),
                new Question6.InsertionSort(),
                new Question6.SelectionSort(),
                new Question6.ShellSort(),
                new Question6.QuickSort(),
                new Question6.MergeSort()
        };

        class Performance {
            public static void main(String[] args) {
                int[] sizes = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
                int iterations = 20;
                String filename = "SortingPerformanceReport.txt";

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
                        Question7.Tester tester = new Question7.Tester(algo) {
                            @Override
                            //need override to run because referencing from other class
                            public int[] sorty(int[] input) {
                                return new int[0];
                            }
                        };
                        writer.write("Sorting algorithm – " + algo.getClass().getSimpleName() + "\n");
                        System.out.println("Testing " + algo.getClass().getSimpleName() + "...");

                        for (int size : sizes) {
                            double avgTime = tester.test(iterations, size);
                            String result = String.format("Sorted %d elements in %.3f ms (avg)\n", size, avgTime);
                            writer.write(result);
                            System.out.print(result);
                        }

                        writer.write("\n");
                        System.out.println();
                    }
                    System.out.println("Performance report saved to " + filename);
                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                }
            }
        }


    }
    public static void main(String[] args){
        run();
    }
}
