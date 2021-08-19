package proxyCuenta.inter;

import proxyCuenta.model.Usuario;

public interface ServicioDescarga {

    String[] TYPE_USER = {"FREE", "PREMIUM"};

    void descargar(Usuario user);
}
