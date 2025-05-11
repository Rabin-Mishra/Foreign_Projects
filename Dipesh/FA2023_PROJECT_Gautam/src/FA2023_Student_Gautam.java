//FA2023_Student_Gautam.java

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FA2023_Student_Gautam {
         // Data Members
    private String courseName;
    private int studentID;
    private String studentLastName;
    private String studentFirstName;
    private float extraCreditScore;
    private String[] assignmentNames;
    private int[] maxScores; // Change to float[]
    private int[] studentScores;
    private String[] studentScoresString;

    // Constructors
    public FA2023_Student_Gautam() {
        // Default constructor
    }

    public FA2023_Student_Gautam(String courseName, int studentID, String studentLastName, String studentFirstName,
            float extraCreditScore, String[] assignmentNames, int[] assignmentSizes,
            int[] maxScores, int[] studentScores, String[] studentScoresString) {
        this.courseName = courseName;
        this.studentID = studentID;
        this.studentLastName = studentLastName;
        this.studentFirstName = studentFirstName;
        this.extraCreditScore = extraCreditScore;
        this.assignmentNames = assignmentNames;
        this.maxScores = maxScores;
        this.studentScores = studentScores;
        this.studentScoresString = studentScoresString;
    }

    public FA2023_Student_Gautam(String course, String studentID2, String studentName, float extraCredit,
            String[] assignmentNames2, int[] assignmentSizes, float[] maxScores2, float[] studentScores2,
            Object object) {
    }

    // Method to calculate total max score
    public float calculateTotalMaxScore() {
        return Arrays.stream(maxScores).sum();
    }

    // Method to calculate total student score
    public float calculateTotalStudentScore() {
        return Arrays.stream(studentScores).sum() + extraCreditScore;
    }

    // Method to calculate numeric grade
    public float calculateNumericGrade() {
        float totalStudentScore = calculateTotalStudentScore();
        float totalMaxScore = calculateTotalMaxScore();
        return (totalStudentScore / totalMaxScore) * 100;
    }

    // Method to determine letter grade
    public char determineLetterGrade() {
        float numericGrade = calculateNumericGrade();
        if (numericGrade >= 90) {
            return 'A';
        } else if (numericGrade >= 80) {
            return 'B';
        } else if (numericGrade >= 70) {
            return 'C';
        } else if (numericGrade >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    // Method to create output string
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("FINAL GRADE OF STUDENT - ").append(studentFirstName).append(" ").append(studentLastName)
                .append("\n");
        output.append("---------------------------------------------------------------------------------\n");
        output.append("COURSE NAME:          ").append(courseName).append("\n");
        output.append("STUDENT ID:           ").append(studentID).append("\n");
        output.append("NAME:                 ").append(studentFirstName).append(" ").append(studentLastName)
                .append("\n");
        output.append("---------------------------------------------------------------------------------\n");
        output.append("EXTRA CREDIT:         ").append(extraCreditScore).append("\n");

        // Append scores for each assignment type
        for (int i = 0; i < assignmentNames.length; i++) {
            output.append(String.format("%-20s", assignmentNames[i])).append(studentScoresString[i]).append("\n");
        }

        output.append("---------------------------------------------------------------------------------\n");
        output.append(String.format("Total scores:         %.2f/%.2f\n", calculateTotalStudentScore(),
                calculateTotalMaxScore()));
        output.append(String.format("Numeric grade:        %.5f\n", calculateNumericGrade()));
        output.append(String.format("Letter grade:         %c\n", determineLetterGrade()));

        return output.toString();
    }

    // Method to create string for writing to file
    public String toFile() {
        StringBuilder output = new StringBuilder();
        output.append(courseName).append(",").append(studentID).append(",").append(studentLastName).append(",")
                .append(studentFirstName).append(",");
        output.append(calculateNumericGrade()).append(",").append(determineLetterGrade()).append(",")
                .append(extraCreditScore).append(",");

        for (int i = 0; i < assignmentNames.length; i++) {
            output.append(studentScoresString[i]);
            if (i < assignmentNames.length - 1) {
                output.append(",");
            }
        }

        return output.toString();
    }

    // Method for short output
    public String shortOutput() {
        return String.format("STUDENT: %-20d\nNAME: %-30s\nNumeric Grade: %-20.5f\nLetter Grade: %-20c\n",
                studentID, studentFirstName + " " + studentLastName, calculateNumericGrade(), determineLetterGrade());
    }

    // Method to close resources
    public void closeResources(Scanner scanner) {
        if (scanner != null) {
            scanner.close();
        }
    }
}
