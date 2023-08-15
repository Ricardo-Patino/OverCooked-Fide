package lista_Ingredientes;
import juego_restaurante.Componentes_juego;
import objeto.Objetos_Ingrediente_Carne;
import objeto.Objetos_Ingrediente_Lechuga;
import objeto.Objetos_Ingrediente_Pan;
import objeto.Objetos_Ingrediente_Queso;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entidades.Entidades;

public class Manejador_Ingredientes {
    Componentes_juego cj;
    
    private ArrayList<Entidades> listaIngredientes;

    private int ingrediente=-1;

    public Manejador_Ingredientes(Componentes_juego cj, int ingrediente){
        this.cj = cj;
        this.ingrediente = ingrediente;
        ingredientes();
    }

    public void dibujar (Graphics2D g2){
        if (cj.estadoJuego==cj.estadoJugador){
            cj.ui.dibujarInventarioIngredientes(g2);
        }
    }

    public void ingredientes(){
        listaIngredientes = new ArrayList<>();
        listaIngredientes.add(new Objetos_Ingrediente_Carne(cj));
        listaIngredientes.add(new Objetos_Ingrediente_Queso(cj));
        listaIngredientes.add(new Objetos_Ingrediente_Lechuga(cj));
        listaIngredientes.add(new Objetos_Ingrediente_Pan(cj));
        aggregateIngredientes();
    }

    public void aggregateIngredientes(){
        if (ingrediente >= 0 && ingrediente < listaIngredientes.size()) {
            Entidades ingredienteSeleccionado = listaIngredientes.get(ingrediente);
            cj.jugador.inventario.add(ingredienteSeleccionado);
        }
    }

    public String getNombreArray() {
        String val="";
        for (int i = 0; i <listaIngredientes.size(); i++) {
            if (listaIngredientes.get(i) == listaIngredientes.get(ingrediente)){
                val = listaIngredientes.get(i).nombre_ingredientes;
            }
        }
        
        return val;
    }

    public void removeIngredientes(){
        if (ingrediente >= 0 && ingrediente < cj.jugador.inventario.size()) {
            cj.jugador.inventario.remove(ingrediente);
        }
    }

    public int getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(int ingrediente) {
        this.ingrediente = ingrediente;
    }
    
}