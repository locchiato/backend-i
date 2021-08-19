package com.clase10;

import org.apache.log4j.Logger;

public abstract class Animal {

    private String nombre;
    private int edad;

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    private static final Logger logger = Logger.getLogger(Animal.class);

    public Logger getLogger(){
        return logger;
    }

    public Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public abstract void correr();

    public boolean esMayorA10(){
        if(edad < 0)
            logger.error("La edad no puede ser negativa");
        return edad > 10;
    }

}
