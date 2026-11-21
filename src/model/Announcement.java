
package model;

public class Announcement {

    private int announcementId;
    private int instructorId;
    private int courseId;
    private String title;
    private String message;

    public Announcement(int instructorId, int courseId,
                        String title, String message) {
        this.instructorId = instructorId;
        this.courseId = courseId;
        this.title = title;
        this.message = message;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }
}

