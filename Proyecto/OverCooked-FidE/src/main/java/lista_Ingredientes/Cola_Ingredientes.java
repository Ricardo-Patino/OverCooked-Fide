package lista_Ingredientes;
/**
 *
 * @author Racha
 */
public class Cola_Ingredientes {
    private Nodo_Ingredientes cabeza;
    private Nodo_Ingredientes ultimo;
    private int largo = 0;
    
    public void inserta(Manejador_Ingredientes valor){
        Nodo_Ingredientes newNode = new Nodo_Ingredientes(valor);
        if(cabeza == null){  // significa que la cola esta vacia
            cabeza = newNode;
            ultimo = newNode;                    
        } else{
            ultimo.setNext(newNode);
            ultimo=newNode;
        }
        largo++;
        
    }
    
    public int getLargo() {
        return largo;
    }
    
    public Nodo_Ingredientes elimina(){
        Nodo_Ingredientes aux = cabeza;
        if(cabeza != null){
            cabeza = cabeza.getNext();
            if (cabeza == null) {
                ultimo = null;  // Si la cabeza se vuelve null, también actualiza "ultimo"
            }
            aux.setNext(null);
            
            largo--;
        }
        return aux;
    }
}
