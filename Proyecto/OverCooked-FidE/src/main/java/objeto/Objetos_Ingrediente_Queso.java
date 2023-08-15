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

public class Objetos_Ingrediente_Queso extends Entidades {
    public Objetos_Ingrediente_Queso(Componentes_juego cj) {
        super(cj);
        nombre_ingredientes = "Ingediente__Queso";
        abajo_1 = estructura("/img_comida/ingredientes", "queso",cj.tamaño_finalObjeto/2,cj.tamaño_finalObjeto/2);
        abajo_2 = estructura("/img_comida/ingredientes", "queso",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);

        valor_queso+=0;
    }
}
