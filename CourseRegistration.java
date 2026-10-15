import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRegistration {

    static List<Course> courses = new ArrayList<>();
    static Student currentStudent;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupCourses();

        System.out.println("===== STUDENT COURSE REGISTRATION SYSTEM =====");
        System.out.print("Enter your Student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter your Name: ");
        String name = scanner.nextLine().trim();

        currentStudent = new Student(id, name);

        boolean running = true;
        while (running) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View My Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewCourses();
                    break;
                case "2":
                    registerCourse();
                    break;
                case "3":
                    dropCourse();
                    break;
                case "4":
                    viewMyCourses();
                    break;
                case "5":
                    running = false;
                    System.out.println("Goodbye, " + currentStudent.name + "!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    static void setupCourses() {
        courses.add(new Course("CS101", "Introduction to Programming",
                "Basics of programming logic", 3, "Mon & Wed, 10 AM"));
        courses.add(new Course("CS102", "Data Structures",
                "Arrays, Linked Lists, Trees, etc.", 2, "Tue & Thu, 11 AM"));
        courses.add(new Course("CS103", "Database Systems",
                "SQL and database design", 3, "Fri, 2 PM"));
    }

    static void viewCourses() {
        System.out.println("\n----- AVAILABLE COURSES -----");
        for (Course c : courses) {
            c.printDetails();
        }
    }

    static Course findCourse(String code) {
        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    static void registerCourse() {
        viewCourses();
        System.out.print("\nEnter course code to register: ");
        String code = scanner.nextLine().trim();

        Course course = findCourse(code);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (currentStudent.registeredCourses.contains(course.code)) {
            System.out.println("You are already registered for this course.");
            return;
        }

        if (course.availableSlots() <= 0) {
            System.out.println("Sorry, this course is full.");
            return;
        }

        course.registeredCount++;
        currentStudent.registeredCourses.add(course.code);
        System.out.println("Successfully registered for " + course.title + "!");
    }

    static void dropCourse() {
        viewMyCourses();

        if (currentStudent.registeredCourses.isEmpty()) {
            return;
        }

        System.out.print("\nEnter course code to drop: ");
        String code = scanner.nextLine().trim();

        Course course = findCourse(code);
        if (course == null || !currentStudent.registeredCourses.contains(code)) {
            System.out.println("You are not registered for this course.");
            return;
        }

        course.registeredCount--;
        currentStudent.registeredCourses.remove(code);
        System.out.println("Successfully dropped " + course.title + ".");
    }

    static void viewMyCourses() {
        System.out.println("\n----- MY REGISTERED COURSES -----");
        if (currentStudent.registeredCourses.isEmpty()) {
            System.out.println("You have not registered for any courses yet.");
            return;
        }

        for (String code : currentStudent.registeredCourses) {
            Course course = findCourse(code);
            if (course != null) {
                course.printDetails();
            }
        }
    }
}

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    int registeredCount;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredCount = 0;
    }

    public int availableSlots() {
        return capacity - registeredCount;
    }

    public void printDetails() {
        System.out.println(code + " - " + title);
        System.out.println("  Description: " + description);
        System.out.println("  Schedule: " + schedule);
        System.out.println("  Available Slots: " + availableSlots() + " / " + capacity);
    }
}

class Student {
    String studentId;
    String name;
    List<String> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}