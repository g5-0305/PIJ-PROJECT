
package model;

public class Instructor {

    private int instructorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String department;

    public Instructor(String firstName, String lastName,
                      String email, String password,
                      String department) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.department = department;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }
}

