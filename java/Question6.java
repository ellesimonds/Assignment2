import java.util.Arrays;

class Question6 {

    //SortingAlgorithm interface
    interface SortingAlgorithm {
        int[] sorty(int[] input);
    }

    //BubbleSort implementation
    static class BubbleSort implements SortingAlgorithm {
        public int[] sorty(int[] input) {
            int n = input.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (input[j] > input[j + 1]) {
                        int temp = input[j];
                        input[j] = input[j + 1];
                        input[j + 1] = temp;
                    }
                }
            }
            return input;
        }
    }

    //InsertionSort implementation
    static class InsertionSort implements SortingAlgorithm {
        public int[] sorty(int[] input) {
            for (int i = 1; i < input.length; i++) {
                int key = input[i];
                int j = i - 1;
                while (j >= 0 && input[j] > key) {
                    input[j + 1] = input[j];
                    j--;
                }
                input[j + 1] = key;
            }
            return input;
        }
    }

    //SelectionSort implementation
    static class SelectionSort implements SortingAlgorithm {
        public int[] sorty(int[] input) {
            for (int i = 0; i < input.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < input.length; j++) {
                    if (input[j] < input[minIndex]) {
                        minIndex = j;
                    }
                }
                int temp = input[minIndex];
                input[minIndex] = input[i];
                input[i] = temp;
            }
            return input;
        }
    }

    //ShellSort implementation
    static class ShellSort implements SortingAlgorithm {
        public int[] sorty(int[] input) {
            int n = input.length;
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    int temp = input[i];
                    int j;
                    for (j = i; j >= gap && input[j - gap] > temp; j -= gap) {
                        input[j] = input[j - gap];
                    }
                    input[j] = temp;
                }
            }
            return input;
        }
    }

    //QuickSort implementation
    static class QuickSort implements SortingAlgorithm {
        public int[] sorty(int[] input) {
            quickSort(input, 0, input.length - 1);
            return input;
        }

        private void quickSort(int[] arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }
        }

        private int partition(int[] arr, int low, int high) {
            int pivot = arr[high];
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (arr[j] < pivot) {
                    i++;
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
            return i + 1;
        }
    }

    //MergeSort implementation
    static class MergeSort implements SortingAlgorithm {
        public int[] sorty(int[] input) {
            mergeSort(input, 0, input.length - 1);
            return input;
        }

        private static void mergeSort(int[] arr, int left, int right) {
            if (left < right) {
                int mid = left + (right - left) / 2;
                mergeSort(arr, left, mid);
                mergeSort(arr, mid + 1, right);
                merge(arr, left, mid, right);
            }
        }

        private static void merge(int[] arr, int left, int mid, int right) {
            int n1 = mid - left + 1;
            int n2 = right - mid;
            int[] leftArr = new int[n1];
            int[] rightArr = new int[n2];

            System.arraycopy(arr, left, leftArr, 0, n1);
            System.arraycopy(arr, mid + 1, rightArr, 0, n2);

            int i = 0, j = 0, k = left;
            while (i < n1 && j < n2) {
                if (leftArr[i] <= rightArr[j]) {
                    arr[k++] = leftArr[i++];
                } else {
                    arr[k++] = rightArr[j++];
                }
            }
            while (i < n1) {
                arr[k++] = leftArr[i++];
            }
            while (j < n2) {
                arr[k++] = rightArr[j++];
            }
        }
    }



        public static void main(String[] args) {
            int[] numbers = {42, 6, 19, -3, 11, -5, 25, 26};

            SortingAlgorithm[] algorithms = {
                    new BubbleSort(),
                    new InsertionSort(),
                    new SelectionSort(),
                    new ShellSort(),
                    new QuickSort(),
                    new MergeSort()
            };

            String[] names = {
                    "Bubble Sort", "Insertion Sort", "Selection Sort",
                    "Shell Sort", "Quick Sort", "Merge Sort"
            };

            for (int i = 0; i < algorithms.length; i++) {
                int[] sorted = algorithms[i].sorty(numbers);
                System.out.println(names[i] + ": " + Arrays.toString(sorted));
            }
        }
    }
