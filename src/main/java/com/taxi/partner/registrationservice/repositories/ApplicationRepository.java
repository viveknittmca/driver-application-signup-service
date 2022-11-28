package com.taxi.partner.registrationservice.repositories;

import com.taxi.partner.registrationservice.domain.ApplicationStatus;
import com.taxi.partner.registrationservice.domain.ServiceCategory;
import com.taxi.partner.registrationservice.domain.Application;
import com.taxi.partner.registrationservice.domain.ServiceRange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {

    List<Application> findAllByApplicationStatus(ApplicationStatus applicationStatus);
    Page<Application> findAllByServiceRange(ServiceRange serviceRange, Pageable pageable);

    Page<Application> findAllByServiceCategory(ServiceCategory serviceCategory, Pageable pageable);

    Page<Application> findAllByServiceRangeAndServiceCategory(ServiceRange serviceRange, ServiceCategory serviceCategory, Pageable pageable);

}
