package com.mesas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuadradoTest {


    private Cuadrado cuadrado;
    private Cuadrado cuadrado2;

    @BeforeEach
    void setUp(){
        cuadrado = new Cuadrado(.5);
        cuadrado2 = new Cuadrado(1.0);
    }

    @Test
    void calcularPerimetro() {
        assertEquals(2.0, cuadrado.calcularPerimetro());
        assertNotEquals(2.14, cuadrado.calcularPerimetro());

    }
}