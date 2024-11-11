package org.example.parcial2_concurrente.componentes;

import java.io.Serializable;

//Clase abstracta que representa los componentes genéricos que la fábrica produce
public abstract class Componente implements Serializable {
    protected String nombre;

    public Componente() {} //Constructor sin parámetros para deserialización

    public Componente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void ensamblar(); //Método abstracto para ensamblar cada componente
}
