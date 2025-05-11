// File Name: FA2023_SalaryInWeek_Gautam.java

import java.util.Scanner;

public class FA2023_SalaryInWeek_Gautam {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for information
        System.out.print("Enter SS Number: ");
        String SSNumber = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Salary Rate: ");
        float salaryRate = scanner.nextFloat();

        // Create an object of FA2023_Employee_Gautam
        FA2023_Employee_Gautam employee = new FA2023_Employee_Gautam(SSNumber, lastName, firstName, "", address,
                salaryRate);

        // Display the output
        System.out.println("\nSALARY OF THE WEEK - " + firstName + " " + lastName
                + "\n---------------------------------------------------");
        System.out.println("SS Number: \t\t\t" + SSNumber);
        System.out.println("Name: \t\t\t\t" + firstName + lastName);
        System.out.println("Address: \t\t\t" + address);
        System.out.println("Salary Rate: \t\t\t" + salaryRate);

        // Calculate and display the salary in one week
        float weeklySalary = employee.calculateSalaryOneWeek();
        System.out.printf("Salary in One Week: \t\t$%.2f%n", weeklySalary);

        scanner.close();
    }
}
