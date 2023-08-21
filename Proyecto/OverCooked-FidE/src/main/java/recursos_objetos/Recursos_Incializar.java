/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recursos_objetos;


import entidades.NPC_1;
// import entidades.NPC_2;
// import entidades.NPC_3;
// import entidades.NPC_4;
// import entidades.NPC_5;

import juego_restaurante.Componentes_juego;
import objeto.Objetos_Hamburguesa_Carne;
import objeto.Objetos_Hamburguesa_Carne_Queso;
import objeto.Objetos_Hamburguesa_Completa;

import objeto.Objetos_Puerta;


/**
 *
 * @author Gabriel
 */

public class Recursos_Incializar {
    Componentes_juego cj;

    public Recursos_Incializar(Componentes_juego cj) {
        this.cj = cj;
    }    

    public void setObjeto() {
        cj.getObj()[1] = new Objetos_Hamburguesa_Completa(cj);
        cj.getObj()[1].setPlanoMundoX(cj.getTamaño_finalObjeto() * 26);
        cj.getObj()[1].setPlanoMundoY( cj.getTamaño_finalObjeto() * 20);

        cj.getObj()[2] = new Objetos_Hamburguesa_Carne(cj);
        cj.getObj()[2].setPlanoMundoX(cj.getTamaño_finalObjeto() * 38);
        cj.getObj()[2].setPlanoMundoY( cj.getTamaño_finalObjeto() * 22);

        cj.getObj()[3] = new Objetos_Hamburguesa_Carne_Queso(cj);
        cj.getObj()[3].setPlanoMundoX(cj.getTamaño_finalObjeto() * 44);
        cj.getObj()[3].setPlanoMundoY( cj.getTamaño_finalObjeto() * 20);
    }

    public void setObjeto_Puerta() {
        cj.getObj()[0] = new Objetos_Puerta(cj);
        cj.getObj()[0].setPlanoMundoX(cj.getTamaño_finalObjeto() * 24);
        cj.getObj()[0].setPlanoMundoY( cj.getTamaño_finalObjeto() * 13);

    }

    // Para inicializar los npc primero se llama a la clase creada que contenga los parametros
    // del npc, ejemplo : NPC_2. Luego se le indica al programa donde estara ubicado ese npc
    // se debe de tener en cuento que X, Y (0,0); Esta ubicado en la Esquina Superior Izquierda.
    // Se llama como esta en los comentarios la cantidad maxima de npcs es de 10 pues es
    // lo que esta estipulado en la clase Componentes_Juegos cuan se llama a npc en la 
    // linea 93 (se puede aumentar la cantidad pero podria perjudicar la jugabilidad), en 
    // Componentes_Juegos se explica un poco mas lo que hace.
    
    public void setNPC() {


        cj.getNpc()[0] = new NPC_1(cj);
        cj.getNpc()[0].setPlanoMundoX(cj.getTamaño_finalObjeto() * 35);
        cj.getNpc()[0].setPlanoMundoY( cj.getTamaño_finalObjeto() * 18);

        // cj.npc[1] = new NPC_2(cj);
        // cj.npc[1].setPlanoMundoX(cj.getTamaño_finalObjeto() * 20);
        // cj.npc[1].setPlanoMundoY( cj.getTamaño_finalObjeto() * 20);

        // cj.npc[2] = new NPC_3(cj);
        // cj.npc[2].setPlanoMundoX(cj.getTamaño_finalObjeto() * 40);
        // cj.npc[2].setPlanoMundoY( cj.getTamaño_finalObjeto() * 18);

        // cj.npc[3] = new NPC_4(cj);
        // cj.npc[3].setPlanoMundoX(cj.getTamaño_finalObjeto() * 45);
        // cj.npc[3].setPlanoMundoY( cj.getTamaño_finalObjeto() * 15);

        // cj.npc[4] = new NPC_5(cj);
        // cj.npc[4].setPlanoMundoX(cj.getTamaño_finalObjeto() * 23);
        // cj.npc[4].setPlanoMundoY( cj.getTamaño_finalObjeto() * 20);
    }
}
