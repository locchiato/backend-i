package proxyCuenta;

import proxyCuenta.inter.ICuenta;
import proxyCuenta.inter.ServicioDescarga;
import proxyCuenta.inter.impl.CuentaBancoAImpl;
import proxyCuenta.model.Cuenta;
import proxyCuenta.model.Usuario;
import proxyCuenta.proxy.CuentaProxy;
import proxyCuenta.proxy.UsuarioProxy;

import java.util.Locale;
import java.util.Scanner;

public class App {

    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {
        probarUsuarioProxy();
    }

    public static void probarCuentaProxy() {
        Cuenta c = new Cuenta(1,"Agustín",3000);

        ICuenta cuentaProxy = new CuentaProxy(new CuentaBancoAImpl());
        cuentaProxy.mostrarSaldo(c);
        c = cuentaProxy.depositarDinero(c,500);
        c = cuentaProxy.retirarDinero(c,200);
        cuentaProxy.mostrarSaldo(c);

    }

    public static void probarUsuarioProxy(){

        Usuario usuario1, usuario2, usuario3;

        try {
            usuario1 = new Usuario("Charly Garcia",1, ServicioDescarga.TYPE_USER[1]);
            usuario2 = new Usuario("Grisel",2,ServicioDescarga.TYPE_USER[0]);
            usuario3 = new Usuario("",3,ServicioDescarga.TYPE_USER[0]);

            System.out.println(" ");
            usuario1.peticionDescarga();
            usuario2.peticionDescarga();

            System.out.println("⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛");
            System.out.println("Ingrese el tipo de suscripción (free/premium):");
            usuario3.setTipoSuscripcion(scanner.next().toUpperCase(Locale.ROOT));

            System.out.println("⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛");
            System.out.println("Procesando Descarga...");
            System.out.println("⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛⩚⩛");
            usuario3.peticionDescarga();

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }


    }
}
