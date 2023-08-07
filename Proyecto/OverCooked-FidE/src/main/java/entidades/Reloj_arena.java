/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import juego_restaurante.Componentes_juego;

/**
 *
 * @author Racha
 */
public class Reloj_arena extends Entidades{
    Componentes_juego cj;
    private long tiempoAnterior;
    
    public Reloj_arena(Componentes_juego cj) {
        this.cj = cj;
        setValoresPorDefecto();
        getImagenReloj();
        tiempoAnterior = System.currentTimeMillis();
    }
    public void setValoresPorDefecto(){
        x= 34;
        y= 503;
        movimiento = "primera_imagen";
    }
    
    public void getImagenReloj(){
        try {
            reloj_1 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_1.png"));
            reloj_2 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_2.png"));
            reloj_3 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_3.png"));
            reloj_4 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_4.png"));
            reloj_5 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_5.png"));
            reloj_6 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_6.png"));
            reloj_7 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_7.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
       public void dibujar (Graphics2D g2){
        
        BufferedImage imagen = null;
        switch (movimiento) {
            case "primera_imagen":
                imagen = reloj_1;
                break;
            case "segunda_imagen":
                imagen = reloj_2;
                break;
            case "tercera_imagen":
                imagen = reloj_3;
                break;
            case "cuarta_imagen":
                imagen = reloj_4;           
                break;
            case "quinta_imagen":
                imagen = reloj_5;              
                break;
            case "sexta_imagen":
                imagen = reloj_6;            
                break;
            case "septima_imagen":
                imagen = reloj_7;       
                break;
            default:
                throw new AssertionError();
        }
        g2.drawImage(imagen, x, y, cj.tamaño_finalObjeto, cj.tamaño_finalObjeto, null);
    }
    
    public void update (){
        long tiempoActual = System.currentTimeMillis();
        long tiempoTranscurrido = (tiempoActual - tiempoAnterior)/500;
        if (tiempoTranscurrido == 0) {
            movimiento = "primera_imagen";
        }
        if (tiempoTranscurrido == 1) {
            movimiento = "segunda_imagen";
        }
        if (tiempoTranscurrido == 2) {
            movimiento = "tercera_imagen";
        }
        if (tiempoTranscurrido == 3) {
            movimiento = "cuarta_imagen";
        }
        if (tiempoTranscurrido == 4) {
            movimiento = "quinta_imagen";
        }
        if (tiempoTranscurrido == 5) {
            movimiento = "sexta_imagen";
        }
        if (tiempoTranscurrido == 6) {
            movimiento = "septima_imagen";
        }
        if (tiempoTranscurrido > 6) {
            tiempoAnterior = tiempoActual;
        }  
    }
}
