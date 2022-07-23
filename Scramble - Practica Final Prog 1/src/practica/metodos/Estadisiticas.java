/*
Clase de Estadisiticas que crea un objeto estadistica que despues puede ser 
representado mediante el metodo toString

 Grupo formado por: Carlos Larruscain y Constantino Byelov
 */
package practica.metodos;

import java.util.Arrays;

/**
 *
 * @author csbye
 */
public class Estadisiticas {

    //Declaramos los atributos de la clase
    private Palabra nombre;

    private Palabra fecha;

    private Palabra hora;

    private Palabra puntos;

    private Estadisiticas[] arrayEst;

    //Constructro de la clase Estadistica
    public Estadisiticas(Palabra nombre, Palabra fecha, Palabra hora, Palabra puntos) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.puntos = puntos;
    }

    //Constructor vacio
    public Estadisiticas() {

    }

    //getter del Nombre
    public Palabra getNombre() {
        return nombre;
    }

    //metodo toString
    @Override
    public String toString() {
        return " " + nombre + " " + fecha + " " + hora + " " + puntos;
    }

    //metodo que crea la ficha estadistica
    public void creaFichasEst() throws Exception {
        FitxerosIn ficheroP;
        int n = 0;
        int pos = 0;
        arrayEst = new Estadisiticas[5];
        //leemos el fichero "alfabet" y vamos sacando los parametros
        Palabra p = new Palabra("estadisticas.txt".toCharArray());
        ficheroP = new FitxerosIn(p);
        //leemos la primera linea que se corresponde al numero de fichas que tenemos 104

        //mientras haya palabras seguimos leyendo
        while (ficheroP.hayPalabras()) {

            //leemos cada palabra por separado y las asignamos a las variables de la ficha
            nombre = (ficheroP.lectura());
            ficheroP.hayPalabras();
            fecha = (ficheroP.lectura());
            ficheroP.hayPalabras();
            hora = (ficheroP.lectura());
            ficheroP.hayPalabras();
            puntos = ficheroP.lectura();

            //creamos cada ficha y las guardamos en un array para poder trabajar con ellas
            if (pos == arrayEst.length) {
                Estadisiticas[] masArrayEst = Arrays.copyOf(arrayEst, arrayEst.length + 1);
                arrayEst = masArrayEst;
            }

            arrayEst[pos] = new Estadisiticas(nombre, fecha, hora, puntos);
            pos++;

        }

        //cerramos enlace con el fichero
        ficheroP.cerrarEnlaceFichero();

        
    }

    //representación de lo que hay en el array de estadisticas
    public void representacion() {

        System.out.println("Estas son las estadísiticas guardadas: ");

        for (int i = 0; i < arrayEst.length; i++) {
            System.out.println(arrayEst[i].toString()+ "\n");
        }

    }
    
    
    //Mediante el metodo busquedaPorNombre donde nos pasan el array nombre como parametro de la funcion
    //buscamos con dicho array el nombre de la ficha que coincida con el.
    public void busquedaPorNombre(char[] p) {
        
        Palabra pal= new Palabra();
        System.out.println("\n");

        for (int i = 0; i < arrayEst.length; i++) {            
            
            if (pal.iguales(p, ((arrayEst[i].getNombre()).toString()).toCharArray())) {
                System.out.println(arrayEst[i].toString());
            }
            
        }
        System.out.println("\n\n");

    }

}
