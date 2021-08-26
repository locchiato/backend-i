package com.clase13;

import java.sql.*;
import org.apache.log4j.Logger;


public class UsuariosDB {

    private static final String SQL_CREATE = "DROP TABLE IF EXISTS USUARIO; CREATE TABLE USUARIO ("
    +   "ID INT PRIMARY KEY, "
    +   "PRIMER_NOMBRE VARCHAR(40) NOT NULL, "
    +   "APELLIDO VARCHAR(40) NOT NULL, "
    +   "EDAD INT NOT NULL"
    +   ")";

    private static final String SQL_INSERT = "INSERT INTO USUARIO (id, primer_nombre, apellido, edad) VALUES " +
            "(1, 'Agustín', 'Bravo', 20), " +
            "(2, 'Paris', 'Franco', 20), " +
            "(3, 'Francisco', 'Gerardi', 18), " +
            "(4, 'Lau', 'Loreiro', 26), " +
            "(5, 'Natalia', 'Narváez', 22)";


    private static final String SQL_DELETE = "DELETE FROM USUARIO WHERE id = 1";
    private static final String SQL_SELECT = "SELECT * FROM USUARIO";

    private static final Logger logger = Logger.getLogger(UsuariosDB.class.toString());

    public static void main(String[] args) throws Exception {

        // 1. Nos conectamos (El DriverManager nos da el Connection)
        Connection connection = DriverManager.getConnection("jdbc:h2:~/OneDrive/Desktop/CTDTrabajos/3erBimestre/Backend1/c12_db", "sa", "");

        // 2. Creamos el Statement para ejecutar sentencias SQL
        Statement statement = connection.createStatement();
        statement.execute(SQL_CREATE);
        statement.execute(SQL_INSERT);

        // 3. Leemos los resultados de las sentencias con ResultSet
        ResultSet rs = statement.executeQuery(SQL_SELECT);

        logger.info("Tabla usuarios previo al DELETE: ");
        while(rs.next()){
            logger.info("id: " + rs.getInt(1) + ". primer_nombre: " + rs.getString(2) + ". apellido:  " + rs.getString(3) + ". edad: " + rs.getInt(4));
        }

        statement.execute(SQL_DELETE);
        rs = statement.executeQuery(SQL_SELECT);

        logger.info("Tabla usuarios posterior al DELETE: ");
        while(rs.next()){
            logger.info("id: " + rs.getInt(1) + ". primer_nombre: " + rs.getString(2) + ". apellido:  " + rs.getString(3) + ". edad: " + rs.getInt(4));
        }

        // 4. Cerramos la Connection
        connection.close();
    }
}
