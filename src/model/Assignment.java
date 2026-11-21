
package model;

public class Assignment {
    private int assignmentId;
    private int courseId;
    private int instructorId;
    private String title;
    private String description;
    private String dueDate;
    private int maxMarks;

    public Assignment(int courseId, int instructorId,
                      String title, String description,
                      String dueDate, int maxMarks) {
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.maxMarks = maxMarks;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }
}

