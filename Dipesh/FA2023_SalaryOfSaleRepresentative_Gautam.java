package Dipesh;
/*code for the driver class FA2023_SalaryOfSaleRepresentative_Gautam.java
*/
import java.util.Scanner;

public class FA2023_SalaryOfSaleRepresentative_Gautam {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        // Read information from the user
        System.out.println("Enter employee name: ");
        String name = keyboard.nextLine();

        System.out.println("Enter employee ID: ");
        String employeeId = keyboard.nextLine();

        System.out.println("Enter sale amount: ");
        float saleAmount = keyboard.nextFloat();

        // Create a Sale Representative object
        FA2023_SaleRepresentative_Gautam saleRep = new FA2023_SaleRepresentative_Gautam(name, employeeId, saleAmount);

        // Display the salary slip
        System.out.println(saleRep);
    }
}
