package ayed_obligatorio;

import java.util.Date;

public class FechaCantidad {
    Date fecha;
    int cantidad;

    public FechaCantidad(Date fecha, int cantidad) {
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
