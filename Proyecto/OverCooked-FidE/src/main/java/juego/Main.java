/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;


import java.awt.Color;
import javax.swing.JFrame;
import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */
public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("OverCooked");
        
        Componentes_juego juego = new Componentes_juego();
        
        ventana.add(juego);
        ventana.pack();
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        juego.iniciarThreadJuego();
    }
}
