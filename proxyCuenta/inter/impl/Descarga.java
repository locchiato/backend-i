package proxyCuenta.inter.impl;

import proxyCuenta.inter.ServicioDescarga;
import proxyCuenta.model.Usuario;

public class Descarga implements ServicioDescarga {
    @Override
    public void descargar(Usuario user) {
        System.out.println("La descarga se realizo con exito.");
    }
}
