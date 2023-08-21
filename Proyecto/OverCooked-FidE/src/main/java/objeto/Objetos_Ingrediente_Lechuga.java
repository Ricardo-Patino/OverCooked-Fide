/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objeto;

import entidades.Entidades;
import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */

public class Objetos_Ingrediente_Lechuga extends Entidades {
    public Objetos_Ingrediente_Lechuga(Componentes_juego cj) {
        super(cj);
        String name="Ingediente__Lechuga";
        setNombre_ingredientes ( name);
        setAbajo_1 ( estructura("/img_comida//ingredientes", "lechuga",cj.getTamaño_finalObjeto()/2,cj.getTamaño_finalObjeto()/2));
        setAbajo_2 ( estructura("/img_comida//ingredientes", "lechuga",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setValor_lechuga(0);
    }

}
