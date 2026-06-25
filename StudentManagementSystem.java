import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
    private List<Student> students;
    private String dataFile;

    public StudentManagementSystem(String dataFile) {
        this.students = new ArrayList<>();
        this.dataFile = dataFile;
        loadStudents();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public boolean removeStudent(int rollNumber) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber() == rollNumber) {
                students.remove(i);
                saveStudents();
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    // CSV format storage implementation
    private void saveStudents() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dataFile))) {
            for (Student student : students) {
                writer.println(student.getRollNumber() + "," + student.getName() + "," + student.getGrade());
            }
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    private void loadStudents() {
        File file = new File(dataFile);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        int rollNumber = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        String grade = parts[2].trim();
                        students.add(new Student(name, rollNumber, grade));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing student data: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
    }
}
