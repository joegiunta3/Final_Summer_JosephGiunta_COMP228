package org.example.model;

import java.time.LocalDate;

public class EmploymentApplication {
    private int applicantId;
    private LocalDate dateAvailable;
    private String desiredPosition;
    private String desiredSalary;
    private boolean authorizedToWork;
    private boolean hasRelatives;
    private String relativesExplanation;

    public EmploymentApplication(int applicantId, LocalDate dateAvailable, String desiredPosition,
                                 String desiredSalary, boolean authorizedToWork,
                                 boolean hasRelatives, String relativesExplanation) {
        this.applicantId = applicantId;
        this.dateAvailable = dateAvailable;
        this.desiredPosition = desiredPosition;
        this.desiredSalary = desiredSalary;
        this.authorizedToWork = authorizedToWork;
        this.hasRelatives = hasRelatives;
        this.relativesExplanation = relativesExplanation;
    }

    public int getApplicantId() { return applicantId; }
    public LocalDate getDateAvailable() { return dateAvailable; }
    public String getDesiredPosition() { return desiredPosition; }
    public String getDesiredSalary() { return desiredSalary; }
    public boolean isAuthorizedToWork() { return authorizedToWork; }
    public boolean isHasRelatives() { return hasRelatives; }
    public String getRelativesExplanation() { return relativesExplanation; }
}
