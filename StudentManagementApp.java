import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem("students.csv");

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add a new Student");
            System.out.println("2. Remove a Student");
            System.out.println("3. Search for a Student");
            System.out.println("4. Display all Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = -1;
                    try {
                        rollNumber = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Roll number must be an integer.");
                        break;
                    }
                    
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }

                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine().trim();
                    if (grade.isEmpty()) {
                        System.out.println("Grade cannot be empty.");
                        break;
                    }

                    Student newStudent = new Student(name, rollNumber, grade);
                    sms.addStudent(newStudent);
                    System.out.println("Student added successfully!");
                    break;
                    
                case "2":
                    System.out.print("Enter Roll Number to remove: ");
                    try {
                        int rollToRemove = Integer.parseInt(scanner.nextLine().trim());
                        if (sms.removeStudent(rollToRemove)) {
                            System.out.println("Student removed successfully.");
                        } else {
                            System.out.println("Student not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Roll number must be an integer.");
                    }
                    break;
                    
                case "3":
                    System.out.print("Enter Roll Number to search: ");
                    try {
                        int rollToSearch = Integer.parseInt(scanner.nextLine().trim());
                        Student found = sms.searchStudent(rollToSearch);
                        if (found != null) {
                            System.out.println("Student found: " + found);
                        } else {
                            System.out.println("Student not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Roll number must be an integer.");
                    }
                    break;
                    
                case "4":
                    List<Student> students = sms.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found in the system.");
                    } else {
                        System.out.println("--- List of Students ---");
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;
                    
                case "5":
                    System.out.println("Exiting Student Management System. Goodbye!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
