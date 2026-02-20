import java.util.*;

// BankAccount Class
class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<String> transactions;

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        transactions.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
        transactions.add("Withdrawn: " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void showTransactions() {
        System.out.println("\nTransaction History:");
        for (String t : transactions) {
            System.out.println(t);
        }
    }

    @Override
    public String toString() {
        return "Account No: " + accountNumber +
               "\nName: " + accountHolderName +
               "\nBalance: " + balance;
    }
}


// BankService Class
class BankService {

    private Map<String, BankAccount> accounts = new HashMap<>();

    public void createAccount(String accNo, String name, double balance) {
        if (accounts.containsKey(accNo)) {
            throw new IllegalArgumentException("Account number already exists!");
        }
        BankAccount account = new BankAccount(accNo, name, balance);
        accounts.put(accNo, account);
        System.out.println("Account created successfully!");
    }

    public BankAccount getAccount(String accNo) {
        BankAccount account = accounts.get(accNo);
        if (account == null) {
            throw new IllegalArgumentException("Account not found!");
        }
        return account;
    }

    public void deleteAccount(String accNo) {
        if (!accounts.containsKey(accNo)) {
            throw new IllegalArgumentException("Account not found!");
        }
        accounts.remove(accNo);
        System.out.println("Account deleted successfully!");
    }
}


// Main Class
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();

        while (true) {
            System.out.println("\n===== Banking Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Delete Account");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter Account Number: ");
                        String accNo = sc.next();
                        System.out.print("Enter Account Holder Name: ");
                        String name = sc.next();
                        System.out.print("Enter Initial Balance: ");
                        double balance = sc.nextDouble();
                        service.createAccount(accNo, name, balance);
                        break;

                    case 2:
                        System.out.print("Enter Account Number: ");
                        accNo = sc.next();
                        System.out.print("Enter Deposit Amount: ");
                        double depositAmount = sc.nextDouble();
                        service.getAccount(accNo).deposit(depositAmount);
                        System.out.println("Amount deposited successfully!");
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        accNo = sc.next();
                        System.out.print("Enter Withdrawal Amount: ");
                        double withdrawAmount = sc.nextDouble();
                        service.getAccount(accNo).withdraw(withdrawAmount);
                        System.out.println("Amount withdrawn successfully!");
                        break;

                    case 4:
                        System.out.print("Enter Account Number: ");
                        accNo = sc.next();
                        System.out.println(service.getAccount(accNo));
                        break;

                    case 5:
                        System.out.print("Enter Account Number: ");
                        accNo = sc.next();
                        service.getAccount(accNo).showTransactions();
                        break;

                    case 6:
                        System.out.print("Enter Account Number: ");
                        accNo = sc.next();
                        service.deleteAccount(accNo);
                        break;

                    case 7:
                        System.out.println("Thank you for using Banking System!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}