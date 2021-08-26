package main.com.dh.clinica;

import main.com.dh.clinica.dao.impl.DomicilioDaoH2;
import main.com.dh.clinica.dao.impl.PacienteDaoH2;
import main.com.dh.clinica.model.Domicilio;
import main.com.dh.clinica.model.Paciente;
import main.com.dh.clinica.service.DomicilioService;
import main.com.dh.clinica.service.PacienteService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        PacienteService pacienteService = new PacienteService(new PacienteDaoH2(new DomicilioDaoH2()));
        DomicilioService domicilioService = new DomicilioService(new DomicilioDaoH2());

        Domicilio domicilio = new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente p = pacienteService.guardar(new Paciente("Santiago", "Paz", "88888888", new Date(), domicilio));
        Domicilio domicilio1 = new Domicilio("Av Avellaneda", "333", "CABA", "Buenos Aires");
        Paciente p1 = pacienteService.guardar(new Paciente("Micaela", "Perez", "99999999", new Date(), domicilio));

        Domicilio domicilio3 = new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente p3 = pacienteService.guardar(new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));

        pacienteService.buscar(p3.getId());


        pacienteService.eliminar(3);
        pacienteService.buscar(3);


        List<Paciente> pacientes = pacienteService.buscarTodos();

        System.out.println(pacientes);
    }
}
