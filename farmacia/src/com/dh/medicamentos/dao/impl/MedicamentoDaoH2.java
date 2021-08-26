package com.dh.medicamentos.dao.impl;

import com.dh.medicamentos.dao.ConfiguracionJDBC;
import com.dh.medicamentos.dao.IDao;
import com.dh.medicamentos.model.Medicamento;
import org.apache.log4j.Logger;

import java.sql.*;


public class MedicamentoDaoH2 implements IDao<Medicamento> {

    private ConfiguracionJDBC configuracionJDBC;
    final static Logger log = Logger.getLogger(MedicamentoDaoH2.class);
    public MedicamentoDaoH2(ConfiguracionJDBC configuracionJDBC) {
        this.configuracionJDBC = configuracionJDBC;
    }


    @Override
    public Medicamento guardar(Medicamento medicamento) {
        log.debug("Registrando un nuevo medicamento : "+ medicamento.toString());
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String query = String.format("INSERT INTO medicamentos(nombre,laboratorio,cantidad,precio) VALUES('%s','%s','%s','%s')",
                medicamento.getNombre(),medicamento.getLaboratorio(),medicamento.getCantidad(),medicamento.getPrecio());

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next())
                medicamento.setId(keys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return medicamento;
    }


    public Medicamento guardar2(Medicamento medicamento) {
        log.debug("Registrando un nuevo medicamento con prepareStatement: "+ medicamento.toString());
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        PreparedStatement sentencia = null;
        String query = "INSERT INTO medicamentos(nombre,laboratorio,cantidad,precio) VALUES(?,?,?,?)";

        try {
            sentencia = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            sentencia.setString(1,medicamento.getNombre());
            sentencia.setString(2, medicamento.getLaboratorio());
            sentencia.setInt(3,medicamento.getCantidad());
            sentencia.setDouble(4,medicamento.getPrecio());
            sentencia.executeUpdate();

            ResultSet keys = sentencia.getGeneratedKeys();
            if (keys.next())
                medicamento.setId(keys.getInt(1));
            sentencia.close();
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return medicamento;
    }


    @Override
    public Medicamento buscar(Integer id) {
        log.debug("Buscando medicamento con id : "+id);
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement stmt = null;
        String query = String.format("SELECT id,nombre,laboratorio,cantidad,precio  FROM medicamentos where id = '%s'", id);
        Medicamento medicamento = null;
        try {
            stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {

                medicamento = new Medicamento(result.getInt("id"),
                        result.getString("nombre"),result.getString("laboratorio"),result.getInt("cantidad"),result.getDouble("precio"));
            }

            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  medicamento;
    }
}
