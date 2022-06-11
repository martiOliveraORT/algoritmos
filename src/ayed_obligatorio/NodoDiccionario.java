
package ayed_obligatorio;

public class NodoDiccionario extends Nodo{
    String palabra;
    NodoDiccionario siguiente;

    public NodoDiccionario(int dato, String palabra) {
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

    public NodoDiccionario getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDiccionario siguiente) {
        this.siguiente = siguiente;
    }

  
    
}
