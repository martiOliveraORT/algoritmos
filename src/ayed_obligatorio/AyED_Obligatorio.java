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
    
    public static void juegodeprueba1(SistemaObligatorio so, Prueba p){
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
        Date fecha=new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fecha=formato.parse("23/03/2022");
        } catch (ParseException ex) {
            Logger.getLogger(AyED_Obligatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    //2.5    
    System.out.println("##################################################################################2.5");
        //Resultado OK - Agregamos 3 mensajes a contacto Pedro que Existe 
        //Parametros (int numOrigen, int numDestino, Date fecha)
        p.ver(so.agregarMensaje(0,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega primer mensaje para Pedro ");
        p.ver(so.agregarMensaje(0,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega segundo mensaje para Pedro ");
        p.ver(so.agregarMensaje(0,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega tercer  mensaje para Pedro ");
        
        //Resultado ERROR - Agregamos  mensaje a contacto 8 que NO Existe
        p.ver(so.agregarMensaje(0,8, fecha).resultado, Retorno.Resultado.ERROR, "Se agrega mensaje a contacto inexistente ");
        
    //2.6
    System.out.println("##################################################################################2.6");
        //Resultado OK - Eliminar mensaje 3 de pedro
        //Parametros (int numOrigen, int numMsj) 
        p.ver(so.eliminarMensaje(2,3).resultado, Retorno.Resultado.OK, "Se elimina mensaje 3 de Pedro ");
        
        //Resultado ERROR - Eliminar mensaje a contacto que no existe. Eliminar linea que no existe
        p.ver(so.eliminarMensaje(8,2).resultado, Retorno.Resultado.ERROR, "Se elimina mensaje a contacto no existente");
        p.ver(so.eliminarMensaje(2,5).resultado, Retorno.Resultado.ERROR, "Se elimina mensaje que no existe");
        
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
        //Resultado OK - Insertar linea vacia al final (forma recursiva)
        //Parametros (int numOrigen, int numMsj)
        p.ver(so.insertarLinea(2, 2).resultado, Retorno.Resultado.OK, "Se agrega linea vacia al final del mensaje nro 2 de Pedro");
        
        p.ver(so.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro");
        
        //TODO: -REVISAR porque no muestra las lineas como el ejemplo 3.1
        //      -Agregar los casos de error
        
    //3.3
    System.out.println("##################################################################################3.3");
        //Resultado OK - Insertar Linea en Posicion
        //Parametros (int numContactoOrigen, int numMensaje, int posicionLinea)
        p.ver(so.insertarLineaPos(2, 2, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 2 de Pedro");
        
        //Resultado ERROR - Insertar linea en posicion contacto no existe. En mensaje que no existe y en posicion no valida
        p.ver(so.insertarLineaPos(8, 2, 1).resultado, Retorno.Resultado.OK, "No se puede agregar linea, no existe contacto");
        p.ver(so.insertarLineaPos(2, 8, 1).resultado, Retorno.Resultado.OK, "No se puede agregar linea, no existe mensaje");
        p.ver(so.insertarLineaPos(2, 2, 8).resultado, Retorno.Resultado.OK, "No se puede agregar linea, no existe linea");
        
    //3.4
    System.out.println("##################################################################################3.4");
        //Resultado OK - Borrar linea
    }
}
