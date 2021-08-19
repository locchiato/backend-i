package com.mesas;

public class Cuadrado implements Figura {

    private Double lado;

    public Cuadrado(Double lado) {
        this.lado = Figura.normalizedDouble(lado);
    }

    @Override
    public Double calcularPerimetro() {
        Double perimetro = 4 * lado;
        return Figura.normalizedDouble(perimetro);
    }
}
