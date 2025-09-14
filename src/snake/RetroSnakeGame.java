package snake;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RetroSnakeGame {
    public RetroSnakeGame() {

        // 创建窗口
        JFrame frame = new JFrame("贪吃蛇");
        // 固定窗口大小
        frame.setResizable(false);
        // 设置窗口出现位置和大小
        frame.setBounds(300,300,675,675);
        // 让窗口可关闭
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 添加游戏面板的组件
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        // 设置窗口可见性
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 在窗口关闭时调用你的方法
                gamePanel.pauseBgm();
            }

        });
    }
}
