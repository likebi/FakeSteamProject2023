package Pacman;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Pacman extends JFrame  {
    private Clip clip;
    private Model model;

    public Pacman(){
        model = new Model();
        add(model);
        setVisible(true);
        setTitle("Pacman");
        setSize(380,420);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 在窗口关闭时调用你的方法
                model.stopBackgroundMusic();
            }

        });
    }
}