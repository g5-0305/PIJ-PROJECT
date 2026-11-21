
package model;

public class Course {

    private int courseId;
    private String courseName;
    private String courseCode;
    private String description;
    private int instructorId;

    public Course(String courseName, String courseCode,
                  String description, int instructorId) {

        this.courseName = courseName;
        this.courseCode = courseCode;
        this.description = description;
        this.instructorId = instructorId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDescription() {
        return description;
    }

    public int getInstructorId() {
        return instructorId;
    }
}

