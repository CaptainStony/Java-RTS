package com.caps.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	private Clip clip;
	public static int masterVolume = 0;
	public static Sound backMusic = new Sound("/backgroundMusic.wav");
	
	public Sound(String fileName) {
		try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));

			clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if(fileName == "/backgroundMusic.wav"){
                gainControl.setValue(-30.0f + masterVolume);

            }
		} catch (Exception e) {
            e.printStackTrace();
		}
	}
	public void play() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();

                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void stop(){

        if(clip == null) return;
        clip.stop();

    }

    public void loop() {
        try {
            if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {

                            clip.stop();
                            clip.setFramePosition(0);
                            clip.loop(Clip.LOOP_CONTINUOUSLY);

                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public boolean isActive(){

        return clip.isActive();

    }

}
