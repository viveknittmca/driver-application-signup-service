package com.taxi.partner.registrationservice.services;

import com.taxi.partner.model.ApplicationDto;
import com.taxi.partner.model.ApplicationPagedList;
import com.taxi.partner.registrationservice.domain.ServiceCategory;
import com.taxi.partner.registrationservice.domain.ServiceRange;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface ApplicationService {
    ApplicationPagedList listApplications(ServiceRange serviceRange, ServiceCategory serviceCategory, PageRequest pageRequest);

    ApplicationDto getById(UUID applicationId);

    ApplicationDto saveNewApplication(ApplicationDto applicationDto);

    ApplicationDto updateApplication(UUID applicationId, ApplicationDto applicationDto);

    //ApplicationDto getByPhoneNumber(String phoneNumber);
}
