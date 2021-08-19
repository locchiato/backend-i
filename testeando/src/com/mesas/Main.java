package com.mesas;

public class Main {

    public static void main(String[] args) {

        for (Double i = 0.0; i < 2; i = i + 0.1) {
        System.out.println("El perimetro de un circulo de radio " + Figura.normalizedDouble(i) +
                    "cm es " + new Circulo(i).calcularPerimetro());
        }

        for (Double i = 0.0; i < 2; i = i + 0.1) {
            System.out.println("El perimetro de un cuadrado de lado " + Figura.normalizedDouble(i) +
                    "cm es " + new Cuadrado(i).calcularPerimetro());
        }
    }
}
