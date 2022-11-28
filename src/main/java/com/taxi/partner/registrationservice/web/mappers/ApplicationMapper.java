package com.taxi.partner.registrationservice.web.mappers;

import com.taxi.partner.registrationservice.domain.Application;
import com.taxi.partner.model.ApplicationDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(ApplicationMapperDecorator.class)
public interface ApplicationMapper {

    ApplicationDto applicationToApplicationDto(Application application);
    Application applicationDtoToApplication(ApplicationDto dto);
}
