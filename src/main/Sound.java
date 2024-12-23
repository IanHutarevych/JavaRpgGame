package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[7];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/theme.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/sound/receivedamage.wav");

        for (int i = 0; i < soundURL.length; i++) {
            if (soundURL[i] == null) {
                System.err.println("Sound file not found at index " + i);
            }
        }
    }

    public void setFile(int i) {
        try {
            if (soundURL[i] == null) {
                System.err.println("Sound file is missing for index " + i);
                return;
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            System.out.println("Sound loaded: " + soundURL[i]);
        } catch (Exception e) {
            System.err.println("Error loading sound at index " + i + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip == null) {
            System.err.println("Clip is null. Did you call setFile()?");
            return;
        }
        clip.start();
    }

    public void loop() {
        if (clip == null) {
            System.err.println("Clip is null. Did you call setFile()?");
            return;
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
