package com.mesas;

public class Circulo implements Figura {

    private Double radio;

    public Circulo(Double radio) {
        this.radio = Figura.normalizedDouble(radio);
    }

    @Override
    public Double calcularPerimetro() {
        Double perimetro = 2 * Math.PI * radio;
            return Figura.normalizedDouble(perimetro);
    }

}
