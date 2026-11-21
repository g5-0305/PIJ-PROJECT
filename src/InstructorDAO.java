
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Instructor;

public class InstructorDAO {

    // 1. Add Instructor
    public boolean addInstructor(Instructor instructor) {

        String query = "INSERT INTO instructors " +
                "(first_name, last_name, email, password, department) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, instructor.getFirstName());
            ps.setString(2, instructor.getLastName());
            ps.setString(3, instructor.getEmail());
            ps.setString(4, instructor.getPassword());
            ps.setString(5, instructor.getDepartment());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding instructor: " + e.getMessage());
            return false;
        }
    }

    // 2. View All Instructors
    public ArrayList<Instructor> getAllInstructors() {

        ArrayList<Instructor> instructors = new ArrayList<>();

        String query = "SELECT * FROM instructors";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Instructor instructor = new Instructor(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("department")
                );

                instructor.setInstructorId(rs.getInt("instructor_id"));
                instructors.add(instructor);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving instructors: " + e.getMessage());
        }

        return instructors;
    }

    // 3. Search Instructor by ID
    public Instructor getInstructorById(int id) {

        String query = "SELECT * FROM instructors WHERE instructor_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Instructor instructor = new Instructor(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("department")
                    );

                    instructor.setInstructorId(rs.getInt("instructor_id"));
                    return instructor;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching instructor: " + e.getMessage());
        }

        return null;
    }

    // 4. Update Instructor
    public boolean updateInstructor(Instructor instructor) {

        String query = "UPDATE instructors SET first_name=?, " +
                "last_name=?, email=?, password=?, department=? " +
                "WHERE instructor_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, instructor.getFirstName());
            ps.setString(2, instructor.getLastName());
            ps.setString(3, instructor.getEmail());
            ps.setString(4, instructor.getPassword());
            ps.setString(5, instructor.getDepartment());
            ps.setInt(6, instructor.getInstructorId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating instructor: " + e.getMessage());
            return false;
        }
    }

    // 5. Delete Instructor
    public boolean deleteInstructor(int id) {

        String query = "DELETE FROM instructors WHERE instructor_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting instructor: " + e.getMessage());
            return false;
        }
    }
}

