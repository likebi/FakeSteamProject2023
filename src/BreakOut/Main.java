package BreakOut;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

	public Main() {
		JFrame frame = new JFrame();
		Gameplay gamePlay = new Gameplay();
		
		frame.setBounds(200,100, 700, 600);
		frame.setTitle("Breakout Ball");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.add(gamePlay);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// 在窗口关闭时调用你的方法
				gamePlay.stopBgm();
			}

		});

	}

}
