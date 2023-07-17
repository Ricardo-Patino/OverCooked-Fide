
package proyecto.overcooked.fide;

/**
 *
 * @author Racha
 */
public class Nodo {
    private Hamburguesa dato;
    private Nodo next;
    
    public Nodo(Hamburguesa dato){
        this.dato=dato;
    }

    public juego getDato() {
        return dato;
    }

    public void setDato(Juego dato) {
        this.dato = dato;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }
    
        public cintaTransportadora getDato() {
        return dato;
    }

    public void setDato(cintaTransportadora dato) {
        this.dato = dato;
    }
}
