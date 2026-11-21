
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Course;

public class CourseDAO {

    // 1. Add a new course
    public boolean addCourse(Course course) {

        String query = "INSERT INTO courses " +
                "(course_name, course_code, description, instructor_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getDescription());
            ps.setInt(4, course.getInstructorId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
            return false;
        }
    }

    // 2. View all courses
    public ArrayList<Course> getAllCourses() {

        ArrayList<Course> courses = new ArrayList<>();

        String query = "SELECT * FROM courses";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Course course = new Course(
                        rs.getString("course_name"),
                        rs.getString("course_code"),
                        rs.getString("description"),
                        rs.getInt("instructor_id")
                );

                course.setCourseId(rs.getInt("course_id"));
                courses.add(course);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        }

        return courses;
    }

    // 3. Search course by ID
    public Course getCourseById(int id) {

        String query = "SELECT * FROM courses WHERE course_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Course course = new Course(
                            rs.getString("course_name"),
                            rs.getString("course_code"),
                            rs.getString("description"),
                            rs.getInt("instructor_id")
                    );

                    course.setCourseId(rs.getInt("course_id"));
                    return course;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching course: " + e.getMessage());
        }

        return null;
    }

    // 4. Update course
    public boolean updateCourse(Course course) {

        String query = "UPDATE courses SET course_name=?, " +
                "course_code=?, description=?, instructor_id=? " +
                "WHERE course_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getDescription());
            ps.setInt(4, course.getInstructorId());
            ps.setInt(5, course.getCourseId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating course: " + e.getMessage());
            return false;
        }
    }

    // 5. Delete course
    public boolean deleteCourse(int id) {

        String query = "DELETE FROM courses WHERE course_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting course: " + e.getMessage());
            return false;
        }
    }
}

