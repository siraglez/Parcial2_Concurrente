package org.example.parcial2_concurrente.configuracion;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //Cola para componentes de tipo "Bola"
    @Bean
    public Queue bolaQueue() {
        return new Queue("bolaQueue", false);
    }

    //Cola para componentes de tipo "Clavo"
    @Bean
    public Queue clavoQueue() {
        return new Queue("clavoQueue", false);
    }
}

