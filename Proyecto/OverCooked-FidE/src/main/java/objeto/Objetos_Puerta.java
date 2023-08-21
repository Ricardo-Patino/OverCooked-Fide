/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objeto;

import java.awt.Rectangle;

import entidades.Entidades;
import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */
public class Objetos_Puerta extends Entidades {

    public Objetos_Puerta(Componentes_juego cj) {
        super(cj);
        setNombre("Puerta");

        setAbajo_1 ( estructura("/img_texturas_mosaicos/Puerta", "puerta",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));

        setColisones (true);
        setAreaEntidades_colision(new Rectangle(0,16,48,32));
        setAreaPorDEFECTO_colisionX ( 0);
        setAreaPorDEFECTO_colisionY ( 16);
    }

}
