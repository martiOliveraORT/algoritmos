
package ayed_obligatorio;

public class NodoMensaje extends Nodo{
    ListaLinea ll;
    NodoMensaje siguiente;

    public NodoMensaje(int dato) {
        super(dato);
        this.ll = new ListaLinea();
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
    
}
