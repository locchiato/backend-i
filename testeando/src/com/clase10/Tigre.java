package com.clase10;

public class Tigre extends Animal{

    public Tigre(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public void correr() {
        getLogger().info("El Tigre esta corriendo..");


    }


}
