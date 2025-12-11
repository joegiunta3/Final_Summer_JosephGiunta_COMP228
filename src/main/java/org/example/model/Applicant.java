package org.example.model;

public class Applicant {
    private int applicantId;
    private String fullName;
    private String address;
    private String contactNumber;
    private String email;
    private String education;
    private String gender;

    public Applicant(String fullName, String address, String contactNumber,
                     String email, String education, String gender) {
        this.fullName = fullName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.education = education;
        this.gender = gender;
    }

    public int getApplicantId() { return applicantId; }
    public void setApplicantId(int applicantId) { this.applicantId = applicantId; }

    public String getFullName() { return fullName; }
    public String getAddress() { return address; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public String getEducation() { return education; }
    public String getGender() { return gender; }
}
