package Tetris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Tetris
{
    public static final int FRAME_WIDTH = 305;
    public static final int FRAME_HEIGHT = 525;
    public Tetris() {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                GameWindow gameWindow = new GameWindow();
                gameWindow.setTitle("Tetris");
                gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gameWindow.setLocationByPlatform(true);
                gameWindow.setSize(FRAME_WIDTH, FRAME_HEIGHT);
                gameWindow.setResizable(false);
                gameWindow.setVisible(true);
                gameWindow.startGame();
                gameWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        // 在窗口关闭时调用你的方法
                        gameWindow.stopBgm();
                    }

                });
            }
        });
    }
}

class GameWindow extends JFrame
{
    private static final long serialVersionUID = 1L;
    //方块的边长
    public static final int BLOCK_SIZE = 20;
    //窗口横向方块总数
    public static final int HORIZON_NODES = Tetris.FRAME_WIDTH / BLOCK_SIZE;
    //窗口竖直方块总数
    public static final int VERTICAL_NODES = Tetris.FRAME_HEIGHT / BLOCK_SIZE;
    //代表方块在窗口上的分布情况，0代表空，1代表正在移动的方块，2代表已经固定的方块
    public static int[][] space = new int[VERTICAL_NODES][HORIZON_NODES];
    //画布
    private Canvas canvas;
    //定时器
    private Timer timer;
    private Clip bgmClip;  // 背景音乐剪辑
    private Clip clearSoundClip;  // 方块消除音效剪辑
    //游戏得分
    private int score;
    //七种图形，分别是田字型、一字型、正Z型、反Z型、正L型、反L型、T字型。每个图形包含4个方块。其中{0,0}为中心点。
    private final int[][][] shapes = {{{-1,0},{0,0},{-1,1},{0,1}},
            {{-1,0},{0,0},{1,0}, {2,0}},
            {{-1,0},{0,0},{0,1}, {1,1}},
            {{-1,1},{0,1},{0,0}, {1,0}},
            {{-1,1},{-1,0},{0,0},{1,0}},
            {{-1,0},{0,0},{1,0}, {1,1}},
            {{-1,0},{0,0},{1,0}, {0,1}}};
    //当前中心点的坐标
    private Point centerPos = new Point();
    //当前正在移动的图形的坐标
    private int[][] currentShape = new int[4][2];

    public GameWindow()
    {
        canvas = new Canvas();
        addKeyListener(new KeyHandler());
        add(canvas);
        loadAudioClips();
        pack();
    }
    //开始游戏
    public void startGame()
    {
        choseShape();
        timer = new Timer(300, new TimerHandler());
        timer.start();
        bgmClip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    //随机选择一个图形
    private void choseShape()
    {
        int index = (int) Math.round(Math.random() * 6);
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                currentShape[i][j] = shapes[index][i][j];
            }
        }
        centerPos.x = HORIZON_NODES / 2;
        centerPos.y = 0;
        updateSpace(1);
    }
    //图形下移
    private boolean moveDown()
    {
        for (int i = 0; i < 4; i++)
        {
            int x = centerPos.x + currentShape[i][0];
            int y = centerPos.y + currentShape[i][1] + 1;
            if (y >= VERTICAL_NODES-1 || space[y][x] == 2)
                return false;
        }
        updateSpace(0);
        centerPos.y++;
        updateSpace(1);
        return true;
    }
    //图形旋转
    private void transform()
    {
        int[][] temp = new int[4][2];
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                temp[i][j] = currentShape[i][j];
            }
        }
        for (int i = 0; i < 4; i++)
        {
            int t = temp[i][1];
            temp[i][1] = temp[i][0];
            temp[i][0] = -1 * t;
        }
        for (int i = 0; i < 4; i++)
        {
            int x = centerPos.x + temp[i][0];
            int y = centerPos.y + temp[i][1];
            if (x < 0 || x >= HORIZON_NODES || y < 0 || y >= VERTICAL_NODES || space[y][x] == 2)
                return;
        }
        updateSpace(0);
        currentShape = temp;
        updateSpace(1);
    }
    //图形左移
    private void moveLeft()
    {
        for (int i = 0; i < 4; i++)
        {
            int x = centerPos.x + currentShape[i][0] - 1;
            int y = centerPos.y + currentShape[i][1];
            if (x < 0 || space[y][x] == 2)
                return;
        }
        updateSpace(0);
        centerPos.x--;
        updateSpace(1);
    }
    //图形右移
    private void moveRight()
    {
        for (int i = 0; i < 4; i++)
        {
            int x = centerPos.x + currentShape[i][0] + 1;
            int y = centerPos.y + currentShape[i][1];
            if (x >= HORIZON_NODES || space[y][x] == 2)
                return;
        }
        updateSpace(0);
        centerPos.x++;
        updateSpace(1);
    }
    //固定图形
    private void fixBox()
    {
        updateSpace(2);
    }
    //消除满行
    private void clearLine()
    {
        int y = centerPos.y + currentShape[0][1];
        int minY = y, maxY = y;
        for (int i = 1; i < 4; i++)
        {
            y = centerPos.y + currentShape[i][1];
            if (y > maxY) maxY = y;
            else if (y < minY) minY = y;
        }

        for(y = minY; y <= maxY; y++)
        {
            int x;
            for (x = 0; x < HORIZON_NODES; x++)
            {
                if (space[y][x] == 0) break;
            }
            if (x == HORIZON_NODES)
            {
                for (int i = 0; i < HORIZON_NODES; i++)
                {
                    for (int j = y; j > 0; j--)
                    {
                        space[j][i] = space[j-1][i];
                    }
                }
                clearSoundClip.start();  // 在消除满行的代码块之后添加这一行
                score ++;
            }
        }
    }
    private void updateSpace(int flag)
    {
        for (int i = 0; i < 4; i++)
        {
            int x = centerPos.x + currentShape[i][0];
            int y = centerPos.y + currentShape[i][1];
            space[y][x] = flag;
        }
    }
    //判断是否触顶
    private boolean gameOver()
    {
        for (int i = 0; i < 4; i++)
        {
            int y = centerPos.y + currentShape[i][1];
            if (y == 0) return true;
        }
        return false;
    }
    //定时器监听
    class TimerHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (!moveDown())
            {
                fixBox();
                clearLine();
                timer.stop();
                if (gameOver())
                    JOptionPane.showMessageDialog(GameWindow.this, String.format("游戏结束，你的得分为%d", score));
                else
                    startGame();
            }
            canvas.repaint();
        }
    }
    //处理方向键
    private class KeyHandler extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            super.keyPressed(e);
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    transform();
                    canvas.repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    timer.setDelay(30);
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft();
                    canvas.repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight();
                    canvas.repaint();
                    break;
            }
        }
    }
    private void loadAudioClips() {
        try {
            // 背景音乐
            File bgmFile = new File("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\Tetris\\Audio\\fangkuaibeijing.wav");
            AudioInputStream bgmStream = AudioSystem.getAudioInputStream(bgmFile);
            bgmClip = AudioSystem.getClip();
            bgmClip.open(bgmStream);

            // 方块消除音效
            File clearSoundFile = new File("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\Tetris\\Audio\\fangkauixiaoshi.wav");
            AudioInputStream clearSoundStream = AudioSystem.getAudioInputStream(clearSoundFile);
            clearSoundClip = AudioSystem.getClip();
            clearSoundClip.open(clearSoundStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBgm(){
        if (bgmClip != null) {
            bgmClip.stop();
        }
    }

}


class Canvas extends JComponent {
    private static final long serialVersionUID = 1L;

    // 定义方块的颜色数组
    private static final Color[] BLOCK_COLORS = {
            null, // 空方块
            Color.RED, // 方块1的颜色
            Color.GREEN, // 方块2的颜色
            Color.BLUE, // 方块3的颜色
            Color.YELLOW, // 方块4的颜色
            Color.ORANGE, // 方块5的颜色
            Color.PINK, // 方块6的颜色
            Color.CYAN // 方块7的颜色
    };

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < GameWindow.VERTICAL_NODES; i++) {
            for (int j = 0; j < GameWindow.HORIZON_NODES; j++) {
                int blockValue = GameWindow.space[i][j];
                if (blockValue != 0) {
                    Color blockColor = BLOCK_COLORS[blockValue];
                    g2.setColor(blockColor);
                    g2.fillRect(j * 20, i * 20, 20, 20);
                }
            }
        }
    }
}
