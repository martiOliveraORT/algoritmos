
package ayed_obligatorio;

import java.util.Date;

public class NodoMensaje extends Nodo{
    ListaLinea ll;
    //TODO: Falta fecha?
    Date fecha;
    NodoMensaje siguiente;
    

    public NodoMensaje(int nromsje) {
        super(nromsje);
        this.ll = new ListaLinea();
        this.fecha = null;
        this.siguiente = null;
    }

    public ListaLinea getLl() {
        return ll;
    }

    public void setLl(ListaLinea ll) {
        this.ll = ll;
    }

    public NodoMensaje getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoMensaje siguiente) {
        this.siguiente = siguiente;
    }   

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
