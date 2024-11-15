package org.example.parcial2_concurrente.configuracion;

import org.example.parcial2_concurrente.servicios.ProductorService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductorConfig {

    @Bean
    public ProductorService productorBola(RabbitTemplate rabbitTemplate) {
        return new ProductorService(rabbitTemplate, "Bola");
    }

    @Bean
    public ProductorService productorClavo(RabbitTemplate rabbitTemplate) {
        return new ProductorService(rabbitTemplate, "Clavo");
    }
}
