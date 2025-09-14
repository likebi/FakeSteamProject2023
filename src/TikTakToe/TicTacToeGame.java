package TikTakToe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

public class TicTacToeGame {
    public static void main() {
        new TicTacToe();
    }
}

class TicTacToe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] button = new JButton[9];
    Boolean player1_turn;
    int movesLeft;
    Clip bgMusic;
    Clip victorySound;

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            button_panel.add(button[i]);
            button[i].setFont(new Font("Arial", Font.BOLD, 120));
            button[i].setFocusable(false);
            button[i].addActionListener(this);
        }

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(0, 255, 0));
        textfield.setFont(new Font("Arial", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        loadBackgroundMusic();
        loadVictorySound();
        firstTurn();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 在窗口关闭时调用你的方法
                stopBgm();
            }

        });

    }

    private void loadBackgroundMusic() {
        try {
            File soundFile = new File("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\TikTakToe\\Audio\\background_[cut_199sec].wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            bgMusic = AudioSystem.getClip();
            bgMusic.open(audioInputStream);
            bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopBgm(){
        if (bgMusic != null) {
            bgMusic.stop();
        }
    }

    private void loadVictorySound() {
        try {
            File soundFile = new File("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\TikTakToe\\Audio\\victory.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            victorySound = AudioSystem.getClip();
            victorySound.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playVictorySound() {
        Thread soundThread = new Thread(() -> {
            if (victorySound.isRunning()) {
                victorySound.stop();
            }
            victorySound.setFramePosition(0);
            victorySound.start();
        });
        soundThread.start();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == button[i]) {
                if (player1_turn) {
                    if (button[i].getText() == "") {
                        button[i].setForeground(new Color(255, 0, 0));
                        button[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O Player's Turn");
                        movesLeft--;
                        if (checkWin("X")) {
                            playVictorySound();
                            JOptionPane.showMessageDialog(frame, "Player X wins!");
                            resetBoard();
                        } else if (movesLeft == 0) {
                            JOptionPane.showMessageDialog(frame, "It's a draw!");
                            resetBoard();
                        }
                    }
                } else {
                    if (button[i].getText() == "") {
                        button[i].setForeground(new Color(0, 0, 255));
                        button[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X Player's Turn");
                        movesLeft--;
                        if (checkWin("O")) {
                            playVictorySound();
                            JOptionPane.showMessageDialog(frame, "Player O wins!");
                            resetBoard();
                        } else if (movesLeft == 0) {
                            JOptionPane.showMessageDialog(frame, "It's a draw!");
                            resetBoard();
                        }
                    }
                }
            }
        }
    }

    public void firstTurn() {
        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X Player's Turn");
        } else {
            player1_turn = false;
            textfield.setText("O Player's Turn");
        }
        movesLeft = 9;
    }

    public boolean checkWin(String symbol) {
        if (
                (button[0].getText().equals(symbol) && button[1].getText().equals(symbol) && button[2].getText().equals(symbol)) ||
                        (button[3].getText().equals(symbol) && button[4].getText().equals(symbol) && button[5].getText().equals(symbol)) ||
                        (button[6].getText().equals(symbol) && button[7].getText().equals(symbol) && button[8].getText().equals(symbol)) ||
                        (button[0].getText().equals(symbol) && button[3].getText().equals(symbol) && button[6].getText().equals(symbol)) ||
                        (button[1].getText().equals(symbol) && button[4].getText().equals(symbol) && button[7].getText().equals(symbol)) ||
                        (button[2].getText().equals(symbol) && button[5].getText().equals(symbol) && button[8].getText().equals(symbol)) ||
                        (button[0].getText().equals(symbol) && button[4].getText().equals(symbol) && button[8].getText().equals(symbol)) ||
                        (button[2].getText().equals(symbol) && button[4].getText().equals(symbol) && button[6].getText().equals(symbol))
        ) {
            return true;
        }
        return false;
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            button[i].setText("");
        }
        firstTurn();
    }
}
