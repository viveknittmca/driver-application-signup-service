package com.taxi.partner.registrationservice.web.controller;

import com.taxi.partner.registrationservice.domain.ServiceCategory;
import com.taxi.partner.registrationservice.services.ApplicationService;
import com.taxi.partner.model.ApplicationDto;
import com.taxi.partner.model.ApplicationPagedList;
import com.taxi.partner.registrationservice.domain.ServiceRange;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class ApplicationController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final ApplicationService applicationService;

    @GetMapping(produces = { "application/json" }, path = "applications")
    public ResponseEntity<ApplicationPagedList> listApplications(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                 @RequestParam(value = "serviceRange", required = false) ServiceRange serviceRange,
                                                                 @RequestParam(value = "serviceCategory", required = false) ServiceCategory serviceCategory){

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        ApplicationPagedList applicationPagedList = applicationService.listApplications(serviceRange, serviceCategory, PageRequest.of(pageNumber, pageSize));

        return new ResponseEntity<>(applicationPagedList, HttpStatus.OK);
    }

    @GetMapping("applications/{applicationId}")
    public ResponseEntity<ApplicationDto> getApplicationById(@PathVariable("applicationId") UUID applicationId){
        return new ResponseEntity<>(applicationService.getById(applicationId), HttpStatus.OK);
    }

    @PostMapping(path = "applications")
    public ResponseEntity saveNewApplication(@RequestBody @Validated ApplicationDto applicationDto){
        return new ResponseEntity<>(applicationService.saveNewApplication(applicationDto), HttpStatus.CREATED);
    }

    @PutMapping("applications/{applicationId}")
    public ResponseEntity updateApplicationById(@PathVariable("applicationId") UUID applicationId, @RequestBody @Validated ApplicationDto applicationDto){
        return new ResponseEntity<>(applicationService.updateApplication(applicationId, applicationDto), HttpStatus.NO_CONTENT);
    }

}
