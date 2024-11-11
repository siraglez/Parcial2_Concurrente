package org.example.parcial2_concurrente.servicios;

import org.example.parcial2_concurrente.fabrica.FabricaComponentes;
import org.example.parcial2_concurrente.componentes.Componente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductorService {

    private final RabbitTemplate rabbitTemplate;
    private final String tipo;

    @Autowired
    public ProductorService(RabbitTemplate rabbitTemplate, @Value("${componente.tipo}") String tipo) {
        this.rabbitTemplate = rabbitTemplate;
        this.tipo = tipo;
    }

    public Mono<Void> produceComponent() {
        Componente componente = FabricaComponentes.crearComponente(tipo);
        String queueName = tipo.equals("Bola") ? "bolaQueue" : "clavoQueue";
        rabbitTemplate.convertAndSend(queueName, componente);
        System.out.println("Producido: " + componente.getNombre());
        return Mono.empty();
    }
}

