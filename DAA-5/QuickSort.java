import java.util.Random;
import java.util.Scanner;

public class QuickSort {
    private int[] array;

    public QuickSort(int[] array) {
        this.array = array;
    }

    // Deterministic method to find pivot
    private int partition(int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    private int partitionRandom(int low, int high) {
        Random rand = new Random();
        int pivot = rand.nextInt(high - low + 1) + low;
        int temp = array[pivot];
        array[pivot] = array[high];
        array[high] = temp;
        return partition(low, high);
    }

    // Deterministic variant of sort
    private void sortDeterministic(int low, int high) {
        if (low < high) {
            int pivot = partition(low, high);
            sortDeterministic(low, pivot - 1);
            sortDeterministic(pivot + 1, high);
        }
    }

    // Randomized variant of sort
    private void sortRandomized(int low, int high) {
        if (low < high) {
            int pivot = partitionRandom(low, high);
            sortRandomized(low, pivot - 1);
            sortRandomized(pivot + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Press Ctrl+C to exit...");
            System.out.print("Enter array (space-separated numbers): ");
            String input = scanner.nextLine();
            String[] elements = input.split(" ");
            int[] array = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                array[i] = Integer.parseInt(elements[i]);
            }

            QuickSort sorter1 = new QuickSort(array.clone());
            QuickSort sorter2 = new QuickSort(array.clone());

            System.out.println("Deterministic variant of sort");
            sorter1.sortDeterministic(0, array.length - 1);
            for (int num : sorter1.array) {
                System.out.print(num + " ");
            }
            System.out.println();

            System.out.println("Randomized variant of sort");
            sorter2.sortRandomized(0, array.length - 1);
            for (int num : sorter2.array) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
