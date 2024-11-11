package org.example.parcial2_concurrente;

import org.example.parcial2_concurrente.componentes.Componente;

import java.util.LinkedList;
import java.util.Queue;

public class BufferCompartido {
    private Queue<Componente> buffer = new LinkedList<>();
    private int capacidad;

    public BufferCompartido(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void producir(Componente componente) throws InterruptedException {
        while (buffer.size() == capacidad) {
            wait(); //Espera si el búfer está lleno
        }
        buffer.add(componente);
        System.out.println("Producido: " + componente.getNombre());
        notifyAll(); //Notifica a los consumidores que pueden consumir
    }

    public synchronized Componente consumir() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); //Espera si no hay componentes
        }
        Componente componente = buffer.poll();
        System.out.println("Consumido: " + componente.getNombre());
        notifyAll(); //Notifica a los productores que pueden producir
        return componente;
    }
}

