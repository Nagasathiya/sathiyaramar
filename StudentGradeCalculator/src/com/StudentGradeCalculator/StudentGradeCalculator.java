package com.StudentGradeCalculator;


import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("--------STUDENT GRADE CALCULATOR-----------");
        
        System.out.print("Name of the Student : ");
        String name = s.next();
        // Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = s.nextInt();
        
        // Array to store marks
        int[] marks = new int[numSubjects];
        
        // Input marks
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + ": ");
            marks[i] = s.nextInt();
            
            // Validate marks
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid input. Please enter marks between 0 and 100.");
                System.out.print("Enter marks obtained in subject " + (i + 1) + ": ");
                marks[i] = s.nextInt();
            }
        }

        // Calculate total marks
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }

        // Calculate average percentage
        double averagePercentage = (totalMarks / (double) (numSubjects * 100)) * 100;

        // Determine grade
        char grade = calculateGrade(averagePercentage);

        // Display results
        System.out.println("\n--- Result ---");
        System.out.println("Name of the Student: " + name);
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
        
        s.close();
    }

    public static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
