package org.example.parcial2_concurrente.servicios;

import org.example.parcial2_concurrente.componentes.Bola;
import org.example.parcial2_concurrente.componentes.Clavo;
import org.example.parcial2_concurrente.visualizacion.GaltonWebSocketHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorService {

    private int bolasCount = 0;
    private int clavosCount = 0;

    private final GaltonWebSocketHandler galtonWebSocketHandler;

    @Autowired
    public ConsumidorService(GaltonWebSocketHandler galtonWebSocketHandler) {
        this.galtonWebSocketHandler = galtonWebSocketHandler;
    }

    @RabbitListener(queues = "bolaQueue")
    public void consumeBola(Bola bola) {
        bolasCount++;
        ensamblarMaquina();
    }

    @RabbitListener(queues = "clavoQueue")
    public void consumeClavo(Clavo clavo) {
        clavosCount++;
        ensamblarMaquina();
    }

    private void ensamblarMaquina() {
        while (bolasCount > 0 && clavosCount > 0) {
            System.out.println("Ensamblando máquina con 1 bola y 1 clavo.");
            bolasCount--;
            clavosCount--;

            // Enviar notificación al frontend para agregar una nueva bola
            galtonWebSocketHandler.broadcastUpdate("Nueva máquina ensamblada");
        }
    }
}
