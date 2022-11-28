package com.taxi.partner.registrationservice.services;

import com.taxi.partner.model.ApplicationDto;
import com.taxi.partner.model.ApplicationPagedList;
import com.taxi.partner.registrationservice.domain.ServiceCategory;
import com.taxi.partner.registrationservice.domain.Application;
import com.taxi.partner.registrationservice.domain.ServiceRange;
import com.taxi.partner.registrationservice.repositories.ApplicationRepository;
import com.taxi.partner.registrationservice.web.controller.NotFoundException;
import com.taxi.partner.registrationservice.web.mappers.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    @Cacheable(cacheNames = "applicationListCache")
    @Override
    public ApplicationPagedList listApplications(ServiceRange serviceRange,
                                                 ServiceCategory serviceCategory, PageRequest pageRequest) {

        ApplicationPagedList applicationPagedList;
        Page<Application> applicationPage;

        if (!StringUtils.isEmpty(serviceRange) && !StringUtils.isEmpty(serviceCategory)) {
            //search both
            applicationPage = applicationRepository
                    .findAllByServiceRangeAndServiceCategory(serviceRange, serviceCategory, pageRequest);
        } else if (!StringUtils.isEmpty(serviceRange) && StringUtils.isEmpty(serviceCategory)) {
            //search beer_service name
            applicationPage = applicationRepository.findAllByServiceRange(serviceRange, pageRequest);
        } else if (StringUtils.isEmpty(serviceRange) && !StringUtils.isEmpty(serviceCategory)) {
            //search beer_service style
            applicationPage = applicationRepository.findAllByServiceCategory(serviceCategory, pageRequest);
        } else {
            applicationPage = applicationRepository.findAll(pageRequest);
        }


            applicationPagedList = new ApplicationPagedList(applicationPage
                    .getContent()
                    .stream()
                    .map(applicationMapper::applicationToApplicationDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(applicationPage.getPageable().getPageNumber(),
                                    applicationPage.getPageable().getPageSize()),
                    applicationPage.getTotalElements());

        return applicationPagedList;
    }

    @Cacheable(cacheNames = "applicationCache", key = "#applicationId")
    @Override
    public ApplicationDto getById(UUID applicationId) {
            return applicationMapper.applicationToApplicationDto(
                    applicationRepository.findById(applicationId).orElseThrow(NotFoundException::new)
            );
    }

    @Override
    public ApplicationDto saveNewApplication(ApplicationDto applicationDto) {
        return applicationMapper.applicationToApplicationDto(applicationRepository.save(applicationMapper.applicationDtoToApplication(applicationDto)));
    }

    @Override
    public ApplicationDto updateApplication(UUID applicationId, ApplicationDto applicationDto) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(NotFoundException::new);
        application.setEmail(applicationDto.getEmail());
        application.setServiceCategory(ServiceCategory.valueOf(applicationDto.getServiceCategory()));
        application.setPhoneNumber(applicationDto.getPhoneNumber());

        return applicationMapper.applicationToApplicationDto(applicationRepository.save(application));
    }

    /*
    @Cacheable(cacheNames = "applicationPhoneNumberCache")
    @Override
    public ApplicationDto getByPhoneNumber(String phoneNumber) {
        return applicationMapper.applicationToApplicationDto(applicationRepository.findAllByPhoneNumber(phoneNumber).stream().findFirst().get());
    }
    */
}
