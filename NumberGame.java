import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int score = 0;
        int roundsPlayed = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            roundsPlayed++;
            int targetNumber = random.nextInt(100) + 1;
            int maxAttempts = 10;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n--- Round " + roundsPlayed + " ---");
            System.out.println("I have generated a random number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess;
                try {
                    guess = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                    continue;
                }

                attempts++;

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    score++;
                    guessedCorrectly = true;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                int attemptsLeft = maxAttempts - attempts;
                if (attemptsLeft > 0) {
                    System.out.println("Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("You've run out of attempts! The correct number was " + targetNumber + ".");
                }
            }

            System.out.println("\nYour current score: " + score + " round(s) won out of " + roundsPlayed + " played.");
            
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                playAgain = false;
            }
        }

        System.out.println("\nThank you for playing!");
        System.out.println("Final Score: " + score + " round(s) won.");
        scanner.close();
    }
}
