/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

// import java.util.Random;

import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */
public class NPC_1 extends Entidades {
    String dialogos_unique[][]= new String[20][20];
    public NPC_1(Componentes_juego cj) {
        super(cj);
        setMovimiento( "abajo");
        setVelocidad(1);
        setDialogosPaginado (-1);
        getImagenNPC_1();
        setDialogos();
    }

    public void getImagenNPC_1() {
        // Cambiar abajo_1 a abajo_2 para obtener los frames deseados y descomentar 
        // iniciarAccionNPC(), y las demas comentarios de getImagenNPC_1(), y en Entidades
        // Descomentar lo que esta comentado en update(), si se quiere crear mas npcs simplemente se
        // crea otra clase y se copian los datos que hay en esta clase y se cambian las rutas de las imagenes
        // con las que estan en /img/img_npcs, tambien se inicializan en el paquete 
        // recursos_objetos/Recursos_Inicializar en el metodo setNPC() y se siguen las instrucciones del metodo

        // setAbajo_1(  estructura("/img_npcs/npc_1/Abajo", "caminando_abajo_NPC_1",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setAbajo_1(  estructura("/img_npcs/npc_1/Abajo", "caminando_abajo_NPC_2",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        // setAbajo_3(  estructura("/img_npcs/npc_1/Abajo", "caminando_abajo_NPC_3",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        // setAbajo_4(  estructura("/img_npcs/npc_1/Abajo", "caminando_abajo_NPC_4",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));

        // setArriba_1(  estructura("/img_npcs/npc_1/Arriba", "caminando_arriba_NPC_1",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setArriba_1(  estructura("/img_npcs/npc_1/Arriba", "caminando_arriba_NPC_2",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        // setArriba_3(  estructura("/img_npcs/npc_1/Arriba", "caminando_arriba_NPC_3",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        // setArriba_4(  estructura("/img_npcs/npc_1/Arriba", "caminando_arriba_NPC_4",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));

        // setDerecha_1 ( estructura("/img_npcs/npc_1/Derecha", "caminando_derecha_NPC_1",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setDerecha_1 ( estructura("/img_npcs/npc_1/Derecha", "caminando_derecha_NPC_2",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        // setDerecha_3 ( estructura("/img_npcs/npc_1/Derecha", "caminando_derecha_NPC_3",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        // setDerecha_4 ( estructura("/img_npcs/npc_1/Derecha", "caminando_derecha_NPC_4",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));

        // setIzquierda_1 ( estructura("/img_npcs/npc_1/Izquierda", "caminando_izquierda_NPC_1",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        setIzquierda_1 ( estructura("/img_npcs/npc_1/Izquierda", "caminando_izquierda_NPC_2",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        // setIzquierda_3 ( estructura("/img_npcs/npc_1/Izquierda", "caminando_izquierda_NPC_3",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
        // setIzquierda_4 ( estructura("/img_npcs/npc_1/Izquierda", "caminando_izquierda_NPC_4",cj.getTamaño_finalObjeto(),cj.getTamaño_finalObjeto()));
    }

    // @Override
    // public void iniciarAccionNPC() {
    //     bloqueoDeAccionesporSegundo++;
    //     if (bloqueoDeAccionesporSegundo == 120) {
    //         Random random = new Random();
    //         int i = random.nextInt(100) + 1;
    //         if (i <= 25) {
    //             movimiento = "arriba";
    //         }
    //         if (i > 25 && i <= 50) {
    //             movimiento = "abajo";
    //         }
    //         if (i > 50 && i <= 75) {
    //             movimiento = "izquierda";
    //         }
    //         if (i > 75 && i <= 100) {
    //             movimiento = "derecha";
    //         }
    //         bloqueoDeAccionesporSegundo = 0;
    //     }
    // }

    public void setDialogos() {
        dialogos_unique[0][0] ="Tienen bebidas?";

        dialogos_unique[1][0] = "¿Sin bebidas? ¡Por favor! Este lugar se ha convertido en el\nparaíso de la deshidratación. Si al menos pudiera exprimir\nalgo de agua de las hamburguesas...";

        dialogos_unique[2][0] = "¿Por qué no podemos tener un día de 'papa-fritas-libre'?\nPero no, aquí siempre estamos en modo 'hamburguesa total'.\nNo sé si reír o llorar por la falta de variedad.";

        dialogos_unique[3][0] = "¡Atención, atención! Anuncio oficial del Departamento de \nPapas Fritas: se declaran en huelga hasta que me escuchen.\n¡Espero que disfruten de su 'papaless' experiencia!";
        
        dialogos_unique[4][0] = "(suspirando dramáticamente) Tres opciones de hamburguesas \nson como una tormenta en un vaso de agua, una tormenta de\ncarne y pan.";

        dialogos_unique[5][0] = "No me queda de otra, dos hamburguesas para llevar, por favor.\nPero que vengan bien acompañadas !que son bien tímdas!";

        dialogos_unique[6][0] = "¡No me hables más bastardo!";
        setDialogos(dialogos_unique);
    }

    public void hablaNPC() {
        mirarJugador();
        iniciarDialogo(this, getDialogosPaginado());
        int aumentar = getDialogosPaginado();
        setDialogosPaginado(aumentar+=1);
        if (getDialogos()[getDialogosPaginado()][0] == null) {
            setDialogosPaginado(0);
        }
    }
}
