package br.com.poli.puzzleN.frontend;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;

public class SondTrack {

    private static File soundFile;
    private static Line line;
    private static Line.Info info;
    private static Clip clip;
    private static int level;

    public SondTrack() {
        try {
            SondTrack.soundFile = new File("res/MainTrack.wav");
            SondTrack.info = new Line.Info(Clip.class);
            SondTrack.line = AudioSystem.getLine(SondTrack.info);
            SondTrack.clip = (Clip) line;
            SondTrack.clip.open(AudioSystem.getAudioInputStream(SondTrack.soundFile));
            SondTrack.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeTrack(String track) {
        try {
            SondTrack.clip.stop();
            SondTrack.clip.close();
            SondTrack.soundFile = new File("res/" + track + ".wav");
            SondTrack.info = new Line.Info(Clip.class);
            SondTrack.clip = (Clip) line;
            SondTrack.clip.open(AudioSystem.getAudioInputStream(SondTrack.soundFile));
            SondTrack.clip.loop(Clip.LOOP_CONTINUOUSLY);

            SondTrack.line = AudioSystem.getLine(SondTrack.info);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void stop() {
        SondTrack.clip.stop();
        SondTrack.clip.close();
    }

    public static void pause() {
        SondTrack.clip.stop();
    }

    public static void play() {
        if (SondTrack.clip.isOpen() == false)
            try {
                SondTrack.clip.open(AudioSystem.getAudioInputStream(SondTrack.soundFile));
                SondTrack.clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        else if (SondTrack.clip.isRunning() == false)
            SondTrack.clip.start();
    }

    public static void altern() {
        if (SondTrack.clip.isRunning() && SondTrack.clip.isOpen())
            SondTrack.pause();
        else
            SondTrack.play();
    }

    public static void setLevel(int volume) {
        FloatControl level = (FloatControl) SondTrack.clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume = volume > SondTrack.level ? volume : volume * -1;
        SondTrack.level = +volume;
        float def = level.getValue() + (level.getMaximum() / 100) * SondTrack.level;
        level.setValue(def);
    }
    public static Clip getClip(){
        return clip;
    }
}