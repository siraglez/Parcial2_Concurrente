package org.example.parcial2_concurrente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Parcial2ConcurrenteApplication {

    public static void main(String[] args) {
        SpringApplication.run(Parcial2ConcurrenteApplication.class, args);
    }
}
