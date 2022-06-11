
package ayed_obligatorio;

public class ListaLinea extends Lista{
    NodoLinea primero;
    NodoLinea ultimo;

    public ListaLinea() {
        this.primero = null;
        this.ultimo = null;
    }

    public NodoLinea getPrimero() {
        return primero;
    }

    public void setPrimero(NodoLinea primero) {
        this.primero = primero;
    }

    public NodoLinea getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoLinea ultimo) {
        this.ultimo = ultimo;
    }
    
    public void agregarinicio(int dato) {
        NodoLinea nuevo = new NodoLinea(dato);
        if(this.esVacia()){
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else{
            nuevo.setSiguiente(this.getPrimero());
            this.setPrimero(nuevo);
        }
        this.cantNodos++;
    }    
    
    public void agregarfinal(int dato) {
        NodoLinea nuevo = new NodoLinea(dato); 
        if (this.esVacia()) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            this.ultimo.setSiguiente(nuevo);
            this.setUltimo(nuevo);
        }
        this.cantNodos++;
    }
    
    public void agregarordenado(int dato) {
        if (this.esVacia()|| dato < this.getPrimero().getDato()){
            this.agregarinicio(dato);
        } else{
            if (dato>this.getUltimo().getDato()){
                this.agregarfinal(dato);
            }else{
                NodoLinea nuevo = new NodoLinea(dato);
                NodoLinea aux = this.getPrimero();
                while (aux.getSiguiente()!= null && dato>= aux.siguiente.dato){
                    aux=aux.getSiguiente();
                }
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);  
                this.cantNodos++; //duda
            }                        
        }
    }
    
    @Override
    public void borrarinicio() {
        if (!this.esVacia()) {
            if (this.primero == this.ultimo) {
                this.setPrimero(null);
                this.setUltimo(null);
            } else {
                this.setPrimero(this.primero.siguiente);
            }
            this.cantNodos--;
        }
    }
    
    @Override
    public void borrarfinal() {
        if (!esVacia()) {
            if (this.primero == this.ultimo) {
                this.setPrimero(null);
                this.setUltimo(null);
            } else {
                NodoLinea aux = this.getPrimero();
                while (aux.getSiguiente() != this.getUltimo()) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
                this.setUltimo(aux);
            }
            this.cantNodos--;
        }
    }
    
    @Override
    public void listar() {
        if (!this.esVacia()) {
            NodoLinea aux = this.getPrimero();
            while (aux != null) {
                System.out.print(aux.getDato() + " - ");
                aux = aux.getSiguiente();
            }
            System.out.println();
        }
    }
    
    @Override
    public boolean buscarelemento(int dato) {
        boolean encontrado = false;
        NodoLinea aux = this.getPrimero();
        while (aux != null && !encontrado) {
            if (aux.getDato() == dato) {
                encontrado = true;
            }
            aux = aux.siguiente;
        }
        return encontrado;
    }
    
    @Override
    public NodoLinea obtenerPunteroElemento(int dato) {
        NodoLinea encontrado = null;
        NodoLinea aux = this.getPrimero();
        while (aux != null && encontrado == null) {
            if (aux.getDato() == dato) {
                encontrado = aux;
            }
            aux = aux.siguiente;
        }
        return encontrado;
    }
    
    @Override
    public boolean borrarElemento(int dato) {
        boolean borrado = false;
        if (!this.esVacia()) {
            NodoLinea aux = this.getPrimero();        
            if (aux.getDato() == dato ) {
                this.setPrimero(this.primero.siguiente);
                borrado = true;
                this.cantNodos--;
            } else {
                while (aux.siguiente != null && !borrado) {
                    if (aux.siguiente.getDato() == dato) {
                        aux.siguiente = aux.siguiente.siguiente;
                        borrado = true;
                        this.cantNodos--;
                        return borrado;
                    }
                    aux = aux.siguiente;
                }
            }
        }
        return borrado;
    }
    
    @Override
    public void mostrarRecAsc() {
       System.out.println(mostrarRecAsc(this.getPrimero(),this.getUltimo())); 
    }

    public String mostrarRecAsc(NodoLinea primero, NodoLinea ultimo) {
        if (primero==ultimo){
            return " " + ultimo.getDato();
        }else{
            return   primero.getDato() + " -" + mostrarRecAsc(primero.getSiguiente(),ultimo)  ;
        }
    }
    
    @Override
    public void mostrarRecDsc() {
        System.out.println(mostrarRecDsc(this.getPrimero(), this.getUltimo()));

    }

    public String mostrarRecDsc(NodoLinea primero, NodoLinea ultimo) {
        if (primero == ultimo) {
            return " " + ultimo.getDato();
        } else {
            return mostrarRecDsc(primero.getSiguiente(), ultimo) + primero.getDato() + " -";
        }
    }
}
