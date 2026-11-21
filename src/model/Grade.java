
package model;

public class Grade {
    private int gradeId;
    private int submissionId;
    private int instructorId;
    private double marksObtained;
    private String feedback;

    public Grade(int submissionId, int instructorId,
                 double marksObtained, String feedback) {
        this.submissionId = submissionId;
        this.instructorId = instructorId;
        this.marksObtained = marksObtained;
        this.feedback = feedback;
    }

    public int getGradeId() {
        return gradeId;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public double getMarksObtained() {
        return marksObtained;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }
}

