/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenes;

import java.awt.Font;
import java.awt.Graphics2D;


import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */
public class Tiquetes_Ordenes{
    Componentes_juego cj;
    Font videoGameFont;

    private String ordenes[]= new String[20];

    private int posicion = 0;
    private int numeroRandom = 0;
    private String numReferencia ="";
   

    public Tiquetes_Ordenes(Componentes_juego cj, String numReferencia, int numeroRandom, int posicion){
        this.cj = cj;
        this.numReferencia= numReferencia;
        this.numeroRandom = numeroRandom;
        this.posicion = posicion;
        videoGameFont = new Font("VideoGame", Font.BOLD, 11);
        setOrdenes();
    }

    public void setOrdenes() {
        ordenes[0] = "¡Orden de Hamburgesa!\n'Pan, Carne, Queso y \nLechuga' Para el estimado\nCliente!"; 
        ordenes[1] = "¡Orden de Hamburgesa!\n'Pan, Carne y Queso'\nPara el estimado Cliente!"; 
        ordenes[2] = "¡Orden de Hamburgesa!\n'Pan y Carne'\nPara el estimado Cliente!"; 
    }

    public void dibujar(Graphics2D g2) {
        if (cj.getTecla().isPresionado_R()==true&&cj.getEstadoJuego()== cj.getEstadoJugador()){
            cj.getUi().dibujarOrdenesMostrar(g2,ordenes, getPosicion(),getNumeroRandom(),
            getNumReferencia(),videoGameFont);
        }
    }
    
    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }


    public int getNumeroRandom() {
        return numeroRandom;
    }

    public int getPosicion() {
        return posicion;
    }
    public void setNumeroRandom(int numeroRandom) {
        this.numeroRandom = numeroRandom;
    }

    // public void dibujarOrdenes_Hacer_2(Graphics2D g2, int numeroRandom) {
    //     if(cj.isVeinteSegundos()==true||ordenHacer_2==true){
    //         final int frameX= cj.tamaño_finalObjeto*12-20;
    //         final int frameY=cj.tamaño_finalObjeto*8-20;
    //         final int frameAncho= cj.tamaño_finalObjeto*4;
    //         final int frameLargo=cj.tamaño_finalObjeto*4;
    //         cj.ui.dibujar_SUB_Pantalla(frameX, frameY, frameAncho, frameLargo);    
    //         g2.setFont(videoGameFont.deriveFont(12F));
    //         g2.setColor(new Color(255, 255, 255, 220)); 
    //         String tiqueteActual=ordenes[numeroRandom];
    //         int textoX = frameX +20;
    //         int textoY = frameY + cj.tamaño_finalObjeto;
    //         for (String linea : tiqueteActual.split("\n")) {
    //             g2.drawString(linea, textoX, textoY);
    //             textoY += 40;
    //         }
    //     }
    // }

    // public void dibujarOrdenes_Hacer_3(Graphics2D g2, int numeroRandom) {
    //     if(cj.isVeinteSegundos()==true||ordenHacer_3==true){
    //         final int frameX= cj.tamaño_finalObjeto*8-20;
    //         final int frameY=cj.tamaño_finalObjeto*8-20;
    //         final int frameAncho= cj.tamaño_finalObjeto*4;
    //         final int frameLargo=cj.tamaño_finalObjeto*4;
    //         cj.ui.dibujar_SUB_Pantalla(frameX, frameY, frameAncho, frameLargo);    
    //         g2.setFont(videoGameFont.deriveFont(12F));
    //         g2.setColor(new Color(255, 255, 255, 220)); 
    //         String tiqueteActual=ordenes[numeroRandom];
    //         int textoX = frameX +20;
    //         int textoY = frameY + cj.tamaño_finalObjeto;
    //         for (String linea : tiqueteActual.split("\n")) {
    //             g2.drawString(linea, textoX, textoY);
    //             textoY += 40;
    //         }
    //     }
    // }


}
