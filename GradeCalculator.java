import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== STUDENT GRADE CALCULATOR =====");

        System.out.print("Enter the number of subjects: ");
        int numSubjects = Integer.parseInt(scanner.nextLine().trim());

        if (numSubjects <= 0) {
            System.out.println("Number of subjects must be greater than 0.");
            scanner.close();
            return;
        }

        int totalMarks = 0;

        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks obtained in Subject " + i + " (out of 100): ");
            int marks = Integer.parseInt(scanner.nextLine().trim());

            while (marks < 0 || marks > 100) {
                System.out.print("Invalid marks. Enter a value between 0 and 100: ");
                marks = Integer.parseInt(scanner.nextLine().trim());
            }

            totalMarks += marks;
        }

        double averagePercentage = (double) totalMarks / numSubjects;
        String grade = calculateGrade(averagePercentage);

        System.out.println("\n===== RESULT =====");
        System.out.println("Total Marks: " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F (Fail)";
        }
    }
}