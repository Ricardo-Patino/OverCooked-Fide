/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantalla_UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entidades.Entidades;
import juego_restaurante.Componentes_juego;

/**
 *
 * @author Gabriel
 */
public class UI {
    
    Componentes_juego cj;
    Graphics2D g2;
    Font pixelatedPusab_, dogicabold, arialFont, videogameFont;

    public boolean finDelJuego = false;
    public String dialogoAcutal = "";
    public int numeroComando = 0;

    public int espacioColumna= 0;
    public int espacioFila= 0;
    public int obtenerEspacioVacio=0;;

    public int cursorX=0;
    public int cursorY=0;
    public int cursorAncho=0;
    public int cursorLargo=0;
    int cantidadOrdenes =0;

    public Entidades npc;

    int indiceCaracteres = 0;
    String textoCombinado = "";

    private ArrayList<BufferedImage> wallpaperFrames;
    private int actualFrameIndex = 0;
    private int animacionDelay = 120;
    private long ultimoFrameTiempo = System.currentTimeMillis();
    int numFrames = 17;

    public UI(Componentes_juego cj) {
        this.cj = cj;
        arialFont = new Font("Arial", Font.PLAIN, 10);
        videogameFont= new Font("VideoGame" , Font.PLAIN,12);
        try {
            InputStream in = getClass().getResourceAsStream("/fuente/PixelatedPusab.ttf");
            InputStream in2 = getClass().getResourceAsStream("/fuente/dogicabold.ttf");
            pixelatedPusab_ = Font.createFont(Font.TRUETYPE_FONT, in);
            dogicabold = Font.createFont(Font.TRUETYPE_FONT, in2);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        wallpaperFrames = new ArrayList<>();
        for (int i = 0; i < numFrames; i++) {
            String framePath = "/animacion_background/16_9/" + i + ".png";
            try {
                InputStream frameStream = getClass().getResourceAsStream(framePath);
                BufferedImage frame = ImageIO.read(frameStream);
                wallpaperFrames.add(frame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void dibujar(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(pixelatedPusab_);
        g2.setColor(Color.BLACK);
        if (cj.getEstadoJuego() == cj.getEstadoTitulo()) {
            dibujarPantallaInicio();
        }
        if (cj.getEstadoJuego() == cj.getJuegoIniciar()) {
            if (cj.getTecla().isPresionado_E() == true) {
                
            }else{
                dibujarBotonIniciarPartida();
            }
        }
        if (cj.getEstadoJuego() == cj.getJuegoDialogos()) {
            dibujarPantallaDialogos();
        }
        if (cj.getEstadoJuego() == cj.getEstadoJugador()) {
            dibujarPantallaJugador();
        }
        if (cj.getEstadoJuego() == cj.getEstadoFinalJuego()) {
            terminoElJuego();
        }
    }

    public void terminoElJuego(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, cj.getAnchoPantalla(), cj.getLargoPantalla());
        int x;
        int y;
        String texto; 
        g2.setFont(videogameFont.deriveFont(Font.BOLD,50f));
        String valorPuntos = String.valueOf(cj.getJugador().getPuntos());
        String valorHamburguesasHechas= String.valueOf(cj.getJugador().getHamburgesas_hechas());
        texto = "Has Obtenido ["+valorPuntos+"] Puntos\nCon ["+valorHamburguesasHechas+"] Hamburgesas Hechas";
        g2.setColor(Color.white);
        x=obtenerX_Centrar_Texto(texto);
        y=cj.getTamaño_finalObjeto()*4;
        for (String linea : texto.split("\n")) {
            g2.drawString(linea, x+350, y);
            if (cj.getJugador().getHamburgesas_hechas()<10){
                x-=60;
            }
            x-=50;
            y += 100;
        }
    }

    public void dibujarOrdenesMostrar(Graphics2D g2, String ordenes[], int posicion, 
        int numRandom, String numReferencia, Font videoGameFont){
        final int frameX= cj.getTamaño_finalObjeto()*posicion-20;
        final int frameY=cj.getTamaño_finalObjeto()*8-20;
        final int frameAncho= cj.getTamaño_finalObjeto()*4;
        final int frameLargo=cj.getTamaño_finalObjeto()*4;
        dibujar_SUB_Pantalla(frameX, frameY, frameAncho, frameLargo);    
        g2.setFont(videoGameFont.deriveFont(12F));
        g2.setColor(new Color(255, 255, 255, 220)); 
        String tiqueteActual=ordenes[numRandom];
        String referencia = "Orden #"+numReferencia+"\n";
        int textoX = frameX +20;
        int textoY = frameY + cj.getTamaño_finalObjeto();
        for (String linea : referencia.split("\n")) {
            g2.drawString(linea, textoX+50, textoY-20);
        }
        for (String linea : tiqueteActual.split("\n")) {
            g2.drawString(linea, textoX, textoY+30);
            textoY += 20;
        }
    }

    public void dibujarInventarioIngredientes(Graphics2D g2){
        //Frame
        int frameX= cj.getTamaño_finalObjeto()*13-20;
        int frameY=cj.getTamaño_finalObjeto()/2;
        int frameAncho= cj.getTamaño_finalObjeto()*6+10;
        int frameLargo=cj.getTamaño_finalObjeto()*2;
        dibujar_SUB_Pantalla(frameX, frameY, frameAncho, frameLargo);
        //Espacio Vacio
        final int espacio_X_iniciar= frameX+20;
        final int espacio_Y_iniciar= frameY+20;
        int espacioTamaño = cj.getTamaño_finalObjeto()+5;
        int espacioX = espacio_X_iniciar+212;
        int espacioY = espacio_Y_iniciar;
        // Jugador ingredientes (items)
        if (cj.getJugador().getInventarioIngredientesMostrarJugador().getTamaño() != 0) {
            for (int i = cj.getJugador().getInventarioIngredientesMostrarJugador().getTamaño() - 1; i >= 0; i--) {
                g2.drawImage(cj.getJugador().getInventarioIngredientesMostrarJugador().get(i).getAbajo_2(), espacioX, espacioY, null);
                espacioX-=espacioTamaño;
            }
        }    
        //BOTON BOTAR INGREDIENTES
        int boton_X_1=frameX+50;
        int boton_Y_1=frameY*5+20;
        int boton_Ancho_1=frameAncho-100;
        int boton_Largo_1=frameLargo/2+40;
        g2.setFont(dogicabold.deriveFont(Font.PLAIN, 8));
        String texto = "Para elimnar un\ningrediente presione\n(ENTER)";
        

        dibujar_SUB_Pantalla(boton_X_1, boton_Y_1, boton_Ancho_1, boton_Largo_1);
        g2.setColor(new Color(255, 255, 255));
        for (String linea : texto.split("\n")) {
            
            g2.drawString(linea, obtenerX_Centrar_Boton(linea,boton_X_1)+100, boton_Y_1+33);
            boton_Y_1 += 15;
        }
        //BOTON BOTAR INGREDIENTES Y AGREGAR PUNTOS EN (pan, lechuga, etc...)
        int boton_X_2=frameX+50;
        int boton_Y_2=frameY*9+20;
        int boton_Ancho_2=frameAncho-100;
        int boton_Largo_2=frameLargo/2+40;
        g2.setFont(dogicabold.deriveFont(Font.PLAIN, 8));
        String texto2 = "Para agregar un\ningrediente presione\n(C)";
        dibujar_SUB_Pantalla(boton_X_2, boton_Y_2, boton_Ancho_2, boton_Largo_2);
        g2.setColor(new Color(255, 255, 255));
        for (String linea : texto2.split("\n")) {
            g2.drawString(linea, obtenerX_Centrar_Boton(linea,boton_X_2)+100, boton_Y_2+33);
            boton_Y_2 += 15;
        }

        //Cursor (Ubicacion Item)
        cursorX= espacio_X_iniciar+(espacioTamaño*espacioColumna);
        cursorY= espacio_Y_iniciar+(espacioTamaño*espacioFila);
        cursorAncho = cj.getTamaño_finalObjeto();
        cursorLargo = cj.getTamaño_finalObjeto();
        
        g2.setColor(new Color(204, 153, 51));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(cursorX, cursorY, cursorAncho, cursorLargo, 10,10);
    }

    public int getNumeroIngrediente(){
        int num=-1;
        if (cj.getJugador().getInventarioIngredientesMostrarJugador().getTamaño() == 0) {
            return num;
        }  
        for (int i = cj.getJugador().getInventarioIngredientesMostrarJugador().getTamaño() - 1; i >= 0; i--) {
            if(espacioColumna==i){
                if (cj.getJugador().getInventarioIngredientesMostrarJugador().get(i).getNombre_ingredientes()=="Ingediente__Carne"){
                    num=1;
                }
                if (cj.getJugador().getInventarioIngredientesMostrarJugador().get(i).getNombre_ingredientes()=="Ingediente__Queso"){
                    num=2;
                }
                if (cj.getJugador().getInventarioIngredientesMostrarJugador().get(i).getNombre_ingredientes()=="Ingediente__Lechuga"){
                    num=3;
                }
                if (cj.getJugador().getInventarioIngredientesMostrarJugador().get(i).getNombre_ingredientes()=="Ingediente__Pan"){
                    num=4;
                }
            }
            
        }
        return num;
    }

    public void dibujarPantallaJugador(){
        final int frameX= cj.getTamaño_finalObjeto();
        final int frameY=cj.getTamaño_finalObjeto()/2;
        final int frameAncho= cj.getTamaño_finalObjeto()*7;
        final int frameLargo=cj.getTamaño_finalObjeto()*5;
        dibujar_SUB_Pantalla(frameX, frameY, frameAncho, frameLargo);
        g2.setColor(Color.white);
        g2.setFont(dogicabold.deriveFont(11F));
        int textoX = frameX +20;
        int textoY = frameY + cj.getTamaño_finalObjeto();
        final int alturaLinea = 23;

        g2.drawString("Nombre", textoX, textoY);
        textoY += alturaLinea;
        g2.drawString("Ordenes Listas", textoX, textoY);
        textoY += alturaLinea;
        g2.drawString("Puntos", textoX, textoY);
        textoY += alturaLinea;
        g2.drawString("Pan", textoX, textoY);
        textoY += alturaLinea;
        g2.drawString("Lechuga", textoX, textoY);
        textoY += alturaLinea;
        g2.drawString("Carne", textoX, textoY);
        textoY += alturaLinea;
        g2.drawString("Queso", textoX, textoY);
        textoY += alturaLinea;

        int finalX = (frameX + frameAncho) - 30;
        textoY = frameY + cj.getTamaño_finalObjeto();
        String valor;

        valor = String.valueOf(cj.getJugador().getNombre_jugador());
        textoX = obtenerX_Alinear_Derecha(valor, finalX);
        g2.drawString(valor, textoX, textoY);
        textoY += alturaLinea;

        valor = String.valueOf(cj.getJugador().getHamburgesas_hechas());
        textoX = obtenerX_Alinear_Derecha(valor, finalX);
        g2.drawString(valor, textoX, textoY);
        textoY += alturaLinea;

        valor = String.valueOf(cj.getJugador().getPuntos());
        textoX = obtenerX_Alinear_Derecha(valor, finalX);
        g2.drawString(valor, textoX, textoY);
        textoY += alturaLinea;

        valor = String.valueOf(cj.getJugador().getValor_pan());
        textoX = obtenerX_Alinear_Derecha(valor, finalX);
        g2.drawImage(cj.getJugador().getObjeto_ver_pan().getAbajo_1(), textoX-180, textoY-15, null);
        g2.drawString(valor, textoX, textoY);
        textoY += alturaLinea;

        valor = String.valueOf(cj.getJugador().getValor_lechuga());
        textoX = obtenerX_Alinear_Derecha(valor, finalX);
        g2.drawImage(cj.getJugador().getObjeto_ver_lechuga().getAbajo_1(), textoX-180, textoY-15, null);
        g2.drawString(valor, textoX, textoY);
        textoY += alturaLinea;

        valor = String.valueOf(cj.getJugador().getValor_carne());
        textoX = obtenerX_Alinear_Derecha(valor, finalX);
        g2.drawImage(cj.getJugador().getObjeto_ver_carne().getAbajo_1(), textoX-180, textoY-15, null);
        g2.drawString(valor, textoX, textoY);
        textoY += alturaLinea;

        valor = String.valueOf(cj.getJugador().getValor_queso());
        textoX = obtenerX_Alinear_Derecha(valor, finalX);
        g2.drawImage(cj.getJugador().getObjeto_ver_queso().getAbajo_1(), textoX-180, textoY-15, null);
        g2.drawString(valor, textoX, textoY);
        textoY += alturaLinea;

    }

    public void dibujarPantallaInicio() {
        g2.setFont(pixelatedPusab_.deriveFont(Font.BOLD, 80F));
        String texto = "OverCooked";
        int x = obtenerX_Centrar_Texto(texto);
        int y = cj.getTamaño_finalObjeto() * 3;
        backgroundAnimado();
        colorTextoInicio(texto, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        texto = "INICIAR JUEGO";
        x = obtenerX_Centrar_Texto(texto);
        y += cj.getTamaño_finalObjeto() * 4;
        colorTextoInicio(texto, x, y);
        if (numeroComando == 0) {
            g2.setColor(new Color(164, 156, 166));
            g2.drawString(">", x - cj.getTamaño_finalObjeto(), y);
            g2.setColor(new Color(38, 38, 38));
            g2.drawString(">", x - cj.getTamaño_finalObjeto() - 5, y - 5);

        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        texto = "SALIR";
        x = obtenerX_Centrar_Texto(texto);
        y += cj.getTamaño_finalObjeto();
        colorTextoInicio(texto, x, y + 10);
        if (numeroComando == 1) {
            g2.setColor(new Color(164, 156, 166));
            g2.drawString(">", x - cj.getTamaño_finalObjeto(), y + 10);
            g2.setColor(new Color(38, 38, 38));
            g2.drawString(">", x - cj.getTamaño_finalObjeto() - 5, y + 5);

        }
    }

    public void dibujarBotonIniciarPartida() {
        g2.setFont(dogicabold.deriveFont(Font.BOLD, 10));
        String texto = "Para iniciar presione (E)";
        int x = obtenerX_Centrar_Texto(texto);
        int y = cj.getTamaño_finalObjeto() * 4;
        int ancho_ = cj.getAnchoPantalla() - (cj.getTamaño_finalObjeto() * 14);
        int largo_ = cj.getTamaño_finalObjeto() / 1;
        dibujar_SUB_Pantalla(x - 20, y, ancho_, largo_);
        g2.setColor(new Color(255, 255, 255));
        g2.drawString(texto, x, y + 28);
    }

    public void backgroundAnimado() {
        int x = 0;
        int y = 0;
        BufferedImage actualFrame = wallpaperFrames.get(actualFrameIndex);
        g2.drawImage(actualFrame, x, y, null);

        long currentTime = System.currentTimeMillis();
        if (currentTime - ultimoFrameTiempo >= animacionDelay) {
            actualFrameIndex = (actualFrameIndex + 1) % wallpaperFrames.size();
            ultimoFrameTiempo = currentTime;
        }
    }

    public void dibujarPantallaDialogos() {
        int x = (cj.getTamaño_finalObjeto() * 4) + 40;
        int y = cj.getTamaño_finalObjeto() / 2;
        int ancho_ = cj.getAnchoPantalla() - (cj.getTamaño_finalObjeto() * 10);
        int largo_ = cj.getTamaño_finalObjeto() * 4;
        dibujar_SUB_Pantalla(x, y, ancho_, largo_);
        g2.setFont(videogameFont.deriveFont(15F));
        g2.setColor(new Color(255, 255, 255));
        x += cj.getTamaño_finalObjeto();
        y += cj.getTamaño_finalObjeto();

        if (npc.getDialogos()[npc.getDialogosPaginado()][npc.getDialogosIndex()] != null) {
            // dialogoAcutal= npc.dialogos[npc.dialogosPaginado][npc.dialogosIndex];

            char caracteres[] = npc.getDialogos()[npc.getDialogosPaginado()][npc.getDialogosIndex()].toCharArray();

            if (indiceCaracteres < caracteres.length) {
                String s = String.valueOf(caracteres[indiceCaracteres]);
                textoCombinado = textoCombinado + s;
                dialogoAcutal = textoCombinado;
                indiceCaracteres++;
            }

            if (cj.getTecla().isPresionadoEnter_dialogos() == true) {

                indiceCaracteres = 0;
                textoCombinado = "";
                if (cj.getEstadoJuego() == cj.getJuegoDialogos()) {
                    int indexFast=  npc.getDialogosIndex();
                    npc.setDialogosIndex(indexFast+=1);
                    cj.getTecla().setPresionadoEnter_dialogos(false);
                }
            }
        } else {
            npc.setDialogosIndex(0);
            if (cj.getEstadoJuego() == cj.getJuegoDialogos()) {
                cj.setEstadoJuego(cj.getJuegoIniciar());
                for (int i=0 ; i<cj.getNpc().length ; i++) {
                    if (cj.getNpc()[i] != null){
                        cj.getNpc()[i].setMovimiento("abajo");
                    }
                }
            }
        }

        for (String linea : dialogoAcutal.split("\n")) {
            g2.drawString(linea, x - 10, y);
            y += 40;
        }
    }

    public void dibujar_SUB_Pantalla(int x, int y, int ancho_, int largo_) {
        Color c = new Color(0, 0, 0, 180);
        g2.setColor(c);
        g2.fillRoundRect(x, y, ancho_, largo_, 35, 35);
        c = new Color(146, 88, 21);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, ancho_ - 10, largo_ - 10, 25, 25);
    }

    public int obtenerX_Centrar_Texto(String texto) {
        int length = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = cj.getAnchoPantalla() / 2 - length / 2;
        return x;
    }

    public int obtenerX_Centrar_Boton(String texto, int x) {
        int length_boton_1 = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int boton_size_X_1 = (x - length_boton_1/2);
        return boton_size_X_1;
    }

    public int obtenerX_Alinear_Derecha(String texto, int finalX) {
        int length = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = finalX - length;
        return x;
    }

    public void colorTextoInicio(String texto, int x, int y) {
        g2.setColor(new Color(0, 0, 0));
        g2.drawString(texto, x, y);
        g2.setColor(new Color(255, 218, 30));
        g2.drawString(texto, x - 5, y - 5);
    }
}
