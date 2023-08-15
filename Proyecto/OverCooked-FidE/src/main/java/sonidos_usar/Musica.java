/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sonidos_usar;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Racha
 */
public class Musica {
    Clip clip;
    URL[] musicaURL = new URL[30];

    public Musica() {
        musicaURL[0] = getClass().getResource("/sonidos/Background/Caketown1.wav");

        musicaURL[1] = getClass().getResource("/sonidos/Comiendo/crunch.5.wav");
        musicaURL[2] = getClass().getResource("/sonidos/Comiendo/crunch.6.wav");
        musicaURL[3] = getClass().getResource("/sonidos/Comiendo/crunch.7.wav");

        musicaURL[4] = getClass().getResource("/sonidos/Inicio/Arabesque.wav");
        musicaURL[5] = getClass().getResource("/sonidos/Puerta_abriendose/qubodup-DoorClose01.wav");
        musicaURL[6] = getClass().getResource("/sonidos/Puerta_abriendose/qubodup-DoorOpen01.wav");
        musicaURL[7] = getClass().getResource("/sonidos/Terminada_partida/6 Open Surge score jingle - AA.wav");
        musicaURL[8] = getClass().getResource("/sonidos/Preparar_comida/ui click 3 [2018-10-13 162315].wav");
        musicaURL[9] = getClass().getResource("/sonidos/Ordenes_sonidos/vgmenuselect.wav");
        musicaURL[10] = getClass().getResource("/sonidos/Ordenes_sonidos/click-click-stereo.wav");
        musicaURL[11] = getClass().getResource("/sonidos/Ordenes_sonidos/click-negative-stereo.wav");
    }

    public void agregarArchivo(int i) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(musicaURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVolumen(double d) {
        if (clip == null) {
            return;
        }
        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float minVolume = gainControl.getMinimum();
            float maxVolume = gainControl.getMaximum();
            float range = maxVolume - minVolume;
            float gain = (float) ((range * d) + minVolume);
            gainControl.setValue(gain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciar() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void parar() {
        clip.stop();
    }

    public void pausar() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void reanudar() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

}
