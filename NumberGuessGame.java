import java.util.Random;
import java.util.Scanner;
public class NumberGuessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsPlayed = 0;
        int roundsWon = 0;
        boolean playAgain = true;

        System.out.println("🎲 Welcome to the Number Guessing Game!");

        while (playAgain) {
            roundsPlayed++;
            boolean won = playRound(scanner, random);
            if (won) roundsWon++;

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("🏁 Game Over!");
        System.out.println("Total Rounds Played: " + roundsPlayed);
        System.out.println("Total Rounds Won: " + roundsWon);

        scanner.close();
    }
    public static boolean playRound(Scanner scanner, Random random) {
        int numberToGuess = random.nextInt(100) + 1;  // 1–100
        int maxAttempts = 7;
        int attempts = 0;

        System.out.println("\n🎮 New Round: Guess a number between 1 and 100");
        System.out.println("You have " + maxAttempts + " attempts.");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == numberToGuess) {
                System.out.println("✅ Correct! You guessed it in " + attempts + " attempts.");
                return true;
            } else if (guess < numberToGuess) {
                System.out.println("📈 Too low!");
            } else {
                System.out.println("📉 Too high!");
            }

            System.out.println("Attempts left: " + (maxAttempts - attempts));
        }

        System.out.println("❌ You've used all attempts. The number was: " + numberToGuess);
        return false;
    }
}

