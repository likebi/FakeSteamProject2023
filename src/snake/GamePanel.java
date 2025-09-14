package snake;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private String snakeHead;
    private int length;
    private int[] snakeX;
    private int[] snakeY;
    private boolean isStart = false;
    private Timer timer = new Timer(170, this);
    private int foodX;
    private int foodY;
    private Random random = new Random();
    private boolean isFail = false;
    private boolean isVictory = false;
    private int score;

    private Clip bgmClip;
    private Clip eatClip;
    private Clip gameOverClip;

    public GamePanel() {
        init();
        setFocusable(true);
        addKeyListener(this);
        timer.start();
        loadSounds();
    }

    private void loadSounds() {
        try {
            File eatFile = new File("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\snake\\Audio\\eating.wav");
            AudioInputStream eatAudioInputStream = AudioSystem.getAudioInputStream(eatFile);
            eatClip = AudioSystem.getClip();
            eatClip.open(eatAudioInputStream);

            File gameOverFile = new File("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\snake\\Audio\\snakegameover.wav");
            AudioInputStream gameOverAudioInputStream = AudioSystem.getAudioInputStream(gameOverFile);
            gameOverClip = AudioSystem.getClip();
            gameOverClip.open(gameOverAudioInputStream);

            File bgmFile = new File("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\snake\\Audio\\snake bg.wav");
            AudioInputStream bgmAudioInputStream = AudioSystem.getAudioInputStream(bgmFile);
            bgmClip = AudioSystem.getClip();
            bgmClip.open(bgmAudioInputStream);
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    private void playSound(Clip clip) {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    private void playBgm() {
        if (bgmClip != null) {
            bgmClip.start();
        }
    }

    public void pauseBgm() {
        if (bgmClip != null) {
            bgmClip.stop();
        }
    }

    private void resumeBgm() {
        if (bgmClip != null) {
            bgmClip.start();
        }
    }

    private void init() {
        snakeHead = "right";
        length = 3;
        snakeX = new int[1000];
        snakeY = new int[1000];
        snakeX[0] = 75;
        snakeY[0] = 75;
        snakeX[1] = 50;
        snakeY[1] = 75;
        snakeX[2] = 25;
        snakeY[2] = 75;
        foodX = 75 + 25 * random.nextInt(21);
        foodY = 75 + 25 * random.nextInt(19);
        for (int i = 0; i < length; i++) {
            if (foodX == snakeX[i] && foodY == snakeY[i]) {
                foodX = 75 + 25 * random.nextInt(21);
                foodY = 75 + 25 * random.nextInt(19);
                i = 0;
            }
        }
        score = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);
        g.fillRect(25, 50, 600, 575);
        if (snakeHead.equals("up")) {
            Data.getUp().paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (snakeHead.equals("down")) {
            Data.getDown().paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (snakeHead.equals("left")) {
            Data.getLeft().paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (snakeHead.equals("right")) {
            Data.getRight().paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        for (int i = 1; i < length; i++) {
            Data.getBody().paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        Data.getFood().paintIcon(this, g, foodX, foodY);
        g.setColor(Color.black);
        g.setFont(new Font("宋体", Font.BOLD, 15));
        g.drawString("长度：" + length, 25, 20);
        g.drawString("分数：" + score, 25, 40);
        if (!isStart) {
            g.setColor(Color.white);
            g.setFont(new Font("楷体", Font.BOLD, 40));
            g.drawString("按下空格开始游戏！", 125, 300);
        }
        if (isFail) {
            g.setColor(Color.red);
            g.setFont(new Font("楷体", Font.BOLD, 40));
            g.drawString("失败，按下空格重新开始", 125, 300);
        }
        if (isVictory) {
            g.setColor(Color.red);
            g.setFont(new Font("宋体", Font.BOLD, 35));
            g.drawString("胜利，你是为数不多的天才", 125, 300);
            g.drawString("按下空格重新开始新一轮游戏", 100, 350);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (isFail || isVictory) {
                isFail = false;
                isVictory = false;
                init();
            } else {
                isStart = !isStart;
                if (isStart) {
                    playBgm();
                } else {
                    pauseBgm();
                }
            }
            repaint();
        }
        if (isStart) {
            if (e.getKeyCode() == KeyEvent.VK_UP && !snakeHead.equals("down")) {
                snakeHead = "up";
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN && !snakeHead.equals("up")) {
                snakeHead = "down";
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT && !snakeHead.equals("right")) {
                snakeHead = "left";
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !snakeHead.equals("left")) {
                snakeHead = "right";
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && !isFail && !isVictory) {
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            if (snakeHead.equals("right")) {
                snakeX[0] = snakeX[0] + 25;
                if (snakeX[0] > 600) snakeX[0] = 25;
            } else if (snakeHead.equals("left")) {
                snakeX[0] = snakeX[0] - 25;
                if (snakeX[0] < 25) snakeX[0] = 600;
            } else if (snakeHead.equals("up")) {
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0] < 50) snakeY[0] = 600;
            } else if (snakeHead.equals("down")) {
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] > 600) snakeY[0] = 50;
            }
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                length++;
                score = score + 10;
                if (length == 300) {
                    isVictory = true;
                } else if (length == 25) {
                    timer = new Timer(145, this);
                } else if (length == 50) {
                    timer = new Timer(125, this);
                } else if (length == 100) {
                    timer = new Timer(100, this);
                } else if (length == 200) {
                    timer = new Timer(70, this);
                }
                foodX = 75 + 25 * random.nextInt(21);
                foodY = 75 + 25 * random.nextInt(19);
                for (int i = 0; i < length; i++) {
                    if (foodX == snakeX[i] && foodY == snakeY[i]) {
                        foodX = 75 + 25 * random.nextInt(21);
                        foodY = 75 + 25 * random.nextInt(19);
                        i = 0;
                    }
                }
                playSound(eatClip);
            }
            for (int i = 2; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                    playSound(gameOverClip);
                }
            }
            repaint();
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}