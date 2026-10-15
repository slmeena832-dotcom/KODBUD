import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(5000.00);
        ATMInterface atm = new ATMInterface(myAccount);

        System.out.println("Welcome to the Java ATM Simulator!");
        atm.start();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}

class ATMInterface {
    private BankAccount account;
    private Scanner scanner;

    public ATMInterface(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    checkBalance();
                    break;
                case "2":
                    deposit();
                    break;
                case "3":
                    withdraw();
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1-4.");
            }
        }

        scanner.close();
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: Rs. %.2f%n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: Rs. ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return;
        }

        account.deposit(amount);
        System.out.printf("Deposit successful! New balance: Rs. %.2f%n", account.getBalance());
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: Rs. ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }

        boolean success = account.withdraw(amount);

        if (success) {
            System.out.printf("Withdrawal successful! New balance: Rs. %.2f%n", account.getBalance());
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
        }
    }

    private double readAmount() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return -1;
        }
    }
}
