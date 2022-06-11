package ayed_obligatorio;
import java.util.Date;

public class SistemaObligatorio implements ISistemaObligatorio{
    public ListaContacto contactos;
    public ListaDiccionario diccionario;
    public int MAX_PALABRAS_X_LINEA;

    public ListaContacto getContactos() {
        return contactos;
    }

    public void setContactos(ListaContacto contactos) {
        this.contactos = contactos;
    }

    public ListaDiccionario getDiccionario() {
        return diccionario;
    }

    public void setDiccionario(ListaDiccionario diccionario) {
        this.diccionario = diccionario;
    }

    public int getMAX_PALABRAS_X_LINEA() {
        return MAX_PALABRAS_X_LINEA;
    }

    public void setMAX_PALABRAS_X_LINEA(int MAX_PALABRAS_X_LINEA) {
        this.MAX_PALABRAS_X_LINEA = MAX_PALABRAS_X_LINEA;
    }
    
//    public void prueba(){
//        ListaContacto contacto = new ListaContacto();
//        contacto
//    }
    
    @Override
    public Retorno crearSistemaMensajes(int MAX_PALABRAS_X_LINEA) {
        Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        this.setContactos(new ListaContacto());
        this.setMAX_PALABRAS_X_LINEA(MAX_PALABRAS_X_LINEA);
        this.setDiccionario(new ListaDiccionario());
        
        return ret;
    }

    @Override
    public Retorno destruirSistemaMensajes() {
        Retorno ret = null;
        this.setContactos(null);
        this.setDiccionario(null);        
        
        return ret;
    }

    @Override
    public Retorno agregarContacto(int numero, String nombre) {
        Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if(contactos.buscarelemento(numero) == false){
            contactos.agregarinicio(numero, nombre);
            ret = new Retorno(Retorno.Resultado.OK);
        } else{
            ret = new Retorno(Retorno.Resultado.ERROR);
        }        
        return ret;
    }

    @Override
    public Retorno eliminarContacto(int numero) {
        if(contactos.buscarelemento(numero) == true){
            contactos.borrarElemento(numero);
            return new Retorno(Retorno.Resultado.OK);
        } else{
            return new Retorno(Retorno.Resultado.ERROR);
        }
    }

    @Override
    public Retorno agregarMensaje(int numOrigen, int numDestino, Date fecha) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if(origen != null){
            origen.getLm().agregarfinal(numDestino);
            return new Retorno(Retorno.Resultado.OK);
        } else{
            return new Retorno(Retorno.Resultado.ERROR);
        }
    }

    @Override
    public Retorno eliminarMensaje(int numOrigen, int numMsj) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if(origen.getLm().buscarelemento(numMsj)){
            origen.getLm().borrarElemento(numMsj);
            return new Retorno(Retorno.Resultado.OK);
        } else{
            return new Retorno(Retorno.Resultado.ERROR);
        }        
    }

    @Override
    public Retorno imprimirTexto(int numOrigen, int numMsj) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen); //obtengo el contacto origen
        if(origen != null && origen.getLm().buscarelemento(numMsj)){  //verifico que exista el num msj en la listamsj del origen
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);  //me traigo el nodomsj del nummsj deseado
            ListaLinea ll = msj.getLl();  //me traigo la lista de lineas de ese nummsj
            NodoLinea linea = ll.getPrimero(); //me posiciono en el primer nodo de la lista de linas
            if(linea != null){
                while(linea.siguiente != null){ //recorro la listalinea para acceder a la listapalabra de cada linea
                    ListaPalabra lp = linea.getLp();
                    if(!lp.esVacia()){                    
                        lp.listar();
                    }
                    linea = linea.siguiente;
                }                    
            }
            return new Retorno(Retorno.Resultado.OK);
        } 
        return new Retorno(Retorno.Resultado.ERROR);
    }

    @Override
    public Retorno insertarLinea(int numOrigen, int numMsj) {        
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if(origen != null && origen.getLm().buscarelemento(numMsj)){
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            msj.getLl().agregarfinal(msj.getLl().getUltimo().getDato()+1); //deberia pasar un dato(?? 
            //suponiendo que el dato de la linea lo ubica en la posicion, le envio como dato a la nueva linea la posicion del que estaba ultimo+1
            return new Retorno(Retorno.Resultado.OK);
        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    @Override
    public Retorno insertarLineaPos(int numOrigen, int numMsj, int posLinea) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if(origen != null && origen.getLm().buscarelemento(numMsj)){
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            ll.agregarordenado(posLinea);            
            return new Retorno(Retorno.Resultado.OK); //Si existe el contacto y existe el msj, el agregarordenado de la listalinea se encarga del insertar la linea
        }
        return new Retorno(Retorno.Resultado.ERROR); //Si no existe alguno, entonces no existe la listalinea para insertar
    }

    @Override
    public Retorno borrarLinea(int numOrigen, int numMsj, int posLinea) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if(origen != null && origen.getLm().buscarelemento(numMsj)){
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            if(!ll.esVacia()){                
                if(ll.borrarElemento(posLinea)){
                    return new Retorno(Retorno.Resultado.OK); //Se pudo borrar
                }  
                return new Retorno (Retorno.Resultado.ERROR); // no se borro porque no existe la linea(?
            }   
        }
        return new Retorno(Retorno.Resultado.ERROR); //No existe el contacto
    }

    @Override
    public Retorno borrarTodo(int numOrigen, int numMsj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno borrarOcurrenciasPalabraEnTexto(int numOrigen, int numMsj, String palabraABorrar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno insertarPlabraEnLinea(int numOrigen, int numMsj, int posLinea, int posPalabra, String palabraAIngresar) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if(origen != null && origen.getLm().buscarelemento(numMsj)){
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            if(ll.buscarelemento(posLinea)){
                NodoLinea linea = ll.obtenerPunteroElemento(posLinea);
                linea.getLp().agregarordenado(posPalabra, palabraAIngresar);
                return new Retorno(Retorno.Resultado.OK);
            }
        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    @Override
    public Retorno insertarPalabraYDesplazar(int numOrigen, int numMsj, int posLinea, int posPalabra, String palabraAIngresar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno borrarPalabra(int numOrigen, int numMsj, int posLinea, int posPalabra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno borrarOcurrenciasPalabraEnLinea(int numOrigen, int numMsj, int posLinea, String palabraABorrar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno imprimirLinea(int numOrigen, int numMsj, int posLinea) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno ingresarPalabraDiccionario(String palabraAIngresar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno borrarPalabraDiccionario(String palabraABorrar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno imprimirDiccionario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno imprimirTextoIncorrecto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno cantMensajes(int numOrigen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
