package io.mb_backend.makingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MakingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakingBackendApplication.class, args);
    }

}
