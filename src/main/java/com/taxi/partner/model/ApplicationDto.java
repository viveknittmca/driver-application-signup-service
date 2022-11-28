package com.taxi.partner.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taxi.partner.registrationservice.domain.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDto implements Serializable  {

    static final long serialVersionUID = -5815566940065181210L;

    @Null
    private UUID id;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String city;
    @NotBlank
    private String province;
    @NotBlank
    private String country;

    @NotNull
    private String serviceCategory;

    @NotNull
    private String serviceRange;

    @NotBlank
    private String preferredLanguage;

    @NotBlank
    private String taxId; // PAN in India

    @NotBlank
    private String drivingLicenseId;

    @NotNull
    private String vehicleType;

    @NotBlank
    private String vehicleRegistrationCertificateId;

    @NotBlank
    private String vehicleFitnessCertificateId;

    @NotBlank
    private String vehiclePermitId;

    @NotBlank
    private String vehicleInsuranceId;

    @NotNull
    private String applicationStatus = ApplicationStatus.NEW.name();

    @Null
    private Long version;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;

}
