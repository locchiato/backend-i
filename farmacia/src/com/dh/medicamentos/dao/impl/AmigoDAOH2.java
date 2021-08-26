package com.dh.medicamentos.dao.impl;

import com.dh.medicamentos.dao.IDao2;
import com.dh.medicamentos.model.Amigo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class AmigoDAOH2 implements IDao2<Amigo> {

    // instancia de logger para esta clase
    private final static Logger log = Logger.getLogger(AmigoDAOH2.class);

    @Override
    public Amigo guardar(Amigo amigo) {
        Connection con = null;

        try {
             // creacion de la conexion
             con = DriverManager.getConnection("jdbc:h2:~/test4", null,null);
             // query con 1 parametro para ingresar
             String consulta = "INSERT INTO AMIGOS (NOMBRE) VALUES (?)";
             PreparedStatement sentencia = con.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
             // se ingresa el parametro faltante
             sentencia.setString(1,amigo.getNombre());
             // se ejecuta la query o consulta
             sentencia.executeUpdate();

             // se obtiene una tabla con la id generada
             ResultSet resultado = sentencia.getGeneratedKeys();

             // se edita el objeto insertado con la id generada
             while(resultado.next()) {
                 amigo.setId(resultado.getInt(1));
             }
             log.info("se guardo el amigo con id:" + amigo.getId());

        } catch (SQLException throwables) {
            log.error(throwables);
        }

        // se devuelve el amigo con los datos actualizados
        return amigo;


    }

    @Override
    public List<Amigo> buscarTodos() {
        Connection con = null;
        ArrayList<Amigo> amigos = null;

        try {

            con = DriverManager.getConnection("jdbc:h2:~/test4", null,null);
            String consulta = "SELECT * FROM AMIGOS";
            PreparedStatement sentencia = con.prepareStatement(consulta);


            ResultSet resultado = sentencia.executeQuery();


            log.info("listando todos los amigos");
            amigos = new ArrayList<>();
            while(resultado.next()) {
                Amigo amigo = new Amigo();
                amigo.setId(resultado.getInt(1));
                amigo.setNombre(resultado.getString(2));
                amigos.add(amigo);

                log.info(amigo.getNombre());
            }


        } catch (SQLException throwables) {
            log.error(throwables);
        }
        return amigos;
    }
}
