import java.util.Scanner;

public class FA2023_RLCCompanySaleProduct_GAUTAM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FA2023_RLCProduct_Order_GAUTAM order = new FA2023_RLCProduct_Order_GAUTAM();
        String input;
        int units;
        int index;

        System.out.println("SALE RLC PRODUCT MENU - GAUTAM");
        System.out.println("------------------------------------------");
        System.out.println("E. Exit");
        System.out.println("S. Size SMALL  ($124.29/per unit)");
        System.out.println("M. Size MEDIUM ($236.59/per unit)");
        System.out.println("L. Size LARGE  ($348.79/per unit)");
        System.out.print("Type S, M, L to select the size, P to print the receipt, or E to End the program: ");

        while (true) {
            input = scanner.next().toUpperCase();

            if (input.equals("E")) {
                System.out.println("The program is terminating...");
                break;
            } else if (input.equals("S") || input.equals("M") || input.equals("L")) {
                System.out.print("Enter the number of units: ");
                units = scanner.nextInt();

                if (input.equals("S")) {
                    index = 0;
                } else if (input.equals("M")) {
                    index = 1;
                } else {
                    index = 2;
                }

                int[] productUnits = order.getProductUnits();
                productUnits[index] += units;

                System.out.print("Type S, M, L to select the size, P to print the receipt, or E to End the program: ");
            } else if (input.equals("P")) {
                // Print the receipt
                System.out.println(order);

                System.out.print("Type S, M, L to select the size, P to print the receipt, or E to End the program: ");
            } else {
                System.out.println("Invalid input. Please select S, M, L, P, or E.");
            }
        }

        scanner.close();
    }
}
