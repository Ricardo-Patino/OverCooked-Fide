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
public class Objetos_Puerta extends Entidades {

    public Objetos_Puerta(Componentes_juego cj) {
        super(cj);
        nombre = "Puerta";

        abajo_1 = estructura("/img_texturas_mosaicos/Puerta", "puerta",cj.tamaño_finalObjeto,cj.tamaño_finalObjeto);

        colisones = true;
        areaEntidades_colision.x = 0;
        areaEntidades_colision.y = 16;
        areaEntidades_colision.width = 48;
        areaEntidades_colision.height = 32;
        areaPorDEFECTO_colisionX = areaEntidades_colision.x;
        areaPorDEFECTO_colisionY = areaEntidades_colision.y;
    }

}
