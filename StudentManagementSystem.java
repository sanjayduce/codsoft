import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private List<Student> studentList;
    private final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        studentList = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        studentList.add(student);
        saveToFile();
    }

    public boolean removeStudent(String rollNumber) {
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                iterator.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(String rollNumber) {
        for (Student s : studentList) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : studentList) {
                System.out.println(s);
            }
        }
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : studentList) {
                writer.println(s.getName() + "," + s.getRollNumber() + "," + s.getGrade() + "," + s.getEmail());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    studentList.add(new Student(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }
}
