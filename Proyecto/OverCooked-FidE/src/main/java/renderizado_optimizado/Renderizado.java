/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renderizado_optimizado;

/**
 *
 * @author Minor
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Renderizado {
    public BufferedImage imagen_a_Escalar(BufferedImage imagenOriginal, int ancho, int largo) {
        BufferedImage imagenEscalada = new BufferedImage(ancho, largo, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imagenEscalada.createGraphics();
        g2.drawImage(imagenOriginal, 0, 0, ancho, largo, null);
        g2.dispose();
        return imagenEscalada;
    }
}
