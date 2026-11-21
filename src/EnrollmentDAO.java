
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Enrollment;

public class EnrollmentDAO {

    // Add a new enrollment
    public boolean addEnrollment(Enrollment enrollment) {
        if (!isValidStatus(enrollment.getStatus())) {
    System.out.println("Invalid status! Use Active, Completed or Dropped.");
    return false;
}
        String query = "INSERT INTO enrollments " +
                "(student_id, course_id, enrollment_date, status) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setString(3, enrollment.getEnrollmentDate());
            ps.setString(4, enrollment.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding enrollment: " + e.getMessage());
            return false;
        }
    }

    // View all enrollments
    public ArrayList<Enrollment> getAllEnrollments() {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        String query = "SELECT * FROM enrollments";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getString("enrollment_date"),
                        rs.getString("status")
                );

                enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
                enrollments.add(enrollment);
            }

        } catch (SQLException e) {
            System.out.println("Error viewing enrollments: " + e.getMessage());
        }

        return enrollments;
    }

    // Search enrollment by ID
    public Enrollment getEnrollmentById(int id) {
        String query = "SELECT * FROM enrollments WHERE enrollment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Enrollment enrollment = new Enrollment(
                            rs.getInt("student_id"),
                            rs.getInt("course_id"),
                            rs.getString("enrollment_date"),
                            rs.getString("status")
                    );

                    enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
                    return enrollment;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching enrollment: " + e.getMessage());
        }

        return null;
    }

    // Update an enrollment
    public boolean updateEnrollment(Enrollment enrollment) {
       if (!isValidStatus(enrollment.getStatus())) {
    System.out.println("Invalid status! Use Active, Completed or Dropped.");
    return false;
}
        String query = "UPDATE enrollments SET student_id=?, course_id=?, " +
                "enrollment_date=?, status=? WHERE enrollment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setString(3, enrollment.getEnrollmentDate());
            ps.setString(4, enrollment.getStatus());
            ps.setInt(5, enrollment.getEnrollmentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating enrollment: " + e.getMessage());
            return false;
        }
    }

    // Delete an enrollment
    public boolean deleteEnrollment(int id) {
        String query = "DELETE FROM enrollments WHERE enrollment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting enrollment: " + e.getMessage());
            return false;
        }
    }
    // Validate enrollment status
private boolean isValidStatus(String status) {
    return status != null &&
           (status.equals("Active") ||
            status.equals("Completed") ||
            status.equals("Dropped"));
}
}


