package com.taxi.partner.registrationservice.web.mappers;

import com.taxi.partner.registrationservice.domain.Application;
import com.taxi.partner.model.ApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ApplicationMapperDecorator implements ApplicationMapper {
    private ApplicationMapper mapper;

    @Autowired
    public void setMapper(ApplicationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ApplicationDto applicationToApplicationDto(Application application) {
       return mapper.applicationToApplicationDto(application);
    }

    @Override
    public Application applicationDtoToApplication(ApplicationDto applicationDto) {
        return mapper.applicationDtoToApplication(applicationDto);
    }
}
