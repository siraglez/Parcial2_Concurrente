package org.example.parcial2_concurrente.servicios;

import org.example.parcial2_concurrente.fabrica.FabricaComponentes;
import org.example.parcial2_concurrente.componentes.Componente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import reactor.core.publisher.Mono;

public class ProductorService {

    private final RabbitTemplate rabbitTemplate;
    private final String tipo;

    public ProductorService(RabbitTemplate rabbitTemplate, String tipo) {
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
