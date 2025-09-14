import AirplaneBattle.ShootGame;
import BreakOut.Main;
import Pacman.Pacman;
import Pintu.MainJFrame;
import Tetris.Tetris;
import TikTakToe.TicTacToeGame;
import WuZiQi.ChessJFrame;
import mines.main.MainFrame;
import snake.RetroSnakeGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Fri Jul 14 15:06:06 MYT 2023
 */



/**
 * @author zijun
 */
public class ChooseGame extends JFrame {

    public ChooseGame() {

        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void Mines(){
        MainFrame.main();
    }

    private void PacManAction(){
        Pacman pacman = new Pacman();
    }

    private void KunJiDaZhanAction(){
        ShootGame.main();
    }

    private void TetrisAction(){
        Tetris tetris = new Tetris();
    }

    private void PinduAction(){
        MainJFrame mainJFrame = new MainJFrame();
    }

    private void WuZiQiAction(){
        ChessJFrame chessJFrame = new ChessJFrame();
        chessJFrame.setLocationRelativeTo(null);//居中显示
        chessJFrame.setVisible(true);//设置为可见
    }

    private void JinZiQiAction(){
        TicTacToeGame.main();
    }

    private void SnakeAction(){
        RetroSnakeGame retroSnakeGame = new RetroSnakeGame();
    }

    private void BombermanAction(){
        GameLauncher.main(new String[0]);
    }

    private void BreakOutAction(){
        new Main();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("FakeSteam");
        button1 = new JButton();
        label1 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        label2 = new JLabel();
        label4 = new JLabel();
        label6 = new JLabel();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        button11 = new JButton();
        button12 = new JButton();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        游戏库 = new JLabel();

        //======== this ========
        setTitle(bundle.getString("this.title"));
        var contentPane = getContentPane();

        //---- button1 ----
        button1.setIcon(new ImageIcon(getClass().getResource("/gameicon/saolei.png")));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mines();
            }});

        //---- label1 ----
        label1.setText(bundle.getString("label1.text"));
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- button2 ----
        button2.setIcon(new ImageIcon(getClass().getResource("/gameicon/pacman.png")));
        button2.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
            PacManAction();
        }});

        //---- button3 ----
        button3.setIcon(new ImageIcon(getClass().getResource("/gameicon/kunjidazhan.png")));
        button3.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                KunJiDaZhanAction();
            }});

        //---- button4 ----
        button4.setIcon(new ImageIcon(getClass().getResource("/gameicon/russiablock.png")));
        button4.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                TetrisAction();
            }});

        //---- label2 ----
        label2.setText(bundle.getString("label2.text"));
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label4 ----
        label4.setText(bundle.getString("label4.text"));
        label4.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label6 ----
        label6.setText(bundle.getString("label6.text"));
        label6.setHorizontalAlignment(SwingConstants.CENTER);

        //---- button6 ----
        button6.setIcon(new ImageIcon(getClass().getResource("/gameicon/bomberman.png")));
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BombermanAction();
            }});

        //---- button7 ----
        button7.setIcon(new ImageIcon(getClass().getResource("/gameicon/pindu.png")));
        button7.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                PinduAction();
            }});

        //---- button8 ----
        button8.setIcon(new ImageIcon(getClass().getResource("/gameicon/wuziqi.png")));
        button8.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                WuZiQiAction();
            }});

        //---- button9 ----
        button9.setIcon(new ImageIcon(getClass().getResource("/gameicon/tantanqiu.png")));
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BreakOutAction();
            }});

        //---- label7 ----
        label7.setText(bundle.getString("label7.text"));
        label7.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label8 ----
        label8.setText(bundle.getString("label8.text"));
        label8.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label9 ----
        label9.setText(bundle.getString("label9.text"));
        label9.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label10 ----
        label10.setText(bundle.getString("label10.text"));
        label10.setHorizontalAlignment(SwingConstants.CENTER);

        //---- button11 ----
        button11.setIcon(new ImageIcon(getClass().getResource("/gameicon/jinziqi.png")));
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JinZiQiAction();
            }});

        //---- button12 ----
        button12.setIcon(new ImageIcon(getClass().getResource("/gameicon/snake.png")));
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeAction();
            }});

        //---- label12 ----
        label12.setText(bundle.getString("label12.text"));
        label12.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label13 ----
        label13.setText(bundle.getString("label13.text"));
        label13.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label14 ----
        label14.setText(bundle.getString("label14.text"));
        label14.setHorizontalAlignment(SwingConstants.CENTER);
        label14.setFont(label14.getFont().deriveFont(label14.getFont().getStyle() | Font.BOLD, 20f));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button6, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button7, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label6, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(button3, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                    .addGap(40, 40, 40)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                                    .addGap(40, 40, 40)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(button11, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label13, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button8, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                    .addGap(40, 40, 40)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button9, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                    .addGap(40, 40, 40)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button12, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(219, 219, 219)
                            .addComponent(label14, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(68, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(label14, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addGap(51, 51, 51)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button11, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label13, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGap(70, 70, 70)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(button6, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button7, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button8, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                            .addComponent(button12, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                        .addComponent(button9, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(59, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());

        //---- 游戏库 ----
        游戏库.setText(bundle.getString("\u6e38\u620f\u5e93.text"));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button1;
    private JLabel label1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label2;
    private JLabel label4;
    private JLabel label6;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JButton button11;
    private JButton button12;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel 游戏库;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
