package com.mesas;

public interface Figura {

    Double calcularPerimetro();

    public static Double normalizedDouble(Double conDecimales) {
        return Math.round(conDecimales * 100) / 100.0;
    }
}
