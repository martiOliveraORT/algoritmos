package ayed_obligatorio;

public class Lista implements ILista{   
    int cantNodos;

    public Lista() {
        this.cantNodos = 0;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public void setCantNodos(int cantNodos) {
        this.cantNodos = cantNodos;
    }    

    @Override
    public boolean esVacia() {
        return this.getCantNodos() == 0;
    }

//    @Override
//    public void agregarinicio(int dato, String nombre) {  
//    }

    @Override
    public void agregarinicio() {
    }

//    @Override
//    public void agregarfinal(int dato, String nombre) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void agregarfinal() {        
    }

//    @Override
//    public void agregarordenado(int dato, String nombre) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void agregarordenado() {
    }

    @Override
    public void agregarenpos(int pos, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarinicio() {        
    }

    @Override
    public void borrarfinal() {
    }

    @Override
    public void listar() {
    }

    @Override
    public int cantnodos() {
        return this.cantNodos;
    }

    @Override
    public boolean buscarelemento(int dato) {
        return false;
    }

    @Override
    public Nodo obtenerPunteroElemento(int dato) {        
        return null;        
    }

    @Override
    public boolean borrarElemento(int dato) {
        return false;
    }

    @Override
    public void mostrarRecAsc() {
    }

    @Override
    public void mostrarRecDsc() {
    }

    @Override
    public int maximo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int minimo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Nodo obtenerPunteroMax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Nodo obtenerPunteroMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}