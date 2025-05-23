package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[19];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/taverna.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/sound/dealdamage.wav");
        soundURL[6] = getClass().getResource("/sound/Ouch.wav");
        soundURL[7] = getClass().getResource("/sound/levelup.wav"); //lvlup
        soundURL[8] = getClass().getResource("/sound/taverna.wav");
        soundURL[9] = getClass().getResource("/sound/cursor.wav");
        soundURL[10] = getClass().getResource("/sound/burning.wav");
        soundURL[11] = getClass().getResource("/sound/treeSound.wav");
        soundURL[12] = getClass().getResource("/sound/GameOver.wav");
        soundURL[13] = getClass().getResource("/sound/speak.wav");
        soundURL[14] = getClass().getResource("/sound/Dungeon.wav");
        soundURL[15] = getClass().getResource("/sound/Merchant.wav");
        soundURL[16] = getClass().getResource("/sound/chipwall.wav");
        soundURL[17] = getClass().getResource("/sound/sleep.wav");
        soundURL[18] = getClass().getResource("/sound/FinalBattle.wav");

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
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
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
    public void checkVolume() {
        switch (volumeScale) {
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
