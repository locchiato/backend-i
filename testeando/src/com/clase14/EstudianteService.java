package com.clase14;

import java.util.List;

public class EstudianteService {



    private IDao<Estudiante> estudianteDao;



    public EstudianteService() {



    }



    public EstudianteService( IDao<Estudiante> estudianteDao) {

        this.estudianteDao = estudianteDao;

    }



    public void setEstudianteDao( IDao<Estudiante> estudianteDao) {

        this.estudianteDao = estudianteDao;

    }



    public Estudiante guardarEstudiante(Estudiante estudiante){

        estudianteDao.guardar(estudiante);

        return estudiante;

    }

    public List<Estudiante> buscarTodos(){

        return estudianteDao.buscarTodos();

    }



    public Estudiante buscar(String id){

        return estudianteDao.buscar(id);

    }



    public void  eliminarEstudiante(String id){

        estudianteDao.eliminar(id);

    }



}