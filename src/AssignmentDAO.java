
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Assignment;

public class AssignmentDAO {

    // Add a new assignment
    public boolean addAssignment(Assignment assignment) {
        String query = "INSERT INTO assignments " +
                "(course_id, instructor_id, title, description, due_date, max_marks) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, assignment.getCourseId());
            ps.setInt(2, assignment.getInstructorId());
            ps.setString(3, assignment.getTitle());
            ps.setString(4, assignment.getDescription());
            ps.setString(5, assignment.getDueDate());
            ps.setInt(6, assignment.getMaxMarks());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding assignment: " + e.getMessage());
            return false;
        }
    }

    // View all assignments
    public ArrayList<Assignment> getAllAssignments() {
        ArrayList<Assignment> assignments = new ArrayList<>();
        String query = "SELECT * FROM assignments";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Assignment assignment = new Assignment(
                        rs.getInt("course_id"),
                        rs.getInt("instructor_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("due_date"),
                        rs.getInt("max_marks")
                );

                assignment.setAssignmentId(rs.getInt("assignment_id"));
                assignments.add(assignment);
            }

        } catch (SQLException e) {
            System.out.println("Error viewing assignments: " + e.getMessage());
        }

        return assignments;
    }

    // Search assignment by ID
    public Assignment getAssignmentById(int id) {
        String query = "SELECT * FROM assignments WHERE assignment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Assignment assignment = new Assignment(
                            rs.getInt("course_id"),
                            rs.getInt("instructor_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("due_date"),
                            rs.getInt("max_marks")
                    );

                    assignment.setAssignmentId(rs.getInt("assignment_id"));
                    return assignment;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching assignment: " + e.getMessage());
        }

        return null;
    }

    // Update assignment
    public boolean updateAssignment(Assignment assignment) {
        String query = "UPDATE assignments SET course_id=?, instructor_id=?, " +
                "title=?, description=?, due_date=?, max_marks=? " +
                "WHERE assignment_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, assignment.getCourseId());
            ps.setInt(2, assignment.getInstructorId());
            ps.setString(3, assignment.getTitle());
            ps.setString(4, assignment.getDescription());
            ps.setString(5, assignment.getDueDate());
            ps.setInt(6, assignment.getMaxMarks());
            ps.setInt(7, assignment.getAssignmentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating assignment: " + e.getMessage());
            return false;
        }
    }

    // Delete assignment
    public boolean deleteAssignment(int id) {
        String query = "DELETE FROM assignments WHERE assignment_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting assignment: " + e.getMessage());
            return false;
        }
    }
}

