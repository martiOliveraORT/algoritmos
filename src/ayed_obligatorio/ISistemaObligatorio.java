package ayed_obligatorio;

import java.util.Date;

public interface ISistemaObligatorio {

    //2.1
    /*
    -Pre-condicion:
        Recibe un entero que sera la cantidad máxima de palabras por línea que acepta el mensaje.
        int MAX_PALABRAS_X_LINEA >=1 
    -Post-condicion:
    Siempre retorna OK.
     */
    Retorno crearSistemaMensajes(int MAX_PALABRAS_X_LINEA);
    
    //2.2
    /*
    -Post-condicion: 
        Siempre retorna OK.
     */
    Retorno destruirSistemaMensajes(); //
    
    //2.3
    /*
    -Pre-condiciones: recibe dos parametros.
        int numero, numero del contacto
        string nomContacto, nombre del contacto
    -Post-condiciones:
        Retorno OK, si pudo agregar el contacto a la lista de contactos.
        Retorno ERROR, si el contacto ya existe.
     */
    Retorno agregarContacto(int numero, String nombre);
    
    //2.4
    /*
    -	Pre-condicion: recibe un parametro entero.
        int numContacto, numero del contacto que queremos eliminar
        int numero > 0
    -Post-condiciones:
        Retorno OK, si pudo eliminar el contacto de la lista de contactos.
        Retorno ERROR, en caso de que no existe un contacto con el número indicado.
     */
    Retorno eliminarContacto(int numero);
    
    //2.5
    /*
    -Pre-condiciones: recibe 3 parametros, 2 enteros y una fecha.
        int numOrigen = 0
        int numDestino, numero del contacto al que quiero agregar el mensaje.
        int numDestino > 0
        Date fecha, será la fecha del mensaje.
    -Post-condiciones:
        Retorno OK, si pudo crear y vincular el mensaje.
        Retorno ERROR, en caso de que el contacto no exista.
     */
    Retorno agregarMensaje(int numOrigen, int numDestino, Date fecha);
    
    //2.6
    /*
    -Pre-condiciones: 2 parametros enteros.
        int numOrigen, numero que identifica al contacto que queremos eliminar el mensaje.
        int numOrigen > 0
        int numMsj, numero del mensaje que queremos eliminar.
        int numMsj > 0
    -Post-condiciones:
        Retorno OK, si pudo eliminar el contacto.
        Retorno ERROR, en caso de que no exista el contacto o el mensaje con los parametros proporcionados. 
     */
    Retorno eliminarMensaje(int numOrigen, int numMsj); 
    
    //3.1
    /*
    -Pre-condiciones: 2 parametros enteros.
        int numOrigen, numero que identifica al contacto a buscar.
        int numOrigen > 0
        int numMsj, numero del mensaje que queremos imprimir.
        int numMsj > 0
    -Post-condiciones: 
        Retorno OK, se muestra el texto con éxito ya sea el mensaje con sus líneas como también cuando el mensaje no contiene líneas, imprimiendo “Texto vacío”.
        Retorno ERROR, no existe contacto con numOrigen o no existe mensaje con numMsj para el contacto indicado.
     */
    Retorno imprimirTexto(int numOrigen, int numMsj);
    
    //3.2
    /*
    -Pre-condiciones: 2 parametros enteros.
        int numOrigen, numero que identifica al contacto a buscar.
        int numOrigen > 0
        int numMsj, numero del mensaje que queremos agregar una línea.
        int numMsj > 0
    -Post-condiciones:
        Retorno OK, se pudo insertar una nueva línea.
        Retorno ERROR, no existe contacto con numOrigen o no existe mensaje con numMsj para el contacto indicado.
     */
    Retorno insertarLinea(int numOrigen, int numMsj);
    
    //3.3
    /*
    -Pre-condiciones: 3 parametros enteros.
        int numOrigen, numero que identifica al contacto a buscar.
	int numOrigen > 0
        int numMsj, numero del mensaje que queremos agregar una línea.
	int numMsj > 0
        int posLinea, posición donde agregaremos la línea.
	posLinea >= 1 y posLinea <= cantidad de líneas actuales +1
    -Post-condiciones:
        Retorno OK, si se pudo insertar la línea con éxito.
        Retorno ERROR, no existe contacto con numOrigen, no existe mensaje con numMsj para el contacto indicado o la posLinea no es válida.
     */
    Retorno insertarLineaPos(int numOrigen, int numMsj, int posLinea);
    
    //3.4
    /* 
    -Pre-condiciones: 3 parametros enteros.
        int numOrigen, numero que identifica al contacto a buscar.
	int numOrigen > 0
        int numMsj, numero del mensaje que queremos borrar una línea.
	int numMsj > 0
        int posLinea, posición de la línea a borrar. La posición es válida solamente si existe en el texto.
	posLinea >= 1 y posLinea <= cantidad de líneas
    -Post-condiciones:
        Retorno OK, se pudo borrar la línea con éxito.
        Retorno ERROR, no existe contacto con numOrigen, no existe mensaje con numMsj para el contacto indicado o la posLinea no es válida.
     */
    Retorno borrarLinea(int numOrigen, int numMsj, int posLinea);
    
    //3.5
    /*
    -Pre-condiciones: 2 parametros enteros.
        int numOrigen, numero que identifica al contacto a buscar.
	int numOrigen > 0
        int numMsj, numero del mensaje que queremos borrar las líneas.
	int numMsj > 0
    -Post-condiciones:
        Retorno OK, se pudo borrar las líneas con éxito.
        Retorno ERROR, no existe contacto con numOrigen o no existe mensaje con numMsj.
     */
    Retorno borrarTodo(int numOrigen, int numMsj);

    //3.6 
    /*
    -Pre-condiciones: 3 parametros, 2 enteros y una string.
        int numOrigen, numero que identifica al contacto a buscar.
	int numOrigen > 0
        int numMsj, numero del mensaje que queremos borrar la ocurrencia.
	int numMsj > 0
        String palabraABorrar, palabra que queremos borrar.
    -Post-condiciones:
        Retorno OK, se pudieron borrar las ocurrencias de la palabra con éxito.
        Retorno ERROR, no existe contacto con numOrigen, no existe mensaje con numMsj para el contacto indicado o si la palabra a borrar no existe.
     */
    Retorno borrarOcurrenciasPalabraEnTexto(int numOrigen, int numMsj, String palabraABorrar);

    //3.7
    /*
    -Pre-condiciones: 5 parametros, 4 enteros y una string.
        int numOrigen, numero que identifica al contacto a buscar.
	int numOrigen > 0
        int numMsj, numero que identifica al mensaje.
	int numMsj > 0
        int posLinea, posición de la línea a ingresar palabra. La posición es válida solamente si existe en el texto.
	posLinea >= 1 y posLinea <= cantidad de líneas 
        int posPalabra, es valida solamente si
	posPalabra >=1 y posPalasbra <= cantidad palabras en la línea +1
        String palabraAIngresar, palabra que queremos ingresar en al línea.
    -Post-condiciones:
        Retorno OK, se pudo insertar la palabra con éxito.
        Retorno ERROR, no existe contacto con numOrigen, no existe mensaje con numMsj para el contacto indicado, la posLinea no es válida, la posPalabra no es válida o si se supera la cantidad máxima de palabras por línea
     */
    Retorno insertarPlabraEnLinea(int numOrigen, int numMsj, int posLinea, int posPalabra, String palabraAIngresar);
    
    //3.8
    /*
    -Pre-condiciones:5 parametros, 4 enteros y una string.
        int numOrigen, numero que identifica al contacto a buscar.
	int numOrigen > 0
        int numMsj, numero que identifica al mensaje.
	int numMsj > 0
        int posLinea, posición de la línea a ingresar palabra. La posición es válida solamente si existe en el texto.
	posLinea >= 1 y posLinea <= cantidad de líneas 
        int posPalabra, es valida solamente si
	posPalabra >=1 y posPalasbra <= cantidad palabras en la línea +1
        String palabraAIngresar, palabra que queremos ingresar en al línea.
    -Post-condiciones:
        Retorno OK, se pudo insertar palabra con éxito.
        Retorno ERROR, no existe contacto con numOrigen, no existe mensaje con numMsj para el contacto indicado, la posLinea no es válida o la posPalabra no es válida.
     */
    Retorno insertarPalabraYDesplazar(int numOrigen, int numMsj, int posLinea, int posPalabra, String palabraAIngresar);

    //3.9
    /*
    -Pre-condiciones: 4 parametros enteros.
        int numOrigen, numero que identifica al contacto a buscar.
	int numOrigen > 0
        int numMsj, numero que identifica al mensaje.
	int numMsj > 0
        int posLinea, es válida solamente si existe en el texto.
        int posPalabra, es válida solamente si éxiste en la línea.  
    -Post-condiciones:
        Retorno OK, se pudo borrar la palabra con éxito.
        Retorno ERROR, no existe contacto con numOrigen, no existe mensaje con numMsj para el contacto indicado, la posLinea no es válida o la posPalabra no es válida.
     */
    Retorno borrarPalabra(int numOrigen, int numMsj, int posLinea, int posPalabra);

    //3.10
    /*
    -Pre-condiciones: 4 parametros, 3 enteros y un string.
        int numOrigen, numero que identifica al contacto a buscar.
	int numOrigen > 0
        int numMsj, numero que identifica al mensaje.
	int numMsj > 0
        int posLinea, es válida solamente si existe en el texto.
        String palabraABorrar, palabra que queremos borrar.
    -Post-condiciones:
        Retorno OK, se pudieron borrar las ocurrencias de la palabra con éxito.
        Retorno ERROR, no existe contacto con numOrigen, no existe mensaje con numMsj para el contacto indicado o la posLinea no es válida.
     */
    Retorno borrarOcurrenciasPalabraEnLinea(int numOrigen, int numMsj, int posLinea, String palabraABorrar);

    //3.11
    /*
    -Pre-condiciones: 3 parametros enteros.
        int numOrigen, numero que identifica al contacto a buscar.
	int numOrigen > 0
        int numMsj, numero que identifica al mensaje.
	int numMsj > 0
        int posLinea, es válida solamente si existe en el texto.
    -Post-condiciones:
        Retorno OK, se mostró la línea.
        Retorno ERROR, no existe contacto con numOrigen, no existe mensaje con numMsj para el contacto indicado o la posLinea no es válida.
     */
    Retorno imprimirLinea(int numOrigen, int numMsj, int posLinea);

    //3.12
    /*
    -Pre-condiciones: 1 parametro string.
        String palabraAIngresar, palabra que queremos ingresar al diccionario.
    -Post-condiciones:
        Retorno OK, se ingresó correctamente.
        Retorno ERROR, la palabra ya existe.
     */
    Retorno ingresarPalabraDiccionario(String palabraAIngresar);

    //3.13
    /*
    -Pre-condiciones: 1 parametro string.
        String palabraABorrar, palabra que queremos borrar del diccionario.
    -Post-condiciones:
        Retorno OK, se borró correctamente.
        Retorno ERROR, la palabra no existe en el diccionario.
     */
    Retorno borrarPalabraDiccionario(String palabraABorrar);

    //3.14
    /*
    -Post-condiciones:
        Retorno OK, se mostró el diccionario correctamente o en su defecto el mensaje “Diccionario vacío”
     */
    Retorno imprimirDiccionario();

    //3.15
    /*
    -Post-condiciones:
        Retorno OK, se muestra el texto.
     */
    Retorno imprimirTextoIncorrecto();

    //4.1
    /*
    -Pre-condiciones: 1 parametro entero.
        int numOrigen, numero que identifica al contacto a buscar.
	numOrigen != 0 (ya que 0 soy yo mismo)
    -Post-condiciones:
        Retorno OK, se muestra el texto con los mensajes enviados por día.
        Retorno ERROR, en caso de que el contacto no exista.
     */
    Retorno cantMensajes(int numOrigen);
}
