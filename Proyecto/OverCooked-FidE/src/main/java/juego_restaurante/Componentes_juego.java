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
import lista_Ingredientes.Cola_Ingredientes;
import lista_Ingredientes.Manejador_Ingredientes;
import lista_Ingredientes.Nodo_Ingredientes;
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
    private final int tamaño_originalObjeto = 16;
    private final int escalar_a_Pantalla = 3;

    // Objeto de 48x48 pixeles
    private final int tamaño_finalObjeto = tamaño_originalObjeto * escalar_a_Pantalla;

    // Pantalla 16:12 == 4:3, 20:12 == 16:9
    private final int tamañoMaximoPantallaColumnas = 20; // 20 , 16
    private final int tamañoMaximoPantallaFilas = 12;
    private final int anchoPantalla = tamaño_finalObjeto * tamañoMaximoPantallaColumnas; // 768 pixeles si son 16 tMPC,
                                                                                        // si 20 tMPC pixeles son 960
    private final int largoPantalla = tamaño_finalObjeto * tamañoMaximoPantallaFilas; // 576 pixeles

    // TAMAÑO DEL MAPA 69x40
    private final int tamañoMaximoMundoColumnas = 69;
    private final int tamañoMaximoMundoFilas = 40;

    int anchoPantalla2 = anchoPantalla;
    int largoPantalla2 = largoPantalla;

    BufferedImage pantallaTemporal;
    Graphics2D g2;

    int FPS = 60;
    
    private long lastTimeReset = System.currentTimeMillis();
    private long lastTimeReset_2 = System.currentTimeMillis();
    private long lastTimeReset_3 = System.currentTimeMillis();
    private long lastTimeReset_4 = System.currentTimeMillis();
    private long lastTimeReset_5 = System.currentTimeMillis();

    private final long RESET_INTERVAL = 1500; // 1 segundo en milisegundos
    private final long RESET_INTERVAL_2 = 80; // 0.08 segundos en milisegundos
    private final long RESET_INTERVAL_3 = 500; // 0.02 segundos en milisegundos

    private boolean timeIsUp = false;
    private int minutes = 5;
    private int seconds = 0;
    int seconds_2=0;
    // SISTEMA
    private Timer timer;
    private Manejador_de_Mosaicos mosaicos = new Manejador_de_Mosaicos(this);
    private Teclado tecla = new Teclado(this);
    Musica musicaTema = new Musica();
    Musica musicaEfectoSonido = new Musica();
    private DetectorColisiones dc = new DetectorColisiones(this);
    private Recursos_Incializar rec_obj = new Recursos_Incializar(this);
    private UI ui = new UI(this);
    private Thread realizarThread;
    Random random = new Random();

    // Tiene 12 objetos que se pueden renderizar al
    // mismo tiempo no significa que solo se puede almacenar 10 objetos
    // OBJETOS Y ENTIDADES    
    private Entidades obj[] = new Entidades[12];
    private Jugador jugador = new Jugador(this, tecla);
    private Entidades npc[] = new Entidades[10];
    final int MAX_ENTIDADES = 30;
    Entidades[] listaEntidades = new Entidades[MAX_ENTIDADES];
    int contadorEntidades = 0;

    //OTROS OBJETOS
    Reloj_arena reloj = new Reloj_arena(this);
    //COLAS PARA TIQUETES
    private Cola cola = new Cola();
    private Nodo nodoEliminar = null;
    private Tiquetes_Ordenes primerEncolado = null;

    //Lista Circular
    private Cola_Ingredientes lista = new Cola_Ingredientes();
    private Nodo_Ingredientes nodoEliminaIngredientes = null;
    private Manejador_Ingredientes manejar_ingredientes =new Manejador_Ingredientes(this, -1);

    // Inicio, etc...
    private int estadoJuego;
    private final int estadoTitulo = 0;
    private final int juegoIniciar = 1;
    private final int juegoDialogos = 2;
    private final int estadoJugador = 3;
    private final int estadoFinalJuego = 4;

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
            iniciarMusica(4);
            ponerVolumenTema((float) 0.7);
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
        lastTimeReset_5 = System.currentTimeMillis();
    }

    public void dibujarPantallaTemporal() {
        if (estadoJuego == estadoTitulo) {
            ui.dibujar(g2);
        } else {
            mosaicos.dibujar(g2);
            
            // Agregar elementos a la lista
            listaEntidades[contadorEntidades++] = jugador;
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    listaEntidades[contadorEntidades++] = npc[i];
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    listaEntidades[contadorEntidades++] = obj[i];
                }
            }
            //Agregar objetos (nuevamente) [hamburguesas] despues de haber pasado cinco segundos 
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
            
            // Ordenar la lista manualmente (usando el algoritmo de selección)
            for (int i = 0; i < contadorEntidades - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < contadorEntidades; j++) {
                    if (listaEntidades[j].getPlanoMundoY() < listaEntidades[minIndex].getPlanoMundoY()) {
                        minIndex = j;
                    }
                }
                Entidades temp = listaEntidades[minIndex];
                listaEntidades[minIndex] = listaEntidades[i];
                listaEntidades[i] = temp;
            }
            
            // Dibujar las entidades
            for (int i = 0; i < contadorEntidades; i++) {
                listaEntidades[i].dibujar(g2);
            }
            
            // Eliminar elementos de la lista
            for (int i = 0; i < contadorEntidades; i++) {
                listaEntidades[i] = null;
            }
            contadorEntidades = 0;
            jugador.dibujar(g2);
            
            ui.dibujar(g2);
            if (tecla.isPresionado_E() == true) {
                reloj.dibujar(g2);
                cola.imprimir_ordenes(g2);
                manejar_ingredientes.dibujar(g2);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Cafe Francisco", Font.ITALIC, 20));
                String timeText = formatTime();
                g2.drawString(timeText, getWidth() - g2.getFontMetrics().stringWidth(timeText) - 837, 535);
                setIngredientesJuego();
                setOrdenesJuego();
                eliminarIngredientesJuego();
                agregarPuntosIngredietesJuego();
            }
        }
    }
    public void setIngredientesJuego(){
        if (lista.getLargo()==5){
            return;
        }
        long currentTime = System.currentTimeMillis();
        while (numeroRandom_2 == numeroAnterior_2){
            numeroRandom_2 = random.nextInt(4);
            
        }
        if (transcurreSegundos()%1==0){
            if (currentTime - lastTimeReset_3 >= RESET_INTERVAL_2) {
                lastTimeReset_3 = currentTime;
                numeroAnterior_2 = numeroRandom_2;
                lista.inserta(new Manejador_Ingredientes(this, numeroRandom_2));
            }
            
        }
    }


    public void eliminarIngredientesJuego(){
        long currentTime = System.currentTimeMillis();
        if (tecla.isPresionadoEnter_ingrediente()!=true){
            return;
        }

        if (transcurreSegundos()%1==0){
            if (currentTime - lastTimeReset_4 >= RESET_INTERVAL_3) {
                lastTimeReset_4 = currentTime;
                
                nodoEliminaIngredientes = lista.elimina();
                jugador.getInventarioIngredientesMostrarJugador().eliminar(ui.espacioColumna);
                tecla.setPresionadoEnter_ingrediente(false);
                usarEfecto_Sonido(10);
            }
        }
        
    }

    public void agregarPuntosIngredietesJuego(){
        long currentTime = System.currentTimeMillis();
        if (tecla.isPresionado_C_ingrediente()!=true){
            return;
        }
        if (transcurreSegundos()%1==0){
            if (currentTime - lastTimeReset_5 >= RESET_INTERVAL_3) {
                lastTimeReset_5 = currentTime;
                tecla.setPresionado_C_ingrediente(false);
                agregarValorIngredientes();
                nodoEliminaIngredientes = lista.elimina();
                jugador.getInventarioIngredientesMostrarJugador().eliminar(ui.espacioColumna);
                usarEfecto_Sonido(10);
            }
        }
        
    }

    public void agregarValorIngredientes(){
        int cantidadCarne = jugador.getValor_carne();
        int cantidadLechuga = jugador.getValor_lechuga();
        int cantidadPan= jugador.getValor_pan();
        int cantidadQueso = jugador.getValor_queso();
        switch (ui.getNumeroIngrediente()) {
            
            case 1:
                //"Ingediente__Carne";
                jugador.setValor_carne(cantidadCarne+=1);
                break;
            case 2:
                //"Ingediente__Queso";
                jugador.setValor_queso(cantidadQueso+=1);
                break;
            case 3:
                //"Ingediente__Lechuga";
                jugador.setValor_lechuga(cantidadLechuga+=1);
                break;
            case 4:
                //"Ingediente__Pan";
                jugador.setValor_pan(cantidadPan+=1);
                break;
            default:
                break;
        }
    }

    public void setOrdenesJuego(){
        if (cola.getLargo()==3){
            return;
        }
        while (numeroRandom == numeroAnterior){
            numeroRandom = random.nextInt(3);
        }
        while (posicionActual == posicionAnterior){
            posicionActual +=4;
            if (posicionActual == 20){
                posicionActual=8;
            }
        }
        String referenciaString="";
        long currentTime = System.currentTimeMillis();
        if (VeinteSegundos()==20){
            if (currentTime - lastTimeReset >= RESET_INTERVAL) {
                lastTimeReset = currentTime;
                referencia+=1;
                referenciaString= String.valueOf(referencia);
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
        else if (tecla.isPresionadoEscape()==true) {
            // Si se presiona Escape
            minutes = 5;
            seconds = 0;
            tecla.setPresionado_E(false);
        } else if (tecla.isPresionado_E()!= true) {
            // Si la tecla E no está presionada
            minutes = 5;
            seconds = 0;
        } else {
            if (minutes == 0 && seconds == 0) {
                timeIsUp = true;
                timer.stop();
                pararMusica();
                usarEfecto_Sonido(7);
                estadoJuego= estadoFinalJuego;
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
    
    public int getTamaño_originalObjeto() {
        return tamaño_originalObjeto;
    }

    public int getEscalar_a_Pantalla() {
        return escalar_a_Pantalla;
    }

    public int getTamaño_finalObjeto() {
        return tamaño_finalObjeto;
    }

    public int getTamañoMaximoPantallaColumnas() {
        return tamañoMaximoPantallaColumnas;
    }

    public int getTamañoMaximoPantallaFilas() {
        return tamañoMaximoPantallaFilas;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public int getLargoPantalla() {
        return largoPantalla;
    }

    public int getTamañoMaximoMundoColumnas() {
        return tamañoMaximoMundoColumnas;
    }

    public int getTamañoMaximoMundoFilas() {
        return tamañoMaximoMundoFilas;
    }

    public boolean isTimeIsUp() {
        return timeIsUp;
    }

    public void setTimeIsUp(boolean timeIsUp) {
        this.timeIsUp = timeIsUp;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds_2() {
        return seconds_2;
    }

    public void setSeconds_2(int seconds_2) {
        this.seconds_2 = seconds_2;
    }

    public int getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(int estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public int getEstadoTitulo() {
        return estadoTitulo;
    }

    public int getJuegoIniciar() {
        return juegoIniciar;
    }

    public int getJuegoDialogos() {
        return juegoDialogos;
    }

    public int getEstadoJugador() {
        return estadoJugador;
    }

    public int getEstadoFinalJuego() {
        return estadoFinalJuego;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter_2() {
        return counter_2;
    }

    public void setCounter_2(int counter_2) {
        this.counter_2 = counter_2;
    }

    public int getCounter_obj() {
        return counter_obj;
    }

    public void setCounter_obj(int counter_obj) {
        this.counter_obj = counter_obj;
    }

    public int getPosicionAnterior() {
        return posicionAnterior;
    }

    public void setPosicionAnterior(int posicionAnterior) {
        this.posicionAnterior = posicionAnterior;
    }

    public int getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(int posicionActual) {
        this.posicionActual = posicionActual;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public int getNumeroAnterior() {
        return numeroAnterior;
    }

    public void setNumeroAnterior(int numeroAnterior) {
        this.numeroAnterior = numeroAnterior;
    }

    public int getNumeroRandom() {
        return numeroRandom;
    }

    public void setNumeroRandom(int numeroRandom) {
        this.numeroRandom = numeroRandom;
    }

    public int getNumeroAnterior_2() {
        return numeroAnterior_2;
    }

    public void setNumeroAnterior_2(int numeroAnterior_2) {
        this.numeroAnterior_2 = numeroAnterior_2;
    }

    public int getNumeroRandom_2() {
        return numeroRandom_2;
    }

    public void setNumeroRandom_2(int numeroRandom_2) {
        this.numeroRandom_2 = numeroRandom_2;
    }

    //CLASES
    
    public Timer getTimer() {
        return timer;
    }

    public Manejador_de_Mosaicos getMosaicos() {
        return mosaicos;
    }

    public Teclado getTecla() {
        return tecla;
    }

    public DetectorColisiones getDc() {
        return dc;
    }

    public Recursos_Incializar getRec_obj() {
        return rec_obj;
    }

    public UI getUi() {
        return ui;
    }

    public Thread getRealizarThread() {
        return realizarThread;
    }

    public Entidades[] getObj() {
        return obj;
    }
    
    public Jugador getJugador() {
        return jugador;
    }
    
    public Entidades[] getNpc() {
        return npc;
    }

    public Cola getCola() {
        return cola;
    }

    public Nodo getNodoEliminar() {
        return nodoEliminar;
    }
    
    public void setNodoEliminar(Nodo nodoEliminar) {
        this.nodoEliminar = nodoEliminar;
    }

    public Tiquetes_Ordenes getPrimerEncolado() {
        return primerEncolado;
    }

    public void setPrimerEncolado(Tiquetes_Ordenes primerEncolado) {
        this.primerEncolado = primerEncolado;
    }

    public Cola_Ingredientes getLista() {
        return lista;
    }

    public Nodo_Ingredientes getNodoEliminaIngredientes() {
        return nodoEliminaIngredientes;
    }

    public Manejador_Ingredientes getManejar_ingredientes() {
        return manejar_ingredientes;
    }

}
