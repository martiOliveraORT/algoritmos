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
        p.ver(so.crearSistemaMensajes(3).resultado, Retorno.Resultado.OK, "Se crea sistema para 3 palabras por linea");
        p.ver(so.agregarContacto(1, "Carlos Perez").resultado, Retorno.Resultado.OK, "Se agrega contacto Carlos Perez al sistema");
        p.ver(so.agregarContacto(1, "Carlos Perez").resultado, Retorno.Resultado.ERROR, "Se intenta agregar contacto Carlos Perez que ya existe");
        p.ver(so.eliminarContacto(1).resultado, Retorno.Resultado.OK, "Se elimina contacto carlos perez del sistema");
        
        p.ver(so.agregarContacto(0, "Yo").resultado, Retorno.Resultado.OK, "se agrega contacto Juan Perez al sistema");
        p.ver(so.agregarContacto(1, "Juan").resultado, Retorno.Resultado.OK, "se agrega contacto Juan al sistema");
        p.ver(so.agregarContacto(2, "Pedro").resultado, Retorno.Resultado.OK, "se agrega contacto Pedro al sistema");
        p.ver(so.agregarContacto(3, "Ana").resultado, Retorno.Resultado.OK, "se agrega contacto Ana al sistema");
        p.ver(so.agregarContacto(4, "Maria").resultado, Retorno.Resultado.OK, "se agrega contacto Maria al sistema");
        
        //Mostramos contactos
        so.getContactos().listar();
        
        // definimos una fecha        
        Date fecha=new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fecha=formato.parse("23/03/2022");
        } catch (ParseException ex) {
            Logger.getLogger(AyED_Obligatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Agregamos 3 mensajes a contacto Pedro que Existe (int numOrigen, int numDestino, Date fecha)
        p.ver(so.agregarMensaje(0,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega primer mensaje para Pedro ");
        p.ver(so.agregarMensaje(0,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega segundo mensaje para Pedro ");
        p.ver(so.agregarMensaje(0,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega tercer  mensaje para Pedro ");
        
        // Agregamos 3 mensajes a contacto 8 que NO Existe
        p.ver(so.agregarMensaje(0,2, fecha).resultado, Retorno.Resultado.ERROR, "Se agrega mensaje a contacto inexistente ");
        
        // Agregamos lineas al mensaje 2 de pedro -> (int numOrigen, int numMsj, int posLinea)
        p.ver(so.insertarLineaPos(3, 2, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 2 de Pedro");
        p.ver(so.insertarLineaPos(3, 2, 2).resultado, Retorno.Resultado.OK, "Se agrega linea 2 en blanco al mensaje 2 de Pedro");
        p.ver(so.insertarLineaPos(3, 2, 3).resultado, Retorno.Resultado.OK, "Se agrega linea 3 en blanco al mensaje 2 de Pedro");
        /*
        // Agregamos palabras al mensaje 2 de pedro en la linea 1
        p.ver(so.insertarPlabraEnLinea(2, 2,1,1,"Hola").resultado, Retorno.Resultado.OK,"Se agrega palabra Hola linea 1 mensaje 2");
        p.ver(so.insertarPlabraEnLinea(2, 2,1,2,"Pedro").resultado, Retorno.Resultado.OK,"Se agrega palabra Pedro linea 1 mensaje 2");
 
        p.ver(so.insertarPlabraEnLinea(2, 2,2,1,"Te").resultado, Retorno.Resultado.OK,"Se agrega palabra Te linea 2 mensaje 2");
        p.ver(so.insertarPlabraEnLinea(2, 2,2,2,"LLamo").resultado, Retorno.Resultado.OK,"Se agrega palabra Te linea 2 mensaje 2");
        p.ver(so.insertarPlabraEnLinea(2, 2,2,3,"En").resultado, Retorno.Resultado.OK,"Se agrega palabra Llamo linea 2 mensaje 2");
        p.ver(so.insertarPlabraEnLinea(2, 2,3,1,"5min").resultado, Retorno.Resultado.OK,"Se agrega palabra 5min linea 3 mensaje 2");
        */
        // Imprimimos mensaje 2 de pedro
        p.ver(so.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro");
    }
}
