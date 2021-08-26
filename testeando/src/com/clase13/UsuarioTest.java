package com.clase13;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    public static final String NOMBRE1 = "roberto";
    public static final String CORREO1 = "roberto.f@gmail.com";
    public static final int SUELDO1 = 100000;
    public static final double INC = 5000.0;
    Usuario user;

    @BeforeEach
    void setUp() {
        user = new Usuario(NOMBRE1, CORREO1, SUELDO1);
    }

    @Test
    void subirSueldo() {
        // verifico datos iniciales
        assertEquals(SUELDO1, user.getSueldo());
        // subir sueldo
        user.subirSueldo(INC);
        // verifico el nuevo sueldo
        assertEquals(SUELDO1 + INC, user.getSueldo());
    }
}