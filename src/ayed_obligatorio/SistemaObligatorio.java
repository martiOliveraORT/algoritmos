package ayed_obligatorio;

import java.util.Date;
import java.util.List;

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
            NodoLinea nuevo = new NodoLinea(1);
            ll.setPrimero(nuevo);
            ll.setUltimo(nuevo);
            ll.cantNodos++;
        } else {
            if (nodo.siguiente == null) {
                NodoLinea nuevo = new NodoLinea(nodo.dato + 1);
                ll.ultimo.setSiguiente(nuevo);
                ll.setUltimo(nuevo);
                ll.cantNodos++;
            } else {
                insertar(nodo.getSiguiente(), ll);
            }
        }
    }

    @Override
    public Retorno insertarLineaPos(int numOrigen, int numMsj, int posLinea) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj) && origen.getLm().obtenerPunteroElemento(numMsj).getLl().cantNodos + 1 >= posLinea) {
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
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj)) {
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            if (!ll.esVacia()) {
                ocurrencia(ll.getPrimero(), ll, palabraABorrar);

                return new Retorno(Retorno.Resultado.OK);
            }
        }
        return new Retorno(Retorno.Resultado.ERROR);
    }

    public static void ocurrencia(NodoLinea nodo, ListaLinea ll, String palabra) {
        if (nodo.siguiente == null) {
            ListaPalabra lp = nodo.getLp();
            if (!lp.esVacia()) {
                NodoPalabra np = lp.getPrimero();
                NodoPalabra p = lp.obtenerPunteroPalabra(palabra);
                for (int i = 0; i < lp.cantNodos + 1 && np != null; i++) {
                    if (np.getPalabra() == palabra) {
                        lp.borrarElemento(np.dato);
                    }
                    np = np.getSiguiente();
                }
            }
        } else {
            NodoLinea nd = nodo.siguiente;
            ocurrencia(nd, ll, palabra);
            ListaPalabra lp = nodo.getLp();
            if (!lp.esVacia()) {
                NodoPalabra np = lp.getPrimero();
                NodoPalabra p = lp.obtenerPunteroPalabra(palabra);
                for (int i = 0; i < lp.cantNodos + 1 && np != null; i++) {
                    if (np.getPalabra() == palabra) {
                        lp.borrarElemento(np.dato);
                    }
                    np = np.getSiguiente();
                }
            }
        }
    }

    @Override
    public Retorno insertarPlabraEnLinea(int numOrigen, int numMsj, int posLinea, int posPalabra, String palabraAIngresar) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj) && posLinea <= origen.getLm().obtenerPunteroElemento(numMsj).getLl().cantNodos) {
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
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen);
        if (origen != null && origen.getLm().buscarelemento(numMsj) && posLinea <= origen.getLm().obtenerPunteroElemento(numMsj).getLl().cantNodos) {
            NodoMensaje msj = origen.getLm().obtenerPunteroElemento(numMsj);
            ListaLinea ll = msj.getLl();
            NodoLinea linea = ll.obtenerPunteroElemento(posLinea);
            if (ll.buscarelemento(posLinea)) {
                if (linea.getLp().cantNodos < 3) {
                    linea.getLp().agregarordenado(posPalabra, palabraAIngresar);
                    return new Retorno(Retorno.Resultado.OK);
                } else {
                    ListaPalabra lp = linea.getLp();
                    NodoPalabra ultima = lp.getUltimo();
                    return insertarPalabraYDesplazar(numOrigen, numMsj, posLinea + 1, 1, ultima.palabra);
                }
            }
        }
        return new Retorno(Retorno.Resultado.ERROR);
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
                    for (int i = 0; i < lp.cantNodos + 1 && np != null; i++) {

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
        NodoContacto aux = contactos.getPrimero();
        for (int i = 1; i < contactos.cantNodos || aux == null; i++) {
            System.out.print(aux.getNombre() + "\n");
            if (aux.getLm().esVacia()) {
                System.out.print("No tiene mensajes \n");
            } else {
                ListaMensaje listaM = aux.getLm();
                NodoMensaje nodoM = listaM.getPrimero();
                while (nodoM != null) {

                    mensajeTextoIncorrecto(nodoM);

                    nodoM = nodoM.getSiguiente(); //Paso al siguiente mensaje
                }
            }
            aux = aux.getSiguiente();//paso al siguiente contacto
        }
        return new Retorno(Retorno.Resultado.OK);
    }

    public void mensajeTextoIncorrecto(NodoMensaje n) {
        ListaLinea listaL = n.getLl();  //me traigo la lista de lineas de ese nummsj
        if (listaL.esVacia()) {
            System.out.print("Texto vacio \n");
        } else {
            NodoLinea nl = listaL.primero;
            int i = 1;
            while (nl != null) {
                if (nl != null) {
                    ListaPalabra lp = existeEnDiccionario(nl.getLp());
                    System.out.print(i + ": ");
                    lp.listar();
                    System.out.print("\n");
                    i++;
                }
                nl = nl.siguiente;
            }
        }
    }

    public ListaPalabra existeEnDiccionario(ListaPalabra listaP) {
        NodoPalabra nodoP = listaP.getPrimero();
        ListaPalabra listaPNueva = new ListaPalabra(3);
        if (nodoP != null) {
            for (int i = 0; i < listaP.cantNodos || nodoP != null; i++) {
                if (!diccionario.buscarPalabra(nodoP.getPalabra())) {
                    listaPNueva.agregarinicio(nodoP.getDato(), nodoP.getPalabra());
                }
                nodoP = nodoP.getSiguiente();
            }
        }
        return listaPNueva;
    }

    @Override
    public Retorno cantMensajes(int numOrigen) {
        NodoContacto origen = contactos.obtenerPunteroElemento(numOrigen); //obtengo el contacto origen
        if (origen != null) {
            ListaMensaje lm = origen.getLm();
            if (!lm.esVacia()) {
                FechaCantidad[] listaNueva;
                listaNueva = new FechaCantidad[lm.cantNodos];
                int k = 0;
                NodoMensaje nodoMensaje = lm.getPrimero();
                for (int i = 0; i < lm.cantNodos; i++) {
                    FechaCantidad nuevo = new FechaCantidad(nodoMensaje.getFecha(), lm.cantidadMensajesPorFecha(nodoMensaje.getFecha()));
                    if (!existeMensaje(listaNueva, nuevo)) {
                        listaNueva[k] = nuevo;
                        k++;
                    }
                    nodoMensaje = nodoMensaje.getSiguiente();
                }
                mostrarCantidad(listaNueva, origen.getNombre());
            } else {
                System.out.print("No tiene mensajes");
            }
            return new Retorno(Retorno.Resultado.OK);
        }

        return new Retorno(Retorno.Resultado.ERROR);
    }

    public static boolean existeMensaje(FechaCantidad[] l, FechaCantidad n) {
        for (int i = 0; i < l.length; i++) {
            if (l[i] != null && l[i].fecha == n.fecha) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarCantidad(FechaCantidad[] l, String nombre) {

        for (int i = 0; i < l.length && l[i] != null; i++) {
            Date fecha = l[i].fecha;
            System.out.print("\t" + fecha.getDate() + "-" + fecha.getMonth() + " | ");
        }
        System.out.println("\n");
        for (int i = 0; i < l.length && l[i] != null; i++) {
            if (i == 0) {
                System.out.print(nombre + "\t");
            }
            System.out.print(" " + l[i].cantidad + "   | ");
        }
        System.out.println("\n");

    }

}
