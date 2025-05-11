// File Name: FA2023_CarpetProjectQuote_Gautam.java

import java.util.Scanner;

public class FA2023_CarpetProjectQuote_Gautam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userName = "Dipesh Gautam";
        String userAddress = "123 Walnut Road Dallas TX 75243";
        displayMenu();

        double totalArea = 0.0;

        int shapeChoice = 0;

        do {
            System.out.print("Enter your choice: ");
            shapeChoice = scanner.nextInt();

            switch (shapeChoice) {
                case 1:
                    totalArea += handleSquare();
                    break;
                case 2:
                    totalArea += handleRectangle();
                    break;
                case 3:
                    totalArea += handleTriangle();
                    break;
                case 4:
                    totalArea += handleCircle();
                    break;
                case 5:
                    totalArea += handleTrapezoid();
                    break;
                case 6:
                    break; // Exit the loop for DONE
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (shapeChoice != 6);

        displayFinalQuote(userName, userAddress, totalArea);

        scanner.close();
    }

    private static void displayMenu() {
        // Display the menu options
        System.out.println("RLC CARPET INSTALLATION – DIPEESH GAUTAM");
        System.out.println("-----------------------------------------------------");
        System.out.println("1. Square");
        System.out.println("2. Rectangle");
        System.out.println("3. Triangle");
        System.out.println("4. Circle");
        System.out.println("5. Trapezoid");
        System.out.println("6. DONE");
    }

    private static double handleSquare() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter side length for Square: ");
        double side = scanner.nextDouble();

        double area = FA2023_ClassWithStaticMember_Gautam.calculateSquareArea(side);

        System.out.println("Square - Area: " + area);
        return area;
    }

    private static double handleRectangle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter length for Rectangle: ");
        double length = scanner.nextDouble();
        System.out.print("Enter width for Rectangle: ");
        double width = scanner.nextDouble();

        double area = FA2023_ClassWithStaticMember_Gautam.calculateRectangleArea(length, width);

        System.out.println("Rectangle - Area: " + area);
        return area;
    }

    private static double handleTriangle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter base length for Triangle: ");
        double base = scanner.nextDouble();
        System.out.print("Enter height for Triangle: ");
        double height = scanner.nextDouble();

        double area = FA2023_ClassWithStaticMember_Gautam.calculateTriangleArea(base, height);

        System.out.println("Triangle - Area: " + area);
        return area;
    }

    private static double handleCircle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter radius for Circle: ");
        double radius = scanner.nextDouble();

        double area = FA2023_ClassWithStaticMember_Gautam.calculateCircleArea(radius);

        System.out.println("Circle - Area: " + area);
        return area;
    }

    private static double handleTrapezoid() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter base1 length for Trapezoid: ");
        double base1 = scanner.nextDouble();
        System.out.print("Enter base2 length for Trapezoid: ");
        double base2 = scanner.nextDouble();
        System.out.print("Enter height for Trapezoid: ");
        double height = scanner.nextDouble();

        double area = FA2023_ClassWithStaticMember_Gautam.calculateTrapezoidArea(base1, base2, height);

        System.out.println("Trapezoid - Area: " + area);
        return area;
    }

    private static void displayFinalQuote(String userName, String userAddress, double totalArea) {
        // Constants for cost calculation
        final double CARPET_COST_PER_SQFT = 2.75;
        final double LABOR_COST_PER_SQFT = 1.25;
        final double MATERIAL_COST_PERCENTAGE = 0.10;
        final double TAX_RATE = 0.0825;

        // Calculate costs
        double carpetCost = totalArea * CARPET_COST_PER_SQFT;
        double materialCost = carpetCost * MATERIAL_COST_PERCENTAGE;
        double laborCost = totalArea * LABOR_COST_PER_SQFT;
        double subtotal = carpetCost + materialCost + laborCost;
        double tax = subtotal * TAX_RATE;
        double totalCharge = subtotal + tax;

        // Display final project quote
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Sum of area (square feet): " + totalArea);
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Carpet Cost ($" + CARPET_COST_PER_SQFT + " per square feet): $" + carpetCost);
        System.out.println("Labor ($" + LABOR_COST_PER_SQFT + " per square feet): $" + laborCost);
        System.out.println("Materials: $" + materialCost);
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Tax (" + (TAX_RATE * 100) + "%): $" + tax);
        System.out.println("Total charge: $" + totalCharge);
    }
}
