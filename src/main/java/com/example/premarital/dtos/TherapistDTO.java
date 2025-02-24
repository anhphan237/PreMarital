package com.example.premarital.dtos;

public class TherapistDTO {
    private Long id;
    private Long userId;
    private String bio;
    private String therapistCertificationName;
    private String certificationIssuedBy;
    private String certificationIssueDate;
    private String certificationExpirationDate;

    public TherapistDTO() {
    }

    public TherapistDTO(Long id, Long userId, String bio, String therapistCertificationName, String certificationIssuedBy, String certificationIssueDate, String certificationExpirationDate) {
        this.id = id;
        this.userId = userId;
        this.bio = bio;
        this.therapistCertificationName = therapistCertificationName;
        this.certificationIssuedBy = certificationIssuedBy;
        this.certificationIssueDate = certificationIssueDate;
        this.certificationExpirationDate = certificationExpirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTherapistCertificationName() {
        return therapistCertificationName;
    }

    public void setTherapistCertificationName(String therapistCertificationName) {
        this.therapistCertificationName = therapistCertificationName;
    }

    public String getCertificationIssuedBy() {
        return certificationIssuedBy;
    }

    public void setCertificationIssuedBy(String certificationIssuedBy) {
        this.certificationIssuedBy = certificationIssuedBy;
    }

    public String getCertificationIssueDate() {
        return certificationIssueDate;
    }

    public void setCertificationIssueDate(String certificationIssueDate) {
        this.certificationIssueDate = certificationIssueDate;
    }

    public String getCertificationExpirationDate() {
        return certificationExpirationDate;
    }

    public void setCertificationExpirationDate(String certificationExpirationDate) {
        this.certificationExpirationDate = certificationExpirationDate;
    }
}
