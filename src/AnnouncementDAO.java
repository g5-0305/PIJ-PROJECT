
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Announcement;

public class AnnouncementDAO {

    // Add a new announcement
    public boolean addAnnouncement(Announcement announcement) {

        String query = "INSERT INTO announcements " +
                "(instructor_id, course_id, title, message) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, announcement.getInstructorId());
            ps.setInt(2, announcement.getCourseId());
            ps.setString(3, announcement.getTitle());
            ps.setString(4, announcement.getMessage());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding announcement: " + e.getMessage());
            return false;
        }
    }

    // View all announcements
    public ArrayList<Announcement> getAllAnnouncements() {

        ArrayList<Announcement> announcements = new ArrayList<>();
        String query = "SELECT * FROM announcements";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Announcement announcement = new Announcement(
                        rs.getInt("instructor_id"),
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getString("message")
                );

                announcement.setAnnouncementId(
                        rs.getInt("announcement_id"));

                announcements.add(announcement);
            }

        } catch (SQLException e) {
            System.out.println("Error viewing announcements: " + e.getMessage());
        }

        return announcements;
    }

    // Search announcement by ID
    public Announcement getAnnouncementById(int id) {

        String query = "SELECT * FROM announcements WHERE announcement_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Announcement announcement = new Announcement(
                            rs.getInt("instructor_id"),
                            rs.getInt("course_id"),
                            rs.getString("title"),
                            rs.getString("message")
                    );

                    announcement.setAnnouncementId(
                            rs.getInt("announcement_id"));

                    return announcement;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching announcement: " + e.getMessage());
        }

        return null;
    }

    // Update announcement
    public boolean updateAnnouncement(Announcement announcement) {

        String query = "UPDATE announcements SET instructor_id=?, " +
                "course_id=?, title=?, message=? WHERE announcement_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, announcement.getInstructorId());
            ps.setInt(2, announcement.getCourseId());
            ps.setString(3, announcement.getTitle());
            ps.setString(4, announcement.getMessage());
            ps.setInt(5, announcement.getAnnouncementId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating announcement: " + e.getMessage());
            return false;
        }
    }

    // Delete announcement
    public boolean deleteAnnouncement(int id) {

        String query = "DELETE FROM announcements WHERE announcement_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting announcement: " + e.getMessage());
            return false;
        }
    }
}

