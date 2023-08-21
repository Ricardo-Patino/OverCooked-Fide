package inventario;

import entidades.Entidades;

public class Inventario {
    private Entidades[] array;
    private int tamaño;

    public Inventario() {
        array = new Entidades[5]; // Tamaño inicial del arreglo
        tamaño = 0;
    }
    
    public void añadir(Entidades item) {
        if (tamaño == array.length) {
            // Si el arreglo está lleno, duplica su tamaño
            Entidades[] newArray = new Entidades[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[tamaño] = item;
        tamaño++;
    }
    
    public Entidades get(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return array[index];
    }
    public void eliminar(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        // Mueve los elementos a la izquierda para eliminar el elemento en el índice dado
        for (int i = index; i < tamaño - 1; i++) {
            array[i] = array[i + 1];
        }
        array[tamaño - 1] = null; // Elimina la referencia al último elemento
        tamaño--;
    }
    
    public int getTamaño() {
        return tamaño;
    }

}
