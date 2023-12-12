import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, Integer> memCache = new HashMap<>();


    public static void main(String[] args) {
        // Test Cases
        int[] testCases = {10, 50, 1000, 50000};

        // Prepare the table header
        System.out.printf("%-10s%-15s%-15s\n", "n", "Execution Time", "Memory Usage (bytes)");
        System.out.println("-------------------------------------------");

        for (int n : testCases) {
            System.out.println("Running for n = " + n);

            // Measure execution time
            long startTime = System.nanoTime();
            int waysToWin = countWaysToWin(1, 1, n);
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;

            // Measure memory usage
            Runtime runtime = Runtime.getRuntime();
            long memoryUsage = runtime.totalMemory() - runtime.freeMemory();

            // Print results
            System.out.printf("%-10d%-15d%-15d\n", n, executionTime, memoryUsage);
            System.out.println("---------------------------");
        }
    }

    public static int countWaysToWin(int currentNumber, int currentPlayer, int finalNumber) {
        //Base Case: if current number is equal to or greater than the final number, then the game ends
        if (currentNumber >= finalNumber) {
            return 0;
        }

        //Memoization check
        String key = currentNumber + "," + currentPlayer;
        if (memCache.containsKey(key)) {
            return memCache.get(key);
        }

        int waysToWin = 0;
        //try all possible moves
        for (int move = 1; move <= 2; move++) {
            int nextNumber = currentNumber +move;

            //check if next move is valid and count ways to win
            if (nextNumber < finalNumber) {
                waysToWin += countWaysToWin(nextNumber, 3 - currentPlayer, finalNumber);
            }
        }
        //save result of current state
        memCache.put(key, 1 + waysToWin);
        return 1 + waysToWin;

    }
}