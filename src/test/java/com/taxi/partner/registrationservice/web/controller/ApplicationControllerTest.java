package com.taxi.partner.registrationservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxi.partner.model.ApplicationDto;
import com.taxi.partner.registrationservice.domain.ApplicationStatus;
import com.taxi.partner.registrationservice.domain.ServiceCategory;
import com.taxi.partner.registrationservice.bootstrap.ApplicationLoader;
import com.taxi.partner.registrationservice.domain.ServiceRange;
import com.taxi.partner.registrationservice.domain.VehicleType;
import com.taxi.partner.registrationservice.services.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ApplicationService applicationService;

    @Test
    void getApplicationById() throws Exception {

        given(applicationService.getById(any())).willReturn(getValidApplicationDto());

        mockMvc.perform(get("/api/v1/applications/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    //@Test
    void saveNewApplication() throws Exception {

        ApplicationDto applicationDto = getValidApplicationDto();
        String applicationDtoJson = objectMapper.writeValueAsString(applicationDto);

        given(applicationService.saveNewApplication(any())).willReturn(getValidApplicationDto());

        mockMvc.perform(post("/api/v1/applications/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(applicationDtoJson))
                .andExpect(status().isCreated());
    }

    //@Test
    void updateApplicationById() throws Exception {
        given(applicationService.updateApplication(any(), any())).willReturn(getValidApplicationDto());

        ApplicationDto applicationDto = getValidApplicationDto();
        String applicationDtoJson = objectMapper.writeValueAsString(applicationDto);

        mockMvc.perform(put("/api/v1/applications/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(applicationDtoJson))
                .andExpect(status().isNoContent());
    }


    ApplicationDto getValidApplicationDto(){
        return ApplicationDto.builder()
                .phoneNumber(ApplicationLoader.APPLICATION_1_PHONE_NUMBER)
                .email(ApplicationLoader.APPLICATION_1_EMAIL)
                .firstName("Vivek")
                .lastName("Rajendran")
                .city("Bangalore")
                .province("Karnataka")
                .country("India")
                .serviceCategory(ServiceCategory.CAB.name())
                .serviceRange(ServiceRange.INTRACITY.name())
                .preferredLanguage("English")
                .taxId("ABCDE1234F")
                .drivingLicenseId("DL1")
                .vehicleType(VehicleType.GO.name())
                .vehicleRegistrationCertificateId("RC1")
                .vehicleFitnessCertificateId("FC1")
                .vehiclePermitId("PR1")
                .vehicleInsuranceId("IN1")
                .applicationStatus(ApplicationStatus.NEW.name())
                .version(1L)
                .build();
    }
}