package fr.adservio.neobankbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NeoBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeoBankBackendApplication.class, args);
    }

}
