import java.util.Scanner;

public class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    checkBalance();
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: $");
                    try {
                        double depositAmount = Double.parseDouble(scanner.nextLine().trim());
                        deposit(depositAmount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid amount.");
                    }
                    break;
                case "3":
                    System.out.print("Enter amount to withdraw: $");
                    try {
                        double withdrawAmount = Double.parseDouble(scanner.nextLine().trim());
                        withdraw(withdrawAmount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid amount.");
                    }
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", account.getBalance());
    }

    private void deposit(double amount) {
        if (account.deposit(amount)) {
            System.out.printf("Successfully deposited $%.2f. New balance: $%.2f\n", amount, account.getBalance());
        } else {
            System.out.println("Deposit failed. Amount must be greater than zero.");
        }
    }

    private void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal failed. Amount must be greater than zero.");
        } else if (account.withdraw(amount)) {
            System.out.printf("Successfully withdrew $%.2f. Remaining balance: $%.2f\n", amount, account.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient funds in your account.");
        }
    }
}
