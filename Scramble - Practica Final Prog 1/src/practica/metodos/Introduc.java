/*
Clase secundaria donde se hacen todos las opciones del menu
 Grupo formado por: Carlos Larruscain y Constantino Byelov
 */
package practica.metodos;

import static java.lang.Thread.sleep;

/**
 *
 * @author csbye
 */
public class Introduc {

    LT lt = new LT();

    public void seleccion() throws Exception {
        //variable booleana para saber si el programa se debe acabar
        boolean acaba = false;

        //mientras acaba no sea true seguimos haciendo este bucle
        while (!acaba) {

            //presentamos el programa
            presentacion();
            //leemos la opcion que nos ha puesto
            Integer modo = lt.llegirSencer();
            //verificamos entramos
            while (modo == null || modo <= 0 || modo>4) {

                System.out.println("ERROR! Introduzca un número valido: ");
                modo = lt.llegirSencer();

            }

            //hacemos un switch case para cada opcion y asic reamos el menu
            switch (modo) {

                case 1:
                    //Si case 1 preguntamos con quien quiere jugar solo o maquina
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("Elija el modo de juego: ");
                    System.out.println("1. Juego Individual.");
                    System.out.println("2. Juego con la maquina.");
                    //leemos el int 
                    Integer elj = lt.llegirSencer();

                    while (elj == null || elj <= 0 || elj>3) {

                        System.out.println("ERROR! Introduzca un número valido: ");
                        elj = lt.llegirSencer();

                    }

                    switch (elj) {

                        //caso 1 si se quiere jugar solo
                        case 1:
                            //empezamos la partida para un solo jugador
                            partida();
                            break;

                        //caso 2 si se quiere jugar contra el ordenador
                        case 2:
                            //preguntamos si quiere jugar contra super ordenador o por niveles
                            System.out.println("Elija contra que inteligencia desea jugar: ");
                            System.out.println("1. Superordenador.");//IA invencible
                            System.out.println("2. Por niveles.");//por niveles, cuanto más bajo el nivel mas facil es ganarle
                            //leemos el numero entrado
                            Integer elj2 = lt.llegirSencer();

                            //verificamos entrada
                            while (elj2 == null || elj2 <= 0 || elj2>3) {

                                System.out.println("ERROR! Introduzca un número valido: ");
                                elj2 = lt.llegirSencer();

                            }
                            //si case 1 juega contra el superordenador si no por niveles
                            switch (elj2) {
                                case 1:
                                    //juego contra el super ordenador
                                    //primero hace sus rondas el humano y despues el ordenador.

                                    //empezamos la partida del humano y despues el del robot
                                    partida2();
                                    partidaIA();

                                    break;

                                case 2:
                                    //juego contra el ordenador dependiendo de un nivel
                                    //Al igual que el superordenador vamos por turnos.
                                    partida2();
                                    niveles();

                                    break;
                                //default del case por si hay algun erorr
                                default:
                                    System.out.println("Ha habido algún error, intente de nuevo.");
                                    break;
                            }
                            break;
                        //default del case por si hay algun erorr
                        default:
                            System.out.println("Ha habido algún error, intente de nuevo.");
                            break;
                    }

                    break;
                //si en el menu se elije la la opcion 2 le damos el menu de estadisiticas
                case 2:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                            + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                            + "ESTADISTICAS");
                    //declaramos el objeto estadistica
                    Estadisiticas est = new Estadisiticas();
                    //menu del estadisticas
                    System.out.println("Elige una opción: ");
                    System.out.println("1. Mostrar todos los registros.");
                    System.out.println("2. Filtrar por nombre.");
                    Integer modo2 = lt.llegirSencer();
                    //verificamos entrada
                    while (modo2 == null || modo2 <= 0 || modo2>3) {

                        System.out.println("ERROR! Introduzca un número valido: ");
                        modo2 = lt.llegirSencer();

                    }
                    //si case 1 mostramos todos los registros
                    switch (modo2) {

                        case 1:
                            //creamos las fichas de estadisticas al leer el fichero 
                            est.creaFichasEst();
                            //por ultimo representamos todo
                            est.representacion();
                            break;
                        case 2:
                            //case 2 se  busca por nombre
                            //creamos las fichas de estadisticas al leer el fichero
                            est.creaFichasEst();
                            //preguntamos el nombre que tenemos que buscar
                            System.out.println("Por que nombre quiere filtrar: ");
                            //leemos el nombre en forma de array
                            char[] nombre = lt.llegirLiniaC();
                            //si el array esta vacio preguntamos otra vez el nombre, 
                            //asi verificamos la entrada
                            while (nombre == null) {
                                System.out.println("ERROR! Introduzca un nombre valido: ");
                                nombre = lt.llegirLiniaC();
                            }
                            //metodo de la clase estadisiticas que busca por nombre con el array nombre pasado por parametro
                            est.busquedaPorNombre(nombre);
                            break;
                        //default por si ha habido algun error
                        default:
                            System.out.println("Ha habido algún error, intente de nuevo.");
                            break;
                    }

                    break;
                //opcion 3, el usuario quiere salir del menu
                case 3:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                            + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                            + " Gracias por jugar, esperamos verle pronto.");
                    //el boolean acaba se pone a true lo que implica que el programa que va ha acabar.
                    acaba = true;

                    break;
                //default por si ha habido algun error.
                default:
                    System.out.println("Ha habido algún error, intente de nuevo.");
                    break;
            }

        }

    }

    //metodos 
    private int puntosJugadorIA;
    private int puntosOrdenadorIA;
    private Integer partidas, partidas2;

    //metodo que presenta el programa
    private void presentacion() {

        System.out.println("Bienvenido al juego, seleccione una de las "
                + "siguientes opciones:\n");

        System.out.println(" 1 - Jugar. \n "
                + "2 - Ver estadísticas.\n "
                + "3 - Salir del juego.\n ");

    }

    //metodo que crea un contador de 30 segundos
    private void contador() throws InterruptedException {

        System.out.println("\n\n\n\n\n\n");
        System.out.println("Tienes 30s para pensar tu respuesta.");
        sleep(1000);
        System.out.print("30s\t");
        sleep(5000);
        System.out.print("25s\t");
        sleep(5000);
        System.out.print("20s\t");
        sleep(5000);
        System.out.print("15s\t");
        sleep(5000);
        System.out.print("10s\t");
        sleep(5000);
        System.out.println("5s\n");
        sleep(5000);
        System.out.print("Se acabó el tiempo, ponga su respuesta: ");

    }

    //metodo que hace el juego al momento de jugar solo
    private void partida() throws Exception {
        //declaramos el objeto ficha
        Fitxa fix = new Fitxa();

        //pedimos al usuario el numero de partidas a jugar
        System.out.println("¿Cuantas partidas quiere jugar?");
        //leemos el int
        partidas = lt.llegirSencer();
        //verificamos la entrada
        while (partidas == null || partidas <= 0) {

            System.out.println("ERROR! Introduzca un número valido: ");
            partidas = lt.llegirSencer();

        }

        //para n partidas n iteraciones
        for (int i = 0; i < partidas; i++) {
            //creamos las fichas
            fix.creaFichas();
            System.out.println("Estas son tus letras: ");
            //sorteamos las fichas
            fix.sorteo();
            //ponemos el contador
            contador();
            //verificamos la palabra puesta
            fix.verifica();
        }
        System.out.println("\nHa conseguido " + fix.puntaje + " puntos.");
        System.out.println("\nEl juego ha acabado.");
        //por ultimo registramos al usuario
        fix.registro();
    }

    //metodo partida2 que se utiliza al momento de jugar contra la IA
    private void partida2() throws Exception {
        //declaro el objeto ficha
        Fitxa fix = new Fitxa();

        //pregunto el numero de partidas que quiere jugar
        System.out.println("¿Cuantas partidas quiere jugar?");
        //leo el int que me meta
        partidas2 = lt.llegirSencer();
        //Valido la entrada si da error le pido otra vez 
        while (partidas2 == null || partidas2 <= 0) {

            System.out.println("ERROR! Introduzca un número valido: ");
            partidas2 = lt.llegirSencer();

        }
        //para el n numero de partidas hacemos n veces el juego
        for (int i = 0; i < partidas2; i++) {
            //creamos las fichas
            fix.creaFichas();
            System.out.println("Estas son tus letras: ");
            //hacemos el sorteo de las fichas
            fix.sorteo();
            //metemos el contador 
            contador();
            //Y por ultimo realizamos la verificacion de la palabra entrada 
            fix.verifica();
        }
        //printeamos el resultado
        System.out.println("\nHa conseguido " + fix.puntaje + " puntos.");
        //asignamos la puntuacion a una variable global
        puntosJugadorIA = fix.puntaje;
        fix.registro();
    }

    //metodo que crea la partida de la IA
    private void partidaIA() throws Exception {
        //declaramos un objeto ficha
        Fitxa fix = new Fitxa();

        //mientras no se acaben las partidas que nos ha petido seguimos ejecutando
        for (int i = 0; i < partidas2; i++) {
            //creamos las fichas
            fix.creaFichas();
            System.out.println("Letras del ordenador: ");
            //sorteamos las fichas
            fix.sorteo();
            System.out.println("\nEl programa puede que tarde un poco, espero un poco.");
            //la respuesta de la ia
            fix.IA();
            //Printeamos el resultado
            System.out.println("El ordenador ha elegido esta palabra: " + fix.p3);
            System.out.println("\nEl ordenador ha conseguido " + fix.mp + " puntos.");
        }
        //asignamos la puntuacion a una variable global
        puntosOrdenadorIA = fix.pf;
        System.out.println("Puntuacion final: "+puntosOrdenadorIA);
        //como ultimo comparamos las puntuaciones obtenidas por los dos y damos el ganador
        compara(puntosJugadorIA, puntosOrdenadorIA);

    }

    //metodo que compara dos ints y dice quien gana
    private void compara(int a, int b) {
        if (a > b) {

            System.out.println("¡Ha ganado el jugador humano!");

        } else {
            System.out.println("¡Ha ganado el ordenador, mejor suerte la siguiente vez!");
        }
    }

    //metodo de la IA por niveles
    private void niveles() throws Exception {
        //declaramos un objeto ficha
        Fitxa fix = new Fitxa();
        //pedimos el nivel de la IA con la cual quiere jugar
        System.out.println("Elija que nivel de dificultad quiere (1-10): ");
        Integer nivel = lt.llegirSencer();

        if (nivel == null || nivel < 0 || nivel > 11) {

            System.out.println("Error, introduzca el nivel de nuevo (1-10): ");
            nivel = lt.llegirSencer();

        }

        for (int i = 0; i < partidas2; i++) {
            //creamos las fichas
            fix.creaFichas();
            System.out.println("Letras del ordenador: ");
            //sorteamos las fichas
            fix.sorteo();
            System.out.println("\nEl programa puede que tarde un poco, espere un poco.");
            //la respuesta de la ia
            fix.niveles(nivel);
            System.out.println("El ordenador ha elegido esta palabra: " + fix.p3);
            System.out.println("\nEl ordenador ha conseguido " + fix.mp + " puntos.");
        }
        
        //asignamos la puntuacion a una variable global
        puntosOrdenadorIA = fix.pf;
        System.out.println("Puntuacion final: "+puntosOrdenadorIA);
        //como ultimo comparamos las puntuaciones obtenidas por los dos y damos el ganador
        compara(puntosJugadorIA, puntosOrdenadorIA);
    }

}
