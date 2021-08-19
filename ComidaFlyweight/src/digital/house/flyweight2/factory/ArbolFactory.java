package digital.house.flyweight2.factory;

import digital.house.flyweight2.dto.TipoArbol;

import java.util.*;

public class ArbolFactory {

    private static final Map<String, TipoArbol> tiposArbol = new HashMap<>();
    private static final ArbolFactory INSTANCE = new ArbolFactory();

    public static ArbolFactory getInstance() {
        return INSTANCE;
    }

    public TipoArbol getTipoArbol(String tipo, String color) {
        String key = tipo + color;
        if(!tiposArbol.containsKey(key)) {
            tiposArbol.put(key, new TipoArbol(tipo, color));
        }

        return tiposArbol.get(key);
    }

}
