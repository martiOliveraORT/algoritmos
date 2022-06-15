package ayed_obligatorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AyED_Obligatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SistemaObligatorio so = new SistemaObligatorio();
        Prueba p = new Prueba();
        juegodeprueba1(so, p);
    }

    public static void juegodeprueba1(SistemaObligatorio so, Prueba p) {
        //2.1
        System.out.println("##################################################################################2.1");;
        p.ver(so.crearSistemaMensajes(3).resultado, Retorno.Resultado.OK, "Se crea sistema para 3 palabras por linea");
        //Resultado OK - Agregamos contactos
        //Parametros(int numero, String nombre)
        //2.3
        System.out.println("##################################################################################2.3");
        p.ver(so.agregarContacto(8, "Carlos Perez").resultado, Retorno.Resultado.OK, "Se agrega contacto Carlos Perez al sistema");

        //Resultado ERROR - Error al agregar contacto
        p.ver(so.agregarContacto(8, "Carlos Perez").resultado, Retorno.Resultado.ERROR, "Se intenta agregar contacto Carlos Perez que ya existe");

        //Resultado OK - Agregamos contactos correctos al sistema (agregamos al inicio)
        p.ver(so.agregarContacto(0, "Yo").resultado, Retorno.Resultado.OK, "se agrega contacto Yo al sistema");
        p.ver(so.agregarContacto(1, "Juan").resultado, Retorno.Resultado.OK, "se agrega contacto Juan al sistema");
        p.ver(so.agregarContacto(2, "Pedro").resultado, Retorno.Resultado.OK, "se agrega contacto Pedro al sistema");
        p.ver(so.agregarContacto(3, "Ana").resultado, Retorno.Resultado.OK, "se agrega contacto Ana al sistema");
        p.ver(so.agregarContacto(4, "Maria").resultado, Retorno.Resultado.OK, "se agrega contacto Maria al sistema");
        //2.4  
        System.out.println("##################################################################################2.4");
        //Resultado OK - Eliminamos a Carlos Perez
        p.ver(so.eliminarContacto(8).resultado, Retorno.Resultado.OK, "Se elimina contacto carlos perez del sistema");

        //Resultado ERROR - Eliminamos a Carlos Perez que ya no existe
        p.ver(so.eliminarContacto(8).resultado, Retorno.Resultado.ERROR, "Se intenta eliminar contacto carlos perez del sistema pero no existe");

        //Mostramos contactos
        System.out.println("Contactos Actuales");
        so.getContactos().listar();

        // definimos una fecha        
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fecha = formato.parse("23/03/2022");
        } catch (ParseException ex) {
            Logger.getLogger(AyED_Obligatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        //2.5    
        System.out.println("##################################################################################2.5");
        //Resultado OK - Agregamos 3 mensajes a contacto Pedro que Existe 
        //Parametros (int numOrigen, int numDestino, Date fecha)
        p.ver(so.agregarMensaje(0, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega primer mensaje para Pedro ");
        p.ver(so.agregarMensaje(0, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega segundo mensaje para Pedro ");
        p.ver(so.agregarMensaje(0, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega tercer  mensaje para Pedro ");

        //Resultado ERROR - Agregamos  mensaje a contacto 8 que NO Existe
        p.ver(so.agregarMensaje(0, 8, fecha).resultado, Retorno.Resultado.ERROR, "Se agrega mensaje a contacto inexistente ");

        //2.6
        System.out.println("##################################################################################2.6");
        //Resultado OK - Eliminar mensaje 3 de pedro
        //Parametros (int numOrigen, int numMsj) 
        p.ver(so.eliminarMensaje(2, 3).resultado, Retorno.Resultado.OK, "Se elimina mensaje 3 de Pedro ");

        //Resultado ERROR - Eliminar mensaje a contacto que no existe. Eliminar linea que no existe
        p.ver(so.eliminarMensaje(8, 2).resultado, Retorno.Resultado.ERROR, "Se elimina mensaje a contacto no existente");
        p.ver(so.eliminarMensaje(2, 5).resultado, Retorno.Resultado.ERROR, "Se elimina mensaje que no existe");

        //3.1
        System.out.println("##################################################################################3.1");
        //Resultado OK - Imprimir mensaje 2 de pedro
        //Parametros (int numOrigen, int numMsj)
        p.ver(so.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro");

        //Resultado ERROR - No existe el contacto 8. No existe el numero de mensaje
        p.ver(so.imprimirTexto(8, 2).resultado, Retorno.Resultado.ERROR, "No se imprime el texto ya que no existe contacto");
        p.ver(so.imprimirTexto(2, 8).resultado, Retorno.Resultado.ERROR, "No se imprime el texto ya que no existe el mensaje");

        //3.2
        System.out.println("##################################################################################3.2");
        System.out.print("Vemos mensajes de pedro sin lineas \n");
        so.imprimirTexto(2, 2);
        //Resultado OK - Insertar linea vacia al final (forma recursiva)
        //Parametros (int numOrigen, int numMsj)
        p.ver(so.insertarLinea(2, 2).resultado, Retorno.Resultado.OK, "Se agrega linea vacia al final del mensaje nro 2 de Pedro");

        //Resultado ERROR - Insertar linea en contacto que no existe, en mensaje que no existe
        p.ver(so.insertarLinea(8, 2).resultado, Retorno.Resultado.ERROR, "No se puede agregar linea, no existe contacto");
        p.ver(so.insertarLinea(2, 5).resultado, Retorno.Resultado.ERROR, "No se puede agregar linea, no existe mensaje");

        System.out.print("Vemos mensajes de pedro con una linea \n");
        so.imprimirTexto(2, 2);

        //3.3
        System.out.println("##################################################################################3.3");
        //Resultado OK - Insertar Linea en Posicion
        //Parametros (int numContactoOrigen, int numMensaje, int posicionLinea)
        p.ver(so.insertarLineaPos(2, 2, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 2 de Pedro");

        //Resultado ERROR - Insertar linea en posicion contacto no existe. En mensaje que no existe y en posicion no valida
        p.ver(so.insertarLineaPos(8, 2, 1).resultado, Retorno.Resultado.ERROR, "No se puede agregar linea, no existe contacto");
        p.ver(so.insertarLineaPos(2, 8, 1).resultado, Retorno.Resultado.ERROR, "No se puede agregar linea, no existe mensaje");
        p.ver(so.insertarLineaPos(2, 2, 8).resultado, Retorno.Resultado.ERROR, "No se puede agregar linea, no existe linea");

        //Resultado OK - Agregamos lineas al mensaje 2 de Pedro
        p.ver(so.insertarLineaPos(2, 2, 2).resultado, Retorno.Resultado.OK, "Se agrega linea 2 en blanco al mensaje 2 de Pedro");
        p.ver(so.insertarLineaPos(2, 2, 3).resultado, Retorno.Resultado.OK, "Se agrega linea 3 en blanco al mensaje 2 de Pedro");
        p.ver(so.insertarLineaPos(2, 2, 4).resultado, Retorno.Resultado.OK, "Se agrega linea 4 en blanco al mensaje 2 de Pedro");
        p.ver(so.insertarLineaPos(2, 1, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 1 de Pedro");
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);

        System.out.print("Mensaje 2 de pedro \n");
        so.imprimirTexto(2, 2);
        //3.4
        System.out.println("##################################################################################3.4");
        //Resultado OK - Borrar linea en la posicion indicada 
        //Parametros(int numOrigen, int numMsj, int posLinea)
        p.ver(so.borrarLinea(2, 2, 4).resultado, Retorno.Resultado.OK, "Borramos linea 4 de mensaje 2 de Pedro");

        //Resultado ERROR - Borrar linea en posicion contacto no existe, el mensaje no existe y posicion no valida
        p.ver(so.borrarLinea(8, 2, 3).resultado, Retorno.Resultado.ERROR, "No se puede borrar linea, no existe contacto");
        p.ver(so.borrarLinea(2, 8, 3).resultado, Retorno.Resultado.ERROR, "No se puede borrar linea, no existe mensaje");
        p.ver(so.borrarLinea(2, 2, 8).resultado, Retorno.Resultado.ERROR, "No se puede borrar linea, no existe posicion");

        //3.5
        System.out.println("##################################################################################3.5");
        //Resultado OK - Borrar todas las lineas del mensaje (forma recursiva)
        //Parametros (int numOrigen, int numMsj)

        //Mostramos mensaje 1 de Pedro con una linea vacia
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);
        p.ver(so.borrarTodo(2, 1).resultado, Retorno.Resultado.OK, "Borramos todas las lineas mensaje 1 de Pedro");
        //Mostramos mensaje 1 de Pedro sin lineas
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);

        //Resultado ERROR - Borrar todas las lineas con contacto que no existe, mensaje que no existe
        p.ver(so.borrarTodo(8, 1).resultado, Retorno.Resultado.ERROR, "No se puede borrar las lineas, no existe contacto");
        p.ver(so.borrarTodo(2, 6).resultado, Retorno.Resultado.ERROR, "No se puede borrar las lineas, no existe mensaje");

        //3.7
        System.out.println("##################################################################################3.7");
        //Resultado OK - Insertar Palabra en Linea 
        //Parametros (int numOrigen, int numMsj, int posLinea, int posPalabra, String palabraAIngresar)
        //Mostramos mensaje 2 de Pedro, sin palabras
        System.out.print("Mensaje 2 de pedro \n");
        so.imprimirTexto(2, 2);
        p.ver(so.insertarPlabraEnLinea(2, 2, 1, 1, "Hola").resultado, Retorno.Resultado.OK, "Agregamos palabra en 1 linea, mensaje 2 de Pedro");
        p.ver(so.insertarPlabraEnLinea(2, 2, 1, 1, "como").resultado, Retorno.Resultado.OK, "Agregamos palabra en 1 linea, mensaje 2 de Pedro");
        p.ver(so.insertarPlabraEnLinea(2, 2, 1, 1, "estas?").resultado, Retorno.Resultado.OK, "Agregamos palabra en 1 linea, mensaje 2 de Pedro");
        p.ver(so.insertarPlabraEnLinea(2, 2, 2, 1, "Prueba").resultado, Retorno.Resultado.OK, "Agregamos palabra en 1 linea, mensaje 2 de Pedro");
        //Mostramos mensaje 2 de Pedro con palabra agregada
        System.out.print("Mensaje 2 de pedro \n");
        so.imprimirTexto(2, 2);

        //Resultado ERROR - Insertar Palabra en linea con contacto que no existe, no existe el mensaje, linea no valida, maximo 3 palabras por linea
        p.ver(so.insertarPlabraEnLinea(8, 2, 1, 1, "Hola").resultado, Retorno.Resultado.ERROR, "No se puede agregar palabra, no existe contacto");
        p.ver(so.insertarPlabraEnLinea(2, 8, 1, 1, "Hola").resultado, Retorno.Resultado.ERROR, "No se puede agregar palabra, no existe mensaje");
        p.ver(so.insertarPlabraEnLinea(2, 2, 8, 1, "Hola").resultado, Retorno.Resultado.ERROR, "No se puede agregar palabra, no existe linea");
        p.ver(so.insertarPlabraEnLinea(2, 2, 1, 1, "prueba").resultado, Retorno.Resultado.ERROR, "No se puede agregar palabra, maximo 3 por linea");

    
        //3.6
        System.out.println("##################################################################################3.6");  
        so.insertarPlabraEnLinea(2, 2, 2, 1, "Prueba");
        so.insertarPlabraEnLinea(2, 2, 2, 2, "Prueba");
        so.imprimirTexto(2, 2);
        //Resultado OK - Si se pudieron borrar las ocurrencias de la palabra con éxito.
        //Parametros (int numOrigen, int numMsj, String palabraABorrar)
        p.ver(so.borrarOcurrenciasPalabraEnTexto(2, 2, "Prueba").resultado, Retorno.Resultado.OK, "Se borro la palabra");
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 2);
        
        //3.8
        System.out.println("##################################################################################3.8");
        System.out.print("\n");
        System.out.print("Ingresar pruebas de insertarPalabraYDesplazar");
        System.out.print("\n");
        System.out.print("\n");
        
        //3.9
        System.out.println("##################################################################################3.9");
        //Resultado OK - Borrar palabra en posicion indicada
        //Parametros (int numOrigen, int numMsj, int posLinea, int posPalabra)
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);
        so.insertarLinea(2, 1);
        so.insertarPlabraEnLinea(2, 1, 1, 1, "Prueba");
        so.insertarPlabraEnLinea(2, 1, 1, 2, "Prueba");
        so.insertarPlabraEnLinea(2, 1, 1, 0, "Trampa");
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);
        p.ver(so.borrarPalabra(2, 1, 1, 1).resultado, Retorno.Resultado.OK, "Borrar palabra en posicion");
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);
        
        //Resultado ERROR - Borrar palabra en posicion indicada con contacto que no existe, numero de mensaje no existe, linea no valida, posicion palabra no valida
        p.ver(so.borrarPalabra(8, 1, 0, 1).resultado, Retorno.Resultado.ERROR, "No se puede borrar palabra, no existe contacto");
        p.ver(so.borrarPalabra(2, 8, 0, 1).resultado, Retorno.Resultado.ERROR, "No se puede borrar palabra, no existe mensaje");
        p.ver(so.borrarPalabra(2, 1, 8, 1).resultado, Retorno.Resultado.ERROR, "No se puede borrar palabra, no existe posicion linea");
        p.ver(so.borrarPalabra(2, 1, 0, 8).resultado, Retorno.Resultado.ERROR, "No se puede borrar palabra, no existe posicion palabra");
        
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);
        
        //3.10
        System.out.println("##################################################################################3.10");
        //Resultado OK - Borrar todas las ocurrencias de la palabra
        //Parametros (int numOrigen, int numMsj, int posLinea, String palabraABorrar)
        so.insertarPlabraEnLinea(2, 1, 1, 0, "Trampa");
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);
        p.ver(so.borrarOcurrenciasPalabraEnLinea(2, 1, 1, "Trampa").resultado, Retorno.Resultado.OK, "Se borra palabra repetida 'Trampa'");
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);
        
        //Resultado ERROR - Borrar todas las ocurrencias de la palabra con contacto que no existe, mensaje que no existe, linea no valida
        p.ver(so.borrarOcurrenciasPalabraEnLinea(8, 1, 1, "Prueba").resultado, Retorno.Resultado.ERROR, "No se puede borrar palabra, no existe contacto");
        p.ver(so.borrarOcurrenciasPalabraEnLinea(2, 8, 1, "Prueba").resultado, Retorno.Resultado.ERROR, "No se puede borrar palabra, no existe mensaje");
        p.ver(so.borrarOcurrenciasPalabraEnLinea(2, 1, 8, "Prueba").resultado, Retorno.Resultado.ERROR, "No se puede borrar palabra, no existe linea");
        
        System.out.print("Mensaje 1 de pedro \n");
        so.imprimirTexto(2, 1);
        
        //3.11
        System.out.println("##################################################################################3.11");
        //Resultado OK - Imprimir linea de mensaje
        //Parametros (int numOrigen, int numMsj, int posLinea)
        p.ver(so.imprimirLinea(2, 2, 1).resultado, Retorno.Resultado.OK, "Se imprime linea 1, mensaje 2 de Pedro");
        so.imprimirLinea(2, 2, 1);
        System.out.println("\n");
        //Resultado ERROR - Imprimir linea de mensajecon contacto que no existe, mensaje que no existe, linea no valida
        p.ver(so.imprimirLinea(8, 2, 1).resultado, Retorno.Resultado.ERROR, "No se puede imprimir linea, no existe contacto");
        p.ver(so.imprimirLinea(2, 8, 1).resultado, Retorno.Resultado.ERROR, "No se puede imprimir linea, no existe mensaje");
        p.ver(so.imprimirLinea(8, 2, 8).resultado, Retorno.Resultado.ERROR, "No se puede imprimir linea, no existe linea");
        
        //3.12
        System.out.println("##################################################################################3.12");
        //Resultado OK - Ingresar Palabra Diccionario
        //Parametro (String palabraAIngresar)
        p.ver(so.ingresarPalabraDiccionario("Prueba").resultado, Retorno.Resultado.OK, "Se ingreso palabra Prueba a diccionario");
        
        //Resultado ERROR - Ingresar palabra a diccionario con palabra ya existente
        p.ver(so.ingresarPalabraDiccionario("Prueba").resultado, Retorno.Resultado.ERROR, "Ya existe esa palabra en el diccionario");
        
        //3.13
        System.out.println("##################################################################################3.13");
        //Resultado OK - Borrar palabra diccionario
        //Parametro (String palabraAIngresar)
        p.ver(so.borrarPalabraDiccionario("Prueba").resultado, Retorno.Resultado.OK, "Se borro palabra Prueba de diccionario");
        
        //Resultado ERROR - Borrar palabra a diccionario que no existe
        p.ver(so.borrarPalabraDiccionario("Prueba").resultado, Retorno.Resultado.ERROR, "No existe esta palabra para borrar diccionario");
        
        //3.14
        System.out.println("##################################################################################3.14");
        System.out.print("\n");
        System.out.print("Ingresar pruebas de imprimirDiccionario");
        System.out.print("\n");
        System.out.print("\n");
        
        //3.15
        System.out.println("##################################################################################3.15");
        System.out.print("\n");
        System.out.print("Ingresar pruebas de ImprimirTextoIncorrecto");
        System.out.print("\n");
        System.out.print("\n");
        
        //4.1
        System.out.println("##################################################################################4.1");
        System.out.print("\n");
        System.out.print("Ingresar pruebas de cantidadDeMensajes");
        System.out.print("\n");
        System.out.print("\n");
    }
    
}
