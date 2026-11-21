import java.util.ArrayList;
import java.util.Scanner;
import model.Student;
import model.Course;
import model.Instructor;
import model.Enrollment;
import model.Grade;
import model.Assignment;
import model.Submission;
import model.Announcement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        int choice;

        do {
            System.out.println("\n===== COURSEWORK MANAGEMENT SYSTEM =====");

            System.out.println("\n--- STUDENT MANAGEMENT ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");

            System.out.println("\n--- COURSE MANAGEMENT ---");
            System.out.println("7. Add Course");
            System.out.println("8. View All Courses");
            System.out.println("9. Search Course by ID");
            System.out.println("10. Update Course");
            System.out.println("11. Delete Course");

            System.out.println("\n--- INSTRUCTOR MANAGEMENT ---");
            System.out.println("13. Add Instructor");
            System.out.println("14. View All Instructors");
            System.out.println("15. Search Instructor by ID");
            System.out.println("16. Update Instructor");
            System.out.println("17. Delete Instructor");

            System.out.println("\n--- ENROLLMENT MANAGEMENT ---");
            System.out.println("19. Add Enrollment");
            System.out.println("20. View All Enrollments");
            System.out.println("21. Search Enrollment by ID");
            System.out.println("22. Update Enrollment");
            System.out.println("23. Delete Enrollment");

            System.out.println("\n--- ASSIGNMENT MANAGEMENT ---");
            System.out.println("25. Add Assignment");
            System.out.println("26. View All Assignments");
            System.out.println("27. Search Assignment by ID");
            System.out.println("28. Update Assignment");
            System.out.println("29. Delete Assignment");

            System.out.println("\n--- SUBMISSION MANAGEMENT ---");
            System.out.println("31. Add Submission");
            System.out.println("32. View All Submissions");
            System.out.println("33. Search Submission by ID");
            System.out.println("34. Update Submission");
            System.out.println("35. Delete Submission");

            System.out.println("\n--- GRADE MANAGEMENT ---");
            System.out.println("37. Add Grade");
            System.out.println("38. View All Grades");
            System.out.println("39. Search Grade by ID");
            System.out.println("40. Update Grade");
            System.out.println("41. Delete Grade");

            System.out.println("\n--- ANNOUNCEMENT MANAGEMENT ---");
            System.out.println("43. Add Announcement");
            System.out.println("44. View All Announcements");
            System.out.println("45. Search Announcement by ID");
            System.out.println("46. Update Announcement");
            System.out.println("47. Delete Announcement");

            System.out.println("\n48. Exit\n");

            System.out.print("Enter your choice: ");

            choice = readInt(sc);

            switch (choice) {

                case 1:
                    System.out.println("\n--- Add Student ---");

                    System.out.print("First Name: ");
                    String firstName = sc.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    System.out.print("Department: ");
                    String department = sc.nextLine();

                    System.out.print("Semester: ");
                    int semester = readInt(sc);

                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    Student student = new Student(
                            firstName, lastName, email,
                            password, department, semester, phone
                    );

                    if (dao.addStudent(student)) {
                        System.out.println("Student added successfully!");
                    } else {
                        System.out.println("Unable to add student.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Student Records ---");

                    ArrayList<Student> students = dao.getAllStudents();

                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        for (Student s : students) {
                            System.out.println("\nStudent ID: " + s.getStudentId());
                            System.out.println("Name: " + s.getFirstName()
                                    + " " + s.getLastName());
                            System.out.println("Email: " + s.getEmail());
                            System.out.println("Department: " + s.getDepartment());
                            System.out.println("Semester: " + s.getSemester());
                            System.out.println("Phone: " + s.getPhone());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    int id = readInt(sc);

                    Student found = dao.getStudentById(id);

                    if (found != null) {
                        System.out.println("Student ID: " + found.getStudentId());
                        System.out.println("Name: " + found.getFirstName()
                                + " " + found.getLastName());
                        System.out.println("Email: " + found.getEmail());
                        System.out.println("Department: " + found.getDepartment());
                        System.out.println("Semester: " + found.getSemester());
                        System.out.println("Phone: " + found.getPhone());
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                    
                case 4: {
                    System.out.print("Enter Student ID to update: ");
                    int updateId = readInt(sc);

                    Student existing = dao.getStudentById(updateId);

                    if (existing == null) {
                        System.out.println("Student not found!");
                        break;
                    }

                    System.out.println("Press Enter to keep the existing value.");

                    System.out.print("First Name (" + existing.getFirstName() + "): ");
                    String updatedFirstName = sc.nextLine();

                    if (updatedFirstName.isEmpty()) {
                        updatedFirstName = existing.getFirstName();
                    }

                    System.out.print("Last Name (" + existing.getLastName() + "): ");
                    String updatedLastName = sc.nextLine();

                    if (updatedLastName.isEmpty()) {
                        updatedLastName = existing.getLastName();
                    }

                    System.out.print("Email (" + existing.getEmail() + "): ");
                    String updatedEmail = sc.nextLine();

                    if (updatedEmail.isEmpty()) {
                        updatedEmail = existing.getEmail();
                    }

                    System.out.print("Department (" + existing.getDepartment() + "): ");
                    String updatedDepartment = sc.nextLine();

                    if (updatedDepartment.isEmpty()) {
                        updatedDepartment = existing.getDepartment();
                    }

                    System.out.print("Semester (" + existing.getSemester() + "): ");
                    String semesterInput = sc.nextLine();

                    int updatedSemester = semesterInput.isEmpty()
                            ? existing.getSemester()
                            : readIntFromInput(semesterInput, sc);

                    System.out.print("Phone (" + existing.getPhone() + "): ");
                    String updatedPhone = sc.nextLine();

                    if (updatedPhone.isEmpty()) {
                        updatedPhone = existing.getPhone();
                    }

                    Student updated = new Student(
                            updatedFirstName,
                            updatedLastName,
                            updatedEmail,
                            existing.getPassword(),
                            updatedDepartment,
                            updatedSemester,
                            updatedPhone
                    );

                    updated.setStudentId(updateId);

                    if (dao.updateStudent(updated)) {
                        System.out.println("Student updated successfully!");
                    } else {
                        System.out.println("Update failed!");
                    }

                    break;
                }

                case 5: {
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = readInt(sc);

                    Student studentToDelete = dao.getStudentById(deleteId);

                    if (studentToDelete == null) {
                        System.out.println("Student not found!");
                    } else {
                        System.out.print("Are you sure you want to delete this student? (yes/no): ");
                        String confirm = sc.nextLine();

                        if (confirm.equalsIgnoreCase("yes")) {
                            if (dao.deleteStudent(deleteId)) {
                                System.out.println("Student deleted successfully!");
                            } else {
                                System.out.println("Unable to delete student!");
                            }
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    break;
                }
                   
                case 7: {
                    System.out.println("\n--- Add Course ---");

                    System.out.print("Course Name: ");
                    String courseName = sc.nextLine();

                    System.out.print("Course Code: ");
                    String courseCode = sc.nextLine();

                    System.out.print("Description: ");
                    String courseDescription = sc.nextLine();

                    System.out.print("Instructor ID: ");
                    int instructorId = readInt(sc);

                    Course newCourse = new Course(
                            courseName, courseCode,
                            courseDescription, instructorId
                    );

                    CourseDAO courseDAO = new CourseDAO();

                    if (courseDAO.addCourse(newCourse)) {
                        System.out.println("Course added successfully!");
                    } else {
                        System.out.println("Unable to add course.");
                    }
                    break;
                }

                case 8: {
                    System.out.println("\n--- All Courses ---");

                    CourseDAO courseDAO = new CourseDAO();
                    ArrayList<Course> courseList = courseDAO.getAllCourses();

                    if (courseList.isEmpty()) {
                        System.out.println("No courses found.");
                    } else {
                        for (Course c : courseList) {
                            System.out.println("\nCourse ID: " + c.getCourseId());
                            System.out.println("Course Name: " + c.getCourseName());
                            System.out.println("Course Code: " + c.getCourseCode());
                            System.out.println("Description: " + c.getDescription());
                            System.out.println("Instructor ID: " + c.getInstructorId());
                        }
                    }
                    break;
                }

                case 9: {
                    System.out.print("Enter Course ID: ");
                    int searchCourseId = readInt(sc);

                    CourseDAO courseDAO = new CourseDAO();
                    Course searchedCourse = courseDAO.getCourseById(searchCourseId);

                    if (searchedCourse != null) {
                        System.out.println("Course ID: " + searchedCourse.getCourseId());
                        System.out.println("Course Name: " + searchedCourse.getCourseName());
                        System.out.println("Course Code: " + searchedCourse.getCourseCode());
                        System.out.println("Description: " + searchedCourse.getDescription());
                        System.out.println("Instructor ID: " + searchedCourse.getInstructorId());
                    } else {
                        System.out.println("Course not found!");
                    }
                    break;
                }

                case 10: {
                    System.out.print("Enter Course ID to update: ");
                    int updateCourseId = readInt(sc);

                    CourseDAO courseDAO = new CourseDAO();
                    Course existingCourse = courseDAO.getCourseById(updateCourseId);

                    if (existingCourse == null) {
                        System.out.println("Course not found!");
                        break;
                    }

                    System.out.println("Press Enter to keep existing values.");

                    System.out.print("Course Name (" + existingCourse.getCourseName() + "): ");
                    String updatedCourseName = sc.nextLine();
                    if (updatedCourseName.isEmpty()) {
                        updatedCourseName = existingCourse.getCourseName();
                    }

                    System.out.print("Course Code (" + existingCourse.getCourseCode() + "): ");
                    String updatedCourseCode = sc.nextLine();
                    if (updatedCourseCode.isEmpty()) {
                        updatedCourseCode = existingCourse.getCourseCode();
                    }

                    System.out.print("Description (" + existingCourse.getDescription() + "): ");
                    String updatedDescription = sc.nextLine();
                    if (updatedDescription.isEmpty()) {
                        updatedDescription = existingCourse.getDescription();
                    }

                    System.out.print("Instructor ID (" + existingCourse.getInstructorId() + "): ");
                    String instructorInput = sc.nextLine();

                    int updatedInstructorId = instructorInput.isEmpty()
                            ? existingCourse.getInstructorId()
                            : readIntFromInput(instructorInput, sc);

                    Course updatedCourse = new Course(
                            updatedCourseName,
                            updatedCourseCode,
                            updatedDescription,
                            updatedInstructorId
                    );

                    updatedCourse.setCourseId(updateCourseId);

                    if (courseDAO.updateCourse(updatedCourse)) {
                        System.out.println("Course updated successfully!");
                    } else {
                        System.out.println("Course update failed!");
                    }
                    break;
                }

                case 11: {
                    System.out.print("Enter Course ID to delete: ");
                    int deleteCourseId = readInt(sc);

                    CourseDAO courseDAO = new CourseDAO();
                    Course courseToDelete = courseDAO.getCourseById(deleteCourseId);

                    if (courseToDelete == null) {
                        System.out.println("Course not found!");
                    } else {
                        System.out.print("Are you sure? (yes/no): ");
                        String confirmation = sc.nextLine();

                        if (confirmation.equalsIgnoreCase("yes")) {
                            if (courseDAO.deleteCourse(deleteCourseId)) {
                                System.out.println("Course deleted successfully!");
                            } else {
                                System.out.println("Unable to delete course. It may be linked to enrollments or assignments.");
                            }
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    break;
                }

                case 13: {
                    System.out.println("\n--- Add Instructor ---");

                    System.out.print("First Name: ");
                    String instructorFirst = sc.nextLine();

                    System.out.print("Last Name: ");
                    String instructorLast = sc.nextLine();

                    System.out.print("Email: ");
                    String instructorEmail = sc.nextLine();

                    System.out.print("Password: ");
                    String instructorPassword = sc.nextLine();

                    System.out.print("Department: ");
                    String instructorDepartment = sc.nextLine();

                    Instructor newInstructor = new Instructor(
                            instructorFirst,
                            instructorLast,
                            instructorEmail,
                            instructorPassword,
                            instructorDepartment
                    );

                    InstructorDAO instructorDAO = new InstructorDAO();

                    if (instructorDAO.addInstructor(newInstructor)) {
                        System.out.println("Instructor added successfully!");
                    } else {
                        System.out.println("Unable to add instructor.");
                    }
                    break;
                }

                case 14: {
                    System.out.println("\n--- All Instructors ---");

                    InstructorDAO instructorDAO = new InstructorDAO();
                    ArrayList<Instructor> instructorList =
                            instructorDAO.getAllInstructors();

                    if (instructorList.isEmpty()) {
                        System.out.println("No instructors found.");
                    } else {
                        for (Instructor i : instructorList) {
                            System.out.println("\nInstructor ID: " + i.getInstructorId());
                            System.out.println("Name: " + i.getFirstName() + " " + i.getLastName());
                            System.out.println("Email: " + i.getEmail());
                            System.out.println("Department: " + i.getDepartment());
                        }
                    }
                    break;
                }

                case 15: {
                    System.out.print("Enter Instructor ID: ");
                    int searchInstructorId = readInt(sc);

                    InstructorDAO instructorDAO = new InstructorDAO();
                    Instructor searchedInstructor =
                            instructorDAO.getInstructorById(searchInstructorId);

                    if (searchedInstructor != null) {
                        System.out.println("Instructor ID: " + searchedInstructor.getInstructorId());
                        System.out.println("Name: " + searchedInstructor.getFirstName()
                                + " " + searchedInstructor.getLastName());
                        System.out.println("Email: " + searchedInstructor.getEmail());
                        System.out.println("Department: " + searchedInstructor.getDepartment());
                    } else {
                        System.out.println("Instructor not found!");
                    }
                    break;
                }

                case 16: {
                    System.out.print("Enter Instructor ID to update: ");
                    int updateInstructorId = readInt(sc);

                    InstructorDAO instructorDAO = new InstructorDAO();
                    Instructor existingInstructor =
                            instructorDAO.getInstructorById(updateInstructorId);

                    if (existingInstructor == null) {
                        System.out.println("Instructor not found!");
                        break;
                    }

                    System.out.println("Press Enter to keep existing values.");

                    System.out.print("First Name (" + existingInstructor.getFirstName() + "): ");
                    String newInstructorFirst = sc.nextLine();
                    if (newInstructorFirst.isEmpty()) {
                        newInstructorFirst = existingInstructor.getFirstName();
                    }

                    System.out.print("Last Name (" + existingInstructor.getLastName() + "): ");
                    String newInstructorLast = sc.nextLine();
                    if (newInstructorLast.isEmpty()) {
                        newInstructorLast = existingInstructor.getLastName();
                    }

                    System.out.print("Email (" + existingInstructor.getEmail() + "): ");
                    String newInstructorEmail = sc.nextLine();
                    if (newInstructorEmail.isEmpty()) {
                        newInstructorEmail = existingInstructor.getEmail();
                    }

                    System.out.print("Password: ");
                    String newInstructorPassword = sc.nextLine();
                    if (newInstructorPassword.isEmpty()) {
                        newInstructorPassword = existingInstructor.getPassword();
                    }

                    System.out.print("Department (" + existingInstructor.getDepartment() + "): ");
                    String newInstructorDepartment = sc.nextLine();
                    if (newInstructorDepartment.isEmpty()) {
                        newInstructorDepartment = existingInstructor.getDepartment();
                    }

                    Instructor updatedInstructor = new Instructor(
                            newInstructorFirst,
                            newInstructorLast,
                            newInstructorEmail,
                            newInstructorPassword,
                            newInstructorDepartment
                    );

                    updatedInstructor.setInstructorId(updateInstructorId);

                    if (instructorDAO.updateInstructor(updatedInstructor)) {
                        System.out.println("Instructor updated successfully!");
                    } else {
                        System.out.println("Instructor update failed!");
                    }
                    break;
                }

                case 17: {
                    System.out.print("Enter Instructor ID to delete: ");
                    int deleteInstructorId = readInt(sc);

                    InstructorDAO instructorDAO = new InstructorDAO();
                    Instructor instructorToDelete =
                            instructorDAO.getInstructorById(deleteInstructorId);

                    if (instructorToDelete == null) {
                        System.out.println("Instructor not found!");
                    } else {
                        System.out.print("Are you sure? (yes/no): ");
                        String confirmation = sc.nextLine();

                        if (confirmation.equalsIgnoreCase("yes")) {
                            if (instructorDAO.deleteInstructor(deleteInstructorId)) {
                                System.out.println("Instructor deleted successfully!");
                            } else {
                                System.out.println("Unable to delete instructor. It may be linked to courses, assignments or announcements.");
                            }
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    break;
                }

                case 19: {
                    // Add Enrollment
                    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

                    System.out.print("Enter Student ID: ");
                    int studentId = readInt(sc);

                    System.out.print("Enter Course ID: ");
                    int courseId = readInt(sc);

                    System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
                    String enrollmentDate = readDate(sc);

                    System.out.print("Enter Status (Active/Completed/Dropped): ");
                    String status = sc.nextLine();

                    Enrollment enrollment = new Enrollment(
                            studentId, courseId, enrollmentDate, status);

                    if (enrollmentDAO.addEnrollment(enrollment)) {
                        System.out.println("Enrollment added successfully!");
                    } else {
                        System.out.println("Failed to add enrollment!");
                    }
                    break;
                }

                case 20: {
                    // View All Enrollments
                    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
                    ArrayList<Enrollment> enrollments =
                            enrollmentDAO.getAllEnrollments();

                    if (enrollments.isEmpty()) {
                        System.out.println("No enrollments found!");
                    } else {
                        for (Enrollment e : enrollments) {
                            System.out.println("\nEnrollment ID: " + e.getEnrollmentId());
                            System.out.println("Student ID: " + e.getStudentId());
                            System.out.println("Course ID: " + e.getCourseId());
                            System.out.println("Date: " + e.getEnrollmentDate());
                            System.out.println("Status: " + e.getStatus());
                        }
                    }
                    break;
                }

                case 21: {
                    // Search Enrollment
                    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

                    System.out.print("Enter Enrollment ID: ");
                    int searchId = readInt(sc);

                    Enrollment e = enrollmentDAO.getEnrollmentById(searchId);

                    if (e != null) {
                        System.out.println("Enrollment ID: " + e.getEnrollmentId());
                        System.out.println("Student ID: " + e.getStudentId());
                        System.out.println("Course ID: " + e.getCourseId());
                        System.out.println("Date: " + e.getEnrollmentDate());
                        System.out.println("Status: " + e.getStatus());
                    } else {
                        System.out.println("Enrollment not found!");
                    }
                    break;
                }

                case 22: {
                    // Update Enrollment
                    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

                    System.out.print("Enter Enrollment ID to update: ");
                    int updateId = readInt(sc);

                    Enrollment existing = enrollmentDAO.getEnrollmentById(updateId);

                    if (existing == null) {
                        System.out.println("Enrollment not found!");
                        break;
                    }

                    System.out.println("Press Enter to keep the existing value.");

                    System.out.print("Student ID (" + existing.getStudentId() + "): ");
                    String studentInput = sc.nextLine();
                    int updatedStudentId = studentInput.isEmpty()
                            ? existing.getStudentId()
                            : readIntFromInput(studentInput, sc);

                    System.out.print("Course ID (" + existing.getCourseId() + "): ");
                    String courseInput = sc.nextLine();
                    int updatedCourseId = courseInput.isEmpty()
                            ? existing.getCourseId()
                            : readIntFromInput(courseInput, sc);

                    System.out.print("Date (" + existing.getEnrollmentDate() + "): ");
                    String updatedDate = sc.nextLine().trim();

                    if (updatedDate.isEmpty()) {
                        updatedDate = existing.getEnrollmentDate();
                    } else {
                        updatedDate = validateDate(updatedDate, sc);
                    }

                    System.out.print("Status (" + existing.getStatus() + "): ");
                    String updatedStatus = sc.nextLine();
                    if (updatedStatus.isEmpty()) {
                        updatedStatus = existing.getStatus();
                    }

                    Enrollment updatedEnrollment = new Enrollment(
                            updatedStudentId, updatedCourseId,
                            updatedDate, updatedStatus);

                    updatedEnrollment.setEnrollmentId(updateId);

                    if (enrollmentDAO.updateEnrollment(updatedEnrollment)) {
                        System.out.println("Enrollment updated successfully!");
                    } else {
                        System.out.println("Update failed!");
                    }
                    break;
                }

                case 23: {
                    // Delete Enrollment
                    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

                    System.out.print("Enter Enrollment ID to delete: ");
                    int deleteId = readInt(sc);

                    System.out.print("Are you sure? (yes/no): ");
                    String confirmation = sc.nextLine();

                    if (confirmation.equalsIgnoreCase("yes")) {
                        if (enrollmentDAO.deleteEnrollment(deleteId)) {
                            System.out.println("Enrollment deleted successfully!");
                        } else {
                            System.out.println("Enrollment not found or deletion failed!");
                        }
                    } else {
                        System.out.println("Deletion cancelled.");
                    }
                    break;
                }

                case 25: {
                    // Add Assignment
                    AssignmentDAO assignmentDAO = new AssignmentDAO();

                    System.out.print("Enter Course ID: ");
                    int courseId = readInt(sc);

                    System.out.print("Enter Instructor ID: ");
                    int instructorId = readInt(sc);

                    System.out.print("Enter Assignment Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Description: ");
                    String description = sc.nextLine();

                    System.out.print("Enter Due Date (YYYY-MM-DD HH:MM:SS): ");
                    String dueDate = readDateTime(sc);

                    System.out.print("Enter Maximum Marks: ");
                    int maxMarks = readInt(sc);

                    Assignment assignment = new Assignment(
                            courseId, instructorId, title,
                            description, dueDate, maxMarks);

                    if (assignmentDAO.addAssignment(assignment)) {
                        System.out.println("Assignment added successfully!");
                    } else {
                        System.out.println("Failed to add assignment!");
                    }
                    break;
                }

                case 26: {
                    // View All Assignments
                    AssignmentDAO assignmentDAO = new AssignmentDAO();
                    ArrayList<Assignment> assignments =
                            assignmentDAO.getAllAssignments();

                    if (assignments.isEmpty()) {
                        System.out.println("No assignments found!");
                    } else {
                        for (Assignment a : assignments) {
                            System.out.println("\nAssignment ID: " + a.getAssignmentId());
                            System.out.println("Course ID: " + a.getCourseId());
                            System.out.println("Instructor ID: " + a.getInstructorId());
                            System.out.println("Title: " + a.getTitle());
                            System.out.println("Description: " + a.getDescription());
                            System.out.println("Due Date: " + a.getDueDate());
                            System.out.println("Maximum Marks: " + a.getMaxMarks());
                        }
                    }
                    break;
                }

                case 27: {
                    // Search Assignment
                    AssignmentDAO assignmentDAO = new AssignmentDAO();

                    System.out.print("Enter Assignment ID: ");
                    int searchId = readInt(sc);

                    Assignment a = assignmentDAO.getAssignmentById(searchId);

                    if (a != null) {
                        System.out.println("Assignment ID: " + a.getAssignmentId());
                        System.out.println("Course ID: " + a.getCourseId());
                        System.out.println("Instructor ID: " + a.getInstructorId());
                        System.out.println("Title: " + a.getTitle());
                        System.out.println("Description: " + a.getDescription());
                        System.out.println("Due Date: " + a.getDueDate());
                        System.out.println("Maximum Marks: " + a.getMaxMarks());
                    } else {
                        System.out.println("Assignment not found!");
                    }
                    break;
                }

                case 28: {
                    // Update Assignment
                    AssignmentDAO assignmentDAO = new AssignmentDAO();

                    System.out.print("Enter Assignment ID to update: ");
                    int updateId = readInt(sc);

                    Assignment existing = assignmentDAO.getAssignmentById(updateId);

                    if (existing == null) {
                        System.out.println("Assignment not found!");
                        break;
                    }

                    System.out.println("Press Enter to keep the existing value.");

                    System.out.print("Course ID (" + existing.getCourseId() + "): ");
                    String courseInput = sc.nextLine();
                    int updatedCourseId = courseInput.isEmpty()
                            ? existing.getCourseId()
                            : readIntFromInput(courseInput, sc);

                    System.out.print("Instructor ID (" + existing.getInstructorId() + "): ");
                    String instructorInput = sc.nextLine();
                    int updatedInstructorId = instructorInput.isEmpty()
                            ? existing.getInstructorId()
                            : readIntFromInput(instructorInput, sc);

                    System.out.print("Title (" + existing.getTitle() + "): ");
                    String updatedTitle = sc.nextLine();
                    if (updatedTitle.isEmpty()) updatedTitle = existing.getTitle();

                    System.out.print("Description (" + existing.getDescription() + "): ");
                    String updatedDescription = sc.nextLine();
                    if (updatedDescription.isEmpty()) {
                        updatedDescription = existing.getDescription();
                    }

                    System.out.print("Due Date (" + existing.getDueDate() + "): ");
                    String updatedDueDate = sc.nextLine().trim();

                    if (updatedDueDate.isEmpty()) {
                        updatedDueDate = existing.getDueDate();
                    } else {
                        updatedDueDate = validateDateTime(updatedDueDate, sc);
                    }

                    System.out.print("Maximum Marks (" + existing.getMaxMarks() + "): ");
                    String marksInput = sc.nextLine();
                    int updatedMaxMarks = marksInput.isEmpty()
                            ? existing.getMaxMarks()
                            : readIntFromInput(marksInput, sc);

                    Assignment updatedAssignment = new Assignment(
                            updatedCourseId, updatedInstructorId,
                            updatedTitle, updatedDescription,
                            updatedDueDate, updatedMaxMarks);

                    updatedAssignment.setAssignmentId(updateId);

                    if (assignmentDAO.updateAssignment(updatedAssignment)) {
                        System.out.println("Assignment updated successfully!");
                    } else {
                        System.out.println("Update failed!");
                    }
                    break;
                }

                case 29: {
                    // Delete Assignment
                    AssignmentDAO assignmentDAO = new AssignmentDAO();

                    System.out.print("Enter Assignment ID to delete: ");
                    int deleteId = readInt(sc);

                    System.out.print("Are you sure? (yes/no): ");
                    String confirmation = sc.nextLine();

                    if (confirmation.equalsIgnoreCase("yes")) {
                        if (assignmentDAO.deleteAssignment(deleteId)) {
                            System.out.println("Assignment deleted successfully!");
                        } else {
                            System.out.println("Assignment not found or deletion failed!");
                        }
                    } else {
                        System.out.println("Deletion cancelled.");
                    }
                    break;
                }

                case 31: {
                    // Add Submission
                    SubmissionDAO submissionDAO = new SubmissionDAO();

                    System.out.print("Enter Assignment ID: ");
                    int assignmentId = readInt(sc);

                    System.out.print("Enter Student ID: ");
                    int studentId = readInt(sc);

                    System.out.print("Enter Submission Text: ");
                    String submissionText = sc.nextLine();

                    System.out.print("Enter File Path (or leave blank): ");
                    String filePath = sc.nextLine();

                    System.out.print("Enter Submission Date (YYYY-MM-DD HH:MM:SS): ");
                    String submittedAt = readDateTime(sc);

                    System.out.print("Enter Status (Submitted/Late/Graded): ");
                    String status = sc.nextLine();

                    Submission submission = new Submission(
                            assignmentId, studentId, submissionText,
                            filePath, submittedAt, status);

                    if (submissionDAO.addSubmission(submission)) {
                        System.out.println("Submission added successfully!");
                    } else {
                        System.out.println("Failed to add submission!");
                    }
                    break;
                }

                case 32: {
                    // View All Submissions
                    SubmissionDAO submissionDAO = new SubmissionDAO();
                    ArrayList<Submission> submissions =
                            submissionDAO.getAllSubmissions();

                    if (submissions.isEmpty()) {
                        System.out.println("No submissions found!");
                    } else {
                        for (Submission s : submissions) {
                            System.out.println("\nSubmission ID: " + s.getSubmissionId());
                            System.out.println("Assignment ID: " + s.getAssignmentId());
                            System.out.println("Student ID: " + s.getStudentId());
                            System.out.println("Submission Text: " + s.getSubmissionText());
                            System.out.println("File Path: " + s.getFilePath());
                            System.out.println("Submitted At: " + s.getSubmittedAt());
                            System.out.println("Status: " + s.getStatus());
                        }
                    }
                    break;
                }

                case 33: {
                    // Search Submission
                    SubmissionDAO submissionDAO = new SubmissionDAO();

                    System.out.print("Enter Submission ID: ");
                    int searchId = readInt(sc);

                    Submission s = submissionDAO.getSubmissionById(searchId);

                    if (s != null) {
                        System.out.println("Submission ID: " + s.getSubmissionId());
                        System.out.println("Assignment ID: " + s.getAssignmentId());
                        System.out.println("Student ID: " + s.getStudentId());
                        System.out.println("Submission Text: " + s.getSubmissionText());
                        System.out.println("File Path: " + s.getFilePath());
                        System.out.println("Submitted At: " + s.getSubmittedAt());
                        System.out.println("Status: " + s.getStatus());
                    } else {
                        System.out.println("Submission not found!");
                    }
                    break;
                }

                case 34: {
                    // Update Submission
                    SubmissionDAO submissionDAO = new SubmissionDAO();

                    System.out.print("Enter Submission ID to update: ");
                    int updateId = readInt(sc);

                    Submission existing = submissionDAO.getSubmissionById(updateId);

                    if (existing == null) {
                        System.out.println("Submission not found!");
                        break;
                    }

                    System.out.println("Press Enter to keep the existing value.");

                    System.out.print("Assignment ID (" + existing.getAssignmentId() + "): ");
                    String assignmentInput = sc.nextLine();
                    int updatedAssignmentId = assignmentInput.isEmpty()
                            ? existing.getAssignmentId()
                            : readIntFromInput(assignmentInput, sc);

                    System.out.print("Student ID (" + existing.getStudentId() + "): ");
                    String studentInput = sc.nextLine();
                    int updatedStudentId = studentInput.isEmpty()
                            ? existing.getStudentId()
                            : readIntFromInput(studentInput, sc);

                    System.out.print("Submission Text (" + existing.getSubmissionText() + "): ");
                    String updatedText = sc.nextLine();
                    if (updatedText.isEmpty()) updatedText = existing.getSubmissionText();

                    System.out.print("File Path (" + existing.getFilePath() + "): ");
                    String updatedFilePath = sc.nextLine();
                    if (updatedFilePath.isEmpty()) updatedFilePath = existing.getFilePath();

                    System.out.print("Submitted At (" + existing.getSubmittedAt() + "): ");
                    String updatedSubmittedAt = sc.nextLine().trim();

                    if (updatedSubmittedAt.isEmpty()) {
                        updatedSubmittedAt = existing.getSubmittedAt();
                    } else {
                        updatedSubmittedAt = validateDateTime(updatedSubmittedAt, sc);
                    }

                    System.out.print("Status (" + existing.getStatus() + "): ");
                    String updatedStatus = sc.nextLine();
                    if (updatedStatus.isEmpty()) {
                        updatedStatus = existing.getStatus();
                    }

                    Submission updatedSubmission = new Submission(
                            updatedAssignmentId, updatedStudentId,
                            updatedText, updatedFilePath,
                            updatedSubmittedAt, updatedStatus);

                    updatedSubmission.setSubmissionId(updateId);

                    if (submissionDAO.updateSubmission(updatedSubmission)) {
                        System.out.println("Submission updated successfully!");
                    } else {
                        System.out.println("Update failed!");
                    }
                    break;
                }

                case 35: {
                    // Delete Submission
                    SubmissionDAO submissionDAO = new SubmissionDAO();

                    System.out.print("Enter Submission ID to delete: ");
                    int deleteId = readInt(sc);

                    System.out.print("Are you sure? (yes/no): ");
                    String confirmation = sc.nextLine();

                    if (confirmation.equalsIgnoreCase("yes")) {
                        if (submissionDAO.deleteSubmission(deleteId)) {
                            System.out.println("Submission deleted successfully!");
                        } else {
                            System.out.println("Submission not found or deletion failed!");
                        }
                    } else {
                        System.out.println("Deletion cancelled.");
                    }
                    break;
                }

                case 37: {
                    GradeDAO gradeDAO = new GradeDAO();

                    System.out.print("Enter Submission ID: ");
                    int submissionId = readInt(sc);

                    System.out.print("Enter Instructor ID: ");
                    int instructorId = readInt(sc);

                    System.out.print("Enter Marks Obtained: ");
                    double marks = readDouble(sc);

                    System.out.print("Enter Feedback: ");
                    String feedback = sc.nextLine();

                    Grade grade = new Grade(submissionId, instructorId, marks, feedback);

                    if (gradeDAO.addGrade(grade))
                        System.out.println("Grade added successfully!");
                    else
                        System.out.println("Failed to add grade!");

                    break;
                }

                case 38: {
                    GradeDAO gradeDAO = new GradeDAO();
                    ArrayList<Grade> grades = gradeDAO.getAllGrades();

                    if (grades.isEmpty()) {
                        System.out.println("No grades found!");
                    } else {
                        for (Grade g : grades) {
                            System.out.println("\nGrade ID: " + g.getGradeId());
                            System.out.println("Submission ID: " + g.getSubmissionId());
                            System.out.println("Instructor ID: " + g.getInstructorId());
                            System.out.println("Marks: " + g.getMarksObtained());
                            System.out.println("Feedback: " + g.getFeedback());
                        }
                    }

                    break;
                }

                case 39: {
                    GradeDAO gradeDAO = new GradeDAO();

                    System.out.print("Enter Grade ID: ");
                    int searchId = readInt(sc);

                    Grade g = gradeDAO.getGradeById(searchId);

                    if (g == null) {
                        System.out.println("Grade not found!");
                    } else {
                        System.out.println("Grade ID: " + g.getGradeId());
                        System.out.println("Submission ID: " + g.getSubmissionId());
                        System.out.println("Instructor ID: " + g.getInstructorId());
                        System.out.println("Marks: " + g.getMarksObtained());
                        System.out.println("Feedback: " + g.getFeedback());
                    }

                    break;
                }

                case 40: {
                    GradeDAO gradeDAO = new GradeDAO();

                    System.out.print("Enter Grade ID to update: ");
                    int updateId = readInt(sc);

                    Grade existing = gradeDAO.getGradeById(updateId);

                    if (existing == null) {
                        System.out.println("Grade not found!");
                        break;
                    }

                    System.out.println("Press Enter to keep the existing value.");

                    System.out.print("Submission ID (" + existing.getSubmissionId() + "): ");
                    String submissionInput = sc.nextLine();
                    int updatedSubmissionId = submissionInput.isEmpty()
                            ? existing.getSubmissionId()
                            : readIntFromInput(submissionInput, sc);

                    System.out.print("Instructor ID (" + existing.getInstructorId() + "): ");
                    String instructorInput = sc.nextLine();
                    int updatedInstructorId = instructorInput.isEmpty()
                            ? existing.getInstructorId()
                            : readIntFromInput(instructorInput, sc);

                    System.out.print("Marks (" + existing.getMarksObtained() + "): ");
                    String marksInput = sc.nextLine();
                    double updatedMarks = marksInput.isEmpty()
                            ? existing.getMarksObtained()
                            : readDoubleFromInput(marksInput, sc);

                    System.out.print("Feedback (" + existing.getFeedback() + "): ");
                    String updatedFeedback = sc.nextLine();
                    if (updatedFeedback.isEmpty())
                        updatedFeedback = existing.getFeedback();

                    Grade updated = new Grade(
                            updatedSubmissionId,
                            updatedInstructorId,
                            updatedMarks,
                            updatedFeedback
                    );

                    updated.setGradeId(updateId);

                    if (gradeDAO.updateGrade(updated))
                        System.out.println("Grade updated successfully!");
                    else
                        System.out.println("Update failed!");

                    break;
                }

                case 41: {
                    GradeDAO gradeDAO = new GradeDAO();

                    System.out.print("Enter Grade ID to delete: ");
                    int deleteId = readInt(sc);

                    System.out.print("Are you sure? (yes/no): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("yes")) {
                        if (gradeDAO.deleteGrade(deleteId))
                            System.out.println("Grade deleted successfully!");
                        else
                            System.out.println("Grade not found or deletion failed!");
                    } else {
                        System.out.println("Deletion cancelled.");
                    }

                    break;
                }

                case 43: {
                    AnnouncementDAO announcementDAO = new AnnouncementDAO();

                    System.out.print("Enter Instructor ID: ");
                    int instructorId = readInt(sc);

                    System.out.print("Enter Course ID: ");
                    int courseId = readInt(sc);

                    System.out.print("Enter Announcement Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Announcement Message: ");
                    String message = sc.nextLine();

                    Announcement announcement = new Announcement(
                            instructorId, courseId, title, message);

                    if (announcementDAO.addAnnouncement(announcement))
                        System.out.println("Announcement added successfully!");
                    else
                        System.out.println("Failed to add announcement!");

                    break;
                }

                case 44: {
                    AnnouncementDAO announcementDAO = new AnnouncementDAO();
                    ArrayList<Announcement> announcements =
                            announcementDAO.getAllAnnouncements();

                    if (announcements.isEmpty()) {
                        System.out.println("No announcements found!");
                    } else {
                        for (Announcement a : announcements) {
                            System.out.println("\nAnnouncement ID: " + a.getAnnouncementId());
                            System.out.println("Instructor ID: " + a.getInstructorId());
                            System.out.println("Course ID: " + a.getCourseId());
                            System.out.println("Title: " + a.getTitle());
                            System.out.println("Message: " + a.getMessage());
                        }
                    }

                    break;
                }

                case 45: {
                    AnnouncementDAO announcementDAO = new AnnouncementDAO();

                    System.out.print("Enter Announcement ID: ");
                    int searchId = readInt(sc);

                    Announcement a = announcementDAO.getAnnouncementById(searchId);

                    if (a == null) {
                        System.out.println("Announcement not found!");
                    } else {
                        System.out.println("Announcement ID: " + a.getAnnouncementId());
                        System.out.println("Instructor ID: " + a.getInstructorId());
                        System.out.println("Course ID: " + a.getCourseId());
                        System.out.println("Title: " + a.getTitle());
                        System.out.println("Message: " + a.getMessage());
                    }

                    break;
                }

                case 46: {
                    AnnouncementDAO announcementDAO = new AnnouncementDAO();

                    System.out.print("Enter Announcement ID to update: ");
                    int updateId = readInt(sc);

                    Announcement existing =
                            announcementDAO.getAnnouncementById(updateId);

                    if (existing == null) {
                        System.out.println("Announcement not found!");
                        break;
                    }

                    System.out.println("Press Enter to keep the existing value.");

                    System.out.print("Instructor ID (" + existing.getInstructorId() + "): ");
                    String instructorInput = sc.nextLine();
                    int updatedInstructorId = instructorInput.isEmpty()
                            ? existing.getInstructorId()
                            : readIntFromInput(instructorInput, sc);

                    System.out.print("Course ID (" + existing.getCourseId() + "): ");
                    String courseInput = sc.nextLine();
                    int updatedCourseId = courseInput.isEmpty()
                            ? existing.getCourseId()
                            : readIntFromInput(courseInput, sc);

                    System.out.print("Title (" + existing.getTitle() + "): ");
                    String updatedTitle = sc.nextLine();
                    if (updatedTitle.isEmpty())
                        updatedTitle = existing.getTitle();

                    System.out.print("Message (" + existing.getMessage() + "): ");
                    String updatedMessage = sc.nextLine();
                    if (updatedMessage.isEmpty())
                        updatedMessage = existing.getMessage();

                    Announcement updated = new Announcement(
                            updatedInstructorId,
                            updatedCourseId,
                            updatedTitle,
                            updatedMessage
                    );

                    updated.setAnnouncementId(updateId);

                    if (announcementDAO.updateAnnouncement(updated))
                        System.out.println("Announcement updated successfully!");
                    else
                        System.out.println("Update failed!");

                    break;
                }

                case 47: {
                    AnnouncementDAO announcementDAO = new AnnouncementDAO();

                    System.out.print("Enter Announcement ID to delete: ");
                    int deleteId = readInt(sc);

                    System.out.print("Are you sure? (yes/no): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("yes")) {
                        if (announcementDAO.deleteAnnouncement(deleteId))
                            System.out.println("Announcement deleted successfully!");
                        else
                            System.out.println("Announcement not found or deletion failed!");
                    } else {
                        System.out.println("Deletion cancelled.");
                    }

                    break;
                }
                
                case 48:
                    System.out.println("Thank you for using Coursework Management System!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 48);

        sc.close();
    }

    // Reads an integer safely. Keeps asking until the user enters a valid number.
    public static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a valid whole number: ");
            }
        }
    }

    // Validates an integer that was already entered in an update field.
    public static int readIntFromInput(String input, Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a valid whole number: ");
                input = sc.nextLine();
            }
        }
    }

    // Reads a decimal number safely.
    public static double readDouble(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a valid decimal number: ");
            }
        }
    }

    // Validates a decimal value that was already entered in an update field.
    public static double readDoubleFromInput(String input, Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a valid decimal number: ");
                input = sc.nextLine();
            }
        }
    }

    // Validates date in YYYY-MM-DD format
public static String readDate(Scanner sc) {
    while (true) {
        String date = sc.nextLine().trim();

        try {
            DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);

            LocalDate.parse(date, formatter);
            return date;

        } catch (Exception e) {
            System.out.print("Invalid date! Enter date in YYYY-MM-DD format: ");
        }
    }
}

// Validates date and time in YYYY-MM-DD HH:MM:SS format
public static String readDateTime(Scanner sc) {
    while (true) {
        String dateTime = sc.nextLine().trim();

        try {
            DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
                .withResolverStyle(ResolverStyle.STRICT);

            LocalDateTime.parse(dateTime, formatter);
            return dateTime;

        } catch (Exception e) {
            System.out.print("Invalid date/time! Enter YYYY-MM-DD HH:MM:SS: ");
        }
    }
}

public static String validateDateTime(String dateTime, Scanner sc) {
    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
            .withResolverStyle(ResolverStyle.STRICT);

    while (true) {
        try {
            LocalDateTime.parse(dateTime, formatter);
            return dateTime;
        } catch (Exception e) {
            System.out.print("Invalid date/time! Enter YYYY-MM-DD HH:MM:SS: ");
            dateTime = sc.nextLine().trim();
        }
    }
}

public static String validateDate(String date, Scanner sc) {
    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);

    while (true) {
        try {
            LocalDate.parse(date, formatter);
            return date;
        } catch (Exception e) {
            System.out.print("Invalid date! Enter YYYY-MM-DD: ");
            date = sc.nextLine().trim();
        }
    }
}
}