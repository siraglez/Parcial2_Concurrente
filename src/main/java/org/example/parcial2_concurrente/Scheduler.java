package org.example.parcial2_concurrente;

import java.util.List;

public class Scheduler {
    private final List<Productor> productores;
    private final int tiempoPorTurno; //Tiempo asignado para cada productor

    public Scheduler(List<Productor> productores, int tiempoPorTurno) {
        this.productores = productores;
        this.tiempoPorTurno = tiempoPorTurno;
    }

    public void iniciar() {
        while (true) {
            for (Productor productor : productores) {
                try {
                    productor.start();
                    Thread.sleep(tiempoPorTurno); //Espera el tiempo por turno
                    productor.interrupt(); //Interrumpe el productor después de su turno
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
