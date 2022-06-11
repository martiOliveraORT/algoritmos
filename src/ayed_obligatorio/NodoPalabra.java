
package ayed_obligatorio;

public class NodoPalabra extends Nodo{
    String palabra;
    NodoPalabra siguiente;

    public NodoPalabra(int dato, String palabra) {
        super(dato);
        this.palabra = palabra;
        this.siguiente = null;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public NodoPalabra getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPalabra siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
