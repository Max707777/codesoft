public class ATMApp {
    public static void main(String[] args) {

        BankAccount userAccount = new BankAccount(1000.00);
        

        ATM atmMachine = new ATM(userAccount);
        

        atmMachine.start();
    }
}
