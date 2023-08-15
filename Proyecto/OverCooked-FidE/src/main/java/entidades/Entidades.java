/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import juego_restaurante.Componentes_juego;
import renderizado_optimizado.Renderizado;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Gabriel
 */
public class Entidades {
    Componentes_juego cj;
    // Plano del mundo X y Y
    public int planoMundoX, planoMundoY;
    public int velocidad;
    // Imagenes del personaje
    public BufferedImage abajo_1, abajo_2, abajo_3, abajo_4,
            arriba_1, arriba_2, arriba_3, arriba_4,
            derecha_1, derecha_2, derecha_3, derecha_4,
            izquierda_1, izquierda_2, izquierda_3, izquierda_4;
    // Posicion a cambiar del personaje
    public String movimiento = "abajo";
    // Un contador para patrones cada cada 60 frames por segundo
    public int contadorPatrones = 0;
    // Cambiador del numero de patrones para escoger entre estados
    // En el que esta la animacion del jugador
    public int numPatrones = 1;
    public Rectangle areaEntidades_colision = new Rectangle(0, 0, 48, 48);
    public int areaPorDEFECTO_colisionX, areaPorDEFECTO_colisionY;
    public boolean hayColisiones = false;

    public BufferedImage imagen;
    public String nombre;
    public String nombre_ingredientes;
    public boolean colisones = false;

    public int bloqueoDeAccionesporSegundo = 0;
    public String dialogos[][] = new String[20][20];
    public int dialogosIndex = 0;
    public int dialogosPaginado = 0;

    public int tipo;
    public String nombre_jugador;
    public int hamburgesas_hechas;
    public int puntos;
    public Entidades objeto_ver_pan;
    public Entidades objeto_ver_lechuga;
    public Entidades objeto_ver_carne;
    public Entidades objeto_ver_queso;
    public int valor_pan;
    public int valor_lechuga;
    public int valor_carne;
    public int valor_queso;


    public void iniciarAccionNPC() {
    }

    public void hablaNPC() {

    }

    public void mirarJugador() {
        movimiento = switch (cj.jugador.movimiento) {
            case "arriba" -> "abajo";
            case "abajo" -> "arriba";
            case "izquierda" -> "derecha";
            case "derecha" -> "izquierda";
            default -> throw new AssertionError();
        };
    }

    public void iniciarDialogo(Entidades entidades, int setNumero) {
        cj.estadoJuego = cj.juegoDialogos;
        cj.ui.npc = entidades;
        dialogosPaginado = setNumero;
    }

    public void update() {
        iniciarAccionNPC();
        hayColisiones = false;
        cj.dc.dectarMosaicoEntidad(this);
        cj.dc.dectarObjetoEntidad(this, false);
        cj.dc.verEntidadJUGADOR(this);
        // if (hayColisiones == false) {
        //     switch (movimiento) {
        //         case "arriba" -> planoMundoY -= velocidad;
        //         case "abajo" -> planoMundoY += velocidad;
        //         case "izquierda" -> planoMundoX -= velocidad;
        //         case "derecha" -> planoMundoX += velocidad;
        //         default -> throw new AssertionError();
        //     }
        // }

        // contadorPatrones++;
        // if (contadorPatrones > 13) {
        //     switch (numPatrones) {
        //         case 1 -> numPatrones = 2;
        //         case 2 -> numPatrones = 3;
        //         case 3 -> numPatrones = 4;
        //         case 4 -> numPatrones = 1;
        //         default -> {
        //         }
        //     }
        //     contadorPatrones = 0;
        // }
    }

    public Entidades(Componentes_juego cj) {
        this.cj = cj;
    }

    public void dibujar(Graphics2D g2) {
        int pantallaDibujarX = planoMundoX - cj.jugador.planoMundoX + cj.jugador.pantallaX;
        int pantallaDibujarY = planoMundoY - cj.jugador.planoMundoY + cj.jugador.pantallaY;

        if (planoMundoX + cj.tamaño_finalObjeto > cj.jugador.planoMundoX - cj.jugador.pantallaX &&
                planoMundoX - cj.tamaño_finalObjeto < cj.jugador.planoMundoX + cj.jugador.pantallaX &&
                planoMundoY + cj.tamaño_finalObjeto > cj.jugador.planoMundoY - cj.jugador.pantallaY &&
                planoMundoY - cj.tamaño_finalObjeto < cj.jugador.planoMundoY + cj.jugador.pantallaY) {
            BufferedImage imagen = null;
            switch (movimiento) {
                case "arriba" -> {
                    if (numPatrones == 1) {
                        imagen = arriba_1;
                    }
                    if (numPatrones == 2) {
                        imagen = arriba_2;
                    }
                    if (numPatrones == 3) {
                        imagen = arriba_3;
                    }
                    if (numPatrones == 4) {
                        imagen = arriba_4;
                    }
                }
                case "abajo" -> {
                    if (numPatrones == 1) {
                        imagen = abajo_1;
                    }
                    if (numPatrones == 2) {
                        imagen = abajo_2;
                    }
                    if (numPatrones == 3) {
                        imagen = abajo_3;
                    }
                    if (numPatrones == 4) {
                        imagen = abajo_4;
                    }
                }
                case "izquierda" -> {
                    if (numPatrones == 1) {
                        imagen = izquierda_1;
                    }
                    if (numPatrones == 2) {
                        imagen = izquierda_2;
                    }
                    if (numPatrones == 3) {
                        imagen = izquierda_3;
                    }
                    if (numPatrones == 4) {
                        imagen = izquierda_4;
                    }
                }
                case "derecha" -> {
                    if (numPatrones == 1) {
                        imagen = derecha_1;
                    }
                    if (numPatrones == 2) {
                        imagen = derecha_2;
                    }
                    if (numPatrones == 3) {
                        imagen = derecha_3;
                    }
                    if (numPatrones == 4) {
                        imagen = derecha_4;
                    }
                }
                default -> throw new AssertionError();
            }
            g2.drawImage(imagen, pantallaDibujarX,
                    pantallaDibujarY, null);
        }
    }

    public BufferedImage estructura(String ruta, String nombreImagen, int ancho, int largo) {
        Renderizado render = new Renderizado();
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream("/img" + ruta + "/" + nombreImagen + ".png"));
            imagen = render.imagen_a_Escalar(imagen, ancho, largo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagen;
    }

}
