package com.mesas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CirculoTest {

    private Circulo circulo;
    private Circulo circulo2;

    @BeforeEach
    void setUp(){
        circulo = new Circulo(.5);
        circulo2 = new Circulo(1.0);
    }

    @Test
    void calcularPerimetro() {
        assertEquals(3.14, circulo.calcularPerimetro());
        assertNotEquals(3.0, circulo.calcularPerimetro());

    }
}