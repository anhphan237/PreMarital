package com.example.premarital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TherapistDTO {
    private Long id;
    private Long userId;
    private String bio;
    private String therapistCertificationName;
    private String certificationIssuedBy;
    private String certificationIssueDate;
    private String certificationExpirationDate;
    private Long therapistMajorId;
}
