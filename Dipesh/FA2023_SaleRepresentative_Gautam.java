package Dipesh;
/*
 code for datatype class
 FA2023_SaleRepresentative_Gautam.java
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class FA2023_SaleRepresentative_Gautam {
    private String name;
    private String employeeId;
    private float saleAmount;

    // Constants for base salary, commission rates, and bonus rates
    private static final double BASE_SALARY = 2800.00;
    private static final double COMMISSION_RATE_1 = 0.0159; // 1.59%
    private static final double COMMISSION_RATE_2 = 0.0239; // 2.39%
    private static final double COMMISSION_RATE_3 = 0.0319; // 3.19%
    private static final double BONUS_RATE_1 = 0.0; // No bonus
    private static final double BONUS_RATE_2 = 0.0159; // 1.59%

    // Constructor
    public FA2023_SaleRepresentative_Gautam(String name, String employeeId, float saleAmount) {
        this.name = name;
        this.employeeId = employeeId;
        this.saleAmount = saleAmount;
    }

    // Method to calculate commission amount
    public double calculateCommission() {
        if (saleAmount>=0 && saleAmount<5000) {
            return 0.0;
        } else if (saleAmount>=5000 && saleAmount<10000) {
            return saleAmount * COMMISSION_RATE_1;
        } else if (saleAmount>=10000 && saleAmount<15000) {
            return saleAmount * COMMISSION_RATE_2;
        } else {
            return saleAmount * COMMISSION_RATE_3;
        }
    }

    // Method to calculate bonus amount
    public double calculateBonus() {
        if (saleAmount >= 10000 && saleAmount < 15000) {
            return saleAmount * BONUS_RATE_2;
        } else if(saleAmount>15000){
            return saleAmount * BONUS_RATE_2;
        }else
            return 0.0;
        }
    
    

    // Method to calculate total salary in a month
    public double calculateTotalSalary() {
        double commission = calculateCommission();
        double bonus = calculateBonus();
        return BASE_SALARY + commission + bonus;
    }

    // Method to generate the salary slip
    public String generateSalarySlip() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String today = dateFormat.format(date);

        StringBuilder sb = new StringBuilder();
        sb.append("SP2023 SALE COMPANY               ").append(name).append("\n");
        sb.append("Today:                           ").append(today).append("\n");
        sb.append("----------------------------------------------------\n");
        sb.append("Employee Id:                    ").append(employeeId).append("\n");
        sb.append("Employee Name:                  ").append(name).append("\n");
        sb.append("Sale Amount:                    ").append(String.format("%.2f", saleAmount)).append("\n");
        sb.append("----------------------------------------------------\n");
        sb.append("Base Salary:                    ").append(String.format("%.2f", BASE_SALARY)).append("\n");
        sb.append("Commission Amount:              ").append(String.format("%.2f", calculateCommission())).append("\n");
        sb.append("Bonus Amount:                   ").append(String.format("%.2f", calculateBonus())).append("\n");
        sb.append("Salary:                         ").append(String.format("%.2f", calculateTotalSalary()))
                .append("\n");
        sb.append("----------------------------------------------------\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return generateSalarySlip();
    }
}
