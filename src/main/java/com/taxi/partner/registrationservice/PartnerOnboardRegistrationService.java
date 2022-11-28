package com.taxi.partner.registrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PartnerOnboardRegistrationService {

    public static void main(String[] args) {
        SpringApplication.run(PartnerOnboardRegistrationService.class, args);
    }

}
