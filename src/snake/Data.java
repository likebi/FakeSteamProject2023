package snake;

import javax.swing.*;
import java.net.URL;

public class Data {

    private static URL bodyURL = Data.class.getResource("image/body.png");
    private static ImageIcon body = new ImageIcon(bodyURL);    private static ImageIcon food = new ImageIcon(Data.class.getResource("image/bianbian.png"));
    private static ImageIcon up = new ImageIcon(Data.class.getResource("image/up.png"));
    private static ImageIcon down = new ImageIcon(Data.class.getResource("image/down.png"));
    private static ImageIcon left = new ImageIcon(Data.class.getResource("image/left.png"));
    private static ImageIcon right = new ImageIcon(Data.class.getResource("image/right.png"));

    public static ImageIcon getBody() {

        return body;
    }

    public static void setBody(ImageIcon body) {

        Data.body = body;
    }

    public static ImageIcon getUp() {

        return up;
    }

    public static void setUp(ImageIcon up) {

        Data.up = up;
    }

    public static ImageIcon getDown() {

        return down;
    }

    public static void setDown(ImageIcon down) {

        Data.down = down;
    }

    public static ImageIcon getLeft() {

        return left;
    }

    public static void setLeft(ImageIcon left) {

        Data.left = left;
    }

    public static ImageIcon getRight() {

        return right;
    }

    public static void setRight(ImageIcon right) {

        Data.right = right;
    }

    public static ImageIcon getFood() {

        return food;
    }

    public static void setFood(ImageIcon food) {

        Data.food = food;
    }
    private static URL eatURL = Data.class.getResource("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\snake\\Audio\\eating.wav");
    private static URL gameOverURL = Data.class.getResource("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\snake\\Audio\\snakegameover.wav");
    private static URL bgmURL = Data.class.getResource("C:\\Users\\zijun\\IdeaProjects\\Java_ShiXun2023_FinalProject\\src\\snake\\Audio\\snake bg.wav");
    public static URL getEatURL() {
        return eatURL;
    }

    public static URL getGameOverURL() {
        return gameOverURL;
    }

    public static URL getBgmURL() {
        return bgmURL;
    }

}