package BreakOut;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;
import java.io.File;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements ActionListener, KeyListener{
	
	private boolean play = false;
	public Clip clip;
	private int score = 0;
	private int totalbricks = 36;
	
	//Timer
	private Timer timer;
	private int delay = 5; //改求速

	//paddle
	private int playerX = 400;
	
	//position of ball
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir ;
	private int ballYdir ;

	private MapGenerator map;

	public Gameplay()
	{
		map = new MapGenerator(4, 9);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

		// 生成随机数对象
		Random random = new Random();

		// 设置球的初始方向为随机值（-1或1）
		ballXdir = random.nextBoolean() ? -1 : 1;
		ballYdir = random.nextBoolean() ? -1 : 1;

	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);

		map.draw((Graphics2D) g);

		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(681, 0, 3, 592);

		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString(""+score, 590,30);

		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);

		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);

		if(totalbricks <= 0)
		{
			play = false;
		    ballXdir = 0;
		    ballYdir = 0;
		    g.setColor(Color.RED);
		    g.setFont(new Font("serif",Font.BOLD, 30));
		    g.drawString("You Won", 260,300);
			playSound("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\BreakOut\\audios\\win.wav");
		             
		    g.setColor(Color.RED);
		    g.setFont(new Font("serif",Font.BOLD, 20));           
		    g.drawString("Press (Enter) to Restart", 230,350);  
		}

		if(ballposY > 570)
		{
			play = false;
		    ballXdir = 0;
		    ballYdir = 0;
		    g.setColor(Color.RED);
		    g.setFont(new Font("serif",Font.BOLD, 30));
		    g.drawString("Game Over, Scores: "+score, 190,300);
			playSound("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\BreakOut\\audios\\life_lost.wav");
		             
		    g.setColor(Color.RED);
		    g.setFont(new Font("serif",Font.BOLD, 20));           
		    g.drawString("Press (Enter) to Restart", 230,350);        
		}
		
		g.dispose();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			}
			else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			}
			else {
				moveLeft();
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalbricks = 36;
				map = new MapGenerator(4, 9);
				
				repaint();
			}
        }		
			
	}

	public void moveLeft() {
		play = true;
		playerX -= 100;
	}

	public void moveRight() {
		play = true;
		playerX += 100;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
			{
				ballYdir = -ballYdir;// 球与挡板碰撞后，改变球的垂直方向
				playSound("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\BreakOut\\audios\\paddle_hit.wav");
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 100, 8)))
			{
				ballYdir = -ballYdir;// 球与挡板碰撞后，改变球的垂直方向
				playSound("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\BreakOut\\audios\\paddle_hit.wav");
				ballXdir = ballXdir + 1;// 球与挡板右侧碰撞后，向右偏移一点，改变球的水平方向
				playSound("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\BreakOut\\audios\\paddle_hit.wav");

			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 110, 8)))
			{
				ballYdir = -ballYdir;// 球与挡板碰撞后，改变球的垂直方向
				playSound("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\BreakOut\\audios\\paddle_hit.wav");
			}

			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)
					{
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{					
							map.setBrickValue(0, i, j);
							score+=5;	
							totalbricks--;

							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							else
							{
								ballYdir = -ballYdir;
							}

							playSound("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\BreakOut\\audios\\brick_hit.wav");
							break A;

						}
					}
				}
			}
			
			ballposX += 2 * ballXdir;
			ballposY += 2 * ballYdir;
			
			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > 670)
			{
				ballXdir = -ballXdir;
			}
			
			repaint();
		}
			
		
		
	}
	public void playSound(String soundFile) {
		try {
			// 创建音频文件对象
			File file = new File(soundFile);

			// 获取音频输入流
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

			// 创建音频剪辑对象
			clip = AudioSystem.getClip();

			// 打开剪辑并加载音频流
			clip.open(audioInputStream);

			// 播放音频
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopBgm(){
		try {
			clip.stop();
			play = false;
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}






