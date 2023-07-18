/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego_restaurante;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import entidades.Jugador;
import entidades.Reloj_arena;
import tecleado_juego.Teclado;


/**
 *
 * @author Gabriel
 */

public class Componentes_juego extends JPanel implements Runnable {
    // Objeto de 16x16 pixeles
    final int tamaño_originalObjeto=16;
    final int escalar_a_Pantalla=3;
    
    // Objeto de 48x48 pixeles
    public final int tamaño_finalObjeto=tamaño_originalObjeto * escalar_a_Pantalla;
    final int tamañoMaximoPantallaColumnas = 16;
    final int tamañoMaximoPantallaFilas = 12;
    final int anchoPantalla=tamaño_finalObjeto*tamañoMaximoPantallaColumnas; //768 pixeles
    final int largoPantalla=tamaño_finalObjeto*tamañoMaximoPantallaFilas; //576 pixeles
        
    Teclado tecla = new Teclado();
    Thread realizarThread;
    Jugador jugador = new Jugador(this, tecla);
    Reloj_arena reloj = new Reloj_arena(this);
    
    int FPS = 60;
    
    public Componentes_juego(){
        this.setPreferredSize(new Dimension(anchoPantalla, largoPantalla));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(tecla);
        this.setFocusable(true);
    }
    
    // X:0 y Y:0 estan en la esquina superior izquierda del panel
    //Los valores de X se incrementan hacia la derecha
    //Los valores de Y se incrementan hacia abajo
    public void update(){
        jugador.update();
        reloj.update();
    }
    
    public void iniciarThreadJuego(){
        realizarThread = new Thread (this);
        realizarThread.start();
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        jugador.dibujar(g2);
        reloj.dibujar(g2);
        g2.dispose();
        
    }
    
    
    @Override
    public void run() {
        
        double intervaloTiempo = 1000000000/FPS; //equivale a 0.1666 segundos
        double cambioVelocidadTiempo = 0;
        long tiempoAnterior = System.nanoTime() ;
        long tiempoActual;
        long temporizador = 0;
        int contador = 0;
        
        while (realizarThread!= null){
            
            tiempoActual = System.nanoTime();
            cambioVelocidadTiempo +=(tiempoActual - tiempoAnterior)/intervaloTiempo;
            temporizador += (tiempoActual - tiempoAnterior);
            tiempoAnterior = tiempoActual;
            
            if (cambioVelocidadTiempo >= 1){
                update();  
                repaint();
                cambioVelocidadTiempo--;
                contador++;
            }
            
            if (temporizador >= 1000000000){
                System.out.println("FPS: "+contador);
                contador =0;
                temporizador=0;
            }
            
        }
    }
    
}
