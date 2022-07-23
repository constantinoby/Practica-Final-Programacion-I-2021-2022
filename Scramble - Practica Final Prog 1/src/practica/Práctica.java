/*
Practica final Prog1
 Grupo formado por: Carlos Larruscain y Constantino Byelov
 */
package practica;

import practica.metodos.Introduc;

/**
 *
 * @author csbye
 */
public class Práctica {

    /**
     * @param args the command line arguments
     */
    private void inicio() throws Exception {

        //introducimos el juego con sus opciones
        Introduc intro = new Introduc();
        intro.seleccion();

    }

    // Declaración del método main para que el programa sea ejecutable 
    public static void main(String[] args) throws Exception {
        new Práctica().inicio();
    }
}
