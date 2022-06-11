
package ayed_obligatorio;

public class NodoLinea extends Nodo{
    ListaPalabra lp;
    NodoLinea siguiente;
    int MAX_PALABRAS_X_LINEA = 3;

    public NodoLinea(int dato) {
        super(dato);
        this.lp = new ListaPalabra(MAX_PALABRAS_X_LINEA);
        this.siguiente = null;
    }

    public ListaPalabra getLp() {
        return lp;
    }

    public void setLp(ListaPalabra lp) {
        this.lp = lp;
    }

    public NodoLinea getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLinea siguiente) {
        this.siguiente = siguiente;
    }

    public int getMAX_PALABRAS_X_LINEA() {
        return MAX_PALABRAS_X_LINEA;
    }

    public void setMAX_PALABRAS_X_LINEA(int MAX_PALABRAS_X_LINEA) {
        this.MAX_PALABRAS_X_LINEA = MAX_PALABRAS_X_LINEA;
    }  
    
}
