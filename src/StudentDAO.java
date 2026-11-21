
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Student;

public class StudentDAO {

    // Add a new student
    public boolean addStudent(Student student) {

        String query = "INSERT INTO students " +
                "(first_name, last_name, email, password, department, semester, phone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPassword());
            ps.setString(5, student.getDepartment());
            ps.setInt(6, student.getSemester());
            ps.setString(7, student.getPhone());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    // Display all students
    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> students = new ArrayList<>();

        String query = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("department"),
                        rs.getInt("semester"),
                        rs.getString("phone")
                );

                student.setStudentId(rs.getInt("student_id"));

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }

        return students;
    }

    // Search student using student ID
    public Student getStudentById(int id) {

        String query = "SELECT * FROM students WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Student student = new Student(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("department"),
                            rs.getInt("semester"),
                            rs.getString("phone")
                    );

                    student.setStudentId(rs.getInt("student_id"));

                    return student;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching student: " + e.getMessage());
        }

        return null; 
    }

    public boolean updateStudent(Student student) {

        String query = "UPDATE students SET first_name=?, last_name=?, " +
                "email=?, department=?, semester=?, phone=? " +
                "WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getDepartment());
            ps.setInt(5, student.getSemester());
            ps.setString(6, student.getPhone());
            ps.setInt(7, student.getStudentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteStudent(int id) {

    String query = "DELETE FROM students WHERE student_id=?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setInt(1, id);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error deleting student: " + e.getMessage());
        return false;
    }
}
}
