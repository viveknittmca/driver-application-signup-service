package com.taxi.partner.registrationservice.bootstrap;

import com.taxi.partner.registrationservice.repositories.ApplicationRepository;
import com.taxi.partner.registrationservice.domain.ServiceCategory;
import com.taxi.partner.registrationservice.domain.Application;
import com.taxi.partner.registrationservice.domain.ApplicationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicationLoader implements CommandLineRunner {

    public static final String APPLICATION_1_PHONE_NUMBER = "9740191100";
    public static final String APPLICATION_1_EMAIL = "viveknittmca@gmail.com";
    public static final String APPLICATION_2_PHONE_NUMBER = "9480764192";
    public static final String APPLICATION_2_EMAIL = "vivekbest86@gmail.com";
    public static final String APPLICATION_3_PHONE_NUMBER = "9791725883";
    public static final String APPLICATION_3_EMAIL = "vivekbest86@yahoo.com";

    private final ApplicationRepository applicationRepository;

    @Override
    public void run(String... args) throws Exception {

          if(applicationRepository.count() == 0 ) {
              loadApplicationObjects();
          }
    }

    private void loadApplicationObjects() {
        Application b1 = Application.builder()
                .phoneNumber(APPLICATION_1_PHONE_NUMBER)
                .email(APPLICATION_1_EMAIL)
                .serviceCategory(ServiceCategory.CAB)
                .applicationStatus(ApplicationStatus.NEW)
                .build();

        Application b2 = Application.builder()
                .phoneNumber(APPLICATION_2_PHONE_NUMBER)
                .email(APPLICATION_2_EMAIL)
                .serviceCategory(ServiceCategory.EATS)
                .applicationStatus(ApplicationStatus.NEW)
                .build();

        Application b3 = Application.builder()
                .phoneNumber(APPLICATION_3_PHONE_NUMBER)
                .email(APPLICATION_3_EMAIL)
                .serviceCategory(ServiceCategory.COURIER)
                .applicationStatus(ApplicationStatus.NEW)
                .build();

        applicationRepository.save(b1);
        applicationRepository.save(b2);
        applicationRepository.save(b3);
    }
}
