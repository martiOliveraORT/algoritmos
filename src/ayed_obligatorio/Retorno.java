
package ayed_obligatorio;

public class Retorno {
    enum Resultado{
        OK, ERROR, NO_IMPLEMENTADA
    };
    
    int valorEntero;
    String valorString;
    boolean valorBool;
    Resultado resultado;
    
    public Retorno(Resultado resultado){
        this.resultado = resultado;
    }
}
