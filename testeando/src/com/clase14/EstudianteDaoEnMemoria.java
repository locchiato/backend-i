package com.clase14;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class EstudianteDaoEnMemoria implements IDao<Estudiante> {

    private List<Estudiante> estudianteRepositorio;



    public EstudianteDaoEnMemoria(List<Estudiante> estudianteRepositorio) {

        this.estudianteRepositorio = estudianteRepositorio;

    }



    @Override

    public Estudiante guardar(Estudiante estudiante) {

        estudianteRepositorio.add(estudiante);

        return estudiante;

    }



    @Override

    public void eliminar(String id) {

        estudianteRepositorio.removeIf(estudiante -> estudiante.getId().equals(id));

    }



    //Utilizamos streams para buscar una materia por id, y tomamos el primero si hay, si no, devolvemos null.

    @Override

    public Estudiante buscar(String id) {

        return estudianteRepositorio.stream().filter(estudiante -> estudiante.getId().equals(id)).findFirst().orElseGet(null);

    }



    @Override

    public List<Estudiante> buscarTodos() {

        return estudianteRepositorio;

    }

    @Test
    public void eliminarEstudianteTest() {

        estudianteDao.eliminar("1");

        assertEquals(estudianteDao.buscar("1"),null);



    }



}