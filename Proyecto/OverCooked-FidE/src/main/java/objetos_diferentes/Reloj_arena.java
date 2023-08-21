/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos_diferentes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */
public class Reloj_arena extends Objetos_diferentes{
    Componentes_juego cj;
    private long tiempoAnterior;
    
    public Reloj_arena(Componentes_juego cj) {
        this.cj = cj;
        setValoresPorDefecto();
        getImagenReloj();
        tiempoAnterior = System.currentTimeMillis();
    }
    public void setValoresPorDefecto(){
        x= 8;
        y= 503;
        movimiento = "primera_imagen";
    }
    
    public void getImagenReloj(){
        try {
            reloj_1 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_Arena/reloj_1.png"));
            reloj_2 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_Arena/reloj_2.png"));
            reloj_3 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_Arena/reloj_3.png"));
            reloj_4 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_Arena/reloj_4.png"));
            reloj_5 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_Arena/reloj_5.png"));
            reloj_6 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_Arena/reloj_6.png"));
            reloj_7 = ImageIO.read(getClass().getResourceAsStream("/img/reloj_Arena/reloj_7.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dibujar (Graphics2D g2){
        
        BufferedImage imagen = null;
        switch (movimiento) {
            case "primera_imagen" -> imagen = reloj_1;
            case "segunda_imagen" -> imagen = reloj_2;
            case "tercera_imagen" -> imagen = reloj_3;
            case "cuarta_imagen" -> imagen = reloj_4;
            case "quinta_imagen" -> imagen = reloj_5;
            case "sexta_imagen" -> imagen = reloj_6;
            case "septima_imagen" -> imagen = reloj_7;
            default -> throw new AssertionError();
        }
        int x_1 = (cj.getTamaño_finalObjeto() / 3) + 45;
        int y_1 = (cj.getTamaño_finalObjeto() * 11) - 11;
        int ancho_1 = cj.getAnchoPantalla() - (cj.getTamaño_finalObjeto() * 18) - 22;
        int largo_1 = cj.getTamaño_finalObjeto() / 2;
        Color c = new Color(0, 0, 0, 180);
        g2.setColor(c);
        g2.fillRoundRect(x_1, y_1, ancho_1, largo_1, 25, 35);
        g2.drawImage(imagen, x, y, cj.getTamaño_finalObjeto(), cj.getTamaño_finalObjeto(), null);
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
