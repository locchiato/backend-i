package proxyCuenta.model;

import proxyCuenta.inter.ServicioDescarga;
import proxyCuenta.proxy.UsuarioProxy;

import java.util.Arrays;

public class Usuario {

    private String nombre;
    private Integer idUsuario;
    private String tipoSuscripcion;

    private ServicioDescarga spotify = new UsuarioProxy();

    public Usuario(String nombre, Integer idUsuario, String tipoSuscripcion) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void peticionDescarga(){
        spotify.descargar(this);
    }

    public void setTipoSuscripcion(String tipoSuscripcion) {
        if(Arrays.asList(ServicioDescarga.TYPE_USER).contains(tipoSuscripcion))
        this.tipoSuscripcion = tipoSuscripcion;
    }
}
