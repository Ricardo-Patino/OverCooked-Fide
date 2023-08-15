/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objeto;

/**
 *
 * @author Gabriel
 */

import entidades.Entidades;
import juego_restaurante.Componentes_juego;

public class Objetos_Hamburguesa_Completa extends Entidades {

    public Objetos_Hamburguesa_Completa(Componentes_juego cj) {
        super(cj);
        nombre = "Hamburguesa_Completa";
        abajo_1 = estructura("/img_comida/comida_lista", "hamburguesa_completa_1",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);
        // areaColision.x = 5;
        hamburgesas_hechas = 0;
        colisones = true;
    }
}
