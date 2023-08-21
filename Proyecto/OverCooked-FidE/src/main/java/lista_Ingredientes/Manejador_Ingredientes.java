package lista_Ingredientes;
import juego_restaurante.Componentes_juego;
import objeto.Objetos_Ingrediente_Carne;
import objeto.Objetos_Ingrediente_Lechuga;
import objeto.Objetos_Ingrediente_Pan;
import objeto.Objetos_Ingrediente_Queso;

import java.awt.Graphics2D;

import entidades.Entidades;

public class Manejador_Ingredientes {
    Componentes_juego cj;
    
    private Entidades[] listaIngredientes = new Entidades[4];

    private int ingrediente=-1;

    public Manejador_Ingredientes(Componentes_juego cj, int ingrediente){
        this.cj = cj;
        this.ingrediente = ingrediente;
        ingredientes();
    }

    public void dibujar (Graphics2D g2){
        if (cj.getEstadoJuego()==cj.getEstadoJugador()){
            cj.getUi().dibujarInventarioIngredientes(g2);
        }
    }

    public void ingredientes(){
        listaIngredientes[0] = new Objetos_Ingrediente_Carne(cj);
        listaIngredientes[1] = new Objetos_Ingrediente_Queso(cj);
        listaIngredientes[2] = new Objetos_Ingrediente_Lechuga(cj);
        listaIngredientes[3] = new Objetos_Ingrediente_Pan(cj);
        aggregateIngredientes();
    }

    public void aggregateIngredientes(){
        if (ingrediente >= 0 && ingrediente < listaIngredientes.length) {
            Entidades ingredientes = listaIngredientes[ingrediente];
            cj.getJugador().getInventarioIngredientesMostrarJugador().añadir(ingredientes);
        }
    }

    public int getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(int ingrediente) {
        this.ingrediente = ingrediente;
    }
    
}