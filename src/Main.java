import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, Integer> memCache = new HashMap<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the final number: ");
        int finalNumber = scanner.nextInt();
        int waysToWin = countWaysToWin(1, 1, finalNumber);
        System.out.println("The number of ways the game can be played is: " +waysToWin);
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