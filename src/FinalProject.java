// Tyler Boudreau
// COP 3330: Object Oriented Programming
// Final Project
// 07/27/2023

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class FinalProject {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String choice;

        System.out.println("\n\t\tWelcome to the Personal Management Program\n");
        do {
            System.out.println("Select one of the following options:\n");
            System.out.println("1 - Add a faculty to the database");
            System.out.println("2 - Add a student to the database");
            System.out.println("3 - Print tuition invoice for a student");
            System.out.println("4 - Print faculty's information");
            System.out.println("5 - Add a staff member");
            System.out.println("6 - Print the information of a staff member");
            System.out.println("7 - Delete a person from database");
            System.out.println("8 - Exit the Program\n");
            System.out.print("Enter your desired option #: \n");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nEnter the following faculty info:\n");
                    Faculty faculty = inputFaculty(sc);
                    people.add(faculty);
                    System.out.println("\nFaculty added!\n");
                    break;
                case "2":
                    System.out.println("\nEnter the following student info:\n");
                    Student student = inputStudent(sc);
                    people.add(student);
                    System.out.println("\nStudent added!\n");
                    break;
                case "3":
                    System.out.print("\nEnter the student's id: \n");
                    String studentId = sc.nextLine();
                    Student studentToPrint = getStudentById(people, studentId);
                    if (studentToPrint != null) {
                        studentToPrint.print();
                    } else {
                        System.out.println("\nStudent not found in database!\n");
                    }
                    break;
                case "4":
                    System.out.print("\nEnter the Faculty's id: \n");
                    String facultyId = sc.nextLine();
                    Faculty facultyToPrint = getFacultyById(people, facultyId);
                    if (facultyToPrint != null) {
                        facultyToPrint.print();
                    } else {
                        System.out.println("Faculty member not found in database!");
                    }
                    break;
                case "5":
                    System.out.println("\nEnter the following staff member info:\n");
                    Staff staff = inputStaff(sc);
                    people.add(staff);
                    System.out.println("\nStaff member added!\n");
                    break;
                case "6":
                    System.out.print("\nEnter the Staff member's id:\n");
                    String staffId = sc.nextLine();
                    Staff staffToPrint = getStaffById(people, staffId);
                    if (staffToPrint != null) {
                        staffToPrint.print();
                    } else {
                        System.out.println("Staff member not found in database!\n");
                    }
                    break;
                case "7":
                    System.out.print("\nEnter the id of the person you wish to delete:\n");
                    String idToDelete = sc.nextLine();
                    if (deletePersonById(people, idToDelete)) {
                        System.out.println("\nPerson " + idToDelete + " has been deleted from the database!\n");
                    } else {
                        System.out.println("\nNo such person exists.\n");
                    }
                    break;
                case "8":
                    System.out.print("\nWould you like to create the report? (Y/N):\n");
                    String createReport = sc.nextLine().toUpperCase();
                    if (createReport.equals("Y")) {
                        System.out.print("\nWould you like to sort students by descending GPA or name:\n(1 for GPA, 2 for name):\n");
                        String sortOption = sc.nextLine();
                        generateReportfile(people, sortOption);
                        System.out.println("\nReport created and saved on your device!\n");
                    }
                    System.out.println("Goodbye!\n");
                    break;
                default:
                    System.out.println("Invalid entry - please try again\n");
            }
        } while (!choice.equals("8"));
    }

    private static Faculty inputFaculty(Scanner sc) {
        System.out.print("Enter the name of the faculty:\n");
        String name = sc.nextLine();
        System.out.print("\nEnter the Faculty's ID:\n");
        String id = sc.nextLine();
        while (!id.matches("[a-zA-Z]{2}\\d{4}")) {
            System.out.println("\n'" + id + "' is an Invalid ID format. Must be in the format 'LetterLetterDigitDigitDigitDigit'.\n");
            System.out.println("Enter the Faculty's ID: ");
            id = sc.nextLine();
        }
        System.out.print("\nEnter the Faculty's Rank: (Professor or Adjunct): ");
        String rank = sc.nextLine().toUpperCase();
        while (!rank.equals("PROFESSOR") && !rank.equals("ADJUNCT")) {
            System.out.println("\n'" + rank + "' is an invalid rank. Please choose either Professor or Adjunct.");
            System.out.print("Enter the Faculty's Rank: (Professor or Adjunct): ");
            rank = sc.nextLine().toUpperCase();
        }
        System.out.print("\nEnter the Faculty's Department:\n(Mathematics, Engineering, or English):\n");
        String department = sc.nextLine().toUpperCase();
        while (!department.equals("MATHEMATICS") && !department.equals("ENGINEERING") && !department.equals("ENGLISH")) {
            System.out.println("\n'" + department + "' is an invalid department. Please choose from Mathematics, Engineering, or English.");
            System.out.print("\nEnter the Faculty's Department:\n(Mathematics, Engineering, or English): ");
            department = sc.nextLine().toUpperCase();
        }
        return new Faculty(name, id, rank, department);
    }

    private static Student inputStudent(Scanner sc) {
        System.out.print("\nEnter the Student's name:\n");
        String name = sc.nextLine();
        System.out.print("\nEnter the Students ID:\n");
        String id = sc.nextLine();
        while (!id.matches("[a-zA-Z]{2}\\d{4}")) {
            System.out.println("\n'" + id + "' is an invalid ID format. Must be in the format: LetterLetterDigitDigitDigitDigit");
            System.out.print("\nEnter the Students ID: ");
            id = sc.nextLine();
        }
        System.out.print("\nEnter the Student's GPA:\n");
        double gpa = sc.nextDouble();
        sc.nextLine();
        System.out.print("\nEnter the student's Credit hours:\n");
        int creditHours = sc.nextInt();
        sc.nextLine();
        return new Student(name, id, gpa, creditHours);
    }

    private static Staff inputStaff(Scanner sc) {
        System.out.print("\nEnter the staff member's name:\n");
        String name = sc.nextLine();
        System.out.print("\nEnter the staff member's ID: ");
        String id = sc.nextLine();
        while (!id.matches("[a-zA-Z]{2}\\d{4}")) {
            System.out.println("\n'" + id + "' is an invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
            System.out.print("\nEnter the staff member's ID: ");
            id = sc.nextLine();
        }
        System.out.print("\nEnter the Staff Member's Department: \n(Mathematics, Engineering, or English):\n");
        String department = sc.nextLine().toUpperCase();
        while (!department.equals("MATHEMATICS") && !department.equals("ENGINEERING") && !department.equals("ENGLISH")) {
            System.out.println("\n'" + department + "' is an invalid department. Please choose from 'Mathematics', 'Engineering', or 'English'.");
            System.out.print("\nEnter the Staff Member's Department: \n(Mathematics, Engineering, or English): ");
            department = sc.nextLine().toUpperCase();
        }
        System.out.print("\nEnter the Staff's status: ('P' for Part Time, 'F' for Full Time): ");
        String status = sc.nextLine().toUpperCase();
        while (!status.equals("P") && !status.equals("F")) {
            System.out.println("\n'" + status + "' is an invalid status. Please choose either 'P' for part time, or 'F' for full time.");
            System.out.print("\nEnter the Staff's status: ('P' for Part Time, 'F' for Full Time): ");
            status = sc.nextLine().toUpperCase();
        }
        return new Staff(name, id, department, status);
    }

    private static Student getStudentById(List<Person> people, String id) {
        for (Person person : people) {
            if (person instanceof Student) {
                Student student = (Student) person;
                if (student.getId().equalsIgnoreCase(id)) {
                    return student;
                }
            }
        }
        return null;
    }

    private static Faculty getFacultyById(List<Person> people, String id) {
        for (Person person : people) {
            if (person instanceof Faculty) {
                Faculty faculty = (Faculty) person;
                if (faculty.getId().equalsIgnoreCase(id)) {
                    return faculty;
                }
            }
        }
        return null;
    }

    private static Staff getStaffById(List<Person> people, String id) {
        for (Person person : people) {
            if (person instanceof Staff) {
                Staff staff = (Staff) person;
                if (staff.getId().equalsIgnoreCase(id)) {
                    return staff;
                }
            }
        }
        return null;
    }

    private static boolean deletePersonById(List<Person> people, String id) {
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            if (person.getId().equalsIgnoreCase(id)) {
                people.remove(i);
                return true;
            }
        }
        return false;
    }

    private static void generateReportfile(List<Person> people, String sortOption) {
        // Gets the current date in format MM/dd/yyyy
        String currentDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        // Creates new list to store Students
        List<Student> students = new ArrayList<>();
        for (Person person : people) {
            if (person instanceof Student) {
                students.add((Student) person);
            }
        }

        // Sort students based on GPA in descending order (sortOption = 1) or by name (sortOption = 2)
        if (sortOption.equals("1")) {
            students.sort(Comparator.comparing(Student::getGpa).reversed());
        } 
        else if (sortOption.equals("2")) {
            students.sort(Comparator.comparing(Student::getName));
        }

        // Save report to file named report.txt
        try (PrintWriter pw = new PrintWriter("report.txt")) {
            pw.println("Sample Report (report.txt)\n");
            pw.println("\t\tReport created on " + currentDate);
            pw.println("\t\t****************************\n");

            pw.println("Faculty Members");
            pw.println("-----------------");
            int facultyCounter = 1;
            for (Person person : people) {
                if (person instanceof Faculty) {
                    Faculty faculty = (Faculty) person;
                    pw.println("\t" + facultyCounter + ". " + faculty);
                    facultyCounter += 1;
                }
            }

            pw.println("\nStaff Members");
            pw.println("----------------");
            int staffCounter = 1;
            for (Person person : people) {
                if (person instanceof Staff) {
                    Staff staff = (Staff) person;
                    pw.println("\t" + staffCounter + ". " + staff);
                    staffCounter += 1;
                }
            }

            pw.println("\nStudents (Sorted by gpa in descending order)");
            pw.println("----------");
            int studentCounter = 1;
            for (Person person : people) {
                if (person instanceof Student) {
                    Student student = (Student) person;
                    pw.println("\t" + studentCounter + ". " + student);
                    studentCounter += 1;
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("\nError: File not found.\n");;
        }
    }
}
