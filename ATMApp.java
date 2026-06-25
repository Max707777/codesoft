public class ATMApp {
    public static void main(String[] args) {
        // Initialize user's bank account with a starting balance
        BankAccount userAccount = new BankAccount(1000.00);
        
        // Connect the ATM with the user's bank account
        ATM atmMachine = new ATM(userAccount);
        
        // Start the ATM interface
        atmMachine.start();
    }
}
