/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego_restaurante;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.management.timer.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import colisiones.DetectorColisiones;
import entidades.Entidades;
import entidades.Jugador;
import juego.Main;
import lista_Ingredientes.InventarioItem;
import lista_Ingredientes.ListaCircular;
import lista_Ingredientes.Manejador_Ingredientes;
import mosaicos.Manejador_de_Mosaicos;
import objetos_diferentes.Reloj_arena;
import ordenes.Cola;
import ordenes.Nodo;
import ordenes.Tiquetes_Ordenes;
import pantalla_UI.UI;
import recursos_objetos.Recursos_Incializar;
import sonidos_usar.Musica;
import tecleado_juego.Teclado;

/**
 *
 * @author Gabriel
 */

public class Componentes_juego extends JPanel implements Runnable {
    // Objeto de 16x16 pixeles
    final int tamaño_originalObjeto = 16;
    final int escalar_a_Pantalla = 3;

    // Objeto de 48x48 pixeles
    public final int tamaño_finalObjeto = tamaño_originalObjeto * escalar_a_Pantalla;

    // Pantalla 16:12 == 4:3, 20:12 == 16:9
    public final int tamañoMaximoPantallaColumnas = 20; // 20 , 16
    public final int tamañoMaximoPantallaFilas = 12;
    public final int anchoPantalla = tamaño_finalObjeto * tamañoMaximoPantallaColumnas; // 768 pixeles si son 16 tMPC,
                                                                                        // si 20 tMPC pixeles son 960
    public final int largoPantalla = tamaño_finalObjeto * tamañoMaximoPantallaFilas; // 576 pixeles

    // TAMAÑO DEL MAPA 69x40
    public final int tamañoMaximoMundoColumnas = 69;
    public final int tamañoMaximoMundoFilas = 40;

    int anchoPantalla2 = anchoPantalla;
    int largoPantalla2 = largoPantalla;

    BufferedImage pantallaTemporal;
    Graphics2D g2;

    int FPS = 60;
    
    private long lastTimeReset = System.currentTimeMillis();
    private long lastTimeReset_2 = System.currentTimeMillis();
    private long lastTimeReset_3 = System.currentTimeMillis();
    public long lastTimeReset_4 = System.currentTimeMillis();

    private final long RESET_INTERVAL = 1000; // 20 segundos en milisegundos
    public final long RESET_INTERVAL_2 = 3000; // 20 segundos en milisegundos

    private boolean timeIsUp = false;
    public int minutes = 5;
    public int seconds = 0;
    int seconds_2=0;
    // SISTEMA
    private Timer timer;
    public Manejador_de_Mosaicos mosaicos = new Manejador_de_Mosaicos(this);
    public Teclado tecla = new Teclado(this);
    Musica musicaTema = new Musica();
    Musica musicaEfectoSonido = new Musica();
    public DetectorColisiones dc = new DetectorColisiones(this);
    public Recursos_Incializar rec_obj = new Recursos_Incializar(this);
    public UI ui = new UI(this);
    public Thread realizarThread;
    Random random = new Random();

    // Tiene 12 objetos que se pueden renderizar al
    // mismo tiempo no significa que solo se puede almacenar 10 objetos
    // OBJETOS Y ENTIDADES    
    public Entidades obj[] = new Entidades[12];
    public Jugador jugador = new Jugador(this, tecla);
    public Entidades npc[] = new Entidades[10];
    ArrayList<Entidades> listaEntidades = new ArrayList<>();
    //OTROS OBJETOS
    Reloj_arena reloj = new Reloj_arena(this);
    //COLAS PARA TIQUETES
    public Cola cola = new Cola();
    public Nodo nodoEliminar = null;
    public Tiquetes_Ordenes primerEncolado = null;

    //Lista Circular
    public ListaCircular lista = new ListaCircular();
    public Manejador_Ingredientes manejar_ingredientes =new Manejador_Ingredientes(this, -1);

    // Inicio, etc...
    public int estadoJuego;
    public final int estadoTitulo = 0;
    public final int juegoIniciar = 1;
    public final int juegoDialogos = 2;
    public final int estadoJugador = 3;
    
    private int counter = 0;
    private int counter_2=0;
    private int counter_obj=0;
    private int posicionAnterior=-1;
    private int posicionActual=8;

    //Numero de orden
    private int referencia = 0;
    //numero de la orden a realizar (ordenes[0]...)
    private int numeroAnterior = -1; 
    private int numeroRandom = random.nextInt(3);

    private int numeroAnterior_2 = -1; 
    private int numeroRandom_2 = random.nextInt(4);

    //Especificaciones de la pantalla del juego
    public Componentes_juego() {
        this.setPreferredSize(new Dimension(anchoPantalla, largoPantalla));
        this.setDoubleBuffered(true);
        this.addKeyListener(tecla);
        this.setFocusable(true); 
    }

    // X:0 y Y:0 estan en la esquina superior izquierda del panel
    // Los valores de X se incrementan hacia la derecha
    // Los valores de Y se incrementan hacia abajo
    public void update() throws InterruptedException {
        if (estadoJuego == juegoIniciar) {
            jugador.update();
            reloj.update();
            for (Entidades npc1 : npc) {
                if (npc1 != null) {
                    npc1.update();
                }
            }
        }
    }

    public void iniciarThreadJuego() {
        realizarThread = new Thread(this);
        realizarThread.start();
    }
    
    public void iniciar_en_Juego() {
        rec_obj.setObjeto_Puerta();
        rec_obj.setObjeto();
        rec_obj.setNPC();
        estadoJuego = estadoTitulo;
        if (estadoJuego == estadoTitulo) {
            //iniciarMusica(4);
            //ponerVolumenTema((float) 0.7);
        }
        pantallaTemporal = new BufferedImage(anchoPantalla, largoPantalla, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) pantallaTemporal.getGraphics();
        //setPantallaCompleta();
    }

    public void setPantallaCompleta() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.ventana);
        anchoPantalla2 = Main.ventana.getWidth();
        largoPantalla2 = Main.ventana.getHeight();
        lastTimeReset = System.currentTimeMillis();
        lastTimeReset_2 = System.currentTimeMillis();
        lastTimeReset_3 = System.currentTimeMillis();
        lastTimeReset_4 = System.currentTimeMillis();

    }

    public void dibujarPantallaTemporal() {
        if (estadoJuego == estadoTitulo) {
            ui.dibujar(g2);
        } else {
            mosaicos.dibujar(g2);

            listaEntidades.add(jugador);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    listaEntidades.add(npc[i]);
                }
            }
            
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    listaEntidades.add(obj[i]);
                }
            }
            for (int i = 1; i < 3; i++){
                if (obj[i]==null){
                    long currentTime = System.currentTimeMillis();
                    if (transcurreSegundos() == 5) {
                        if (currentTime - lastTimeReset_2 >= RESET_INTERVAL) {
                            lastTimeReset_2=currentTime;
                            rec_obj.setObjeto();
                        }
                    }
                }
            }

            jugador.dibujar(g2);
            Collections.sort(listaEntidades, new Comparator<Entidades>() {
                @Override
                public int compare(Entidades e1, Entidades e2 ) {
                    int resultado = Integer.compare(e1.planoMundoY, e2.planoMundoY);
                    
                    return resultado;
                }
            });
            for (int i = 0; i < listaEntidades.size(); i++) {
                listaEntidades.get(i).dibujar(g2);
            }
            
            for (int i = 0; i < listaEntidades.size(); i++) {
                listaEntidades.remove(i);
            }
            
            ui.dibujar(g2);
            if (tecla.presionado_E == true) {
                reloj.dibujar(g2);
                cola.imprimir_ordenes(g2);
                manejar_ingredientes.dibujar(g2);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Cafe Francisco", Font.ITALIC, 20));
                String timeText = formatTime();
                g2.drawString(timeText, getWidth() - g2.getFontMetrics().stringWidth(timeText) - 837, 535);

                setIngredientesJuego();
                setOrdenesJuego();
                aplicarPuntos();
                eliminarIngredientes();
            }
        }
    }
    
    public void setIngredientesJuego(){
        if (lista.size()!=5){
            long currentTime = System.currentTimeMillis();
            while (numeroRandom_2 == numeroAnterior_2){
                numeroRandom_2 = random.nextInt(4);
                
            }
            
            if (transcurreSegundos()==3){
                if (currentTime - lastTimeReset_3 >= RESET_INTERVAL) {
                    lastTimeReset_3 = currentTime;
                    lista.inserta(new Manejador_Ingredientes(this, numeroRandom_2));
                    numeroAnterior_2 = numeroRandom_2;
                }
            }
        }
        
    }

    public void aplicarPuntos(){
        if (estadoJuego!=estadoJugador){
            return;
        }
        if (buscarAgregarPuntos_Ingredientes()==-1){
            return;
        }
        String valor=lista.agregar_valor_inventario(buscarAgregarPuntos_Ingredientes());
        for (int i=0; i<jugador.inventario.size(); i++) {
            if (jugador.inventario.get(i).nombre.equals(valor)){
                switch (valor){
                    case "Ingredientes_Pan":
                    if (jugador.valor_pan==0){
                        jugador.valor_pan += 1;
                    }
                    jugador.valor_pan++;
                    break;
                case "Ingredientes_Carne":
                    if (jugador.valor_carne==0){
                        jugador.valor_carne += 1;
                    }
                    jugador.valor_carne++;
                    break;
                case "Ingredientes_Queso":
                    if (jugador.valor_queso==0){
                        jugador.valor_queso += 1;
                    }
                    jugador.valor_queso++;
                    break;
                case "Ingredientes_Lechuga":
                    if (jugador.valor_lechuga==0){
                        jugador.valor_lechuga += 1;
                    }
                    jugador.valor_lechuga++;
                    break; 
                }
        
                ui.inventarioItems.remove(buscarEliminarIngrediente());
                lista.elimina(buscarEliminarIngrediente());
            }
        }
        
    }

    public void eliminarIngredientes(){
        long currentTime = System.currentTimeMillis();
        if (estadoJuego!=estadoJugador){
            return;
        }
        System.out.println(ui.inventarioItems.size()+ " + "+ jugador.inventario.size());
        if ((buscarEliminarIngrediente()+1)==0){
            return;
        }
        int valor_ing = 0;
        String valor=lista.agregar_valor_inventario(buscarAgregarPuntos_Ingredientes()+1);
        for (int i=0; i<jugador.inventario.size(); i++) {
            if (jugador.inventario.get(i).nombre_ingredientes.equals(valor)){
                valor_ing=(i+1);
            }
        }
        if (valor_ing == 0){
            return ; 
        }
        if (transcurreSegundos()==2){
            if (currentTime - lastTimeReset_4 >= RESET_INTERVAL) {
                lastTimeReset_4=currentTime;
                lista.elimina(valor_ing);
                ui.inventarioItems.remove(valor_ing);
            }
        }
    }


    public int buscarEliminarIngrediente(){
        int indice = -1;
        if (tecla.presionadoEnter_ingrediente!=true){
            return indice;
        }
        if (tecla.presionado_C_ingrediente!=false){
            return indice;
        }
        
        for (int i = 0; i < ui.inventarioItems.size(); i++) {
            InventarioItem item = ui.inventarioItems.get(i);
            if (ui.cursorX >= item.getPosX() && ui.cursorX < item.getPosX() + ui.cursorAncho &&
                ui.cursorY >= item.getPosY() && ui.cursorY < item.getPosY() + ui.cursorLargo) {
                // El cursor está sobre la imagen en la posición 'i'
                indice = i;
                break;
            }
            
        }
        tecla.presionadoEnter_ingrediente=false;  
        return indice;
    }

    public int buscarAgregarPuntos_Ingredientes(){
        int indice = -1;
        if (tecla.presionado_C_ingrediente!=true){
            return indice;
        }
        if (tecla.presionadoEnter_ingrediente!=false){
            return indice;
        }
        for (int i = 0; i < ui.inventarioItems.size(); i++) {
            InventarioItem item = ui.inventarioItems.get(i);
            if (ui.cursorX >= item.getPosX() && ui.cursorX < item.getPosX() + ui.cursorAncho &&
                ui.cursorY >= item.getPosY() && ui.cursorY < item.getPosY() + ui.cursorLargo) {
                // El cursor está sobre la imagen en la posición 'i'
                indice = i;
                break;
            }
        }
        tecla.presionado_C_ingrediente=false;  
        return indice;
    }


    public void setOrdenesJuego(){
        if (cola.getLargo()==3){
            return;
        }
        long currentTime = System.currentTimeMillis();
        while (numeroRandom == numeroAnterior){
            numeroRandom = random.nextInt(3);
        }
        while (posicionActual == posicionAnterior){
            posicionActual +=4;
            if (posicionActual == 20){
                posicionActual=8;
            }
        }
        if (VeinteSegundos()==20){
            if (currentTime - lastTimeReset >= RESET_INTERVAL) {
                lastTimeReset = currentTime;
                referencia+=1;
                String referenciaString= String.valueOf(referencia);
                cola.encola(new Tiquetes_Ordenes(this, referenciaString, numeroRandom, posicionActual));
                numeroAnterior = numeroRandom;
                posicionAnterior=posicionActual;
                usarEfecto_Sonido(9);
            }
        }
    }

    public void dibujarNuevaPantalla() {
        Graphics g = getGraphics();
        g.drawImage(pantallaTemporal, 0, 0, anchoPantalla2, largoPantalla2, null);
        g.dispose();
    }

    @Override
    public void run() {

        timer = new Timer();
        double intervaloTiempo = 1000000000 / FPS; // equivale a 0.1666 segundos
        double delta = 0;
        long tiempoAnterior = System.nanoTime();
        long tiempoActual;
        long temporizador = 0;
        int contador = 0;

        while (realizarThread != null) {
            
            tiempoActual = System.nanoTime();
            delta += (tiempoActual - tiempoAnterior) / intervaloTiempo;
            temporizador += (tiempoActual - tiempoAnterior);
            tiempoAnterior = tiempoActual;
            if (delta >= 1) {

                try {
                    update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dibujarPantallaTemporal();
                dibujarNuevaPantalla();
                delta--;
                contador++;
            }
            
            if (temporizador >= 1000000000) {

                // TIMER PARA TERMINAR CON EL JUEGO
                timerJuego();
                // FPS MOSTRADOS EN TERMINAL
                System.out.println("FPS: " + contador);
                contador = 0;
                temporizador = 0;
            }
            timer.start();
        }
    }

    public void iniciarMusica(int i) {
        musicaTema.agregarArchivo(i);
        musicaTema.iniciar();
        musicaTema.loop();
    }

    public void pararMusica() {
        musicaTema.parar();
    }

    public void ponerVolumenTema(float i) {
        musicaTema.setVolumen(i);
    }

    public void pausarTema() {
        musicaTema.pausar();
    }

    public void reanudarTema() {
        musicaTema.reanudar();
    }

    public void usarEfecto_Sonido(int i) {
        musicaEfectoSonido.agregarArchivo(i);
        musicaEfectoSonido.iniciar();
    }
    // Dentro del metodo se verifica si el valor actual de la
    // variable counter es divisible por 5.

    // Si la condicion se cumple (es decir si el valor de counter es multiplo de 5)
    // entonces se asigna el valor 0 a la variable counter esto hace que la variable
    // counter se reinicie a cero cada vez que alcanza un multiplo de 5.
    private String formatTime() {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(minutes) + ":" + df.format(seconds);
    }
    private int VeinteSegundos() {
        counter_2 = (counter+1);
        return counter_2;
    }
    public int transcurreSegundos() {
        int counter_3 = (counter_obj+1);
        return counter_3;
    }
    private void timerJuego() {
        if (timeIsUp) {} 
        else if (tecla.presionadoEscape==true) {
            // Si se presiona Escape
            minutes = 5;
            seconds = 0;
            tecla.presionado_E = false;
        } else if (tecla.presionado_E!= true) {
            // Si la tecla E no está presionada
            minutes = 5;
            seconds = 0;
        } else {
            if (minutes == 0 && seconds == 0) {
                timeIsUp = true;
                timer.stop();
                pararMusica();
                usarEfecto_Sonido(7);
                musicaEfectoSonido.setVolumen(0.7);
            }
            else if (seconds == 0) {
                minutes--;
                seconds = 59;
            } else {
                seconds--;
                counter_obj++;
                if (counter_obj == 5) {
                    counter_obj = 0;
                }
                if (cola.getLargo() != 3) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 20) {
                    counter = 0;
                }
            }
        }
    }
}
