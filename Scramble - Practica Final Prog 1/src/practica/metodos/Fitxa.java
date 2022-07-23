/*
 Con dicha clase generamos lo que es una ficha y la separamos con sus debido atributos
 Grupo formado por: Carlos Larruscain y Constantino Byelov
 */
package practica.metodos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author csbye
 */
public class Fitxa {

    //Atributos de la clase, además de variables globales
    private char letra;
    private int cantidad;
    private int puntos;
    public int puntaje = 0;
    private Fitxa[] arrayFitxa;
    private char[] tablero, tablero2;
    private Fitxa[] tableroPuntos;
    private char[] palabra;
    public Palabra p3;

    //constructor con los atributos pertinentes a la clase ficha
    public Fitxa(char letra, int cantidad, int puntos) {
        this.letra = letra;
        this.cantidad = cantidad;
        this.puntos = puntos;
    }

    //constructor con los atributos letra y puntos sin el atributo cantidad.
    public Fitxa(char letra, int puntos) {
        this.letra = letra;
        this.puntos = puntos;
    }

    //constructor vacio
    public Fitxa() {
    }

    //getter del atributo letra
    public char getLetra() {
        return letra;
    }

    //getter del atributo cantidad
    public int getCantidad() {
        return cantidad;
    }

    //getter del atributo puntos
    public int getPuntos() {
        return puntos;
    }

    //metodo toString de la clase Ficha
    @Override
    public String toString() {
        return "Fitxa: " + "Letra=" + letra + " Puntos=" + puntos + '}';
    }

    //metodo que crea todas las fichas, acabamos con un array de fichas con el cual se puede trabajar.
    public void creaFichas() throws Exception {
        //declaramos la clase fichero
        FitxerosIn ficheroP;
        //declaramos el array de fichas
        arrayFitxa = new Fitxa[110];
        int pos = 0;

        //leemos el fichero "alfabet" y vamos sacando los parametros
        Palabra p = new Palabra("alfabet.alf".toCharArray());
        ficheroP = new FitxerosIn(p);
        //leemos la primera linea que se corresponde al numero de fichas que tenemos 104
        ficheroP.hayPalabras();
        ficheroP.lectura();

        //mientras haya palabras seguimos leyendo
        while (ficheroP.hayPalabras()) {

            //leemos cada palabra por separado y las asignamos a las variables de la ficha
            char let = (ficheroP.lectura()).obtenerCaracter(0);
            ficheroP.hayPalabras();
            int cant = Integer.parseInt(ficheroP.lectura().toString());
            ficheroP.hayPalabras();
            int punt = Integer.parseInt(ficheroP.lectura().toString());

            //creamos cada ficha y las guardamos en un array para poder trabajar con ellas
            for (int i = 0; i < cant; i++) {
                //En el caso de que el array de fichas se quede pequeño lo aumentamos
                if (pos == arrayFitxa.length) {
                    Fitxa[] masArrayEst = Arrays.copyOf(arrayFitxa, arrayFitxa.length + 1);
                    arrayFitxa = masArrayEst;
                }

                arrayFitxa[pos] = new Fitxa(let, punt);
                pos++;

            }

        }

        //cerramos enlace con el fichero
        ficheroP.cerrarEnlaceFichero();
    }

    //metodo que coge aleatoriamente una ficha y la pone en el tablero
    public void sorteo() {

        //variables char array
        Random r = new Random();
        tablero = new char[11];
        tablero2 = new char[11];
        tableroPuntos = new Fitxa[11];

        for (int i = 0; i < 11; i++) {
            int x = r.nextInt(104);
            //Hago un array de booleanos para ver si dicha ya ha salido

            //cogemos 11 letras y las asignamos a un array
            tablero[i] = arrayFitxa[x].getLetra();
            tablero2[i] = arrayFitxa[x].getLetra();
            tableroPuntos[i] = arrayFitxa[x];

        }

        //imprimo las fichas que han salido
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(tablero[i] + "   ");
        }

    }

    //metodo que verifica si la palabra metida tiene las letras del tablero
    public void verifica() throws Exception {
        //cogemos la palabra que nos han puesto 
        LT lt = new LT();
        boolean encontrada = false;
        int num = 0;
        palabra = lt.llegirLiniaC();
        //verificamos que las letras que tiene la palabra estan en el array tablero
        //cogemos la  primera letra del array palabra 
        for (int i = 0; i < palabra.length; i++) {
            encontrada = false;
            //esta se va al segundo for donde entra al array tablero
            for (int j = 0; j < tablero.length && !encontrada; j++) {
                //si la letra de palabra es igual al de tablero entramos en el if
                if (palabra[i] == tablero[j]) {
                    tablero[j] = 0;
                    num++;
                    encontrada = true;
                }
            }
        }

        //si las letras estan en el tablero 
        if (num == palabra.length) {
            //aqui se ha visto que las letras de la palabra introducida son TODAS pertenecientes a la tabla dada
            // además se mira que la letra no se repita y no coga dos veces la misma letra cogiendola por buena
            System.out.println("Verificando...");
            //Ahora miramos si la palabra que nos han dado pertenece al diccionario castellano.
            verificaDIC();
            //bucle iterativo del fichero de palabras en busca de la palabra 
            //si encontramos dicha palabra miramos cuanta puntuacion daria 

        } else {
            //si la palabra no pertenece restamos 10 puntos.
            System.out.println("Palabra con letras no pertenecientes a su tablero.");
            puntaje = puntaje - 10;
            System.out.println("Puntuación actual(2): " + puntaje);

        }

    }

    //metodo que verifica si la palabra metida esta en el diccionario
    private void verificaDIC() throws Exception {

        //abrimos el fichero de palabras
        Palabra pa = new Palabra("esp.dic".toCharArray());
        FitxerosIn fic = new FitxerosIn(pa);
        boolean encontrada = false;

        //declaramos 2 objetos palabras
        Palabra p2 = new Palabra();

        //leemos palabras
        fic.hayPalabras();
        p2 = fic.lectura();

        //mientras haya palabras leemos
        while (fic.hayPalabras()) {

            //si la palabra leida es igual a la palabra del usuario calculamos la puntuacion
            if (p2.iguales(palabra, (p2.toString().toCharArray()))) {
                System.out.println("Puntuación actual(1): " + calculo());
                encontrada = true;
            }
            //leemos la siguiente palabra
            p2 = fic.lectura();

        }

        //si la palabra no se ha encontrado restamos 10 puntos al puntuaje y hacemos el print
        if (encontrada == false) {
            System.out.println("No se ha encontrado dicha palabra en nuestro diccionario.");
            puntaje = puntaje - 10;

            System.out.println("Puntuación actual(Penalizado): " + puntaje);
        }
        //cerramos el fichero.
        fic.cerrarEnlaceFichero();
    }

    //metodo que calcula la puntuacion de una palabra.
    private int calculo() {
        boolean encontrada = false;

        //vamos letra por letra y con ayuda del tableroPunto vamos sacando los puntos de cada char.
        for (int i = 0; i < palabra.length; i++) {
            encontrada = false;
            for (int j = 0; j < tableroPuntos.length && !encontrada; j++) {
                if (tableroPuntos[j].getLetra() == palabra[i]) {
                    //vamos concatenando las puntuaciones para conseguir la puntuacion final.
                    puntaje = puntaje + tableroPuntos[j].getPuntos();
                    encontrada = true;
                }
            }

        }
        //hacemos return del puntaje 
        return puntaje;

    }

    //metodo que registra todos los datos dentro del fichero estadisiticas.
    public void registro() throws Exception {
        //Declaramos la apertura del fichero de escritura
        LT lt = new LT();

        //preguntamos el nombre del usuario
        System.out.println("Introduzca su nombre, para penerle en las estadisiticas del juego: ");
        char[] nombre = lt.llegirLiniaC();

        //abrimos el fichero out
        Palabra punt = new Palabra(puntaje);
        Palabra pa = new Palabra("estadisticas.txt".toCharArray());
        Palabra fecha = new Palabra(fecha().toCharArray());
        FicherosOut fo = new FicherosOut(pa);

        //pasamos el nombre a string para poder guardarlo
        Palabra p2 = new Palabra(nombre);

        //escribimos el nombre
        fo.nuevaLinea();
        fo.escritura(p2);
        fo.escrituraSeparador();
        //la fecha 
        fo.escritura(fecha);
        fo.escrituraSeparador();
        //y la puntuacion final
        fo.escritura(punt);
        fo.nuevaLinea();
        //cerramos el enlace con el fichero
        fo.cerrarEnlaceFichero();
    }
    int mp, pf;

    //metodo IA que hace la elección de la mejor palabra en el modo superordenador
    public void IA() throws Exception {

        //declaramos la apertura del fichero
        Palabra pa = new Palabra("esp.dic".toCharArray());
        FitxerosIn fic = new FitxerosIn(pa);

        //declaramos un objeto palabras
        Palabra p2 = new Palabra();
        //leemos una palabra
        fic.hayPalabras();
        p2 = fic.lectura();
        //declaramos un itn maximapuntuacion=mp
        mp = 0;

        //leemos la primera palabra, mientras haya palabras 
        while (fic.hayPalabras()) {

            //leemos la palabra del fichero y lo pasamos a array
            palabra = p2.toString().toCharArray();

            boolean encontrada = false;
            int num = 0;
            //Verificamos si la palabra elegida se puede hacer con las letras del tablero
            for (int i = 0; i < palabra.length; i++) {
                encontrada = false;
                //esta se va al segundo for donde entra al array tablero
                for (int j = 0; j < tablero.length && !encontrada; j++) {
                    //si la letra de palabra es igual al de tablero entramos en el if
                    if (palabra[i] == tablero[j]) {
                        tablero[j] = 0;
                        num++;
                        encontrada = true;
                    }
                }
            }
            //cada vez que hayamos verificado una palabra hacemos que el array 
            //se vuelva a llenar con las mismas letras
            for (int j = 0; j < tablero2.length; j++) {
                tablero[j] = tablero2[j];
            }

            //si las letras estan en el tablero 
            if (num == palabra.length) {
                //aqui se ha visto que las letras de la palabra introducida son TODAS pertenecientes a la tabla dada
                // además se mira que la letra no se repita y no coga dos veces la misma letra cogiendola por buena

                //Ahora miramos si la palabra que nos han dado pertenece al diccionario castellano.
                verificaDICIA();

                //si la puntuacion de la palabra leida es mayor que la actual intercambiamos palabras y actualizamos puntos
                if (puntos2 > mp) {

                    //cambiamos mp por puntos2
                    mp = puntos2; 
                    
                    //cambiamos la palabra leida por la palabra parametro
                    p3 = p2;
                    puntos2 = 0;

                }

            }
            //leemos la siguiente palabra
            p2 = fic.lectura();
        }
        pf=pf+mp;
        fic.cerrarEnlaceFichero();
    }

    public void niveles(int niv) throws Exception {

        //declaramos la apertura del fichero
        Palabra pa = new Palabra("esp.dic".toCharArray());
        FitxerosIn fic = new FitxerosIn(pa);

        //declaramos un objeto palabras
        Palabra p2 = new Palabra();
        //leemos una palabra
        fic.hayPalabras();
        p2 = fic.lectura();
        //declaramos un itn maximapuntuacion=mp
        mp = 0;

        Random rd = new Random();
       int rand=0;
        
        //Si nivel entre 0-3 facil
        if (niv>0 && niv<=3) {
            rand = (rd.nextInt(niv)+1)*100 ;
        }else if(niv>3 && niv<=6){
             rand = (rd.nextInt(niv)+1)*10000 ;
        }else if(niv>6 && niv<=10){
            rand = (rd.nextInt(niv)+1)*15000 ;
        }

        while (rand > niv) {
           
            fic.hayPalabras();
            p2 = fic.lectura();
                    
                   
            palabra = p2.toString().toCharArray();

            boolean encontrada = false;
            int num = 0;

                //Verificamos si la palabra elegida se puede hacer con las letras del tablero
                for (int i = 0; i < palabra.length; i++) {
                    encontrada = false;
                    //esta se va al segundo for donde entra al array tablero
                    for (int j = 0; j < tablero.length && !encontrada; j++) {
                        //si la letra de palabra es igual al de tablero entramos en el if
                        if (palabra[i] == tablero[j]) {
                            tablero[j] = 0;
                            num++;
                            encontrada = true;
                        }
                    }
                }
                //cada vez que hayamos verificado una palabra hacemos que el array 
                //se vuelva a llenar con las mismas letras
                for (int j = 0; j < tablero2.length; j++) {
                    tablero[j] = tablero2[j];
                }

                //si las letras estan en el tablero 
                if (num == palabra.length) {
                    //aqui se ha visto que las letras de la palabra introducida son TODAS pertenecientes a la tabla dada
                    // además se mira que la letra no se repita y no coga dos veces la misma letra cogiendola por buena

                    //Ahora miramos si la palabra que nos han dado pertenece al diccionario castellano.
                    verificaDICIA();

                    //si la puntuacion de la palabra leida es mayor que la actual intercambiamos palabras y actualizamos puntos
                    if (puntos2 > mp) {

                        //cambiamos mp por puntos2
                        mp = puntos2;
                        //cambiamos la palabra leida por la palabra parametro
                        p3 = p2;
                        puntos2 = 0;

                    }

                }
                //leemos la siguiente palabra
                p2 = fic.lectura();
                niv++;
            
        }
        pf=pf+mp;
        fic.cerrarEnlaceFichero();
    }
    //metodo que calcula la puntuacion de una palabra.

    private int calculo2() {
        puntaje = 0;
        boolean encontrada = false;

        //con el for vamos caracter por caracter sacando los puntos que vale 
        //cada uno y alfinal hacemos return de la puntuacion pricipal
        for (int i = 0; i < palabra.length; i++) {
            encontrada = false;
            for (int j = 0; j < tableroPuntos.length && !encontrada; j++) {
                if (tableroPuntos[j].getLetra() == palabra[i]) {
                    puntaje = puntaje + tableroPuntos[j].getPuntos();
                    encontrada = true;
                }
            }

        }
        return puntaje;

    }

    int puntos2;

    //metodo verificaDic de la Ia que va verificando si la palabra dada exista en el diccionario o no
    private void verificaDICIA() throws Exception {

        //abrimos el fichero de palabras
        Palabra pa = new Palabra("esp.dic".toCharArray());
        FitxerosIn fic = new FitxerosIn(pa);
        boolean encontrada = false;

        //declaramos 2 objetos palabras
        Palabra p2 = new Palabra();

        //leemos la palabra
        fic.hayPalabras();
        p2 = fic.lectura();

        //mientras haya palabras 
        while (fic.hayPalabras()) {

            //si las palabras leidas son iguales 
            if (p2.iguales(palabra, (p2.toString().toCharArray()))) {
                //calculamos los puntos y los asignamos a c
                int c = calculo2();
                //si c mayor que los puntos actuales actualizmos puntos2 con la puntuacion de c
                if (c > puntos2) {
                    puntos2 = c;
                }
                //boolean que dice que hemos encontrado la palabra
                encontrada = true;
            }
            //leemos una nueva palabra
            p2 = fic.lectura();

        }

        //si el boolean es falso, implica que la palabra no se ha encontrado y como consecuencia
        //hacemos que la puntuacion se reduzca 10.
        if (encontrada == false) {
            System.out.println("No se ha encontrado dicha palabra en nuestro diccionario.");
            puntaje = puntaje - 10;

            System.out.println("Puntuación actual: " + puntaje);
        }
        //cerramos el fichero
        fic.cerrarEnlaceFichero();
    }

    //metodo fecha que obtiene la fecha actual y la devuelve en forma de string
    //cabe destacar que el string no se manipula en ningun momento, ya que en cuanto 
    //sale de este metodo pasa a ser un array y el array pasa ha ser una palabra la cual 
    //ya manipulamos.
    private String fecha() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
