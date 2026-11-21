
package model;

public class Student {

    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String department;
    private int semester;
    private String phone;

    // Constructor
    public Student(String firstName, String lastName, String email,
                   String password, String department, int semester,
                   String phone) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.department = department;
        this.semester = semester;
        this.phone = phone;
    }

    // Getters
    public int getStudentId() {
        return studentId;
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

    public int getSemester() {
        return semester;
    }

    public String getPhone() {
        return phone;
    }

    // Setter for generated student ID
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
