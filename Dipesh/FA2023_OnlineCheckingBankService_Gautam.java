package Dipesh;

import java.util.Scanner;

public class FA2023_OnlineCheckingBankService_Gautam {
    public static void main(String[] args) {
        FA2023_CheckingBankAccount_Gautam account = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("ONLINE BANK – JAMES SMITH");
            System.out.println("-------------------------------");
            System.out.println("1. Open New Account");
            System.out.println("2. Check Current Balance");
            System.out.println("3. Check Interest Rate");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Bank Statement");
            System.out.println("0. Exit");
            System.out.print("Select a task: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (account == null) {
                        // Read information for opening a new account
                        System.out.print("Enter account name: ");
                        scanner.nextLine(); // Consume the newline character
                        String accountName = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        float initialBalance = scanner.nextFloat();
                        System.out.print("Enter interest rate: ");
                        float interestRate = scanner.nextFloat();

                        if (initialBalance < 20.0f) {
                            System.out.println("To open a new account, the money amount should be at least $20.00");
                        } else {
                            account = new FA2023_CheckingBankAccount_Gautam(accountName, initialBalance, interestRate);
                            account.openNewAccount();
                        }
                    } else {
                        System.out.println("Open New Account before selecting this task.");
                    }
                    break;

                case 2:
                    if (account != null) {
                        account.checkCurrentBalance();
                    } else {
                        System.out.println(
                                "You should select task 1 to create a new account before selecting this task.");
                    }
                    break;

                case 3:
                    if (account != null) {
                        account.checkInterestRate();
                    } else {
                        System.out.println(
                                "You should select task 1 to create a new account before selecting this task.");
                    }
                    break;

                case 4:
                    if (account != null) {
                        System.out.print("Enter the deposit amount: ");
                        float depositAmount = scanner.nextFloat();
                        account.deposit(depositAmount);
                    } else {
                        System.out.println(
                                "You should select task 1 to create a new account before selecting this task.");
                    }
                    break;

                case 5:
                    if (account != null) {
                        System.out.print("Enter the withdrawal amount: ");
                        float withdrawalAmount = scanner.nextFloat();
                        account.withdraw(withdrawalAmount);
                    } else {
                        System.out.println(
                                "You should select task 1 to create a new account before selecting this task.");
                    }
                    break;

                case 6:
                    if (account != null) {
                        account.bankStatement();
                    } else {
                        System.out.println(
                                "You should select task 1 to create a new account before selecting this task.");
                    }
                    break;

                case 0:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid task.");
            }
        }
    }
}
