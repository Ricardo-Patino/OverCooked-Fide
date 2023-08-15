package ordenes;

public class Nodo {
    private Tiquetes_Ordenes dato;
    private Nodo atras;

    public Nodo(Tiquetes_Ordenes dato) {
        this.dato = dato;
        this.atras = null;
    }

 

    public Tiquetes_Ordenes getDato() {
        return dato;
    }

 

    public void setDato(Tiquetes_Ordenes dato) {
        this.dato = dato;
    }

 

    public Nodo getAtras() {
        return atras;
    }

 

    public void setAtras(Nodo atras) {
        this.atras = atras;
    }

}