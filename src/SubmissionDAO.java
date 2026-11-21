
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Submission;

public class SubmissionDAO {

    // Add a new submission
    public boolean addSubmission(Submission submission) {
        if (!isValidStatus(submission.getStatus())) {
    System.out.println("Invalid status! Use Submitted, Late or Graded.");
    return false;
}
        String query = "INSERT INTO submissions " +
                "(assignment_id, student_id, submission_text, " +
                "file_path, submitted_at, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, submission.getAssignmentId());
            ps.setInt(2, submission.getStudentId());
            ps.setString(3, submission.getSubmissionText());
            ps.setString(4, submission.getFilePath());
            ps.setString(5, submission.getSubmittedAt());
            ps.setString(6, submission.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding submission: " + e.getMessage());
            return false;
        }
    }

    // View all submissions
    public ArrayList<Submission> getAllSubmissions() {
        ArrayList<Submission> submissions = new ArrayList<>();
        String query = "SELECT * FROM submissions";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Submission submission = new Submission(
                        rs.getInt("assignment_id"),
                        rs.getInt("student_id"),
                        rs.getString("submission_text"),
                        rs.getString("file_path"),
                        rs.getString("submitted_at"),
                        rs.getString("status")
                );

                submission.setSubmissionId(rs.getInt("submission_id"));
                submissions.add(submission);
            }

        } catch (SQLException e) {
            System.out.println("Error viewing submissions: " + e.getMessage());
        }

        return submissions;
    }

    // Search submission by ID
    public Submission getSubmissionById(int id) {
        String query = "SELECT * FROM submissions WHERE submission_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Submission submission = new Submission(
                            rs.getInt("assignment_id"),
                            rs.getInt("student_id"),
                            rs.getString("submission_text"),
                            rs.getString("file_path"),
                            rs.getString("submitted_at"),
                            rs.getString("status")
                    );

                    submission.setSubmissionId(rs.getInt("submission_id"));
                    return submission;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching submission: " + e.getMessage());
        }

        return null;
    }

    // Update submission
    public boolean updateSubmission(Submission submission) {
       if (!isValidStatus(submission.getStatus())) {
    System.out.println("Invalid status! Use Submitted, Late or Graded.");
    return false;
}
        String query = "UPDATE submissions SET assignment_id=?, student_id=?, " +
                "submission_text=?, file_path=?, submitted_at=?, status=? " +
                "WHERE submission_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, submission.getAssignmentId());
            ps.setInt(2, submission.getStudentId());
            ps.setString(3, submission.getSubmissionText());
            ps.setString(4, submission.getFilePath());
            ps.setString(5, submission.getSubmittedAt());
            ps.setString(6, submission.getStatus());
            ps.setInt(7, submission.getSubmissionId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating submission: " + e.getMessage());
            return false;
        }
    }

    // Delete submission
    public boolean deleteSubmission(int id) {
        String query = "DELETE FROM submissions WHERE submission_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting submission: " + e.getMessage());
            return false;
        }
    }
    // Validate submission status
private boolean isValidStatus(String status) {
    return status != null &&
           (status.equals("Submitted") ||
            status.equals("Late") ||
            status.equals("Graded"));
}
}

