package proxyCuenta.proxy;

import proxyCuenta.inter.ServicioDescarga;
import proxyCuenta.inter.impl.Descarga;
import proxyCuenta.model.Usuario;

public class UsuarioProxy implements ServicioDescarga {

    private ServicioDescarga servicio = new Descarga();

    @Override
    public void descargar(Usuario user) {
        if(comprobarSuscripcion(user))
            servicio.descargar(user);
        else
            System.out.println("No se pudo completar la descarga.");
    }

    public Boolean comprobarSuscripcion(Usuario user) {
        return user.getTipoSuscripcion().equals(ServicioDescarga.TYPE_USER[1]);
    }
}
