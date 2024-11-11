package org.example.parcial2_concurrente;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferCompartido buffer = new BufferCompartido(10);

        //Lista de productores
        List<Productor> productores = new ArrayList<>();
        productores.add(new Productor(buffer, "Clavo"));
        productores.add(new Productor(buffer, "Bola"));

        //Creación del scheduler
        Scheduler scheduler = new Scheduler(productores, 1000); //Tiempo por turno en milisegundos

        // Creación de consumidor (línea de ensamblaje)
        Consumidor ensamblador = new Consumidor(buffer);

        // Iniciar el ensamblador
        ensamblador.start();

        //Iniciar el scheduler
        scheduler.iniciar();
    }
}

