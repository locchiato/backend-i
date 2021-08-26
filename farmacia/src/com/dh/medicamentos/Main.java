package com.dh.medicamentos;

import com.dh.medicamentos.dao.impl.AmigoDAOH2;
import com.dh.medicamentos.model.Amigo;
import com.dh.medicamentos.service.AmigoService;
import org.junit.Assert;

public class Main {

    public static void main(String[] args) {
	// write your code here
        AmigoService service = new AmigoService(new AmigoDAOH2());
        int cantidad = service.listarAmigos().size();
        System.out.println("La cantidad es: " + cantidad);

        Amigo amigo = new Amigo();
        amigo.setNombre("Jorge");

        System.out.println("El id es: " + amigo.getId());

        service.guardarAmigo(amigo);
        System.out.println("El id es: " + amigo.getId());

        cantidad = service.listarAmigos().size();

        System.out.println("La cantidad es: " + cantidad);


    }
}
