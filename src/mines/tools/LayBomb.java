package mines.tools;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import mines.bean.MineLable;

import javax.sound.sampled.*;

public class LayBomb {
	public static void lay(MineLable[][] lable, int row, int col) {

		int count = 0;
		Random random = new Random();
		while (count < StaticTool.allcount) {
			int x = random.nextInt(StaticTool.allrow);
			int y = random.nextInt(StaticTool.allcol);
			if (lable[x][y].isMineTag() == false && !(x == row && y == col)) {
				lable[x][y].setMineTag(true);
				lable[x][y].setCounAround(9);
				if (StaticTool.isHole == true) {
					lable[x][y].setIcon(StaticTool.holeIcon);
				}

				count++;
			}
		}

		computeBomb(lable);
	}
	private static void playExplosionSound() {
		try {
			File audioFile = new File("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\mines\\Audio\\bomb.wav");
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			Clip explosionSound = AudioSystem.getClip();
			explosionSound.open(audioStream);
			explosionSound.start();

			System.out.println("Sound playing: " + explosionSound.isRunning());
			System.out.println("Sound frames: " + explosionSound.getFrameLength());
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	// ��������...

	public static void computeBomb(MineLable lable[][]) {

		for (int i = 0; i < lable.length; i++) {
			for (int j = 0; j < lable[i].length; j++) {
				if (lable[i][j].isMineTag() == false) {
					int count = 0;
					for (int x = Math.max(0, i - 1); x <= Math.min(
							StaticTool.allrow - 1, i + 1); x++) {
						for (int y = Math.max(0, j - 1); y <= Math.min(
								StaticTool.allcol - 1, j + 1); y++) {
							if (lable[x][y].isMineTag() == true) {
								count++;
								playExplosionSound();

							}
						}
					}

					lable[i][j].setCounAround(count);

				}

			}

		}

	}
}
