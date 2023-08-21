/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objeto;

import entidades.Entidades;
import juego_restaurante.Componentes_juego;

/**
 *
 * @author Racha
 */

public class Objetos_Ingrediente_Queso extends Entidades {
    public Objetos_Ingrediente_Queso(Componentes_juego cj) {
        super(cj);
        String name = "Ingediente__Queso";
        setNombre_ingredientes (name );
        setAbajo_1 ( estructura("/img_comida/ingredientes", "queso",cj.getTamaño_finalObjeto()/2,cj.getTamaño_finalObjeto()/2));
        setAbajo_2 ( estructura("/img_comida/ingredientes", "queso",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setValor_queso(0);
    }
}
