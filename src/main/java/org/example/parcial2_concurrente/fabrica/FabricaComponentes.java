package org.example.parcial2_concurrente.fabrica;

import org.example.parcial2_concurrente.componentes.Bola;
import org.example.parcial2_concurrente.componentes.Clavo;
import org.example.parcial2_concurrente.componentes.Componente;

//Clase que implementará el patrón factory para producir diferentes tipos de componentes
public class FabricaComponentes {
    public static Componente crearComponente(String tipo) {
        switch (tipo) {
            case "Clavo":
                return new Clavo();
            case "Bola":
                return new Bola();
            default:
                throw new IllegalArgumentException("Componente no reconocido: " + tipo);
        }
    }
}

