package kursJava.Fruit_Shooter_Game;

import java.awt.*;

public class Charakterss {
    protected Image image;
    private int x;
    private int y;
    private int w;
    private int h;

    public Charakterss(String imagePath, int width, int height) {
        this.image = Toolkit.getDefaultToolkit().getImage(imagePath).getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight() {
        return image.getHeight(null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXspeed(int x,int speed) {
        this.x = x+speed;
    }

    public void setYspeed(int y,int speed) {
        this.y = y+speed;
    }

    public void resize(int w, int h){
        this.w = w;
        this.h = h;
        this.image = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
