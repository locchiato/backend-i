package com.clase10;

public class Leon extends Animal{

    private boolean esAlfa;

    public Leon(String nombre, int edad, boolean esAlfa) {
        super(nombre, edad);
        this.esAlfa = esAlfa;
    }

    @Override
    public void correr() {
        getLogger().info("El Leon " + getNombre() + " esta corriendo..");
    }
}
