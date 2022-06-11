
package ayed_obligatorio;

public class NodoContacto extends Nodo{
    String nombre;
    ListaMensaje lm;
    NodoContacto siguiente;

    public NodoContacto(int dato, String nombre) {
        super(dato);
        this.nombre = nombre;
        this.lm = new ListaMensaje();
        this.siguiente = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaMensaje getLm() {
        return lm;
    }

    public void setLm(ListaMensaje lm) {
        this.lm = lm;
    }

    public NodoContacto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoContacto siguiente) {
        this.siguiente = siguiente;
    }  
    
    
    
}
