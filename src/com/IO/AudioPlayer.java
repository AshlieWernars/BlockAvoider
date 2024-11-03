package com.IO;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	// Classes
	private AudioInputStream audioInputStream;
	private FloatControl gainControl;
	private Clip clip;

	public AudioPlayer(String path) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());

			clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			clip.loop(Clip.LOOP_CONTINUOUSLY);

			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-50f);
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Audio File Not Supported.");
			System.err.println("Audio File Path: " + path);
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		if (clip == null) {
			return;
		}

		if (!clip.isActive()) {
			clip.start();
		}
	}

	public void stop() {
		if (clip == null) {
			return;
		}

		if (clip.isActive()) {
			clip.stop();
		}
	}

	public void setVolume(float adjustment) {
		gainControl.setValue(adjustment);
	}
}