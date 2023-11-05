import java.util.Scanner;

public class Knapsack {
    public static int knapsack(int[] values, int[] weights, int capacity) {
        int[][] dp = new int[values.length + 1][capacity + 1];

        for (int item = 1; item <= values.length; item++) {
            for (int weight = 1; weight <= capacity; weight++) {
                if (weights[item - 1] <= weight) {
                    dp[item][weight] = Math.max(dp[item - 1][weight - weights[item - 1]] + values[item - 1], dp[item - 1][weight]);
                } else {
                    dp[item][weight] = dp[item - 1][weight];
                }
            }
        }
        return dp[values.length][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Ctrl+C to terminate...");
        
        System.out.print("Enter number of items: ");
        int n = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];

        System.out.print("Enter values of items (space-separated): ");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter weights of items (space-separated): ");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.print("Enter maximum weight: ");
        int capacity = scanner.nextInt();

        int maximumValue = knapsack(values, weights, capacity);
        System.out.println("The maximum value of items that can be carried: " + maximumValue);
        
        scanner.close();
    }
}
