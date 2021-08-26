package main.com.dh.academia;

import main.com.dh.academia.dao.ConfiguracionJDBC;
import main.com.dh.academia.dao.IDao;
import main.com.dh.academia.dao.impl.EstudianteDaoEnMemoria;
import main.com.dh.academia.dao.impl.EstudianteDaoH2;
import main.com.dh.academia.model.Estudiante;
import main.com.dh.academia.service.EstudianteService;
import org.junit.Assert;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // copie todito lo testeado en EstudianteServiceTest.java
        IDao<Estudiante> estudianteDaoEnMemoria = new EstudianteDaoEnMemoria(new ArrayList());
        IDao<Estudiante> estudianteDaoH2 = new EstudianteDaoH2(new ConfiguracionJDBC());
        EstudianteService estudianteService = new EstudianteService();

        estudianteService.setEstudianteDao(estudianteDaoEnMemoria);
        estudianteService.guardarEstudiante(new Estudiante("1", "EstudianteUno", "ApellidoUno"));
        estudianteService.setEstudianteDao(estudianteDaoH2);
        estudianteService.guardarEstudiante(new Estudiante("2", "EstudianteDos", "ApellidoDos"));

        estudianteService.setEstudianteDao(estudianteDaoEnMemoria);
        Estudiante estudiante = estudianteService.buscar("1");

        System.out.println("El estudiante con id 1 es:" + estudiante);

        estudianteService.setEstudianteDao(estudianteDaoH2);
        estudiante = estudianteService.buscar("2");

        System.out.println("El estudiante con id 2 es:" + estudiante);
    }
}
