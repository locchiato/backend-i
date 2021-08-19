package digital.house.flyweight2.models;

import digital.house.flyweight2.dto.TipoArbol;

public class Arbol {

    private Double ancho;
    private Double alto;
    private TipoArbol tipo;

    public Arbol(Double ancho, Double alto, TipoArbol tipo) {
        this.ancho = ancho;
        this.alto = alto;
        this.tipo = tipo;
    }

}
