
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Grade;

public class GradeDAO {

    // Add a new grade
    public boolean addGrade(Grade grade) {
        String query = "INSERT INTO grades " +
                "(submission_id, instructor_id, marks_obtained, feedback) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, grade.getSubmissionId());
            ps.setInt(2, grade.getInstructorId());
            ps.setDouble(3, grade.getMarksObtained());
            ps.setString(4, grade.getFeedback());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding grade: " + e.getMessage());
            return false;
        }
    }

    // View all grades
    public ArrayList<Grade> getAllGrades() {
        ArrayList<Grade> grades = new ArrayList<>();
        String query = "SELECT * FROM grades";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Grade grade = new Grade(
                        rs.getInt("submission_id"),
                        rs.getInt("instructor_id"),
                        rs.getDouble("marks_obtained"),
                        rs.getString("feedback")
                );

                grade.setGradeId(rs.getInt("grade_id"));
                grades.add(grade);
            }

        } catch (SQLException e) {
            System.out.println("Error viewing grades: " + e.getMessage());
        }

        return grades;
    }

    // Search grade by ID
    public Grade getGradeById(int id) {
        String query = "SELECT * FROM grades WHERE grade_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Grade grade = new Grade(
                            rs.getInt("submission_id"),
                            rs.getInt("instructor_id"),
                            rs.getDouble("marks_obtained"),
                            rs.getString("feedback")
                    );

                    grade.setGradeId(rs.getInt("grade_id"));
                    return grade;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching grade: " + e.getMessage());
        }

        return null;
    }

    // Update grade
    public boolean updateGrade(Grade grade) {
        String query = "UPDATE grades SET submission_id=?, instructor_id=?, " +
                "marks_obtained=?, feedback=? WHERE grade_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, grade.getSubmissionId());
            ps.setInt(2, grade.getInstructorId());
            ps.setDouble(3, grade.getMarksObtained());
            ps.setString(4, grade.getFeedback());
            ps.setInt(5, grade.getGradeId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating grade: " + e.getMessage());
            return false;
        }
    }

    // Delete grade
    public boolean deleteGrade(int id) {
        String query = "DELETE FROM grades WHERE grade_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting grade: " + e.getMessage());
            return false;
        }
    }
}

