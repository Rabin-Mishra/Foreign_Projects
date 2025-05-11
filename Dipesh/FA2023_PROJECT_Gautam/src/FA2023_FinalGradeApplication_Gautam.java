//FA2023_FinalGradeApplication_Gautam.java

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FA2023_FinalGradeApplication_Gautam {

    public static void main(String[] args) {
        // Constants
        final int SIZE = 7;
        final String[] ASSIGNMENT_NAMES = { "QUIZ", "HOMEWORK", "LAB", "PROJECT", "TEAMWORK", "DISCUSSION TOPIC",
                "TEST" };

        // Initialize arrays to store assignment sizes and max scores
        int[] assignmentSizes = new int[SIZE];
        float[] maxScores = new float[SIZE];

        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < SIZE; i++) {
                System.out.print("Enter the number of " + ASSIGNMENT_NAMES[i] + " assignments: ");
                assignmentSizes[i] = scanner.nextInt();

                System.out.print("Enter the max score for each " + ASSIGNMENT_NAMES[i] + ": ");
                maxScores[i] = scanner.nextFloat() * assignmentSizes[i];
            }

            // Display the menu
            displayMenu();

            // Read user choice
            int choice = scanner.nextInt();

            // Process user choice
            switch (choice) {
                case 1:
                    gradingOneStudent(ASSIGNMENT_NAMES, assignmentSizes, maxScores);
                    break;
                case 2:
                    printGradeOfOneStudentFromFile(ASSIGNMENT_NAMES, assignmentSizes, maxScores);
                    break;
                case 3:
                    printGradesOfClassFromFile(ASSIGNMENT_NAMES, assignmentSizes, maxScores);
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("TASK OF GRADING - YOUR NAME");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("1. Grading One Student");
        System.out.println("2. Printing The Grade of One Student from input file");
        System.out.println("3. Printing The Grades of Class");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void gradingOneStudent(String[] assignmentNames, int[] assignmentSizes, float[] maxScores) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter course name: ");
            String courseName = scanner.nextLine();

            System.out.print("Enter student ID: ");
            int studentID = scanner.nextInt();

            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter student last name: ");
            String studentLastName = scanner.nextLine();

            System.out.print("Enter student first name: ");
            String studentFirstName = scanner.nextLine();

            System.out.print("Enter extra credit score: ");
            float extraCreditScore = scanner.nextFloat();

            float[] studentScores = new float[assignmentNames.length];
            String[] studentScoresString = new String[assignmentNames.length];

            // Read scores for each assignment type
            for (int i = 0; i < assignmentNames.length; i++) {
                System.out.print("Enter scores for " + assignmentNames[i] + " (space-separated): ");
                scanner.nextLine(); // Consume the newline character
                studentScoresString[i] = scanner.nextLine();
                String[] scores = studentScoresString[i].split(" ");
                for (String score : scores) {
                    studentScores[i] += Float.parseFloat(score);
                }
            }

            // Create an object of the data type class
            FA2023_Student_Gautam student = new FA2023_Student_Gautam(courseName, Integer.toString(studentID),
                    studentFirstName + " " + studentLastName, extraCreditScore, assignmentNames, assignmentSizes,
                    maxScores, studentScores, studentScoresString);

            // Display the grade of the student
            System.out.println(student.toString());

            // Write to file
            writeToFile(student.toFile());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void printGradeOfOneStudentFromFile(String[] assignmentNames, int[] assignmentSizes,
            float[] maxScores) {
        try (BufferedReader reader = new BufferedReader(new FileReader("studentGrades.txt"))) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter student ID: ");
            String userInputStudentID = scanner.nextLine();

            String line;
            boolean studentFound = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String studentID = data[1];

                // Check if the current line corresponds to the user input student ID
                if (userInputStudentID.equals(studentID)) {
                    FA2023_Student_Gautam student = createStudentFromData(data, assignmentNames, assignmentSizes,
                            maxScores);
                    System.out.println(student.toString());
                    studentFound = true;
                    break; // Stop reading the file after finding the student
                }
            }

            // If student not found
            if (!studentFound) {
                System.out.println("Student not found in the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printGradesOfClassFromFile(String[] assignmentNames, int[] assignmentSizes,
            float[] maxScores) {
        try (BufferedReader reader = new BufferedReader(new FileReader("studentGrades.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                FA2023_Student_Gautam student = createStudentFromData(data, assignmentNames, assignmentSizes,
                        maxScores);
                System.out.println(student.shortOutput());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String data) {
        try {
            FileWriter writer = new FileWriter("studentGrades.txt", true);
            writer.write(data + "\n");
            writer.close();
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file.");
        }
    }

    private static FA2023_Student_Gautam createStudentFromData(String[] data, String[] assignmentNames,
            int[] assignmentSizes, float[] maxScores) {
        String course = data[0];
        String studentID = data[1];
        String studentName = data[2];
        float extraCredit = Float.parseFloat(data[3]);
        float[] studentScores = new float[assignmentNames.length];

        for (int i = 0; i < assignmentNames.length; i++) {
            String[] scores = data[4 + i].split(" ");
            float sum = 0;
            for (String score : scores) {
                sum += Float.parseFloat(score);
            }
            studentScores[i] = sum;
        }

        return new FA2023_Student_Gautam(course, studentID, studentName, extraCredit, assignmentNames,
                assignmentSizes, maxScores, studentScores, null);
    }
}
