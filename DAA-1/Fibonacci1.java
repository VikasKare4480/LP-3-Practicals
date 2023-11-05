import java.util.Scanner;
import java.util.HashMap;

public class Fibonacci1 {
    // Iterative method to calculate Fibonacci numbers
    public static int fibonacciIterative(int n) {

        int iSteps = 0;
        if (n <= 1) {
            return n;
        }
        int a = 0;
        int b = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            iSteps++;
            result = a + b;
            a = b;
            b = result;
        }
        System.out.println(result);
        return iSteps;
    }

    // Recursive method to calculate Fibonacci numbers
    static int rSteps = 0;
    static int fib = 0;
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        rSteps++;
        fib = fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        return rSteps;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n (nth Fibonacci number): ");
        int n = scanner.nextInt();
        scanner.close();

        // Calculate and print Fibonacci numbers using both methods
        int iSteps = fibonacciIterative(n);
        int rSteps = fibonacciRecursive(n);

        System.out.println("Fibonacci Number (Iterative): " + iSteps);
        System.out.println("Fibonacci Number (Recursive): " + rSteps);
    }
}

