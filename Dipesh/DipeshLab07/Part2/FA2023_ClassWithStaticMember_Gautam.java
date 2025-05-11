// File Name: FA2023_ClassWithStaticMember_Gautam.java

public class FA2023_ClassWithStaticMember_Gautam {
    // Static methods to calculate area for different shapes
    public static double calculateSquareArea(double side) {
        return side * side;
    }

    public static double calculateRectangleArea(double length, double width) {
        return length * width;
    }

    public static double calculateTriangleArea(double base, double height) {
        return (base * height) / 2;
    }

    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }

    public static double calculateTrapezoidArea(double base1, double base2, double height) {
        return (base1 + base2) * height / 2;
    }

    public static double calculateParallelogramArea(double base, double height) {
        return base * height;
    }
}
