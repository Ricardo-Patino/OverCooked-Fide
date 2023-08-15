/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mosaicos;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import juego_restaurante.Componentes_juego;
import renderizado_optimizado.Renderizado;

/**
 *
 * @author Gabriel
 */
public class Manejador_de_Mosaicos {
    Componentes_juego cj;
    public Mosaicos[] mosaico;
    public int numero_de_Mosaico_Mapa[][];
    String archivo = "/mapas_en_texto/mapa_restaurante_1.txt";

    public Manejador_de_Mosaicos(Componentes_juego cj) {
        this.cj = cj;
        mosaico = new Mosaicos[29];
        numero_de_Mosaico_Mapa = new int[cj.tamañoMaximoMundoColumnas][cj.tamañoMaximoMundoFilas];
        getManejador_de_Mosaicos();
        cargarMapaRestuarante(archivo);
    }

    public void cargarMapaRestuarante(String archivo) {
        try {
            InputStream mapa = getClass().getResourceAsStream(archivo);
            // Error del mapa cuando Input Stream esta fuera de try (me tarde 10 min para
            // darme cuenta del error jaja)
            if (mapa == null) {
                throw new IOException("Archivo del mapa no encontrado.");
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(mapa))) {
                int columna = 0;
                int fila = 0;
                String lineas;
                while ((lineas = br.readLine()) != null && columna < cj.tamañoMaximoMundoColumnas
                        && fila < cj.tamañoMaximoMundoFilas) {
                    String numeros[] = lineas.split(" ");

                    for (columna = 0; columna < cj.tamañoMaximoMundoColumnas; columna++) {
                        int num = Integer.parseInt(numeros[columna]);
                        numero_de_Mosaico_Mapa[columna][fila] = num;
                    }
                    columna = 0;
                    fila++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getManejador_de_Mosaicos() {

        estructura(0, "Piso", "cesped_", false);
        estructura(1, "Piso", "piso_madera", false);
        estructura(2, "Piso", "cesped_inferior", true);
        estructura(3, "Piso", "cesped_inferior_derecha", true);
        estructura(4, "Piso", "cesped_inferior_izquierda", true);
        estructura(5, "Piso", "cesped_superior", true);
        estructura(6, "Piso", "cesped_superior_derecha", true);
        estructura(7, "Piso", "cesped_superior_izquierda", true);
        estructura(8, "Piso", "cesped_izquierda", true);
        estructura(9, "Piso", "cesped_derecha", true);
        estructura(10, "Paredes", "pared_adentro", true);
        estructura(11, "Paredes", "pared_afuera", true);
        estructura(12, "Paredes", "pared_lado_izquierdo", true);
        estructura(13, "Paredes", "pared_lado_derecho", true);
        estructura(14, "Escaleras", "escaleras_suben_bajan_clientes_1", false);
        estructura(15, "Escaleras", "escaleras_suben_bajan_clientes_2", false);
        estructura(16, "Sillas_Mesa", "mesa", true);
        estructura(17, "Sillas_Mesa", "silla_alFrente", true);
        estructura(18, "Sillas_Mesa", "silla_derecha", true);
        estructura(19, "Sillas_Mesa", "silla_izquierda", true);
        estructura(20, "Sillas_Mesa", "silla_detras", true);
        estructura(21, "Puerta", "puerta", false);
        estructura(22, "Mesa_atender_comida", "mesa_1_esquina_inferior_derecha", true);
        estructura(23, "Mesa_atender_comida", "mesa_2_esquina_inferior_izquierda", true);
        estructura(24, "Mesa_atender_comida", "mesa_1_izquierda", true);
        estructura(25, "Mesa_atender_comida", "mesa_2_derecha", true);
        estructura(26, "Mesa_atender_comida", "mesa_elCentro_1", true);
        estructura(27, "Mesa_atender_comida", "mesa_elCentro_2", true);
        estructura(28, "Piso", "agua", false);

    }

    public void estructura(int indice, String ruta, String ruta_imagen, boolean colision) {
        Renderizado render = new Renderizado();
        try {
            mosaico[indice] = new Mosaicos();
            mosaico[indice].imagen = ImageIO.read(
                    getClass().getResourceAsStream("/img/img_texturas_mosaicos/" + ruta + "/" + ruta_imagen + ".png"));
            mosaico[indice].imagen = render.imagen_a_Escalar(mosaico[indice].imagen, cj.tamaño_finalObjeto,
                    cj.tamaño_finalObjeto);
            mosaico[indice].colision_conMosaicos = colision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dibujar(Graphics2D g2) {

        int mundoFila = 0;
        int mundoColumna = 0;

        while (mundoColumna < cj.tamañoMaximoMundoColumnas && mundoFila < cj.tamañoMaximoMundoFilas) {

            int numero_especificoMapa = numero_de_Mosaico_Mapa[mundoColumna][mundoFila];
            // Recordar que el JFrame tiene la poscion ubicada como (x, y = 0,0) en
            // la esquina superior derecha

            // mundoX y mundoY es la posicion en el mapa donde se dibujara el mosaico
            int mundoX = mundoColumna * cj.tamaño_finalObjeto;
            int mundoY = mundoFila * cj.tamaño_finalObjeto;
            // Esto funciona como la camara del jugador acorde a la posicion del mismo en el
            // mundoX y mundoY
            int pantallaDibujarX = mundoX - cj.jugador.planoMundoX + cj.jugador.pantallaX;
            int pantallaDibujarY = mundoY - cj.jugador.planoMundoY + cj.jugador.pantallaY;

            // Usa un muro invisible para dibujar alrededor jugador
            // los mosaicos tal como una camara
            if (mundoX + cj.tamaño_finalObjeto > cj.jugador.planoMundoX - cj.jugador.pantallaX &&
                    mundoX - cj.tamaño_finalObjeto < cj.jugador.planoMundoX + cj.jugador.pantallaX &&
                    mundoY + cj.tamaño_finalObjeto > cj.jugador.planoMundoY - cj.jugador.pantallaY &&
                    mundoY - cj.tamaño_finalObjeto < cj.jugador.planoMundoY + cj.jugador.pantallaY) {
                    g2.drawImage(mosaico[numero_especificoMapa].imagen, pantallaDibujarX, pantallaDibujarY, null);
            }

            mundoColumna++;
            if (mundoColumna == cj.tamañoMaximoMundoColumnas) {
                mundoColumna = 0;
                mundoFila++;
            }
        }
    }
}
