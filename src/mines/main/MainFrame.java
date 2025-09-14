package mines.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mines.panel.BombJMenuBar;
import mines.panel.BombJPanel;
import mines.panel.FaceJPanel;
import mines.timer.TimerListener;
import mines.tools.StaticTool;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class MainFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private BombJMenuBar menuBar = new BombJMenuBar(this);

	private FaceJPanel faceJPanel = new FaceJPanel(this);

	private BombJPanel bombJPanel = new BombJPanel(this);

	private TimerListener timerListener = new TimerListener(this);

	private Timer timer = new Timer(1000, timerListener);
	private Clip backgroundMusic;
	private Clip explosionSound;



	public MainFrame() {
		init();

		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setTitle("ɨ��");
		this.setSize(new Dimension(220, 300));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		playBackgroundMusic("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\mines\\Audio\\background.wav");
		this.pack();
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// 在窗口关闭时调用你的方法
				stopBackgroundMusic();
			}

		});


	}
	private void playBackgroundMusic(String filePath) {
		try {
			File audioFile = new File(filePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			backgroundMusic = AudioSystem.getClip();
			backgroundMusic.open(audioStream);
			backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	private void loadExplosionSound(String filePath) {
		try {
			File audioFile = new File(filePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			explosionSound = AudioSystem.getClip();
			explosionSound.open(audioStream);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public void stopBackgroundMusic() {
		if (backgroundMusic != null && backgroundMusic.isRunning()) {
			backgroundMusic.stop();
			backgroundMusic.close();
		}
	}


	private void init() {
		this.setJMenuBar(menuBar);
		this.add(faceJPanel, BorderLayout.NORTH);
		this.add(bombJPanel);
		loadExplosionSound("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\mines\\Audio\\bomb.wav");

	}
	public void playExplosionSound() {
		if (explosionSound != null && !explosionSound.isRunning()) {
			explosionSound.setFramePosition(0);
			explosionSound.start();
		}
	}

	public void stopExplosionSound() {
		if (explosionSound != null && explosionSound.isRunning()) {
			explosionSound.stop();
			explosionSound.setFramePosition(0);
		}
	}


	public void reStartGame() {

		this.remove(faceJPanel);
		this.remove(bombJPanel);

		StaticTool.bombCount = StaticTool.allcount;
		StaticTool.timecount = 0;
		StaticTool.isStart = false;

		faceJPanel = new FaceJPanel(this);
		bombJPanel = new BombJPanel(this);
		this.add(faceJPanel, BorderLayout.NORTH);
		this.add(bombJPanel);
		this.pack();
		this.validate();

		getTimer().stop();

	}

	public FaceJPanel getFaceJPanel() {
		return faceJPanel;
	}

	public BombJPanel getBombJPanel() {
		return bombJPanel;
	}

	public Timer getTimer() {
		return timer;
	}
	@Override
	public void dispose() {
		stopBackgroundMusic();
		super.dispose();
	}


	/**
	 *
	 */
	public static void main() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new MainFrame();

	}

}
