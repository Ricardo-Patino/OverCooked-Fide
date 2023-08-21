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
public class Objetos_Hamburguesa_Carne_Queso extends Entidades {

    public Objetos_Hamburguesa_Carne_Queso(Componentes_juego cj) {
        super(cj);
        setNombre( "Hamburguesa__Carne_Queso");
        setAbajo_1(estructura("/img_comida/comida_lista", "hamburguesa_con_carne_queso_1",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setHamburgesas_hechas( 0);
        
        setColisones (true);
    }

}
