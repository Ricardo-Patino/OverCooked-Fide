package ordenes;

import java.awt.Graphics2D;

/**
 *
 * @author Racha
 */

public class Cola {
    private Nodo frente;
    private Nodo ultimo;
    private int largo;

 
    public Cola() {
        this.frente = null;
        this.ultimo = null;
        this.largo = 0;
    }

    public void encola(Tiquetes_Ordenes valor){
        Nodo newNode = new Nodo(valor);
        if(frente == null){  // significa que la cola esta vacia
            frente = newNode;
            ultimo = newNode;                    
        } else{
            ultimo.setAtras(newNode);
            ultimo=newNode;
        }
        largo++;
        
    }

    public Nodo atiende(){
        Nodo aux = frente;
        if(frente!=null){
            frente=frente.getAtras();
            aux.setAtras(null);
            largo--;
        }
        return aux;
    }
    
    public Tiquetes_Ordenes buscarPrimerEncolado() {
        if (frente != null) {
            return frente.getDato(); // Retorna el primer elemento encolado
        }
        return null; // La cola está vacía, no hay elemento para retornar
    }

    public boolean encuentra(Tiquetes_Ordenes referencia){
        if(frente != null){
            if (ultimo.getDato().getNumReferencia().equals(referencia.getNumReferencia())){
                return true;
            }else{
                Nodo aux=frente;
                while(aux!=null){
                    if(aux.getDato().getNumReferencia().equals(referencia.getNumReferencia())){
                        return true;
                    }
                    aux=aux.getAtras();
                }
            }
        }
        return false;
    }

    public int getLargo() {
        return largo;
    }

    
    public void imprimir_ordenes(Graphics2D g2){
        Nodo aux=frente; 
        while(aux!=null){
            aux.getDato().dibujar(g2);
            aux=aux.getAtras();
        }
    }
}
