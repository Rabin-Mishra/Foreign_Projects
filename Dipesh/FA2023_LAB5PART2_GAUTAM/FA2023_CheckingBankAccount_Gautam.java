package Dipesh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FA2023_CheckingBankAccount_Gautam {
    private String accountName;
    private float balance;
    private float interestRate;
    private String accountNumber;

    public FA2023_CheckingBankAccount_Gautam(String accountName, float balance, float interestRate) {
        this.accountName = accountName;
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNumber = generateAccountNumber();
    }

    public void openNewAccount() {
        System.out.println("ONLINE BANK – JAMES SMITH");
        System.out.println("-------------------------------");
        System.out.println("NEW ACCOUNT");
        System.out.println("Account Number:  " + accountNumber);
        System.out.println("Account Name:    " + accountName);
        System.out.println("Balance:          $" + balance);
        System.out.println("Interest Rate:    " + interestRate + "%");
    }

    public void checkCurrentBalance() {
        System.out.println("ONLINE BANK – JAMES SMITH");
        System.out.println("-------------------------------");
        System.out.println("CURRENT ACCOUNT");
        System.out.println("Account Number:  " + accountNumber);
        System.out.println("Account Name:    " + accountName);
        System.out.println("Balance:          $" + balance);
    }

    public void checkInterestRate() {
        System.out.println("ONLINE BANK – JAMES SMITH");
        System.out.println("-------------------------------");
        System.out.println("INTEREST RATE");
        System.out.println("Account Number:  " + accountNumber);
        System.out.println("Account Name:    " + accountName);
        System.out.println("Balance:          $" + balance);
        System.out.println("Interest Rate:    " + interestRate + "%");
    }

    public void deposit(float amount) {
        balance += amount;
        System.out.println("ONLINE BANK – JAMES SMITH");
        System.out.println("-------------------------------");
        System.out.println("DEPOSIT");
        System.out.println("Account Number:  " + accountNumber);
        System.out.println("Account Name:    " + accountName);
        System.out.println("Balance:          $" + balance);
        System.out.println("Deposit:          $" + amount);
        System.out.println("New Balance:      $" + balance);
    }

    public void withdraw(float amount) {
        if (balance - amount < 100.0f) {
            System.out.println("ONLINE BANK – JAMES SMITH");
            System.out.println("-------------------------------");
            System.out.println("WITHDRAW");
            System.out.println("Account Number:  " + accountNumber);
            System.out.println("Account Name:    " + accountName);
            System.out.println("Balance:          $" + balance);
            System.out.println("Withdraw:         $" + amount + " - Denied");
            System.out.println("New Balance:      $" + balance);
        } else {
            balance -= amount;
            System.out.println("ONLINE BANK – JAMES SMITH");
            System.out.println("-------------------------------");
            System.out.println("WITHDRAW");
            System.out.println("Account Number:  " + accountNumber);
            System.out.println("Account Name:    " + accountName);
            System.out.println("Balance:          $" + balance);
            System.out.println("Withdraw:         $" + amount);
            System.out.println("New Balance:      $" + balance);
        }
    }

    public void bankStatement() {
        System.out.println("ONLINE BANK – JAMES SMITH");
        System.out.println("-------------------------------");
        System.out.println("BANK STATEMENT");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        System.out.println("Statement Date:   " + dateFormat.format(date));
        System.out.println("Account Number:  " + accountNumber);
        System.out.println("Account Name:    " + accountName);
        System.out.println("Balance:          $" + balance);
        System.out.println("Interest Rate:    " + interestRate + "%");
        float interestAmount = (interestRate / 100) * balance;
        balance += interestAmount;
        System.out.println("Interest Amount:  $" + interestAmount);
        System.out.println("New Balance:      $" + balance);
    }

    private String generateAccountNumber() {
        Random rand = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            accountNumber.append(rand.nextInt(10));
        }
        return accountNumber.toString();
    }
}
