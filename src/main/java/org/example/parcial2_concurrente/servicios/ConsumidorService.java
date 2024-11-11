package org.example.parcial2_concurrente.servicios;

import org.example.parcial2_concurrente.componentes.Bola;
import org.example.parcial2_concurrente.componentes.Clavo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorService {

    private int bolasCount = 0;
    private int clavosCount = 0;

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
        if (bolasCount >= 1 && clavosCount >= 1) {
            System.out.println("Ensamblando máquina con 1 bola y 1 clavo.");
            bolasCount--;
            clavosCount--;
        }
    }
}

