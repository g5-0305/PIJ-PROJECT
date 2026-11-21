
package model;

public class Submission {
    private int submissionId;
    private int assignmentId;
    private int studentId;
    private String submissionText;
    private String filePath;
    private String submittedAt;
    private String status;

    public Submission(int assignmentId, int studentId,
                      String submissionText, String filePath,
                      String submittedAt, String status) {
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.submissionText = submissionText;
        this.filePath = filePath;
        this.submittedAt = submittedAt;
        this.status = status;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getSubmissionText() {
        return submissionText;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }
}

