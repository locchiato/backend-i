package test.com.dh.medicamentos.service;

import com.dh.medicamentos.dao.ConfiguracionJDBC;
import com.dh.medicamentos.dao.impl.AmigoDAOH2;
import com.dh.medicamentos.dao.impl.MedicamentoDaoH2;
import com.dh.medicamentos.model.Medicamento;
import com.dh.medicamentos.service.AmigoService;
import com.dh.medicamentos.service.MedicamentoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.dh.medicamentos.model.Amigo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AmigoServiceTest {


    @Test
    public void guardar(){
        AmigoService service = new AmigoService(new AmigoDAOH2());
        Amigo amigo = new Amigo();
        amigo.setNombre("Jorge");
        service.guardarAmigo(amigo);

        Assert.assertTrue(amigo.getId() != 0);
    }

    @Test
    public void listadoAmigos(){
        AmigoService service = new AmigoService(new AmigoDAOH2());
        int cantidad = service.listarAmigos().size();

        Assert.assertTrue(cantidad > 0);
    }

}