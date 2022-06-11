
package ayed_obligatorio;
import java.util.Date;

public interface ISistemaObligatorio {
    //2.1
    Retorno crearSistemaMensajes(int MAX_PALABRAS_X_LINEA); //
    //2.2
    Retorno destruirSistemaMensajes(); //
    //2.3
    Retorno agregarContacto(int numero, String nombre); //
    //2.4
    Retorno eliminarContacto(int numero); //
    //2.5
    Retorno agregarMensaje(int numOrigen, int numDestino, Date fecha); //
    //2.6
    Retorno eliminarMensaje(int numOrigen, int numMsj); //
    //3.1
    Retorno imprimirTexto(int numOrigen, int numMsj); //
    //3.2
    Retorno insertarLinea(int numOrigen, int numMsj); //
    //3.3
    Retorno insertarLineaPos(int numOrigen, int numMsj, int posLinea); //
    //3.4
    Retorno borrarLinea(int numOrigen, int numMsj, int posLinea); //
    //3.5
    Retorno borrarTodo(int numOrigen, int numMsj);
    //3.6 
    Retorno borrarOcurrenciasPalabraEnTexto(int numOrigen, int numMsj, String palabraABorrar);
    //3.7
    Retorno insertarPlabraEnLinea(int numOrigen, int numMsj, int posLinea, int posPalabra, String palabraAIngresar); //
    //3.8
    Retorno insertarPalabraYDesplazar(int numOrigen, int numMsj, int posLinea, int posPalabra, String palabraAIngresar);
    //3.9
    Retorno borrarPalabra(int numOrigen, int numMsj, int posLinea, int posPalabra);
    //3.10
    Retorno borrarOcurrenciasPalabraEnLinea(int numOrigen, int numMsj, int posLinea, String palabraABorrar);
    //3.11
    Retorno imprimirLinea(int numOrigen, int numMsj, int posLinea);
    //3.12
    Retorno ingresarPalabraDiccionario(String palabraAIngresar);
    //3.13
    Retorno borrarPalabraDiccionario(String palabraABorrar);
    //3.14
    Retorno imprimirDiccionario();
    //3.15
    Retorno imprimirTextoIncorrecto();
    //4.1
    Retorno cantMensajes(int numOrigen);
}
