package org.example.parcial2_concurrente;


import org.example.parcial2_concurrente.componentes.Componente;

public class Productor extends Thread {
    private BufferCompartido buffer;
    private String tipoComponente;
    public Productor(BufferCompartido buffer, String tipoComponente) {
        this.buffer = buffer;
        this.tipoComponente = tipoComponente;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Componente componente = FabricaComponentes.crearComponente(tipoComponente);
                buffer.producir(componente);
                Thread.sleep(1000); //Simula el tiempo de producción
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

