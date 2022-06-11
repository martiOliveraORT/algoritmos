
package ayed_obligatorio;

public interface ILista {
    boolean esVacia();
    //void agregarinicio(int dato, String nombre);
    //    void agregarinicio(int dato);
        void agregarinicio();
    //void agregarfinal(int dato, String nombre);
    //    void agregarfinal(int dato);
        void agregarfinal();
    //void agregarordenado(int dato, String nombre);
    //    void agregarordenado(int dato);
        void agregarordenado();
        void agregarenpos(int pos, String nombre);
    void borrarinicio();
    void borrarfinal();
    void listar();
    int cantnodos();
    boolean buscarelemento(int dato);
    Nodo obtenerPunteroElemento(int dato);
    boolean borrarElemento(int dato);
    void mostrarRecAsc();
    void mostrarRecDsc();
    int maximo();
    int minimo();
    Nodo obtenerPunteroMax();
    Nodo obtenerPunteroMin();



}
