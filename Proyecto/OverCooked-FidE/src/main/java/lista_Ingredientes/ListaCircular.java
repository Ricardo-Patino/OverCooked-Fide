package lista_Ingredientes;

public class ListaCircular {
    private Nodo_Ingredientes cabeza;
    private Nodo_Ingredientes ultimo;

    public void inserta(Manejador_Ingredientes p) {
        if (cabeza == null) {
            cabeza = new Nodo_Ingredientes(p);
            ultimo = cabeza;
        } else if (p.getIngrediente() < cabeza.getDato().getIngrediente()) {
            Nodo_Ingredientes nuevoNodo_Ingredientes = new Nodo_Ingredientes(p);
            nuevoNodo_Ingredientes.setNext(cabeza);
            cabeza = nuevoNodo_Ingredientes;
        } else if (ultimo.getDato().getIngrediente() <= p.getIngrediente()) {
            ultimo.setNext(new Nodo_Ingredientes(p));
            ultimo = ultimo.getNext();
        } else {
            Nodo_Ingredientes aux = cabeza;
            while (aux.getNext().getDato().getIngrediente() < p.getIngrediente()) {
                aux = aux.getNext();
            }
            Nodo_Ingredientes temp = new Nodo_Ingredientes(p);
            temp.setNext(aux.getNext());
            aux.setNext(temp);
        }
    }

    public int size() {
        int count = 0;
        Nodo_Ingredientes aux = cabeza;
        while (aux != null) {
            count++;
            aux = aux.getNext();
            if (aux == cabeza)
                break;
        }
        return count;
    }

    public void elimina(int id) {
        // Si una Manejador_Ingredientes tiene el id, lo elimina
        if (cabeza != null) { // Si hay algo en la lista buscaré
            if (cabeza.getDato().getIngrediente() == id) {
                cabeza = cabeza.getNext();
                cabeza.getDato().removeIngredientes();
            } else {
                Nodo_Ingredientes aux = cabeza; // utilizo aux como indice
                // Mientras no se acabe la lista y el elemento
                // de la lista sea menor que el buscado
                if (aux!=null){
                    while (aux.getNext().getDato().getIngrediente() < id) {
                        aux = aux.getNext();
                    }
                    // avanzo en la lista
    
                    // si es el de adelante lo borro
                    if (aux.getNext().getDato().getIngrediente() == id) {
                        aux.setNext(aux.getNext().getNext()); // cambio las referencias
                    }
                }
            }
        }
    }
    public String agregar_valor_inventario(int id) {
        // Si una Manejador_Ingredientes tiene el id, lo elimina
        String valorObjeto = "";
        if (cabeza != null) { // Si hay algo en la lista buscaré
            if (cabeza.getDato().getIngrediente() == id) {
                valorObjeto = cabeza.getDato().getNombreArray();
                System.out.println(valorObjeto);
                cabeza = cabeza.getNext();
            } else {
                Nodo_Ingredientes aux = cabeza; // utilizo aux como indice
                // Mientras no se acabe la lista y el elemento
                // de la lista sea menor que el buscado
                while (aux.getNext().getDato().getIngrediente() < id) {
                    aux = aux.getNext();
                }
                // avanzo en la lista

                // si es el de adelante lo borro
                if (aux.getNext().getDato().getIngrediente() == id) {
                    aux.setNext(aux.getNext().getNext()); // cambio las referencias
                
                }
                
            }
        }
        return valorObjeto;
    }
}