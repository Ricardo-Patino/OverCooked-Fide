package lista_Ingredientes;

public class Nodo_Ingredientes {
    private Manejador_Ingredientes dato;
    private Nodo_Ingredientes next;
    
    public Nodo_Ingredientes(Manejador_Ingredientes dato){
        this.dato=dato;
    }

    public Manejador_Ingredientes getDato() {
        return dato;
    }

    public void setDato(Manejador_Ingredientes dato) {
        this.dato = dato;
    }

    public Nodo_Ingredientes getNext() {
        return next;
    }

    public void setNext(Nodo_Ingredientes next) {
        this.next = next;
    }
    
}