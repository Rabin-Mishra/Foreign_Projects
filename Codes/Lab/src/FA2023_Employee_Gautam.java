// File Name: FA2023_Employee_Gautam.java

public class FA2023_Employee_Gautam extends FA2023_Person {
    private float salaryRate;

    // Constructors
    public FA2023_Employee_Gautam() {
        super(); // Call the no-argument constructor of the superclass
        salaryRate = 0.0f; // Set default value
    }

    public FA2023_Employee_Gautam(String num, String last, String first, String phoneN, String addr, float rate) {
        super(num, last, first, phoneN, addr); // Call the parameterized constructor of the superclass
        salaryRate = rate;
    }

    // Additional mutator and accessor methods for salaryRate

    public void setSalaryRate(float rate) {
        salaryRate = rate;
    }

    public float getSalaryRate() {
        return salaryRate;
    }

    // Method to calculate salaryOneWeek() as specified in the lab instructions
    public float calculateSalaryOneWeek() {
        if (salaryRate < 1000) {
            return salaryRate * 40; // Salary one week = salary rate per hour * 40 (hours)
        } else {
            return salaryRate / 52; // Salary one week = salary rate per year / 52 (weeks)
        }
    }

    // Override the toString() method to include additional information
    @Override
    public String toString() {
        return super.toString() +
               String.format("%-15s%25s\n", "Salary Rate: ", salaryRate);
    }

    public void setSSNumber(String nextLine) {
    }
}
