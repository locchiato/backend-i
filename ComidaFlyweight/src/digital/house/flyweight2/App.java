package digital.house.flyweight2;

import digital.house.flyweight2.factory.ArbolFactory;
import digital.house.flyweight2.models.Bosque;

public class App {

    public static void main(String[] args) {

        Bosque bosque = new Bosque();

        bosque.plantarArbol(400.0, 200.0, "orna", "verde"); // arbol 1
        bosque.plantarArbol(300.0, 500.0, "frutal", "rojo"); // arbol 2
        bosque.plantarArbol(200.0, 100.0, "frutal", "celeste"); // arbol 3


        for (int i = 0; i < 500000; i++) {
            bosque.plantarArbol(400.0, 200.0, "coniferas", "rojo"); // arboles 1
        }

        for (int i = 0; i < 500000; i++) {
            bosque.plantarArbol(400.0, 200.0, "coniferas", "verde"); // arboles 2
        }

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memoria usada: " + (runtime.totalMemory() -
                runtime.freeMemory())/ (1024 * 1024) + "MB");


    }
}
