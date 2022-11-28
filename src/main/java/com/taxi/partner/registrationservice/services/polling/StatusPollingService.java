package com.taxi.partner.registrationservice.services.polling;

import com.taxi.partner.registrationservice.domain.Application;
import com.taxi.partner.registrationservice.repositories.ApplicationRepository;
import com.taxi.partner.model.events.NewApplicationEvent;
import com.taxi.partner.registrationservice.config.JmsConfig;
import com.taxi.partner.registrationservice.domain.ApplicationStatus;
import com.taxi.partner.registrationservice.web.mappers.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusPollingService {
    private final ApplicationRepository applicationRepository;
    private final JmsTemplate jmsTemplate;
    private final ApplicationMapper applicationMapper;

    //@Scheduled(fixedRate = 5000) //every 5 seconds
    public void checkForNewApplicationStatus(){
        List<Application> applications = applicationRepository.findAllByApplicationStatus(ApplicationStatus.NEW);

        applications.forEach(application -> {
            ApplicationStatus applicationStatus = application.getApplicationStatus();
            log.debug("Checking Status for: " + application.getEmail() + " / " + application.getId());
            log.debug("Status is: "  + applicationStatus);

            if(ApplicationStatus.NEW == applicationStatus){
                jmsTemplate.convertAndSend(JmsConfig.REVIEW_REQUEST_QUEUE, new NewApplicationEvent(applicationMapper.applicationToApplicationDto(application)));
                //TODO - Receive Event from Acknowledge queue and change the status to review
                application.setApplicationStatus(ApplicationStatus.REVIEW);
                applicationRepository.save(application);
            }

        });

    }
}
