import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

class Student {
    private int rollNo;
    private String name;
    private String course;
    private double marks;

    public Student(int rollNo, String name, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }

    public void displayStudent() {
        System.out.println("Roll No: " + rollNo + " | Name: " + name + 
                         " | Course: " + course + " | Marks: " + marks);
    }
}

public class StudentManagement {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        System.out.println("=== Student Management System ===");
        
        do {
            System.out.println("\n1. Add Student\n2. View All Students\n3. Search Student\n4. Delete Student\n5. Exit");
            System.out.print("Enter choice: ");
            
            try {
                choice = sc.nextInt();
                sc.nextLine();
                
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: viewAllStudents(); break;
                    case 3: searchStudent(); break;
                    case 4: deleteStudent(); break;
                    case 5: System.out.println("Exiting... Thank you!"); break;
                    default: System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                sc.nextLine();
                choice = 0;
            }
        } while (choice != 5);
        
        sc.close();
    }

    private static void addStudent() {
        try {
            System.out.print("Enter Roll No: ");
            int rollNo = sc.nextInt();
            sc.nextLine();
            
            for (Student s : studentList) {
                if (s.getRollNo() == rollNo) {
                    System.out.println("Student with this Roll No already exists!");
                    return;
                }
            }
            
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Course: ");
            String course = sc.nextLine();
            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();
            
            Student newStudent = new Student(rollNo, name, course, marks);
            studentList.add(newStudent);
            System.out.println("Student Added Successfully!");
            
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please try again.");
            sc.nextLine();
        }
    }

    private static void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        System.out.println("\n--- Student List ---");
        for (Student s : studentList) {
            s.displayStudent();
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Roll No to search: ");
        int searchRoll = sc.nextInt();
        boolean found = false;
        
        for (Student s : studentList) {
            if (s.getRollNo() == searchRoll) {
                System.out.println("Student Found:");
                s.displayStudent();
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Student not found!");
    }

    private static void deleteStudent() {
        System.out.print("Enter Roll No to delete: ");
        int deleteRoll = sc.nextInt();
        boolean removed = false;
        
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNo() == deleteRoll) {
                studentList.remove(i);
                System.out.println("Student Deleted Successfully!");
                removed = true;
                break;
            }
        }
        if (!removed) System.out.println("Student not found!");
    }
}