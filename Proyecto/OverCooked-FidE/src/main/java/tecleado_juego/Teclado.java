/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tecleado_juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */
public class Teclado implements KeyListener {
    Componentes_juego cj;
    public boolean arriba, abajo, izquierda, derecha,
            presionadoEnter_dialogos, presionadoEscape, presionado_E = false, presionado_R = false,
            presionadoEnter_ingrediente=false,presionado_C_ingrediente=false;

    public Teclado(Componentes_juego cj) {
        this.cj = cj;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void estadoTituloTeclado(int presionado) {
        if (presionado == KeyEvent.VK_W || presionado == KeyEvent.VK_UP) {
            cj.ui.numeroComando--;
            if (cj.ui.numeroComando < 0) {
                cj.ui.numeroComando = 2;
            }
        }
        if (presionado == KeyEvent.VK_S|| presionado == KeyEvent.VK_DOWN) {
            cj.ui.numeroComando++;
            if (cj.ui.numeroComando > 2) {
                cj.ui.numeroComando = 0;
            }
        }
        
        if (presionado == KeyEvent.VK_ENTER) {
            if (cj.ui.numeroComando == 0) {
                cj.estadoJuego = cj.juegoIniciar;
                //cj.pararMusica();
                //cj.iniciarMusica(0);
                //cj.ponerVolumenTema((float) 0.7);
                presionadoEscape = false;
            }
            if (cj.ui.numeroComando == 1) {
                System.exit(0);
                
            }
            if (cj.ui.numeroComando == 2) {
                //HACER ALGO
            }
        }
    }

    public void estadoIniciarTeclado(int presionado) {
        if (presionado == KeyEvent.VK_W) {
            arriba = true;
        }
        if (presionado == KeyEvent.VK_S) {
            abajo = true;
        }
        if (presionado == KeyEvent.VK_A) {
            izquierda = true;
        }
        if (presionado == KeyEvent.VK_D) {
            derecha = true;
        }
        if (presionado == KeyEvent.VK_ENTER) {
            presionadoEnter_dialogos = true;
        }
        if (presionado == KeyEvent.VK_ESCAPE) {
            cj.pararMusica();
            cj.iniciarMusica(4);
            cj.ponerVolumenTema((float) 0.7);
            presionadoEscape = true;
            cj.estadoJuego = cj.estadoTitulo;
        }
        if (presionado == KeyEvent.VK_E) {
            presionado_E = true;
        }
        if (presionado == KeyEvent.VK_R&& presionado_E==true){
            presionado_R=true;
            cj.estadoJuego=cj.estadoJugador;
        }
    }

    public void estadoDialogosTeclado(int presionado) {
        if (presionado == KeyEvent.VK_ENTER) {
            presionadoEnter_dialogos = true;

        }
    }

    public void estadoVerJugador(int presionado) {
        if (presionado == KeyEvent.VK_R && presionado_E==true) {
            cj.estadoJuego = cj.juegoIniciar;
        }
        if (presionado == KeyEvent.VK_A && presionado_E==true){
            if (cj.ui.espacioColumna!=0){
                cj.ui.espacioColumna--;
                cj.usarEfecto_Sonido(10);
            }
        }
        if (presionado == KeyEvent.VK_D && presionado_E==true){
            if (cj.ui.espacioColumna!=4){
                cj.ui.espacioColumna++;
                cj.usarEfecto_Sonido(10);
            }
        }
        if (presionado == KeyEvent.VK_ENTER && presionado_E==true){
            presionadoEnter_ingrediente=true;
        }
        if (presionado == KeyEvent.VK_C && presionado_E==true){
            presionado_C_ingrediente=true;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int presionado = e.getKeyCode();

        if (cj.estadoJuego == cj.estadoTitulo) {
            estadoTituloTeclado(presionado);
        }

        if (cj.estadoJuego == cj.juegoIniciar) {
            estadoIniciarTeclado(presionado);

        } else if (cj.estadoJuego == cj.juegoDialogos) {
            estadoDialogosTeclado(presionado);
        }else if (cj.estadoJuego == cj.estadoJugador) {
            estadoVerJugador(presionado);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int presionado = e.getKeyCode();

        if (presionado == KeyEvent.VK_W) {
            arriba = false;
        }
        if (presionado == KeyEvent.VK_S) {
            abajo = false;
        }
        if (presionado == KeyEvent.VK_A) {
            izquierda = false;
        }
        if (presionado == KeyEvent.VK_D) {
            derecha = false;
        }

    }
}
