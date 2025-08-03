rimport java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter roll number: ");
                    String roll = sc.nextLine().trim();
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine().trim();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine().trim();

                    if (name.isEmpty() || roll.isEmpty() || grade.isEmpty() || email.isEmpty()) {
                        System.out.println("All fields are required!");
                    } else if (!email.contains("@")) {
                        System.out.println("Invalid email format!");
                    } else if (sms.searchStudent(roll) != null) {
                        System.out.println("Student with this roll number already exists!");
                    } else {
                        sms.addStudent(new Student(name, roll, grade, email));
                        System.out.println("Student added successfully.");
                    }
                    break;

                case 2:
                    System.out.print("Enter roll number to remove: ");
                    String rollToRemove = sc.nextLine();
                    if (sms.removeStudent(rollToRemove)) {
                        System.out.println("Student removed.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter roll number to search: ");
                    String rollToSearch = sc.nextLine();
                    Student found = sms.searchStudent(rollToSearch);
                    if (found != null) {
                        System.out.println("Student Found:\n" + found);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
