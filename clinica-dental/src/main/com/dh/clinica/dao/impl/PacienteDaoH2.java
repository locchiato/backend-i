package main.com.dh.clinica.dao.impl;

import main.com.dh.clinica.dao.IDao;
import main.com.dh.clinica.dao.configuracion.ConfiguracionJDBC;
import main.com.dh.clinica.model.Domicilio;
import main.com.dh.clinica.model.Paciente;
import main.com.dh.clinica.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PacienteDaoH2 implements IDao<Paciente> {

    private ConfiguracionJDBC configuracionJDBC;
    private DomicilioDaoH2 domicilioDaoH2;

    public PacienteDaoH2(DomicilioDaoH2 domicilioDaoH2) {
        this.configuracionJDBC = new ConfiguracionJDBC();
        this.domicilioDaoH2 = domicilioDaoH2;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement stmt = null;
        paciente.setDomicilio(domicilioDaoH2.guardar(paciente.getDomicilio()));
        String query = String.format("INSERT INTO pacientes(nombre,apellido,dni,fecha_ingreso,domicilio_id) VALUES(" +
                        "'%s','%s','%s','%s','%d')", paciente.getNombre(),
                paciente.getApellido(), paciente.getDni(), Util.dateToTimestamp(paciente.getFechaIngreso()), paciente.getDomicilio().getId());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if(keys.next())
                paciente.setId(keys.getInt(1));
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paciente;
    }

    @Override
    public Optional<Paciente> buscar(Integer id) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement stmt = null;
        String query = String.format("SELECT id,nombre,apellido,dni,fecha_ingreso,domicilio_id  FROM pacientes where id = '%s'", id);
        Paciente paciente = null;
        try {
            stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                paciente = crearObjetoPaciente(result);
            }

            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return paciente != null ? Optional.of(paciente) : Optional.empty();
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement stmt = null;
        String query = String.format("DELETE FROM pacientes where id = %s", id);
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement stmt = null;
        String query = "SELECT *  FROM pacientes";
        List<Paciente> pacientes = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {

                pacientes.add(crearObjetoPaciente(result));

            }

            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return pacientes;
    }

    private Paciente crearObjetoPaciente(ResultSet result) throws SQLException {

        Integer idPaciente = result.getInt("id");
        String nombre = result.getString("nombre");
        String apellido = result.getString("apellido");
        String dni = result.getString("dni");
        Date fechaIngreso = result.getDate("fecha_ingreso");
        Domicilio domicilio = domicilioDaoH2.buscar(result.getInt("domicilio_id")).orElse(null);
        return new Paciente(idPaciente, nombre, apellido, dni, fechaIngreso, domicilio);

    }
}
