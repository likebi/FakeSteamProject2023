package Pintu;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
    private Clip moveSound;
    private Clip gameOverSound;

    public SoundManager() {
        try {
            moveSound = AudioSystem.getClip();
            gameOverSound = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void loadSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            if (soundFile.exists()) {
                if (filePath.endsWith("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\Pintu\\Audio\\PinTuMove.wav")) {
                    moveSound.open(AudioSystem.getAudioInputStream(soundFile));
                } else if (filePath.endsWith("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\Pintu\\Audio\\PintuFinished.wav")) {
                    gameOverSound.open(AudioSystem.getAudioInputStream(soundFile));
                }
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playMoveSound() {
        if (moveSound.isRunning()) {
            moveSound.stop();
        }
        moveSound.setFramePosition(0);
        moveSound.start();
    }

    public void playGameOverSound() {
        if (gameOverSound.isRunning()) {
            gameOverSound.stop();
        }
        gameOverSound.setFramePosition(0);
        gameOverSound.start();
    }

}
