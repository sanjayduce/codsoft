import java.util.Scanner;

// Class to represent the Bank Account
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
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false; // insufficient funds
        }
    }
}

// Class to represent the ATM
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== ATM MENU ====");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("✅ Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            boolean success = account.withdraw(amount);
            if (success) {
                System.out.println("💸 Please collect your cash: ₹" + amount);
            } else {
                System.out.println("❌ Insufficient balance.");
            }
        } else {
            System.out.println("❌ Invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            account.deposit(amount);
            System.out.println("✅ Amount deposited: ₹" + amount);
        } else {
            System.out.println("❌ Invalid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("💰 Your current balance is: ₹" + account.getBalance());
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create a bank account with initial balance
        BankAccount account = new BankAccount(10000.00);

        // Create an ATM linked to this account
        ATM atm = new ATM(account);

        // Start the ATM interface
        atm.start();
    }
}
