package ayed_obligatorio;

import java.util.Date;

public class SistemaObligatorio implements ISistemaObligatorio {

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
        Retorno ret = new Retorno(Retorno.Resultado.OK);
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
        if (contactos.buscarelemento(numero) == false) {
            contactos.agregarinicio(numero, nombre);
            ret = new Retorno(Retorno.Resultado.OK);
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        return ret;
    }

    @Override
    public Retorno eliminarContacto(int numero) {
        if (contactos.buscarelemento(numero) == true) {
            contactos.borrarElemento(numero);
            return new Retorno(Retorno.Resultado.OK);
        } else {
            return new Retorno(Retorno.Resultado.ERROR);
        }
    }

    @Override
    public Retorno agregarMensaje(int numOrigen, int numDestino, Date fecha) {
        NodoContacto destino = contactos.obtenerPunteroElemento(numDestino);//puntero del elemento
        if (destino != null) { //si existe este puntero
            destino.getLm().agregarfinal(destino.getLm().cantNodos + 1, fecha); //agrego el numero destino 
            return new Retorno(Retorno.Resultado.OK);
        } else {
            return new Retorno(Retorno.Resultado.ERROR);
        }
    }

    @Override
    public Retorno eliminarMensaje(int numOrigen, int numMsj) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj)) {
            origen.getLm().borrarElemento(numMsj);
            return new Retorno(Retorno.Resultado.OK);
        } else {
            return new Retorno(Retorno.Resultado.ERROR);
        }
    }

    @Override
    public Retorno imprimirTexto(int numOrigen, int numMsj) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen); //obtengo el contacto origen
        if (origen != null && origen.getLm().buscarelemento(numMsj)) {  //verifico que exista el num msj en la listamsj del origen
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);  //me traigo el nodomsj del nummsj deseado
            ListaLinea ll = msj.getLl();  //me traigo la lista de lineas de ese nummsj
            if (ll.esVacia()) {
                System.out.print("Texto vacio \n");
            } else {
                NodoLinea nl = ll.primero;
                for (int i = 0; i < ll.cantNodos; i++) {
                    if (nl != null) {
                        System.out.print(i + 1 + ". ");
                        nl.getLp().listar();
                        System.out.print("\n");
                        nl = nl.siguiente;
                    }
                }
            }
            return new Retorno(Retorno.Resultado.OK);
        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    //Hacerlo con recursividad, encontrar los axiomas
    //Inserta una nueva línea vacía al final del texto
    @Override
    public Retorno insertarLinea(int numOrigen, int numMsj) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj)) {
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);//ver obpunteroele
            ListaLinea ll = msj.getLl();
            NodoLinea nodoPrimero = ll.getPrimero();
            insertar(nodoPrimero, ll);
            return new Retorno(Retorno.Resultado.OK);

        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    public static void insertar(NodoLinea nodo, ListaLinea ll) {
        if (nodo == null) {
            ll.agregarfinal(1);
        } else {
            if (nodo.siguiente == null) {
                ll.agregarfinal(nodo.dato + 1);
            } else {
                insertar(nodo.getSiguiente(), ll);
            }
        }
    }

    @Override
    public Retorno insertarLineaPos(int numOrigen, int numMsj, int posLinea) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj) && origen.getLm().obtenerPunteroElemento(numMsj).getLl().cantNodos+1 >= posLinea) {
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);//ver obpunteroele
            ListaLinea ll = msj.getLl();
            ll.agregarordenado(posLinea);
            return new Retorno(Retorno.Resultado.OK); //Si existe el contacto y existe el msj, el agregarordenado de la listalinea se encarga del insertar la linea
        }
        return new Retorno(Retorno.Resultado.ERROR); //Si no existe alguno, entonces no existe la listalinea para insertar
    }

    @Override
    public Retorno borrarLinea(int numOrigen, int numMsj, int posLinea) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj) && origen.getLm().obtenerPunteroElemento(numMsj).getLl().cantNodos >= posLinea) {
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            if (!ll.esVacia()) {
                if (ll.borrarElemento(posLinea)) {
                    return new Retorno(Retorno.Resultado.OK); //Se pudo borrar
                }
                return new Retorno(Retorno.Resultado.ERROR); // no se borro porque no existe la linea(?
            }
        }
        return new Retorno(Retorno.Resultado.ERROR); //No existe el contacto
    }

    //Se hace con recursividad
    @Override
    public Retorno borrarTodo(int numOrigen, int numMsj) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj)) {
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            remove(ll.getPrimero(), ll);
            return new Retorno(Retorno.Resultado.OK);
        }
        return new Retorno(Retorno.Resultado.ERROR); //No existe el contacto, no existe el nummsj
    }

    public static void remove(NodoLinea nodo, ListaLinea ll) {
        if (nodo == null) {
            return;
        } else {
            remove(nodo.getSiguiente(), ll);
            ll.borrarElemento(nodo.dato);
        }
    }

    @Override
    public Retorno borrarOcurrenciasPalabraEnTexto(int numOrigen, int numMsj, String palabraABorrar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno insertarPlabraEnLinea(int numOrigen, int numMsj, int posLinea, int posPalabra, String palabraAIngresar) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj) && posLinea <= origen.getLm().obtenerPunteroElemento(numMsj).getLl().cantNodos ) {
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            if (ll.obtenerPunteroElemento(posLinea).getLp().cantNodos >= 3) {
                return new Retorno(Retorno.Resultado.ERROR);
            }
            if (ll.buscarelemento(posLinea)) {
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
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj)) {
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            if (ll.buscarelemento(posLinea)) {
                NodoLinea linea = ll.obtenerPunteroElemento(posLinea);
                NodoPalabra nodo = linea.getLp().obtenerPunteroElemento(posPalabra);
                linea.getLp().borrarElemento(nodo.dato);
                return new Retorno(Retorno.Resultado.OK);
            }
        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    @Override
    public Retorno borrarOcurrenciasPalabraEnLinea(int numOrigen, int numMsj, int posLinea, String palabraABorrar) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj)) {
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            if (ll.buscarelemento(posLinea)) {
                NodoLinea linea = ll.obtenerPunteroElemento(posLinea);
                ListaPalabra lp = linea.getLp();
                if (!lp.esVacia()) {
                    NodoPalabra np = lp.getPrimero();
                    NodoPalabra palabra = lp.obtenerPunteroPalabra(palabraABorrar);
                    for (int i = 0; i < lp.cantNodos + 1 && np !=null ; i++) {
                        if (np.getPalabra() == palabraABorrar) {
                            lp.borrarElemento(np.dato);
                        }
                        np = np.getSiguiente();
                    }
                }
                return new Retorno(Retorno.Resultado.OK);
            }
        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    //Imprime la línea con todas sus palabras. Se debe imprimir el número de línea según
    //muestra el ejemplo. La posicionLinea es válida solamente si posicionLinea existe en el texto
    @Override
    public Retorno imprimirLinea(int numOrigen, int numMsj, int posLinea) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen); //obtengo el contacto origen
        if (origen != null && origen.getLm().buscarelemento(numMsj)) {  //verifico que exista el num msj en la listamsj del origen
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);  //me traigo el nodomsj del nummsj deseado
            ListaLinea ll = msj.getLl();  //me traigo la lista de lineas de ese nummsj
            NodoLinea nl = ll.obtenerPunteroElemento(posLinea);
            nl.getLp().listar();
            return new Retorno(Retorno.Resultado.OK);
        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    @Override
    public Retorno ingresarPalabraDiccionario(String palabraAIngresar) {
        if (!diccionario.buscarPalabra(palabraAIngresar)) {
            diccionario.agregarfinal(0, palabraAIngresar);
            return new Retorno(Retorno.Resultado.OK);
        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    @Override
    public Retorno borrarPalabraDiccionario(String palabraABorrar) {
        if (diccionario.buscarPalabra(palabraABorrar)) {
            diccionario.borrarPalabra(palabraABorrar);
            return new Retorno(Retorno.Resultado.OK);
        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    @Override
    public Retorno imprimirDiccionario() {
        if (!diccionario.esVacia()) {
            NodoDiccionario key = diccionario.getPrimero();
            for (int i = 0; i < diccionario.cantNodos; i++) {
                //TODO: Hay que mostralo ordenado
                System.out.print(key.palabra + "\n");
                key = key.getSiguiente();
            }
        } else {
            System.out.print("Diccionario vacio \n");
        }
        return new Retorno(Retorno.Resultado.OK);
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
