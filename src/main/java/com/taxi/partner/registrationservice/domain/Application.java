package com.taxi.partner.registrationservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Application {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String phoneNumber;


    @Column(unique = true)
    private String email;

    private String firstName;
    private String middleName;
    private String lastName;

    private String city;
    private String province;
    private String country;

    @Enumerated(EnumType.STRING)
    private ServiceCategory serviceCategory;

    @Enumerated(EnumType.STRING)
    private ServiceRange serviceRange;

    private String preferredLanguage;

    private String taxId; // PAN in India
    private String drivingLicenseId;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private String vehicleRegistrationCertificateId;
    private String vehicleFitnessCertificateId;
    private String vehiclePermitId;
    private String vehicleInsuranceId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus = ApplicationStatus.NEW;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

}
