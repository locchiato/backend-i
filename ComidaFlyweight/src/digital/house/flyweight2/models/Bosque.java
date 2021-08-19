package digital.house.flyweight2.models;

import digital.house.flyweight2.dto.TipoArbol;
import digital.house.flyweight2.factory.ArbolFactory;

import java.util.*;

public class Bosque {

    public static final List<Arbol> arboles = new ArrayList<>();
    private ArbolFactory factory = ArbolFactory.getInstance();

    public Arbol plantarArbol(Double ancho, Double alto, String tipo, String color) {
        Arbol arbol = new Arbol(ancho, alto, factory.getTipoArbol(tipo, color));
        arboles.add(arbol);
        return arbol;
    }

    public void listarArboles(){
        for (Arbol arbol : arboles) {
            System.out.println(arbol.toString());
        }
    }

}
