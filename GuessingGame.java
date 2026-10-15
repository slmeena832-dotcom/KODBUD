import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    static final int MIN_RANGE = 1;
    static final int MAX_RANGE = 100;
    static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsWon = 0;
        int roundsPlayed = 0;

        System.out.println("===== NUMBER GUESSING GAME =====");

        boolean playAgain = true;

        while (playAgain) {
            roundsPlayed++;
            int targetNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + roundsPlayed + ": I'm thinking of a number between "
                    + MIN_RANGE + " and " + MAX_RANGE + ".");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");

            while (attemptsLeft > 0 && !guessedCorrectly) {
                System.out.print("\nEnter your guess (" + attemptsLeft + " attempts left): ");

                int guess;
                try {
                    guess = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("That's not a valid number. Try again.");
                    continue;
                }

                attemptsLeft--;

                if (guess == targetNumber) {
                    System.out.println("Correct! You guessed the number " + targetNumber + "!");
                    guessedCorrectly = true;
                    roundsWon++;
                } else if (guess < targetNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\nOut of attempts! The number was: " + targetNumber);
            }

            System.out.println("Score so far -> Rounds Won: " + roundsWon + " / Rounds Played: " + roundsPlayed);

            System.out.print("\nDo you want to play another round? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("y");
        }

        System.out.println("\n===== GAME OVER =====");
        System.out.println("Final Score -> Rounds Won: " + roundsWon + " / Rounds Played: " + roundsPlayed);
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}