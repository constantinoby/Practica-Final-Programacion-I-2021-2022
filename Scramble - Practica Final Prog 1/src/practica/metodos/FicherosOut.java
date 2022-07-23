package practica.metodos;

/*
CLASE FicherosOut
Aglutina las delcaraciones y funcionalidades necesarias para posibilitar la escritura
de objetos Palabra en un fichero de texto

 Grupo formado por: Carlos Larruscain y Constantino Byelov
 */


import java.io.BufferedWriter;
import java.io.FileWriter;

public class FicherosOut {
    //DECLARACIÓN ATRIBUTOS
    //declaración atributo de clase constante entero que representa el código
    //de caracter del caracter espacio en blanco
    private static final int COD_ESPACIO=(int) ' ';
    //declaración atributo de clase constante entero que representa el código
    //del caracter de control RETURN
    private static final int RETURN=(int) '\r';
    //declaración atributo de clase constante entero que representa el código
    //del caracter de control SALTO DE LINEA
    private static final int SALTO_LINEA=(int) '\n';
    //declaración atributo de objeto BufferedWriter que posibilite el enlace
    //con el fichero de texto a nivel de escritura
    private BufferedWriter fichero=null;  
    
    //MÉTODOS
    //MÉTODOS CONSTRUCTORES
    
    public FicherosOut(Palabra n) throws Exception {
        //establecimiento enlace BufferedWriter con fichero de texto identificado
        //a través del parámetro String nombreFichero dado
        fichero=new BufferedWriter(new FileWriter(n.toString2(), true));
    }
  
    //MÉTODOS FUNCIONALES
    
    //MÉTODO escritura QUE LLEVA A CABO LA ESCRITURA DE UNA PALABRA EN EL
    //FICHERO DE TEXTO REPRESENTADO POR EL OBJETO BufferedWriter CORRESPONDIENTE
    public void escritura(Palabra palabra) throws Exception {
            //escritura en el fichero del caracter indice-ésimo de la palabra
            //dada
            fichero.write(palabra.toString2());
        
    }
    
    //MÉTODO escrituraSeparador QUE LLEVA A CABO LA ESCRITURA DEL CÓDIGO
    //DE CARACTER DEL ESPACIO EN BLANCO EN EL FICHERO
    public void escrituraSeparador() throws Exception {
        //escritura del código del espacio en blanco en el fichero
        fichero.write(COD_ESPACIO);
    }
    
    //MÉTODO nuevaLinea QUE LLEVA A CABO LA CREACIÓN DE UNA NUEVA LINEA
    //EN EL FICHERO
    public void nuevaLinea() throws Exception {
        //escritura caracter de control RETURN en el fichero
        fichero.write(RETURN);
        //escritura caracter de control SALTO_LINEA en el fichero
        fichero.write(SALTO_LINEA);
    }
    
    //MÉTODO cerrarEnlaceFichero QUE LLEVA A CABO EL CIERRE DEL ENLACE BufferedWriter
    //con el fichero 
    public void cerrarEnlaceFichero() throws Exception {
        fichero.close();
    }
}
