import java.io.*;
import java.util.*;

class Student {
    int rollNo;
    String studentID;
    String name;
    ArrayList<Integer> marks;
    double percentage;
    String grade;
    String result;

    Student(int rollNo, String name, ArrayList<Integer> marks) {
        this.rollNo = rollNo;
        this.studentID = "STU2025" + String.format("%03d", rollNo);
        this.name = name;
        this.marks = marks;
        calculateResults();
    }

    void calculateResults() {
        int total = 0;
        boolean fail = false;
        for (int m : marks) {
            total += m;
            if (m < 33) fail = true;
        }
        this.percentage = total / (double) marks.size();

        if (fail) {
            this.grade = "F";
            this.result = "Fail";
        } else if (percentage >= 90) grade = "A+";
        else if (percentage >= 80) grade = "A";
        else if (percentage >= 70) grade = "B";
        else if (percentage >= 60) grade = "C";
        else if (percentage >= 50) grade = "D";
        else if (percentage >= 40) grade = "E";
        else {
            grade = "F";
            result = "Fail";
        }

        if (!fail && result == null) result = "Pass";
    }

    String toTextFormat() {
        return rollNo + ", " + studentID + ", " + name + ", " + marks + ", " +
               String.format("%.2f", percentage) + "%, " + grade + ", " + result;
    }

    String toCSVFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(rollNo).append(",").append(studentID).append(",").append(name);
        for (int m : marks) sb.append(",").append(m);
        sb.append(",").append(String.format("%.2f", percentage)).append(",").append(grade).append(",").append(result);
        return sb.toString();
    }
}

public class StudentResultProcessingSystem {
    static ArrayList<Student> students = new ArrayList<Student>();
    static Scanner sc = new Scanner(System.in);
    static int nextRollNo = 1;

    public static void main(String[] args) {
        loadFromFiles();
        int choice;
        do {
            System.out.println("\n===== Student Result Processing System =====");
            System.out.println("1. Add Student Result");
            System.out.println("2. View All Results");
            System.out.println("3. Search Result");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Delete Student Record");
            System.out.println("6. Show Class Topper");
            System.out.println("7. Show Failed Students");
            System.out.println("8. Save & Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllResults();
                    break;
                case 3:
                    searchResult();
                    break;
                case 4:
                    updateMarks();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    showTopper();
                    break;
                case 7:
                    showFailedStudents();
                    break;
                case 8:
                    saveToFiles();
                    System.out.println("Data saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        ArrayList<Integer> marks = new ArrayList<Integer>();
        System.out.print("Enter number of subjects: ");
        int subjects = sc.nextInt();
        for (int i = 1; i <= subjects; i++) {
            System.out.print("Enter marks for Subject " + i + ": ");
            marks.add(sc.nextInt());
        }
        sc.nextLine();
        students.add(new Student(nextRollNo++, name, marks));
        System.out.println("Student added successfully!");
    }

    static void viewAllResults() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("\n--- All Student Results ---");
        for (Student s : students) {
            System.out.println(s.toTextFormat());
        }
    }

    static void searchResult() {
        System.out.print("Enter Name or Student ID to search: ");
        String key = sc.nextLine().trim();
        boolean found = false;
        for (Student s : students) {
            if (s.name.equalsIgnoreCase(key) || s.studentID.equalsIgnoreCase(key)) {
                System.out.println(s.toTextFormat());
                found = true;
            }
        }
        if (!found) System.out.println("No record found!");
    }

    static void updateMarks() {
        System.out.print("Enter Student ID to update: ");
        String id = sc.nextLine().trim();
        for (Student s : students) {
            if (s.studentID.equalsIgnoreCase(id)) {
                ArrayList<Integer> marks = new ArrayList<Integer>();
                System.out.print("Enter number of subjects: ");
                int subjects = sc.nextInt();
                for (int i = 1; i <= subjects; i++) {
                    System.out.print("Enter new marks for Subject " + i + ": ");
                    marks.add(sc.nextInt());
                }
                sc.nextLine();
                s.marks = marks;
                s.calculateResults();
                System.out.println("Marks updated!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.nextLine().trim();
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.studentID.equalsIgnoreCase(id)) {
                it.remove();
                System.out.println("Record deleted!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void showTopper() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.percentage > topper.percentage) topper = s;
        }
        System.out.println("Class Topper: " + topper.toTextFormat());
    }

    static void showFailedStudents() {
        boolean found = false;
        for (Student s : students) {
            if (s.result.equalsIgnoreCase("Fail")) {
                System.out.println(s.toTextFormat());
                found = true;
            }
        }
        if (!found) System.out.println("No failed students!");
    }

    static void saveToFiles() {
        try {
            // Save to TXT
            PrintWriter txt = new PrintWriter(new FileWriter("StudentResults.txt"));
            for (Student s : students) txt.println(s.toTextFormat());
            txt.close();
    
            // Save to CSV
            PrintWriter csv = new PrintWriter(new FileWriter("StudentResults.csv"));
            csv.println("Roll No,Student ID,Name,Subject1,Subject2,Subject3,Percentage,Grade,Result");
            for (Student s : students) csv.println(s.toCSVFormat());
            csv.close();
    
            // Open CSV file automatically
            File csvFile = new File("StudentResults.csv");
            if (csvFile.exists()) {
                try {
                    java.awt.Desktop.getDesktop().open(csvFile);
                } catch (IOException e) {
                    System.out.println("CSV file created, but could not be opened automatically.");
                }
            }
    
        } catch (IOException e) {
            System.out.println("Error saving files: " + e.getMessage());
        }
    }
    

    static void loadFromFiles() {
        File file = new File("StudentResults.txt");
        if (!file.exists()) {
            // preload 2 sample students
            ArrayList<Integer> m1 = new ArrayList<Integer>(Arrays.asList(90, 85, 95));
            students.add(new Student(nextRollNo++, "Ayushi", m1));
            ArrayList<Integer> m2 = new ArrayList<Integer>(Arrays.asList(60, 45, 50));
            students.add(new Student(nextRollNo++, "Rohit", m2));
            saveToFiles();
            return;
        }
        // load existing data
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // basic loading from TXT is skipped for simplicity
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
