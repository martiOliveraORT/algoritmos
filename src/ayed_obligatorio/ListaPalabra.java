package ayed_obligatorio;

public class ListaPalabra extends Lista{
    NodoPalabra primero;
    NodoPalabra ultimo;
    int maxPalabras;    

    public ListaPalabra(int maxPalabras) {
        this.primero = null;
        this.ultimo = null;
        this.maxPalabras = maxPalabras;
    }

    public NodoPalabra getPrimero() {
        return primero;
    }

    public void setPrimero(NodoPalabra primero) {
        this.primero = primero;
    }

    public NodoPalabra getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoPalabra ultimo) {
        this.ultimo = ultimo;
    }

    public int getMaxPalabras() {
        return maxPalabras;
    }

    public void setMaxPalabras(int maxPalabras) {
        this.maxPalabras = maxPalabras;
    }

    public void agregarinicio(int dato, String nombre) {
        NodoPalabra nuevo = new NodoPalabra(dato, nombre);
        if(this.maxPalabras != this.cantNodos) {
            if(this.esVacia()){
                this.setPrimero(nuevo);
                this.setUltimo(nuevo);
            } else{
                nuevo.setSiguiente(this.getPrimero());
                this.setPrimero(nuevo);
            }
            this.cantNodos++;            
        }
    }
    
    public void agregarfinal(int dato, String nombre) {
        NodoPalabra nuevo = new NodoPalabra(dato, nombre);     
        if (this.maxPalabras != this.cantNodos) {
            if (this.esVacia()) {
                this.setPrimero(nuevo);
                this.setUltimo(nuevo);
            } else {
                this.ultimo.setSiguiente(nuevo);
                this.setUltimo(nuevo);
            }
            this.cantNodos++;   
        }
    }
    
    public void agregarordenado(int dato, String nombre) {
        if (this.maxPalabras != this.cantNodos) {
            if (this.esVacia() || dato < this.getPrimero().getDato()) {
                this.agregarinicio(dato, nombre);
            } else {
                if (dato > this.getUltimo().getDato()) {
                    this.agregarfinal(dato, nombre);
                } else {
                    NodoPalabra nuevo = new NodoPalabra(dato, nombre);
                    NodoPalabra aux = this.getPrimero();
                    while (aux.getSiguiente() != null && dato >= aux.siguiente.dato) {
                        aux = aux.getSiguiente();
                    }
                    nuevo.setSiguiente(aux.getSiguiente());
                    aux.setSiguiente(nuevo);
                    this.cantNodos++; // duda
                }
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
                NodoPalabra aux = this.getPrimero();
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
            NodoPalabra aux = this.getPrimero();
            while (aux != null) {
                System.out.print(aux.getPalabra() + " ");
                aux = aux.getSiguiente();
            }
        }
    }
    
    @Override
    public boolean buscarelemento(int dato) {
        boolean encontrado = false;
        NodoPalabra aux = this.getPrimero();
        while (aux != null && !encontrado) {
            if (aux.getDato() == dato) {
                encontrado = true;
            }
            aux = aux.siguiente;
        }
        return encontrado;
    }
    
    @Override
    public NodoPalabra obtenerPunteroElemento(int dato) {
        NodoPalabra encontrado = null;
        NodoPalabra aux = this.getPrimero();
        while (aux != null && encontrado == null) {
            if (aux.getDato() == dato) {
                encontrado = aux;
            }
            aux = aux.siguiente;
        }
        return encontrado;
    }
    
    public NodoPalabra obtenerPunteroPalabra(String palabra) {
        NodoPalabra encontrado = null;
        NodoPalabra aux = this.getPrimero();
        while (aux != null && encontrado == null) {
            if (aux.getPalabra()== palabra) {
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
            NodoPalabra aux = this.getPrimero();        
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

    public String mostrarRecAsc(NodoPalabra primero, NodoPalabra ultimo) {
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

    public String mostrarRecDsc(NodoPalabra primero, NodoPalabra ultimo) {
        if (primero == ultimo) {
            return " " + ultimo.getDato();
        } else {
            return mostrarRecDsc(primero.getSiguiente(), ultimo) + primero.getDato() + " -";
        }
    }
    
}
