package com.clase13;

import java.sql.*;

public class Test {

    private static final String SQL_CREATE_TABLE = "DROP TABLE IF EXISTS USUARIO; CREATE TABLE USUARIO"
            + "("
            + "ID INT PRIMARY KEY NOT NULL, "
            + " NOMBRE varchar(100) NOT NULL, "
            + " EMAIL varchar(100) NOT NULL, "
            + " SUELDO numeric(15, 2) NOT NULL"
            + " )";

    private static final String SQL_INSERT = "INSERT INTO USUARIO (ID, NOMBRE, EMAIL, SUELDO) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE USUARIO SET SUELDO = ? WHERE EMAIL = ?";

    public static void main(String[] args) throws  Exception{
        Usuario usuario = new Usuario("Pedro", "pedro@gmail.com", 18d);
        Usuario usuario2 = new Usuario("María", "maría@hotmail.com", 20d);

        //guardamos al usuario en la base de datos, para eso debemos crear una conexión:
        Connection connection = null;
        //en un principio va a ser null, porque la vamos a crear dentro de un try catch

        try{ //en el try creamos la conexión
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE);

            //Una vez creada la tabla, hacemos un prepareStatement para hacer el insert:
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);

            //Empezamos a insertar en la BD

            //Primero el ID, que es un int:
            psInsert.setInt(1, 1);

            //el nombre lo sacamos del usuario
            psInsert.setString(2, usuario.getNombre());
            psInsert.setString(3, usuario.getEmail());
            psInsert.setDouble(4, usuario.getSueldo());

            psInsert.execute();

            psInsert.setInt(1, 2);
            psInsert.setString(2, usuario2.getNombre());
            psInsert.setString(3, usuario2.getEmail());
            psInsert.setDouble(4, usuario2.getSueldo());

            //ahora sólo falta ejecutar el insert:
            psInsert.execute();

            //Empezar la transacción. Para esto, debemos setear el autocommit en false:
            connection.setAutoCommit(false); //si no ponemos nada, es true por default.

            //ahora, preparamos el segundo statement, que es el UPDATE:
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setDouble(1, usuario.subirSueldo(10));
            psUpdate.setString(2, usuario.getEmail());
            psUpdate.execute();

            //esto lo hacemos a propósito para que haya un rollback. Nos va a dar una arithmeticException:
            //int a = 4/0;

            //ahora, hacemos un  para finalizar el block de transacción:

            connection.commit();

            //ES BUENA PRÁCTICA PONER EL AUTOCOMMIT OTRA VEZ EN TRUE:
            connection.setAutoCommit(true);

            //Ahora corremos una query para ver si el sueldo ahora es 28:
            String sql = "SELECT * FROM USUARIO";
            Statement stmt = connection.createStatement();

            //con resultSet buscamos en la base de datos; esto nos devuelve un objeto resultSet:
            ResultSet rd = stmt.executeQuery(sql);
            //y ahora iteramos este objeto:
            while(rd.next()){
                System.out.println(rd.getInt(1) + " " + rd.getString(2) + " " + rd.getString(3) + " " + rd.getDouble(4));
            }

        }catch(Exception e){
            e.printStackTrace();
            connection.rollback(); //para que la conexión haga un rollback, debe haber una excepción

        }finally { //es buena práctica cerrar la conexión
            connection.close();

        }

        //como hubo un error, el rollback nos trae el valor anterior al update:
        /*Connection connection1 = getConnection();
        String sql = "SELECT * FROM USUARIO";
        Statement stmt = connection1.createStatement();
        ResultSet rd = stmt.executeQuery(sql);

        while(rd.next()){
            System.out.println(rd.getInt(1) + " " + rd.getString(2) + " " + rd.getString(3) + " " + rd.getDouble(4));
        }*/
    }

    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/test2");
    }
}
